package po;

public class HotZone {
    private String teamNameEn;
    private String type; //last5 or total
    
    //total 时表示的是所有区域的总数据，目前只有attempted有值
    private String zone; //total,c08,c1624,c24Plus,c816,l1624,l24Plus,l816,lc1624,lc24Plus,r1624,r24Plus,r816,rc1624,rc24Plus
    
    private boolean isSeason;   //是否为常规赛
    
    //此项表示数据表示的是否为所有球队的总计
    private boolean isTotal;

    private int attempted;
    private int made;
    private double pct;
    private double disPct;
    public HotZone() {
        super();
    }
    
    public HotZone(String teamNameEn, String type, String zone, boolean isSeason, boolean isTotal, int attempted,
            int made, double pct, double disPct) {
        super();
        this.teamNameEn = teamNameEn;
        this.type = type;
        this.zone = zone;
        this.isSeason = isSeason;
        this.isTotal = isTotal;
        this.attempted = attempted;
        this.made = made;
        this.pct = pct;
        this.disPct = disPct;
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
    public boolean isTotal() {
        return isTotal;
    }
    public void setTotal(boolean isTotal) {
        this.isTotal = isTotal;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public String getZone() {
        return zone;
    }
    public void setZone(String zone) {
        this.zone = zone;
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
    
    public HotZone clone(){
        return new HotZone(this.teamNameEn,
        this.type,
        this.zone,
        this.isSeason,
        this.isTotal,
        this.attempted,
        this.made,
        this.pct,
        this.disPct);
    }
    
    
    
}
