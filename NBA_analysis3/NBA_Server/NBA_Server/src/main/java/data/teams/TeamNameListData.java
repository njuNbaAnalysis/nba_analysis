package data.teams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.TeamListItem;
import data.GetConnection;

    //调用者需要手动init,finish
public class TeamNameListData{

    Connection conn;
    PreparedStatement pstm;
    Statement stmt;
    ResultSet rs;

    public TeamNameListData() {
        super();
    }
    
    public boolean addTeamNameListItem(TeamListItem item){
        
        String sql = "insert into teamnamelist(teamNameEn,teamNameZh,conference,division) values(?,?,?,?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, item.getTeamNameEn());
            pstm.setString(2, item.getTeamNameZh());
            pstm.setString(3, item.getConference());
            pstm.setString(4, item.getDivision());
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
    public static String getParameterString(String rawParameter){
        if(rawParameter == null || rawParameter.equals("")){
            return "%";
        }
        else{
            return rawParameter;
        }
    }
}
