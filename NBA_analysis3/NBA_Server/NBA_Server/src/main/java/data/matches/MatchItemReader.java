package data.matches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.teams.TeamNameList;
import po.matchItem;
import vo.RecordOfPlayervo;
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
		// GetConnection.free(rs, conn, statement);

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
					.executeQuery("select * from  matchitem where  Pid = "
							+ playerId);
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
		// GetConnection.free(rs, conn, statement);

		return result;
	}

	public ArrayList<RecordOfPlayervo> getRecordOfPlayerById(String pid) {
		// TODO Auto-generated method stub
		ArrayList<RecordOfPlayervo> result = new ArrayList<RecordOfPlayervo>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("select * from  matchitem m1,matchlist m2  where  m1.Pid = "
							+ pid
							+ " and m1.Mid = m2.Mid order by m2.date  desc limit 5");
			while (rs.next()) {
				RecordOfPlayervo temp = new RecordOfPlayervo(rs.getString(3),
						rs.getString(3), rs.getDouble(5), rs.getInt(7),
						rs.getInt(8), rs.getInt(10), rs.getInt(11),
						rs.getInt(13), rs.getInt(14), rs.getInt(17),
						rs.getInt(18), rs.getInt(16), rs.getInt(19),
						rs.getInt(20), rs.getInt(21), rs.getInt(22),
						rs.getInt(23), rs.getInt(24), rs.getBoolean(4),
						rs.getDouble(9), rs.getDouble(12), rs.getDouble(6));
				temp.setMid(rs.getString(1));
				temp.setDate(rs.getString(26));
				if (rs.getString(28).equals("湖人")) {
					temp.setAway_Team(rs.getString(29));
				}else{
					temp.setAway_Team(rs.getString(28));
				}
				result.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return result;
	}

}
