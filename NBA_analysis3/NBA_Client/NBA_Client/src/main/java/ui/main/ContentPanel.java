package ui.main;

import javax.swing.JPanel;

import ui.statistics.PlayerStatTablePanel;
import BLservice.BLservice;

public class ContentPanel extends JPanel {
	private MenuPanel menu;
	private static JPanel content;
	private static int width;
	private static int height;
	static BLservice bl;
	private static PlayerStatTablePanel teamRankTablePanel;
	static String season="14-15";
	static boolean isPlayOff=false;
	
	public ContentPanel(int width,int height, BLservice bl) {
		setLayout(null);
		this.width=width;
		this.height = height;
		this.bl = bl;
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
	
	public static void changeSeason(String seasonInfo){
		season = seasonInfo.substring(0,5);
		isPlayOff  = seasonInfo.contains("季");
		content.removeAll();
		teamRankTablePanel = new PlayerStatTablePanel(width,height,bl,content,season,isPlayOff);
		teamRankTablePanel.setBounds(0, 0, width-width/10, height);
		content.add(teamRankTablePanel);
		content.updateUI();
		
	}

	
}
