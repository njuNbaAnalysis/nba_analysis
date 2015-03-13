package ui;

import java.awt.Color;
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

public class MenuPanel extends JPanel {
	private int width;
	private int height;
	private int heightOfHead = 116;
	private static Image logo;
	int selectedNumber = -1;

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
	JButton statistics;
	JButton player;
	JButton team;
	JButton playerStat;
	JButton teamStat;
	JButton exit;

	public void paintComponent(Graphics g) {
		g.setColor(new Color(31, 31, 31));
		g.fillRect(0, 0, width, height);
		g.drawImage(logo, 0, 0, this);
	}

	public MenuPanel(int width, int height) {
		setLayout(null);
		this.width = width / 10;
		this.height = height;

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
		try {
			bufferLogo = ImageIO.read(new File("image" + File.separator
					+ "logo3.png"));
			bufferLogo = this.resize(bufferLogo, width, width*29/50);
			//System.out.println(width+" "+width*29/50);
			bufferPlayer = ImageIO.read(new File("image" + File.separator
					+ "qiuyuan_l.png"));
			bufferPlayer = this.resize(bufferPlayer, width, width*3/20);
			bufferPlayerD = ImageIO.read(new File("image" + File.separator
					+ "qiuyuan_d.png"));
			bufferPlayerD = this.resize(bufferPlayerD, width, width*3/20);
			bufferTeam = ImageIO.read(new File("image" + File.separator
					+ "qiudui_l.png"));
			bufferTeam = this.resize(bufferTeam, width, width*3/20);
			bufferTeamD = ImageIO.read(new File("image" + File.separator
					+ "qiudui_d.png"));
			bufferTeamD = this.resize(bufferTeamD, width, width*3/20);
			bufferplayerStat = ImageIO.read(new File("image" + File.separator
					+ "qiuyuantongji_l.png"));
			bufferplayerStat = this.resize(bufferplayerStat, width, width/8);
			bufferplayerStatD = ImageIO.read(new File("image" + File.separator
					+ "qiuyuantongji_d.png"));
			bufferplayerStatD = this.resize(bufferplayerStatD, width, width/8);
			bufferTeamStat = ImageIO.read(new File("image" + File.separator
					+ "qiuduitongji_l.png"));
			bufferTeamStat = this.resize(bufferTeamStat, width, width/8);
			bufferTeamStatD = ImageIO.read(new File("image" + File.separator
					+ "qiuduitongji_d.png"));
			bufferTeamStatD = this.resize(bufferTeamStatD, width, width/8);
			bufferStat = ImageIO.read(new File("image" + File.separator
					+ "tongji_l.png"));
			bufferStat = this.resize(bufferStat, width, width*3/20);
			bufferStatD = ImageIO.read(new File("image" + File.separator
					+ "tongji_d.png"));
			bufferStatD = this.resize(bufferStatD, width, width*3/20);
			bufferPlayerB = ImageIO.read(new File("image" + File.separator
					+ "qiuyuan_b.png"));
			bufferPlayerB = this.resize(bufferPlayerB, width, width*3/20);
			bufferTeamB = ImageIO.read(new File("image" + File.separator
					+ "qiudui_b.png"));
			bufferTeamB = this.resize(bufferTeamB, width, width*3/20);
			bufferplayerStatB = ImageIO.read(new File("image" + File.separator
					+ "qiuyuantongji_b.png"));
			bufferplayerStatB = this.resize(bufferplayerStatB, width, width/8);
			bufferTeamStatB = ImageIO.read(new File("image" + File.separator
					+ "qiuduitongji_b.png"));
			bufferTeamStatB = this.resize(bufferTeamStatB, width, width/8);
			bufferStatB = ImageIO.read(new File("image" + File.separator
					+ "tongji_b.png"));
			bufferStatB = this.resize(bufferStatB, width, width*3/20);
			bufferExit = ImageIO.read(new File("image" + File.separator
					+ "exit_l.png"));
			bufferExit = this.resize(bufferExit, width, width*3/20);
			bufferExitR = ImageIO.read(new File("image" + File.separator
					+ "exit_r.png"));
			bufferExitR = this.resize(bufferExitR, width, width*3/20);

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
		
		playerIconB = new ImageIcon(bufferPlayerB);
		teamIconB = new ImageIcon(bufferTeamB);
		playerStatisticsIconB = new ImageIcon(bufferplayerStatB);
		teamStatisticsIconB = new ImageIcon(bufferTeamStatB);
		statisticsIconB = new ImageIcon(bufferStatB);

	}

	public void initButton() {

		statistics = new JButton();
		statistics.setSize( width, width*3/20);
		statistics.setLocation(0, heightOfHead + 50);
		statistics.setContentAreaFilled(false);
		statistics.setBorderPainted(false);
		statistics.setIcon(statisticsIcon);
		MouseHandle statListener = new MouseHandle(statisticsIconD,
				statisticsIcon, statisticsIconB, 0);
		statistics.addMouseListener(statListener);
		this.add(statistics);

		playerStat = new JButton();
		playerStat.setSize(width, width/8);
		playerStat.setLocation(0, heightOfHead + 85);
		playerStat.setContentAreaFilled(false);
		playerStat.setBorderPainted(false);
		playerStat.setIcon(playerStatisticsIcon);
		MouseHandle playerStatListener = new MouseHandle(playerStatisticsIconD,
				playerStatisticsIcon, playerStatisticsIconB, 1);
		playerStat.addMouseListener(playerStatListener);
		this.add(playerStat);

		teamStat = new JButton();
		teamStat.setSize(width, width/8);
		teamStat.setLocation(0, heightOfHead + 110);
		teamStat.setContentAreaFilled(false);
		teamStat.setBorderPainted(false);
		teamStat.setIcon(teamStatisticsIcon);
		MouseHandle teamStatListener = new MouseHandle(teamStatisticsIconD,
				teamStatisticsIcon, teamStatisticsIconB, 2);
		teamStat.addMouseListener(teamStatListener);
		this.add(teamStat);

		player = new JButton();
		player.setSize(width, width*3/20);
		player.setLocation(0, heightOfHead + 160);
		player.setContentAreaFilled(false);
		player.setBorderPainted(false);
		player.setIcon(playerIcon);
		MouseHandle playerListener = new MouseHandle(playerIconD, playerIcon,
				playerIconB, 3);
		player.addMouseListener(playerListener);
		this.add(player);

		team = new JButton();
		team.setSize(width, width*3/20);
		team.setLocation(0, heightOfHead + 200);
		team.setContentAreaFilled(false);
		team.setBorderPainted(false);
		team.setIcon(teamIcon);
		MouseHandle teamListener = new MouseHandle(teamIconD, teamIcon,
				teamIconB, 4);
		team.addMouseListener(teamListener);
		this.add(team);

		exit = new JButton();
		exit.setSize(width, width*3/20);
		exit.setLocation(0,  height-80);
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
			
			if(type==5){
				System.exit(0);
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
		}

	}
	
	public  BufferedImage resize(BufferedImage image, int width, int height) {
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
