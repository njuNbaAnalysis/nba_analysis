package data.matches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.NBALivePlayer;
import po.NBALiveTeam;
import data.GetConnection;

public class NBALiveReader {
	
	public ArrayList<NBALivePlayer> getAllNBALivePlayer() {
		ArrayList<NBALivePlayer> result = new ArrayList<NBALivePlayer>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from playernamelist");
			while (rs.next()) {
				result.add(new NBALivePlayer(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GetConnection.free(rs, conn, statement);

		return result;
	}
	
	public ArrayList<NBALiveTeam> getAllNBALiveTeam() {
		ArrayList<NBALiveTeam> result = new ArrayList<NBALiveTeam>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from teamchineselist");
			while (rs.next()) {
				result.add(new NBALiveTeam(rs.getString(1), rs.getString(2), rs.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GetConnection.free(rs, conn, statement);

		return result;
	}
	
}
