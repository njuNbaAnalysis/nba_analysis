package logic.teams;

import java.awt.Image;
import java.util.ArrayList;

import util.SvgImage;

public class Team {
    //raw data
	private String name;                   //球队名
	private String abbreviation;           //缩写
	private String location;               //地区
	private char conference;               //东部赛区or西部赛区 E,W
	private String division;
	private String homeCourt;              //主场
	private int setUpTime;                 //建立时间
	private SvgImage logo;				   //队标
	
    //not raw data
	private ArrayList<String> playerList = new ArrayList<String>();       //球员列表
	
	//累加
	private int numOfMatches;                   //比赛场数
	private int numOfVictory;                  //胜利场数
	private int fieldGoalAttemps;               //投篮出手次数
	private int fieldGoalHits;                 //投篮命中次数
	private int threePointerAttempts;           //三分出手次数
	private int threePointerHits;               //三分命中次数
	private int freeThrowAttempts;                     //罚球出手次数
	private int freeThrowHits;                  //罚球命中次数
	private int offensiveRebounds;              //进攻篮板数
	private int defensiveRebounds;              //防守篮板数
	
    private int assists;                        //助攻数
    private int steals;                         //抢断数
    private int blockShots;                     //总盖帽数
    private int turnOver;                       //总失误数
    private int fouls;                          //总犯规数
    private int points;                         //总比赛得分数

	//后期-交叉处理
    private double offensiveRounds;             //进攻回合
    private double defensiveRounds;             //防守回合
	private double offenseEfficiency;           //进攻效率
	private double defenseEfficiency; 		    //防守效率
	private double reboundsEfficiency;          //篮板效率
	private double stealsEfficiency;			//抢断效率
	private double assistsPercentage;           //助攻率 
	
	//computeData 计算过程中用到，界面后期可能用到
	private int pointsRival;                   //对手总得分
	private int fieldGoalAttempsRival;         //对手投篮出手次数
	private int threePointerAttemptsRival;     //对手三分出手次数
	private int offenseReboundsRival;          //对手总进攻篮板
	private int defenseReboundsRival;          //对手总防守篮板 
	
    //后期
    //private int rebounds;                       //篮板数
    //private double threePointersPercentage;     //三分命中率
    //private double freeThrowsPercentage;        //罚球命中率
	//private double fieldGoalsPercentage;           //投篮命中率
    //private double winningPercentage;           //胜率  
	//private int reboundsRival;               //对手总篮板
	
    public Team(String name, String abbreviation, String location, char conference, String division, String homeCourt,
            int setUpTime, SvgImage logo) {
        super();
        this.name = name;
        this.abbreviation = abbreviation;
        this.location = location;
        this.conference = conference;
        this.division = division;
        this.homeCourt = homeCourt;
        this.setUpTime = setUpTime;
        this.logo = logo;
    }

    public String getName() {
        return name;
    }


    public String getAbbreviation() {
        return abbreviation;
    }


    public String getLocation() {
        return location;
    }


    public char getConference() {
        return conference;
    }


    public String getDivision() {
        return division;
    }


    public String getHomeCourt() {
        return homeCourt;
    }


    public int getSetUpTime() {
        return setUpTime;
    }


    public Image getLogo(int width,int height) {
    	System.out.println(logo==null);
        return logo.getImage(width, height);
    }
    
    public void setLogo(SvgImage logo) {
        this.logo = logo;
    }
    
    
    
    //now raw data
    public ArrayList<String> getPlayerList() {
        return playerList;
    }


    public int getNumOfMatches() {
        return numOfMatches;
    }


    public int getFieldGoalAttemps() {
        return fieldGoalAttemps;
    }


    public int getThreePointerAttempts() {
        return threePointerAttempts;
    }


    public int getThreePointerHits() {
        return threePointerHits;
    }


    public double getThreePointersPercentage() {
        if(threePointerAttempts == 0){
            return 0;
        }
        else{
            return 1.0 * threePointerHits / threePointerAttempts;
        }
    }


    public int getFreeThrows() {
        return freeThrowAttempts;
    }


    public int getFreeThrowHits() {
        return freeThrowHits;
    }


    public double getFreeThrowsPercentage() {
        if(freeThrowAttempts == 0){
            return 0;
        }
        else{
            return 1.0 * freeThrowHits / freeThrowAttempts;
        }
    }


    public int getRebounds() {
        return offensiveRebounds + defensiveRebounds;
    }


    public int getOffensiveRebounds() {
        return offensiveRebounds;
    }


    public int getDefensiveRebounds() {
        return defensiveRebounds;
    }


    public int getAssists() {
        return assists;
    }


    public int getSteals() {
        return steals;
    }


    public int getBlockShots() {
        return blockShots;
    }


    public int getTurnOver() {
        return turnOver;
    }


    public int getFouls() {
        return fouls;
    }


    public int getPoints() {
        return points;
    }


    public double getWinningPercentage() {
        if(numOfMatches == 0){
            return 0;
        }
        else{
            return 1.0 * numOfVictory / numOfMatches;
        }
    }


    public double getOffensiveRounds() {
        return offensiveRounds;
    }


    public double getOffenseEfficiency() {
        return offenseEfficiency;
    }


    public double getDefenseEfficiency() {
        return defenseEfficiency;
    }


    public double getReboundsEfficiency() {
        return reboundsEfficiency;
    }


    public double getStealsEfficiency() {
        return stealsEfficiency;
    }


    public double getAssistsPercentage() {
        return assistsPercentage;
    }


    public void setPlayerList(ArrayList<String> playerList) {
        this.playerList = playerList;
    }


    public void setNumOfMatches(int numOfMatches) {
        this.numOfMatches = numOfMatches;
    }


    public void setFieldGoalAttemps(int fieldGoalAttemps) {
        this.fieldGoalAttemps = fieldGoalAttemps;
    }


    public void setThreePointerAttempts(int threePointerAttempts) {
        this.threePointerAttempts = threePointerAttempts;
    }


    public void setThreePointerHits(int threePointerHits) {
        this.threePointerHits = threePointerHits;
    }


    public void setFreeThrows(int freeThrows) {
        this.freeThrowAttempts = freeThrows;
    }


    public void setFreeThrowHits(int freeThrowHits) {
        this.freeThrowHits = freeThrowHits;
    }


    public void setOffensiveRebounds(int offensiveRebounds) {
        this.offensiveRebounds = offensiveRebounds;
    }


    public void setDefensiveRebounds(int defensiveRebounds) {
        this.defensiveRebounds = defensiveRebounds;
    }


    public void setAssists(int assists) {
        this.assists = assists;
    }


    public void setSteals(int steals) {
        this.steals = steals;
    }


    public void setBlockShots(int blockShots) {
        this.blockShots = blockShots;
    }


    public void setTurnOver(int turnOver) {
        this.turnOver = turnOver;
    }


    public void setFouls(int fouls) {
        this.fouls = fouls;
    }


    public void setPoints(int points) {
        this.points = points;
    }


    public void setOffensiveRounds(double offensiveRounds) {
        this.offensiveRounds = offensiveRounds;
    }


    public void setOffenseEfficiency(double offenseEfficiency) {
        this.offenseEfficiency = offenseEfficiency;
    }


    public void setDefenseEfficiency(double defenseEfficiency) {
        this.defenseEfficiency = defenseEfficiency;
    }


    public void setReboundsEfficiency(double reboundsEfficiency) {
        this.reboundsEfficiency = reboundsEfficiency;
    }


    public void setStealsEfficiency(double stealsEfficiency) {
        this.stealsEfficiency = stealsEfficiency;
    }


    public void setAssistsPercentage(double assistsPercentage) {
        this.assistsPercentage = assistsPercentage;
    }

    public int getNumOfVictory() {
        return numOfVictory;
    }

    public void setNumOfVictory(int numOfVictory) {
        this.numOfVictory = numOfVictory;
    }

    public int getFieldGoalHits() {
        return fieldGoalHits;
    }

    public void setFieldGoalHits(int fieldGoalHits) {
        this.fieldGoalHits = fieldGoalHits;
    }

    public int getReboundsRival() {
        return offenseReboundsRival + this.defenseReboundsRival;
    }

    public int getOffenseReboundsRival() {
        return offenseReboundsRival;
    }

    public void setOffenseReboundsRival(int offenseReboundsRival) {
        this.offenseReboundsRival = offenseReboundsRival;
    }

    public int getDefenseReboundsRival() {
        return defenseReboundsRival;
    }

    public void setDefenseReboundsRival(int defenseReboundsRival) {
        this.defenseReboundsRival = defenseReboundsRival;
    }

    public double getDefensiveRounds() {
        return defensiveRounds;
    }

    public void setDefensiveRounds(double defensiveRounds) {
        this.defensiveRounds = defensiveRounds;
    }

    public int getPointsRival() {
        return pointsRival;
    }

    public void setPointsRival(int pointsRival) {
        this.pointsRival = pointsRival;
    }

    public int getFieldGoalAttempsRival() {
        return fieldGoalAttempsRival;
    }

    public void setFieldGoalAttempsRival(int fieldGoalAttempsRival) {
        this.fieldGoalAttempsRival = fieldGoalAttempsRival;
    }

    public int getThreePointerAttemptsRival() {
        return threePointerAttemptsRival;
    }

    public void setThreePointerAttemptsRival(int threePointerAttemptsRival) {
        this.threePointerAttemptsRival = threePointerAttemptsRival;
    }

    public double getFieldGoalsPercentage() {
        if(fieldGoalAttemps == 0){
            return 0;
        }
        else{
            return 1.0 * fieldGoalHits / fieldGoalAttemps;
        }
    }

	public double getAverageOffenseRebounds() {
	    if(numOfMatches == 0){
	        return 0;
	    }
	    else{
	        return 1.0 * offensiveRebounds / numOfMatches;
	    }
	}

	public double getAverageRebounds() {
	    if(this.getNumOfMatches() == 0){
	        return 0;
	    }
	    else{
	        return 1.0 * this.getRebounds() / this.getNumOfMatches();
	    }
	}

	public double getAverageAssists() {
	    if(this.getNumOfMatches() == 0){
	        return 0;
	    }
	    else{
	        return 1.0 * this.getAssists() / this.getNumOfMatches();
	    }
	}

	public double getAverageTurnOver() {
	    if(this.getNumOfMatches() == 0){
            return 0;
        }
        else{
            return 1.0 * this.getTurnOver() / this.getNumOfMatches();
        }
	}

	public double getAverageSteals() {
	    if(this.getNumOfMatches() == 0){
            return 0;
        }
        else{
            return 1.0 * this.getSteals() / this.getNumOfMatches();
        }
	}

	public double getAverageBlockShots() {
	    if(this.getNumOfMatches() == 0){
            return 0;
        }
        else{
            return 1.0 * this.getBlockShots() / this.getNumOfMatches();
        }
	}

	public double getAverageFouls() {
	    if(this.getNumOfMatches() == 0){
            return 0;
        }
        else{
            return 1.0 * this.getFouls() / this.getNumOfMatches();
        }
	}

	public double getAveragePoints() {
	    if(this.getNumOfMatches() == 0){
            return 0;
        }
        else{
            return 1.0 * this.getPoints() / this.getNumOfMatches();
        }
	}

	public double getAverageDefenseRebounds() {
	    if(numOfMatches == 0){
            return 0;
        }
        else{
            return 1.0 * defensiveRebounds / numOfMatches;
        }
	}

    public void setConference(char conference) {
        this.conference = conference;
    }
}
