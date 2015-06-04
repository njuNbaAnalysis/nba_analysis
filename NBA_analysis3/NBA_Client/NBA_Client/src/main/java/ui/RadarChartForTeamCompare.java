package ui;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

import logic.BLController;
import logic.teams.Team;

public class RadarChartForTeamCompare extends JPanel {
	private Team t1;
	private Team t2;
	private double[] value_a;
	private double[] value_b;
	private String[] attr = { "内线", "外线", "配合", "进攻", "得分" };
	int limit = 10;

	RadarChartForTeamCompare(int width, int height, Team t1, Team t2) {
		this.setLayout(null);
		this.t1 = t1;
		this.t2 = t2;
		this.setSize(width, height);
		init();
		RadarChart chart = new RadarChart(5, 5, width, width, value_a, value_b,
				limit, attr);
		chart.setLocation(0, 0);
		this.add(chart);
		chart.go();
	}

	private void init() {
		value_a = new double[5];
		value_b = new double[5];
		// value_a = bl.getAbility();
		// 都是假数据
		for (int i = 0; i < 5; i++) {
			value_a[i] = 2 * i % 10 + 1;
		}
		for (int i = 0; i < 5; i++) {
			value_b[i] = 3 * i % 10 + 1;
		}
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

		String[] attr = { "内线", "外线", "配合", "进攻", "得分" };
		RadarChartForTeamCompare chart = new RadarChartForTeamCompare(960, 720,
				t1, t2);
		chart.setBounds(100, 100, 1280, 1080);
		f.setLayout(null);
		f.add(chart);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.repaint();
	}
}
