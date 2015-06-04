package vo;

import java.io.Serializable;

//每一个球队的记录
public class RecordOfPlayervo implements Serializable{
    private String playerName;
    private String position;
    private int minutes;    //在场时间，以秒为单位
    private int fieldGoalHits; //投篮命中数
    private int fieldGoalAttempts;//投篮出手数
    private int threePointHits;//三分球命中数
    private int threePointAttemps;//三分球出手数
    private int freeThrowHits;//罚球命中数
    private int freeThrowAttemps;//罚球出手数
    private int offensiveRebounds;//进攻篮板数
    private int defensiveRebounds;//防守篮板数
    private int rebounds;//总篮板数
    private int assists;//助攻数
    private int steals;//抢断数
    private int blocks;//盖帽数
    private int turnOver;//失误数
    private int fauls;//犯规数
    private int points;//个人得分
    private boolean isStarted;  //是否是首发出场
    private double threePointersPercentage;     //三分命中率
    private double freeThrowsPercentage;        //罚球命中率
    private double fieldGoalsPercentage;           //投篮命中率
	public RecordOfPlayervo(String playerName, String position, int minutes,
			int fieldGoalHits, int fieldGoalAttempts, int threePointHits,
			int threePointAttemps, int freeThrowHits, int freeThrowAttemps,
			int offensiveRebounds, int defensiveRebounds, int rebounds,
			int assists, int steals, int blocks, int turnOver, int fauls,
			int points, boolean isStarted, double threePointersPercentage,
			double freeThrowsPercentage, double fieldGoalsPercentage) {
		super();
		this.playerName = playerName;
		this.position = position;
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
	public String getPlayerName() {
		return playerName;
	}
	public String getPosition() {
		return position;
	}
	public int getMinutes() {
		return minutes;
	}
	public int getFieldGoalHits() {
		return fieldGoalHits;
	}
	public int getFieldGoalAttempts() {
		return fieldGoalAttempts;
	}
	public int getThreePointHits() {
		return threePointHits;
	}
	public int getThreePointAttemps() {
		return threePointAttemps;
	}
	public int getFreeThrowHits() {
		return freeThrowHits;
	}
	public int getFreeThrowAttemps() {
		return freeThrowAttemps;
	}
	public int getOffensiveRebounds() {
		return offensiveRebounds;
	}
	public int getDefensiveRebounds() {
		return defensiveRebounds;
	}
	public int getRebounds() {
		return rebounds;
	}
	public int getAssists() {
		return assists;
	}
	public int getSteals() {
		return steals;
	}
	public int getBlocks() {
		return blocks;
	}
	public int getTurnOver() {
		return turnOver;
	}
	public int getFauls() {
		return fauls;
	}
	public int getPoints() {
		return points;
	}
	public boolean isStarted() {
		return isStarted;
	}
	public double getThreePointersPercentage() {
		return threePointersPercentage;
	}
	public double getFreeThrowsPercentage() {
		return freeThrowsPercentage;
	}
	public double getFieldGoalsPercentage() {
		return fieldGoalsPercentage;
	}
	
	
	public int getFieldGoalPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getThreePointPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getFreeThrowPercentage() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getEfficiency() {
		// TODO Auto-generated method stub
		return 0;
	}
    
    
}
