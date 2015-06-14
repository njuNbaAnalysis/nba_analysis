package ui.team;

import java.awt.Color;
import java.rmi.RemoteException;

import javax.swing.JFrame;
import javax.swing.JPanel;

import BLservice.BLservice;
import ui.chart.RadarChart;
import vo.Teamvo;


public class RadarChartForTeamCompare extends JPanel {
	private Teamvo t1;
	private Teamvo t2;
	private BLservice bl;
	private String season;
	private boolean isPlayOff;
	private double[] value_a;
	private double[] value_b;
	private String[] attr = { "内线", "外线", "配合", "进攻", "防守" };
	int limit = 10;
	private double [] coefficient = {1,1,0.1,1.2,80};

	RadarChartForTeamCompare(int width, int height, Teamvo t1, Teamvo t2,BLservice bl,String season,boolean isPlayOff) {
		this.setLayout(null);
		this.t1 = t1;
		this.t2 = t2;
		this.setSize(width, height);
		this.bl = bl;
		this.season = season;
		this.isPlayOff = isPlayOff;
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
		try {
			value_a = bl.getTeamAbility(t1.getAbbreviation(), season, isPlayOff);
			for(int i=0;i<5;i++){
				if(i==4){
					value_a[i] = 10 - 10*(value_a[i]-coefficient[4])/coefficient[4];
				}else{
					value_a[i] = value_a[i]/coefficient[i];
				}
				
				
			}
			
			
			
			value_b = bl.getTeamAbility(t2.getAbbreviation(), season, isPlayOff);
			for(int i=0;i<5;i++){
				if(i==4){
					value_b[i] = 10 - 10*(value_b[i]-coefficient[4])/coefficient[4];
				}else{
					value_b[i] = value_b[i]/coefficient[i];
				}
			}
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

	/*public static void main(String[] args) {
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
	}*/
}
