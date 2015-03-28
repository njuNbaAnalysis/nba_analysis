package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class PlayerInfoPanel extends JPanel{
	
	
	public PlayerInfoPanel(){
		
	}
	
	
	public class InfoLabel extends JLabel{
		Image portrait;
		Image logo;
		
		public void paintComponent(Graphics g) {
			g.drawImage(portrait, 0, 0, this);
			
		}

		public InfoLabel(Image portrait, Image logo) {
			super();
			this.portrait = portrait;
			this.logo = logo;
		}
		
		
		
		
	}
	
}
