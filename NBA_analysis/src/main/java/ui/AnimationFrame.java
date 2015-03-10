package ui;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class AnimationFrame  extends JFrame{
	 public JLayeredPane lp = null;
	 
	@SuppressWarnings("restriction")
	public AnimationFrame() {
		
		lp = this.getLayeredPane();
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.setSize(901, 649);
       
        
        this.setTitle("Papers&Tapes");
        this.setLocation((width - 901) / 2, (height - 649) / 2);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLayout(null);
        

        OpenAnimation open = new OpenAnimation(this,0,0);
        open.animation();
	}

}