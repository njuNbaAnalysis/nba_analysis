package ui;

import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.BLController;
import logic.BLService;
import logic.teams.Team;

public class LineChartPanelForTeamCompare extends JPanel {
	private int width;
	private int height;
	private String[] x_name = new String[10];
	private String[] y_name = null;
	private double[] t1_value = new double[10];
	private double[] t2_value = new double[10];
	private String[] labelContent_t1 = new String[10];
	private String[] labelContent_t2 = new String[10];
	private Team t1;
	private Team t2;
	private BLService bl;
	private int seg;
	private int limit;
	private DecimalFormat df = new DecimalFormat("#0.0");

	LineChartPanelForTeamCompare(int width, int height, String type, Team t1,
			Team t2, BLService bl) {
		this.width = width;
		this.height = height;
		this.bl = bl;
		this.t1 = t1;
		this.t2 = t2;
		this.setSize(width, height);
		this.setLayout(null);
		init(type);
		LineChart chart = new LineChart(x_name.length, seg, width, height,
				limit, x_name, y_name, t1_value, t2_value, labelContent_t1,
				labelContent_t2);
		chart.setBounds(0, 0, width, height);
		this.add(chart);
		chart.go();
		repaint();
	}

	private void init(String type) {
		for (int i = 0; i < x_name.length; i++) {
			x_name[i] = x_name.length - i + "";
		}
		switch (type) {
		case "进攻":
			initOffend();
			break;
		case "防守":
			initDefend();
			break;
		case "节奏":
			initTempo();
			break;
		case "攻防比":
			initOffendThanDefend();
			break;
		case "战绩":
			initStandinds();
			break;
		case "交手":
			initGrips();
			break;
		}

	}

	private void initGrips() {

		y_name = new String[4];
		y_name[0] = "";
		y_name[1] = "lose";
		y_name[2] = "win";
		y_name[3] = "";
		// t1_value = t1.getLatestWinOrLose();
		// labelContent_t1 = t1.getLatestPoints(t1,t2);
		for (int i = 0; i < t1_value.length; i++) {
			t1_value[i] = (i + 1) % 2 + 1;
			labelContent_t1[i] = "101-100";
		}

		// t2_value = t1.getLatestWinOrLose();
		// labelContent_t2 = t2.getLatestPoints(t1,t2);
		for (int i = 0; i < t2_value.length; i++) {
			t2_value[i] = (i) % 2 + 1;
			labelContent_t2[i] = "101-100";
		}// TODO Auto-generated method stub
		seg = 3;
		limit = 3;

	}

	private void initStandinds() {
		y_name = new String[4];
		y_name[0] = "low";
		y_name[1] = "lose";
		y_name[2] = "win";
		y_name[3] = "above";
		//t1_value = t1.getLatestWinOrLose();
		// labelContent_t1 = t1.getLatestPoints(t1,t2);
		for (int i = 0; i < t1_value.length; i++) {
			t1_value[i] = (i + 1) % 2 + 1;
			labelContent_t1[i] = "101-100";
		}

		// t2_value = t1.getLatestWinOrLose();
		// labelContent_t2 = t2.getLatestPoints(t1,t2);
		for (int i = 0; i < t2_value.length; i++) {
			t2_value[i] = (i) % 2 + 1;
			labelContent_t2[i] = "101-100";
		}// TODO Auto-generated method stub
		seg = 3;
		limit = 3;

	}

	private void initOffendThanDefend() {
		// t1_value = t1.getLatestOffendThanDefend();
		for (int i = 0; i < t1_value.length; i++) {
			t1_value[i] = i * 1.0 / (i + 1);
			labelContent_t1[i] = df.format(t1_value[i]);
		}

		// t2_value = t2.getLatestOffendThanDefend();
		for (int i = 0; i < t2_value.length; i++) {
			t2_value[i] = (i + 1) * 1.0 / (i);
			labelContent_t2[i] = df.format(t2_value[i]);
		}// TODO Auto-generated method stub
		seg = 10;
		limit = 2;

	}

	private void initTempo() {
		// t1_value = t1.getLatestTempo();
		for (int i = 0; i < t1_value.length; i++) {
			t1_value[i] = 100 - i;
			labelContent_t1[i] = df.format(t1_value[i]);
		}

		// t2_value = t2.getLatestTempo();
		for (int i = 0; i < t2_value.length; i++) {
			t2_value[i] = 100 + i;
			labelContent_t2[i] = df.format(t2_value[i]);
		}// TODO Auto-generated method stub
		seg = 12;
		limit = 120;

	}

	private void initDefend() {
		// t1_value = t1.getLatestDefend();
		for (int i = 0; i < t1_value.length; i++) {
			t1_value[i] = 100 - i;
			labelContent_t1[i] = df.format(t1_value[i]);
		}

		// t2_value = t2.getLatestDefend();
		for (int i = 0; i < t2_value.length; i++) {
			t2_value[i] = 88 + i;
			labelContent_t2[i] = df.format(t2_value[i]);
		}// TODO Auto-generated method stub
		seg = 12;
		limit = 120;
	}

	private void initOffend() {
		// t1_value = t1.getLatestOffend();
		for (int i = 0; i < t1_value.length; i++) {
			t1_value[i] = 100 - i;
			labelContent_t1[i] = df.format(t1_value[i]);
		}

		// t2_value = t2.getLatestOffend();
		for (int i = 0; i < t2_value.length; i++) {
			t2_value[i] = 100 + i;
			labelContent_t2[i] = df.format(t2_value[i]);
		}
		seg = 12;
		limit = 120;

	}

	public static void main(String[] args) {
		JFrame f = new JFrame();
		f.setBounds(0, 0, 1280, 1080);
		final BLController bl = BLController.getInstance();
		bl.init();
		while (bl.getProgress() < 9) {
			System.out.println(bl.getProgress());

		}
		Team t1 = bl.getAllTeams().get(0);
		Team t2 = bl.getAllTeams().get(1);
		LineChartPanelForTeamCompare chart = new LineChartPanelForTeamCompare(
				960, 720, "进攻", t1, t2, bl);
		chart.setBounds(100, 100, 1280, 1080);
		f.setLayout(null);
		f.add(chart);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.repaint();
	}
}
