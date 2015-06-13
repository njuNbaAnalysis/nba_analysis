package ui.main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import ui.hot.HotAndKingPanel;
import ui.match.MatchPanel;
import ui.player.PlayerInfoPanel;
import ui.statistics.PlayerStatTablePanel;
import ui.statistics.TeamStatTablePanel;
import ui.team.AllTeamPanel;
import ui.team.RadarChartForTeamCompare;
import ui.team.TeamComparePanel;
import vo.Teamvo;
import BLservice.BLservice;



public class MenuPanel extends JPanel {
	private int width;
	private int height;
	private int heightOfHead;
	private static Image logo;
	int selectedNumber = -1;
	boolean canFresh;
	boolean hasFreshed = true;
	int currentType=1;
	String season;
	boolean isPlayOff;
	BLservice bl;
	ImageIcon playerIcon;
	ImageIcon playerIconD;
	ImageIcon teamIcon;
	ImageIcon teamIconD;
	ImageIcon playerStatisticsIcon;
	ImageIcon playerStatisticsIconD;
	ImageIcon teamStatisticsIcon;
	ImageIcon teamStatisticsIconD;
	ImageIcon statisticsIcon;
	ImageIcon statisticsIconD;
	ImageIcon playerIconB;
	ImageIcon teamIconB;
	ImageIcon playerStatisticsIconB;
	ImageIcon teamStatisticsIconB;
	ImageIcon statisticsIconB;
	ImageIcon exitIcon;
	ImageIcon exitIconR;
	ImageIcon matchIcon;
	ImageIcon matchIconD;
	ImageIcon matchIconB;
	ImageIcon hotPlayerIcon;
	ImageIcon hotPlayerIconD;
	ImageIcon hotPlayerIconB;
	ImageIcon freshIcon;
	ImageIcon freshIconD;
	ImageIcon freshIconR;
	JButton statistics;
	JButton player;
	JButton team;
	JButton playerStat;
	JButton teamStat;
	JButton match;
	JButton hotPlayer;
	JButton fresh;
	JButton exit;
	JPanel content;
	SeclectLabel select;
	ResultLabel result;
	MouseHandle freshListener;

	public void paintComponent(Graphics g) {
		g.setColor(new Color(31, 31, 31));
		g.fillRect(0, 0, width, height);
		g.drawImage(logo, 0, 0, this);
	}

	public MenuPanel(int width, int height, JPanel content, BLservice bl ,String season,boolean isPlayOff ) {
		setLayout(null);
		this.width = width / 10;
		this.height = height;
		this.heightOfHead = 116 * height / (1080);
		this.content = content;
		this.bl = bl;
		this.season = season;
		this.isPlayOff = isPlayOff;
		loadImage();
		initButton();
		select = new SeclectLabel(200, 40);
		select.setBounds(0, 116, 200, 40);
		Thread sl = new Thread(select);
		sl.start();
		this.add(select);
		Thread fresh = new Thread(new Refresh());
		fresh.start();
	}

	public void loadImage() {
		BufferedImage bufferLogo = null;
		BufferedImage bufferPlayer = null;
		BufferedImage bufferPlayerD = null;
		BufferedImage bufferTeam = null;
		BufferedImage bufferTeamD = null;
		BufferedImage bufferplayerStat = null;
		BufferedImage bufferplayerStatD = null;
		BufferedImage bufferTeamStat = null;
		BufferedImage bufferTeamStatD = null;
		BufferedImage bufferStat = null;
		BufferedImage bufferStatD = null;
		BufferedImage bufferPlayerB = null;
		BufferedImage bufferTeamB = null;
		BufferedImage bufferplayerStatB = null;
		BufferedImage bufferTeamStatB = null;
		BufferedImage bufferStatB = null;
		BufferedImage bufferExit = null;
		BufferedImage bufferExitR = null;
		BufferedImage bufferMatchB = null;
		BufferedImage bufferMatch = null;
		BufferedImage bufferMatchD = null;
		BufferedImage bufferHotPlayerB = null;
		BufferedImage bufferHotPlayer = null;
		BufferedImage bufferHotPlayerD = null;
		BufferedImage bufferFresh = null;
		BufferedImage bufferFreshD = null;
		BufferedImage bufferFreshR = null;
		try {
			bufferLogo = ImageIO.read(new File("image" + File.separator
					+ "logo3.png"));
			// System.out.println(width+" "+bufferLogo.getWidth());
			bufferLogo = this.resize(bufferLogo, width * 25 / 24,
					(width * 25 * 29) / (50 * 24));
			// System.out.println(width+" "+bufferLogo.getWidth());
			bufferPlayer = ImageIO.read(new File("image" + File.separator
					+ "qiuyuan_l.png"));
			bufferPlayer = this.resize_B(bufferPlayer);
			bufferPlayerD = ImageIO.read(new File("image" + File.separator
					+ "qiuyuan_d.png"));
			bufferPlayerD = this.resize_B(bufferPlayerD);
			bufferTeam = ImageIO.read(new File("image" + File.separator
					+ "qiudui_l.png"));
			bufferTeam = this.resize_B(bufferTeam);
			bufferTeamD = ImageIO.read(new File("image" + File.separator
					+ "qiudui_d.png"));
			bufferTeamD = this.resize_B(bufferTeamD);
			bufferplayerStat = ImageIO.read(new File("image" + File.separator
					+ "qiuyuantongji_l.png"));
			bufferplayerStat = this.resize_S(bufferplayerStat);
			bufferplayerStatD = ImageIO.read(new File("image" + File.separator
					+ "qiuyuantongji_d.png"));
			bufferplayerStatD = this.resize_S(bufferplayerStatD);
			bufferTeamStat = ImageIO.read(new File("image" + File.separator
					+ "qiuduitongji_l.png"));
			bufferTeamStat = this.resize_S(bufferTeamStat);
			bufferTeamStatD = ImageIO.read(new File("image" + File.separator
					+ "qiuduitongji_d.png"));
			bufferTeamStatD = this.resize_S(bufferTeamStatD);
			bufferStat = ImageIO.read(new File("image" + File.separator
					+ "tongji_l.png"));
			bufferStat = this.resize_B(bufferStat);
			bufferStatD = ImageIO.read(new File("image" + File.separator
					+ "tongji_d.png"));
			bufferStatD = this.resize_B(bufferStatD);
			bufferPlayerB = ImageIO.read(new File("image" + File.separator
					+ "qiuyuan_b.png"));
			bufferPlayerB = this.resize_B(bufferPlayerB);
			bufferTeamB = ImageIO.read(new File("image" + File.separator
					+ "qiudui_b.png"));
			bufferTeamB = this.resize_B(bufferTeamB);
			bufferplayerStatB = ImageIO.read(new File("image" + File.separator
					+ "qiuyuantongji_b.png"));
			bufferplayerStatB = this.resize_S(bufferplayerStatB);
			bufferTeamStatB = ImageIO.read(new File("image" + File.separator
					+ "qiuduitongji_b.png"));
			bufferTeamStatB = this.resize_S(bufferTeamStatB);
			bufferStatB = ImageIO.read(new File("image" + File.separator
					+ "tongji_b.png"));
			bufferStatB = this.resize_B(bufferStatB);
			bufferExit = ImageIO.read(new File("image" + File.separator
					+ "exit_l.png"));
			bufferExit = this.resize_B(bufferExit);
			bufferExitR = ImageIO.read(new File("image" + File.separator
					+ "exit_r.png"));
			bufferExitR = this.resize_B(bufferExitR);

			bufferMatchB = ImageIO.read(new File("image" + File.separator
					+ "match_b.png"));
			bufferMatchB = this.resize_B(bufferMatchB);
			bufferMatch = ImageIO.read(new File("image" + File.separator
					+ "match_l.png"));
			bufferMatch = this.resize_B(bufferMatch);
			bufferMatchD = ImageIO.read(new File("image" + File.separator
					+ "match_d.png"));
			bufferMatchD = this.resize_B(bufferMatchD);

			bufferHotPlayerB = ImageIO.read(new File("image" + File.separator
					+ "hotplayer_b.png"));
			bufferHotPlayerB = this.resize_B(bufferHotPlayerB);
			bufferHotPlayer = ImageIO.read(new File("image" + File.separator
					+ "hotplayer_l.png"));
			bufferHotPlayer = this.resize_B(bufferHotPlayer);
			bufferHotPlayerD = ImageIO.read(new File("image" + File.separator
					+ "hotplayer_d.png"));
			bufferHotPlayerD = this.resize_B(bufferHotPlayerD);
			bufferFresh = ImageIO.read(new File("image" + File.separator
					+ "fresh_l.png"));
			bufferFresh = this.resize_B(bufferFresh);
			bufferFreshD = ImageIO.read(new File("image" + File.separator
					+ "fresh_d.png"));
			bufferFreshD = this.resize_B(bufferFreshD);
			bufferFreshR = ImageIO.read(new File("image" + File.separator
					+ "fresh_r.png"));
			bufferFreshR = this.resize_B(bufferFreshR);

		} catch (IOException e) {
			e.printStackTrace();
		}

		logo = bufferLogo;
		playerIcon = new ImageIcon(bufferPlayer);
		playerIconD = new ImageIcon(bufferPlayerD);
		teamIcon = new ImageIcon(bufferTeam);
		teamIconD = new ImageIcon(bufferTeamD);
		playerStatisticsIcon = new ImageIcon(bufferplayerStat);
		playerStatisticsIconD = new ImageIcon(bufferplayerStatD);
		teamStatisticsIcon = new ImageIcon(bufferTeamStat);
		teamStatisticsIconD = new ImageIcon(bufferTeamStatD);
		statisticsIcon = new ImageIcon(bufferStat);
		statisticsIconD = new ImageIcon(bufferStatD);
		exitIcon = new ImageIcon(bufferExit);
		exitIconR = new ImageIcon(bufferExitR);
		matchIcon = new ImageIcon(bufferMatch);
		matchIconD = new ImageIcon(bufferMatchD);
		matchIconB = new ImageIcon(bufferMatchB);
		playerIconB = new ImageIcon(bufferPlayerB);
		teamIconB = new ImageIcon(bufferTeamB);
		playerStatisticsIconB = new ImageIcon(bufferplayerStatB);
		teamStatisticsIconB = new ImageIcon(bufferTeamStatB);
		statisticsIconB = new ImageIcon(bufferStatB);
		hotPlayerIcon = new ImageIcon(bufferHotPlayer);
		hotPlayerIconD = new ImageIcon(bufferHotPlayerD);
		hotPlayerIconB = new ImageIcon(bufferHotPlayerB);
		freshIcon = new ImageIcon(bufferFresh);
		freshIconD = new ImageIcon(bufferFreshD);
		freshIconR = new ImageIcon(bufferFreshR);
	}

	public void initButton() {

		statistics = new JButton();
		statistics.setSize(width * 25 / 24, (width * 25 * 3) / (24 * 20));
		statistics.setLocation(0, heightOfHead + 50 * height / 1080);
		statistics.setContentAreaFilled(false);
		statistics.setBorderPainted(false);
		statistics.setIcon(statisticsIcon);
		MouseHandle statListener = new MouseHandle(statisticsIconD,
				statisticsIcon, statisticsIconB, 0);
		statistics.addMouseListener(statListener);
		this.add(statistics);

		playerStat = new JButton();
		playerStat.setSize(width * 25 / 24, (width * 25) / (24 * 8));
		playerStat.setLocation(0, heightOfHead + 85 * height / 1080);
		playerStat.setContentAreaFilled(false);
		playerStat.setBorderPainted(false);
		playerStat.setIcon(playerStatisticsIcon);
		MouseHandle playerStatListener = new MouseHandle(playerStatisticsIconD,
				playerStatisticsIcon, playerStatisticsIconB, 1);
		playerStat.addMouseListener(playerStatListener);
		this.add(playerStat);

		teamStat = new JButton();
		teamStat.setSize(width * 25 / 24, (width * 25) / (24 * 8));
		teamStat.setLocation(0, heightOfHead + 110 * height / 1080);
		teamStat.setContentAreaFilled(false);
		teamStat.setBorderPainted(false);
		teamStat.setIcon(teamStatisticsIcon);
		MouseHandle teamStatListener = new MouseHandle(teamStatisticsIconD,
				teamStatisticsIcon, teamStatisticsIconB, 2);
		teamStat.addMouseListener(teamStatListener);
		this.add(teamStat);

		player = new JButton();
		player.setSize(width * 25 / 24, (width * 25 * 3) / (24 * 20));
		player.setLocation(0, heightOfHead + 160 * height / 1080);
		player.setContentAreaFilled(false);
		player.setBorderPainted(false);
		player.setIcon(playerIcon);
		MouseHandle playerListener = new MouseHandle(playerIconD, playerIcon,
				playerIconB, 3);
		player.addMouseListener(playerListener);
		this.add(player);

		team = new JButton();
		team.setSize(width * 25 / 24, (width * 25 * 3) / (24 * 20));
		team.setLocation(0, heightOfHead + 200 * height / 1080);
		team.setContentAreaFilled(false);
		team.setBorderPainted(false);
		team.setIcon(teamIcon);
		MouseHandle teamListener = new MouseHandle(teamIconD, teamIcon,
				teamIconB, 4);
		team.addMouseListener(teamListener);
		this.add(team);

		match = new JButton();
		match.setSize(width * 25 / 24, (width * 25 * 3) / (24 * 20));
		match.setLocation(0, heightOfHead + 240 * height / 1080);
		match.setContentAreaFilled(false);
		match.setBorderPainted(false);
		match.setIcon(matchIcon);
		MouseHandle matchListener = new MouseHandle(matchIconD, matchIcon,
				matchIconB, 6);
		match.addMouseListener(matchListener);
		this.add(match);

		hotPlayer = new JButton();
		hotPlayer.setSize(width * 25 / 24, (width * 25 * 3) / (24 * 20));
		hotPlayer.setLocation(0, heightOfHead + 280 * height / 1080);
		hotPlayer.setContentAreaFilled(false);
		hotPlayer.setBorderPainted(false);
		hotPlayer.setIcon(hotPlayerIcon);
		MouseHandle hotPlayerListener = new MouseHandle(hotPlayerIconD,
				hotPlayerIcon, hotPlayerIconB, 7);
		hotPlayer.addMouseListener(hotPlayerListener);
		this.add(hotPlayer);

		fresh = new JButton();
		fresh.setSize(width * 25 / 24, (width * 25 * 3) / (24 * 20));
		fresh.setLocation(0, height - 120 * height / 1080);
		fresh.setContentAreaFilled(false);
		fresh.setBorderPainted(false);
		fresh.setIcon(freshIcon);
	    freshListener = new MouseHandle(freshIconD, freshIcon,
				freshIcon, 8);
		fresh.addMouseListener(freshListener);
		this.add(fresh);

		exit = new JButton();
		exit.setSize(width * 25 / 24, (width * 25 * 3) / (24 * 20));
		exit.setLocation(0, height - 80 * height / 1080);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.setIcon(exitIcon);
		MouseHandle exitStatListener = new MouseHandle(exitIconR, exitIcon,
				exitIcon, 5);
		exit.addMouseListener(exitStatListener);
		this.add(exit);

	}
	

	class MouseHandle extends MouseAdapter {

		ImageIcon newIcon;
		ImageIcon oldIcon;
		ImageIcon selIcon;
		int type;

		public MouseHandle(ImageIcon newIm, ImageIcon oldIm,
				ImageIcon selectIm, int x) {
			newIcon = newIm;
			oldIcon = oldIm;
			selIcon = selectIm;
			type = x;

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if ((selectedNumber != type) && type != 8) {
				((JButton) e.getSource()).setIcon(selIcon);
				clearImage(selectedNumber);
				selectedNumber = type;
			}

			if (type == 1) {

				PlayerStatTablePanel teamRankTablePanel = new PlayerStatTablePanel(
						width * 10, height, bl, content,season,isPlayOff);
				teamRankTablePanel.setBounds(0, 0, width * 9, height);
				content.removeAll();
				content.add(teamRankTablePanel);
				content.updateUI();
				currentType=1;

			}

			if (type == 2) {
				TeamStatTablePanel teamRankTablePanel = new TeamStatTablePanel(
						width * 10, height, bl, content,season,isPlayOff);
				teamRankTablePanel.setBounds(0, 0, width * 9, height);
				content.removeAll();
				content.add(teamRankTablePanel);
				content.updateUI();
				currentType=2;
				// teamRankTablePanel.refreshTablePanel(type);

			}
			if (type == 3) {

				PlayerInfoPanel teamRankTablePanel;
				try {
					teamRankTablePanel = new PlayerInfoPanel(
							width * 9, height, bl.getAllPlayers(season,isPlayOff).get(0), bl,
							content,season,isPlayOff);
					teamRankTablePanel.setBounds(0, 0, width * 9, height);
					teamRankTablePanel.startAnimation();
					content.removeAll();
					content.add(teamRankTablePanel);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				content.updateUI();
				currentType=3;
			}
			if (type == 4) {
				AllTeamPanel team;
				try {
					team = new AllTeamPanel(width * 9, height, bl,
							content, season, isPlayOff);
					content.removeAll();
					content.setVisible(false);
					content.add(team);
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				// TeamInfoPanel teamRankTablePanel = new
				// TeamInfoPanel(width*9,height,bl.getAllTeams().get(0),bl,content);
				// team.setBounds(0, 0, width*9, height);
				// teamRankTablePanel.startAnimation();
				
				content.updateUI();
				content.setVisible(true);
				currentType=4;
			}
			if (type == 5) {
				System.exit(0);
			}
			if (type == 6) {
				// teamRankTablePanel.refreshTablePanel(type);
				MatchPanel matchPanel = new MatchPanel(width * 9, height, bl,
						content,season,isPlayOff);
				JScrollPane scrollPane = new JScrollPane(matchPanel);
				scrollPane.setBounds(0, 0, width * 9, height);
				scrollPane.getVerticalScrollBar().setUnitIncrement(20);
				content.removeAll();
				content.add(scrollPane);
				content.updateUI();
				currentType=6;
			}
			if (type == 7) {

				HotAndKingPanel hotAndKingPanel = new HotAndKingPanel(
						width * 10, height * 6 / 5, bl, content,season,isPlayOff);
				hotAndKingPanel.setPreferredSize(new Dimension(width * 10,
						height * 6 / 5));
				JScrollPane scrollPane = new JScrollPane(hotAndKingPanel);
				scrollPane.getVerticalScrollBar().setUnitIncrement(20);
				scrollPane.setBounds(0, 0, width * 9, height);
				content.removeAll();
				content.add(scrollPane);
				content.updateUI();
				currentType=7;
			}

			if (type == 8) {
				// ((JButton) e.getSource()).setIcon(selIcon);
				if (!hasFreshed) {
					freshListener.oldIcon = freshIcon;
					((JButton) e.getSource()).setIcon(freshIcon);
					hasFreshed=true;
				}
				refresh();
			}
		}

		public void mouseEntered(MouseEvent e) {

			if (selectedNumber != type) {
				((JButton) e.getSource()).setIcon(newIcon);
			}
		}

		public void mouseExited(MouseEvent e) {

			if (selectedNumber != type) {
				((JButton) e.getSource()).setIcon(oldIcon);
			}
		}

		public void mouseClicked(MouseEvent e) {

		}
	}

	private void clearImage(int type) {
		switch (type) {
		case 0:
			statistics.setIcon(statisticsIcon);
			break;
		case 1:
			playerStat.setIcon(playerStatisticsIcon);
			break;
		case 2:
			teamStat.setIcon(teamStatisticsIcon);
			break;
		case 3:
			player.setIcon(playerIcon);
			break;
		case 4:
			team.setIcon(teamIcon);
			break;
		case 6:
			match.setIcon(matchIcon);
			break;
		case 7:
			hotPlayer.setIcon(hotPlayerIcon);
			break;
		case 8:
			fresh.setIcon(freshIcon);
		}

	}

	public BufferedImage resize_B(BufferedImage image) {
		double raw_width = (double) width * 25 / 24;
		return resize(image, (int) raw_width, (int) (raw_width * 3 / 20));

	}

	public BufferedImage resize_S(BufferedImage image) {
		double raw_width = (double) width * 25 / 24;
		return resize(image, (int) raw_width, (int) (raw_width / 8));

	}

	public static BufferedImage resize(BufferedImage image, int width,
			int height) {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TRANSLUCENT);

		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}

	private void refresh(){
		switch (currentType){
		case 1:{
			PlayerStatTablePanel teamRankTablePanel = new PlayerStatTablePanel(
					width * 10, height, bl, content,season,isPlayOff);
			teamRankTablePanel.setBounds(0, 0, width * 9, height);
			content.removeAll();
			content.add(teamRankTablePanel);
			content.updateUI();
			break;
		}
		case 2:{
			TeamStatTablePanel teamRankTablePanel = new TeamStatTablePanel(
					width * 10, height, bl, content,season,isPlayOff);
			teamRankTablePanel.setBounds(0, 0, width * 9, height);
			content.removeAll();
			content.add(teamRankTablePanel);
			content.updateUI();
			break;
		}
		case 3:{
			PlayerInfoPanel teamRankTablePanel;
			try {
				teamRankTablePanel = new PlayerInfoPanel(
						width * 9, height, bl.getAllPlayers(season,isPlayOff).get(0), bl,
						content,season,isPlayOff);
				teamRankTablePanel.setBounds(0, 0, width * 9, height);
				teamRankTablePanel.startAnimation();
				content.removeAll();
				content.add(teamRankTablePanel);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			content.updateUI();
			break;
		}
		case 4:{
			AllTeamPanel team;
			try {
				team = new AllTeamPanel(width * 9, height, bl,
						content, season, isPlayOff);
				content.removeAll();
				content.setVisible(false);
				content.add(team);
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
			content.updateUI();
			content.setVisible(true);
			break;
		}
		case 6:{
			MatchPanel matchPanel = new MatchPanel(width * 9, height, bl,
					content, season, isPlayOff);
			JScrollPane scrollPane = new JScrollPane(matchPanel);
			scrollPane.setBounds(0, 0, width * 9, height);
			scrollPane.getVerticalScrollBar().setUnitIncrement(20);
			content.removeAll();
			content.add(scrollPane);
			content.updateUI();
			break;
		}
		case 7:{
			HotAndKingPanel hotAndKingPanel = new HotAndKingPanel(
					width * 10, height * 6 / 5, bl, content,season,isPlayOff);
			hotAndKingPanel.setPreferredSize(new Dimension(width * 10,
					height * 6 / 5));
			JScrollPane scrollPane = new JScrollPane(hotAndKingPanel);
			scrollPane.getVerticalScrollBar().setUnitIncrement(20);
			scrollPane.setBounds(0, 0, width * 9, height);
			content.removeAll();
			content.add(scrollPane);
			content.updateUI();
			currentType=7;
			break;
		}
		}
		
	}
	
	class Refresh implements Runnable {

		public void run() {
			while (true) {
				//canFresh = bl.isMatchChanged();
				canFresh = false;
				if (canFresh) {
					fresh.setIcon(freshIconR);
					freshListener.oldIcon = freshIconR;
					hasFreshed=false;
				}

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO 自动生成的 catch 块
					e.printStackTrace();
				}
			}
		}

	}
	
	class SeclectLabel extends JButton implements Runnable {

		int width;
		int height;
		JTextField text;
		final String initStr = "选择赛季";
		String old;

		public void paintComponent(Graphics g2) {
			super.paintComponent(g2);
			Graphics2D g = (Graphics2D) g2.create();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

		}

		public SeclectLabel(int width, int height) {
			this.width = width;
			this.height = height;
			this.setLayout(null);
			this.setSize(width, height);
			this.setBorder(null);
			text = new JTextField();
			text.setBorder(null);
			text.setForeground(Color.gray);
			text.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			text.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					JTextField src = (JTextField) e.getSource();
					src.setForeground(Color.gray);
					if (src.getText().trim().isEmpty()) {
						src.setText(initStr);
						src.setForeground(Color.LIGHT_GRAY);
					}

				}

				@Override
				public void focusGained(FocusEvent e) {
					JTextField src = (JTextField) e.getSource();
					src.setForeground(Color.black);
					if (src.getText().equals(initStr)) {
						src.setText(null);
					}

				}
			});
			text.setText(initStr);
			text.setBounds(0, 0, 200, 40);
			this.add(text);

		}

		@Override
		public void run() {

			while (true) {
				if (text.getText() != null) {
					if ((!text.getText().trim().equals(initStr))
							&& (!text.getText().trim().equals(""))) {
						if (!text.getText().trim().equals(old)) {
							old = text.getText().trim();
							ArrayList<String> items = new ArrayList<String>(); //猜测结果
							int begin = 84;
							int end   = 85;
							String beginStr,endStr,tmpSeason;
							for (int i=0;i<29;i++) {
								begin = (begin+1)%100;
								end = 	(begin+1)%100;
								beginStr = begin<10?("0"+begin):(""+begin);
								endStr = end<10?("0"+end):(""+end);
								tmpSeason = beginStr+"-"+endStr;
								
								if(tmpSeason.contains(old)||
											(old.length()>=2&&tmpSeason.contains(old.substring(old.length()-2, old.length())))){
									items.add(tmpSeason);
								}
							}
							if (items.size() > 0) {
									if (result != null) {
										MenuPanel.this.remove(result);
									}
									result = new ResultLabel(items);
									result.setLocation(0, 156);
									result.setLayout(null);
									result.setOpaque(true);
									MenuPanel.this.add(result);
									MenuPanel.this.updateUI();
								    statistics.setVisible(false);
									player.setVisible(false);
									team.setVisible(false);
									playerStat.setVisible(false);
									teamStat.setVisible(false);
									match.setVisible(false);
									hotPlayer.setVisible(false);
									
							} else {
								if (result != null) {
									result.setVisible(false);
								}
									statistics.setVisible(true);
									player.setVisible(true);
									team.setVisible(true);
									playerStat.setVisible(true);
									teamStat.setVisible(true);
									match.setVisible(true);
									hotPlayer.setVisible(true);
							}

						}

					}
				}

			}

		}
	}
	
	/**
	 * 
	 * @author NJUYuanRui 搜索结果展示框
	 */
	class ResultLabel extends JLabel {
		public ResultLabel(ArrayList<String> results) {
			int height = 35 * results.size();
			this.setSize(200, height);
			this.setOpaque(true);
			this.setBackground(Color.white);
			for (int i = 0; i < results.size(); i++) {
				ResultItemLabel itemLabel = new ResultItemLabel(results.get(i));
				itemLabel.setLocation(0, 35 * i + 5);
				this.add(itemLabel);
			}

		}
	}

	/**
	 * 
	 * @author NJUYuanRui 搜索结果展示条目
	 */
	class ResultItemLabel extends JLabel implements MouseListener {
		String team;
		public ResultItemLabel(String item) {
			this.team = item;
			this.setOpaque(true);
			this.setSize(200, 30);
			this.setFont(new Font("微软雅黑", Font.PLAIN, 15));
			this.setForeground(Color.black);
			this.setText(item);
			this.setBackground(Color.white);
			this.setHorizontalAlignment(LEFT);
			this.addMouseListener(this);

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO 自动生成的方法存根

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			this.setBackground(Color.gray);

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			this.setBackground(Color.white);

		}

		@Override
		public void mousePressed(MouseEvent arg0) {

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			//更新方法
			result.setVisible(false);
			statistics.setVisible(true);
			player.setVisible(true);
			MenuPanel.this.team.setVisible(true);
			playerStat.setVisible(true);
			teamStat.setVisible(true);
			match.setVisible(true);
			hotPlayer.setVisible(true);
			select.text.setText(team+"赛季");
		}
	}
	
	

}
