package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.BLController;
import logic.matches.Match;
import logic.teams.Team;

public class MatchPanel extends JPanel {

	private HeadLabel head;
	private int width;
	private int height;
	BLController bl;
	Date date = new Date();
	ArrayList<Match> matchList;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public MatchPanel(int width1, int height1, BLController bl) {
		super();
		/*
		 * this.width=width1; this.height = height1;
		 */
		this.bl = bl;
		this.matchList = new ArrayList<Match>(bl.getTodayMatches("13-14_2014-01-01"));
		this.width = 1920;
		this.height = 1080;

		int size = matchList.size();
		setBackground(Color.white);
		this.setBounds(0, 0, width, height);
		this.setLayout(null);
		head = new HeadLabel(width, height / 10);
		this.add(head);

		// will be replaced by loop...
		for (int i = 0; i < size; i++) {
			InfoLabel info = new InfoLabel(0, 150 + 320 * i, width, 300,
					matchList.get(i));
			this.add(info);
			/*
			 * InfoLabel info1 = new InfoLabel(0, 470 , width, 300,
			 * matchList.get(1)); this.add(info1); InfoLabel info2 = new
			 * InfoLabel(0, 790 , width, 300, matchList.get(0));
			 * this.add(info2); InfoLabel info3 = new InfoLabel(0, 1110 , width,
			 * 300, matchList.get(0)); this.add(info3);
			 */
		}
		this.setPreferredSize(new Dimension(1920, 200 + size * 320));
	}

	class HeadLabel extends JLabel {

		int width;
		int height;

		private JButton left;
		private JButton right;
		private JButton calendar;
		private JLabel currentLabel;
		ImageIcon left_l;
		ImageIcon left_d;
		ImageIcon right_l;
		ImageIcon right_d;

		public void paintComponent(Graphics g) {
			g.setColor(Color.WHITE);
			g.fillRect(0, 0, width, height);
			g.setColor(new Color(30, 81, 140));
			g.fillRect(0, 0, width, 80);
			g.setColor(Color.WHITE);

		}

		public HeadLabel(int width, int height) {
			super();
			setLayout(null);
			this.width = width;
			this.height = height;
			this.setBounds(0, 0, width, height);

			// load images for button icons
			try {
				left_l = new ImageIcon(ImageIO.read(new File("image"
						+ File.separator + "right_l.png")));
				left_d = new ImageIcon(ImageIO.read(new File("image"
						+ File.separator + "right_d.png")));
				right_l = new ImageIcon(ImageIO.read(new File("image"
						+ File.separator + "left_l.png")));
				right_d = new ImageIcon(ImageIO.read(new File("image"
						+ File.separator + "left_d.png")));
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

			initButton();

		}

		public void initButton() {
			left = new JButton();
			left.setSize(24, 25);
			left.setLocation(50, 25);
			left.setContentAreaFilled(false);
			left.setBorderPainted(false);
			left.setIcon(left_d);
			MouseHandle leftListener = new MouseHandle(left_l, left_d, left_l,
					1);
			left.addMouseListener(leftListener);
			this.add(left);

			right = new JButton();
			right.setSize(24, 25);
			right.setLocation(250, 25);
			right.setContentAreaFilled(false);
			right.setBorderPainted(false);
			right.setIcon(right_d);
			MouseHandle rightListener = new MouseHandle(right_l, right_d,
					right_l, 2);
			right.addMouseListener(rightListener);
			this.add(right);

			currentLabel = new JLabel(df.format(date));
			currentLabel.setForeground(Color.white);
			currentLabel.setFont(new Font("default", Font.BOLD, 20));
			currentLabel.setLocation(110, 25);
			currentLabel.setSize(120, 25);
			this.add(currentLabel);
		}

	}

	class InfoLabel extends JLabel {

		int width;
		int height;
		String date;
		Match match;
		Image logo;
		Team[] teams;

		public void paintComponent(Graphics g) {
			g.setColor(new Color(246, 246, 246));
			g.fillRect(0, 0, width, height);
			g.setColor(new Color(169, 11, 51));
			g.fillRect(0, 0, width / 10, height / 10);
			g.setColor(new Color(190, 157, 83));
			g.drawLine(0, height / 10, width, height / 10);
			g.drawLine(0, height * 9 / 10, width, height * 9 / 10);
			g.setColor(Color.WHITE);
			g.fillRect(0, height / 10 + 1, width * 2 / 5, height * 8 / 10 - 1);
			g.setFont(new Font("default", Font.BOLD, 20));
			g.drawString("结束", 45, 22);
			g.drawImage(teams[0].getLogo(96, 60), 50, 60, this);
			g.drawImage(teams[1].getLogo(96, 60), 50, 170, this);
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.PLAIN, 30));
			g.drawString(match.getTeams()[0], 150, 90);
			g.drawString(match.getTeams()[1], 150, 200);
			g.setFont(new Font("default", Font.PLAIN, 15));
	
			g.drawString(teams[0].getNumOfVictory()+"-"+(teams[0].getNumOfMatches()-teams[0].getNumOfVictory()), 160, 110);
			g.drawString(teams[1].getNumOfVictory()+"-"+(teams[1].getNumOfMatches()-teams[1].getNumOfVictory()), 160, 220);
			g.drawString("1", 270, 60);
			g.drawString("2", 350, 60);
			g.drawString("3", 430, 60);
			g.drawString("4", 510, 60);
			g.setFont(new Font("default", Font.PLAIN, 30));
			g.drawString(match.getPointsList().get(0)[0]+"", 255, 110);
			g.drawString(match.getPointsList().get(1)[0]+"", 335, 110);
			g.drawString(match.getPointsList().get(2)[0]+"", 415, 110);
			g.drawString(match.getPointsList().get(3)[0]+"", 495, 110);
			
			g.setColor(new Color(169, 11, 51));
			g.drawString(match.getPointsList().get(0)[1]+"", 415, 210);
			g.drawString(match.getPointsList().get(1)[1]+"", 255, 210);
			g.drawString(match.getPointsList().get(2)[1]+"", 335, 210);
			g.drawString(match.getPointsList().get(3)[1]+"", 495, 210);
			g.setFont(new Font("default", Font.PLAIN, 40));
			g.setColor(new Color(169, 11, 51));
			g.drawString(match.getPoints()[1]+"", 580, 215);
			g.setColor(Color.black);
			g.drawString(match.getPoints()[0]+"", 580, 115);

			g.setFont(new Font("default", Font.BOLD, 20));
			g.drawString(match.getTeams()[0], 980, 70);
			g.drawString(match.getTeams()[1], 1380, 70);
			g.drawString("得分", 800, 110);
			g.drawString("篮板", 800, 170);
			g.drawString("助攻", 800, 230);
			g.setColor(Color.blue);
			g.setFont(new Font("default", Font.PLAIN, 17));
			g.drawString("张冠炜           42", 980, 110);
			g.drawString("张冠炜           45", 980, 170);
			g.drawString("张冠炜           18", 980, 230);

			g.drawString("张冠炜           42", 1380, 110);
			g.drawString("张冠炜           45", 1380, 170);
			g.drawString("张冠炜           18", 1380, 230);

		}

		public InfoLabel(int x, int y, int width, int height, Match match) {
			this.width = width;
			this.height = height;
			this.match = match;
			teams = bl.getTeamsByMatch(match);
			//System.out.println(teams[0]==null);
			this.setBounds(x, y, width, height);

			// stub logo
			try {
				logo = ImageIO.read(new File("image" + File.separator
						+ "logo1_s.png"));
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}

		}

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

			if (type == 1) {
				date = new Date(date.getTime() - 24 * 60 * 60 * 1000);
				head.currentLabel.setText(df.format(date));
			}
			if (type == 2) {
				date = new Date(date.getTime() + 24 * 60 * 60 * 1000);
				head.currentLabel.setText(df.format(date));
			}
			if (type == 3) {
			}

			if (type == 5) {
			}
		}

		public void mouseEntered(MouseEvent e) {
			((JButton) e.getSource()).setIcon(newIcon);
		}

		public void mouseExited(MouseEvent e) {

			((JButton) e.getSource()).setIcon(oldIcon);
		}

		public void mouseClicked(MouseEvent e) {

		}
	}

}
