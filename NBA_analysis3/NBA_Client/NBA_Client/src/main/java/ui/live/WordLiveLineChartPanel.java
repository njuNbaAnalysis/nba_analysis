package ui.live;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Comparator;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import ui.chart.LineChart;
import util.Tools;
import vo.EventVo;

public class WordLiveLineChartPanel extends JPanel {
	private int width;
	private int height;
	private SectionButton[] btArray;
	private ArrayList<EventVo> eventList;
	int sectionSize;
	int selectedNumber = -1;// 默认选中“全部”
	JPanel chartPanel;

	WordLiveLineChartPanel(int width, int height, ArrayList<EventVo> eventList) {
		this.setLayout(null);
		this.setSize(width, height);
		this.width = width;
		this.height = height;

		this.eventList = eventList;
		setButton();
		setLineChart(eventList);

	}

	private void setLineChart(ArrayList<EventVo> eventList) {
		ArrayList<EventVo> a_events = new ArrayList<EventVo>();
		ArrayList<EventVo> b_events = new ArrayList<EventVo>();
		int minute = Tools.getSectionTimeInSecond(Tools
				.getSectionNum(eventList)+1) / 60;
		for (int i = 0; i < eventList.size(); i++) {
			EventVo event = eventList.get(i);
			if (event.getNum() == 0) {
				a_events.add(event);
			}
			if (event.getNum() == 1) {
				b_events.add(event);
			}

		}
		int min = 1000;
		int max = 0;
		for (int i = 0; i < eventList.size(); i++) {
			int point = eventList.get(i).getTeamPoint();
			if (point < min) {
				min = point;
			}

			if (point > max) {
				max = point;
			}
		}
		int separator = 5;
		min = (min / separator) * separator;
		max = (max / separator) * separator + separator;
		int size = (max - min) / separator + 1;
		int[] seg = new int[size];
		for (int i = min; i <= max; i += separator) {
			seg[(i - min) / separator] = i;
		}
		chartPanel = new JPanel();
		chartPanel.setBounds(0, height / 10,width, height * 9 / 10);
		chartPanel.setLayout(null);
		System.out.println("chart all: eventList:"+eventList.size()+" "+a_events.size()+" "+b_events.size());
		LiveLineChart chart = new LiveLineChart(seg, width, height * 9 / 10, a_events,
				b_events, minute, 0);
		chart.setLocation(0, 0);
		chartPanel.add(chart);
		this.add(chartPanel);
		chart.go();

	}

	private void updateLineChart(int type) {
		ArrayList<EventVo> a_events = new ArrayList<EventVo>();
		ArrayList<EventVo> b_events = new ArrayList<EventVo>();
		int min = 1000;
		int max = 0;
		int minute = 12;
		int startTime = Tools.getSectionTimeInSecond(type);
		if (type != 0) {
			if (type > 4) {
				minute = 5;
			}

			for (EventVo event : eventList) {
				if (event.getNum() == 0 && event.getSection() == type) {
					a_events.add(event);
				}
				if (event.getNum() == 1 && event.getSection() == type) {
					b_events.add(event);
				}

			}

			for (EventVo event : eventList) {
				int point = event.getTeamPoint();
				if (point < min && event.getSection() == type) {
					min = point;
				}

				if (point > max && event.getSection() == type) {
					max = point;
				}
			}
			//System.out.println("min:" + min + " max:" + max);

		} else {
			minute = Tools.getSectionTimeInSecond(Tools
					.getSectionNum(eventList)+1) / 60;
			for (EventVo event : eventList) {
				if (event.getNum() == 0) {
					a_events.add(event);
				}
				if (event.getNum() == 1) {
					b_events.add(event);
				}

			}

			for (EventVo event : eventList) {
				int point = event.getTeamPoint();
				if (point < min) {
					min = point;
				}

				if (point > max) {
					max = point;
				}
			}
		}

		int separator = 5;
		min = (min / separator) * separator;
		max = (max / separator) * separator + separator;
		int size = (max - min) / separator + 1;
		int[] seg = new int[size];
		for (int i = min; i <= max; i += separator) {
			seg[(i - min) / separator] = i;
		}
		chartPanel.removeAll();
		System.out.println("chart all: eventList:"+eventList.size()+" "+a_events.size()+" "+b_events.size());
		LiveLineChart chart = new LiveLineChart(seg, width, height * 9 / 10, a_events,
				b_events, minute, startTime);

		chart.setLocation(0, 0);
		chartPanel.add(chart);
		chartPanel.updateUI();
		chartPanel.repaint();
		chart.go();

	}

	private void setButton() {
		sectionSize = Tools.getSectionNum(eventList);
		btArray = new SectionButton[sectionSize + 1];
		for (int i = 0; i < sectionSize + 1; i++) {
			btArray[i] = new SectionButton(Tools.getSectionInChinese(i), i);
			btArray[i].setBounds(width * i / 8, 0, width / 8, height / 10);
			MouseHandle mouseHandle = new MouseHandle(new Color(69, 69, 69),
					new Color(87, 89, 91), i);
			btArray[i].addMouseListener(mouseHandle);
			this.add(btArray[i]);

		}
	}

	public void paintComponent(Graphics g) {
		g.setColor(new Color(87, 89, 91));
		g.fillRect(0, 0, width, height / 10);
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

				for (int i = 0; i <= sectionSize; i++) {
					if (type == i) {
						updateLineChart(type);
					}

				}

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

	private class LiveLineChart extends JPanel {

		private int[] seg;// 纵坐标数组

		private int width;
		private int height;
		private double chartheight;
		private double chartwidth;
		private double x_separator;
		private double y_separator;
		private int a_num;
		private int b_num;
		private int y_num;

		private ArrayList<EventVo> a_value;// 事件按照时间顺序
		private ArrayList<EventVo> b_value;

		private volatile int a_finished;
		private int[] ax_array_limit;
		private double ax_now;
		private int[] ay_array_limit;
		private double ay_now;

		private volatile int b_finished;
		private int[] bx_array_limit;
		private double bx_now;
		private int[] by_array_limit;
		private double by_now;

		private Color a_color = new Color(92, 132, 158);
		private Color b_color = new Color(216, 200, 129);

		private int threadDelay = 1000;

		private int y_now = -1;// 当前纵坐标的值
		private int startTime;

		LiveLineChart(int[] seg, int width, int height,
				ArrayList<EventVo> a_value, ArrayList<EventVo> b_value,
				int minute, int startTime) {
			this.width = width;
			this.height = height;
			this.seg = seg;
			this.a_value = a_value;
			this.b_value = b_value;
			this.chartheight = height * 18 / 20;
			this.chartwidth = width * 18 / 20;
			this.x_separator = chartwidth * 1.0 / (minute * 60);// 表示1秒钟对应的x轴上的偏移
			this.y_separator = chartheight * 1.0
					/ (seg[seg.length - 1] - seg[0]);// 表示1得分对应的y轴上的偏移

			this.a_num = a_value.size();
			this.b_num = b_value.size();
			this.y_num = seg.length;
			this.startTime = startTime;

			this.setSize(width, height);
			this.setLayout(null);
			init();
			LineChartListener l = new LineChartListener();
			this.addMouseListener(l);
			this.addMouseMotionListener(l);
			
		}

		private void init() {
			// 设置点的坐标

			a_finished = 1;
			ax_array_limit = new int[a_num];
			ay_array_limit = new int[a_num];

			b_finished = 1;
			bx_array_limit = new int[b_num];
			by_array_limit = new int[b_num];

			for (int i = 0; i < a_num; i++) {
				ax_array_limit[i] = (int) (x_separator
						* (a_value.get(i).getTimeInSecond() - startTime) + width / 20);
				ay_array_limit[i] = (int) ((height * 1 / 20 + chartheight) - (y_separator * (a_value
						.get(i).getTeamPoint() - seg[0])));
				if (i == 0) {
					ax_now = ax_array_limit[i];
					ay_now = ay_array_limit[i];

				}
			}

			for (int i = 0; i < b_num; i++) {
				bx_array_limit[i] = (int) (x_separator
						* (b_value.get(i).getTimeInSecond() - startTime) + width / 20);
				by_array_limit[i] = (int) ((height * 1 / 20 + chartheight) - (y_separator * (b_value
						.get(i).getTeamPoint() - seg[0])));

				if (i == 0) {
					bx_now = bx_array_limit[i];
					by_now = by_array_limit[i];

				}
			}

		}

		public void paintComponent(Graphics g) {
			Graphics2D g2 = (Graphics2D) g;
			g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g2.setColor(this.getBackground());

			g2.fillRect(0, 0, 2000, 1500);// 预设一个比较大的值

			// 画坐标系，坐标系左右有width/20的空隙，上下有height/20的空隙
			Stroke stroke = g2.getStroke();
			for (int i = 0; i < y_num; i++) {

				// 设置虚线
				Stroke dash = new BasicStroke(1.5f, BasicStroke.CAP_BUTT,
						BasicStroke.JOIN_ROUND, 3.5f, new float[] { 15, 10 },
						0f);
				g2.setStroke(dash);
				g2.setColor(new Color(206, 206, 206, 127));

				g2.drawLine(width / 20,
						(int) (height * 19 / 20 - (seg[i] - seg[0])
								* y_separator), width * 19 / 20,
						(int) (height * 19 / 20 - (seg[i] - seg[0])
								* y_separator));

				g2.setStroke(stroke);
				g2.setColor(Color.black);
				g2.drawString(seg[i] + "", width / 40,
						(int) (height * 19 / 20 - (seg[i] - seg[0])
								* y_separator));
			}

			// 画直线
			int[] ax_temp = new int[a_finished + 1];
			int[] ay_temp = new int[a_finished + 1];

			int[] bx_temp = new int[b_finished + 1];
			int[] by_temp = new int[b_finished + 1];
			for (int i = 0; i < (a_finished + 1); i++) {
				if (i == (a_finished)) {
					ax_temp[i] = (int) ax_now;
					ay_temp[i] = (int) ay_now;
				} else {
					ax_temp[i] = ax_array_limit[i];
					ay_temp[i] = ay_array_limit[i];
				}
			}
			for (int i = 0; i < (b_finished + 1); i++) {
				if (i == (b_finished)) {
					bx_temp[i] = (int) bx_now;
					by_temp[i] = (int) by_now;
				} else {
					bx_temp[i] = bx_array_limit[i];
					by_temp[i] = by_array_limit[i];
				}
			}

			synchronized (g) {
				g2.setColor(a_color);
				if (a_finished <= a_num) {
					g2.drawPolyline(ax_temp, ay_temp,
							Math.min(a_finished, a_num));
				}
				g2.setColor(b_color);
				if (b_finished <= b_num) {

					g2.drawPolyline(bx_temp, by_temp,
							Math.min(b_finished, b_num));
				}

			}

		}

		private class DrawLineThread implements Runnable {

			@Override
			public void run() {
				while (a_finished < a_num || b_finished < b_num) {
					double k1 = 0;
					double k2 = 0;
					if (a_finished < a_num) {
						if(ax_array_limit[a_finished] == ax_array_limit[a_finished - 1]){
							if(ay_array_limit[a_finished] > ay_array_limit[a_finished - 1]){
								k1 = Double.MAX_VALUE;
							}
							if(ay_array_limit[a_finished] < ay_array_limit[a_finished - 1]){
								k1 = -Double.MAX_VALUE;
							}
							
						}else{
							k1 = (ay_array_limit[a_finished] - ay_array_limit[a_finished - 1])
									* 1.0
									/ (ax_array_limit[a_finished] - ax_array_limit[a_finished - 1]);// 斜率k1
						}
						
					}
					if (b_finished < b_num) {
						if(bx_array_limit[b_finished] == bx_array_limit[b_finished - 1]){
							k2 = Double.MAX_VALUE;
						}else{
							k2 = (by_array_limit[b_finished] - by_array_limit[b_finished - 1])
									* 1.0
									/ (bx_array_limit[b_finished] - bx_array_limit[b_finished - 1]);// 斜率k2
						}
						
					}

					if (a_finished < a_num
							&& ax_now <= ax_array_limit[a_finished]) {

						ax_now += 2;

					}

					if (a_finished < a_num
							&& ay_now <= ay_array_limit[a_finished]) {
						ay_now += k1 * 2;
					}

					// 修正
					if (a_finished < a_num) {
						for (int i = 1; i < a_num; i++) {
							if ((ax_now >= ax_array_limit[i - 1]
									&& ax_now <= ax_array_limit[i])
									||( ay_now >= ay_array_limit[i - 1]
									&& ay_now <= ay_array_limit[i])) {
								ax_now = ax_array_limit[i];
								ay_now = ay_array_limit[i];
								a_finished = i;
							}
						}
						System.out.println("a_limit:" + a_num);
						System.out.println(ax_array_limit[a_finished] + "  " + ay_array_limit[a_finished]);
						System.out.println("a_finished:" + a_finished);
						System.out.println(ax_now + "   " + ay_now);
						
					}

					if (b_finished < b_num
							&& bx_now <= bx_array_limit[b_finished]) {

						bx_now += 2;

					}

					if (b_finished < b_num
							&& by_now <= by_array_limit[b_finished]) {

						by_now += k2 * 2;
					}

					if (b_finished < b_num) {
						for (int i = 1; i < b_num; i++) {
							if ((bx_now >= bx_array_limit[i - 1]
									&& bx_now <= bx_array_limit[i])
									||( by_now >= by_array_limit[i - 1]
									&& by_now <= by_array_limit[i])) {
								bx_now = bx_array_limit[i];
								by_now = by_array_limit[i];
								b_finished = i;
							}
						}
						System.out.println("b_limit:" + b_num);
						System.out.println(bx_array_limit[b_finished] + "  " + by_array_limit[b_finished]);
						System.out.println("b_finished:" + b_finished);
						System.out.println(bx_now + "    " + by_now);
						
					}

					repaint();
					
					if(ax_now == ax_array_limit[a_num-1]&&ay_now == ay_array_limit[a_num-1]){
						a_finished = a_num;
					}
					
					if(bx_now == bx_array_limit[b_num-1]&&by_now == by_array_limit[b_num-1]){
						b_finished = b_num;
					}

					try {
						Thread.sleep(threadDelay);
					} catch (InterruptedException ex) {
					}
				}
				a_finished = a_num;
				b_finished = b_num;
			}

		}

		private class LineChartListener extends MouseAdapter {

			public void mousePressed(MouseEvent e) {

			}

			public void mouseExited(MouseEvent e) {

			}

			public void mouseClicked(MouseEvent e) {

			}

			public void mouseMoved(MouseEvent e) {
				// 显示距离最小的节点值
				if (a_finished >= a_num && b_finished >= b_num) {

					int x = e.getX();
					int y = e.getY();
					double squareDistance = Double.MAX_VALUE;
					int record = -1;
					for (int i = 0; i < a_num; i++) {
						double d = (ax_array_limit[i] - x)
								* (ax_array_limit[i] - x)
								+ (ay_array_limit[i] - y)
								* (ay_array_limit[i] - y);
						if (d < squareDistance) {
							squareDistance = d;
							record = i;
						}
					}

					for (int i = 0; i < b_num; i++) {
						double d = (bx_array_limit[i] - x)
								* (bx_array_limit[i] - x)
								+ (by_array_limit[i] - y)
								* (by_array_limit[i] - y);
						if (d < squareDistance) {
							squareDistance = d;
							record = i + a_num;
						}
					}

					NodeLabel node;
					int node_x = 0;
					int node_y = 0;
					int node_width = width / 5;
					int node_height = height / 8;
					if (record < a_num) {
						node = new NodeLabel(a_color, a_value.get(record));
						node_x = ax_array_limit[record];
						node_y = ay_array_limit[record];
					} else {
						record -= a_num;
						node = new NodeLabel(b_color, b_value.get(record));
						node_x = bx_array_limit[record];
						node_y = by_array_limit[record];
					}
					node.setBounds(node_x, node_y - node_height / 2,
							node_width, node_height);

					if (node_x + node_width > chartwidth) {
						node.setTowards(false);
						node.setBounds(node_x - node_width, node_y
								- node_height / 2, node_width, node_height);
					}

					LiveLineChart.this.removeAll();
					LiveLineChart.this.updateUI();
					LiveLineChart.this.add(node);
				}

				repaint();
			}
		}

		private class NodeLabel extends JLabel {
			private Color borderColor;
			private EventVo event;
			private boolean isLeft = true;

			NodeLabel(Color borderColor, EventVo event) {
				this.borderColor = borderColor;
				this.event = event;
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

				// 画边框
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

				String content = event.getPoints() + " " + event.getTime()
						+ " Q" + event.getSection();

				g2.setColor(Color.black);
				int strWidth = g2.getFontMetrics(g2.getFont()).stringWidth(
						content);
				g2.drawString(content, lableWidth / 2 - strWidth / 2,
						lableHeight * 2 / 5);
				content = event.getDescription();
				strWidth = g2.getFontMetrics(g2.getFont()).stringWidth(content);
				g2.drawString(content, lableWidth / 2 - strWidth / 2,
						lableHeight * 4 / 5);
			}
		}

		public void go() {
			Thread s = new Thread(new DrawLineThread());
			s.start();

		}

	}
}
