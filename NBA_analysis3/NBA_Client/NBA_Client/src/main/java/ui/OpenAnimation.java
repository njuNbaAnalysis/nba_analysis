package ui;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OpenAnimation {
	AnimationFrame gameFrame;
	public DisplayJPanel j;
	int x;
	int y;
	Image image = null;
  
	public OpenAnimation(AnimationFrame gameFrame, int x, int y) {
		this.gameFrame = gameFrame;
		this.x = x;
		this.y = y;

		try {
			BufferedImage image0 = ImageIO.read(new FileInputStream("image"
					+ File.separator + "entrance.png"));
			image = image0;
		} catch (IOException e) {
			System.out.print(e);
		}

	}

	public void animation() {
		j = new DisplayJPanel(image,gameFrame.reader);
		j.setOpaque(false);
		gameFrame.lp.add(j, new Integer(300));

		j.setSize(901, 649);
		j.setLocation(x, y);
		j.setVisible(true);

		new Thread(j).start();

	}

}
