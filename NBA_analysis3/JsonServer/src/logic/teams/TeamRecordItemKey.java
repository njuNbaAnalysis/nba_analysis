package logic.teams;

/**
 * 此类用作对TeamRecordItem的hashMap的检索key
 * @author Lionel
 *
 */
public class TeamRecordItemKey {
    private String dataType;    //playerItem,teamItem,rivalTeamItem，分别代表球员总计数据，球队总计数据，和对方球队总计数据
    private String teamNameEn;
    private boolean isSeason;   //是否是常规赛，regular season
    private String beginYear;
    private String playerId;
    public TeamRecordItemKey() {
        super();
    }
    public TeamRecordItemKey(String dataType, String teamNameEn, boolean isSeason, String beginYear, String playerId) {
        super();
        this.dataType = dataType;
        this.teamNameEn = teamNameEn;
        this.isSeason = isSeason;
        this.beginYear = beginYear;
        this.playerId = playerId;
    }
    public String getDataType() {
        return dataType;
    }
    public void setDataType(String dataType) {
        this.dataType = dataType;
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
    public String getBeginYear() {
        return beginYear;
    }
    public void setBeginYear(String beginYear) {
        this.beginYear = beginYear;
    }
    public String getPlayerId() {
        return playerId;
    }
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }
    @Override
    public String toString() {
        return "TeamRecordItemKey [dataType=" + dataType + ", teamNameEn=" + teamNameEn + ", isSeason=" + isSeason
                + ", beginYear=" + beginYear + ", playerId=" + playerId + "]";
    }
    @Override
    public int hashCode() {
        return 0;
    }
    /**
     * 这个方法用作对hashmap的索引，所有中心思想是有值就一定需要相同，没有值无需进行判断
     * @param key
     * @return
     */
    public boolean equals(TeamRecordItemKey key) {
        if(this.isSeason() != key.isSeason()){
            return false;
        }
        
        if(!this.getDataType().equals(key.getDataType())){
            return false;
        }
        
        if(!this.getTeamNameEn().equals(key.getTeamNameEn())){
            return false;
        }
        
        if(this.getBeginYear() != null && !this.getBeginYear().isEmpty()
                && key.getBeginYear() != null && !key.getBeginYear().isEmpty()
                && !this.getBeginYear().equals(key.getBeginYear())){
            return false;
        }
        
        return true;
    }
    
    
}
