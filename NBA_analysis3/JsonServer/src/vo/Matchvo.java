package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import compare.RecordOfPlayerAssistsComp;
import compare.RecordOfPlayerPointsComp;
import compare.RecordOfPlayerReboundsComp;

public class Matchvo implements Serializable {

	private String Mid;
	private String date; // 例如13-14_2014-01-01 :前面表示13-14赛季，后面表示日期
	private boolean isplayoff;
	private String[] teams;// 对阵队伍 2容量的teams数组,这里面存的是缩写！！！
	private int[] points;// 比分 2容量
	private ArrayList<int[]> pointsList;// 每一节的比分，可能会有加时赛
	private ArrayList<RecordOfPlayervo> firstRecordList; // 主场记录
	private ArrayList<RecordOfPlayervo> secondRecordList; // 客场记录

	public Matchvo(String Mid, String date, boolean isplayoff, String[] teams,
			int[] points, ArrayList<int[]> pointsList,
			ArrayList<RecordOfPlayervo> firstRecordList,
			ArrayList<RecordOfPlayervo> secondRecordList) {
		super();
		this.Mid = Mid;
		this.date = date;
		this.isplayoff = isplayoff;
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

	public String getMid() {
		return Mid;
	}

	/*
	 * 直播专用 :MatchVO中的recordOfPlayer中的Pid为position
	 */

	private int[] rebounds;
	private int[] assists;
	private int[] blocks;
	private int[] turnOver;
	private int[] quickPoints; // 快攻
	private int[] RestrictedPoints;// 禁区得分
	private int[] turnOverPoints; // 对手失误得分
	private int[] MaxPoints; // 最多领先分数
	private double[] fieldGoalsPercentage;
	private double[] threePointersPercentage;
	private double[] freeThrowsPercentage;

	public Matchvo(String date, boolean isplayoff, String[] teams,
			int[] points, ArrayList<int[]> pointsList,
			ArrayList<RecordOfPlayervo> firstRecordList,
			ArrayList<RecordOfPlayervo> secondRecordList, int[] rebounds,
			int[] assists, int[] blocks, int[] turnOver, int[] quickPoints,
			int[] restrictedPoints, int[] turnOverPoints, int[] maxPoints,
			double[] fieldGoalsPercentage, double[] threePointersPercentage,
			double[] freeThrowsPercentage) {
		super();
		this.date = date;
		this.teams = teams;
		this.points = points;
		this.pointsList = pointsList;
		this.firstRecordList = firstRecordList;
		this.secondRecordList = secondRecordList;
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
		this.isplayoff = isplayoff;
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

	public boolean isIsplayoff() {
		return isplayoff;
	}

	public String getSeason() {

		return date.split("_")[0];
	}

	public KingsOfMatchvo[] getKingsOfMatch() {
		// TODO 自动生成的方法存根
		Collections.sort(firstRecordList, new RecordOfPlayerPointsComp());
		String nameOfPointsKing1 = firstRecordList.get(0).getPlayerName();
		String IdOfPointsKing1 = firstRecordList.get(0).getPid();
		int points1 = firstRecordList.get(0).getPoints();
		Collections.sort(firstRecordList, new RecordOfPlayerAssistsComp());
		String nameOfAssistsKing1 = firstRecordList.get(0).getPlayerName();
		String IdOfAssistsKing1 = firstRecordList.get(0).getPid();
		int assists1 = firstRecordList.get(0).getAssists();
		Collections.sort(firstRecordList, new RecordOfPlayerReboundsComp());
		String nameOfReboundsKing1 = firstRecordList.get(0).getPlayerName();
		String IdOfReboundsKing1 = firstRecordList.get(0).getPid();
		int rebounds1 = firstRecordList.get(0).getRebounds();
		Collections.sort(firstRecordList, new RecordOfPlayerPointsComp());
		String nameOfPointsKing2 = secondRecordList.get(0).getPlayerName();
		String IdOfPointsKing2 = secondRecordList.get(0).getPid();
		int points2 = secondRecordList.get(0).getPoints();
		Collections.sort(secondRecordList, new RecordOfPlayerAssistsComp());
		String nameOfAssistsKing2 = secondRecordList.get(0).getPlayerName();
		String IdOfAssistsKing2 = secondRecordList.get(0).getPid();
		int assists2 = secondRecordList.get(0).getAssists();
		Collections.sort(secondRecordList, new RecordOfPlayerReboundsComp());
		String nameOfReboundsKing2 = secondRecordList.get(0).getPlayerName();
		String IdOfReboundsKing2 = firstRecordList.get(0).getPid();
		int rebounds2 = secondRecordList.get(0).getRebounds();
		KingsOfMatchvo[] result = {
				new KingsOfMatchvo(nameOfPointsKing1, IdOfPointsKing1, points1,
						nameOfReboundsKing1, IdOfReboundsKing1, rebounds1,
						nameOfAssistsKing1, IdOfAssistsKing1, assists1),
				new KingsOfMatchvo(nameOfPointsKing2, IdOfPointsKing2, points2,
						nameOfReboundsKing2, IdOfReboundsKing2, rebounds2,
						nameOfAssistsKing2, IdOfAssistsKing2, assists2) };
		return result;
	}
}