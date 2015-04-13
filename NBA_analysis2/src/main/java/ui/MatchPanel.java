package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.BLController;
import logic.matches.KingsOfMatch;
import logic.matches.Match;
import logic.teams.Team;

public class MatchPanel extends JPanel {

	private HeadLabel head;
	private int width;
	private int height;
	private boolean isFolded = false;
	int currentIndex = 0;
	MatchTablePanel table;
	BLController bl;
	Thread thread;
	Date date;
	ArrayList<Match> matchList;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public MatchPanel(int width1, int height1, BLController bl) {
		super();
		/*
		 * this.width=width1; this.height = height1;
		 */
		this.bl = bl;
		this.width = width1;
		this.height = height1;
		this.setBounds(0, 0, width, height);
		this.setLayout(null);
		setBackground(Color.white);
		try {
			date = df.parse("2014-01-01");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		this.matchList = new ArrayList<Match>(
				bl.getTodayMatches("13-14_2014-01-01"));
		addChildren();

	}

	public void update(Graphics g) {
		paint(g);
	}

	public void addChildren() {
		int size = matchList.size();

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
		this.setPreferredSize(new Dimension(width, 200 + size * 320));

	}

	public void unfold(final int index) {
		final int size = matchList.size();

		head = new HeadLabel(width, height / 10);
	
		this.setPreferredSize(new Dimension(width, 1000 + size * 320));
		//thread.interrupt();
		
		//notifyAll();
		thread = new Thread(new Runnable() {
			@Override
			public void run() {
				for (int j = 0; j <= 30; j++) {
					MatchPanel.this.removeAll();
					MatchPanel.this.add(head);
					
					
					for (int i = 0; i < size; i++) {
						if (i <= index) {
							InfoLabel info = new InfoLabel(0, 150 + 320 * i,
									width, 300, matchList.get(i));
							MatchPanel.this.add(info);
						} else {
							InfoLabel info = new InfoLabel(0, 150 + 10 * j + 5
									* j * j / 9 + 320 * i, width, 300,
									matchList.get(i));
							MatchPanel.this.add(info);
						}
					}
					table.setBounds(0, 470+320*index, width, 10 * j + 5
							* j * j / 9);
					MatchPanel.this.add(table);
					
					MatchPanel.this.repaint();
					 MatchPanel.this.updateUI();

					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						// TODO 自动生成的 catch 块
						e.printStackTrace();
					}

				}
			}
		});
		thread.start();
		
		

	}

	public void fold(final int index) {
		MatchPanel.this.removeAll();
		addChildren();
		MatchPanel.this.updateUI();
	}

	public class HeadLabel extends JLabel {

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
		JButton stat;
		String date;
		Match match;
		Team[] teams;
		KingsOfMatch[] kings;

		public void paintComponent(Graphics g2) {
			Graphics2D g = (Graphics2D) g2.create();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
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
			g.drawImage(teams[0].getLogo(96, 80), 50, 60, this);
			g.drawImage(teams[1].getLogo(96, 80), 50, 170, this);
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.PLAIN, 30));
			g.drawString(match.getTeams()[0], 150, 90);
			g.drawString(match.getTeams()[1], 150, 200);
			g.setFont(new Font("default", Font.PLAIN, 15));

			g.drawString(
					teams[0].getNumOfVictory()
							+ "-"
							+ (teams[0].getNumOfMatches() - teams[0]
									.getNumOfVictory()), 160, 110);
			g.drawString(
					teams[1].getNumOfVictory()
							+ "-"
							+ (teams[1].getNumOfMatches() - teams[1]
									.getNumOfVictory()), 160, 220);
			g.drawString("1", 270, 60);
			g.drawString("2", 350, 60);
			g.drawString("3", 430, 60);
			g.drawString("4", 510, 60);
			g.setFont(new Font("default", Font.PLAIN, 30));
			g.drawString(match.getPointsList().get(0)[0] + "", 255, 110);
			g.drawString(match.getPointsList().get(1)[0] + "", 335, 110);
			g.drawString(match.getPointsList().get(2)[0] + "", 415, 110);
			g.drawString(match.getPointsList().get(3)[0] + "", 495, 110);

			g.setColor(new Color(169, 11, 51));
			g.drawString(match.getPointsList().get(0)[1] + "", 415, 210);
			g.drawString(match.getPointsList().get(1)[1] + "", 255, 210);
			g.drawString(match.getPointsList().get(2)[1] + "", 335, 210);
			g.drawString(match.getPointsList().get(3)[1] + "", 495, 210);
			g.setFont(new Font("default", Font.PLAIN, 40));
			g.setColor(new Color(169, 11, 51));
			g.drawString(match.getPoints()[1] + "", 580, 215);
			g.setColor(Color.black);
			g.drawString(match.getPoints()[0] + "", 580, 115);

			g.setFont(new Font("default", Font.BOLD, 20));
			g.drawString(match.getTeams()[0], 980, 70);
			g.drawString(match.getTeams()[1], 1380, 70);
			g.drawString("得分", 800, 110);
			g.drawString("篮板", 800, 170);
			g.drawString("助攻", 800, 230);
			g.setColor(Color.blue);
			g.setFont(new Font("default", Font.BOLD, 17));
			g.drawString(
					kings[0].getNameOfPointsKing() + "  "
							+ kings[0].getPoints(), 980, 110);
			g.drawString(
					kings[0].getNameOfReboundsKing() + "  "
							+ kings[0].getRebounds(), 980, 170);
			g.drawString(
					kings[0].getNameOfAssistsKing() + "  "
							+ kings[0].getAssists(), 980, 230);

			g.drawString(
					kings[1].getNameOfPointsKing() + "  "
							+ kings[1].getPoints(), 1380, 110);
			g.drawString(
					kings[1].getNameOfReboundsKing() + "  "
							+ kings[1].getRebounds(), 1380, 170);
			g.drawString(
					kings[1].getNameOfAssistsKing() + "  "
							+ kings[1].getAssists(), 1380, 230);

		}

		public InfoLabel(int x, int y, int width, int height, Match match) {
			this.width = width;
			this.height = height;
			this.match = match;
			teams = bl.getTeamsByMatch(match);
			kings = match.getKingsOfMatch();
			// System.out.println(teams[0]==null);
			this.setBounds(x, y, width, height);
			initButton();

		}

		public void initButton() {
			stat = new JButton("技术统计");
			stat.setSize(100, 30);
			stat.setLocation(0, height * 9 / 10);
			stat.setContentAreaFilled(false);
			stat.setBorderPainted(false);
			stat.setIcon(null);
			MouseHandle statListener = new MouseHandle(null, null, null, 3,
					currentIndex);
			currentIndex = (currentIndex+1)%matchList.size();
			stat.addMouseListener(statListener);
			this.add(stat);
		}
	}

	class MouseHandle extends MouseAdapter {

		ImageIcon newIcon;
		ImageIcon oldIcon;
		ImageIcon selIcon;
		int type;
		int index;

		public MouseHandle(ImageIcon newIm, ImageIcon oldIm,
				ImageIcon selectIm, int x) {
			newIcon = newIm;
			oldIcon = oldIm;
			selIcon = selectIm;
			type = x;
		}

		public MouseHandle(ImageIcon newIm, ImageIcon oldIm,
				ImageIcon selectIm, int x, int index) {
			newIcon = newIm;
			oldIcon = oldIm;
			selIcon = selectIm;
			type = x;
			this.index = index;
		}

		@Override
		public void mousePressed(MouseEvent e) {

			if (type == 1) {
				date = new Date(date.getTime() - 24 * 60 * 60 * 1000);

				head.currentLabel.setText(df.format(date));
				MatchPanel.this.matchList = new ArrayList<Match>(
						bl.getTodayMatches("13-14_" + df.format(date)));
				MatchPanel.this.removeAll();
				MatchPanel.this.addChildren();
				MatchPanel.this.updateUI();
			}
			if (type == 2) {
				date = new Date(date.getTime() + 24 * 60 * 60 * 1000);
				head.currentLabel.setText(df.format(date));
				MatchPanel.this.matchList = new ArrayList<Match>(
						bl.getTodayMatches("13-14_" + df.format(date)));
				MatchPanel.this.removeAll();
				MatchPanel.this.addChildren();
				MatchPanel.this.updateUI();
			}
			if (type == 3) {
				isFolded = !isFolded;
				if (!isFolded) {
					
					fold(index);
				} else {
					table = new MatchTablePanel(width, 800, matchList.get(index), bl);
					unfold(index);

				}
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
