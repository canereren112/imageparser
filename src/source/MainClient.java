package source;

import java.awt.GridLayout;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainClient {
	private String resolutions;
	private List<ClientInformation> clientInformationList;
	
	public MainClient() {

	}
	
	public void fillAvailableScreenResolutionsFromServer() {
		System.out.println("Connecting to " + AxisClientThread.serverName + " on port " + AxisClientThread.port);
		Socket client = null;
		try {
			client = new Socket(AxisClientThread.serverName, AxisClientThread.port);
			System.out.println("Just connected to " + client.getRemoteSocketAddress());
			
			try {
				InputStream is1 = client.getInputStream();
				InputStreamReader isr1 = new InputStreamReader(is1);
				BufferedReader br1 = new BufferedReader(isr1);
				this.resolutions = br1.readLine();
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}finally{
			if(client != null){
				try {
					client.close();
				} catch (IOException e) {
					e.printStackTrace();
				}	
			}
			
		}
	}
	
	public void chooseResolutionAndFpsForClients(int numOfClients){
		JPanel panel = new JPanel(new GridLayout(0, 1));
		List<JTextField> resolutionList = new ArrayList<JTextField>();
		List<JTextField> fpsList = new ArrayList<JTextField>();

		panel.add(new JLabel("Please Choose between available Resolutions as : " ));
		panel.add(new JLabel(getResolutions().substring(0, 84)));
		panel.add(new JLabel(getResolutions().substring(84)));

		for(int i= 0; i < numOfClients; i++){
			JTextField resolutionField = new JTextField();
			JTextField fpsField = new JTextField();
			
			panel.add(new JLabel("Client" + (i+1) +" Resolution: " ));
			panel.add(resolutionField);
			resolutionList.add(resolutionField);
			
			panel.add(new JLabel("FPS : " ));
			fpsList.add(fpsField);
			panel.add(fpsField);
		}


		int result = JOptionPane.showConfirmDialog(null, panel, "Test", JOptionPane.OK_CANCEL_OPTION,
				JOptionPane.PLAIN_MESSAGE);
		
		if (result == JOptionPane.OK_OPTION) {
			fillClientInformationList(resolutionList,fpsList, numOfClients);
		} else {
			System.out.println("Cancelled");
		}
	}
	
	private void fillClientInformationList(List<JTextField> resolutionList, List<JTextField> fpsList, int numOfClients){
		clientInformationList = new ArrayList<ClientInformation>();
		for (int i = 0; i < numOfClients; i++){
			ClientInformation clientInfo = new ClientInformation(resolutionList.get(i).getText(), fpsList.get(i).getText());
			clientInformationList.add(clientInfo);
		}
		AxisClientThread.setClientInformationList(clientInformationList);
	}
	
	public String getResolutions() {
		return resolutions;
	}

	public List<ClientInformation> getClientInformationList() {
		return clientInformationList;
	}



}
