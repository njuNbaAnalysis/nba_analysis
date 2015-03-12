package logic.teams;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Team {
    //raw data
	private String name;                        //球队名
	private String abbreviation;           //缩写
	private String location;               //地区
	private char conference;            //东部赛区or西部赛区 E,W
	private String division;
	private String homeCourt;              //主场
	private int setUpTime;           //建立时间
	private BufferedImage logo;					//队标
	

    //not raw data
	private ArrayList<String> playerList;       //球员列表
	private int numOfMatches;                   //比赛场数
	private int fieldGoalAttemps;                 //投篮出手次数
	
	private int threePointerAttempts;                    //三分出手次数
	private int threePointerHits;               //三分命中次数
	private double threePointersPercentage;     //三分命中率
	
	private int freeThrows;               //罚球出手次数
	private int freeThrowHits;           //罚球命中次数
	private double freeThrowsPercentage;        //罚球命中率
	
	private int rebounds;                       //篮板数
	private int offensiveRebounds;             //进攻篮板数
	private int defensiveRebounds;             //防守篮板数
	
	private int assists;                        //助攻数
	private int Steals;                         //抢断数
	private int blockShots;						//总盖帽数
	private int turnOver;						//总失误数
	private int fouls;							//总犯规数
	private int points;							//总比赛得分数
	
	private double winningPercentage;       	//胜率
	private int offensiveRounds;                   //进攻回合
	private double offenseEfficiency;        //进攻效率
	private double defenseEfficiency; 		//防守效率
	private double reboundsEfficiency;             //篮板效率
	private double stealsEfficiency;			//抢断效率
	private double assistsPercentage;             		//助攻率 

	
    public Team(String name, String abbreviation, String location, char conference, String division, String homeCourt,
            int setUpTime, BufferedImage logo) {
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


    public BufferedImage getLogo() {
        return logo;
    }


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
        return threePointersPercentage;
    }


    public int getFreeThrows() {
        return freeThrows;
    }


    public int getFreeThrowHits() {
        return freeThrowHits;
    }


    public double getFreeThrowsPercentage() {
        return freeThrowsPercentage;
    }


    public int getRebounds() {
        return rebounds;
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
        return Steals;
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
        return winningPercentage;
    }


    public int getOffensiveRounds() {
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


    public void setThreePointersPercentage(double threePointersPercentage) {
        this.threePointersPercentage = threePointersPercentage;
    }


    public void setFreeThrows(int freeThrows) {
        this.freeThrows = freeThrows;
    }


    public void setFreeThrowHits(int freeThrowHits) {
        this.freeThrowHits = freeThrowHits;
    }


    public void setFreeThrowsPercentage(double freeThrowsPercentage) {
        this.freeThrowsPercentage = freeThrowsPercentage;
    }


    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
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
        Steals = steals;
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


    public void setWinningPercentage(double winningPercentage) {
        this.winningPercentage = winningPercentage;
    }


    public void setOffensiveRounds(int offensiveRounds) {
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
}
