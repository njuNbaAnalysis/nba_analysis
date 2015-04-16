package ui;

import javax.swing.JPanel;

import ui.MatchPanel.InfoLabel;
import logic.BLController;
import logic.matches.Match;

public class MatchInfoPanel extends JPanel {
	int width;
	int height;
	Match match;
	
	
	public MatchInfoPanel(int width,int height,Match match,BLController bl){
		this.width = width;
		this.height = height;
		this.match = match;
		this.setLayout(null);
		this.setBounds(0,0,width,height);
		MatchPanel temp = new MatchPanel(width, height, null, null);
		InfoLabel info = temp.new InfoLabel(0, 200,
				width, 300, match);
		
		this.add(info);
		
		MatchTablePanel table =  new MatchTablePanel(width, 800, match, bl);
		table.setBounds(0, 500, width, 800);
		this.add(table);
	}
	
}
