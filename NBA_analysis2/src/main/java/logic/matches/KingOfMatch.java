package logic.matches;

/**
 * @author Lionel
 *  某场比赛的得分王，一场比赛有两个，每对一个，用在match的getKingsOfMatch中
 */
public class KingOfMatch {
    private String name;
    private int points;
    private int rebounds;
    private int assists;
    
    
    
    public KingOfMatch() {
        super();
    }
    public KingOfMatch(String name, int points, int rebounds, int assists) {
        super();
        this.name = name;
        this.points = points;
        this.rebounds = rebounds;
        this.assists = assists;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getPoints() {
        return points;
    }
    public void setPoints(int points) {
        this.points = points;
    }
    public int getRebounds() {
        return rebounds;
    }
    public void setRebounds(int rebounds) {
        this.rebounds = rebounds;
    }
    public int getAssists() {
        return assists;
    }
    public void setAssists(int assists) {
        this.assists = assists;
    }
    
    
    //debug only
    public String toString(){
        return this.getName() + "\n"
                + this.getPoints() + "\n"
                + this.getRebounds() + "\n"
                + this.getAssists();
    }
    
    
}
