package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

import compare.RecordOfPlayerAssistsComp;
import compare.RecordOfPlayerPointsComp;
import compare.RecordOfPlayerReboundsComp;

/**
 * 一场比赛的所有信息，分为历史比赛信息和正在直播的比赛信息
 * */
public class Matchvo implements Serializable {

	/**
	 * 历史比赛信息：
	 * */
	/**该比赛的唯一标识符Mid*/
	private String Mid;
	/**比赛日期：例如13-14_2014-01-01 :前面表示13-14赛季，后面表示日期*/
	private String date; // 
	/**该比赛是否为季后赛*/
	private boolean isplayoff;
	/** 对阵队伍 2容量的teams数组,这里面存的是缩写，前面为主队，后面为客队！！！，*/
	private String[] teams;// 对阵队伍 2容量的teams数组,这里面存的是缩写！！！
	/** 比分情况，数组为 2容量，前面为主队，后面为客队*/
	private int[] points;// 比分 2容量
	/**该比赛每一节的比分，可能会有加时赛，size》4代表有加时赛，第5节代表加时赛1*/
	private ArrayList<int[]> pointsList;// 每一节的比分，可能会有加时赛
	/** 该比赛主场球队每个上场球员的比赛信息*/
	private ArrayList<RecordOfPlayervo> firstRecordList; // 主场记录
	/** 该比赛客场球队每个上场球员的比赛信息*/
	private ArrayList<RecordOfPlayervo> secondRecordList; // 客场记录

	/**
	 * 对该类的所有属性（非直播所用属性）进行构造
	 * */
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

	/**返回比赛日期：例如13-14_2014-01-01 :前面表示13-14赛季，后面表示日期*/
	public String getDate() {
		return date;
	}

	/**返回每场比赛的唯一标识符Mid*/
	public String[] getTeams() {
		return teams;
	}
	/** 返回比分情况，数组为 2容量，前面为主队，后面为客队*/
	public int[] getPoints() {
		return points;
	}
	/**返回该比赛每一节的比分，可能会有加时赛，size》4代表有加时赛，第5节代表加时赛1*/
	public ArrayList<int[]> getPointsList() {
		return pointsList;
	}
	/** 返回该比赛主场球队每个上场球员的比赛信息*/
	public ArrayList<RecordOfPlayervo> getFirstRecordList() {
		return firstRecordList;
	}
	/** 返回该比赛客场球队每个上场球员的比赛信息*/
	public ArrayList<RecordOfPlayervo> getSecondRecordList() {
		return secondRecordList;
	}
	/**该比赛的唯一标识符Mid*/
	public String getMid() {
		return Mid;
	}

	/**
	 * 直播专用 :MatchVO中的recordOfPlayer中的Pid为position
	 */

	/** 篮板情况，数组为 2容量，前面为主队，后面为客队*/
	private int[] rebounds;
	/** 助攻情况，数组为 2容量，前面为主队，后面为客队*/
	private int[] assists;
	/** 盖帽情况，数组为 2容量，前面为主队，后面为客队*/
	private int[] blocks;
	/** 失误情况，数组为 2容量，前面为主队，后面为客队*/
	private int[] turnOver;
	/** 快攻次数情况，数组为 2容量，前面为主队，后面为客队*/
	private int[] quickPoints; // 快攻
	/** 禁区得分情况，数组为 2容量，前面为主队，后面为客队*/
	private int[] RestrictedPoints;// 禁区得分
	/** 对手失误得分情况，数组为 2容量，前面为主队，后面为客队*/
	private int[] turnOverPoints; // 对手失误得分
	/** 最多领先分数情况，数组为 2容量，前面为主队，后面为客队*/
	private int[] MaxPoints; // 最多领先分数
	/** 投篮命中率情况，数组为 2容量，前面为主队，后面为客队*/
	private double[] fieldGoalsPercentage;
	/** 三分命中率情况，数组为 2容量，前面为主队，后面为客队*/
	private double[] threePointersPercentage;
	/** 罚球命中率情况，数组为 2容量，前面为主队，后面为客队*/
	private double[] freeThrowsPercentage;

	/**
	 * 对该类的所有属性（直播专用属性）进行构造
	 * */
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

	/** 返回该场比赛篮板情况，数组为 2容量，前面为主队，后面为客队*/
	public int[] getRebounds() {
		return rebounds;
	}

	/** 返回该场比赛助攻情况，数组为 2容量，前面为主队，后面为客队*/
	public int[] getAssists() {
		return assists;
	}

	/** 返回该场比赛盖帽情况，数组为 2容量，前面为主队，后面为客队*/
	public int[] getBlocks() {
		return blocks;
	}

	/** 返回该场比赛失误情况，数组为 2容量，前面为主队，后面为客队*/
	public int[] getTurnOver() {
		return turnOver;
	}

	/** 返回该场比赛快攻得分情况，数组为 2容量，前面为主队，后面为客队*/
	public int[] getQuickPoints() {
		return quickPoints;
	}

	/** 返回该场比赛禁区得分情况，数组为 2容量，前面为主队，后面为客队*/
	public int[] getRestrictedPoints() {
		return RestrictedPoints;
	}

	/** 返回该场比赛对手失误得分情况，数组为 2容量，前面为主队，后面为客队*/
	public int[] getTurnOverPoints() {
		return turnOverPoints;
	}

	/** 返回该场比赛最大领先分数情况，数组为 2容量，前面为主队，后面为客队*/
	public int[] getMaxPoints() {
		return MaxPoints;
	}

	/** 返回该场比赛禁区投篮命中率情况，数组为 2容量，前面为主队，后面为客队*/
	public double[] getFieldGoalsPercentage() {
		return fieldGoalsPercentage;
	}

	/** 返回该场比赛禁区三分命中率情况，数组为 2容量，前面为主队，后面为客队*/
	public double[] getThreePointersPercentage() {
		return threePointersPercentage;
	}

	/** 返回该场比赛禁区罚球命中率情况，数组为 2容量，前面为主队，后面为客队*/
	public double[] getFreeThrowsPercentage() {
		return freeThrowsPercentage;
	}

	/** 返回该场比赛是否为季后赛*/
	public boolean isIsplayoff() {
		return isplayoff;
	}

	/** 返回该场比赛的赛季数，例如13-14*/
	public String getSeason() {

		return date.split("_")[0];
	}

	/** 返回该场比赛的比赛数据王，数组容量为2，分别为主队和客队的数据王*/
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
		System.out.println("dadsdaSDa: "+nameOfPointsKing1+"  "+IdOfPointsKing1);
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
