package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicLabelUI;

import util.UIUtils;
import logic.BLController;
import logic.BLService;
import logic.players.Player;
import logic.teams.Team;

public class HotAndKingPanel extends JPanel {
	private int num = 5;
	private int width;
	private int height;
	private BLService bl;
	private static String FONT_OF_HOT = "微软雅黑";
	private static String [] typeArray = {"T","P","HP"};
	private KingPanel teamKingPanel;
	private KingPanel playerKingPanel;
	private HotPlayerPanel hotPlayerPanel;

	public HotAndKingPanel(int width, int height, BLService bl) {
		this.width = width;
		this.height = height;
		this.bl = bl;
		this.setLayout(null);
		
		//创建字体
		UIUtils.createFont("font"+File.separator+"Oswald-Bold.otf");
		
		String[] playerColumnName = { "得分", "篮板", "助攻", "抢断", "盖帽" };
		playerKingPanel = new KingPanel("P", "每日 联盟数据王",
				playerColumnName, width*9/10, height / 4);
		playerKingPanel.setBounds(0, 0, width*9/10, height / 4);
		this.add(playerKingPanel);

		String[] teamColumnName = { "得分", "篮板", "助攻", "抢断", "盖帽", "三分%", "%",
				"罚球%" };
		teamKingPanel = new KingPanel("T", "常规赛 联盟球队数据",
				teamColumnName, width*9/10, height / 4);
		teamKingPanel.setBounds(0, height / 4, width*9/10, height / 4);
		this.add(teamKingPanel);

		
		// 设置player
		Object[] o = bl.getAllPlayers().subList(0, num).toArray();
		Player[] p = new Player[num];
		for (int i = 0; i < num; i++) {
			p[i] = (Player) o[i]; 
		}
		String[] hotColumnName = { "场均得分", "场均篮板", "场均助攻" };
		hotPlayerPanel = new HotPlayerPanel("热门球员",typeArray[2],
				hotColumnName, p,width, height / 2);
		hotPlayerPanel.setBounds(0, height / 2, width*9/10, height / 2);


		this.add(hotPlayerPanel);

		System.out.println("here");
	}

	private class KingPanel extends JPanel {
		private String type;// 有球员和球队数据王两种，分别用"P"和"T"表示
		private String headName;
		private String[] columnName;
		private TableButton[] btArray;
		private TableHeadLabel tableHeadLabel;
		private JLabel tableContentLabel;
		private int kingWidth;
		private int kingHeight;

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
			} else if (type.equals("T")) {
				Object[] o = bl.getAllTeams().subList(0, num).toArray();
				Team[] t = new Team[num];
				for (int i = 0; i < num; i++) {
					t[i] = (Team) o[i];
				}
				setTeamTableContent(t);
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
				btArray[i] = new TableButton(columnName[i],type);
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

		private void setTeamTableContent(Team[] teams) {
			tableContentLabel = new TeamTableContentLabel(teams, kingWidth,
					kingHeight * 2 / 3);
			tableContentLabel.setBounds(0, kingHeight / 3, kingWidth,
					kingHeight * 2 / 3);
			this.add(tableContentLabel);

		}
 
		public void setTeams(Team [] teams) {
			((TeamTableContentLabel) tableContentLabel).setTeams(teams);			
		}
		
		public void setPlayers(Player [] players) {
			((PlayerTableContentLabel) tableContentLabel).setPlayers(players);			
		}
	}

	private class HotPlayerPanel extends JPanel {
		private int hotWidth;
		private int hotHeight;
		private String headName;
		private String[] columnName;
		private Player[] players;
		private JLabel tableContentLabel;
		private TableButton[] btArray;
		private TableHeadLabel tableHeadLabel;
		private String type;		
		public HotPlayerPanel(String headName,String type,String[] columnName,Player[] players,
				int hotWidth, int hotHeight) {
			this.columnName = columnName;
			this.type = type;
			this.hotWidth = hotWidth;
			this.hotHeight = hotHeight;
			this.headName = headName;
			this.players = players;
			this.setLayout(null);
			setTableHeadLabel();
			setButton();
			setHotTableContentLabel();
		}

		private void setHotTableContentLabel() {
			tableContentLabel = new HotTableContentLabel(players, hotWidth,
					hotHeight * 5 / 6);
			tableContentLabel.setBounds(0, hotHeight / 6, hotWidth,
					hotHeight * 5 / 6);
			this.add(tableContentLabel);

		}

		public void setPlayers(Player[] players) {
			this.players = players;

		}

		private void setTableHeadLabel() {
			tableHeadLabel = new TableHeadLabel(headName);
			tableHeadLabel.setBounds(0, 0, hotWidth, hotHeight / 10);
			this.add(tableHeadLabel);

		}

		private void setButton() {
			int size = columnName.length;
			btArray = new TableButton[size];
			for (int i = 0; i < size; i++) {
				btArray[i] = new TableButton(columnName[i],type);
				btArray[i].setBounds(hotWidth * i / size, hotHeight / 10,
						hotWidth / size, hotHeight / 15);
				this.add(btArray[i]);
			}
		}
	}

	private class TableButton extends JButton {
		private String type;
		private String field;

		private TableButton() {
			super();

			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setForeground(Color.white);
			this.setBackground(new Color(87, 89, 91));
			this.setBorder(BorderFactory.createLineBorder(new Color(122, 122,
					122), 2));
			this.setFocusPainted(false);

			this.setFont(new Font(FONT_OF_HOT, Font.CENTER_BASELINE,
					15 * height / 1280));

			this.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					TableButton.this.setBackground(new Color(69, 69, 69));
				}

				public void mouseExited(MouseEvent e) {
					TableButton.this.setBackground(new Color(87, 89, 91));
				}

				//暂时刷新是假的
				public void mousePressed(MouseEvent e) {
					switch(type){
					case "T":
						//ArrayList<Team> teamList = bl.getSeasonKingTeam(field,5);
						ArrayList<Team> teamList = bl.getAllTeams();
						Team [] teams = new Team[5];
						for(int i=0;i<5;i++){
							teams[i] = teamList.get(i);
						}
						teamKingPanel.setTeams(teams);
						teamKingPanel.repaint();
						break;
					case "P":
					//	ArrayList<Player> playerList1 = bl.getSeasonKingPlayer(field,5);
						ArrayList<Player> playerList1 = bl.getAllPlayers();
						Player [] players1 = new Player[5];
						for(int i=0;i<5;i++){
							players1[i] = playerList1.get(i);
						}
						playerKingPanel.setPlayers(players1);
						playerKingPanel.repaint();
						break;
					case "HP":	
						ArrayList<Player> playerList2 = bl.getAllPlayers();
						Player [] players2 = new Player[5];
						for(int i=0;i<5;i++){
							players2[i] = playerList2.get(i);
						}
						hotPlayerPanel.setPlayers(players2);
						hotPlayerPanel.repaint();
						break;
					}
					//System.out.println("点击");
				}
			});
		}

		public TableButton(String text, String type) {
			this();
			this.setText(text);
			this.field = text;
			this.type = type;
		}
	}

	private class TableHeadLabel extends JLabel {
		public TableHeadLabel() {
			this.setForeground(Color.WHITE);
			this.setOpaque(true);
			this.setFont(new Font(FONT_OF_HOT, Font.CENTER_BASELINE,
					30 * height / 1280));
			this.setBackground(new Color(30, 81, 140));
			this.setUI(new LabelUI());
		}

		public TableHeadLabel(String text) {
			this();
			this.setText(text);
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

	private class HotTableContentLabel extends JLabel {
		private Player[] players;
		private int contentWidth;
		private int contentHeight;

		public HotTableContentLabel(Player[] players, int contentWidth,
				int contentHeight) {
			this.players = players;
			this.contentWidth = contentWidth;
			this.contentHeight = contentHeight;
		}
		
		public void setPlayers(Player[] players){
			this.players = players;
			this.repaint();
		}

		public void paintComponent(Graphics g2) {
			// 背景
			Graphics2D g = (Graphics2D) g2.create();
			g.setColor(Color.white);
			g.fillRect(0, 0, contentWidth,
					contentHeight);
			// 头像
			BufferedImage portrait = UIUtils.resize(players[0].getPortrait(),
					contentWidth / 10, contentHeight * 3 / 10);
			g.drawImage(portrait, 0,0, this);
			// 排名
			g.setColor(new Color(190, 157, 83));
			g.setFont(new Font("微软雅黑", Font.BOLD, 50));
			g.drawString(1 + "", contentWidth / 3, contentHeight * 1 / 5);
			// 姓名
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 25));
			g.drawString(players[0].getName(), contentWidth / 2,
					contentHeight * 1 / 10);

			// 位置，球队
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			g.drawString(players[0].getPosition() + "/" + players[0].getTeam(),
					contentWidth / 2, contentHeight * 1 / 5);

			// 最近五场/提升率
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			g.drawString("最近五场" + "/" + "提升率", contentWidth / 2,
					contentHeight * 3 / 10);
			// 数据、球队图标暂无

			g.setColor(new Color(158, 158, 158));
			g.fillRect(0, contentHeight * 2 / 5, contentWidth,
					contentHeight * 1 / 20);
			
			//排名
			g.setColor(new Color(68, 68, 68));
			g.setFont(new Font("微软雅黑", Font.PLAIN, 15));
			g.drawString("排名", contentWidth / 13,
					contentHeight * 7 / 16);
			g.drawString("球队", contentWidth / 6,
					contentHeight * 7 / 16);
			g.drawString("最近五场/提升率", contentWidth *7/ 12,
					contentHeight * 7 / 16);

			for (int i = 2; i <= num; i++) {
				int posh = contentHeight/2+contentHeight*(i-2)/ 8-contentHeight/20;
				
				g.setColor(new Color(246,246,246));
				g.fillRect(0,contentHeight/2+contentHeight*(i-2)/ 8-contentHeight/20,contentWidth/6,contentHeight/10);
				
				//排名
				g.setColor(new Color(158,158,158));
				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 20));
				g.drawString(i + "", contentWidth / 12, posh+contentHeight/16);
				//头像
				BufferedImage image = UIUtils.resize(
						players[i - 1].getPortrait(), contentWidth / 25,
						contentHeight / 10);

				g.drawImage(image, contentWidth / 6, posh, this);
				//名字
				g.setColor(Color.black);
				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 15));
				g.drawString(players[i - 1].getName(), contentWidth * 9 / 40,
						posh+contentHeight/30);
				
				//球队
				String str = Integer.toString(players[i - 1].getNumber()) + " "
						+ players[i - 1].getPosition() + "/"
						+ players[i - 1].getTeam();
				g.drawString(str, contentWidth * 9 / 40, posh+contentHeight*7/80);
				
				//最近五场/提升率
				
				g.setColor(Color.black);
				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 30));
				g.drawString("20.0",contentWidth *7/ 12,contentHeight/2+contentHeight*(i-2)/ 8+contentHeight/40);
				
				g.setColor(new Color(57,167,229));
				g.setFont(new Font("Oswald-Bold", Font.PLAIN, 15));
				g.drawString("/     97.5%",contentWidth *7/ 11,contentHeight/2+contentHeight*(i-2)/ 8+contentHeight/40);
			}

		}
	}

	public static void main(String[] args) {
		BLService bl = BLController.getInstance();
		bl.init();
		while(bl.getProgress()<9){
			System.out.println(bl.getProgress());
		}

		JFrame f = new JFrame();
		f.setBounds(0, 0, 1920, 1280);
	//	f.setUndecorated(true);
		f.setLayout(null);
		// f.getContentPane().setLayout(null);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		HotAndKingPanel p = new HotAndKingPanel(2000, 1500, bl);
		//p.setBounds(0, 0, 1920, 1580);
		
		p.setPreferredSize(new Dimension(2000,1500));
		
		JScrollPane js = new JScrollPane();
		js.setBounds(0, 0, 1920, 1080);
		js.getVerticalScrollBar().setUnitIncrement(20);
		js.setViewportView(p);
		//js.add(p);
		//js.setWheelScrollingEnabled(true);

		f.add(js);
		f.setVisible(true);
	}
}
