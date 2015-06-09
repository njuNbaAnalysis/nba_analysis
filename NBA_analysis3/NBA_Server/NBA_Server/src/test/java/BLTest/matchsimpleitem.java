package BLTest;

public class matchsimpleitem {
	private int mid;
	private String home_team;
	private String away_team;
	private int points; // 分差：主队减去客队

	public matchsimpleitem(int mid, String home_team, String away_team,
			int points) {
		super();
		this.mid = mid;
		this.home_team = home_team;
		this.away_team = away_team;
		this.points = points;
	}

	public int getMid() {
		return mid;
	}

	public String getHome_team() {
		return home_team;
	}

	public String getAway_team() {
		return away_team;
	}

	public int getPoints() {
		return points;
	}

}