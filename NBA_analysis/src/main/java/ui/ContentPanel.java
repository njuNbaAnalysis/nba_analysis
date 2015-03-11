package ui;

import javax.swing.BoxLayout;
import javax.swing.JPanel;

public class ContentPanel extends JPanel {
	private MenuPanel menu;
	private TeamRankTablePanel teamRankTablePanel;
	
	public ContentPanel(int width,int height) {
		//setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setLayout(null);
		menu = new MenuPanel(width,height);
		menu.setBounds(0, 0, width/10, height);
		teamRankTablePanel = new TeamRankTablePanel();
		teamRankTablePanel.setBounds(width/10, 0, width-width/10, height);
		this.add(menu);
		this.add(teamRankTablePanel);
		
	}
	
}
