package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import logic.teams.Team;

public class TeamComparePanel extends JPanel {
	private Team team1;
	private Team team2;
	private RadarChart radar;
	private LineChartPanel lineChartPanel;
	int width;
	int height;

	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(new Color(148, 148, 148));
		g.fillRect(0, 0, width, height);
		g.drawImage(team1.getLogo(420, 320), 58, 45, this);
		g.drawImage(team2.getLogo(420, 320), 1250, 45, this);

		g.setColor(Color.black);
		g.fillRect(538, 0, 655, 130);
		g.setColor(Color.white);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 100));
		g.drawString("VS", 800, 100);

		g.setColor(new Color(36, 36, 36));
		g.fillRect(0, 410, 537, 158);
		g.fillRect(1194, 410, 537, 158);
		g.setColor(new Color(104, 104, 104));
		g.fillRect(0, 568, 537, 35);
		g.fillRect(1194, 568, 537, 35);
		g.setColor(new Color(221, 61, 66));
		g.fillRect(0, 568, 537 * team1.getNumOfVictory() / 82, 35);
		g.setColor(new Color(6, 74, 150));
		g.fillRect(1728 - 537 * team2.getNumOfVictory() / 82, 568,
				537 * team2.getNumOfVictory() / 82, 35);

		g.setColor(Color.white);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		g.drawString(team1.getName(), 230, 454);
		g.drawString(team2.getName(), 1200, 454);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		g.drawString(
				(team1.getConference() == 'E' ? "东区" : "西区") + "#"
						+ team1.getRankingInLeague(), 411, 519);
		g.drawString(
				(team2.getConference() == 'E' ? "东区" : "西区") + "#"
						+ team2.getRankingInLeague(), 1200, 519);
		g.drawString(team1.getNumOfVictory() + "胜",
				537 * team1.getNumOfVictory() / 82 - 60, 593);
		g.drawString("负" + (82 - team1.getNumOfVictory()),
				537 * team1.getNumOfVictory() / 82, 593);
		g.drawString("胜" + team2.getNumOfVictory(),
				1728 - 537 * team2.getNumOfVictory() / 82, 593);
		g.drawString((82 - team2.getNumOfVictory()) + "负",
				1668 - 537 * team2.getNumOfVictory() / 82, 593);
	}

	public TeamComparePanel(Team team1, Team team2, int width, int height) {
		this.team1 = team1;
		this.team2 = team2;
		this.width = width;
		this.height = height;

		this.setSize(width, height);
		this.setLayout(null);
		setBackground(new Color(148, 148, 148));
		initRadar();
		initLineChart();
		
		this.setPreferredSize(new Dimension(width, 808 + 730+200));
	}
	
	private void initRadar(){
		double[] pa = { 2, 4, 6, 8, 4, 3, 1, 9 };
		double[] pb = { 1, 3, 0, 1, 8, 1, 5, 7 };
		double limit = 10;
		radar = new RadarChart(8, 5, 654, 562, pa, pb, limit);
		radar.setBounds(537, 130, 654, 562);
		this.add(radar);
		radar.go();
		this.repaint();

	}
	
	private void initLineChart(){
		lineChartPanel = new LineChartPanel(width, 730, team1, team2);
		lineChartPanel.setLocation(0, 808);
		this.add(lineChartPanel);
	}

}

class LineChartPanel extends JPanel {
	int width;
	int height;

	private LineChart lineChart;
	Team team1;
	Team team2;

	int currentChosen = 0;

	ArrayList<ChartButton> chartButtons = new ArrayList<ChartButton>();
	String modules[] = { "进攻", "防守", "节奏", "攻防比", "战绩", "交手" };

	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(new Color(46, 46, 46));
		g.fillRect(0, 0, width, height);
		g.setColor(new Color(83, 82, 100));
		g.fillRect(280, 0, 20, height);

	}

	public LineChartPanel(int width, int height, Team team1, Team team2) {
		super();
		this.width = width;
		this.height = height;
		this.team1 = team1;
		this.team2 = team2;
		this.setLayout(null);
		this.setSize(width, height);

		initButtons();
		initChart();
	}

	private void initChart(){
		double[] value = { 3, 1, 4, 8, 5, 7, 8, 7, 1, 4 };
		double[] value2 = { 1, 5, 7, 3, 8, 3, 5, 3, 6, 2 };
		String[] name = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		double limit = 10;
		lineChart = new LineChart(10, 5, 1180, height, limit, name, value,
				value2);
		lineChart.setBounds(295, 0, 1180, height);
		this.add(lineChart);
		lineChart.go();
		this.repaint();
	}
	
	private void initButtons() {
		//默认初始状态-->选择第一张表 
		ChartButton first = new ChartButton(modules[0], 295, 117);
		first.isChosen=true;
		first.setLocation(0, 0);
		chartButtons.add(first);
		this.add(first);
		
		
		for (int i = 1; i < modules.length; i++) {
			ChartButton tem = new ChartButton(modules[i], 295, 117);
			tem.setLocation(0, 120 * i);
			chartButtons.add(tem);
			this.add(tem);
		}
	}

	public void updateChart(String module) {
		int index; // 匹配名称和序号
		for (index = 0; index < modules.length; index++) {
			if (modules[index] == module) {
				break;
			}
		}

		if (currentChosen != -1) {
			chartButtons.get(currentChosen).recover();
		}
		
		currentChosen = index;
		
		/*
		 * 在这里更新折线图 接口未定
		 */

	}

}

class ChartButton extends JLabel implements MouseListener {
	String name;
	int width;
	int height;
	boolean isChosen;
	boolean isMouseEntered;

	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		
		g.setColor(new Color(83, 82, 100)); 
		g.fillRect(0, 0, width, height);
		 
		g.setFont(new Font("微软雅黑", Font.PLAIN, 50));
		FontMetrics fm = g.getFontMetrics();

		int stringWidth = fm.stringWidth(name);
		int stringAscent = fm.getAscent();
		int xCoordinate = getWidth() / 2 - stringWidth / 2;
		int yCoordinate = getHeight() / 2 + stringAscent / 3;

		if (isChosen) {
			g.setColor(new Color(191, 22, 28));
			int x[] = { 0, width - width / 20, width, width - width / 20, 0 };
			int y[] = { 0, 0, height / 2, height, height };
			g.fillPolygon(x, y, 5);

			g.setColor(Color.black);
			g.drawString(name, xCoordinate, yCoordinate);
		}

		else if ((!isChosen) && isMouseEntered) {
			g.setColor(new Color(107, 106, 120));
			g.fillRect(0, 0, width - width / 20, height);

			g.setColor(Color.white);
			g.drawString(name, xCoordinate, yCoordinate);
		}

		else {
			g.setColor(Color.black);
			g.fillRect(0, 0, width - width / 20, height);

			g.setColor(Color.white);
			g.drawString(name, xCoordinate, yCoordinate);
		}

	}

	public ChartButton(String name, int width, int height) {
		super();
		this.name = name;
		this.isChosen = false;
		this.isMouseEntered = false;
		this.width = width;
		this.height = height;

		this.setSize(width, height);
		this.addMouseListener(this);

	}
	
	public void recover(){
		isChosen = false;
		repaint();
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		isMouseEntered = true;
		repaint();

	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		isMouseEntered = false;
		repaint();

	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO 自动生成的方法存根

	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		isChosen = true;
		repaint();

	}

}
