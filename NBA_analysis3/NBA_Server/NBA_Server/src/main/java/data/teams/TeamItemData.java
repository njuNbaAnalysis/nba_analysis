package data.teams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.TeamListItem;
import po.TeamRecordItem;
import data.GetConnection;

public class TeamItemData {

    Connection conn;
    PreparedStatement pstm;
    Statement stmt;
    ResultSet rs;

    public TeamItemData() {
        super();
    }
    
    public boolean addTeamRecordItem(TeamRecordItem item){
        
        String sql = "insert into teamrecorditem(dataType,teamNameEn,isSeason,beginYear,PlayerId,showUpNumbers,startingTimes,during,fieldGoalsPercentage,fieldGoalHitsAverage,fieldGoalAttempsAverage,threePointerPercentage,threePointerHitsAverage,threePointerAttemptsAverage,freeThrowPercentage,freeThrowHitsAverage,freeThrowAttemptsAverage,reboundsAverage,offensiveReboundsAverage,defensiveReboundsAverage,assistsAverage,stealsAverage,blockShotsAverage,turnOversAverage,foulsAverage,pointsAverage,numOfVictory,numOfFailure) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, item.getDataType());
            pstm.setString(2, item.getTeamNameEn());
            pstm.setBoolean(3, item.isSeason());
            pstm.setString(4, item.getBeginYear());
            pstm.setString(5, item.getPlayerId());
            pstm.setInt(6, item.getShowUpNumbers());
            pstm.setInt(7, item.getStartingTimes());
            pstm.setDouble(8, item.getDuring());
            pstm.setDouble(9, item.getFieldGoalsPercentage());
            pstm.setDouble(10, item.getFieldGoalHitsAverage());
            pstm.setDouble(11, item.getFieldGoalAttempsAverage());
            pstm.setDouble(12, item.getThreePointerPercentage());
            pstm.setDouble(13, item.getThreePointerHitsAverage());
            pstm.setDouble(14, item.getThreePointerAttemptsAverage());
            pstm.setDouble(15, item.getFreeThrowPercentage());
            pstm.setDouble(16, item.getFreeThrowHitsAverage());
            pstm.setDouble(17, item.getFreeThrowAttemptsAverage());
            pstm.setDouble(18, item.getReboundsAverage());
            pstm.setDouble(19, item.getOffensiveReboundsAverage());
            pstm.setDouble(20, item.getDefensiveReboundsAverage());
            pstm.setDouble(21, item.getAssistsAverage());
            pstm.setDouble(22, item.getStealsAverage());
            pstm.setDouble(23, item.getBlockShotsAverage());
            pstm.setDouble(24, item.getTurnOversAverage());
            pstm.setDouble(25, item.getFoulsAverage());
            pstm.setDouble(26, item.getPointsAverage());
            pstm.setInt(27, item.getNumOfVictory());
            pstm.setInt(28, item.getNumOfFailure());
            
            pstm.executeUpdate();
            return true;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 对需要搜索的项进行赋值，无需限定值的项将string值设为""或null
     * 
     * @param item
     * @return 空的集合或者有值的集合
     */
    public ArrayList<TeamListItem> getTeamList(TeamListItem item){
        ArrayList<TeamListItem> resultList = new ArrayList<TeamListItem>();
        
        String sql = "select * from teamnamelist where teamNameEn like ? "
                + "and teamNameZh like ? "
                + "and conference like ? "
                + "and division like ?";
        try {
            pstm = conn.prepareStatement(sql);
            
            pstm.setString(1, getParameterString(item.getTeamNameEn()));
            pstm.setString(2, getParameterString(item.getTeamNameZh()));
            pstm.setString(3, getParameterString(item.getConference()));
            pstm.setString(4, getParameterString(item.getDivision()));
            rs = pstm.executeQuery();
            
            while(rs.next()){
                resultList.add(new TeamListItem(rs.getString("teamNameEn"),
                        rs.getString("teamNameZh"),
                        rs.getString("conference"),
                        rs.getString("division")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultList;
    }
    
    public void init(){
        conn = GetConnection.getConnection();
    }

    public void finish(){
        GetConnection.free(rs, conn, pstm);
    }

    /**
     * 
     * @param rawParameter
     * @return sql like语句中合适的参数
     */
    private String getParameterString(String rawParameter){
        if(rawParameter == null || rawParameter.equals("")){
            return "%";
        }
        else{
            return rawParameter;
        }
    }
}
