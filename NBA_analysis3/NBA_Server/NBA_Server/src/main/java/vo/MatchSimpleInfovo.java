package vo;

import java.io.Serializable;
import java.util.Date;

public class MatchSimpleInfovo implements Serializable{
    private String date;//日期，例如2015-06-05 
    private boolean isWin;
    private int[] points;//2元数组,传入的球队在前
    private String nameOfRival;//缩写
    private boolean isAtHome;//是否主场
    public MatchSimpleInfovo() {
        super();
    }
    public MatchSimpleInfovo(String date, boolean isWin, int[] points, String nameOfRival, boolean isAtHome) {
        super();
        this.date = date;
        this.isWin = isWin;
        this.points = points;
        this.nameOfRival = nameOfRival;
        this.isAtHome = isAtHome;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public boolean isWin() {
        return isWin;
    }
    public void setWin(boolean isWin) {
        this.isWin = isWin;
    }
    public int[] getPoints() {
        return points;
    }
    public void setPoints(int[] points) {
        this.points = points;
    }
    public String getNameOfRival() {
        return nameOfRival;
    }
    public void setNameOfRival(String nameOfRival) {
        this.nameOfRival = nameOfRival;
    }
    public boolean isAtHome() {
        return isAtHome;
    }
    public void setAtHome(boolean isAtHome) {
        this.isAtHome = isAtHome;
    }
    
    
}
