package logic.matches;

import java.util.ArrayList;
import java.util.Date;

import data.matches.MatchMistake;

public class Match {
	private Date date;
	private String[] teams;// 对阵队伍 2容量的teams数组
	private int[] points;// 比分 2容量
	private ArrayList<int[]> pointsList;// 每一节的比分，可能会有加时赛

	private ArrayList<RecordOfPlayer> firstRecordList; // 主场记录
	private ArrayList<RecordOfPlayer> secondRecordList; // 客场记录

	private ArrayList<MatchMistake> ListOfMistake; // 记录数据错误；如果没有错误，则为null

	public Match(Date date, String[] teams, int[] points,
			ArrayList<int[]> pointsList,
			ArrayList<RecordOfPlayer> firstRecordList,
			ArrayList<RecordOfPlayer> secondRecordList,
			ArrayList<MatchMistake> ListOfMistake) {
		super();
		this.date = date;
		this.teams = teams;
		this.points = points;
		this.pointsList = pointsList;
		this.firstRecordList = firstRecordList;
		this.secondRecordList = secondRecordList;
		this.ListOfMistake = ListOfMistake;
	}

	public Date getDate() {
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

	public ArrayList<RecordOfPlayer> getFirstRecordList() {
		return firstRecordList;
	}

	public ArrayList<RecordOfPlayer> getSecondRecordList() {
		return secondRecordList;
	}

}
