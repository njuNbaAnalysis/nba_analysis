package data.teams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import data.GetConnection;
import data.po.TeamListItem;

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

    public void init(){
        conn = GetConnection.getConnection();
    }

    public void finish(){
        GetConnection.free(rs, conn, pstm);
    }
}
