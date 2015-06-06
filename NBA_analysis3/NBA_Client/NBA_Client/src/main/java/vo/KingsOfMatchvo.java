package vo;

/**
 * @author Lionel
 *  某场比赛的得分王，一场比赛有两个，每对一个，用在match的getKingsOfMatch中
 */
public class KingsOfMatchvo {
	private String Pid;
    private String nameOfPointsKing;
    private int points;
    private String nameOfReboundsKing;
    private int rebounds;
    private String nameOfAssistsKing;
    private int assists;
    
    
    
    public KingsOfMatchvo() {
        super();
    }



    public KingsOfMatchvo(String Pid ,String nameOfPointsKing, int points, String nameOfReboundsKing, int rebounds,
            String nameOfAssistsKing, int assists) {
        super();
        this.Pid = Pid;
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



	public String getPid() {
		return Pid;
	}
    
}
