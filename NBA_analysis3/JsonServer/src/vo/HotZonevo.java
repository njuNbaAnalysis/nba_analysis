package vo;

import java.io.Serializable;
import java.util.HashMap;

public class HotZonevo  implements Serializable{
    private String teamNameEn;
    private boolean isSeason;   //是否为常规赛
    private boolean isTotal;    //是否是所有球队的总计数据
    
    //键值为zone,zone可取值为total,c08,c1624,c24Plus,c816,l1624,l24Plus,l816,lc1624,lc24Plus,r1624,r24Plus,r816,rc1624,rc24Plus
    //当键值取total时，值为所有区域的总和，其中只有attempted是有定义的。
    private HashMap<String,Data> last5; 
    private HashMap<String,Data> total;
    
    public HotZonevo() {
        super();
    }
    

    
    

    public HotZonevo(String teamNameEn, boolean isSeason, boolean isTotal, HashMap<String, Data> last5,
            HashMap<String, Data> total) {
        super();
        this.teamNameEn = teamNameEn;
        this.isSeason = isSeason;
        this.isTotal = isTotal;
        this.last5 = last5;
        this.total = total;
    }





    public class Data{
        private int attempted;
        private int made;
        private double pct;
        private double disPct;
        public Data() {
            super();
        }
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

    public HashMap<String, Data> getLast5() {
        return last5;
    }

    public void setLast5(HashMap<String, Data> last5) {
        this.last5 = last5;
    }

    public HashMap<String, Data> getTotal() {
        return total;
    }

    public void setTotal(HashMap<String, Data> total) {
        this.total = total;
    }





    public boolean isTotal() {
        return isTotal;
    }





    public void setTotal(boolean isTotal) {
        this.isTotal = isTotal;
    }
    
}
