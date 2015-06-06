package ui.chart;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class LineChart extends JPanel {
	private int num;// 表示几个点
	private int seg;// 表示纵坐标几个隔断
	private double limit;// 表示纵坐标极限
	private int width;
	private int height;
	private String[] x_name;
	private String[] y_name;
	private double[] a_value;
	private double[] b_value;

	private double[] x_array_limit;
	private double[] ay_array_limit;
	private double[] x_array_now;
	private double[] ay_array_now;

	private double[] by_array_limit;
	private double[] by_array_now;

	private String[] labelContent_a;
	private String[] labelContent_b;
	
	private Color a_color;
	private Color b_color;

	private int finished = 1;// 表示已经画完的点数
	private int threadDelay = 5;

	private int block_now = -1;// 当前横坐标在的块
	private int y_now = -1;// 当前纵坐标的值

	private DecimalFormat df = new DecimalFormat("#0.0");

	LineChart(int num, int seg, int width, int height, double limit,
			String[] x_name, String [] y_name,double[] a_value, double[] b_value,String [] labelContent_a,String [] labelContent_b,Color a_color,Color b_color) {
		this.num = num;
		this.width = width;
		this.height = height;
		this.seg = seg;
		this.limit = limit;
		this.x_name = x_name;
		this.y_name = y_name;
		this.a_value = a_value;
		this.b_value = b_value;
		this.labelContent_a = labelContent_a;
		this.labelContent_b = labelContent_b;
		this.a_color = a_color;
		this.b_color = b_color;
		this.setLayout(null);
		init();
		LineChartListener l = new LineChartListener();
		this.addMouseListener(l);
		this.addMouseMotionListener(l);
	}

	private void init() {
		// 设置点的坐标
		double chartheight = height * 18 / 20;
		double chartwidth = width * 18 / 20;
		double x_separator = chartwidth / num;
		x_array_limit = new double[num];
		ay_array_limit = new double[num];
		x_array_now = new double[num];
		ay_array_now = new double[num];

		by_array_limit = new double[num];

		by_array_now = new double[num];
		for (int i = 0; i < num; i++) {
			x_array_limit[i] = (int) x_separator * (i + 1) + width / 20;
			ay_array_limit[i] = (int) ((height * 1 / 20 + chartheight) - (chartheight
					* 1.0 / limit * a_value[i]));
			by_array_limit[i] = (int) ((height * 1 / 20 + chartheight) - (chartheight
					* 1.0 / limit * b_value[i]));
			if (i == 0) {
				x_array_now[i] = x_array_limit[i];
				ay_array_now[i] = ay_array_limit[i];
				by_array_now[i] = by_array_limit[i];
			} else {
				x_array_now[i] = x_array_limit[i - 1];
				ay_array_now[i] = ay_array_limit[i - 1];

				by_array_now[i] = by_array_limit[i - 1];
			}
			// System.out.println(i+" start"+ax_array_now[i]+" "+ay_array_now[i]);
		}
		if(y_name==null){
			y_name = new String [seg+1];
			for(int i=0;i<=seg;i++){
				y_name[i] = i * limit / seg + "";
			}
		}
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2 = (Graphics2D) g;
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g2.setColor(this.getBackground());

		g2.fillRect(0, 0, 2000, 1500);// 预设一个比较大的值

		g2.setColor(new Color(83, 83, 100));
		g2.fillRect(0, 0, width, height);// 预设一个比较大的值
		double chartheight = height * 18 / 20;
		double chartwidth = width * 18 / 20;
		double y_separator = chartheight * 1.0 / seg;
		double x_separator = chartwidth / num;
		// 画坐标系，坐标系左右有width/20的空隙，上下有height/20的空隙
		Stroke stroke = g2.getStroke();
		for (int i = 0; i <= seg; i++) {
			if (i != 0) {
				// 设置虚线
				Stroke dash = new BasicStroke(1.5f, BasicStroke.CAP_BUTT,
						BasicStroke.JOIN_ROUND, 3.5f, new float[] { 15, 10 },
						0f);
				g2.setStroke(dash);
				g2.setColor(new Color(206, 206, 206, 127));
			} else {
				g2.setColor(Color.black);
			}

			g2.drawLine(width / 20, (int) (height * 19 / 20 - i * y_separator),
					width * 19 / 20, (int) (height * 19 / 20 - i * y_separator));
			g2.setStroke(stroke);
			g2.setColor(Color.black);
			g2.drawString(y_name[i], width / 40,
					(int) (height * 19 / 20 - i * y_separator));
		}
		g2.setStroke(stroke);
		g2.setColor(Color.black);

		g2.drawLine(width / 20, height * 19 / 20, width / 20, height * 1 / 20);

		// 画横坐标
		for (int i = 0; i < num; i++) {

			g2.drawLine((int) x_separator * (i + 1) + width / 20,
					(int) (height * 19 / 20) - 5, (int) x_separator * (i + 1)
							+ width / 20, (int) (height * 19 / 20));
			g2.drawString(x_name[i], (int) x_separator * (i + 1) + width / 20,
					height * 39 / 40);
		}

		// 画直线
		int[] x_temp = new int[num];
		int[] ay_temp = new int[num];

		int[] by_temp = new int[num];
		for (int i = 0; i < num; i++) {
			x_temp[i] = (int) x_array_now[i];
			ay_temp[i] = (int) ay_array_now[i];
			by_temp[i] = (int) by_array_now[i];
		}
		
		
		Stroke blod = new BasicStroke(2.0f);
		g2.setStroke(blod);
		
		synchronized (this) {
			if (finished < num) {
				g2.setColor(a_color);
				
				g2.drawPolyline(x_temp, ay_temp, finished + 1);
				g2.setColor(b_color);
				
				g2.drawPolyline(x_temp, by_temp, finished + 1);
			} else {
				g2.setColor(a_color);
				g2.drawPolyline(x_temp, ay_temp, finished);
				g2.setColor(b_color);
				g2.drawPolyline(x_temp, by_temp, finished);
			}
		}
		g2.setStroke(stroke);

		// 画鼠标监听的横线以及数字框
		if (y_now > height / 20 && y_now < (chartheight + height / 20)) {

			g2.setColor(Color.gray);
			g2.drawLine(width / 20, y_now, width * 19 / 20, y_now);

			g2.setColor(Color.white);
			g2.fillRect(width / 40, y_now - 25, width / 40 - 1, 50);

			g2.setColor(Color.black);

			double val = ((1.0 - (y_now - height / 20) * 1.0 / chartheight) * limit);

			g2.drawString(df.format(val), width / 40, y_now);
		}

		// 画鼠标监听的框
		if (block_now >= 0) {
			g2.setColor(new Color(206, 206, 206, 50));
			// System.out.println("花模块");
			g2.fillRect(
					(int) (x_array_limit[block_now] - x_separator * 9 / 20),
					height / 20, (int) x_separator, (int) chartheight);
		}

	}

	private class DrawLineThread implements Runnable {

		@Override
		public void run() {
			while (finished < num) {
				double k1 = (ay_array_limit[finished] - ay_array_limit[finished - 1])
						* 1.0
						/ (x_array_limit[finished] - x_array_limit[finished - 1]);// 斜率k1
				double k2 = (by_array_limit[finished] - by_array_limit[finished - 1])
						* 1.0
						/ (x_array_limit[finished] - x_array_limit[finished - 1]);// 斜率k2
				if (x_array_now[finished] < x_array_limit[finished]) {

					x_array_now[finished] += 2;
					ay_array_now[finished] += k1 * 2;
					by_array_now[finished] += k2 * 2;
				}

				// 修正
				if (x_array_now[finished] > x_array_limit[finished]) {
					x_array_now[finished] = x_array_limit[finished];
				}

				if (x_array_now[finished] == x_array_limit[finished]) {
					finished++;
				}

				repaint();

				try {
					Thread.sleep(threadDelay);
				} catch (InterruptedException ex) {
				}
			}

		}

	}



	private class LineChartListener extends MouseAdapter {
		double chartheight = height * 18 / 20;
		double chartwidth = width * 18 / 20;
		double y_separator = chartheight * 1.0 / seg;
		double x_separator = chartwidth / num;

		public void mousePressed(MouseEvent e) {

		}

		public void mouseExited(MouseEvent e) {

		}

		public void mouseClicked(MouseEvent e) {

		}

		public void mouseMoved(MouseEvent e) {

			if (finished == num) {
				int x = e.getX();
				int y = e.getY();
				for (int i = 0; i < num; i++) {
					if (x >= (x_array_limit[i] - x_separator / 2)
							&& x < (x_array_limit[i] + x_separator / 2)) {
						LineChart.this.removeAll();
						LineChart.this.updateUI();
						block_now = i;
						NodeLabel node1 = new NodeLabel(a_color,
								labelContent_a[i]);

						node1.setSize((int) chartwidth/10, (int) chartheight / 20);

						NodeLabel node2 = new NodeLabel(b_color,
								labelContent_b[i]);

						node2.setSize((int) chartwidth/10 + 10,
								(int) chartheight / 20);
						if (block_now < num / 2) {
							node1.setLocation((int) x_array_limit[i],
									(int) (ay_array_limit[i] - chartheight / 40));
							node1.setTowards(true);
							node2.setLocation((int) x_array_limit[i],
									(int) (by_array_limit[i] - chartheight / 40));
							node2.setTowards(true);
						} else {
							node1.setLocation(
									(int) (x_array_limit[i] - chartwidth/10),
									(int) (ay_array_limit[i] - chartheight / 40));
							node1.setTowards(false);
							node2.setLocation((int) (x_array_limit[i]
									- chartwidth/10 - 10),
									(int) (by_array_limit[i] - chartheight / 40));
							node2.setTowards(false);
						}

						LineChart.this.add(node1);
						LineChart.this.add(node2);
						break;
					}

				}
				y_now = y;
				// System.out.println(x + " " + y);
				repaint();
			}

		}
	}

	private class NodeLabel extends JLabel {
		private Color borderColor;
		private String content;
		private boolean isLeft = true;

		NodeLabel(Color borderColor, String content) {
			this.borderColor = borderColor;
			this.content = content;
			setOpaque(true);

		}

		public void setTowards(boolean isLeft) {
			this.isLeft = isLeft;
		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			int n = 7;
			int[] x_point = new int[n];
			int[] y_point = new int[n];
			int lableWidth = this.getWidth();
			int lableHeight = this.getHeight();
			if (isLeft) {
				x_point[0] = lableWidth / 10;
				y_point[0] = 1;
				x_point[1] = lableWidth - 1;
				y_point[1] = 1;
				x_point[2] = lableWidth - 1;
				y_point[2] = lableHeight - 1;
				x_point[3] = lableWidth / 10;
				y_point[3] = lableHeight - 1;
				x_point[4] = lableWidth / 10;
				y_point[4] = lableHeight * 3 / 5;
				x_point[5] = 0;
				y_point[5] = lableHeight / 2;
				x_point[6] = lableWidth / 10;
				y_point[6] = lableHeight * 2 / 5;
			} else {
				x_point[0] = 1;
				y_point[0] = 1;
				x_point[1] = lableWidth * 9 / 10;
				y_point[1] = 1;
				x_point[2] = lableWidth * 9 / 10;
				y_point[2] = lableHeight * 2 / 5;
				x_point[3] = lableWidth;
				y_point[3] = lableHeight / 2;
				x_point[4] = lableWidth * 9 / 10;
				y_point[4] = lableHeight * 3 / 5;
				x_point[5] = lableWidth * 9 / 10;
				y_point[5] = lableHeight - 1;
				x_point[6] = 1;
				y_point[6] = lableHeight - 1;
			}

			g2.setColor(new Color(220, 220, 223, 127));
			g2.fillPolygon(x_point, y_point, n);

			Stroke stroke = g2.getStroke();
			g2.setStroke(new BasicStroke(2f));
			g2.setColor(borderColor);
			g2.drawPolygon(x_point, y_point, n);
			g2.setStroke(stroke);

			g2.setColor(Color.black);
			
			int strWidth = g2.getFontMetrics(g2.getFont()).stringWidth(content);
			g2.drawString(content, lableWidth /2 - strWidth/2, lableHeight*3 / 5);
		}
	}

	public void go() {
		Thread s = new Thread(new DrawLineThread());
		s.start();

	}

	/*public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(0, 0, 1280, 1080);
		double[] value = { 3, 1, 4, 8, 5, 7, 8, 7, 1, 4 };
		String[] stringvalue = { "3", "1", "4", "8", "5", "7", "8", "7", "1", "4" };
		double[] value2 = { 1, 5, 7, 3, 8, 3, 5, 3, 6, 2 };
		String[] stringvalue2 = { "1", "5", "7", "3", "8", "3", "5", "3", "6", "2" };
		String[] name = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" };
		double limit = 10;
		LineChart chart = new LineChart(10, 5, 960, 720, limit, name, null,value,
				value2,stringvalue,stringvalue2);
		chart.setBounds(100, 100, 1280, 1080);
		f.setLayout(null);
		f.add(chart);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		chart.go();
		f.repaint();
	}*/
}
