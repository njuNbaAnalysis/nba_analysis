package ui;

import java.awt.Image;
import java.awt.image.RenderedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import logic.BLController;
import logic.BLService;

public class UITest{
	BLService bl = BLController.getInstance();
	Image i;

	public UITest() {
		//f = new JFrame();
		
		//this.getContentPane().setLayout(new GridLayout(1, 2));
		while (bl.getProgress() < 9) {
		    
		    try {
                Thread.sleep(300);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
		    
		}
		i = bl.getAllTeams().get(1)
				.getLogo(1380,768);
		try {
			ImageIO.write((RenderedImage) i, "JPG",new File("D:\\test.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	
	public static void main(String[] args) {
		UITest test = new UITest();
		
	}
}
