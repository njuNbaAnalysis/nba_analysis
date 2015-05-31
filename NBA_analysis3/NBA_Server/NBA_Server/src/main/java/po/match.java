package po;

import java.util.ArrayList;


public class match {
	private String Mid;
	private String date;
	private String season;
	private String home_team;
	private String away_team;
	private boolean isplayoff;
	private int home_points;
	private int away_points;
	private ArrayList<matchItem> matchItemList = new ArrayList<matchItem>();       //记录每场比赛中每个人的具体信息
	private ArrayList<pointsItem> pointsItemList = new ArrayList<pointsItem>();			//记录每场比赛的每节得分
	private pointsItem pointsItem;

	public match(String Mid, String date, String season, String home_team,
			String away_team, boolean isplayoff, int home_points,
			int away_points) {
		this.Mid = Mid;
		this.date = date;
		this.season = season;
		this.home_team = home_team;
		this.away_team = away_team;
		this.isplayoff = isplayoff;
		this.home_points = home_points;
		this.away_points = away_points;
	}

	public String getMid() {
		return Mid;
	}

	public void setMid(String mid) {
		Mid = mid;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getHome_team() {
		return home_team;
	}

	public void setHome_team(String home_team) {
		this.home_team = home_team;
	}

	public String getAway_team() {
		return away_team;
	}

	public void setAway_team(String away_team) {
		this.away_team = away_team;
	}

	public boolean isIsplayoff() {
		return isplayoff;
	}

	public void setIsplayoff(boolean isplayoff) {
		this.isplayoff = isplayoff;
	}

	public int getHome_points() {
		return home_points;
	}

	public void setHome_points(int home_points) {
		this.home_points = home_points;
	}

	public int getAway_points() {
		return away_points;
	}

	public void setAway_points(int away_points) {
		this.away_points = away_points;
	}

	
	public void setMatchItemList(ArrayList<matchItem> matchItemList) {
		this.matchItemList = matchItemList;
	}

	public ArrayList<matchItem> getMatchItemList() {
		return matchItemList;
	}

	public ArrayList<pointsItem> getPointsItemList() {
		return pointsItemList;
	}

	public void setPointsItemList(ArrayList<pointsItem> pointsItemList) {
		this.pointsItemList = pointsItemList;
	}

	public pointsItem getPointsItem() {
		return pointsItem;
	}

	public void setPointsItem(pointsItem pointsItem) {
		this.pointsItem = pointsItem;
	}
	
	
}
