package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import logic.BLController;
import logic.BLService;

public class MenuPanel extends JPanel {
	private int width;
	private int height;
	private int heightOfHead ;
	private static Image logo;
	int selectedNumber = -1;
	BLController bl;
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
	JButton statistics;
	JButton player;
	JButton team;
	JButton playerStat;
	JButton teamStat;
	JButton match;
	JButton hotPlayer;
	JButton exit;
	JPanel content;
	
	public void paintComponent(Graphics g) {
		g.setColor(new Color(31, 31, 31));
		g.fillRect(0, 0, width, height);
		g.drawImage(logo, 0, 0, this);
	}

	public MenuPanel(int width, int height, JPanel content,BLController bl) {
		setLayout(null);
		this.width = width / 10;
		this.height = height;
		this.heightOfHead = 116*height/(1080);
		this.content = content;
		this.bl=bl;
		loadImage();
		initButton();
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
		try {
			bufferLogo = ImageIO.read(new File("image" + File.separator
					+ "logo3.png"));
			//System.out.println(width+" "+bufferLogo.getWidth());
			bufferLogo = this.resize(bufferLogo, width*25/24, (width*25*29)/(50*24));
			//System.out.println(width+" "+bufferLogo.getWidth());
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

	}

	public void initButton() {

		statistics = new JButton();
		statistics.setSize( width*25/24, (width*25*3)/(24*20));
		statistics.setLocation(0, heightOfHead + 50*height/1080);
		statistics.setContentAreaFilled(false);
		statistics.setBorderPainted(false);
		statistics.setIcon(statisticsIcon);
		MouseHandle statListener = new MouseHandle(statisticsIconD,
				statisticsIcon, statisticsIconB, 0);
		statistics.addMouseListener(statListener);
		this.add(statistics);

		playerStat = new JButton();
		playerStat.setSize( width*25/24, (width*25)/(24*8));
		playerStat.setLocation(0, heightOfHead + 85*height/1080);
		playerStat.setContentAreaFilled(false);
		playerStat.setBorderPainted(false);
		playerStat.setIcon(playerStatisticsIcon);
		MouseHandle playerStatListener = new MouseHandle(playerStatisticsIconD,
				playerStatisticsIcon, playerStatisticsIconB, 1);
		playerStat.addMouseListener(playerStatListener);
		this.add(playerStat);

		teamStat = new JButton();
		teamStat.setSize(width*25/24, (width*25)/(24*8));
		teamStat.setLocation(0, heightOfHead + 110*height/1080);
		teamStat.setContentAreaFilled(false);
		teamStat.setBorderPainted(false);
		teamStat.setIcon(teamStatisticsIcon);
		MouseHandle teamStatListener = new MouseHandle(teamStatisticsIconD,
				teamStatisticsIcon, teamStatisticsIconB, 2);
		teamStat.addMouseListener(teamStatListener);
		this.add(teamStat);

		player = new JButton();
		player.setSize( width*25/24, (width*25*3)/(24*20));
		player.setLocation(0, heightOfHead + 160*height/1080);
		player.setContentAreaFilled(false);
		player.setBorderPainted(false);
		player.setIcon(playerIcon);
		MouseHandle playerListener = new MouseHandle(playerIconD, playerIcon,
				playerIconB, 3);
		player.addMouseListener(playerListener);
		this.add(player);

		team = new JButton();
		team.setSize( width*25/24, (width*25*3)/(24*20));
		team.setLocation(0, heightOfHead + 200*height/1080);
		team.setContentAreaFilled(false);
		team.setBorderPainted(false);
		team.setIcon(teamIcon);
		MouseHandle teamListener = new MouseHandle(teamIconD, teamIcon,
				teamIconB, 4);
		team.addMouseListener(teamListener);
		this.add(team);
	
		match = new JButton();
		match.setSize( width*25/24, (width*25*3)/(24*20));
		match.setLocation(0, heightOfHead + 240*height/1080);
		match.setContentAreaFilled(false);
		match.setBorderPainted(false);
		match.setIcon(matchIcon);
		MouseHandle matchListener = new MouseHandle(matchIconD, matchIcon,
				matchIconB, 6);
		match.addMouseListener(matchListener);
		this.add(match);
		
		hotPlayer = new JButton();
		hotPlayer.setSize( width*25/24, (width*25*3)/(24*20));
		hotPlayer.setLocation(0, heightOfHead + 280*height/1080);
		hotPlayer.setContentAreaFilled(false);
		hotPlayer.setBorderPainted(false);
		hotPlayer.setIcon(hotPlayerIcon);
		MouseHandle hotPlayerListener = new MouseHandle(hotPlayerIconD, hotPlayerIcon,
				hotPlayerIconB, 7);
		hotPlayer.addMouseListener(hotPlayerListener);
		this.add(hotPlayer);

		exit = new JButton();
		exit.setSize( width*25/24, (width*25*3)/(24*20));
		exit.setLocation(0,  height-80*height/1080);
		exit.setContentAreaFilled(false);
		exit.setBorderPainted(false);
		exit.setIcon(exitIcon);
		MouseHandle exitStatListener = new MouseHandle(exitIconR,
				exitIcon, exitIconR, 5);
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
			if (selectedNumber != type) {
				((JButton) e.getSource()).setIcon(selIcon);
				clearImage(selectedNumber);
				selectedNumber = type;
			}
			
			if(type==1){
				
				PlayerStatTablePanel teamRankTablePanel = new PlayerStatTablePanel(width*10,height,bl,content);
				teamRankTablePanel.setBounds(0, 0, width*9, height);
				content.removeAll();
				content.add(teamRankTablePanel);
				content.updateUI();
			
			}
			
			if(type==2){
				TeamStatTablePanel teamRankTablePanel = new TeamStatTablePanel(width*10,height,bl,content);
				teamRankTablePanel.setBounds(0, 0, width*9, height);
				content.removeAll();
				content.add(teamRankTablePanel);
				content.updateUI();
				//teamRankTablePanel.refreshTablePanel(type);
				
			}
			if(type==3){
				
				PlayerInfoPanel teamRankTablePanel = new PlayerInfoPanel(width*9,height,bl.getAllPlayers().get(0),bl);
				teamRankTablePanel.setBounds(0, 0, width*9, height);
				teamRankTablePanel.startAnimation();
				content.removeAll();
				content.add(teamRankTablePanel);
				content.updateUI();
				
			}
			if(type==4){
				TeamInfoPanel teamRankTablePanel = new TeamInfoPanel(width*9,height,bl.getAllTeams().get(0),bl);
				teamRankTablePanel.setBounds(0, 0, width*9, height);
				//teamRankTablePanel.startAnimation();
				content.removeAll();
				content.add(teamRankTablePanel);
				content.updateUI();
			}
			if(type==5){
				System.exit(0);
			}
			if(type==6){
				//teamRankTablePanel.refreshTablePanel(type);
				MatchPanel matchPanel = new MatchPanel(width*9,height,bl);
				JScrollPane scrollPane = new JScrollPane(matchPanel);
				scrollPane.setBounds(0, 0, width*9, height);
				scrollPane.getVerticalScrollBar().setUnitIncrement(20);
				content.removeAll();
				content.add(scrollPane);
				content.updateUI();
			}
			if(type==7){
				
				
				HotAndKingPanel hotAndKingPanel  = new HotAndKingPanel(width*10,height*6/5,bl);
				hotAndKingPanel.setPreferredSize(new Dimension(width*10,height*6/5));
				JScrollPane scrollPane = new JScrollPane(hotAndKingPanel);
				scrollPane.getVerticalScrollBar().setUnitIncrement(20);
				scrollPane.setBounds(0, 0, width*9, height);
				content.removeAll();
				content.add(scrollPane);
				content.updateUI();
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
		}

	}
	
	public BufferedImage resize_B(BufferedImage image){
		double raw_width =(double)width*25/24 ;
		return resize(image,(int)raw_width,(int)(raw_width*3/20));
		
	}
	
	public BufferedImage resize_S(BufferedImage image){
		double raw_width =(double)width*25/24 ;
		return resize(image,(int)raw_width,(int)(raw_width/8));
		
	}
	
	public static BufferedImage resize(BufferedImage image, int width, int height) {
		BufferedImage bi = new BufferedImage(width, height,
				BufferedImage.TRANSLUCENT);
		
		Graphics2D g2d = (Graphics2D) bi.createGraphics();
		g2d.addRenderingHints(new RenderingHints(
				RenderingHints.KEY_RENDERING,
				RenderingHints.VALUE_RENDER_QUALITY));
		g2d.drawImage(image, 0, 0, width, height, null);
		g2d.dispose();
		return bi;
	}

}
