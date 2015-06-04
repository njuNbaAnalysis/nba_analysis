package logic.matches;

import java.util.ArrayList;

public class NBALiveMatch {
	
	private int[] rebounds; 
	private int[] assists;
	private int[] blocks;
	private int[] turnOver;
	private int[] quickPoints; 		//快攻
	private int[] RestrictedPoints;//禁区得分
	private int[] turnOverPoints;	//对手失误得分
	private int[] MaxPoints;		//最多领先分数
	private double[] fieldGoalsPercentage;
	private double[] threePointersPercentage;
	private double[] freeThrowsPercentage;
	private String date;    //例如13-14_2014-01-01 :前面表示13-14赛季，后面表示日期 
	private String[] teams;// 对阵队伍 2容量的teams数组,这里面存的是缩写！！！
	private int[] points;// 比分 2容量
	private ArrayList<int[]> pointsList;// 每一节的比分，可能会有加时赛
	public NBALiveMatch(int[] rebounds, int[] assists, int[] blocks,
			int[] turnOver, int[] quickPoints, int[] restrictedPoints,
			int[] turnOverPoints, int[] maxPoints,
			double[] fieldGoalsPercentage, double[] threePointersPercentage,
			double[] freeThrowsPercentage, String date, String[] teams,
			int[] points, ArrayList<int[]> pointsList) {
		super();
		this.rebounds = rebounds;
		this.assists = assists;
		this.blocks = blocks;
		this.turnOver = turnOver;
		this.quickPoints = quickPoints;
		RestrictedPoints = restrictedPoints;
		this.turnOverPoints = turnOverPoints;
		MaxPoints = maxPoints;
		this.fieldGoalsPercentage = fieldGoalsPercentage;
		this.threePointersPercentage = threePointersPercentage;
		this.freeThrowsPercentage = freeThrowsPercentage;
		this.date = date;
		this.teams = teams;
		this.points = points;
		this.pointsList = pointsList;
	}
	public int[] getRebounds() {
		return rebounds;
	}
	public int[] getAssists() {
		return assists;
	}
	public int[] getBlocks() {
		return blocks;
	}
	public int[] getTurnOver() {
		return turnOver;
	}
	public int[] getQuickPoints() {
		return quickPoints;
	}
	public int[] getRestrictedPoints() {
		return RestrictedPoints;
	}
	public int[] getTurnOverPoints() {
		return turnOverPoints;
	}
	public int[] getMaxPoints() {
		return MaxPoints;
	}
	public double[] getFieldGoalsPercentage() {
		return fieldGoalsPercentage;
	}
	public double[] getThreePointersPercentage() {
		return threePointersPercentage;
	}
	public double[] getFreeThrowsPercentage() {
		return freeThrowsPercentage;
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
	
	
}
