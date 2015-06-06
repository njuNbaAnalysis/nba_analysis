package vo;


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


    public int getPoints() {
        return points;
    }


    public String getNameOfReboundsKing() {
        return nameOfReboundsKing;
    }


    public int getRebounds() {
        return rebounds;
    }
    

    public String getNameOfAssistsKing() {
        return nameOfAssistsKing;
    }


    public int getAssists() {
        return assists;
    }
    
}
