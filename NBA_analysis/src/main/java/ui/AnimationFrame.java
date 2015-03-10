package ui;

import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

public class AnimationFrame  extends JFrame{
	 public JLayeredPane lp = null;
	 
	public AnimationFrame() {
		lp = this.getLayeredPane();
		int height = Toolkit.getDefaultToolkit().getScreenSize().height;
	    int width = Toolkit.getDefaultToolkit().getScreenSize().width;
		this.setSize(400, 245);
       
        
        this.setTitle("Papers&Tapes");
        this.setLocation((width - 400) / 2, (height - 245) / 2);
        this.setResizable(false);
        this.setUndecorated(true);
        this.setVisible(true);
        this.setLayout(null);
        

        OpenAnimation2 open = new OpenAnimation2(this,0,0);
        open.animation();
	}

}