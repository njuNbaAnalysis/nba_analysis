package ui;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.table.DefaultTableModel;

import logic.BLController;
import logic.BLService;
import logic.players.Player;

public class PlayerInfoPanel extends JPanel {
	private BLService bl;
	private PlayerBasicInfoLabel playerBasicInfoLabel;
	private PlayerDetailPanel playerDetailPanel;
	private PlayerDetailTablePanel playerDetailTablePanel;

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		

	}

	public PlayerInfoPanel(int width, int height, Player player,BLService bl) {
		this.bl = bl;
		this.setLayout(null);
		//for test
		//Player p = bl.getAllPlayers().get(0);
		
		playerBasicInfoLabel = new PlayerBasicInfoLabel(player,width,height/4);
		playerBasicInfoLabel.setBounds(0, 0, width, height/6);
		this.add(playerBasicInfoLabel);
		
		playerDetailPanel = new PlayerDetailPanel(player, bl.getAlliancePlayerAverageData(),width, height/4);
		playerDetailPanel.setBounds(0, height/4, width, height/4);
		this.add(playerDetailPanel);
		
		playerDetailTablePanel = new PlayerDetailTablePanel(player, width, height/2);
		playerDetailTablePanel.setBounds(0, height/2, width, height/2);
		this.add(playerDetailTablePanel);
		
	}
	
	public void startAnimation(){
		playerDetailPanel.go();
	}

	
}
