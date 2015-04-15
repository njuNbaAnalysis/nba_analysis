package logic.matches;

//每一个球队的记录
public class RecordOfPlayer{
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
    
    public RecordOfPlayer(String playerName, String position, int minutes, int fieldGoals, int fieldAttempts,
            int threePoints, int threePointAttemps, int freeThrows, int freeThrowAttemps, int offensiveRebounds,
            int defensiveRebounds, int rebounds, int assists, int steals, int blocks, int turnOver, int fauls,
            int points,boolean isStarted) {
        super();
        this.playerName = playerName;
        this.position = position;
        this.minutes = minutes;
        this.fieldGoalHits = fieldGoals;
        this.fieldGoalAttempts = fieldAttempts;
        this.threePointHits = threePoints;
        this.threePointAttemps = threePointAttemps;
        this.freeThrowHits = freeThrows;
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

    public int getThreePointAttemps() {
        return threePointAttemps;
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
    public void setPoints(int points){
    	this.points = points;
    }    
    public boolean isStarted(){
    	return isStarted;
    }

    public int getFieldGoalHits() {
        return fieldGoalHits;
    }

    public void setFieldGoalHits(int fieldGoalHits) {
        this.fieldGoalHits = fieldGoalHits;
    }

    public int getFieldGoalAttempts() {
        return fieldGoalAttempts;
    }

    public void setFieldGoalAttempts(int fieldGoalAttempts) {
        this.fieldGoalAttempts = fieldGoalAttempts;
    }

    public int getThreePointHits() {
        return threePointHits;
    }

    public void setThreePointHits(int threePointHits) {
        this.threePointHits = threePointHits;
    }

    public int getFreeThrowHits() {
        return freeThrowHits;
    }

    public void setFreeThrowHits(int freeThrowHits) {
        this.freeThrowHits = freeThrowHits;
    }

    public void setThreePointAttemps(int threePointAttemps) {
        this.threePointAttemps = threePointAttemps;
    }

    public void setFreeThrowAttemps(int freeThrowAttemps) {
        this.freeThrowAttemps = freeThrowAttemps;
    }

	public int getEfficiency() {
		int efficiency = (rebounds + points + blocks + steals + assists)
				- (fieldGoalAttempts - fieldGoalHits) -
						(freeThrowAttemps  - freeThrowHits)
				- turnOver; // (得分+篮板+助攻+抢断+盖帽)-（出手次数-命中次数）-（罚球次数-罚球命中次数）-失误次数
		return efficiency;
	}

	public double getThreePointPercentage() {
		
		double threePointersPercentage = 0;
		if (threePointAttemps != 0) {
			threePointersPercentage = threePointHits * 1.0
					/ threePointAttemps;
		}
		return threePointersPercentage;
	}

	public double getFreeThrowPercentage() {
		double freeThrowsPercentage = 0;
		if (freeThrowAttemps != 0) {
			freeThrowsPercentage = freeThrowHits * 1.0 / freeThrowAttemps;
		}
		return freeThrowsPercentage;
	}

	public double getFieldGoalPercentage() {
		double fieldGoalsPercentage = 0;
		if (fieldGoalAttempts != 0) {
			fieldGoalsPercentage = fieldGoalHits * 1.0 / fieldGoalAttempts;
		}
		return fieldGoalsPercentage;
	}

}
