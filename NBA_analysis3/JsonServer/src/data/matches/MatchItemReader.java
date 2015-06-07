package data.matches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.teams.TeamNameList;
import po.matchItem;
import data.GetConnection;

public class MatchItemReader {

	public ArrayList<matchItem> getMatchItemById(String id) {
		ArrayList<matchItem> result = new ArrayList<matchItem>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("select * from  matchitem where  Mid = " + id);
			while (rs.next()) {
				result.add(new matchItem(rs.getString(1), rs.getBoolean(2), rs
						.getString(3), rs.getBoolean(4), rs.getDouble(5), rs
						.getDouble(6), rs.getInt(7), rs.getInt(8), rs
						.getDouble(9), rs.getInt(10), rs.getInt(11), rs
						.getDouble(12), rs.getInt(13), rs.getInt(14), rs
						.getDouble(15), rs.getInt(16), rs.getInt(17), rs
						.getInt(18), rs.getInt(19), rs.getInt(20), rs
						.getInt(21), rs.getInt(22), rs.getInt(23), rs
						.getInt(24)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GetConnection.free(rs, conn, statement);

		return result;
	}
	
	public ArrayList<matchItem> getMatchItemByPlayerId(String playerId) {
		ArrayList<matchItem> result = new ArrayList<matchItem>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("select * from  matchitem where  Pid = " + playerId);
			while (rs.next()) {
				result.add(new matchItem(rs.getString(1), rs.getBoolean(2), rs
						.getString(3), rs.getBoolean(4), rs.getDouble(5), rs
						.getDouble(6), rs.getInt(7), rs.getInt(8), rs
						.getDouble(9), rs.getInt(10), rs.getInt(11), rs
						.getDouble(12), rs.getInt(13), rs.getInt(14), rs
						.getDouble(15), rs.getInt(16), rs.getInt(17), rs
						.getInt(18), rs.getInt(19), rs.getInt(20), rs
						.getInt(21), rs.getInt(22), rs.getInt(23), rs
						.getInt(24)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GetConnection.free(rs, conn, statement);

		return result;
	}

}
