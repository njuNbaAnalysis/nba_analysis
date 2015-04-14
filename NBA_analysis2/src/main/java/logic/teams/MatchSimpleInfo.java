package logic.teams;

import java.util.Date;

/**
 * @author Lionel
 * 界面层使用
 * 相对与一支球队来说的
 * 一场比赛的日期，结果，比分，对手，以及主客场
 */
public class MatchSimpleInfo {
    private Date date;//月份为0~11 
    private boolean isWin;
    private int[] points;//2元数组,传入的球队在前
    private String nameOfRival;//缩写
    private boolean isAtHome;//是否主场
    public MatchSimpleInfo() {
        super();
    }
    public MatchSimpleInfo(Date date, boolean isWin, int[] points, String nameOfRival, boolean isAtHome) {
        super();
        this.date = date;
        this.isWin = isWin;
        this.points = points;
        this.nameOfRival = nameOfRival;
        this.isAtHome = isAtHome;
    }
    public Date getDate() {
        return date;
    }
    public void setDate(Date date) {
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
