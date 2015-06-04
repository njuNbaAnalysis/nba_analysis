package ui;

import javax.swing.JPanel;

import BLservice.BLservice;
import logic.BLController;

public class ContentPanel extends JPanel {
	private MenuPanel menu;
	private JPanel content;
	private PlayerStatTablePanel teamRankTablePanel;
	String season="14-15";
	boolean isPlayOff=false;
	
	public ContentPanel(int width,int height, BLservice bl) {
		setLayout(null);
		
		content = new JPanel();
		content.setBounds(width/10, 0, width-width/10, height);
		content.setLayout(null);
		teamRankTablePanel = new PlayerStatTablePanel(width,height,bl,content,season,isPlayOff);
		teamRankTablePanel.setBounds(0, 0, width-width/10, height);
		content.add(teamRankTablePanel);
		
		menu = new MenuPanel(width,height,content,bl,season,isPlayOff);
		menu.setBounds(0, 0, width/10, height);
		this.add(menu);
		this.add(content);
		
	}
	
}
