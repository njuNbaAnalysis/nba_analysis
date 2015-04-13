package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;

import javax.swing.JLabel;

import logic.BLController;
import logic.BLService;
import logic.players.Player;
import logic.teams.Team;

public class TeamBasicInfoLabel extends JLabel {
	private Team team;
	private int width;
	private int height;

	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(Color.white);
		g.fillRect(0, 0, 1800, 300);
		// g.setColor(new Color(150, 150, 150, 222));
		
		
		for (int i = 0; i < 20; i++) {
			double x = Math.random();
			g.setColor(new Color(180+(int)(20*x),190+(int)(20*x),197+(int)(20*x),120));
			g.fillRect(0, 10*i, (int)(x*100+100) , 10);
		}
		g.setColor(new Color(190, 157, 83));
		g.fillRect(0, 130, 1800, 35);
		g.drawImage(team.getLogo(200, 200), 0, 0, this);

		g.setColor(new Color(190, 157, 83));
		g.setFont(new Font("default", Font.BOLD, 50));
		g.drawString(team.getAbbreviation() + "", 420, 60);
		g.setColor(new Color(218, 218, 218));
		g.drawLine(550, 20, 550, 80);
		g.setColor(new Color(68, 68, 68));
		g.setFont(new Font("default", Font.ROMAN_BASELINE, 25));
		g.drawString(team.getNumOfVictory() + "胜-" + (team.getNumOfMatches()-team.getNumOfVictory())
				+ "负", 555, 40);
		g.setFont(new Font("default", Font.PLAIN, 20));

		g.setColor(new Color(30, 81, 140));
		g.drawString("主场:" + team.getHomeCourt(), 555, 70);
		
		g.drawString("在"+(team.getConference()=='E'?"东部":"西部")+"排名#"+team.getRankingInLeague(),555,95);
		g.setColor(new Color(68, 68, 68));
		g.setFont(new Font("default", Font.PLAIN, 15));
		g.drawString("场均得分", 950, 125);
		g.drawString("场均篮板", 1050, 125);
		g.drawString("场均助攻", 1150, 125);
		g.drawString((team.getAveragePoints() + "").substring(0, 5), 950, 152);
		g.drawString((team.getAverageRebounds() + "").substring(0, 4), 1050,
				152);
		g.drawString((team.getAverageAssists() + "").substring(0, 4), 1150, 152);
		g.setColor(new Color(30, 81, 140));
		g.setFont(new Font("default", Font.PLAIN, 15));
		g.drawString("常规赛数据", 850, 152);

	}

	public TeamBasicInfoLabel(Team team, int width, int height) {
		super();
		/*
		 * this.player = player; this.width = width; this.height = height;
		 * this.setSize(width, height);
		 */

		this.team = team;
		this.height = height;
		this.width = width;
		this.setSize(width, height);

	}

}
