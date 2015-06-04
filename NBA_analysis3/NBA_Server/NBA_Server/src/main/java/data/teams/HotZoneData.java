package data.teams;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.HotZone;
import util.Tools;
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

    /**
     * 根据hotZone中的isSeason,isTotal,teamNameEn,type,zone进行返回 
     * 其中isTotal项为true是，teamNameEn项被忽略
     * @param hotZone
     * @return
     */
    public ArrayList<HotZone> getHotZones(HotZone hotZone){
        ArrayList<HotZone> resultList = new ArrayList<HotZone>();
        
        String sql = "select * from hotzone where type like ? "
                + "and zone like ? "
                + "and isSeason = ? "
                + "and isTotal = ? ";
        
        //如果不是isTotal，对语句中加入teamNameEn项
        //其中包含对pstm的赋值
        if(hotZone.isTotal()){
            try {
                pstm = conn.prepareStatement(sql);
                pstm.setBoolean(4, true);
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
        }
        else{
            sql += "and teamNameEn like ? ";
            try {
                pstm = conn.prepareStatement(sql);
                pstm.setBoolean(4, false);
                pstm.setString(5, Tools.getParameterString(hotZone.getTeamNameEn()));
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            
            
        }
        
        try {
            pstm.setString(1, Tools.getParameterString(hotZone.getType()));
            pstm.setString(2, Tools.getParameterString(hotZone.getZone()));
            pstm.setBoolean(3, hotZone.isSeason());
            
            rs = pstm.executeQuery();
            
            while(rs.next()){
                resultList.add(new HotZone(rs.getString("teamNameEn"),
                        rs.getString("type"),
                        rs.getString("zone"),
                        rs.getBoolean("isSeason"),
                        rs.getBoolean("isTotal"),
                        rs.getInt("attempted"),
                        rs.getInt("made"),
                        rs.getDouble("pct"),
                        rs.getDouble("disPct")));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        
        return resultList;
    }
    
    public void init(){
        conn = GetConnection.getConnection();
    }
}
