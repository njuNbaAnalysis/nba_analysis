package vo;

import java.util.ArrayList;

public class Matchvo {
	private String date;    //例如13-14_2014-01-01 :前面表示13-14赛季，后面表示日期 
	private String[] teams;// 对阵队伍 2容量的teams数组,这里面存的是缩写！！！
	private int[] points;// 比分 2容量
	private ArrayList<int[]> pointsList;// 每一节的比分，可能会有加时赛
	private ArrayList<RecordOfPlayervo> firstRecordList; // 主场记录
	private ArrayList<RecordOfPlayervo> secondRecordList; // 客场记录
	
	public Matchvo(String date, String[] teams, int[] points,
			ArrayList<int[]> pointsList,
			ArrayList<RecordOfPlayervo> firstRecordList,
			ArrayList<RecordOfPlayervo> secondRecordList) {
		super();
		this.date = date;
		this.teams = teams;
		this.points = points;
		this.pointsList = pointsList;
		this.firstRecordList = firstRecordList;
		this.secondRecordList = secondRecordList;
	}

	public String getDate() {
		return date;
	}

	public String[] getTeams() {
		return teams;
	}

	public int[] getPoints() {
		return points;
	}

	public ArrayList<int[]> getPointsList() {
		return pointsList;
	}

	public ArrayList<RecordOfPlayervo> getFirstRecordList() {
		return firstRecordList;
	}

	public ArrayList<RecordOfPlayervo> getSecondRecordList() {
		return secondRecordList;
	}
	

}
