package ui.team;

import javax.swing.JPanel;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import dataFactory.DataFactoryMySql;
import BLservice.BLservice;
import util.UIUtils;
import vo.Matchvo;
import vo.Playervo;
import vo.Teamvo;

public class PredictPanel extends JPanel {
	private boolean[] attribute;
	private String[] attributeNames;
	private double[] cof;
	private String season;
	private boolean isPlayOff;
	private int width;
	private int height;
	private int defauleNum = 8;// 默认显示的选项数量

	private JButton setting;
	ImageIcon settingIcon;
	ImageIcon settingIconD;
	ImageIcon settingIconB;
	boolean selected = false;
	private JScrollPane content;

	private Teamvo team1;
	private Teamvo team2;

	private BLservice bl;

	private double win;
	private double[] points;

	protected DecimalFormat df = new DecimalFormat("#0.0");

	PredictPanel(int width, int height, String[] attributeNames, Teamvo team1,
			Teamvo team2, BLservice bl, String season, boolean isPlayOff) throws RemoteException {
		this.width = width;
		this.height = height;
		this.attributeNames = attributeNames;
		this.season = season;
		this.isPlayOff = isPlayOff;
		this.team1 = team1;
		this.team2 = team2;
		this.bl = bl;
		this.win = bl.getWinPercentage(team1.getAbbreviation(), team2.getAbbreviation(), season, isPlayOff);
		this.points[0] = bl.getTeamPoints(team1.getAbbreviation(), season, isPlayOff);
		this.points[1] = bl.getTeamPoints(team1.getAbbreviation(), season, isPlayOff);
		
		this.setLayout(null);
		this.setSize(width, height);

		init();
		loadImage();
		setButton();
		content = new JScrollPane();
		content.setBounds(0, height * 5 / 20, width, height * 15 / 20);

		content.setLayout(null);
		content.getVerticalScrollBar().setUnitIncrement(20);
		CompareBarChartPanel chartPanel = new CompareBarChartPanel(width,
				height * 12 / 20, attribute, attributeNames, getValue(team1),
				getValue(team2), cof);
		chartPanel.setLocation(0, 0);
		content.add(chartPanel);
		this.add(content);

		repaint();
	}

	private double[] getValue(Teamvo t1) {
		double[] teamData = new double[attributeNames.length];
		teamData[0] = t1.getAveragePoints();
		teamData[1] = t1.getAverageAssists();
		teamData[2] = t1.getAverageRebounds();
		teamData[3] = t1.getFieldGoalsPercentage() * 100;
		teamData[4] = t1.getThreePointersPercentage() * 100;
		teamData[5] = t1.getFreeThrowsPercentage() * 100;
		teamData[6] = t1.getAverageBlockShots();
		teamData[7] = t1.getAverageTurnOver();

		return teamData;
	}

	private void init() {
		int size = attributeNames.length;
		attribute = new boolean[size];
		cof = new double[size];
		for (int i = 0; i < size; i++) {
			if (i < defauleNum) {
				attribute[i] = true;
			} else {
				attribute[i] = false;
			}
			cof[i] = 120;
		}

	}

	public void paintComponent(Graphics g2) {
		Graphics2D g = (Graphics2D) g2;

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.drawImage(
				UIUtils.resize(team1.getLogo(), width / 10, height * 4 / 20),
				0, 0, this);
		g.drawImage(
				UIUtils.resize(team2.getLogo(), width / 10, height * 4 / 20),
				width * 9 / 10, 0, this);

		g.setColor(new Color(190, 157, 83));
		g.setFont(new Font("微软雅黑", Font.BOLD, 50));
		g.drawString(team1.getAbbreviation() + "", 420, 60);

		g.setColor(new Color(218, 218, 218));
		g.drawLine(550, 20, 550, 120);

		g.setColor((new Color(68, 68, 68)));
		g.setFont(new Font("微软雅黑", Font.ROMAN_BASELINE, 25));

		g.drawString(df.format(win), width * 7 / 20, height / 20);
		g.drawString(df.format((1 - win)), width * 13 / 20, height / 20);

		g.drawString(df.format(points[0]), width * 7 / 20, height * 3 / 20);
		g.drawString(df.format(points[1]), width * 13 / 20, height * 3 / 20);

		g.setColor(new Color(190, 157, 83));
		g.setFont(new Font("微软雅黑", Font.BOLD, 50));
		g.drawString(team2.getAbbreviation() + "", width * 14 / 20, 60);
		g.setColor(new Color(218, 218, 218));
		g.drawLine(550, 20, 550, 80);

		g.setColor(new Color(218, 218, 218));
		g.drawLine(width * 14 / 20 - 10, 20, width * 14 / 20 - 10, 120);

		g2.setColor(new Color(218, 218, 218));
		g2.fillRect(0, height * 4 / 20, width * 29 / 30, height / 20);
		// 球队对比

		g2.setColor(Color.BLACK);
		g2.setFont(new Font("微软雅黑", Font.PLAIN, height / 30));
		String content = "赛季数据";
		int strWidth = g.getFontMetrics().stringWidth(content);
		int strHeight = g.getFontMetrics().getHeight();
		g2.drawString("赛季数据", width / 2 - strWidth / 2, height * 9 / 40
				+ strHeight / 4);

		content = "预测胜率";
		strWidth = g.getFontMetrics().stringWidth(content);
		strHeight = g.getFontMetrics().getHeight();
		g2.drawString(content, width / 2 - strWidth / 2, height * 1 / 20
				+ strHeight / 4 - 10);

		content = "预测比分";
		strWidth = g.getFontMetrics().stringWidth(content);
		strHeight = g.getFontMetrics().getHeight();
		g2.drawString(content, width / 2 - strWidth / 2, height * 3 / 20
				+ strHeight / 4 - 10);
	}

	public void loadImage() {

		BufferedImage bufferTable = null;
		BufferedImage bufferTableB = null;
		BufferedImage bufferTableD = null;

		try {

			bufferTable = ImageIO.read(new File("image" + File.separator
					+ "setting.png"));
			bufferTable = UIUtils.resize(bufferTable, width / 30,
					height / 20 + 1);
			bufferTableB = ImageIO.read(new File("image" + File.separator
					+ "setting_b.png"));
			bufferTableB = UIUtils
					.resize(bufferTableB, width / 30, height / 20);
			bufferTableD = ImageIO.read(new File("image" + File.separator
					+ "setting_d.png"));
			bufferTableD = UIUtils
					.resize(bufferTableD, width / 30, height / 20);

		} catch (IOException e) {
			e.printStackTrace();
		}

		settingIcon = new ImageIcon(bufferTable);
		settingIconB = new ImageIcon(bufferTableB);
		settingIconD = new ImageIcon(bufferTableD);

	}

	private void setButton() {
		setting = new JButton();
		MouseHandle mouseHandler = new MouseHandle(settingIconB, settingIcon,
				settingIconD);
		setting.setContentAreaFilled(false);
		setting.setBorderPainted(false);
		setting.setIcon(settingIcon);
		setting.addMouseListener(mouseHandler);
		setting.setBounds(width * 29 / 30, height * 4 / 20, width / 30,
				height / 20);
		this.add(setting);

	}

	private class MouseHandle extends MouseAdapter {

		ImageIcon newIcon;
		ImageIcon oldIcon;
		ImageIcon selIcon;

		public MouseHandle(ImageIcon newIm, ImageIcon oldIm, ImageIcon selectIm) {
			newIcon = newIm;
			oldIcon = oldIm;
			selIcon = selectIm;

		}

		@Override
		public void mousePressed(MouseEvent e) {
			selected = !selected;

			if (selected) {
				setting.setIcon(selIcon);

				CompareBarChartPanel chartPanel = new CompareBarChartPanel(
						width, height * 12 / 20, attribute, attributeNames,
						getValue(team1), getValue(team2), cof);
				chartPanel.setLocation(0, height * 3 / 20);
				ChoosePanel choose = new ChoosePanel(width, height * 3 / 20);
				choose.setLocation(0, 0);
				content.removeAll();

				content.add(chartPanel);
				content.add(choose);
				content.validate();

				content.updateUI();
				content.repaint();
			} else {
				setting.setIcon(oldIcon);

				CompareBarChartPanel chartPanel = new CompareBarChartPanel(
						width, height * 12 / 20, attribute, attributeNames,
						getValue(team1), getValue(team2), cof);
				chartPanel.setLocation(0, 0);

				content.removeAll();

				content.add(chartPanel);

				content.validate();
				content.updateUI();
				content.repaint();
			}

		}

		public void mouseEntered(MouseEvent e) {
			if (!selected) {
				setting.setIcon(newIcon);
			}

		}

		public void mouseExited(MouseEvent e) {
			if (!selected) {
				setting.setIcon(oldIcon);
			}

		}

		public void mouseClicked(MouseEvent e) {

		}
	}

	private class ChoosePanel extends JPanel {
		int size;
		int chooseWidth;
		int chooseHeight;

		ChoosePanel(int chooseWidth, int chooseHeight) {
			this.setLayout(null);
			this.chooseWidth = chooseWidth;
			this.chooseHeight = chooseHeight;
			size = attributeNames.length;
			for (int i = 0; i < size; i++) {
				MyCheckBox checkBox = new MyCheckBox(chooseWidth / 5,
						chooseHeight / (size / 5 + 1), attributeNames[i], i);
				checkBox.setLocation(chooseWidth / 5 * (i % 5),
						(chooseHeight / (size / 5 + 1)) * (i / 5));
				checkBox.setSelected(attribute[i]);

				ChoosePanel.this.add(checkBox);
			}
			this.setSize(chooseWidth, chooseHeight);
		}

		public void paintComponent(Graphics g) {
			g.setColor(new Color(87, 89, 91));
			g.fillRect(0, 0, chooseWidth, chooseHeight * (size / 5 + 1));
		}
	}

	private class MyCheckBox extends JLabel {
		boolean sel = false;
		int labelWidth;
		int labelHeight;
		int[] square_x;
		int[] square_y;
		int[] hook_x;
		int[] hook_y;
		int num;
		String str;

		MyCheckBox(int width, int height, String str, int num) {
			this.setSize(width, height);
			this.labelWidth = width;
			this.labelHeight = height;
			this.str = str;
			this.num = num;
			init();
			MouseHandle m = new MouseHandle();
			this.addMouseListener(m);

		}

		private void init() {
			square_x = new int[4];
			square_y = new int[4];
			square_x[0] = labelWidth * 13 / 64;
			square_y[0] = labelHeight / 3;
			square_x[1] = labelWidth / 4;
			square_y[1] = labelHeight / 3;
			square_x[2] = labelWidth / 4;
			square_y[2] = labelHeight * 2 / 3;
			square_x[3] = labelWidth * 13 / 64;
			square_y[3] = labelHeight * 2 / 3;

			hook_x = new int[3];
			hook_y = new int[3];
			hook_x[0] = labelWidth * 13 / 64;
			hook_y[0] = labelHeight / 2;
			hook_x[1] = labelWidth * 7 / 32;
			hook_y[1] = labelHeight * 2 / 3;
			hook_x[2] = labelWidth / 4;
			hook_y[2] = labelHeight / 3;
		}

		public void setSelected(boolean b) {
			sel = b;
		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			Stroke stoke = g2.getStroke();
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			BasicStroke stokeLine = new BasicStroke(3.0f);
			g2.setColor(Color.black);
			g2.drawPolygon(square_x, square_y, square_x.length);
			g2.setStroke(stokeLine);
			g2.setStroke(stoke);
			g2.setColor(Color.white);
			g2.setFont(new Font("微软雅黑", Font.PLAIN, height / 60));
			int strHeight = g2.getFontMetrics(g2.getFont()).getHeight();
			g2.drawString(str, labelWidth / 3, labelHeight * 3 / 5);

			if (sel) {
				g2.setColor(Color.white);
				g2.drawPolyline(hook_x, hook_y, hook_x.length);
			}
		}

		private class MouseHandle extends MouseAdapter {

			@Override
			public void mousePressed(MouseEvent e) {
				sel = !sel;
				attribute[num] = sel;
				PredictPanel.this.content.repaint();
			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

			public void mouseClicked(MouseEvent e) {

			}
		}

	}

	private class CompareBarChartPanel extends JPanel {
		private int width;
		private int height;

		boolean[] attr;
		String[] attr_name;
		private double[] a_value;
		private double[] b_value;
		private double[] coefficient;
		private Color[] colorArray = { new Color(158, 158, 158),
				new Color(0, 103, 178) };

		public CompareBarChartPanel(int width, int height, boolean[] attr,
				String[] attr_name, double[] a_value, double[] b_value,
				double[] coefficient) {
			super();
			this.width = width;
			this.height = height;
			this.attr = attr;
			this.attr_name = attr_name;
			this.a_value = a_value;
			this.b_value = b_value;
			this.coefficient = coefficient;
			this.setSize(width, height);
		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(new Color(246, 246, 246));
			g2.fillRect(width * 9 / 20, 0, width / 10, height);

			int half = 9 * width / 20;
			int barWidth = width * 8 / 20;
			int barHeight = height / 12;
			int barHeightSeparator = height / 40;
			for (int i = 0, j = 0; i < attr_name.length; i++) {
				if (attr[i]) {

					Color a;
					Color b;

					if (a_value[i] > b_value[i]) {
						a = colorArray[1];
						b = colorArray[0];
					} else {
						a = colorArray[0];
						b = colorArray[1];
					}
					// 左长方形
					int leftBarWidth = (int) (a_value[i] / coefficient[i] * barWidth);

					g2.setFont(new Font("微软雅黑", Font.PLAIN, height / 30));
					int strHeight = g.getFontMetrics().getHeight();
					g2.setColor(Color.black);
					g2.drawString(df.format(a_value[i]) + "", half
							- leftBarWidth - width / 20,
							(int) (barHeight * (j + 1.5) + barHeightSeparator
									* (j + 1) - strHeight / 2));

					g2.setColor(a);
					g2.fillRect(half - leftBarWidth, (int) (barHeight
							* (j + 0.5) + barHeightSeparator * (j + 1)),
							leftBarWidth, barHeight);

					// 右长方形
					int rightBarWidth = (int) (b_value[i] / coefficient[i] * barWidth);
					g2.setColor(b);
					g2.fillRect(width - half,
							(int) (barHeight * (j + 0.5) + barHeightSeparator
									* (j + 1)), rightBarWidth, barHeight);

					g2.setFont(new Font("微软雅黑", Font.PLAIN, height / 30));
					g2.setColor(Color.black);
					g2.drawString(df.format(b_value[i]) + "", width - half
							+ rightBarWidth + width / 20,
							(int) (barHeight * (j + 1.5) + barHeightSeparator
									* (j + 1) - strHeight / 2));

					// 中间属性名
					g2.setColor(Color.black);
					g2.setFont(new Font("微软雅黑", Font.PLAIN, height / 30));
					int strWidth = g.getFontMetrics().stringWidth(attr_name[i]);
					strHeight = g.getFontMetrics().getHeight();

					g2.drawString(attr_name[i], width / 2 - strWidth / 2,
							(int) (barHeight * (j + 1.5) + barHeightSeparator
									* (j + 1) - strHeight / 2));

					j++;
				}
			}

		}

	}

}
