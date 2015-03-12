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

public class MainFrame extends JFrame {

	private JPanel contentPane;
	private Dimension screenSize ;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame frame = new MainFrame();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setUndecorated(true);
	    screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		System.out.println((int)screenSize.getWidth()+" "+(int)screenSize.getHeight());
		//setBounds((int)screenSize.getWidth(), 0, (int)screenSize.getWidth(), (int)screenSize.getHeight());
		setBounds((int)screenSize.getWidth(), 0, (int)screenSize.getWidth(), (int)screenSize.getHeight());
		contentPane = new ContentPanel((int)screenSize.getWidth(), (int)screenSize.getHeight());
		
		//contentPane = new TeamRankTablePanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
	}

}
