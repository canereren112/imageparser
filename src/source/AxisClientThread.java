package source;

import java.io.OutputStreamWriter;
import java.io.BufferedWriter;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;
//import java.text.SimpleDateFormat;
//import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AxisClientThread implements Runnable {

	public static int port;
	public static String serverName;
	private String threadName;
	private int threadId;
	private static List<ClientInformation> clientInformationList;
	
	public AxisClientThread(String threadName, int threadId) {
		this.threadName = threadName;
		this.threadId = threadId;
	}
	
	
	
	@Override
	public void run() {
		System.out.println(this.threadName + " " + this.threadId + " thread started...");
		JLabel jLabel = null;
		try {
			System.out.println("Connecting to " + serverName + " on port " + port);
			Socket client = new Socket(serverName, port);
			System.out.println("Just connected to " + client.getRemoteSocketAddress());

			try {
				InputStream is1 = client.getInputStream();
				InputStreamReader isr1 = new InputStreamReader(is1);
				BufferedReader br1 = new BufferedReader(isr1);
				String message1 = br1.readLine();
				//System.out.println("Available resolution: " + message1);
			} catch(Exception e) {
				e.printStackTrace();
			}

			OutputStream os = client.getOutputStream();			
			
			String resolution = clientInformationList.get(threadId).getResolution();
			String fps = clientInformationList.get(threadId).getFps();

			OutputStreamWriter osw1 = new OutputStreamWriter(os);
			BufferedWriter bw1 = new BufferedWriter(osw1);
			String send = "resolution=" + resolution + "&" + "fps=" + fps;
			
			
			bw1.write(send);
			bw1.flush();

			System.out.println("Sent " + send + " to server.");
			int size;
			int i, j = 0;
			
			try {
				InputStream in = client.getInputStream();
				DataInputStream data = new DataInputStream(in);

				while (true) {
					size = data.readInt();
					System.out.println("Frame size: " + size);

					byte[] bytes = new byte[size]; 
					for(i = 0; i < size; i++) {
						in.read(bytes, i, 1);
					}

					System.out.println("Refreshing the Image");
					InputStream tempInputStream = new ByteArrayInputStream(bytes);
					BufferedImage image = ImageIO.read(tempInputStream);

					if (jLabel != null) {
						jLabel.setIcon(new ImageIcon(image));
						jLabel.repaint();
					} else {
						jLabel = AxisFileReader.showImagePane(image);
					}
					j++;
				}
			} catch(Exception e) {
				e.printStackTrace();
			}
			client.close();
		} catch (IOException e) {
				e.printStackTrace();
		}
	}


	public static void fillServerNameAndPort(String[] args) {
		System.out.println("IP is " + args[0]);
		System.out.println("Port is " + args[1]);

		AxisClientThread.serverName = args[0];
		AxisClientThread.port = Integer.parseInt(args[1]);
	}


	public static void setClientInformationList(List<ClientInformation> clientInformationList) {
		AxisClientThread.clientInformationList = clientInformationList;
	}
}
