package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicLabelUI;

import ui.HotAndKingPanel.KingPanel;
import util.UIUtils;
import logic.BLService;
import logic.players.Player;
import logic.teams.Team;

public class TeamInfoPanel extends JPanel {
	private TeamButton[] btArray;
	private int width;
	private int height;
	private TeamBasicInfoLabel teamBasicInfoPanel;
	private static String[] tabName = { "赛程", "数据王", "阵容" };
	private Team team;
	private BLService bl;
	private KingPanel playerKingPanel;

	public TeamInfoPanel(int width, int height, Team team, BLService bl) {
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.team = team;
		this.setLayout(null);
		this.bl = bl;

		teamBasicInfoPanel = new TeamBasicInfoLabel(team, width, height / 4);
		teamBasicInfoPanel.setBounds(0, 0, width, height / 4);
		this.add(teamBasicInfoPanel);

		String[] columnName = { "得分", "篮板", "助攻", "抢断", "盖帽",
				"三分%", "%", "罚球%" };

		playerKingPanel = new KingPanel("P", "常规赛 数据王",
				columnName, width, height / 3);
		playerKingPanel.setBounds(0, height/3, width, height/3);
		this.add(playerKingPanel);

		setButton();
		
		this.repaint();
	}

	private void setButton() {
		int size = tabName.length;
		btArray = new TeamButton[size];
		for (int i = 0; i < size; i++) {
			btArray[i] = new TeamButton(tabName[i], i);
			btArray[i].setBounds(width * i / size, height / 4, width / size,
					height / 15);
			this.add(btArray[i]);
		}
	}

	private class TeamButton extends JButton {
		private int type;

		public TeamButton(String text, int type) {
			super(text);
			this.type = type;
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setForeground(Color.white);
			this.setBackground(new Color(87, 89, 91));
			this.setBorder(BorderFactory.createLineBorder(new Color(122, 122,
					122), 2));
			this.setFocusPainted(false);

			this.setFont(new Font("微软雅黑", Font.CENTER_BASELINE,
					15 * height / 1280));

			this.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					TeamButton.this.setBackground(new Color(69, 69, 69));
				}

				public void mouseExited(MouseEvent e) {
					TeamButton.this.setBackground(new Color(87, 89, 91));
				}

				// 暂时刷新是假的
				public void mousePressed(MouseEvent e) {
					switch (TeamButton.this.type) {
					case 0:
						// ArrayList<Team> teamList =
						// bl.getSeasonKingTeam(field,5);
						// 赛程
						break;
					case 1:
						// ArrayList<Player> playerList1 =
						// bl.getSeasonKingPlayer(field,5);
						ArrayList<String> nameList = team.getPlayerList();
						Player[] playerList1 = new Player[5];
						for (int i = 0; i < nameList.size(); i++) {
							playerList1[i] = (bl.getPlayerByName(nameList
									.get(i)));
						}
						String[] columnName = { "得分", "篮板", "助攻", "抢断", "盖帽",
								"三分%", "%", "罚球%" };

						playerKingPanel = new KingPanel("P", "常规赛数据王",
								columnName, width, height / 3);
						playerKingPanel.setPlayers(playerList1);
						playerKingPanel.repaint();
						break;
					case 2:
						/*
						 * ArrayList<Player> playerList2 = bl.getAllPlayers();
						 * Player [] players2 = new Player[5]; for(int
						 * i=0;i<5;i++){ players2[i] = playerList2.get(i); }
						 * hotPlayerPanel.setPlayers(players2);
						 * hotPlayerPanel.repaint();
						 */
						break;
					}
					// System.out.println("点击");
				}
			});
		}

	}

	public class KingPanel extends JPanel {
		private String type;// 有球员和球队数据王两种，分别用"P"和"T"表示
		private String headName;
		private String[] columnName;
		private TableButton[] btArray;
		private JLabel tableContentLabel;
		private JLabel tableHeadLabel;
		private int kingWidth;
		private int kingHeight;
		private int num = 5;

		KingPanel(String type, String headName, String[] columnName,
				int kingWidth, int kingHeight) {
			this.type = type;
			this.headName = headName;
			this.columnName = columnName;
			this.kingWidth = kingWidth;
			this.kingHeight = kingHeight;
			this.setLayout(null);
			setTableHeadLabel();
			setButton();
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
			} 

		}

		private void setTableHeadLabel() {
			tableHeadLabel = new TableHeadLabel(headName);
			tableHeadLabel.setBounds(0, 0, kingWidth, kingHeight / 6);
			this.add(tableHeadLabel);

		}

		private void setButton() {
			int size = columnName.length;
			btArray = new TableButton[size];
			for (int i = 0; i < size; i++) {
				btArray[i] = new TableButton(columnName[i], type);
				btArray[i].setBounds(kingWidth * i / size, kingHeight / 6,
						kingWidth / size, kingHeight / 8);
				this.add(btArray[i]);
			}
		}

		private void setPlayerTableContent(Player[] players) {

			tableContentLabel = new PlayerTableContentLabel(players, kingWidth,
					kingHeight * 2 / 3);
			tableContentLabel.setBounds(0, kingHeight / 3, kingWidth,
					kingHeight * 2 / 3);
			this.add(tableContentLabel);
		}


		public void setPlayers(Player[] players) {
			((PlayerTableContentLabel) tableContentLabel).setPlayers(players);
		}

		private class TableButton extends JButton {
			private String type;
			private String field;

			private TableButton(String field,String type) {
				super();
				this.field = field;
				this.type = type;
				this.setHorizontalAlignment(SwingConstants.CENTER);
				this.setForeground(Color.white);
				this.setBackground(new Color(87, 89, 91));
				this.setBorder(BorderFactory.createLineBorder(new Color(122,
						122, 122), 2));
				this.setFocusPainted(false);

				this.setFont(new Font("微软雅黑", Font.CENTER_BASELINE,
						15 * height / 1280));

				this.addMouseListener(new MouseAdapter() {
					public void mouseEntered(MouseEvent e) {
						TableButton.this.setBackground(new Color(69, 69, 69));
					}

					public void mouseExited(MouseEvent e) {
						TableButton.this.setBackground(new Color(87, 89, 91));
					}

					// 暂时刷新是假的
					public void mousePressed(MouseEvent e) {
						switch (TableButton.this.type) {

						case "P":
							// ArrayList<Player> playerList1 =
							// bl.getSeasonKingPlayer(field,5);
							ArrayList<Player> playerList1 = bl.getAllPlayers();
							Player[] players1 = new Player[5];
							for (int i = 0; i < 5; i++) {
								players1[i] = playerList1.get(i);
							}
							playerKingPanel.setPlayers(players1);
							playerKingPanel.repaint();
							break;

						}
						// System.out.println("点击");
					}
				});
			}

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
	}

	private class TableHeadLabel extends JLabel {
		private String text;

		public TableHeadLabel() {
			this.setForeground(Color.WHITE);
			this.setOpaque(true);

			this.setBackground(new Color(30, 81, 140));
			this.setUI(new LabelUI());
		}

		public TableHeadLabel(String text) {
			this();
			this.text = text;
			// this.setText(text);
		}

		public void paintComponent(Graphics g2) {
			super.paintComponent(g2);
			Graphics2D g = (Graphics2D) g2.create();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(Color.white);
			g.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 30 * height / 1280));
			g.drawString(text, width / 20, height / 40);
		}

		private class LabelUI extends BasicLabelUI {

			@Override
			public void paint(Graphics g, JComponent c) {
				// TODO Auto-generated method stub

				int y = (int) c.getSize().getHeight();
				int x = (int) c.getSize().getWidth();

				g.setColor(new Color(190, 157, 83));
				g.drawLine(0, y - 1, x - 1, y - 1);

				super.paint(g, c);
			}
		}
	}
}
