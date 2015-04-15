package logic.players;

import java.awt.Image;

import test.data.PlayerKingInfo;

public class todayPlayer {
	String name;
	String team;
	String position;
	private int Points;
	private int Rebounds;
	private int Assists;
	private int steals;// 总抢断数
	private int blockShots;// 总盖帽数

	public todayPlayer(String name, String team, String position,
			int Points, int Rebounds, int Assists,int steals,int blockShots) {
		this.name = name;
		this.team = team;
		this.position = position;
		this.Assists = Assists;
		this.Points = Points;
		this.Rebounds = Rebounds;
		this.steals = steals;
		this.blockShots = blockShots;
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

	

	public int getPoints() {
		return Points;
	}

	public int getRebounds() {
		return Rebounds;
	}

	public int getAssists() {
		return Assists;
	}

	public int getSteals() {
		return steals;
	}

	public int getBlockShots() {
		return blockShots;
	}

	public PlayerKingInfo getKingInfo(String field) {
		// 默认average
		PlayerKingInfo info = new PlayerKingInfo();

		info.setField(field);
		switch (field) {
		case "point":
		case "score": // 热门球队处为score，sort处
			info.setValue(this.getPoints());
			break;
		case "rebound":
			info.setValue(this.getRebounds());
			break;
		case "assist":
			info.setValue(this.getAssists());
			break;
		case "blockShot":
			info.setValue(this.getBlockShots());
			break;
		case "steal":
			info.setValue(this.getSteals());
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
