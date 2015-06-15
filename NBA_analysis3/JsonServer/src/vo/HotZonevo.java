package vo;

import java.io.Serializable;
import java.util.HashMap;

/**热区信息：包括球员/球队在某个区域内的能力展现情况的值与联盟平均水平的值以及两个值之间的对比
 * 
 *  键值为zone,zone可取值为total,c08,c1624,c24Plus,c816,l1624,l24Plus,l816,lc1624,lc24Plus,r1624,r24Plus,r816,rc1624,rc24Plus
 *  当键值取total时，值为所有区域的总和，其中只有attempted是有定义的。
 */
public class HotZonevo implements Serializable{
	/**球队名称（为3个英文大写的缩写）*/
    private String teamNameEn;
    /**是否为常规赛（true表示常规赛，false表示季后赛）*/
    private boolean isSeason;   //是否为常规赛
    /**是否是所有球队的总计数据（true总计数据，false表示平均数据）*/
    private boolean isTotal;    //是否是所有球队的总计数据
    
    /**最近5场比赛的数据*/
    private HashMap<String,Data> last5; 
    /**整个赛季的数据*/
    private HashMap<String,Data> total;
    
    /**
	 * 空构造器
	 * */
    public HotZonevo() {
        super();
    }
    

    
    

    /**
	 * 对该类的所有属性进行构造
	 * */
    public HotZonevo(String teamNameEn, boolean isSeason, boolean isTotal, HashMap<String, Data> last5,
            HashMap<String, Data> total) {
        super();
        this.teamNameEn = teamNameEn;
        this.isSeason = isSeason;
        this.isTotal = isTotal;
        this.last5 = last5;
        this.total = total;
    }




    /**
	 * HotZonevo的内部类，用于计算和存储热区信息的具体信息
	 * */
    public class Data implements Serializable{
        private int attempted;
        private int made;
        private double pct;
        private double disPct;
        public Data() {
            super();
        }
        /**
         * data所有属性的构造函数
         * */
        public Data(int attempted, int made, double pct, double disPct) {
            super();
            this.attempted = attempted;
            this.made = made;
            this.pct = pct;
            this.disPct = disPct;
        }
        public int getAttempted() {
            return attempted;
        }
        public void setAttempted(int attempted) {
            this.attempted = attempted;
        }
        public int getMade() {
            return made;
        }
        public void setMade(int made) {
            this.made = made;
        }
        public double getPct() {
            return pct;
        }
        public void setPct(double pct) {
            this.pct = pct;
        }
        public double getDisPct() {
            return disPct;
        }
        public void setDisPct(double disPct) {
            this.disPct = disPct;
        }
        
        
    }


    /**返回该球队名称（为3个英文大写的缩写）*/
    public String getTeamNameEn() {
        return teamNameEn;
    }

 
    public void setTeamNameEn(String teamNameEn) {
        this.teamNameEn = teamNameEn;
    }
    /**返回是否为常规赛*/
    public boolean isSeason() {
        return isSeason;
    }

    public void setSeason(boolean isSeason) {
        this.isSeason = isSeason;
    }

    /**返回该球队的最近5场热区值*/
    public HashMap<String, Data> getLast5() {
        return last5;
    }

    public void setLast5(HashMap<String, Data> last5) {
        this.last5 = last5;
    }
    
    /**返回该球队的该赛季的热区值*/
    public HashMap<String, Data> getTotal() {
        return total;
    }

    public void setTotal(HashMap<String, Data> total) {
        this.total = total;
    }


    /**返回所得数据是否是总计还是平均*/
    public boolean isTotal() {
        return isTotal;
    }





    public void setTotal(boolean isTotal) {
        this.isTotal = isTotal;
    }





    @Override
    public String toString() {
        return "HotZonevo [teamNameEn=" + teamNameEn + ", isSeason=" + isSeason + ", isTotal=" + isTotal + ", last5="
                + last5 + ", total=" + total + "]";
    }
    
}
