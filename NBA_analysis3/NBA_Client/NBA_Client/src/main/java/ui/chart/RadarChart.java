package ui.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RadarChart extends JPanel {
	private int dimension;// 表示维度
	private int seg;// 表示数据的段数
	private String[] attr;// 属性名
	private int width;
	private int height;

	private double limit;// 雷达区的极限值
	private double pa_limit[];// 雷达区A的极限值
	private double pb_limit[];// 雷达区B的极限值

	private double pa_now[];// 雷达区A的当前值
	private double pb_now[];// 雷达区B的当前值

	private double parameter[];// 步长
	private int threadDelay;

	// private

	public RadarChart(int n, int m, int width, int height, double pa[], double pb[],
			double limit, String[] attr) {
		this.dimension = n;
		this.seg = m;
		this.attr = attr;
		this.setLayout(null);

		this.width = width;
		this.height = height;
		this.setSize(width, height);
		threadDelay = 5;
		this.pa_limit = pa;
		this.pb_limit = pb;
		this.limit = limit;
		init();

	}

	private void init() {
		pa_now = new double[dimension];
		pb_now = new double[dimension];

		parameter = new double[dimension];
		for (int i = 0; i < dimension; i++) {
			pa_now[i] = 0;
			pb_now[i] = 0;

			parameter[i] = limit * 1.0 / (3 * height / 8);
		}
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(new Color(158, 158, 158));
		g2.fillRect(0, 0, width, height);
		double offset = 360 / dimension;
		double rad = Math.PI / 180;
		int anchor_x = width / 2;
		int anchor_y = height / 2;
		double separator = (3 * height / 8) * 1.0 / seg;// 每条横线之间的间隔
		double max = seg * separator;// 单个坐标的极限

		int ax_now[] = new int[dimension];// 雷达区A的横坐标
		int ay_now[] = new int[dimension];// 雷达区A的纵坐标
		int bx_now[] = new int[dimension];// 雷达区B的横坐标
		int by_now[] = new int[dimension];// 雷达区B的纵坐标
		// 设置多边形坐标

		for (int i = 0; i < dimension; i++) {

			double ra = pa_now[i] * max / limit;

			ax_now[i] = (int) (anchor_x + ra
					* Math.cos(offset * i * rad + Math.PI / 2));
			ay_now[i] = (int) (anchor_y - ra
					* Math.sin(offset * i * rad + Math.PI / 2));
			double rb = pb_now[i] * max / limit;

			bx_now[i] = (int) (anchor_x + rb
					* Math.cos(offset * i * rad + Math.PI / 2));
			by_now[i] = (int) (anchor_y - rb
					* Math.sin(offset * i * rad + Math.PI / 2));

		}
		// 画多边形，最后一位透明度
		
		g2.setColor(new Color(221, 61, 66, 255));

		g2.fillPolygon(ax_now, ay_now, dimension);
		g2.setColor(new Color(6, 74, 150, 127));
		g2.fillPolygon(bx_now, by_now, dimension);
		// 画多边形边界

		g2.setColor(Color.white);
		Stroke stroke = g2.getStroke();
		Stroke lineStroke = new BasicStroke(1.5f);
		g2.setStroke(lineStroke);
		g2.drawPolygon(ax_now, ay_now, dimension);
		g2.drawPolygon(bx_now, by_now, dimension);
		// 画坐标系
		g2.setColor(Color.white);

		for (int k = 0; k <= seg; k++) {
			double r = k * separator;

			for (int i = 0; i < dimension; i++) {

				int x1 = (int) (anchor_x + r
						* Math.cos(offset * i * rad + Math.PI / 2));
				int y1 = (int) (anchor_y - r
						* Math.sin(offset * i * rad + Math.PI / 2));
				int x2 = (int) (anchor_x + r
						* Math.cos(offset * (i + 1) * rad + Math.PI / 2));
				int y2 = (int) (anchor_y - r
						* Math.sin(offset * (i + 1) * rad + Math.PI / 2));

				g2.drawLine(x1, y1, x2, y2);

			}
		}
		for (int i = 0; i < dimension; i++) {
			int x4 = (int) (anchor_x + max
					* Math.cos(offset * i * rad + Math.PI / 2));
			int y4 = (int) (anchor_y - max
					* Math.sin(offset * i * rad + Math.PI / 2));

			g2.drawLine(anchor_x, anchor_y, x4, y4);
			g2.setFont(new Font("微软雅黑", Font.PLAIN, 20));
			int x3 = (int) (anchor_x + (max + 15)
					* Math.cos(offset * i * rad + Math.PI / 2)) - 20;
			int y3 = (int) (anchor_y - (max + 15)
					* Math.sin(offset * i * rad + Math.PI / 2));
			g2.drawString(attr[i] + "", x3, y3);

		}

		// 画坐标系上的值
		// System.out.println("separator:"+separator);
		for (int k = 0; k <= seg; k++) {
			g2.drawString((int) (k * limit / seg) + "", anchor_x,
					(int) (anchor_y - k * separator));
		}
		g2.setStroke(stroke);

	}

	private class RadarChartThread implements Runnable {
		@Override
		public void run() {
			while (!drawComplete()) {
				for (int i = 0; i < dimension; i++) {
					if (pa_now[i] < pa_limit[i]) {

						pa_now[i] += parameter[i];

						// 修正
						if (pa_now[i] > pa_limit[i]) {
							pa_now[i] = pa_limit[i];
						}

					}
					if (pb_now[i] < pb_limit[i]) {
						pb_now[i] += parameter[i];

						// 修正
						if (pb_now[i] > pb_limit[i]) {
							pb_now[i] = pb_limit[i];
						}
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
		for (int i = 0; i < dimension; i++) {
			if (pa_now[i] < pa_limit[i] || pb_now[i] < pb_limit[i]) {
				flag = false;
				return flag;
			}
		}

		return flag;
	}

	// 务必调用这个方法启动动画
	public void go() {
		Thread s = new Thread(new RadarChartThread());
		s.start();

	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(0, 0, 1280, 1080);
		double[] pa = { 2, 4, 2, 4, 8 };
		double[] pb = { 1, 3, 6, 1, 2 };
		double limit = 10;
		String[] attr = { "内线", "外线", "配合", "进攻", "得分" };
		RadarChart chart = new RadarChart(5, 5, 1280, 1080, pa, pb, limit, attr);
		chart.setBounds(0, 0, 1280, 1080);
		f.setLayout(null);
		f.add(chart);
		f.setVisible(true);
		chart.go();
		f.repaint();
	}

}
