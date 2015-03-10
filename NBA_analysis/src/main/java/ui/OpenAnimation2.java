package ui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;


public class OpenAnimation2 {
	AnimationFrame gameFrame;
	public DisplayJPanel2 j;
	int x;
	int y;
	Image image = null;
	
	public OpenAnimation2(AnimationFrame gameFrame,int x,int y) {
	this.gameFrame=gameFrame;
	this.x=x;
	this.y=y;
    
	
		
		try{
			BufferedImage image0 = ImageIO.read(new FileInputStream("open"+File.separator+"HOME.png"));
			image = image0;
		}catch(IOException e){
			System.out.print(e);
		}
	
	}
	

	public void animation(){
		j = new DisplayJPanel2(image);
		gameFrame.lp.add(j, new Integer(300));
		j.setSize(600*2/3, 368*2/3);
		j.setLocation(x,y);
		j.setVisible(true);
		j.setOpaque(false);
		
		new Thread(j).start();
		
		
		
		
	

	}
		
		
	

}
