package source;

import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class ReceiveImageFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	public ReceiveImageFrame(JLabel label) {
		super("ReceiveImageFrame");
		this.setSize(500, 500);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.add(label, BorderLayout.CENTER);
		this.setVisible(true);

	}

	}


