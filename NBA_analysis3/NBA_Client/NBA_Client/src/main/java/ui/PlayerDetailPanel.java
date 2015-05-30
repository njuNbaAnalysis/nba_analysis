package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicLabelUI;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;

import compare.PalyerScreening;
import logic.BLController;
import logic.BLService;
import logic.players.Player;

public class PlayerDetailPanel extends JPanel {
	private static String FONT_OF_DETAIL = "微软雅黑";
	private Player player;
	private int width;
	private int height;
	private int[] barPositonx;
	private int[] barPositony;
	private int[] barWidth;
	private double[] barHeight;// 当前高度
	private double[] barNum;// 当前数据
	private double[] numLimits;// 数据限制
	private double[] numInc;// 每次的数据增幅
	private double[] heightLimits;// 高度限制
	private double[] allianceAverage;
	private double[] playerData;
	private int barsize = 10;
	private int threadDelay = 25;
	protected DecimalFormat df = new DecimalFormat("#0.0");

	private JLabel head;
	private JLabel points;
	private JLabel rebounds;
	private JLabel assist;
	private JLabel freeThrow;
	private JLabel threePointers;

	PlayerDetailPanel(Player player, double[] allianceAverage, int width,
			int height) {
		this.setLayout(null);
		this.player = player;
		this.width = width;
		this.height = height;
		this.allianceAverage = allianceAverage;
		playerData = new double[5];
		playerData[0] = Double
				.parseDouble(df.format(player.getAveragePoints()));
		playerData[1] = Double.parseDouble(df.format(player
				.getAverageRebounds()));
		playerData[2] = Double
				.parseDouble(df.format(player.getAverageAssists()));
		playerData[3] = Double.parseDouble(df.format(player.getFreeThrowsPercentage() * 100));
		playerData[4] = Double.parseDouble(df.format(player.getThreePointersPercentage()* 100));

		this.allianceAverage = allianceAverage;
		allianceAverage[0] = Double.parseDouble(df.format(allianceAverage[0]));
		allianceAverage[1] = Double.parseDouble(df.format(allianceAverage[1]));
		allianceAverage[2] = Double.parseDouble(df.format(allianceAverage[2]));
		allianceAverage[3] = Double.parseDouble(df.format(allianceAverage[3] * 100));
		allianceAverage[4] = Double.parseDouble(df.format(allianceAverage[4] * 100));

		setLabel();
		setBarCharts();

	}

	private void setLabel() {

		head = new BarChartLabel();
		head.setText("球员/均值");
		head.setBounds(0, height * 80 / 96, width * 1 / 5, height * 1 / 6);
		this.add(head);

		points = new BarChartLabel();
		points.setText("平均得分");
		points.setBounds(width * 1 / 5, height * 80 / 96, width * 3 / 20,
				height * 1 / 6);
		this.add(points);

		rebounds = new BarChartLabel();
		rebounds.setText("平均篮板");
		rebounds.setBounds(width * 7 / 20, height * 80 / 96, width * 3 / 20,
				height * 1 / 6);
		this.add(rebounds);

		assist = new BarChartLabel();
		assist.setText("平均助攻");
		assist.setBounds(width * 10 / 20, height * 80 / 96, width * 3 / 20,
				height * 1 / 6);
		this.add(assist);

		freeThrow = new BarChartLabel();
		freeThrow.setText("罚球 %");
		freeThrow.setBounds(width * 13 / 20, height * 80 / 96, width * 3 / 20,
				height * 1 / 6);
		this.add(freeThrow);

		threePointers = new BarChartLabel("special");
		threePointers.setText("三分 %");
		threePointers.setBounds(width * 16 / 20, height * 80 / 96,
				width * 4 / 20, height * 1 / 6);
		this.add(threePointers);

	}

	// 暂时硬编码，没有球员信息
	private void setBarCharts() {

		barPositonx = new int[barsize];
		barPositony = new int[barsize];
		barWidth = new int[barsize];
		barHeight = new double[barsize];
		numLimits = new double[barsize];
		heightLimits = new double[barsize];
		barNum = new double[barsize];
		numInc = new double[barsize];
		for (int i = 0; i < barsize; i++) {
			if (i % 2 == 0) {
				barPositonx[i] = width * 1 / 4 + (3 * i) * width / 40 - width
						/ 100;
				numLimits[i] = playerData[i / 2];
			} else {
				barPositonx[i] = barPositonx[i - 1] + width / 25;
				numLimits[i] = allianceAverage[i / 2];
			}

			barPositony[i] = height * 80 / 96;
			barHeight[i] = 0;
			barWidth[i] = width / 64;
			barNum[i] = 0;

		}
		numInc[0] = 0.5;
		numInc[1] = 0.5;
		numInc[2] = 0.3;
		numInc[3] = 0.3;
		numInc[4] = 0.3;
		numInc[5] = 0.3;
		numInc[6] = 1;
		numInc[7] = 1;
		numInc[8] = 1;
		numInc[9] = 1;

		heightLimits[0] = playerData[0] / 40.0 * height / 3;
		heightLimits[1] = allianceAverage[0] / 40.0 * height / 3;

		heightLimits[2] = playerData[1] / 25.0 * height / 3;
		heightLimits[3] = allianceAverage[1] / 25.0 * height / 3;

		heightLimits[4] = playerData[2] / 20.0 * height / 3;
		heightLimits[5] = allianceAverage[2] / 20.0 * height / 3;

		heightLimits[6] = playerData[3] / 40.0 * height / 5;
		heightLimits[7] = allianceAverage[3] / 40.0 * height / 5;
		heightLimits[8] = playerData[4] / 40.0 * height / 5;
		heightLimits[9] = allianceAverage[4] / 40.0 * height / 5;

	}

	// 务必调用这个方法启动动画
	public void go() {
		Thread s = new Thread(new BarChartThread());
		s.start();

	}

	public void paintComponent(Graphics g2) {

		Graphics2D g = (Graphics2D) g2.create();

		g.setColor(this.getBackground());
		g.fillRect(0, 0, this.getWidth(), this.getHeight());

		for (int i = 0; i < barsize; i++) {
			if (i % 2 == 0) {
				g.setColor(new Color(56, 167, 229));
			} else {
				g.setColor(new Color(206, 206, 206));
			}

			g.fillRect(barPositonx[i], barPositony[i] - (int) barHeight[i],
					barWidth[i], (int) barHeight[i]);
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.ROMAN_BASELINE, 15));

			g.drawString(Double.toString(barNum[i]), barPositonx[i] + 5,
					barPositony[i] - (int) barHeight[i] - 10);
		}

		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(new Color(69, 69, 69));
		g.fillRect(0, 0, width, height / 6);
		g.setColor(Color.WHITE);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("联盟对比", width / 20, height / 10);

		g.setColor(new Color(56, 167, 229));
		g.fillRect(width / 15, height * 2 / 5, width / 100, width / 100);
		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("球员", width / 15 + 20, height * 17 / 40);

		g.setColor(new Color(206, 206, 206));
		g.fillRect(width / 15, height / 2, width / 100, width / 100);
		g.setColor(Color.BLACK);
		g.setFont(new Font("default", Font.BOLD, 20));
		g.drawString("联盟平均值", width / 15 + 20, height * 21 / 40);
	}

	private class BarChartThread implements Runnable {

		@Override
		public void run() {
			while (!drawComplete()) {
				for (int i = 0; i < barsize; i++) {
					if (barNum[i] < numLimits[i]) {

						barHeight[i] += heightLimits[i] / numLimits[i];

						barNum[i] += numInc[i];
						barNum[i] = Double.parseDouble(df.format(barNum[i]));

					}

				}
				repaint();

				try {
					Thread.sleep(threadDelay);
				} catch (InterruptedException ex) {
				}
			}

			// 修正属性
			for (int i = 0; i < barsize; i++) {
				barNum[i] = numLimits[i];
			}

			repaint();

		}

	}

	private boolean drawComplete() {

		boolean flag = true;
		for (int i = 0; i < barsize; i++) {
			if (barNum[i] < numLimits[i]) {
				flag = false;
				return flag;
			}
		}

		return flag;
	}

	private class BarChartLabel extends JLabel {
		public BarChartLabel() {
			this.setForeground(Color.black);
			this.setOpaque(true);
			this.setFont(new Font(FONT_OF_DETAIL, Font.CENTER_BASELINE,
					60 * height / 1280));
			this.setBackground(new Color(246, 246, 246));
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setUI(new LabelUI());
		}

		public BarChartLabel(String kind) {
			this();
			if (kind.equals("special")) {
				this.setUI(new LabelUI());
			}
		}

		private class LabelUI extends BasicLabelUI {

			@Override
			public void paint(Graphics g, JComponent c) {
				// TODO Auto-generated method stub

				int y = (int) c.getSize().getHeight();
				int x = (int) c.getSize().getWidth();

				g.setColor(new Color(190, 157, 83));
				g.drawLine(0, y - 1, x - 1, y - 1);

				g.setColor(new Color(206, 206, 206));
				g.drawLine(x - 1, 0, x - 1, y - 1);
				super.paint(g, c);
			}
		}

		private class SpecialLabelUI extends BasicLabelUI {

			@Override
			public void paint(Graphics g, JComponent c) {
				// TODO Auto-generated method stub

				int y = (int) c.getSize().getHeight();
				int x = (int) c.getSize().getWidth();

				g.setColor(new Color(190, 157, 83));
				g.drawLine(0, y - 1, x - 1, y - 1);

				super.paint(g, c);
			}
		}
	}

	private class BarChartButton extends JButton {
		public BarChartButton() {
			super();

			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setForeground(Color.white);
			this.setBackground(new Color(87, 89, 91));
			this.setBorder(BorderFactory.createLineBorder(new Color(122, 122,
					122), 2));
			this.setFocusPainted(false);

			this.setFont(new Font(FONT_OF_DETAIL, Font.CENTER_BASELINE,
					80 * height / 1280));

			this.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					// BarChartButton.this.setContentAreaFilled(true);
					BarChartButton.this.setBackground(new Color(69, 69, 69));
				}

				public void mouseExited(MouseEvent e) {
					// BarChartButton.this.setContentAreaFilled(false);
					BarChartButton.this.setBackground(new Color(87, 89, 91));
				}

				// not finished
				public void mousePressed(MouseEvent e) {
					System.out.println("点击");
				}
			});
		}
	}

	public static void main(String[] args) {
		BLService bl = BLController.getInstance();
		bl.init();
		while (bl.getProgress() < 9) {
			System.out.println(bl.getProgress());
		}
		JFrame f = new JFrame();
		f.setBounds(0, 0, 1280, 300);
		// f.setBackground(Color.white);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlayerDetailPanel p = new PlayerDetailPanel(bl.getAllPlayers().get(0),
				bl.getAllianceAverageData(), 1280, 300);
		f.getContentPane().setLayout(null);
		f.setContentPane(p);
		// f.repaint();
		f.setVisible(true);
		p.go();
	}
}
