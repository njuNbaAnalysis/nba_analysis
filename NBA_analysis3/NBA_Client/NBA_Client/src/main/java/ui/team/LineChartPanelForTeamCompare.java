package ui.team;

import java.awt.Color;
import java.rmi.RemoteException;
import java.text.DecimalFormat;

import javax.swing.JFrame;
import javax.swing.JPanel;

import BLservice.BLservice;
import ui.chart.LineChart;
import vo.Teamvo;

public class LineChartPanelForTeamCompare extends JPanel {
	private int width;
	private int height;
	private String[] x_name = new String[10];
	private String[] y_name = null;
	private double[] t1_value = new double[10];
	private double[] t2_value = new double[10];
	private String[] labelContent_t1 = new String[10];
	private String[] labelContent_t2 = new String[10];
	private Teamvo t1;
	private Teamvo t2;
	private BLservice bl;
	private String season;
	private boolean isPlayOff;
	private int seg;
	private int limit;
	private DecimalFormat df = new DecimalFormat("#0.0");
	
	private Color a_color;
	private Color b_color;
	LineChartPanelForTeamCompare(int width, int height, String type, Teamvo t1,
			Teamvo t2, Color a_color,Color b_color,BLservice bl,String season,boolean isPlayOff) throws RemoteException {
		this.width = width;
		this.height = height;
		this.bl = bl;
		System.out.println(t1.getAbbreviation()+" "+season+" "+isPlayOff);
		this.t1 = bl.getTeamWithLatest10Data(t1.getAbbreviation(), season, isPlayOff);
		//System.out.println("1:"+t1.getLatestDefend()[0]+" "+t1.getLatestDefend()[1]);
		System.out.println(t2.getAbbreviation()+" "+season+" "+isPlayOff);
		this.t2 = bl.getTeamWithLatest10Data(t2.getAbbreviation(), season, isPlayOff);
		//System.out.println("2:"+t2.getLatestDefend()[0]+" "+t2.getLatestDefend()[1]);
		this.setSize(width, height);
		this.a_color = a_color;
		this.b_color = b_color;
		this.season = season;
		this.isPlayOff = isPlayOff;
		this.setLayout(null);
		init(type);
		LineChart chart = new LineChart(x_name.length, seg, width, height,
				limit, x_name, y_name, t1_value, t2_value, labelContent_t1,
				labelContent_t2,a_color,b_color);
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
		
		boolean[] t_value = t1.getLatestWinOrLose();
		for(int i=0;i<t_value.length;i++){
			if(t_value[i]){
				t1_value[i] = 2;
			}else{
				t1_value[i] = 1;
			}
			
		}
		
		t_value = t2.getLatestWinOrLose();
		for(int i=0;i<t_value.length;i++){
			if(t_value[i]){
				t2_value[i] = 2;
			}else{
				t2_value[i] = 1;
			}
			
		}
		
		
		//bl.getLatestPoints(t1,t2);
		// labelContent_t1 = t1.getLatestPoints(t1,t2);
		for (int i = 0; i < t1_value.length; i++) {
			t1_value[i] = (i + 1) % 2 + 1;
			labelContent_t1[i] = "101-100";
		}

		// t2_value = t1.getLatestWinOrLose();
		// labelContent_t2 = t2.getLatestPoints(t1,t2);
		for (int i = 0; i < t2_value.length; i++) {
			t2_value[i] = (i) % 2;
			labelContent_t2[i] = "101-100";
		}// TODO Auto-generated method stub
		seg = 3;
		limit = 3;

	}

	private void initStandinds() {
		y_name = new String[4];
		y_name[0] = "";
		y_name[1] = "lose";
		y_name[2] = "win";
		y_name[3] = "";
		//t1_value = t1.getLatestWinOrLose();
		// labelContent_t1 = t1.getLatestPoints(t1,t2);
	//	 t1.getLatestPoints(t1,t2);
		boolean[] t_value = t1.getLatestWinOrLose();
		for(int i=0;i<t_value.length;i++){
			if(t_value[i]){
				t1_value[i] = 2;
			}else{
				t1_value[i] = 1;
			}
			
		}
		
		t_value = t2.getLatestWinOrLose();
		for(int i=0;i<t_value.length;i++){
			if(t_value[i]){
				t2_value[i] = 2;
			}else{
				t2_value[i] = 1;
			}
			
		}
		
		labelContent_t1 = t1.getLatestRecord();
		labelContent_t2 = t2.getLatestRecord();
		
		seg = 3;
		limit = 3;

	}

	private void initOffendThanDefend() {
		t1_value = t1.getLatestOffendThanDefend();
		
		for(int i=0;i<t1_value.length;i++){
			System.out.println(t1_value[i]);
			labelContent_t1[i] = df.format(t1_value[i]);
		}
		
		t2_value = t2.getLatestOffendThanDefend();
		for(int i=0;i<t2_value.length;i++){
			System.out.println(t2_value[i]);
			labelContent_t2[i] = df.format(t2_value[i]);
		}
		
		seg = 10;
		limit = 2;

	}

	private void initTempo() {
		t1_value = t1.getLatestTempo();
		for (int i = 0; i < t1_value.length; i++) {

			labelContent_t1[i] = df.format(t1_value[i]);
		}

		t2_value = t2.getLatestTempo();
		for (int i = 0; i < t2_value.length; i++) {
			labelContent_t2[i] = df.format(t2_value[i]);
		}// TODO Auto-generated method stub
		seg = 12;
		limit = 120;

	}

	private void initDefend() {
		t1_value = t1.getLatestDefend();
		for (int i = 0; i < t1_value.length; i++) {
			t1_value[i] = t1_value[i];
			labelContent_t1[i] = df.format(t1_value[i]);
			System.out.println("Defend t1_value:"+t1_value[i]);
		}

		t2_value = t2.getLatestDefend();
		for (int i = 0; i < t2_value.length; i++) {
			t2_value[i] = t2_value[i];
			labelContent_t2[i] = df.format(t2_value[i]);
			System.out.println("Defend t2_value:"+t2_value[i]);
		}// TODO Auto-generated method stub
		seg = 10;
		limit = 200;
	}

	private void initOffend() {
		t1_value = t1.getLatestOffend();
		
		for (int i = 0; i < t1_value.length; i++) {
			t1_value[i] = t1_value[i];
			System.out.println(t1_value[i] );
			labelContent_t1[i] = df.format(t1_value[i]);

		}

		t2_value = t2.getLatestOffend();
		for (int i = 0; i < t2_value.length; i++) {
			t2_value[i] = t2_value[i];
			System.out.println(t2_value[i] );
			labelContent_t2[i] = df.format(t2_value[i]);
		}
		seg = 10;
		limit = 200;

	}

/*	public static void main(String[] args) {
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
				960, 720, "进攻", t1, t2, new Color(221, 61, 66),new Color(6, 74, 150),bl);
		chart.setBounds(100, 100, 1280, 1080);
		f.setLayout(null);
		f.add(chart);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		f.repaint();
	}*/
}
