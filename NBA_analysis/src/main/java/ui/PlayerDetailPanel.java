package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicLabelUI;

import compare.PalyerScreening;

import logic.players.Player;

public class PlayerDetailPanel extends JPanel {
	private static String FONT_OF_DETAIL = "微软雅黑";
	private Player player;
	private int width;
	private int height;
	private int[] barPositonx;
	private int[] barPositony;
	private int[] barWidth;
	private double[] barHeight;
	private int[] now;
	private int[] limits;
	private int barsize = 10;
	private int threadDelay = 10;
	
	private JButton data;
	private JButton statistics;
	private JButton contrast;

	private JLabel head;
	private JLabel points;
	private JLabel rebounds;
	private JLabel assist;
	private JLabel freeThrow;
	private JLabel threePointers;

	PlayerDetailPanel(Player player, int width, int height) {
		this.setLayout(null);
		this.player = player;
		this.width = width;
		this.height = height;
		
		setLabel();
		setButton();
		setBarCharts();

	}

	private void setButton() {
		data = new BarChartButton();
		data.setText("资料");
		data.setBounds(0, 0, width/3, height/6);
		this.add(data);
		
		statistics = new BarChartButton();
		statistics.setText("数据");
		statistics.setBounds(width/3, 0, width/3, height/6);
		this.add(statistics);
		
		contrast = new BarChartButton();
		contrast.setText("对比");
		contrast.setBounds(width*2/3, 0, width/3, height/6);
		this.add(contrast);
		
	}
	private void setLabel() {

		head = new BarChartLabel();
		head.setText("球员/均值");
		head.setBounds(0, height * 67 / 96, width * 1 / 5,
				height * 1 / 6);
		this.add(head);

		points = new BarChartLabel();
		points.setText("平均得分");
		points.setBounds(width * 1 / 5, height * 67 / 96, width * 3 / 20,
				height * 1 / 6);
		this.add(points);

		rebounds = new BarChartLabel();
		rebounds.setText("平均篮板");
		rebounds.setBounds(width * 7 / 20, height * 67 / 96, width * 3 / 20,
				height * 1 / 6);
		this.add(rebounds);

		assist = new BarChartLabel();
		assist.setText("平均助攻");
		assist.setBounds(width * 10 / 20, height * 67 / 96, width * 3 / 20,
				height * 1 / 6);
		this.add(assist);

		freeThrow = new BarChartLabel();
		freeThrow.setText("罚球 %");
		freeThrow.setBounds(width * 13 / 20, height * 67 / 96, width * 3 / 20,
				height * 1 / 6);
		this.add(freeThrow);

		threePointers = new BarChartLabel("special");
		threePointers.setText("三分 %");
		threePointers.setBounds(width * 16 / 20, height * 67 / 96,
				width * 4 / 20, height * 1 / 6);
		this.add(threePointers);

	}

	// 暂时硬编码，没有球员信息
	private void setBarCharts() {

		barPositonx = new int[barsize];
		barPositony = new int[barsize];
		barWidth = new int[barsize];
		barHeight = new double[barsize];
		limits = new int[barsize];
		now = new int[barsize];
		for (int i = 0; i < barsize; i++) {
			if (i % 2 == 0) {
				barPositonx[i] = width * 1 / 4 + (3 * i) * width / 40 - width
						/ 100;
			} else {
				barPositonx[i] = barPositonx[i - 1] + width / 25;
			}

			barPositony[i] = height * 67 / 96;
			barHeight[i] = 0;
			barWidth[i] = width / 32;
			now[i] = 0;
			limits[i] = 30 * (i + 1);// 这个属性硬编码
		}

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
			g.fillRect(barPositonx[i], barPositony[i] - (int)barHeight[i],
					barWidth[i], (int)barHeight[i]);
			g.setColor(Color.black);
			g.setFont(new Font("default", Font.ROMAN_BASELINE, 15));
			g.drawString(Integer.toString(now[i]), barPositonx[i]+5,
					barPositony[i] - (int)barHeight[i] - 10);
		}

	}

	private class BarChartThread implements Runnable {

		@Override
		public void run() {
			while (!drawComplete()) {
				for (int i = 0; i < barsize; i++) {
					if (now[i] < limits[i]) {
						barHeight[i]+=height*1.0/1280.0;
						now[i]++;
					}

				}
				repaint();

				try {
					Thread.sleep(threadDelay);
				} catch (InterruptedException ex) {
				}
			}

		}

	}

	private boolean drawComplete() {
		boolean flag = true;
		for (int i = 0; i < barsize; i++) {
			if (now[i] < limits[i]) {
				flag = false;
				break;
			}
		}
		return flag;
	}

	

	private class BarChartLabel extends JLabel {
		public BarChartLabel() {
			this.setForeground(Color.black);
			this.setOpaque(true);
			this.setFont(new Font(FONT_OF_DETAIL, Font.CENTER_BASELINE, 60*height/1280));
			this.setBackground(new Color(246, 246, 246));
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setUI(new LabelUI());
		}
		public BarChartLabel(String kind){
			this();
			if(kind.equals("special")){
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
	
	private class BarChartButton extends JButton{
		public BarChartButton() {
			super();
			
			this.setHorizontalAlignment(SwingConstants.CENTER);
			this.setForeground(Color.white);
			this.setBackground(new Color(87, 89, 91));
			this.setBorder(BorderFactory.createLineBorder(new Color(122, 122, 122),
					2));
			this.setFocusPainted(false);

			this.setFont(new Font(FONT_OF_DETAIL, Font.CENTER_BASELINE, 80*height/1280));
		
			this.addMouseListener(new MouseAdapter() {
				public void mouseEntered(MouseEvent e) {
					//BarChartButton.this.setContentAreaFilled(true);
					BarChartButton.this.setBackground(new Color(69, 69, 69));
				}

				public void mouseExited(MouseEvent e) {
					//BarChartButton.this.setContentAreaFilled(false);
					BarChartButton.this.setBackground(new Color(87, 89, 91));
				}
				//not finished
				public void mousePressed(MouseEvent e) {
					System.out.println("点击");
				}
			});
		}
	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(0, 0, 1280, 300);
		// f.setBackground(Color.white);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		PlayerDetailPanel p = new PlayerDetailPanel(null, 1280, 300);
		f.getContentPane().setLayout(null);
		f.setContentPane(p);
		//f.repaint();
		f.setVisible(true);
		p.go();
	}
}
