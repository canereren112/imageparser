package source;

import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class AxisFileReader {
	
	public static synchronized JLabel showImagePane(BufferedImage image){
		
		JLabel label = null ;

		label = new JLabel(new ImageIcon(image));
		new ReceiveImageFrame(label);
		return label;
		
	}

}
