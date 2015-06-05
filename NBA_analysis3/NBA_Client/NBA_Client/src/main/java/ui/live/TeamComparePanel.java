package ui.live;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import util.UIUtils;
import vo.Matchvo;
import vo.Teamvo;

public class TeamComparePanel extends JPanel {
	private boolean[] attribute;
	private String[] attributeNames;
	private double[] cof;
	private int width;
	private int height;
	private int defauleNum = 8;// 默认显示的选项数量

	private JButton setting;
	ImageIcon settingIcon;
	ImageIcon settingIconD;
	ImageIcon settingIconB;
	boolean selected = false;
	private JScrollPane content;
	private Matchvo match;

	TeamComparePanel(int width, int height, String[] attributeNames,
			Matchvo match) {
		this.width = width;
		this.height = height;
		this.attributeNames = attributeNames;
		this.match = match;
		this.setLayout(null);
		this.setSize(width, height);
		init();
		loadImage();
		setButton();
		content = new JScrollPane();
		content.setBounds(0, height / 20, width, height * 19 / 20);

		content.setLayout(null);

		CompareBarChartPanel chartPanel = new CompareBarChartPanel(width,
				height * 17 / 20, attribute, attributeNames,
				getTeamValue(match), getRivalValue(match), cof);
		chartPanel.setLocation(0, 0);
		content.add(chartPanel);
		this.add(content);
		repaint();
	}

	
	private double[] getTeamValue(Matchvo m) {
		double[] teamData = new double[attributeNames.length];

		teamData[0] = m.getFieldGoalsPercentage()[0];
		teamData[1] = m.getThreePointersPercentage()[0];
		teamData[2] = m.getFreeThrowsPercentage()[0];
		//System.out.println("Rebound0:"+m.getRebounds()[0]);
		teamData[3] = m.getRebounds()[0];
		teamData[4] = m.getAssists()[0];
		teamData[5] = m.getBlocks()[0];
		teamData[6] = m.getTurnOver()[0];
		teamData[7] = m.getQuickPoints()[0];
		teamData[8] = m.getRestrictedPoints()[0];
		teamData[9] = m.getTurnOverPoints()[0];
		teamData[10] = m.getMaxPoints()[0];

		return teamData;
	}

	private double[] getRivalValue(Matchvo m) {
		double[] teamData = new double[attributeNames.length];
		teamData[0] = m.getFieldGoalsPercentage()[1];
		teamData[1] = m.getThreePointersPercentage()[1];
		teamData[2] = m.getFreeThrowsPercentage()[1];
		//System.out.println("Rebound1:"+m.getRebounds()[1]);
		teamData[3] = m.getRebounds()[1];
		teamData[4] = m.getAssists()[1];
		teamData[5] = m.getBlocks()[1];
		teamData[6] = m.getTurnOver()[1];
		teamData[7] = m.getQuickPoints()[1];
		teamData[8] = m.getRestrictedPoints()[1];
		teamData[9] = m.getTurnOverPoints()[1];
		teamData[10] = m.getMaxPoints()[1];
		

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
			cof[i] = 100;
		}
		
		
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D) g;
		g2.setColor(getBackground());
		g2.fillRect(0, 0, width, height);
		g2.setColor(new Color(218, 218, 218));
		g2.fillRect(0, 0, width * 29 / 30, height / 20);
		// 球队对比
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("微软雅黑", Font.PLAIN, height / 30));
		String content = "球队对比";
		int strWidth = g.getFontMetrics().stringWidth(content);
		int strHeight = g.getFontMetrics().getHeight();
		g2.drawString("球队对比", width / 2 - strWidth / 2, height / 40 + strHeight
				/ 4);
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
		setting.setBounds(width * 29 / 30, 0, width / 30, height / 20);
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
						width, height * 17 / 20, attribute, attributeNames,
						getTeamValue(match), getRivalValue(match), cof);
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
						width, height * 17 / 20, attribute, attributeNames,
						getTeamValue(match), getRivalValue(match), cof);
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
				TeamComparePanel.this.repaint();
			}

			public void mouseEntered(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

			public void mouseClicked(MouseEvent e) {

			}
		}

	}

	private class RadioButtonPanel {
		RadioButtonPanel() {

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
			int barHeight = height / 16;
			int barHeightSeparator = height / 40;
			for (int i = 0, j = 0; i < attr_name.length; i++) {
				if (attr[i]) {

					Color a;
					Color b;

					if (a_value[i] > b_value[i]) {
						a = colorArray[0];
						b = colorArray[1];
					} else {
						a = colorArray[1];
						b = colorArray[0];
					}
					// 左长方形
					int leftBarWidth = (int) (a_value[i] / coefficient[i] * barWidth);

					g2.setFont(new Font("微软雅黑", Font.PLAIN, height / 30));
					int strHeight = g.getFontMetrics().getHeight();
					g2.setColor(Color.black);
					g2.drawString(a_value[i] + "", half - leftBarWidth - width
							/ 20, (int) (barHeight * (j + 1.5)
							+ barHeightSeparator * (j + 1) - strHeight / 2));

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
					g2.drawString(b_value[i] + "", width - half + rightBarWidth
							+ width / 20, (int) (barHeight * (j + 1.5)
							+ barHeightSeparator * (j + 1) - strHeight / 2));

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
