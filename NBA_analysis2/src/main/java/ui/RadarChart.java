package ui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class RadarChart extends JPanel {
	private int dimension;// 表示维度
	private int seg;// 表示数据的段数
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

	RadarChart(int n, int m, int width, int height, double pa[], double pb[],
			double limit) {
		this.dimension = n;
		this.seg = m;
		this.setLayout(null);

		this.width = width;
		this.height = height;
		this.setSize(width,height);
		// System.out.println(this.height);
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
		System.out.println("max" + max);
		System.out.println("limit" + limit);
		for (int i = 0; i < dimension; i++) {

			double ra = pa_now[i] * max / limit;
			// System.out.println(i + " " + pa_now[i] + " " + ra);
			ax_now[i] = (int) (anchor_x + ra * Math.cos(offset * i * rad));
			ay_now[i] = (int) (anchor_y - ra * Math.sin(offset * i * rad));
			double rb = pb_now[i] * max / limit;
			// System.out.println(i + " " + pb_now[i] + " " + rb);
			bx_now[i] = (int) (anchor_x + rb * Math.cos(offset * i * rad));
			by_now[i] = (int) (anchor_y - rb * Math.sin(offset * i * rad));

		}
		// 画多边形，最后一位透明度
		g.setColor(new Color(56, 167, 229, 255));

		g.fillPolygon(ax_now, ay_now, dimension);
		g.setColor(new Color(206, 206, 206, 127));
		g.fillPolygon(bx_now, by_now, dimension);
		// 画多边形边界
		g.setColor(Color.black);
		g.drawPolygon(ax_now, ay_now, dimension);
		g.drawPolygon(bx_now, by_now, dimension);
		// 画坐标系
		g.setColor(Color.black);
		for (int k = 0; k <= seg; k++) {
			double r = k * separator;

			for (int i = 0; i < dimension; i++) {

				int x1 = (int) (anchor_x + r * Math.cos(offset * i * rad));
				int y1 = (int) (anchor_y - r * Math.sin(offset * i * rad));
				int x2 = (int) (anchor_x + r * Math.cos(offset * (i + 1) * rad));
				int y2 = (int) (anchor_y - r * Math.sin(offset * (i + 1) * rad));

				int x3 = (int) (anchor_x + (max + 15)
						* Math.cos(offset * i * rad));
				int y3 = (int) (anchor_y - (max + 15)
						* Math.sin(offset * i * rad));
				g.drawString(i + 1 + "", x3, y3);

				g.drawLine(x1, y1, x2, y2);

			}
		}
		for (int i = 0; i < dimension; i++) {
			int x4 = (int) (anchor_x + max * Math.cos(offset * i * rad));
			int y4 = (int) (anchor_y + max * Math.sin(offset * i * rad));
			g.setColor(Color.black);

			g.drawLine(anchor_x, anchor_y, x4, y4);

		}

		// 画坐标系上的值
		// System.out.println("separator:"+separator);
		for (int k = 0; k <= seg; k++) {
			g.drawString((int) (k * limit / seg) + "", anchor_x,
					(int) (anchor_y - k * separator));
		}

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
		double[] pa = { 2, 4, 6, 8};
		double[] pb = { 1, 3, 6, 1};
		double limit = 10;
		RadarChart chart = new RadarChart(4, 5, 1280, 1080, pa, pb, limit);
		chart.setBounds(0, 0, 1280, 1080);
		f.setLayout(null);
		f.add(chart);
		f.setVisible(true);
		chart.go();
		f.repaint();
	}

}
