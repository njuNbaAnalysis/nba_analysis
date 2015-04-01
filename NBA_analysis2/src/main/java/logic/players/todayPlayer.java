package logic.players;

import test.data.PlayerKingInfo;

public class todayPlayer {
	String name;
	String team;
	String position;
	double averagePoints;
	double averageRebounds;
	double averageAssists;

	public todayPlayer(String name, String team, String position,
			int Points, int Rebounds, int Assists) {
		this.name = name;
		this.team = team;
		this.position = position;
		this.averageAssists = Assists;
		this.averagePoints = Points;
		this.averageRebounds = Rebounds;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public double getAveragePoints() {
		return averagePoints;
	}

	public void setAveragePoints(double averagePoints) {
		this.averagePoints = averagePoints;
	}

	public double getAverageRebounds() {
		return averageRebounds;
	}

	public void setAverageRebounds(double averageRebounds) {
		this.averageRebounds = averageRebounds;
	}

	public double getAverageAssists() {
		return averageAssists;
	}

	public void setAverageAssists(double averageAssists) {
		this.averageAssists = averageAssists;
	}

	public PlayerKingInfo getKingInfo(String field) {
		// 默认average
		PlayerKingInfo info = new PlayerKingInfo();

		info.setField(field);
		switch (field) {
		case "point":
		case "score": // 热门球队处为score，sort处
			info.setValue(this.getAveragePoints());
			break;
		case "rebound":
			info.setValue(this.getAverageRebounds());
			break;
		case "assist":
			info.setValue(this.getAverageAssists());
			break;
		default:
			System.out.println("error in TodayPlayer.getHotInfo: " + field);
		}

		info.setName(name);
		info.setTeamName(team);
		info.setPosition(position);
		return info;
	}
}
