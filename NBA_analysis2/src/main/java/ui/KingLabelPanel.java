package ui;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicLabelUI;

import logic.BLService;
import logic.players.Player;
import logic.teams.Team;
import util.UIUtils;

public class KingLabelPanel extends HotLabelPanel {
	private String type;// 有球员和球队数据王两种，分别用"P"和"T"表示
	private int num = 5;
	private Player[] players;
	private JPanel content;

	KingLabelPanel(String type, String headName, String[] columnName,
			int kingWidth, int kingHeight, BLService bl, JPanel content) {
		super(headName, columnName, kingWidth, kingHeight, bl);
		this.type = type;
		this.content = content;
		setTableHeadLabel();
		setButton(type);
		
		setTableContent(type);
		
		
	}

	public void setTableContent(String type) {
		if (type.equals("P")) {
			Object[] o = bl.getAllPlayers().subList(0, num).toArray();
			Player[] p = new Player[num];
			for (int i = 0; i < num; i++) {
				p[i] = (Player) o[i];
			}
			setPlayerTableContent(p);
		} else if (type.equals("T")) {
			Object[] o = bl.getAllTeams().subList(0, num).toArray();
			Team[] t = new Team[num];
			for (int i = 0; i < num; i++) {
				t[i] = (Team) o[i];
			}
			setTeamTableContent(t);
		} else {
			setPlayerTableContent(null);
		}

	}

	private void setPlayerTableContent(Player[] players) {

		tableContentLabel = new PlayerTableContentLabel(players, hotWidth,
				hotHeight * 2 / 3,"point");
		tableContentLabel.setBounds(0, hotHeight / 3, hotWidth,
				hotHeight * 2 / 3);
		this.players = players;
		this.add(tableContentLabel);
	}

	private void setTeamTableContent(Team[] teams) {
		tableContentLabel = new TeamTableContentLabel(teams, hotWidth,
				hotHeight * 2 / 3);
		tableContentLabel.setBounds(0, hotHeight / 3, hotWidth,
				hotHeight * 2 / 3);
		this.add(tableContentLabel);

	}

	public void setTeams(Team[] teams) {
		((TeamTableContentLabel) tableContentLabel).setTeams(teams);
	}

	public void setPlayers(Player[] players,String field) {
		((PlayerTableContentLabel) tableContentLabel).setPlayers(players,field);
		this.players = players;
	}

	public Player[] getPlayers() {
		return players;
	}

	private class PlayerTableContentLabel extends JLabel {
		private Player[] players;
		private int contentWidth;
		private int contentHeight;
		private JLabel[] playerNames;
		private JLabel[] playerTeamNames;
		private String field;
		public PlayerTableContentLabel(Player[] players, int contentWidth,
				int contentHeight,String field) {
			this.players = players;
			this.contentWidth = contentWidth;
			this.contentHeight = contentHeight;
			this.field = field;
			setPlayerNameLabel();

		}

		public void paintComponent(Graphics g2) {
			// 背景
			Graphics2D g = (Graphics2D) g2.create();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.white);
			g.fillRect(0, 0, contentWidth, contentHeight);
			// 头像
			if(players[0]!=null){
				BufferedImage action = UIUtils.resize(players[0].getAction(),
						contentWidth / 10, contentHeight);
				g.drawImage(action, 0, 0, this);
				// 排名
				g.setColor(new Color(190, 157, 83));
				g.setFont(new Font("微软雅黑", Font.BOLD, 50));
				g.drawString(1 + "", contentWidth / 5, contentHeight * 2 / 5);
				// 姓名

				playerNames[0].setText(players[0].getName());

				// 号码
				g.setColor(new Color(68, 68, 68));
				g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
				g.drawString(Integer.toString(players[0].getNumber()), contentWidth / 5,
						contentHeight * 3 / 5);
				// 位置
				g.setColor(new Color(68, 68, 68));
				g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
				g.drawString(players[0].getPosition(), contentWidth / 4,
						contentHeight * 3 / 5);
				// 球队
				playerTeamNames[0].setText(players[0].getTeam());
				// 数据、球队图标暂无
				
				String data = getPlayerData(players[0]);
				g.setColor(new Color(68, 68, 68));
				g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
				g.drawString(data, contentWidth / 4,
						contentHeight * 4 / 5);
				
				
				
				
				
				g.setColor(new Color(246, 246, 246));
				g.fillRect(contentWidth / 2, 0, contentWidth / 10,
						contentHeight * 9 / 10);
			}
			

			for (int i = 2; i <= num; i++) {
				if(players[i-1]!=null){
					g.setColor(new Color(146, 144, 144));
					g.setFont(new Font("Oswald-Bold", Font.PLAIN, 20));
					g.drawString(i + "", contentWidth * 11 / 20, contentHeight
							* (i - 1) / 5);

					BufferedImage image = UIUtils.resize(
							players[i - 1].getPortrait(), contentWidth / 30,
							contentHeight / 5);

					g.drawImage(image, contentWidth * 3 / 5, contentHeight
							* (8 * i - 13) / 40, this);
					// 球员label
					playerNames[i - 1].setText(players[i - 1].getName());

					g.setColor(new Color(68, 68, 68));
					String str = Integer.toString(players[i - 1].getNumber()) + " "
							+ players[i - 1].getPosition();

					// 球队label
					playerTeamNames[i - 1].setText(players[i - 1].getTeam());
					// JLabel team = new JLabel(players[i - 1].getTeam());

					g.drawString(str, contentWidth * 13 / 20, contentHeight
							* (4 * i - 3) / 20);

					// 没有球队图片，没有球员得分
					String data = getPlayerData(players[i-1]);
					g.setColor(new Color(68, 68, 68));
					g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
					g.drawString(data, contentWidth * 17 / 20,
							contentHeight
							* (4 * i - 3) / 20);
				}
					
				
				
			}

		}

		private String getPlayerData(Player player) {
			DecimalFormat df = new DecimalFormat("#0.0");
			double result = 0;
		//	System.out.println(field);
			switch(field){
			case "point":
			case "场均得分":
			case "得分":
				
				result = player.getAveragePoints();
				break;
			case "rebound":
			case "场均篮板":
			case "篮板":
				result = player.getAverageRebounds();
				break;
			case "assist":
			case "场均助攻":
			case "助攻":
				result = player.getAverageAssists();
				break;
			case "steal":
			case "场均抢断":
			case "抢断":
				result = player.getAverageSteals();
				break;
			case "block":
			case "blockShot":
			case "场均盖帽":
			case "盖帽":
				result = player.getAverageBlockShots();
				break;
			case "三分%":
			case "three":
				result = player.getThreePointersPercentage()*100;
				break;
			
			case  "%":
			case "shot":
				result = player.getFieldGoalsPercentage()*100;
				break;
			case "罚球%":
			case "penalty":
			    result = player.getFreeThrowsPercentage()*100;
				break;
			default:
				System.out.println("Error in ImprovefLabelPanel.paintComponent()!!!"+field);	
			}
			return df.format(result);
		}

		public void setPlayers(Player[] players,String field) {
			this.players = players;
			this.field = field;
			this.repaint();
		}

		private void setPlayerNameLabel() {
			playerNames = new JLabel[5];
			playerTeamNames = new JLabel[5];
			// JLabel teamName = new JLabel(players[0].getName());
			for (int i = 0; i < 5; i++) {
				playerNames[i] = new JLabel();
				playerTeamNames[i] = new JLabel();
				if (i == 0) {
					playerNames[i].setForeground(new Color(68, 68, 68));
					playerNames[i].setFont(new Font("微软雅黑",
							Font.ROMAN_BASELINE, 25));
					playerNames[i].setBounds(contentWidth / 4,
							contentHeight * 1 / 4, contentWidth / 8,
							contentHeight * 3 / 20);

					playerTeamNames[i].setForeground(new Color(68, 68, 68));
					playerTeamNames[i]
							.setFont(new Font("微软雅黑", Font.PLAIN, 20));
					playerTeamNames[i].setBounds(contentWidth * 3 / 10,
							contentHeight * 19 / 40, contentWidth / 8,
							contentHeight * 3 / 20);
				} else {
					playerNames[i].setForeground(Color.black);
					playerNames[i].setFont(new Font("Oswald-Bold", Font.PLAIN,
							15));
					playerNames[i].setBounds(contentWidth * 13 / 20,
							contentHeight * (16 * i - 13) / 80,
							contentWidth * 1 / 15, contentHeight * (1) / 5);

					playerTeamNames[i].setForeground(Color.black);
					playerTeamNames[i].setFont(new Font("Oswald-Bold",
							Font.PLAIN, 15));
					playerTeamNames[i].setBounds(contentWidth * 55 / 80,
							(contentHeight * (8 * i - 3) / 40),
							contentWidth * 1 / 20, contentHeight * (1) / 5);

				}
				playerNames[i].addMouseListener(new PlayerMouseAdapter());
				playerNames[i].setOpaque(false);
				playerNames[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));


				playerTeamNames[i].addMouseListener(new TeamMouseAdapter());
				playerTeamNames[i].setOpaque(false);
				playerTeamNames[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				this.add(playerNames[i]);
				this.add(playerTeamNames[i]);
			}

		}
	}

	private class PlayerMouseAdapter extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			Player p = KingLabelPanel.this.bl.getPlayerByName(((JLabel) e
					.getSource()).getText());
			PlayerInfoPanel playInfoPanel = new PlayerInfoPanel(hotWidth,
					hotHeight * 3, p, KingLabelPanel.this.bl,
					KingLabelPanel.this.content);
			playInfoPanel.setBounds(0, 0, hotWidth, hotHeight * 3);
			KingLabelPanel.this.content.removeAll();
			KingLabelPanel.this.content.add(playInfoPanel);
			KingLabelPanel.this.content.updateUI();
			playInfoPanel.startAnimation();

		}

	}

	private class TeamMouseAdapter extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			Team t = KingLabelPanel.this.bl.getTeamByName(((JLabel) e
					.getSource()).getText());
			TeamInfoPanel teamInfoPanel = new TeamInfoPanel(hotWidth,
					hotHeight * 3, t, KingLabelPanel.this.bl,
					KingLabelPanel.this.content);
			teamInfoPanel.setBounds(0, 0, hotWidth, (int) (hotHeight * 3.5));
			KingLabelPanel.this.content.removeAll();
			KingLabelPanel.this.content.add(teamInfoPanel);
			KingLabelPanel.this.content.updateUI();

		}

	}

	private class TeamTableContentLabel extends JLabel {
		private Team[] teams;
		private int contentWidth;
		private int contentHeight;
		// JLabel teamName;
		JLabel[] teamNames;

		public TeamTableContentLabel(Team[] teams, int contentWidth,
				int contentHeight) {
			this.teams = teams;
			this.contentWidth = contentWidth;
			this.contentHeight = contentHeight;
			setTeamNameLabel();
		}

		public void setTeams(Team[] teams) {
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
			teamNames[0].setText(teams[0].getName());

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
				g.setFont(new Font("default", Font.PLAIN, 15));

				teamNames[i - 1].setText(teams[i - 1].getName());

				String str = teams[i - 1].getConference()
						+ teams[i - 1].getDivision();
				g.drawString(str, contentWidth * 13 / 20, contentHeight
						* (4 * i - 3) / 20);

				// 没有球队得分
			}
		}

		private void setTeamNameLabel() {

			teamNames = new JLabel[5];
			// JLabel teamName = new JLabel(players[0].getName());
			for (int i = 0; i < 5; i++) {

				teamNames[i] = new JLabel();
				if (i == 0) {
					teamNames[i].setForeground(new Color(68, 68, 68));
					teamNames[i].setFont(new Font("微软雅黑", Font.ROMAN_BASELINE,
							25));
					teamNames[i].setBounds(contentWidth / 4,
							contentHeight * 1 / 4, contentWidth / 8,
							contentHeight * 13 / 80);

				} else {

					teamNames[i].setForeground(Color.black);
					teamNames[i]
							.setFont(new Font("Oswald-Bold", Font.PLAIN, 15));
					teamNames[i].setBounds(contentWidth * 13 / 20,
							contentHeight * (16 * i - 13) / 80,
							contentWidth * 1 / 15, contentHeight * (1) / 5);

				}
				teamNames[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
				teamNames[i].addMouseListener(new TeamMouseAdapter());
				teamNames[i].setOpaque(false);
				this.add(teamNames[i]);
			}

		}

	}

}
