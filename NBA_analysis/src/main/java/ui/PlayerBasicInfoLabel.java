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

public class PlayerBasicInfoLabel extends JLabel{
	private Player player;
	private int width;
	private int height;

	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2.create();
		g.setColor(Color.white);
		g.fillRect(0, 0, 1800, 300);
		g.setColor(new Color(190,157,83,200));
		g.fillRect(0, 100, 1800, 35);
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING , RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(player.getPortrait(), 0, 0, this);
		g.setColor(new Color(190,157,83));
		g.setFont(new Font("default", Font.BOLD, 50));
		g.drawString(player.getNumber()+"", 500, 60);
		g.setColor(new Color(218,218,218));
		g.drawLine(550, 20, 550, 80);
		g.setColor(new Color(68,68,68));
		g.setFont(new Font("default", Font.ROMAN_BASELINE, 25));
		g.drawString(player.getName(), 555, 45);
		g.setFont(new Font("default", Font.PLAIN, 20));
		g.drawString(player.getPosition(), 555, 80);
		g.setColor(new Color(30,81,140));
		g.drawString(player.getTeam(), 600, 80);
		g.setColor(new Color(68,68,68));
		g.setFont(new Font("default", Font.PLAIN, 15));
		g.drawString("场均得分", 450, 98);
		g.drawString("场均篮板", 550, 98);
		g.drawString("场均助攻", 650, 98);
		g.setColor(new Color(68,68,68));
		g.setFont(new Font("default", Font.BOLD, 25));
		g.drawString((player.getAveragePoints()+"").substring(0,3), 455, 126);
		g.drawString((player.getAverageRebounds()+"").substring(0,3), 555, 126);
		g.drawString((player.getAverageAssists()+"").substring(0,3), 655, 126);
		
		
	}

	public PlayerBasicInfoLabel(Player player, int width, int height) {
		super();
		/*this.player = player;
		this.width = width;
		this.height = height;
		this.setSize(width, height);*/
		BLService bl = BLController.getInstance();
		this.player = bl.getAllPlayers().get(0);
		this.height=150;
		this.width=1728;
		this.setSize(1728, 150);
			
	}

	
}
	