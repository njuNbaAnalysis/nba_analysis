package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class MenuPanel extends JPanel {
	private int width;
	private int height;
	private static Image logo;
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(31,31,31));
		g.fillRect(0, 0, width, height);
		g.drawImage(logo, 0,0,this);
	}
	
	public MenuPanel(int width,int height) {
		this.width=width/10;
		this.height=height;
		
		BufferedImage bufferLogo = null;
	

		try {
			bufferLogo = ImageIO.read(new File("image" + File.separator
					+ "logo3.png"));
			
		} catch (IOException e) {
			e.printStackTrace();
		}

		logo = bufferLogo;
		
	}
	
	
}
