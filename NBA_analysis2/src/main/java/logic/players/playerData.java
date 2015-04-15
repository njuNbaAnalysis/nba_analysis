package logic.players;

public class playerData { // 比赛关键数据记录
	private String Date;
	private String enemy;  //对手
	private double points;
	private double rebounds;
	private double assists;
	private double block;
	private double steal;

	public playerData(double points, double rebounds, double assists,double steal,double block,String date,String enemy) {
		this.points = points;
		this.rebounds = rebounds;
		this.assists = assists;
		this.steal = steal;
		this.block = block;
		this.Date = date;
		this.enemy = enemy;
	}

	public String getDate() {
		return Date;
	}

	public void setDate(String date) {
		Date = date;
	}

	public String getEnemy() {
		return enemy;
	}

	public void setEnemy(String enemy) {
		this.enemy = enemy;
	}

	public double getBlock() {
		return block;
	}

	public void setBlock(double block) {
		this.block = block;
	}

	public double getSteal() {
		return steal;
	}

	public void setSteal(double steal) {
		this.steal = steal;
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
