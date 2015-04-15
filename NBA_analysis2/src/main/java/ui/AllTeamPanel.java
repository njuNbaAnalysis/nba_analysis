package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;

import logic.BLController;
import logic.teams.Team;

public class AllTeamPanel extends JPanel{
	
	ArrayList<Team> teams;
	int width;
	int height;
	private JPanel content;
	

	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(new Color(30, 81, 140));
		g.fillRect(0, 0, width, height/10);
		g.setColor(Color.WHITE);
		g.setFont(new Font("default", Font.BOLD, 50));
		g.drawString("球队列表", 30, 70);
		
		for(int i=0;i<3;i++){
			for(int j = 0 ; j<10;j++){
				g.drawImage(teams.get(i*10+j).getLogo(96, 80), 100+150*j, 200+300*i, this);
			}
		}
		
	}
	
	public AllTeamPanel(final int width, final int height, final BLController bl,JPanel content){
		this.width=width;
		this.height=height;
		this.content = content;
		
		this.setBounds(0, 0, width, height);
		this.setLayout(null);
		setBackground(Color.white);
		
	    teams = bl.getAllTeams();
		
		for(int i = 0 ; i < 3 ; i++  ){
			for(int j = 0 ; j<10;j++){
				final int row = i;
				final int colum = j;
				JButton each = new JButton(teams.get(i*10+j).getName());
				each.setBounds(100+150*j, 300+300*i, 96, 30);
				each.setBackground(new Color(30,81,140));
				each.setFocusable(false);
				each.setForeground(Color.white);
				each.setBorderPainted(false);
				each.addMouseListener(new MouseAdapter() {
		            @Override
		            public void mouseClicked(MouseEvent e) {
		         
		            	TeamInfoPanel m = new TeamInfoPanel(width,height*10/9,teams.get(row*10+colum),bl,AllTeamPanel.this.content);
		            	m.setBounds(0, 0, width, height*10/9);
		            	AllTeamPanel.this.content.removeAll();
		            	AllTeamPanel.this.content.add(m);
		            	AllTeamPanel.this.content.updateUI();
		                
		            }
		        });
				this.add(each);
			}
		}
		
	}
}
