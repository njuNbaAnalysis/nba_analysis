package ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import logic.BLController;
import logic.matches.Match;

public class Test extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		final BLController bl = BLController.getInstance();
		bl.init();
		while(bl.getProgress()!=10){
			System.out.println(bl.getProgress());
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setBounds(1920, 0, 1920, 1080);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(null);
		setContentPane(contentPane);
		contentPane.add(new TeamBasicInfoLabel(bl.getAllTeams().get(0), 192*9, 200));
		
		/*MatchPanel matchPanel = new MatchPanel(0,0,bl);
		JScrollPane scrollPane = new JScrollPane(matchPanel);
		scrollPane.setBounds(0, 0, 1920, 1080);
		contentPane.add(scrollPane);*/
		
		//contentPane.add(matchPanel);
	}

}
