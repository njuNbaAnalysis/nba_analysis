package data.teams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import po.HotZone;
import data.GetConnection;

public class HotZoneData {
    Connection conn;
    PreparedStatement pstm;
    Statement stmt;
    ResultSet rs;
    
    public boolean addHotZone(HotZone hotZone){
        
        String sql = "insert into hotzone(teamNameEn,type,zone,isSeason,isTotal,attempted,made,pct,disPct) "
                + "value(?,?,?,?,?,?,?,?,?)";
        try {
            pstm = conn.prepareStatement(sql);
            pstm.setString(1, hotZone.getTeamNameEn());
            pstm.setString(2, hotZone.getType());
            pstm.setString(3, hotZone.getZone());
            pstm.setBoolean(4, hotZone.isSeason());
            pstm.setBoolean(5, hotZone.isTotal());
            pstm.setInt(6, hotZone.getAttempted());
            pstm.setInt(7, hotZone.getMade());
            pstm.setDouble(8, hotZone.getPct());
            pstm.setDouble(9, hotZone.getDisPct());
            
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
}
