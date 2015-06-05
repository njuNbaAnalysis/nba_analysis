package po;

public class pointsItem {
	private String Mid;
	private int period; // 节数，第几节
	private int home_points; // 主场得分
	private int away_points; // 客场得分

	public pointsItem(String mid, int period, int home_points, int away_points) {
		super();
		Mid = mid;
		this.period = period;
		this.home_points = home_points;
		this.away_points = away_points;
	}

	public String getMid() {
		return Mid;
	}

	public void setMid(String mid) {
		Mid = mid;
	}

	public int getPeriod() {
		return period;
	}

	public void setPeriod(int period) {
		this.period = period;
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

	public int[] getPoints() {
		int[] result = { home_points, away_points };
		return result;
	}
}
