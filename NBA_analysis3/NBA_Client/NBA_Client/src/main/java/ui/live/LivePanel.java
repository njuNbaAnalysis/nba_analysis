package ui.live;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
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
import ui.MatchTablePanel;
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

	LivePanel(int width, int height,BLservice bl,Matchvo match) {
		this.setLayout(null);
		this.width = width;
		this.height = height;
		this.bl = bl;
		this.match = match;
		this.setSize(width,height);
		setLabel();
		setButton();
		setContent();
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
		InfoLabel infoLabel = new InfoLabel(width,height/4,match);
		infoLabel.setLocation(0,0);
		this.add(infoLabel);

	}

	private class InfoLabel extends JLabel {
		private int labelWidth;
		private int labelHeight;
		private Teamvo [] teams;
		InfoLabel(int width,int height,Matchvo match) {
			this.labelWidth = width;
			this.labelHeight = height;
			this.setLayout(null);
			this.setSize(width, height);
			teams = bl.getTeamsByMatch(match);

		}
		public void paintComponent(Graphics g2){
			Graphics2D g = (Graphics2D) g2.create();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(new Color(246, 246, 246));
			g.fillRect(0, 0, labelWidth, labelHeight/10);
			g.setColor(new Color(122, 122, 122));
			g.fillRect(labelWidth*5/ 12, 0, labelWidth/ 6, labelHeight / 10);
			g.setColor(new Color(190, 157, 83));
			g.drawLine(0, labelHeight / 10, labelWidth, labelHeight / 10);

			
			g.setColor(Color.WHITE);
			g.fillRect(0, labelHeight / 10 + 1, labelWidth, labelHeight * 9 / 10 - 1);
			
			g.setColor(Color.BLACK);
			g.setFont(new Font("default", Font.BOLD, 20));
			int strWidth = g.getFontMetrics(g.getFont()).stringWidth("结束");
			g.drawString("结束",labelWidth/2-strWidth/2 ,labelHeight * 1 / 10-8);//这个结束需要修改
			
			g.drawImage(teams[0].getLogo(), labelWidth*12/36, labelHeight*7/24, this);
			g.drawImage(teams[1].getLogo(), labelWidth*83/144, labelHeight*7/24,this);
			
			
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.PLAIN, 30));
			g.drawString(match.getTeams()[0], labelWidth*5/36, labelHeight*5/12);
			g.drawString(match.getTeams()[1], labelWidth*30/36, labelHeight*5/12);
			
			
			g.setFont(new Font("default", Font.PLAIN, 15));

			g.drawString(
					"球队战绩"+teams[0].getNumOfVictory()
							+ "-"
							+ (teams[0].getNumOfMatches() - teams[0]
									.getNumOfVictory()), labelWidth*4/36, labelHeight*7/12);
			g.drawString(
					"球队战绩"+teams[1].getNumOfVictory()
							+ "-"
							+ (teams[1].getNumOfMatches() - teams[1]
									.getNumOfVictory()), labelWidth*30/36, labelHeight*7/12);
			
			g.setFont(new Font("default", Font.PLAIN, 80));
			strWidth = g.getFontMetrics(g.getFont()).stringWidth("VS");
			g.drawString("VS",labelWidth/2-strWidth/2 ,labelHeight  *7/ 12);
			
			g.setFont(new Font("default", Font.PLAIN, 15));
			
			
			g.drawString("1", labelWidth*10/24, labelHeight*2/3);
			g.drawString("2", labelWidth*11/24, labelHeight*2/3);
			g.drawString("3", labelWidth*12/24, labelHeight*2/3);
			g.drawString("4", labelWidth*13/24, labelHeight*2/3);
			
			
			g.setFont(new Font("default", Font.PLAIN, 30));
			g.drawString(match.getTeams()[0], labelWidth*8/24, labelHeight*19/24);
			g.drawString(match.getTeams()[1], labelWidth*8/24, labelHeight*22/24);
			g.drawString(match.getPointsList().get(0)[0] + "", labelWidth*10/24, labelHeight*19/24);
			g.drawString(match.getPointsList().get(1)[0] + "", labelWidth*11/24, labelHeight*19/24);
			g.drawString(match.getPointsList().get(2)[0] + "", labelWidth*12/24, labelHeight*19/24);
			g.drawString(match.getPointsList().get(3)[0] + "", labelWidth*13/24, labelHeight*19/24);
			g.drawString(match.getPoints()[0]+"", labelWidth*14/24, labelHeight*19/24);
			g.setColor(new Color(169, 11, 51));
			
			g.drawString(match.getPointsList().get(0)[1] + "", labelWidth*10/24, labelHeight*22/24);
			g.drawString(match.getPointsList().get(1)[1] + "", labelWidth*11/24, labelHeight*22/24);
			g.drawString(match.getPointsList().get(2)[1] + "", labelWidth*12/24, labelHeight*22/24);
			g.drawString(match.getPointsList().get(3)[1] + "", labelWidth*13/24, labelHeight*22/24);
			g.drawString(match.getPoints()[1]+"", labelWidth*14/24, labelHeight*22/24);
			
			g.setFont(new Font("default", Font.PLAIN, 70));
			g.setColor(Color.black);
			g.drawString(match.getPoints()[0]+"", labelWidth*6/24, labelHeight*13/24);
			g.setColor(new Color(169, 11, 51));
			g.drawString(match.getPoints()[1]+"", labelWidth*16/24, labelHeight*13/24);
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
				
				MatchTablePanel table =  new MatchTablePanel(width, height * 13 / 20, match, bl);
				table.setBounds(0,0, width, height * 13 / 20);
				content.removeAll();
				content.updateUI();
				
				content.add(table);
				repaint();
			}

			if (type == 1) {
				wordLivePanel =  new WordLivePanel(width, height * 13 / 20, eventList);
				wordLivePanel.setLocation(0,0);
				content.removeAll();
				content.updateUI();
				
				content.add(wordLivePanel);
				repaint();
			}

			if (type == 2) {
				String [] column = {"%","三分%","罚球%","篮板","助攻","盖帽","失误","快攻得分","禁区得分","对方失误得分","最多领先分数"};
				TeamComparePanel comparePanel =  new TeamComparePanel(width, height * 13 / 20, column,match);
				comparePanel.setLocation(0,0);
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
		if(wordLivePanel!=null){
			int latestTime = 0;
			for(EventVo event:eventList){
				if(latestTime>event.getTimeInSecond()){
					latestTime = event.getTimeInSecond();
				}
			}
			
			int newTime = eventList.get(0).getTimeInSecond();
			for(EventVo event:newEventList){
				if(event.getTimeInSecond()>latestTime){
					eventList.add(event);
				}
			}
			
			
			wordLivePanel.refresh(eventList);
		}
		
	}
	
	public void setMatch(Matchvo match){
		this.match = match;
	}

	public static void main(String[] args) {
		final JFrame f = new JFrame();
		f.setBounds(0, 0, 1280, 1080);
		DataFactory factory = DataFactoryMySql.getInstance();
		final BLservice bl = factory.getBLservice();

		Matchvo m = bl.getLiveMatchInfo();
		final LivePanel chart = new LivePanel(1280, 1080,bl,m);
		chart.setBounds(0, 0, 1280, 1080);
	
		f.setLayout(null);
		f.add(chart);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// ArrayList<EventVo> eventList = new ArrayList<EventVo>();
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				int i = 0;
				while (true) {
					ArrayList<EventVo> eventList = bl.getLiveEvent();

					
					Matchvo m = bl.getLiveMatchInfo();
					
					chart.refresh(eventList);
					chart.setMatch(m);
					try {
						Thread.sleep(300);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					f.repaint();
				}
				
				

			}
		});
		thread.start();
	}

	
}
