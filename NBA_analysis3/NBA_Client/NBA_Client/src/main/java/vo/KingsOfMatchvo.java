package vo;

/**
 * @author Lionel
 *  某场比赛的得分王，一场比赛有两个，每对一个，用在match的getKingsOfMatch中
 */
public class KingsOfMatchvo {
    private String nameOfPointsKing;
    private int points;
    private String nameOfReboundsKing;
    private int rebounds;
    private String nameOfAssistsKing;
    private int assists;
    
    
    
    public KingsOfMatchvo() {
        super();
    }



    public KingsOfMatchvo(String nameOfPointsKing, int points, String nameOfReboundsKing, int rebounds,
            String nameOfAssistsKing, int assists) {
        super();
        this.nameOfPointsKing = nameOfPointsKing;
        this.points = points;
        this.nameOfReboundsKing = nameOfReboundsKing;
        this.rebounds = rebounds;
        this.nameOfAssistsKing = nameOfAssistsKing;
        this.assists = assists;
    }



    public String getNameOfPointsKing() {
        return nameOfPointsKing;
    }



    public void setNameOfPointsKing(String nameOfPointsKing) {
        this.nameOfPointsKing = nameOfPointsKing;
    }



    public int getPoints() {
        return points;
    }



    public void setPoints(int points) {
        this.points = points;
    }



    public String getNameOfReboundsKing() {
        return nameOfReboundsKing;
    }



    public void setNameOfReboundsKing(String nameOfReboundsKing) {
        this.nameOfReboundsKing = nameOfReboundsKing;
    }



    public int getRebounds() {
        return rebounds;
    }



    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }



    public String getNameOfAssistsKing() {
        return nameOfAssistsKing;
    }



    public void setNameOfAssistsKing(String nameOfAssistsKing) {
        this.nameOfAssistsKing = nameOfAssistsKing;
    }



    public int getAssists() {
        return assists;
    }



    public void setAssists(int assists) {
        this.assists = assists;
    }
    
    
    
}
