package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class DisplayJPanel2 extends JPanel implements Runnable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1903514087174132006L;
	Image image =null;
	
	public void paintComponent(Graphics g){
        
    		g.drawImage(image, 0, 0, this);
    		
        	
        	
    		}
    	
	public DisplayJPanel2(Image image){
    	this.image = image;
    	
    }

	public void run() {
		repaint();
		
	}
}
