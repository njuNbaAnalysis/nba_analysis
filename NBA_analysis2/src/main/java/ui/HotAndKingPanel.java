package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;



import util.UIUtils;
import logic.BLService;
import logic.players.Player;
import logic.players.todayPlayer;
import logic.teams.Team;

public class HotAndKingPanel extends JPanel {
	private int num = 5;
	private int width;
	private int height;
	private BLService bl;
	private static String FONT_OF_HOT = "微软雅黑";
	private static String [] typeArray = {"T","P","HP"};
	private KingLabelPanel teamKingPanel;
	private KingLabelPanel playerKingPanel;
	private ImprovedLabelPanel hotPlayerPanel;

	public HotAndKingPanel(int width, int height, BLService bl) {
		this.width = width;
		this.height = height;
		this.bl = bl;
		this.setLayout(null);
		
		//创建字体
		UIUtils.createFont("font"+File.separator+"Oswald-Bold.otf");
		
		String[] playerColumnName = { "得分", "篮板", "助攻", "抢断", "盖帽" };
		playerKingPanel = new KingLabelPanel("P", "每日 联盟数据王",
				playerColumnName, width*9/10, height / 4,bl);
		playerKingPanel.setBounds(0, 0, width*9/10, height / 4);
		this.add(playerKingPanel);

		String[] teamColumnName = { "得分", "篮板", "助攻", "抢断", "盖帽", "三分%", "%",
				"罚球%" };
		teamKingPanel = new KingLabelPanel("T", "常规赛 联盟球队数据",
				teamColumnName, width*9/10, height / 4,bl);
		teamKingPanel.setBounds(0, height / 4, width*9/10, height / 4);
		this.add(teamKingPanel);

		
		// 设置player
		
		ArrayList<Player> playerList = bl.getMostImprovedPlayer("point", 5);
		Player [] players = new Player[5];
		for(int i=0;i<5;i++){
			players[i] = playerList.get(i);
		}
		String[] hotColumnName = { "场均得分", "场均篮板", "场均助攻" };
		hotPlayerPanel = new ImprovedLabelPanel("热门球员",typeArray[2],
				hotColumnName, players,width, height / 2,bl);
		hotPlayerPanel.setBounds(0, height / 2, width*9/10, height / 2);
		this.add(hotPlayerPanel);

	}
	

	private class PlayerTableContentLabel extends JLabel {
		private Player[] players;
		private int contentWidth;
		private int contentHeight;

		public PlayerTableContentLabel(Player[] players, int contentWidth,
				int contentHeight) {
			this.players = players;
			this.contentWidth = contentWidth;
			this.contentHeight = contentHeight;
		}

		public void paintComponent(Graphics g2) {
			// 背景
			Graphics2D g = (Graphics2D) g2.create();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.white);
			g.fillRect(0, 0, contentWidth, contentHeight);
			// 头像
			BufferedImage action = UIUtils.resize(players[0].getAction(),
					contentWidth / 10, contentHeight);
			g.drawImage(action, 0, 0, this);
			// 排名
			g.setColor(new Color(190, 157, 83));
			g.setFont(new Font("微软雅黑", Font.BOLD, 50));
			g.drawString(1 + "", contentWidth / 5, contentHeight * 2 / 5);
			// 姓名
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 25));
			g.drawString(players[0].getName(), contentWidth / 4,
					contentHeight * 2 / 5);
			// 号码
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			g.drawString(players[0].getNumber() + "", contentWidth / 5,
					contentHeight * 3 / 5);
			// 位置
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			g.drawString(players[0].getPosition(), contentWidth / 4,
					contentHeight * 3 / 5);
			// 球队
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			g.drawString(players[0].getTeam(), contentWidth * 3 / 10,
					contentHeight * 3 / 5);
			// 数据、球队图标暂无

			g.setColor(new Color(246, 246, 246));
			g.fillRect(contentWidth / 2, 0, contentWidth / 10,
					contentHeight * 9 / 10);

			for (int i = 2; i <= num; i++) {
				g.setColor(new Color(146, 144, 144));
				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 20));
				g.drawString(i + "", contentWidth * 11 / 20, contentHeight
						* (i - 1) / 5);

				BufferedImage image = UIUtils.resize(
						players[i - 1].getPortrait(), contentWidth / 30,
						contentHeight / 5);

				g.drawImage(image, contentWidth * 3 / 5, contentHeight
						* (8 * i - 13) / 40, this);

				g.setColor(Color.black);
				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 15));
				g.drawString(players[i - 1].getName(), contentWidth * 13 / 20,
						contentHeight * (4 * i - 5) / 20);
				String str = Integer.toString(players[i - 1].getNumber()) + " "
						+ players[i - 1].getPosition() + " "
						+ players[i - 1].getTeam();
				g.drawString(str, contentWidth * 13 / 20, contentHeight
						* (4 * i - 3) / 20);

				// 没有球队图片，没有球员得分
			}

		}
	    public void setPlayers(Player[] players){
	    	this.players = players;
	    	this.repaint();
	    }
	}

	private class TeamTableContentLabel extends JLabel {
		private Team[] teams;
		private int contentWidth;
		private int contentHeight;

		public TeamTableContentLabel(Team[] teams, int contentWidth,
				int contentHeight) {
			this.teams = teams;
			this.contentWidth = contentWidth;
			this.contentHeight = contentHeight;
		}
		
		public void setTeams(Team[] teams){
			this.teams = teams;
			this.repaint();
		}

		public void paintComponent(Graphics g2) {
			// 背景
			Graphics2D g = (Graphics2D) g2.create();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.white);
			g.fillRect(0, 0, contentWidth, contentHeight);
			// 队标
			BufferedImage action = UIUtils.resize(
					teams[0].getLogo(contentWidth / 12, contentHeight),
					contentWidth / 12, contentHeight);
			g.drawImage(action, 0, 0, this);
			// 排名
			g.setColor(new Color(190, 157, 83));
			g.setFont(new Font("default", Font.BOLD, 50));
			g.drawString(1 + "", contentWidth / 5, contentHeight * 2 / 5);
			// 队名
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("default", Font.ROMAN_BASELINE, 25));
			g.drawString(teams[0].getName(), contentWidth / 4,
					contentHeight * 2 / 5);
			// 联盟
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("default", Font.PLAIN, 20));
			g.drawString(
					teams[0].getConference() + "   " + teams[0].getDivision(),
					contentWidth / 5, contentHeight * 3 / 5);

			// 数据暂无

			g.setColor(new Color(246, 246, 246));
			g.fillRect(contentWidth / 2, 0, contentWidth / 10,
					contentHeight * 9 / 10);

			for (int i = 2; i <= num; i++) {
				g.setColor(new Color(146, 144, 144));
				g.setFont(new Font("default", Font.BOLD, 20));
				g.drawString(i + "", contentWidth * 11 / 20, contentHeight
						* (i - 1) / 5);

				BufferedImage image = UIUtils.resize(teams[i - 1].getLogo(
						contentWidth / 30, contentHeight / 5),
						contentWidth / 30, contentHeight / 5);

				g.drawImage(image, contentWidth * 3 / 5, contentHeight
						* (8 * i - 13) / 40, this);

				g.setColor(Color.black);
				g.setFont(new Font("default", Font.BOLD, 15));
				g.drawString(teams[i - 1].getName(), contentWidth * 13 / 20,
						contentHeight * (4 * i - 5) / 20);
				String str = teams[i - 1].getConference()
						+ teams[i - 1].getDivision();
				g.drawString(str, contentWidth * 13 / 20, contentHeight
						* (4 * i - 3) / 20);

				// 没有球队得分
			}
		}
	}

	

}
