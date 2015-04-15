package ui;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import logic.BLController;
import logic.teams.Team;

public class AllTeamPanel extends JPanel{
	
	ArrayList<Team> teams;
	

	public void paintComponent(Graphics g) {
		g.setColor(new Color(31, 31, 31));
		for(int i=0;i<3;i++){
			for(int j = 0 ; j<10;j++){
				g.drawImage(teams.get(i*10+j).getLogo(96, 80), 100+150*j, 200+300*i, this);
			}
		}
		
	}
	
	public AllTeamPanel(int width, int height, BLController bl){
		this.setBounds(0, 0, width, height);
		this.setLayout(null);
		setBackground(Color.white);
		
	    teams = bl.getAllTeams();
		
		for(int i = 0 ; i < 3 ; i++  ){
			for(int j = 0 ; j<10;j++){
				JButton each = new JButton(teams.get(i*10+j).getName());
				each.setBounds(100+150*j, 300+300*i, 96, 30);
				each.setBackground(new Color(30,81,140));
				each.setBorderPainted(false);
				this.add(each);
			}
		}
		
	}
}
