package vo;

import java.io.Serializable;

/**
 *用于记录每一场比赛，每一只球队中某个球员在该比赛中的表现 
 * */
public class RecordOfPlayervo implements Serializable {
	/**该球员的姓名*/
	private String playerName;
	/**该球员的唯一标识符Pid*/
	private String Pid;
	/**该球员的在场时间，以秒为单位*/
	private double minutes; // 在场时间，以秒为单位
	/**该球员的投篮命中数*/
	private int fieldGoalHits; // 投篮命中数
	/**该球员的投篮出手数*/
	private int fieldGoalAttempts;// 投篮出手数
	/**该球员的三分球命中数*/
	private int threePointHits;// 三分球命中数
	/**该球员的三分球出手数*/
	private int threePointAttemps;// 三分球出手数
	/**该球员的罚球命中数*/
	private int freeThrowHits;// 罚球命中数
	/**该球员的罚球出手数*/
	private int freeThrowAttemps;// 罚球出手数
	/**该球员的进攻篮板数*/
	private int offensiveRebounds;// 进攻篮板数
	/**该球员的防守篮板数*/
	private int defensiveRebounds;// 防守篮板数
	/**该球员的总篮板数*/
	private int rebounds;// 总篮板数
	/**该球员的总助攻数*/
	private int assists;// 助攻数
	/**该球员的总抢断数*/
	private int steals;// 抢断数
	/**该球员的总盖帽数*/
	private int blocks;// 盖帽数
	/**该球员的总 失误数*/
	private int turnOver;// 失误数
	/**该球员的总 犯规数*/
	private int fauls;// 犯规数
	/**该球员的个人得分*/
	private int points;// 个人得分
	/**该球员的是否首发上场*/
	private boolean isStarted; // 是否是首发出场
	/**该球员的三分命中率*/
	private double threePointersPercentage; // 三分命中率
	/**该球员的罚球命中率*/
	private double freeThrowsPercentage; // 罚球命中率
	/**该球员的投篮命中率*/
	private double fieldGoalsPercentage; // 投篮命中率
	/**对应比赛的Mid*/
	private String Mid;
	/**对应比赛的date*/
	private String date;
	/**对应比赛的对手对位*/
	private String away_Team;

	/**
	 * 对该类的所有属性进行构造
	 * */
	public RecordOfPlayervo(String playerName, String Pid, double minutes,
			int fieldGoalHits, int fieldGoalAttempts, int threePointHits,
			int threePointAttemps, int freeThrowHits, int freeThrowAttemps,
			int offensiveRebounds, int defensiveRebounds, int rebounds,
			int assists, int steals, int blocks, int turnOver, int fauls,
			int points, boolean isStarted, double threePointersPercentage,
			double freeThrowsPercentage, double fieldGoalsPercentage) {
		super();
		this.playerName = playerName;
		this.Pid = Pid;
		this.minutes = minutes;
		this.fieldGoalHits = fieldGoalHits;
		this.fieldGoalAttempts = fieldGoalAttempts;
		this.threePointHits = threePointHits;
		this.threePointAttemps = threePointAttemps;
		this.freeThrowHits = freeThrowHits;
		this.freeThrowAttemps = freeThrowAttemps;
		this.offensiveRebounds = offensiveRebounds;
		this.defensiveRebounds = defensiveRebounds;
		this.rebounds = rebounds;
		this.assists = assists;
		this.steals = steals;
		this.blocks = blocks;
		this.turnOver = turnOver;
		this.fauls = fauls;
		this.points = points;
		this.isStarted = isStarted;
		this.threePointersPercentage = threePointersPercentage;
		this.freeThrowsPercentage = freeThrowsPercentage;
		this.fieldGoalsPercentage = fieldGoalsPercentage;
	}

	/**返回该球员的姓名*/
	public String getPlayerName() {
		return playerName;
	}

	/**返回该球员的唯一标识符Pid*/
	public String getPid() {
		return Pid;
	}

	/**返回该球员的在场时间，以秒为单位*/
	public double getMinutes() {
		return minutes;
	}

	/**返回该球员的投篮命中数*/
	public int getFieldGoalHits() {
		return fieldGoalHits;
	}

	/**返回该球员的投篮出手数*/
	public int getFieldGoalAttempts() {
		return fieldGoalAttempts;
	}

	/**返回该球员的三分球命中数*/
	public int getThreePointHits() {
		return threePointHits;
	}

	/**返回该球员的三分球出手数*/
	public int getThreePointAttemps() {
		return threePointAttemps;
	}

	/**返回该球员的罚球球命中数*/
	public int getFreeThrowHits() {
		return freeThrowHits;
	}

	/**返回该球员的罚球出手数*/
	public int getFreeThrowAttemps() {
		return freeThrowAttemps;
	}

	/**返回该球员的进攻篮板数*/
	public int getOffensiveRebounds() {
		return offensiveRebounds;
	}

	/**返回该球员的防守篮板数*/
	public int getDefensiveRebounds() {
		return defensiveRebounds;
	}

	/**返回该球员的篮板数*/
	public int getRebounds() {
		return rebounds;
	}

	/**返回该球员的助攻数*/
	public int getAssists() {
		return assists;
	}

	/**返回该球员的抢断数*/
	public int getSteals() {
		return steals;
	}

	/**返回该球员的盖帽数*/
	public int getBlocks() {
		return blocks;
	}

	/**返回该球员的失误数*/
	public int getTurnOver() {
		return turnOver;
	}

	/**返回该球员的犯规数*/
	public int getFauls() {
		return fauls;
	}

	/**返回该球员的得分数*/
	public int getPoints() {
		return points;
	}

	/**返回该球员是否为首发*/
	public boolean isStarted() {
		return isStarted;
	}

	/**返回该球员三分命中率*/
	public double getThreePointPercentage() {
		return threePointersPercentage;
	}

	/**返回该球员罚球命中率*/
	public double getFreeThrowPercentage() {
		return freeThrowsPercentage;
	}

	/**返回该球员投篮命中率*/
	public double getFieldGoalPercentage() {
		return fieldGoalsPercentage;
	}

	/**返回该球员的效率值*/
	public double getEfficiency() {
		double efficiency = (rebounds + points + blocks + steals + assists)
				- (fieldGoalAttempts - fieldGoalHits)
				- (freeThrowAttemps - freeThrowHits) - turnOver; // (得分+篮板+助攻+抢断+盖帽)-（出手次数-命中次数）-（罚球次数-罚球命中次数）-失误次数
		return efficiency;
	}

	/**返回该比赛唯一标识符Mid*/
	public String getMid() {
		return Mid;
	}

	public void setMid(String mid) {
		Mid = mid;
	}

	/**返回该比赛的日期*/
	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	/**返回该比赛的对手队伍名*/
	public String getAway_Team() {
		return away_Team;
	}

	public void setAway_Team(String away_Team) {
		this.away_Team = away_Team;
	}
	
}
