package ui;

import javax.swing.JPanel;

import logic.teams.Team;

public class RadarChartForTeamCompare extends JPanel{
	private Team t1;
	private Team t2;
	private double [] value_a;
	private double [] value_b;
	private String[] attr ={"内线","外线","配合","进攻","得分"};
	int limit = 10;
	RadarChartForTeamCompare(int width,int height,Team t1,Team t2){
		this.setLayout(null);
		this.t1 = t1;
		this.t2 = t2;
		init();
		RadarChart chart = new RadarChart(5, 5, 1280, 1080, value_a, value_b, limit,attr);
		chart.setBounds(0, 0, 1280, 1080);
	}
	private void init() {
		value_a = new double [5];
		value_b = new double [5];
		//value_a = bl.getAbility();
		//都是假数据
		for(int i=0;i<5;i++){
			value_a[i] = 2*i%10;
		}
		for(int i=0;i<5;i++){
			value_b[i] = 3*i%10;
		}
	}
}
