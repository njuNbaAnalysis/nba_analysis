package po;

public class TeamRecordItem {
    private String dataType;    //playerItem,teamItem,rivalTeamItem，分别代表球员总计数据，球队总计数据，和对方球队总计数据
    private String teamNameEn;
    private boolean isSeason;   //是否是常规赛，regular season
    private String beginYear;
    private String playerId;
    private int showUpNumbers;  //比赛场数，球队球员通用
    private int startingTimes;
    private double during;  //总时间
    private double fieldGoalsPercentage;
    private double fieldGoalHitsAverage;
    private double fieldGoalAttempsAverage;
    private double threePointerPercentage;
    private double threePointerHitsAverage;
    private double threePointerAttemptsAverage;
    private double freeThrowPercentage;
    private double freeThrowHitsAverage;
    private double freeThrowAttemptsAverage;
    private double reboundsAverage;
    private double offensiveReboundsAverage;
    private double defensiveReboundsAverage;
    private double assistsAverage;
    private double stealsAverage;
    private double blockShotsAverage;
    private double turnOversAverage;
    private double foulsAverage;
    private double pointsAverage;
    
    private int numOfVictory;//球队胜利场数
    private int numOfFailure;//球队失败场数
    public TeamRecordItem() {
        super();
    }
    public TeamRecordItem(String dataType, String teamNameEn, boolean isSeason, String beginYear, String playerId,
            int showUpNumbers, int startingTimes, double during, double fieldGoalsPercentage,
            double fieldGoalHitsAverage, double fieldGoalAttempsAverage, double threePointerPercentage,
            double threePointerHitsAverage, double threePointerAttemptsAverage, double freeThrowPercentage,
            double freeThrowHitsAverage, double freeThrowAttemptsAverage, double reboundsAverage,
            double offensiveReboundsAverage, double defensiveReboundsAverage, double assistsAverage,
            double stealsAverage, double blockShotsAverage, double turnOversAverage, double foulsAverage,
            double pointsAverage, int numOfVictory, int numOfFailure) {
        super();
        this.dataType = dataType;
        this.teamNameEn = teamNameEn;
        this.isSeason = isSeason;
        this.beginYear = beginYear;
        this.playerId = playerId;
        this.showUpNumbers = showUpNumbers;
        this.startingTimes = startingTimes;
        this.during = during;
        this.fieldGoalsPercentage = fieldGoalsPercentage;
        this.fieldGoalHitsAverage = fieldGoalHitsAverage;
        this.fieldGoalAttempsAverage = fieldGoalAttempsAverage;
        this.threePointerPercentage = threePointerPercentage;
        this.threePointerHitsAverage = threePointerHitsAverage;
        this.threePointerAttemptsAverage = threePointerAttemptsAverage;
        this.freeThrowPercentage = freeThrowPercentage;
        this.freeThrowHitsAverage = freeThrowHitsAverage;
        this.freeThrowAttemptsAverage = freeThrowAttemptsAverage;
        this.reboundsAverage = reboundsAverage;
        this.offensiveReboundsAverage = offensiveReboundsAverage;
        this.defensiveReboundsAverage = defensiveReboundsAverage;
        this.assistsAverage = assistsAverage;
        this.stealsAverage = stealsAverage;
        this.blockShotsAverage = blockShotsAverage;
        this.turnOversAverage = turnOversAverage;
        this.foulsAverage = foulsAverage;
        this.pointsAverage = pointsAverage;
        this.numOfVictory = numOfVictory;
        this.numOfFailure = numOfFailure;
    }
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
    }
    public String getTeamNameEn() {
        return teamNameEn;
    }
    public void setTeamNameEn(String teamNameEn) {
        this.teamNameEn = teamNameEn;
    }
    public boolean isSeason() {
        return isSeason;
    }
    public void setSeason(boolean isSeason) {
        this.isSeason = isSeason;
    }
    public String getBeginYear() {
        return beginYear;
    }
    public void setBeginYear(String beginYear) {
        this.beginYear = beginYear;
    }
    public String getPlayerId() {
        return playerId;
    }
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
    public int getShowUpNumbers() {
        return showUpNumbers;
    }
    public void setShowUpNumbers(int showUpNumbers) {
        this.showUpNumbers = showUpNumbers;
    }
    public int getStartingTimes() {
        return startingTimes;
    }
    public void setStartingTimes(int startingTimes) {
        this.startingTimes = startingTimes;
    }
    public double getDuring() {
        return during;
    }
    public void setDuring(double during) {
        this.during = during;
    }
    public double getFieldGoalsPercentage() {
        return fieldGoalsPercentage;
    }
    public void setFieldGoalsPercentage(double fieldGoalsPercentage) {
        this.fieldGoalsPercentage = fieldGoalsPercentage;
    }
    public double getFieldGoalHitsAverage() {
        return fieldGoalHitsAverage;
    }
    public void setFieldGoalHitsAverage(double fieldGoalHitsAverage) {
        this.fieldGoalHitsAverage = fieldGoalHitsAverage;
    }
    public double getFieldGoalAttempsAverage() {
        return fieldGoalAttempsAverage;
    }
    public void setFieldGoalAttempsAverage(double fieldGoalAttempsAverage) {
        this.fieldGoalAttempsAverage = fieldGoalAttempsAverage;
    }
    public double getThreePointerPercentage() {
        return threePointerPercentage;
    }
    public void setThreePointerPercentage(double threePointerPercentage) {
        this.threePointerPercentage = threePointerPercentage;
    }
    public double getThreePointerHitsAverage() {
        return threePointerHitsAverage;
    }
    public void setThreePointerHitsAverage(double threePointerHitsAverage) {
        this.threePointerHitsAverage = threePointerHitsAverage;
    }
    public double getThreePointerAttemptsAverage() {
        return threePointerAttemptsAverage;
    }
    public void setThreePointerAttemptsAverage(double threePointerAttemptsAverage) {
        this.threePointerAttemptsAverage = threePointerAttemptsAverage;
    }
    public double getFreeThrowPercentage() {
        return freeThrowPercentage;
    }
    public void setFreeThrowPercentage(double freeThrowPercentage) {
        this.freeThrowPercentage = freeThrowPercentage;
    }
    public double getFreeThrowHitsAverage() {
        return freeThrowHitsAverage;
    }
    public void setFreeThrowHitsAverage(double freeThrowHitsAverage) {
        this.freeThrowHitsAverage = freeThrowHitsAverage;
    }
    public double getFreeThrowAttemptsAverage() {
        return freeThrowAttemptsAverage;
    }
    public void setFreeThrowAttemptsAverage(double freeThrowAttemptsAverage) {
        this.freeThrowAttemptsAverage = freeThrowAttemptsAverage;
    }
    public double getReboundsAverage() {
        return reboundsAverage;
    }
    public void setReboundsAverage(double reboundsAverage) {
        this.reboundsAverage = reboundsAverage;
    }
    public double getOffensiveReboundsAverage() {
        return offensiveReboundsAverage;
    }
    public void setOffensiveReboundsAverage(double offensiveReboundsAverage) {
        this.offensiveReboundsAverage = offensiveReboundsAverage;
    }
    public double getDefensiveReboundsAverage() {
        return defensiveReboundsAverage;
    }
    public void setDefensiveReboundsAverage(double defensiveReboundsAverage) {
        this.defensiveReboundsAverage = defensiveReboundsAverage;
    }
    public double getAssistsAverage() {
        return assistsAverage;
    }
    public void setAssistsAverage(double assistsAverage) {
        this.assistsAverage = assistsAverage;
    }
    public double getStealsAverage() {
        return stealsAverage;
    }
    public void setStealsAverage(double stealsAverage) {
        this.stealsAverage = stealsAverage;
    }
    public double getBlockShotsAverage() {
        return blockShotsAverage;
    }
    public void setBlockShotsAverage(double blockShotsAverage) {
        this.blockShotsAverage = blockShotsAverage;
    }
    public double getTurnOversAverage() {
        return turnOversAverage;
    }
    public void setTurnOversAverage(double turnOversAverage) {
        this.turnOversAverage = turnOversAverage;
    }
    public double getFoulsAverage() {
        return foulsAverage;
    }
    public void setFoulsAverage(double foulsAverage) {
        this.foulsAverage = foulsAverage;
    }
    public double getPointsAverage() {
        return pointsAverage;
    }
    public void setPointsAverage(double pointsAverage) {
        this.pointsAverage = pointsAverage;
    }
    public int getNumOfVictory() {
        return numOfVictory;
    }
    public void setNumOfVictory(int numOfVictory) {
        this.numOfVictory = numOfVictory;
    }
    public int getNumOfFailure() {
        return numOfFailure;
    }
    public void setNumOfFailure(int numOfFailure) {
        this.numOfFailure = numOfFailure;
    }
    @Override
    public String toString() {
        return "TeamRecordItem [dataType=" + dataType + ", teamNameEn=" + teamNameEn + ", isSeason=" + isSeason
                + ", beginYear=" + beginYear + ", playerId=" + playerId + ", showUpNumbers=" + showUpNumbers
                + ", startingTimes=" + startingTimes + ", during=" + during + ", fieldGoalsPercentage="
                + fieldGoalsPercentage + ", fieldGoalHitsAverage=" + fieldGoalHitsAverage
                + ", fieldGoalAttempsAverage=" + fieldGoalAttempsAverage + ", threePointerPercentage="
                + threePointerPercentage + ", threePointerHitsAverage=" + threePointerHitsAverage
                + ", threePointerAttemptsAverage=" + threePointerAttemptsAverage + ", freeThrowPercentage="
                + freeThrowPercentage + ", freeThrowHitsAverage=" + freeThrowHitsAverage
                + ", freeThrowAttemptsAverage=" + freeThrowAttemptsAverage + ", reboundsAverage=" + reboundsAverage
                + ", offensiveReboundsAverage=" + offensiveReboundsAverage + ", defensiveReboundsAverage="
                + defensiveReboundsAverage + ", assistsAverage=" + assistsAverage + ", stealsAverage=" + stealsAverage
                + ", blockShotsAverage=" + blockShotsAverage + ", turnOversAverage=" + turnOversAverage
                + ", foulsAverage=" + foulsAverage + ", pointsAverage=" + pointsAverage + ", numOfVictory="
                + numOfVictory + ", numOfFailure=" + numOfFailure + "]";
    }
    
}
