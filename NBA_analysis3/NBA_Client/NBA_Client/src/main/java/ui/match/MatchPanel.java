package ui.match;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.live.LivePanel;
import ui.player.PlayerInfoPanel;
import util.UIUtils;
import vo.FutureMatchvo;
import vo.KingsOfMatchvo;
import vo.Matchvo;
import vo.Playervo;
import vo.Teamvo;
import BLservice.BLservice;



public class MatchPanel extends JPanel {

	private HeadLabel head;
	private int width;
	private int height;
	private boolean isFolded = false;
	String season = "13-14";
	boolean isPlayOff;
	int currentIndex = 0;
	JPanel content;
	MatchTablePanel table;
	BLservice bl;
	Thread thread;
	Date date;
	String dateForMatch;
	String dateForFutureMatch;
	ArrayList<Matchvo> matchList;
	ArrayList<FutureMatchvo> futureMatchList;
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

	public MatchPanel(int width1, int height1, BLservice bl,JPanel content,String season,boolean isPlayOff) {
		super();
		/*
		 * this.width=width1; this.height = height1;
		 */
		this.content = content;
		this.bl = bl;
		this.width = width1;
		this.height = height1;
		this.setBounds(0, 0, width, height);
		this.setLayout(null);
		this.season = season;
		this.isPlayOff = isPlayOff;
		setBackground(Color.white);
		
		date = new Date();
		
		dateForFutureMatch = df.format(date);
		int year = date.getYear()+1900;
		int month = date.getMonth();
		System.out.println("year:"+year+" month"+month);
		if(month<=7){
			dateForMatch = ((year-1)%100)+"-"+(year%100)+"_"+dateForFutureMatch;
		}else{
			dateForMatch = (year%100)+"-"+((year+1)%100)+"_"+dateForFutureMatch;
		}
		System.out.println(dateForMatch);
		System.out.println(dateForFutureMatch);		
		try {
			this.matchList = new ArrayList<Matchvo>(
					bl.getTodayMatches(dateForMatch));
			this.futureMatchList = bl.getFutureMatches(dateForFutureMatch);
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
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
			
		}
		
		int sizeF = futureMatchList.size();
		for (int i = 0; i < sizeF; i++) {
			LiveInfoLabel info = new LiveInfoLabel(0, 150 + 320 * (i+size), width, 300,
					futureMatchList.get(i));
			this.add(info);
			
		}
		
		this.setPreferredSize(new Dimension(width, 200 + (size+sizeF) * 320));

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

	public class InfoLabel extends JLabel {

		int width;
		int height;
		JButton stat;
		Matchvo match;
		Teamvo[] teams = new Teamvo[2];
		KingsOfMatchvo[] kings;
		JButton b1;
		JButton b2;
		JButton b3;
		JButton b4;
		JButton b5;
		JButton b6;

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
			g.drawImage(UIUtils.resize(teams[0].getLogo(), 96, 80), 50, 60, this);
			g.drawImage(UIUtils.resize(teams[1].getLogo(), 96, 80), 50, 170, this);
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
			/*g.drawString(
					kings[0].getNameOfPointsKing() + "", 980, 110);*/
			/*g.drawString(
					kings[0].getNameOfReboundsKing() + "", 980, 170);*/
			/*g.drawString(
					kings[0].getNameOfAssistsKing() + "", 980, 230);*/

			/*g.drawString(
					kings[1].getNameOfPointsKing() + "", 1380, 110);
			g.drawString(
					kings[1].getNameOfReboundsKing() + "", 1380, 170);
			g.drawString(
					kings[1].getNameOfAssistsKing() + "", 1380, 230);*/
			
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.PLAIN, 17));
			g.drawString(
					 kings[0].getPoints()+"", 1200, 110);
			g.drawString(
					kings[0].getRebounds()+"", 1200, 170);
			g.drawString(
					kings[0].getAssists()+"", 1200, 230);

			g.drawString(
					 kings[1].getPoints()+"", 1600, 110);
			g.drawString(
					 kings[1].getRebounds()+"", 1600, 170);
			g.drawString(
					kings[1].getAssists()+"", 1600, 230);

		}

		public InfoLabel(int x, int y, int width, int height, Matchvo match) {
			this.width = width;
			this.height = height;
			this.match = match;
			String[] teamNames = match.getTeams();
			
			try {
				System.out.println(teamNames[0]);
				System.out.println(teamNames[1]);
				System.out.println(season);
				System.out.println(isPlayOff);
				teams[0] = bl.getTeamByTeamName(teamNames[0], season, isPlayOff);
				teams[1] = bl.getTeamByTeamName(teamNames[1], season, isPlayOff);
			} catch (RemoteException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
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
			
			b1 = new JButton(kings[0].getNameOfReboundsKing());
			b1.setForeground(Color.blue);
			b1.setFont(new Font("default", Font.BOLD, 17));
			b1.setContentAreaFilled(false);
			b1.setBorderPainted(false);
			b1.setBounds(930, 150,200,30);
			b1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			b1.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                
	                	//Playervo p = MatchPanel.this.bl.getPlayerByName(kings[0].getNameOfReboundsKing());
	            		Playervo p=null;
						try {
							p = MatchPanel.this.bl.getPlayerById("104");
						} catch (RemoteException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
	            	
	            		PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel(MatchPanel.this.width,MatchPanel.this.height,p,MatchPanel.this.bl,MatchPanel.this.content, season, isPlayOff);
	            		playerInfoPanel.setBounds(0, 0, MatchPanel.this.width, MatchPanel.this.height);
	            		playerInfoPanel.startAnimation();
	            		MatchPanel.this.content.removeAll();
	            		MatchPanel.this.content.add(playerInfoPanel);
	            		MatchPanel.this.content.updateUI();
	            		playerInfoPanel.startAnimation();
	            }
	        });
			this.add(b1);
			
			b2 = new JButton(kings[0].getNameOfPointsKing());
			b2.setForeground(Color.blue);
			b2.setFont(new Font("default", Font.BOLD, 17));
			b2.setContentAreaFilled(false);
			b2.setBorderPainted(false);
			b2.setBounds(930, 90,200,30);
			b2.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			b2.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                
	                	//Playervo p = MatchPanel.this.bl.getPlayerByName(kings[0].getNameOfPointsKing());
	            	Playervo p=null;
					try {
						p = MatchPanel.this.bl.getPlayerById("104");
					} catch (RemoteException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
	            		PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel(MatchPanel.this.width,MatchPanel.this.height,p,MatchPanel.this.bl,MatchPanel.this.content,season, isPlayOff);
	            		playerInfoPanel.setBounds(0, 0, MatchPanel.this.width, MatchPanel.this.height);
	            		playerInfoPanel.startAnimation();
	            		MatchPanel.this.content.removeAll();
	            		MatchPanel.this.content.add(playerInfoPanel);
	            		MatchPanel.this.content.updateUI();
	            		playerInfoPanel.startAnimation();
	            }
	        });
			this.add(b2);
			
			b3 = new JButton(kings[0].getNameOfAssistsKing());
			b3.setForeground(Color.blue);
			b3.setFont(new Font("default", Font.BOLD, 17));
			b3.setContentAreaFilled(false);
			b3.setBorderPainted(false);
			b3.setBounds(930, 210,200,30);
			b3.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			b3.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                
	                	//Playervo p = MatchPanel.this.bl.getPlayerByName(kings[0].getNameOfAssistsKing());
	            	Playervo p=null;
					try {
						p = MatchPanel.this.bl.getPlayerById("104");
					} catch (RemoteException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
	            		PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel(MatchPanel.this.width,MatchPanel.this.height,p,MatchPanel.this.bl,MatchPanel.this.content,season, isPlayOff);
	            		playerInfoPanel.setBounds(0, 0, MatchPanel.this.width, MatchPanel.this.height);
	            		playerInfoPanel.startAnimation();
	            		MatchPanel.this.content.removeAll();
	            		MatchPanel.this.content.add(playerInfoPanel);
	            		MatchPanel.this.content.updateUI();
	            		playerInfoPanel.startAnimation();
	            }
	        });
			this.add(b3);
			
			
			b4 = new JButton(kings[1].getNameOfPointsKing());
			b4.setForeground(Color.blue);
			b4.setFont(new Font("default", Font.BOLD, 17));
			b4.setContentAreaFilled(false);
			b4.setBorderPainted(false);
			b4.setBounds(1330, 90,200,30);
			b4.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			b4.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                
	                	//Playervo p = MatchPanel.this.bl.getPlayerByName(kings[1].getNameOfPointsKing());
	            	Playervo p=null;
					try {
						p = MatchPanel.this.bl.getPlayerById("104");
					} catch (RemoteException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
	            		PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel(MatchPanel.this.width,MatchPanel.this.height,p,MatchPanel.this.bl,MatchPanel.this.content,season, isPlayOff);
	            		playerInfoPanel.setBounds(0, 0, MatchPanel.this.width, MatchPanel.this.height);
	            		playerInfoPanel.startAnimation();
	            		MatchPanel.this.content.removeAll();
	            		MatchPanel.this.content.add(playerInfoPanel);
	            		MatchPanel.this.content.updateUI();
	            		playerInfoPanel.startAnimation();
	            }
	        });
			this.add(b4);
			
			
			b5 = new JButton(kings[1].getNameOfReboundsKing());
			b5.setForeground(Color.blue);
			b5.setFont(new Font("default", Font.BOLD, 17));
			b5.setContentAreaFilled(false);
			b5.setBorderPainted(false);
			b5.setBounds(1330, 150,200,30);
			b5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			b5.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                
	                	//Playervo p = MatchPanel.this.bl.getPlayerByName(kings[1].getNameOfReboundsKing());
	            	Playervo p=null;
					try {
						p = MatchPanel.this.bl.getPlayerById("104");
					} catch (RemoteException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
	            		PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel(MatchPanel.this.width,MatchPanel.this.height,p,MatchPanel.this.bl,MatchPanel.this.content,season, isPlayOff);
	            		playerInfoPanel.setBounds(0, 0, MatchPanel.this.width, MatchPanel.this.height);
	            		playerInfoPanel.startAnimation();
	            		MatchPanel.this.content.removeAll();
	            		MatchPanel.this.content.add(playerInfoPanel);
	            		MatchPanel.this.content.updateUI();
	            		playerInfoPanel.startAnimation();
	            }
	        });
			this.add(b5);
			
			
			b6 = new JButton(kings[1].getNameOfAssistsKing());
			b6.setForeground(Color.blue);
			b6.setFont(new Font("default", Font.BOLD, 17));
			b6.setContentAreaFilled(false);
			b6.setBorderPainted(false);
			b6.setBounds(1330, 210,200,30);
			b6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
			b6.addMouseListener(new MouseAdapter() {
	            @Override
	            public void mouseClicked(MouseEvent e) {
	                
	                	//Playervo p = MatchPanel.this.bl.getPlayerByName(kings[1].getNameOfAssistsKing());
	            	Playervo p=null;
					try {
						p = MatchPanel.this.bl.getPlayerById("104");
					} catch (RemoteException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
	            		PlayerInfoPanel playerInfoPanel = new PlayerInfoPanel(MatchPanel.this.width,MatchPanel.this.height,p,MatchPanel.this.bl,MatchPanel.this.content,season, isPlayOff);
	            		playerInfoPanel.setBounds(0, 0, MatchPanel.this.width, MatchPanel.this.height);
	            		playerInfoPanel.startAnimation();
	            		MatchPanel.this.content.removeAll();
	            		MatchPanel.this.content.add(playerInfoPanel);
	            		MatchPanel.this.content.updateUI();
	            		playerInfoPanel.startAnimation();
	            }
	        });
			this.add(b6);
			
			
		}
	}

	public class LiveInfoLabel extends JLabel {

		int width;
		int height;
		JButton stat;
		JButton live;
		FutureMatchvo match;
		Image imgH;
		Image imgA;
		

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
			g.drawString("直播", 45, 22);
			
			g.drawImage(UIUtils.resize(imgH, 96, 80), 50, 60, this);
			g.drawImage(UIUtils.resize(imgA, 96, 80), 50, 170, this);
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.PLAIN, 30));
			g.drawString(match.getHome_team(), 150, 90);
			g.drawString(match.getAway_team(), 150, 200);
			g.setFont(new Font("default", Font.PLAIN, 15));

			
			g.drawString("1", 270, 60);
			g.drawString("2", 350, 60);
			g.drawString("3", 430, 60);
			g.drawString("4", 510, 60);
			g.setFont(new Font("default", Font.PLAIN, 30));
			g.drawString(0 + "", 255, 110);
			g.drawString(0 + "", 335, 110);
			g.drawString(0 + "", 415, 110);
			g.drawString(0 + "", 495, 110);

			g.setColor(new Color(169, 11, 51));
			g.drawString(0 + "", 415, 210);
			g.drawString(0 + "", 255, 210);
			g.drawString(0 + "", 335, 210);
			g.drawString(0 + "", 495, 210);
			g.setFont(new Font("default", Font.PLAIN, 40));
			g.setColor(new Color(169, 11, 51));
			g.drawString(0 + "", 580, 215);
			g.setColor(Color.black);
			g.drawString(0 + "", 580, 115);

			

		}

		public LiveInfoLabel(int x, int y, int width, int height, FutureMatchvo match) {
			this.width = width;
			this.height = height;
			this.match = match;
			
			String pathH = "Data" + File.separator +"teamImage"+File.separator+ match.getHome_team() + ".png";
			String pathA = "Data" + File.separator +"teamImage"+File.separator+ match.getAway_team() + ".png";
			try {
				imgH  = ImageIO.read(new File(pathH));
				imgA  = ImageIO.read(new File(pathA));
			} catch (IOException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			
			
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
			
			live = new JButton("比赛直播");
			live.setSize(100, 30);
			live.setLocation(60, height * 9 / 10);
			live.setContentAreaFilled(false);
			live.setBorderPainted(false);
			live.setIcon(null);
			
			live.addMouseListener(new MouseListener() {
				
				@Override
				public void mouseReleased(MouseEvent arg0) {
					LivePanel panel = new LivePanel(MatchPanel.this.width, MatchPanel.this.height, MatchPanel.this.bl, match.getMid());
					MatchPanel.this.removeAll();
					MatchPanel.this.add(panel);
					panel.setLocation(0, 0);
					
				}
				
				@Override
				public void mousePressed(MouseEvent arg0) {
					// TODO 自动生成的方法存根
					
				}
				
				@Override
				public void mouseExited(MouseEvent arg0) {
					// TODO 自动生成的方法存根
					
				}
				
				@Override
				public void mouseEntered(MouseEvent arg0) {
					// TODO 自动生成的方法存根
					
				}
				
				@Override
				public void mouseClicked(MouseEvent arg0) {
					// TODO 自动生成的方法存根
					
				}
			});
			this.add(live);
			
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
				try {
					MatchPanel.this.matchList = new ArrayList<Matchvo>(
							bl.getTodayMatches("13-14_" + df.format(date)));
				} catch (RemoteException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				MatchPanel.this.removeAll();
				MatchPanel.this.addChildren();
				MatchPanel.this.updateUI();
			}
			if (type == 2) {
				date = new Date(date.getTime() + 24 * 60 * 60 * 1000);
				head.currentLabel.setText(df.format(date));
				try {
					MatchPanel.this.matchList = new ArrayList<Matchvo>(
							bl.getTodayMatches("13-14_" + df.format(date)));
				} catch (RemoteException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
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
