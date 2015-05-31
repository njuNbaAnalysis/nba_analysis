package ui;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JPanel;

import ui.MatchPanel.InfoLabel;
import logic.BLController;
import logic.matches.Match;

public class MatchInfoPanel extends JPanel {
	int width;
	int height;
	Matchvo match;
	
	
	public MatchInfoPanel(int width,int height,Matchvo match,BLController bl){
		this.width = width;
		this.height = height;
		this.match = match;
		this.setLayout(null);
		setBackground(Color.white);
		this.setBounds(0,0,width,height);
		MatchPanel temp = new MatchPanel(width, height, bl, new JPanel());
		InfoLabel info = temp.new InfoLabel(0, 0,
				width, 300, match);
		
		this.add(info);
		
		MatchTablePanel table =  new MatchTablePanel(width, 800, match, bl);
		table.setBounds(0, 300, width, 1000);
		this.add(table);
		
		this.setPreferredSize(new Dimension(width, 1300));
	}
	
}
