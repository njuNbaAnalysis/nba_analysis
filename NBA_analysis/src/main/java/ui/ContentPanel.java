package ui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	private MenuPanel menu;
	private PlayerStatTablePanel teamRankTablePanel;
	
	public ContentPanel(int width,int height, BLService bl) {
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setLayout(null);
		teamRankTablePanel = new PlayerStatTablePanel(width,height,bl);
		teamRankTablePanel.setBounds(width/10, 0, width-width/10, height);
		menu = new MenuPanel(width,height,teamRankTablePanel);
		menu.setBounds(0, 0, width/10, height);
		this.add(menu);
		this.add(teamRankTablePanel);
		
	}
	
}
