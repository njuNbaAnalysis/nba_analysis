package data.teams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import po.TeamCode;
import data.GetConnection;

public class TeamCodeData {
    Connection conn;
    PreparedStatement pstm;
    Statement stmt;
    ResultSet rs;

    public TeamCodeData() {
        super();
    }
    
    public boolean addTeamCode(TeamCode teamCode){
        
        String sql = "update teamnamelist set teamCode = ? where teamNameEn = ?";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, teamCode.getCode());
            pstm.setString(2, teamCode.getAbbr());
            
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
