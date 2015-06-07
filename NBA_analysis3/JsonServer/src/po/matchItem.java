package po;

public class matchItem {
	private String Mid;
	private boolean ishome;
	private String Pid;
	private boolean isstart;
	private double time;
	private double fieldGoalspercent;
	private int fieldGoalsAttempt;
	private int fieldGoalsHit;
	private double threepointpercent;
	private int threepointAttempt;
	private int threepointHit;
	private double freethrowpercent;
	private int freethrowAttempt;
	private int freethrowHit;
	private double trueshootingpercent;
	private int rebounds;
	private int offenseRebounds;
	private int defenseRebounds;
	private int assists;
	private int steals;
	private int blockShots;
	private int turnOver;
	private int fouls;
	private int points;

	public matchItem(String Mid, boolean ishome, String Pid, boolean isstart,
			double time, double fieldGoalspercent, int fieldGoalsAttempt,
			int fieldGoalsHit, double threepointpercent, int threepointAttempt,
			int threepointHit, double freethrowpercent, int freethrowAttempt,
			int freethrowHit, double trueshootingpercent, int rebounds,
			int offenseRebounds, int defenseRebounds, int assists, int steals,
			int blockShots, int turnOver, int fouls, int points) {
		this.Mid = Mid;
		this.ishome = ishome;
		this.Pid = Pid;
		this.isstart = isstart;
		this.time = time;
		this.fieldGoalspercent = fieldGoalspercent;
		this.fieldGoalsAttempt = fieldGoalsAttempt;
		this.fieldGoalsHit = fieldGoalsHit;
		this.threepointpercent = threepointpercent;
		this.threepointAttempt = threepointAttempt;
		this.threepointHit = threepointHit;
		this.freethrowpercent = freethrowpercent;
		this.freethrowAttempt = freethrowAttempt;
		this.freethrowHit = freethrowHit;
		this.trueshootingpercent = trueshootingpercent;
		this.rebounds = rebounds;
		this.offenseRebounds = offenseRebounds;
		this.defenseRebounds = defenseRebounds;
		this.assists = assists;
		this.steals = steals;
		this.blockShots = blockShots;
		this.turnOver = turnOver;
		this.fouls = fouls;
		this.points = points;
	}
	
	
	public String getMid() {
		return Mid;
	}

	public void setMid(String mid) {
		Mid = mid;
	}

	public boolean isIshome() {
		return ishome;
	}

	public void setIshome(boolean ishome) {
		this.ishome = ishome;
	}

	public String getPid() {
		return Pid;
	}

	public void setPid(String pid) {
		Pid = pid;
	}

	public boolean isIsstart() {
		return isstart;
	}

	public void setIsstart(boolean isstart) {
		this.isstart = isstart;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public double getFieldGoalspercent() {
		return fieldGoalspercent;
	}

	public void setFieldGoalspercent(double fieldGoalspercent) {
		this.fieldGoalspercent = fieldGoalspercent;
	}

	public int getFieldGoalsAttempt() {
		return fieldGoalsAttempt;
	}

	public void setFieldGoalsAttempt(int fieldGoalsAttempt) {
		this.fieldGoalsAttempt = fieldGoalsAttempt;
	}

	public int getFieldGoalsHit() {
		return fieldGoalsHit;
	}

	public void setFieldGoalsHit(int fieldGoalsHit) {
		this.fieldGoalsHit = fieldGoalsHit;
	}

	public double getThreepointpercent() {
		return threepointpercent;
	}

	public void setThreepointpercent(double threepointpercent) {
		this.threepointpercent = threepointpercent;
	}

	public int getThreepointAttempt() {
		return threepointAttempt;
	}

	public void setThreepointAttempt(int threepointAttempt) {
		this.threepointAttempt = threepointAttempt;
	}

	public int getThreepointHit() {
		return threepointHit;
	}

	public void setThreepointHit(int threepointHit) {
		this.threepointHit = threepointHit;
	}

	public double getFreethrowpercent() {
		return freethrowpercent;
	}

	public void setFreethrowpercent(double freethrowpercent) {
		this.freethrowpercent = freethrowpercent;
	}

	public int getFreethrowAttempt() {
		return freethrowAttempt;
	}

	public void setFreethrowAttempt(int freethrowAttempt) {
		this.freethrowAttempt = freethrowAttempt;
	}

	public int getFreethrowHit() {
		return freethrowHit;
	}

	public void setFreethrowHit(int freethrowHit) {
		this.freethrowHit = freethrowHit;
	}

	public double getTrueshootingpercent() {
		return trueshootingpercent;
	}

	public void setTrueshootingpercent(double trueshootingpercent) {
		this.trueshootingpercent = trueshootingpercent;
	}

	public int getRebounds() {
		return rebounds;
	}

	public void setRebounds(int rebounds) {
		this.rebounds = rebounds;
	}

	public int getOffenseRebounds() {
		return offenseRebounds;
	}

	public void setOffenseRebounds(int offenseRebounds) {
		this.offenseRebounds = offenseRebounds;
	}

	public int getDefenseRebounds() {
		return defenseRebounds;
	}

	public void setDefenseRebounds(int defenseRebounds) {
		this.defenseRebounds = defenseRebounds;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getSteals() {
		return steals;
	}

	public void setSteals(int steals) {
		this.steals = steals;
	}

	public int getBlockShots() {
		return blockShots;
	}

	public void setBlockShots(int blockShots) {
		this.blockShots = blockShots;
	}

	public int getTurnOver() {
		return turnOver;
	}

	public void setTurnOver(int turnOver) {
		this.turnOver = turnOver;
	}

	public int getFouls() {
		return fouls;
	}

	public void setFouls(int fouls) {
		this.fouls = fouls;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

}
