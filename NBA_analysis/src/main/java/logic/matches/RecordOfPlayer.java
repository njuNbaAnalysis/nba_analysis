package logic.matches;

//每一个球队的记录
public class RecordOfPlayer{
    private String playerName;
    private String position;
    private int minutes;    //在场时间
    private int fieldGoals; //投篮命中数
    private int fieldAttempts;//投篮出手数
    private int threePoints;//三分球命中数
    private int threePointAttemps;//三分球出手数
    private int freeThrows;//罚球命中数
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
    
    public RecordOfPlayer(String playerName, String position, int minutes, int fieldGoals, int fieldAttempts,
            int threePoints, int threePointAttemps, int freeThrows, int freeThrowAttemps, int offensiveRebounds,
            int defensiveRebounds, int rebounds, int assists, int steals, int blocks, int turnOver, int fauls,
            int points) {
        super();
        this.playerName = playerName;
        this.position = position;
        this.minutes = minutes;
        this.fieldGoals = fieldGoals;
        this.fieldAttempts = fieldAttempts;
        this.threePoints = threePoints;
        this.threePointAttemps = threePointAttemps;
        this.freeThrows = freeThrows;
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

    public int getFieldGoals() {
        return fieldGoals;
    }

    public int getFieldAttempts() {
        return fieldAttempts;
    }

    public int getThreePoints() {
        return threePoints;
    }

    public int getThreePointAttemps() {
        return threePointAttemps;
    }

    public int getFreeThrows() {
        return freeThrows;
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
    
    
    
}
