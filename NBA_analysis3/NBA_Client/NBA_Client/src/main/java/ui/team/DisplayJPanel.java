package ui.team;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;

import BLservice.BLservice;



public class DisplayJPanel extends JPanel implements Runnable {
	
	private static final long serialVersionUID = 1903514087174132006L;
	Image image =null;
	int progress=0;
	ImageReader reader =null;
	public void paintComponent(Graphics g){ 
		    
    		g.drawImage(image, 0, 0, this);  
    		g.setColor(new Color(1f,1f,1f,0.5f));
			g.fillRect(0, 638, progress, 10);
   	}
    	
	public DisplayJPanel(Image image, ImageReader reader){
    	this.image = image;
    	this.reader = reader;
    	
    }

	public void run() {
		while(progress<=reader.getProgress()*100-1||reader.getProgress()<9){

			if(progress<=reader.getProgress()*100-1){
				progress++;
			}
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
