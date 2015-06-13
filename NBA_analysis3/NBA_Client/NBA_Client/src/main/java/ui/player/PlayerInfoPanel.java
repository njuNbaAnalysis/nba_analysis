package ui.player;

import java.awt.Graphics;
import java.awt.Image;
import java.rmi.RemoteException;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import BLservice.BLservice;
import vo.Playervo;

public class PlayerInfoPanel extends JPanel {
	private BLservice bl;
	private PlayerBasicInfoLabel playerBasicInfoLabel;
	private PlayerDetailPanel playerDetailPanel;
	private PlayerDetailTablePanel playerDetailTablePanel;
	private JPanel content;
	private String season;
	private boolean isPlayOff;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		

	}

	public PlayerInfoPanel(int width, int height, Playervo player,BLservice bl,JPanel content,String season,boolean isPlayOff) {
		this.bl = bl;
		this.setLayout(null);
		this.content = content;
		this.season = season;
		this.isPlayOff = isPlayOff;
		
		
		playerBasicInfoLabel = new PlayerBasicInfoLabel(player,width,height/4,content,bl,season,isPlayOff);
		playerBasicInfoLabel.setBounds(0, 0, width, height/6);
		this.add(playerBasicInfoLabel);
		
		double[] data;
		try {
			data = bl.getAlliancePlayerAverageData(season,isPlayOff);
			playerDetailPanel = new PlayerDetailPanel(player, data,width, height/4);
			playerDetailPanel.setBounds(0, height/4, width, height/4);
			this.add(playerDetailPanel);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		try {
			playerDetailTablePanel = new PlayerDetailTablePanel(player, width, height/2,bl,season);
			playerDetailTablePanel.setBounds(0, height/2, width, height/2);
			this.add(playerDetailTablePanel);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	public void startAnimation(){
		playerDetailPanel.go();
	}

	
}
