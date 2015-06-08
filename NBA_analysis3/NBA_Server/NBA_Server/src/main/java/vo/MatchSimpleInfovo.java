package vo;

import java.io.Serializable;


/**
 * 用于记录一场比赛的简略信息，包括：比赛时间，是否胜利，球队得分以及对手得分，球队名（英文缩写），是否在主场
 * */
public class MatchSimpleInfovo implements Serializable{
	/**日期，例如2015-06-05 */
    private String date;//
    /**是否胜利：true表示胜利，false表示失败 */
    private boolean isWin;
    /**比赛得分 （球队得分以及对手得分）2元数组,该球队在前，对手在后*/
    private int[] points;//2元数组,传入的球队在前
    /**该球队名（英文缩写*/
    private String nameOfRival;//缩写
    /**球队名是否在主场，true表示在主场，false表示不再主场*/
    private boolean isAtHome;//是否主场
    public MatchSimpleInfovo() {
        super();
    }
    
    /**
	 * 对该类的所有属性进行构造
	 * */
    public MatchSimpleInfovo(String date, boolean isWin, int[] points, String nameOfRival, boolean isAtHome) {
        super();
        this.date = date;
        this.isWin = isWin;
        this.points = points;
        this.nameOfRival = nameOfRival;
        this.isAtHome = isAtHome;
    }
    /**得到该比赛日期*/
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    /**得到该比赛是否胜利*/
    public boolean isWin() {
        return isWin;
    }
    public void setWin(boolean isWin) {
        this.isWin = isWin;
    }
    /**得到该比赛得分情况，2元数组,该球队在前，对手在后*/
    public int[] getPoints() {
        return points;
    }
    public void setPoints(int[] points) {
        this.points = points;
    }
    /**得到该比赛球队英文名缩写*/
    public String getNameOfRival() {
        return nameOfRival;
    }
    public void setNameOfRival(String nameOfRival) {
        this.nameOfRival = nameOfRival;
    }
    /**得到该球队是否在主场*/
    public boolean isAtHome() {
        return isAtHome;
    }
    public void setAtHome(boolean isAtHome) {
        this.isAtHome = isAtHome;
    }
    
    
}
