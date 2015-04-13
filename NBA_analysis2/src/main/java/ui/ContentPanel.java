package ui;

import javax.swing.JPanel;

import logic.BLController;

public class ContentPanel extends JPanel {
	private MenuPanel menu;
	private JPanel content;
	private PlayerStatTablePanel teamRankTablePanel;
	
	public ContentPanel(int width,int height, BLController bl) {
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setLayout(null);
		content = new JPanel();
		content.setBounds(width/10, 0, width-width/10, height);
		content.setLayout(null);
		teamRankTablePanel = new PlayerStatTablePanel(width,height,bl);
		teamRankTablePanel.setBounds(0, 0, width-width/10, height);
		content.add(teamRankTablePanel);
		
		menu = new MenuPanel(width,height,content,bl);
		menu.setBounds(0, 0, width/10, height);
		this.add(menu);
		this.add(content);
		
	}
	
}
