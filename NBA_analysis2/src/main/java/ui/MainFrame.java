package ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import logic.BLController;
import logic.BLService;

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private Dimension screenSize ;
	BLService bl;
	

	/**
	 * Create the frame.
	 * @param bl 
	 */
	public MainFrame(BLController  bl) {
		this.bl=bl;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
	    screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		System.out.println((int)screenSize.getWidth()+" "+(int)screenSize.getHeight());
//		setBounds((int)screenSize.getWidth(), 0, (int)screenSize.getWidth(), (int)screenSize.getHeight());
		setBounds(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight());
		//setBounds((int)screenSize.getWidth(), 0, (int)screenSize.getWidth(), (int)screenSize.getHeight());
		setBounds(0, 0, (int)screenSize.getWidth(), (int)screenSize.getHeight());
		contentPane = new ContentPanel((int)screenSize.getWidth(), (int)screenSize.getHeight(),bl);
		
		//contentPane = new TeamRankTablePanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}

}
