package data.matches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.match;
import data.GetConnection;

public class MatchReader {

	public ArrayList<match> getAllMatch() { // 慎用！！！！
		ArrayList<match> result = new ArrayList<match>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from matchlist");
			while (rs.next()) {
				result.add(new match(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getString(5), rs
						.getBoolean(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GetConnection.free(rs, conn, statement);

		return result;
	}

	public ArrayList<match> getMatchesBySeason(String season,boolean isplayoff) { 
		ArrayList<match> result = new ArrayList<match>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from  matchlist where  season =  '"+season+"赛季' and isplayoff = "+isplayoff);
			while (rs.next()) {
				result.add(new match(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getString(5), rs
						.getBoolean(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GetConnection.free(rs, conn, statement);

		return result;
	}

	public ArrayList<match> getMatchesBySeason(String season) { 
		ArrayList<match> result = new ArrayList<match>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from  matchlist where  season =  '"+season+"赛季'");
			while (rs.next()) {
				result.add(new match(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getString(5), rs
						.getBoolean(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GetConnection.free(rs, conn, statement);

		return result;
	}
	
	public ArrayList<match> getMatchesByTime(String time){
		ArrayList<match> result = new ArrayList<match>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
//			SELECT * FROM playerlist p1, playeritem p2 WHERE p1.Pid = p2.Pid AND p2.season =  '"
//					+ season + "' AND p2.isplayoff = "+isPlayoff)
			rs = statement.executeQuery("select * from  matchlist m1, matchitem m2, pointslist p where  m1.date =  '"+time+"' AND m1.");
			while (rs.next()) {
				result.add(new match(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getString(5), rs
						.getBoolean(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GetConnection.free(rs, conn, statement);

		return result;
	}
	
	public match getMatchesById(String id){
		match result = null;

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from  matchlist where  Mid = "+id);
			while (rs.next()) {
				result = (new match(rs.getString(1), rs.getString(2), rs
						.getString(3), rs.getString(4), rs.getString(5), rs
						.getBoolean(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GetConnection.free(rs, conn, statement);

		return result;
	}

}
