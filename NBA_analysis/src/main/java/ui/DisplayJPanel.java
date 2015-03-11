package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

public class DisplayJPanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1903514087174132006L;
	Image image =null;
	int progress=0;
	
	public void paintComponent(Graphics g){ 
		    
    		g.drawImage(image, 0, 0, this);  
    		g.setColor(new Color(1f,1f,1f,0.5f));
			g.fillRect(0, 628, progress, 20);
   	}
    	
	public DisplayJPanel(Image image){
    	this.image = image;
    	
    	
    }

	public void run() {
		while(progress++<=901){
			repaint();
			try {
				Thread.sleep(3);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
		}
	}
}
