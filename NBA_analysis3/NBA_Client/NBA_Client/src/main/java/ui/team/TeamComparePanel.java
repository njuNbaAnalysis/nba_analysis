package ui.team;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import util.UIUtils;
import vo.Teamvo;
import BLservice.BLservice;

interface ModuleButtonListener {
	public void update(String module);
}

interface ModuleButtonPainter {
	public void origin(Graphics2D g, ModuleButton button);

	public void isChosen(Graphics2D g, ModuleButton button);

	public void mouseEnter(Graphics2D g, ModuleButton button);

	public void mouseExit(Graphics2D g, ModuleButton button);
}

public class TeamComparePanel extends JPanel implements ModuleButtonListener {
	private Teamvo team1;
	private Teamvo team2;
	SeclectLabel select_l;
	SeclectLabel select_r;
	ResultLabel result_l;
	ResultLabel result_r;
	LineChartPanel lineChartPanel;
	HotZonePanel hotZonePanel;
	private RadarChartForTeamCompare radar;
	BLservice bl;
	private String season;
	private boolean isPlayOff;
	int width;
	int height;

	int currentChosen = 0;
	ArrayList<JPanel> panels = new ArrayList<JPanel>();
	ArrayList<ModuleButton> buttons = new ArrayList<ModuleButton>();
	ArrayList<Teamvo> allteams;

	String modules[] = { "近期表现", "热区对比", "当家球星", "神预测" };

	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setColor(new Color(148, 148, 148));
		g.fillRect(0, 0, width, height);
		g.drawImage(UIUtils.resize(team1.getLogo(), 420, 320), 58, 45, this);
		g.drawImage(UIUtils.resize(team2.getLogo(), 420, 320), 1250, 45, this);

		g.setColor(Color.black);
		g.fillRect(538, 0, 655, 130);
		g.setColor(Color.white);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 100));
		g.drawString("VS", 800, 100);

		g.setColor(new Color(36, 36, 36));
		g.fillRect(0, 410 + 50, 537, 158);
		g.fillRect(1194, 410 + 50, 537, 158);
		g.setColor(new Color(104, 104, 104));
		g.fillRect(0, 568 + 50, 537, 35);
		g.fillRect(1194, 568 + 50, 537, 35);
		g.setColor(new Color(221, 61, 66));
		g.fillRect(0, 568 + 50, 537 * team1.getNumOfVictory() / 82, 35);
		g.setColor(new Color(6, 74, 150));
		g.fillRect(1728 - 537 * team2.getNumOfVictory() / 82, 568 + 50,
				537 * team2.getNumOfVictory() / 82, 35);

		g.setColor(Color.white);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		g.drawString(team1.getName(), 230, 454 + 50);
		g.drawString(team2.getName(), 1200, 454 + 50);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 25));
		g.drawString(
				(team1.getConference() == 'E' ? "东区" : "西区") + "#"
						+ team1.getRankingInLeague(), 411, 519 + 50);
		g.drawString(
				(team2.getConference() == 'E' ? "东区" : "西区") + "#"
						+ team2.getRankingInLeague(), 1200, 519 + 50);
		g.drawString(team1.getNumOfVictory() + "胜",
				537 * team1.getNumOfVictory() / 82 - 60, 593 + 50);
		g.drawString("负" + (82 - team1.getNumOfVictory()),
				537 * team1.getNumOfVictory() / 82, 593 + 50);
		g.drawString("胜" + team2.getNumOfVictory(),
				1728 - 537 * team2.getNumOfVictory() / 82, 593 + 50);
		g.drawString((82 - team2.getNumOfVictory()) + "负",
				1668 - 537 * team2.getNumOfVictory() / 82, 593 + 50);
	}

	public TeamComparePanel(Teamvo team1, Teamvo team2, int width, int height,
			BLservice bl,String season,boolean isPlayOff) {
		this.team1 = team1;
		this.team2 = team2;
		this.width = width;
		this.height = height;
		this.bl = bl;
		this.season = season;
		this.isPlayOff = isPlayOff;
		try {
			allteams = bl.getAllTeams(team1.getSeason(), team1.isPlayOff());
		} catch (RemoteException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}

		this.setSize(width, height);
		this.setLayout(null);
		setBackground(new Color(148, 148, 148));
		initButtons();
		initRadar();
		initPanels();

		this.setPreferredSize(new Dimension(width, 808 + 730 + 200));
	}

	private void initRadar() {

		radar = new RadarChartForTeamCompare(654, 562, team1, team2,bl,season,isPlayOff);
		radar.setBounds(539, 130, 654, 562);
		this.add(radar);
		radar.setVisible(true);
		this.repaint();

	}

	private void initPanels() {
	    lineChartPanel = new LineChartPanel(width, 730, team1,
				team2, bl,season,isPlayOff);
		lineChartPanel.setLocation(0, 808);
		panels.add(lineChartPanel);
		this.add(lineChartPanel);
		lineChartPanel.setVisible(true);

	    hotZonePanel = new HotZonePanel(width, 700);
		hotZonePanel.setLocation(0, 808);
		panels.add(hotZonePanel);
		this.add(hotZonePanel);
		hotZonePanel.setVisible(false);

	}

	private void initButtons() {
		// 默认初始状态-->选择第一张表
		MainButtonPainter painter = new MainButtonPainter();

		ModuleButton first = new ModuleButton(modules[0], 430, 100, this,
				painter);
		first.isChosen = true;
		first.setLocation(0, 707);
		buttons.add(first);
		this.add(first);

		for (int i = 1; i < modules.length; i++) {
			ModuleButton tem = new ModuleButton(modules[i], 430, 100, this,
					painter);
			tem.setLocation(432 * i, 707);
			buttons.add(tem);
			this.add(tem);
		}

		select_l = new SeclectLabel(537, 60, true);
		select_l.setBounds(0, 400, 537, 60);
		Thread sl = new Thread(select_l);
		select_r = new SeclectLabel(537, 60, false);
		select_r.setBounds(1194, 400, 537, 60);
		Thread sr = new Thread(select_r);
		sl.start();
		sr.start();
		this.add(select_l);
		this.add(select_r);

	}

	@Override
	public void update(String module) {
		int index; // 匹配名称和序号
		for (index = 0; index < modules.length; index++) {
			if (modules[index] == module) {
				break;
			}
		}

		if (currentChosen != index) {
			buttons.get(currentChosen).recover();
		}
		currentChosen = index;

		for (int i = 0; i < panels.size(); i++) {
			JPanel each = panels.get(i);
			if (i != index) {
				each.setVisible(false);
			}
		}
		panels.get(index).setVisible(true);

	}

	class SeclectLabel extends JButton implements Runnable {

		int width;
		int height;
		boolean isLeft;
		JTextField text;
		final String initStr = "search";
		String old;

		public void paintComponent(Graphics g2) {
			super.paintComponent(g2);
			Graphics2D g = (Graphics2D) g2.create();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			g.setColor(new Color(206, 206, 206));
			g.fillRect(0, 0, width, height);

		}

		public SeclectLabel(int width, int height, boolean isLeft) {
			this.width = width;
			this.height = height;
			this.isLeft = isLeft;
			this.setLayout(null);
			this.setSize(width, height);
			this.setBorder(null);
			text = new JTextField();
			text.setBorder(null);
			text.setForeground(Color.gray);
			text.setFont(new Font("微软雅黑", Font.PLAIN, 25));
			text.addFocusListener(new FocusListener() {

				@Override
				public void focusLost(FocusEvent e) {
					JTextField src = (JTextField) e.getSource();
					src.setForeground(Color.gray);
					if (src.getText().trim().isEmpty()) {
						src.setText(initStr);
						src.setForeground(Color.LIGHT_GRAY);
					}

				}

				@Override
				public void focusGained(FocusEvent e) {
					JTextField src = (JTextField) e.getSource();
					src.setForeground(Color.black);
					if (src.getText().equals(initStr)) {
						src.setText(null);
					}

				}
			});
			text.setText(initStr);
			text.setBounds(15, 10, 500, 40);
			this.add(text);

		}

		@Override
		public void run() {

			while (true) {
				if (text.getText() != null) {
					if ((!text.getText().trim().equals(initStr))
							&& (!text.getText().trim().equals(""))) {
						if (!text.getText().trim().equals(old)) {
							old = text.getText().trim();
							ArrayList<Teamvo> items = new ArrayList<Teamvo>();
							for (Teamvo each : allteams) {
								if (each.getName().contains(old)) {
									items.add(each);
								}
							}
							if (items.size() > 0) {
								if (isLeft) {
									if (result_l != null) {
										TeamComparePanel.this.remove(result_l);
									}
									result_l = new ResultLabel(items,true);
									result_l.setLocation(0, 460);
									result_l.setLayout(null);
									TeamComparePanel.this.add(result_l);
									TeamComparePanel.this.updateUI();
								} else {
									if (result_r != null) {
										TeamComparePanel.this.remove(result_r);
									}
									result_r = new ResultLabel(items,false);
									result_r.setLocation(1194, 460);
									result_r.setLayout(null);
									TeamComparePanel.this.add(result_r);
									TeamComparePanel.this.updateUI();
								}
							} else {
								if (isLeft) {
									if (result_l != null) {
										result_l.setVisible(false);
									}
								} else {
									if (result_r != null) {
										result_r.setVisible(false);
									}
								}
							}

						}

					}
				}

			}

		}
	}
	
	/**
	 * 
	 * @author NJUYuanRui 搜索结果展示框
	 */
	class ResultLabel extends JLabel {
		boolean isLeft;
		public ResultLabel(ArrayList<Teamvo> results,boolean isLeft) {
			int height = 50 * results.size();
			this.setSize(537, height);
			this.isLeft = isLeft;
			this.setOpaque(true);
			this.setBackground(Color.white);
			for (int i = 0; i < results.size(); i++) {
				ResultItemLabel itemLabel = new ResultItemLabel(results.get(i)
						,isLeft);
				itemLabel.setLocation(0, 50 * i + 5);
				this.add(itemLabel);
			}

		}
	}

	/**
	 * 
	 * @author NJUYuanRui 搜索结果展示条目
	 */
	class ResultItemLabel extends JLabel implements MouseListener {
		Teamvo team;
		boolean isLeft;
		public ResultItemLabel(Teamvo item,boolean isLeft) {
			this.team = item;
			this.isLeft = isLeft;
			this.setOpaque(true);
			this.setSize(537, 40);
			this.setFont(new Font("微软雅黑", Font.PLAIN, 25));
			this.setForeground(Color.black);
			this.setText(item.getName());
			this.setBackground(Color.white);
			this.setHorizontalAlignment(LEFT);
			this.addMouseListener(this);

		}

		@Override
		public void mouseClicked(MouseEvent arg0) {
			// TODO 自动生成的方法存根

		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			this.setBackground(Color.gray);

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			this.setBackground(Color.white);

		}

		@Override
		public void mousePressed(MouseEvent arg0) {

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			//更新方法
			if(isLeft){
				TeamComparePanel.this.team1 = team;
				TeamComparePanel.this.repaint();
				radar = new RadarChartForTeamCompare(654, 562, team1, team2,bl,season,isPlayOff);
			}else{
				TeamComparePanel.this.team2 = team;
				TeamComparePanel.this.repaint();
			}
		}
	}

}

/*
 * 用于切换展示模型的按钮painter
 */
class MainButtonPainter implements ModuleButtonPainter {

	@Override
	public void origin(Graphics2D g, ModuleButton button) {
		g.setColor(new Color(69, 69, 69));
		g.fillRect(0, 0, button.width, button.height);

	}

	@Override
	public void isChosen(Graphics2D g, ModuleButton button) {
		g.setColor(Color.black);
		g.fillRect(0, 0, button.width, button.height);
		g.setColor(Color.white);
		g.drawString(button.name, button.textXCoordinate,
				button.textYCoordinate);

	}

	@Override
	public void mouseEnter(Graphics2D g, ModuleButton button) {
		g.setColor(Color.black);
		g.fillRect(0, 0, button.width, button.height);
		g.setColor(Color.white);
		g.drawString(button.name, button.textXCoordinate,
				button.textYCoordinate);

	}

	@Override
	public void mouseExit(Graphics2D g, ModuleButton button) {
		g.setColor(new Color(69, 69, 69));
		g.fillRect(0, 0, button.width, button.height);
		g.setColor(Color.white);
		g.drawString(button.name, button.textXCoordinate,
				button.textYCoordinate);
	}

}

/*
 * 折线图展示窗口 包括折线图表和选择按钮
 */
class LineChartPanel extends JPanel implements ModuleButtonListener {
	int width;
	int height;
	BLservice bl;
	private LineChartPanelForTeamCompare lineChart;
	Teamvo team1;
	Teamvo team2;
	String season;
	boolean isPlayOff;

	int currentChosen = 0;

	ArrayList<ModuleButton> chartButtons = new ArrayList<ModuleButton>();
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

		Stroke stroke = g.getStroke();
		Stroke lineStroke = new BasicStroke(1f);
		g.setStroke(lineStroke);
		g.setColor(new Color(221, 61, 66));
		g.drawLine(1480, 154, 1509, 126);
		g.drawLine(1509, 126, 1530, 153);
		g.drawLine(1530, 153, 1563, 128);
		g.drawLine(1563, 128, 1578, 145);
		g.setColor(new Color(6, 74, 150));
		g.drawLine(1480, 514, 1509, 486);
		g.drawLine(1509, 486, 1530, 513);
		g.drawLine(1530, 513, 1563, 488);
		g.drawLine(1563, 488, 1578, 505);

		g.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		g.setColor(Color.white);
		g.drawString(team1.getName(), 1610, 147);
		g.drawString(team2.getName(), 1610, 507);

	}

	public LineChartPanel(int width, int height, Teamvo team1, Teamvo team2,
			BLservice bl,String season,boolean isPlayOff) {
		super();
		this.width = width;
		this.height = height;
		this.team1 = team1;
		this.team2 = team2;
		this.bl = bl;
		this.season = season;
		this.isPlayOff = isPlayOff;
		this.setLayout(null);
		this.setSize(width, height);

		initButtons();
		initChart();
	}

	private void initChart() {

		lineChart = new LineChartPanelForTeamCompare(1180, height, "进攻", team1,
				team2, new Color(221, 61, 66), new Color(6, 74, 150), bl,season,isPlayOff);
		lineChart.setBounds(295, 0, 1180, height);
		this.add(lineChart);

	}

	private void initButtons() {
		// 默认初始状态-->选择第一张表
		LineChartButtonPainter painter = new LineChartButtonPainter();

		ModuleButton first = new ModuleButton(modules[0], 295, 117, this,
				painter);
		first.isChosen = true;
		first.setLocation(0, 0);
		chartButtons.add(first);
		this.add(first);

		for (int i = 1; i < modules.length; i++) {
			ModuleButton tem = new ModuleButton(modules[i], 295, 117, this,
					painter);
			tem.setLocation(0, 120 * i);
			chartButtons.add(tem);
			this.add(tem);
		}
	}

	public void update(String module) {
		int index; // 匹配名称和序号
		for (index = 0; index < modules.length; index++) {
			if (modules[index] == module) {
				break;
			}
		}

		if (currentChosen != index) {
			chartButtons.get(currentChosen).recover();
		}

		currentChosen = index;
		this.remove(lineChart);
		lineChart = new LineChartPanelForTeamCompare(1180, height, module,
				team1, team2, new Color(221, 61, 66), new Color(6, 74, 150), bl,season,isPlayOff);
		lineChart.setBounds(295, 0, 1180, height);
		this.add(lineChart);
		this.repaint();

	}

	class LineChartButtonPainter implements ModuleButtonPainter {

		@Override
		public void origin(Graphics2D g, ModuleButton button) {
			g.setColor(new Color(83, 82, 100));
			g.fillRect(0, 0, button.width, button.height);

		}

		@Override
		public void isChosen(Graphics2D g, ModuleButton button) {
			g.setColor(new Color(191, 22, 28));
			int x[] = { 0, button.width - button.width / 20, button.width,
					button.width - button.width / 20, 0 };
			int y[] = { 0, 0, button.height / 2, button.height, button.height };
			g.fillPolygon(x, y, 5);
			g.setColor(Color.black);
			g.drawString(button.name, button.textXCoordinate,
					button.textYCoordinate);

		}

		@Override
		public void mouseEnter(Graphics2D g, ModuleButton button) {
			g.setColor(new Color(107, 106, 120));
			g.fillRect(0, 0, button.width - button.width / 20, button.height);

			g.setColor(Color.white);
			g.drawString(button.name, button.textXCoordinate,
					button.textYCoordinate);

		}

		@Override
		public void mouseExit(Graphics2D g, ModuleButton button) {
			g.setColor(Color.black);
			g.fillRect(0, 0, button.width - button.width / 20, button.height);

			g.setColor(Color.white);
			g.drawString(button.name, button.textXCoordinate,
					button.textYCoordinate);

		}

	}

}

class ModuleButton extends JLabel implements MouseListener {
	String name;
	int width;
	int height;

	boolean isChosen;
	boolean isMouseEntered;

	int textXCoordinate; // 文字x坐标
	int textYCoordinate; // 文字y坐标

	ModuleButtonListener parent;
	ModuleButtonPainter painter;

	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 50));
		FontMetrics fm = g.getFontMetrics();
		int stringWidth = fm.stringWidth(name);
		int stringAscent = fm.getAscent();
		textXCoordinate = getWidth() / 2 - stringWidth / 2;
		textYCoordinate = getHeight() / 2 + stringAscent / 3;

		painter.origin(g, this);

		if (isChosen) {
			painter.isChosen(g, this);
		}

		else if ((!isChosen) && isMouseEntered) {
			painter.mouseEnter(g, this);
		}

		else {
			painter.mouseExit(g, this);
		}

	}

	public ModuleButton(String name, int width, int height,
			ModuleButtonListener parent, ModuleButtonPainter painter) {
		super();
		this.name = name;
		this.width = width;
		this.height = height;
		this.isChosen = false;
		this.isMouseEntered = false;
		this.painter = painter;
		this.parent = parent;
		this.setSize(width, height);
		this.addMouseListener(this);

	}

	public void recover() {
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
		parent.update(name);
		repaint();

	}

}

class HotZonePanel extends JPanel {
	int width;
	int height;
	JLabel position_l;
	JLabel seasonHitRate_l;
	JLabel latest5HitRate_l;
	JLabel position_r;
	JLabel seasonHitRate_r;
	JLabel latest5HitRate_r;
	ArrayList<HotZone> hotZones = new ArrayList<HotZone>();

	Position positions[] = { Position.l24Plus, Position.l1624_1,
			Position.l816_1, Position.c08_4, Position.r816_1, Position.r1624_1,
			Position.r24Plus_1, Position.lc1624_1, Position.c816_2,
			Position.c1624_2, Position.rc1624_1, Position.lc24Plus_1,
			Position.c24Plus_1, Position.rc24Plus_1 };

	String posNames[] = { "左侧24英尺以外", "左侧16-24英尺", "左侧8-16英尺", "8英尺以内",
			"右侧8-16英尺", "右侧16-24英尺", "右侧24英尺以外", "左侧居中16-24英尺", "中间8-16英尺",
			"中间16-24英尺", "右侧居中16-24英尺", "左侧居中24英尺以外", "中间24英尺以外", "右侧居中24英尺以外" };

	public void paintComponent(Graphics g2) {
		super.paintComponent(g2);
		Graphics2D g = (Graphics2D) g2.create();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(Color.white);
		g.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		g.drawString("投篮区域", 330, 50);
		g.drawString("赛季表现", 330, 245);
		g.drawString("最近五场表现", 270, 450);
		g.drawString("投篮区域", 1280, 50);
		g.drawString("赛季表现", 1280, 245);
		g.drawString("最近五场表现", 1280, 450);

	}

	public HotZonePanel(int width, int height) {

		// 自己做桩
		int tem[] = { 10, 20 };
		hotZones.add(new HotZone(Position.c08_4, 0.5, tem, 0.5, tem));
		hotZones.add(new HotZone(Position.c1624_2, 0.5, tem, 0.5, tem));
		hotZones.add(new HotZone(Position.c24Plus_1, 0.5, tem, 0.5, tem));
		hotZones.add(new HotZone(Position.c816_2, 0.5, tem, 0.5, tem));
		hotZones.add(new HotZone(Position.l1624_1, 0.5, tem, 0.5, tem));
		hotZones.add(new HotZone(Position.l24Plus, 0.5, tem, 0.5, tem));
		hotZones.add(new HotZone(Position.l816_1, 0.5, tem, 0.5, tem));
		hotZones.add(new HotZone(Position.lc1624_1, 0.5, tem, 0.5, tem));
		hotZones.add(new HotZone(Position.lc24Plus_1, 0.5, tem, 0.5, tem));
		hotZones.add(new HotZone(Position.r1624_1, 0.5, tem, 0.5, tem));
		hotZones.add(new HotZone(Position.r24Plus_1, 0.5, tem, 0.5, tem));
		hotZones.add(new HotZone(Position.r816_1, 0.5, tem, 0.5, tem));
		hotZones.add(new HotZone(Position.rc1624_1, 0.5, tem, 0.5, tem));
		hotZones.add(new HotZone(Position.rc24Plus_1, 0.5, tem, 0.5, tem));
		// 以上是假数据

		this.setLayout(null);
		this.width = width;
		this.height = height;
		this.setSize(width, height);
		this.setBackground(new Color(46, 46, 46));
		JPanel hotZonePic = new HotZonePicPanel(686, 678, this);
		this.add(hotZonePic);
		hotZonePic.setSize(686, 678);
		hotZonePic.setLocation((width - 686) / 2, 0);
		hotZonePic.setVisible(true);
		hotZonePic.setOpaque(true);

		initLabel();
	}

	private void initLabel() {

		position_l = new JLabel();
		position_l.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		position_l.setForeground(Color.white);
		position_l.setSize(300, 60);
		position_l.setLocation(200, 100);
		position_l.setText("");
		position_l.setVisible(true);
		this.add(position_l);

		seasonHitRate_l = new JLabel();
		seasonHitRate_l.setFont(new Font("default", Font.PLAIN, 50));
		seasonHitRate_l.setForeground(new Color(221, 61, 66));
		seasonHitRate_l.setSize(300, 60);
		seasonHitRate_l.setLocation(320, 275);
		seasonHitRate_l.setText("");
		seasonHitRate_l.setVisible(true);
		this.add(seasonHitRate_l);

		latest5HitRate_l = new JLabel();
		latest5HitRate_l.setFont(new Font("default", Font.PLAIN, 50));
		latest5HitRate_l.setForeground(new Color(221, 61, 66));
		latest5HitRate_l.setSize(300, 60);
		latest5HitRate_l.setLocation(320, 480);
		latest5HitRate_l.setText("");
		latest5HitRate_l.setVisible(true);
		this.add(latest5HitRate_l);

		position_r = new JLabel();
		position_r.setFont(new Font("微软雅黑", Font.PLAIN, 30));
		position_r.setForeground(Color.white);
		position_r.setSize(300, 60);
		position_r.setLocation(1280, 100);
		position_r.setText("");
		position_r.setVisible(true);
		this.add(position_r);

		seasonHitRate_r = new JLabel();
		seasonHitRate_r.setFont(new Font("default", Font.PLAIN, 50));
		seasonHitRate_r.setForeground(new Color(6, 74, 150));
		seasonHitRate_r.setSize(300, 60);
		seasonHitRate_r.setLocation(1280, 275);
		seasonHitRate_r.setText("");
		seasonHitRate_r.setVisible(true);
		this.add(seasonHitRate_r);

		latest5HitRate_r = new JLabel();
		latest5HitRate_r.setFont(new Font("default", Font.PLAIN, 50));
		latest5HitRate_r.setForeground(new Color(6, 74, 150));
		latest5HitRate_r.setSize(300, 60);
		latest5HitRate_r.setLocation(1280, 480);
		latest5HitRate_r.setText("");
		latest5HitRate_r.setVisible(true);
		this.add(latest5HitRate_r);

	}

	public void update(int index) {
		for (HotZone each : hotZones) {
			if (each.position == positions[index]) {
				position_l.setText(posNames[index]);
				position_l.setLocation(
						450 - position_l.getFontMetrics(position_l.getFont())
								.stringWidth(posNames[index]), 100);
				seasonHitRate_l.setText(each.latest5HitRate * 100 + "%");
				latest5HitRate_l.setText(each.latest5HitRate * 100 + "%");
				position_r.setText(posNames[index]);
				seasonHitRate_r.setText(each.latest5HitRate * 100 + "%");
				latest5HitRate_r.setText(each.latest5HitRate * 100 + "%");
			}
		}

	}

	public class HotZone {
		Position position;
		double seasonHitRate; // 璧涘鍛戒腑锟??
		int[] seasonHitsShots; // 璧涘鍛戒腑鏁板拰鍑烘墜锟??
		double latest5HitRate; // 杩戜簲鍦哄懡涓巼
		int[] latest5HitsShots; // 杩戜簲鍦哄懡涓暟鍜屽嚭鎵嬫暟

		public HotZone(Position position, double seasonHitRate,
				int[] seasonHitsShots, double latest5HitRate,
				int[] latest5HitsShots) {
			super();
			this.position = position;
			this.seasonHitRate = seasonHitRate;
			this.seasonHitsShots = seasonHitsShots;
			this.latest5HitRate = latest5HitRate;
			this.latest5HitsShots = latest5HitsShots;
		}

		public Position getPosition() {
			return position;
		}

		public double getSeasonHitRate() {
			return seasonHitRate;
		}

		public int[] getSeasonHitsShots() {
			return seasonHitsShots;
		}

		public double getLatest5HitRate() {
			return latest5HitRate;
		}

		public int[] getLatest5HitsShots() {
			return latest5HitsShots;
		}

	}

	enum Position {
		l24Plus, // 宸︿晶24鑻卞昂浠ュ
		l1624_1, // 宸︿晶16-24鑻卞昂
		l816_1, // 宸︿晶8-16鑻卞昂
		c08_4, // 8鑻卞昂浠ュ唴
		r816_1, // 鍙充晶8-16鑻卞昂
		r1624_1, // 鍙充晶16-24鑻卞昂
		r24Plus_1, // 鍙充晶24鑻卞昂浠ュ
		c816_2, // 涓棿8-16鑻卞昂
		lc1624_1, // 宸︿晶灞呬腑16-24鑻卞昂
		c1624_2, // 涓棿16-24鑻卞昂
		rc1624_1, // 鍙充晶灞呬腑16-24鑻卞昂
		lc24Plus_1, // 宸︿晶灞呬腑24鑻卞昂浠ュ
		c24Plus_1, // 涓棿24鑻卞昂浠ュ
		rc24Plus_1, // 鍙充晶灞呬腑24鑻卞昂浠ュ

	}

	class HotZonePicPanel extends JPanel implements MouseMotionListener {

		int width;
		int height;
		HotZonePanel hotZonePanel;
		ArrayList<BufferedImage> imageList = new ArrayList<BufferedImage>(); // 原图片组件
		ArrayList<BufferedImage> imageList_highlight = new ArrayList<BufferedImage>(); // 高亮图片组件
		ArrayList<BufferedImage> imageToDraw = new ArrayList<BufferedImage>(); // 待绘图片组件

		int currentHighLight; // 当前高亮的组件1~14，如果为0则表示没有高亮

		public void paintComponent(Graphics g2) {
			Graphics2D g = (Graphics2D) g2.create();
			g.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);

			super.paintComponent(g);

			for (BufferedImage each : imageToDraw) {
				g.drawImage(each, 0, 0, this);

			}

		}

		public HotZonePicPanel(int width, int height, HotZonePanel hotZonePanel) {
			this.hotZonePanel = hotZonePanel;

			this.setLayout(null);
			this.addMouseMotionListener(this);

			ImageReader reader = ImageReader.getInstance();
			imageList = reader.getHot_imageList();
			imageList_highlight = reader.getHot_imageList_highlight();
			imageToDraw = reader.getHot_imageToDraw();

		}

		@Override
		public void mouseDragged(MouseEvent arg0) {
			// TODO 自动生成的方法存根
		}

		@Override
		public void mouseMoved(MouseEvent e) {

			int ix = e.getX();
			int iy = e.getY();
			int index = 0; // 储存匹配到的图片序号
			for (BufferedImage each : imageList) {
				index++;
				int alpha = (each.getRGB(ix, iy) >> 24) & 0xff;
				if (alpha != 0) {
					// 首先恢复之前高亮的部分
					if (currentHighLight != 0) {
						imageToDraw.set(currentHighLight - 1,
								imageList.get(currentHighLight - 1));
					}
					// 修改当前高亮部分序号
					currentHighLight = index;
					imageToDraw.set(index - 1,
							imageList_highlight.get(index - 1));
					// 修改右侧数据
					hotZonePanel.update(index - 1);
					repaint();
					break;
				}
			}

		}
	}

}


