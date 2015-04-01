package logic.players;

public class playerData { // 比赛关键数据记录
	private double points;
	private double rebounds;
	private double assists;

	public playerData(double points, double rebounds, double assists) {
		this.points = points;
		this.rebounds = rebounds;
		this.assists = assists;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}

	public double getRebounds() {
		return rebounds;
	}

	public void setRebounds(double rebounds) {
		this.rebounds = rebounds;
	}

	public double getAssists() {
		return assists;
	}

	public void setAssists(double assists) {
		this.assists = assists;
	}

}
