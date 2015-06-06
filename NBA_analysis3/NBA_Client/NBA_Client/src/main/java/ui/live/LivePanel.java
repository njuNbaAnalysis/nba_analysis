package ui.live;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import dataFactory.DataFactory;
import dataFactory.DataFactoryMySql;
import BLservice.BLservice;
import ui.match.MatchTablePanel;
import util.Tools;
import util.UIUtils;
import vo.EventVo;
import vo.Matchvo;
import vo.Teamvo;

public class LivePanel extends JPanel {
	private int width;
	private int height;
	private int selectedNumber = -1;
	private JScrollPane content;
	private LiveButton[] btArray;
	private ArrayList<EventVo> eventList = new ArrayList<EventVo>();
	WordLivePanel wordLivePanel;

	private Matchvo match;
	private BLservice bl;

	LivePanel(int width, int height, BLservice bl, Matchvo match) {
		this.setLayout(null);
		this.width = width;
		this.height = height;
		this.bl = bl;
		this.match = match;
		this.setSize(width, height);

		setLabel();
		setButton();
		setContent();

		Thread live = new Thread(new LiveThread());
		live.start();
	}

	private void setContent() {
		content = new JScrollPane();
		content.setBounds(0, height * 6 / 20, width, height * 14 / 20);
		content.setLayout(null);
		wordLivePanel = new WordLivePanel(width, height * 13 / 20, eventList);
		wordLivePanel.setLocation(0, 0);
		content.add(wordLivePanel);
		this.add(content);

	}

	private void setLabel() {
		InfoLabel infoLabel = new InfoLabel(width, height / 4, match);
		infoLabel.setLocation(0, 0);
		this.add(infoLabel);

	}

	private class InfoLabel extends JLabel {
		private int labelWidth;
		private int labelHeight;
		private Teamvo[] teams;

		InfoLabel(int width, int height, Matchvo match) {
			this.labelWidth = width;
			this.labelHeight = height;
			this.setLayout(null);
			this.setSize(width, height);

			this.teams = new Teamvo[2];
			try {
				// System.out.println(match.getSeason());
				// System.out.println(match.getSeason()); //
				teams[0] = bl.getTeamByTeamName(match.getTeams()[0],
						match.getSeason(), match.isIsplayoff());
				teams[1] = bl.getTeamByTeamName(match.getTeams()[1],
						match.getSeason(), match.isIsplayoff());
			} catch (RemoteException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

		public void paintComponent(Graphics g2) {
			Graphics2D g = (Graphics2D) g2.create();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(new Color(246, 246, 246));
			g.fillRect(0, 0, labelWidth, labelHeight / 10);
			g.setColor(new Color(122, 122, 122));
			g.fillRect(labelWidth * 5 / 12, 0, labelWidth / 6, labelHeight / 10);
			g.setColor(new Color(190, 157, 83));
			g.drawLine(0, labelHeight / 10, labelWidth, labelHeight / 10);

			g.setColor(Color.WHITE);
			g.fillRect(0, labelHeight / 10 + 1, labelWidth,
					labelHeight * 9 / 10 - 1);

			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 20));
			int strWidth = g.getFontMetrics(g.getFont()).stringWidth("结束");
			g.drawString("结束", labelWidth / 2 - strWidth / 2,
					labelHeight * 1 / 10 - 8);// 这个结束需要修改


			g.drawImage(UIUtils.resize(teams[0].getLogo(), labelWidth / 10,
					labelHeight * 3 / 10), labelWidth * 12 / 36,
					labelHeight * 7 / 24, this);
			g.drawImage(UIUtils.resize(teams[1].getLogo(), labelWidth / 10,
					labelHeight * 3 / 10), labelWidth * 83 / 144,
					labelHeight * 7 / 24, this);

			g.setColor(Color.black);
			g.setFont(new Font("default", Font.PLAIN, 30));
			g.drawString(match.getTeams()[0], labelWidth * 5 / 36,
					labelHeight * 5 / 12);
			g.drawString(match.getTeams()[1], labelWidth * 30 / 36,
					labelHeight * 5 / 12);

			g.setFont(new Font("default", Font.PLAIN, 15));

			g.drawString(
					"球队战绩"
							+ teams[0].getNumOfVictory()
							+ "-"
							+ (teams[0].getNumOfMatches() - teams[0]
									.getNumOfVictory()), labelWidth * 4 / 36,
					labelHeight * 7 / 12);
			g.drawString(
					"球队战绩"
							+ teams[1].getNumOfVictory()
							+ "-"
							+ (teams[1].getNumOfMatches() - teams[1]
									.getNumOfVictory()), labelWidth * 30 / 36,
					labelHeight * 7 / 12);

			g.setFont(new Font("default", Font.PLAIN, 80));
			strWidth = g.getFontMetrics(g.getFont()).stringWidth("VS");
			g.drawString("VS", labelWidth / 2 - strWidth / 2,
					labelHeight * 7 / 12);

			g.setFont(new Font("default", Font.PLAIN, 15));

			g.drawString(match.getTeams()[0], labelWidth * 8 / 24,
					labelHeight * 19 / 24);
			g.drawString(match.getTeams()[1], labelWidth * 8 / 24,
					labelHeight * 22 / 24);

			// 有加时的情况
			int section = match.getPointsList().size();
			int extwidth = labelWidth*11/24;
			g.setFont(new Font("default", Font.PLAIN, 15));

			for (int i = 0; i < section; i++) {
				g.setColor(Color.BLACK);
				if(i>=4){
					g.drawString("加时"+(i-3) + "", labelWidth * (10 + i) / 24,
							labelHeight * 2 / 3);
				}else{
					g.drawString(i + "", labelWidth * (10 + i) / 24,
							labelHeight * 2 / 3);
				}
				
				g.drawString(match.getPointsList().get(i)[0] + "", labelWidth
						* (10 + i) / 24, labelHeight * 19 / 24);
				g.setColor(new Color(169, 11, 51));
				g.drawString(match.getPointsList().get(i)[1] + "", labelWidth
						* (10 + i) / 24, labelHeight * 22 / 24);
				extwidth += labelWidth / 24;

			}
			
			g.setColor(Color.BLACK);
			g.drawString(match.getPoints()[0] + "", extwidth, labelHeight * 19 / 24);
			
			g.setColor(new Color(169, 11, 51));
			g.drawString(match.getPoints()[1] + "",extwidth, labelHeight * 22 / 24);
			
			

			g.setFont(new Font("default", Font.PLAIN, 70));
			g.setColor(Color.black);
			g.drawString(match.getPoints()[0] + "", labelWidth * 5 / 24,
					labelHeight * 13 / 24);
			g.setColor(new Color(169, 11, 51));
			g.drawString(match.getPoints()[1] + "", labelWidth * 17 / 24,
					labelHeight * 13 / 24);
		}
	}

	private void setButton() {

		String[] btNames = { "技术统计", "文字直播", "球队对比" };
		btArray = new LiveButton[btNames.length];
		for (int i = 0; i < btNames.length; i++) {
			btArray[i] = new LiveButton(btNames[i], i);
			btArray[i].setBounds(width * (2 * i + 1) / 8, height / 4,
					width / 4, height / 20);
			MouseHandle mouseHandle = new MouseHandle(new Color(69, 69, 69),
					new Color(87, 89, 91), i);
			btArray[i].addMouseListener(mouseHandle);
			this.add(btArray[i]);

		}

	}

	private class LiveButton extends JButton {
		private String field;
		private int number;

		LiveButton(String text, int number) {
			this.field = text;
			this.number = number;
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setForeground(Color.white);
			this.setBackground(new Color(87, 89, 91));
			this.setBorder(BorderFactory.createEmptyBorder());
			this.setFocusPainted(false);

			this.setFont(new Font("微软雅黑", Font.CENTER_BASELINE, 20));
			this.setText(text);
		}
	}

	private class MouseHandle extends MouseAdapter {

		Color newColor;
		Color oldColor;
		int type;

		public MouseHandle(Color newColor, Color oldColor, int x) {
			this.newColor = newColor;
			this.oldColor = oldColor;
			type = x;

		}

		@Override
		public void mousePressed(MouseEvent e) {
			if ((selectedNumber != type)) {
				((JButton) e.getSource()).setBackground(newColor);
				if (selectedNumber != -1) {
					btArray[selectedNumber].setBackground(oldColor);
				}

				selectedNumber = type;
			}
			if (type == 0) {

				MatchTablePanel table = new MatchTablePanel(width,
						height * 13 / 20, match, bl);
				table.setBounds(0, 0, width, height * 13 / 20);
				content.removeAll();
				content.updateUI();

				content.add(table);
				repaint();
			}

			if (type == 1) {
				wordLivePanel = new WordLivePanel(width, height * 13 / 20,
						eventList);
				wordLivePanel.setLocation(0, 0);
				content.removeAll();
				content.updateUI();

				content.add(wordLivePanel);
				repaint();
			}

			if (type == 2) {
				String[] column = { "%", "三分%", "罚球%", "篮板", "助攻", "盖帽", "失误",
						"快攻得分", "禁区得分", "对方失误得分", "最多领先分数" };
				TeamComparePanel comparePanel = new TeamComparePanel(width,
						height * 13 / 20, column, match);
				comparePanel.setLocation(0, 0);
				content.removeAll();
				content.updateUI();

				content.add(comparePanel);
				repaint();
			}

		}

		public void mouseEntered(MouseEvent e) {

			if (selectedNumber != type) {
				((JButton) e.getSource()).setBackground(newColor);
			}
		}

		public void mouseExited(MouseEvent e) {

			if (selectedNumber != type) {
				((JButton) e.getSource()).setBackground(oldColor);
			}
		}

		public void mouseClicked(MouseEvent e) {

		}
	}

	public void refresh(ArrayList<EventVo> newEventList) {
		if (wordLivePanel != null && newEventList.size() > 0) {
			int latestTime = 0;
			EventVo latestEvent = null;
			if (eventList.size() != 0) {
				for (EventVo event : eventList) {
					if (latestTime > event.getTimeInSecond()) {
						latestEvent = event;
						latestTime = event.getTimeInSecond();
					}
				}
			}

			// 最新的事件在newEventList的第一个
			for (int i = newEventList.size() - 1; i >= 0; i--) {
				EventVo event = newEventList.get(i);
				if (event.getTimeInSecond() >= latestTime) {
					if (latestEvent == null
							|| !event.getDescription().equals(
									latestEvent.getDescription())) {
						eventList.add(event);
					}
				}
			}

			wordLivePanel.refresh(eventList);
		}

	}

	public void setMatch(Matchvo match) {
		this.match = match;
	}

	private class LiveThread implements Runnable {

		@Override
		public void run() {

			while (true) {
				ArrayList<EventVo> eventList = null;
				Matchvo m = null;
				try {
					eventList = bl.getLiveEvent();
					System.out.println("eventList" + eventList.size());
					m = bl.getLiveMatchInfo();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

				LivePanel.this.refresh(eventList);
				LivePanel.this.setMatch(m);
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

			}

		}

	}

	public static void main(String[] args) {
		final JFrame f = new JFrame();
		f.setBounds(0, 0, 1280, 1080);
		DataFactory factory = DataFactoryMySql.getInstance();
		final BLservice bl = factory.getBLservice();
		try {
			bl.initNBALive();
		} catch (RemoteException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}

		Matchvo m = null;
		try {
			m = bl.getLiveMatchInfo();
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		LivePanel chart = new LivePanel(1280, 1080, bl, m);
		chart.setBounds(0, 0, 1280, 1080);

		f.setLayout(null);
		f.add(chart);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}
