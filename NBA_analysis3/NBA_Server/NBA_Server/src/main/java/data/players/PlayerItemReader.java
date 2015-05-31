package data.players;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.GetConnection;
import po.playerItem;

public class PlayerItemReader {

	public ArrayList<playerItem> getPlayerItemById(String id) {
		ArrayList<playerItem> result = new ArrayList<playerItem>();
		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from playeritem where Pid = "
					+ id);

			while (rs.next()) {
				result.add(new playerItem(rs.getString(1), rs.getBoolean(2), rs
						.getString(3), rs.getString(4), rs.getInt(5), rs
						.getInt(6), rs.getDouble(7), rs.getDouble(8), rs
						.getDouble(9), rs.getDouble(10), rs.getDouble(11), rs
						.getDouble(12), rs.getDouble(13), rs.getDouble(14), rs
						.getDouble(15), rs.getDouble(16), rs.getDouble(17), rs
						.getDouble(18), rs.getDouble(19), rs.getDouble(20), rs
						.getDouble(21), rs.getDouble(22), rs.getDouble(23), rs
						.getDouble(24), rs.getDouble(25), rs.getInt(26), rs
						.getInt(27), rs.getInt(28), rs.getInt(29), rs
						.getInt(30), rs.getInt(31), rs.getInt(32), rs
						.getInt(33), rs.getInt(34), rs.getInt(35), rs
						.getInt(36), rs.getDouble(37), rs.getInt(38), rs
						.getInt(39), rs.getInt(40), rs.getInt(41), rs
						.getInt(42), rs.getInt(43), rs.getDouble(44), rs
						.getDouble(45), rs.getDouble(46), rs.getDouble(47), rs
						.getDouble(48), rs.getDouble(49), rs.getDouble(50), rs
						.getDouble(51), rs.getDouble(52), rs.getDouble(53), rs
						.getDouble(54), rs.getDouble(55), rs.getDouble(56), rs
						.getDouble(57), rs.getInt(58), rs.getDouble(59), rs
						.getInt(60), rs.getDouble(61), rs.getDouble(62), rs
						.getDouble(63), rs.getDouble(64), rs.getDouble(65), rs
						.getDouble(66), rs.getDouble(67), rs.getDouble(68), rs
						.getDouble(69), rs.getDouble(70), rs.getDouble(71), rs
						.getDouble(72), rs.getDouble(73), rs.getDouble(74), rs
						.getDouble(75), rs.getDouble(76), rs.getDouble(77), rs
						.getDouble(78), rs.getDouble(79)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GetConnection.free(rs, conn, statement);
		return result;
	}
	
	public ArrayList<playerItem> getPlayerItemBySeason(String season) {
		ArrayList<playerItem> result = new ArrayList<playerItem>();
		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from  playeritem where  season =  '"+season+"'");
			while (rs.next()) {
				result.add(new playerItem(rs.getString(1), rs.getBoolean(2), rs
						.getString(3), rs.getString(4), rs.getInt(5), rs
						.getInt(6), rs.getDouble(7), rs.getDouble(8), rs
						.getDouble(9), rs.getDouble(10), rs.getDouble(11), rs
						.getDouble(12), rs.getDouble(13), rs.getDouble(14), rs
						.getDouble(15), rs.getDouble(16), rs.getDouble(17), rs
						.getDouble(18), rs.getDouble(19), rs.getDouble(20), rs
						.getDouble(21), rs.getDouble(22), rs.getDouble(23), rs
						.getDouble(24), rs.getDouble(25), rs.getInt(26), rs
						.getInt(27), rs.getInt(28), rs.getInt(29), rs
						.getInt(30), rs.getInt(31), rs.getInt(32), rs
						.getInt(33), rs.getInt(34), rs.getInt(35), rs
						.getInt(36), rs.getDouble(37), rs.getInt(38), rs
						.getInt(39), rs.getInt(40), rs.getInt(41), rs
						.getInt(42), rs.getInt(43), rs.getDouble(44), rs
						.getDouble(45), rs.getDouble(46), rs.getDouble(47), rs
						.getDouble(48), rs.getDouble(49), rs.getDouble(50), rs
						.getDouble(51), rs.getDouble(52), rs.getDouble(53), rs
						.getDouble(54), rs.getDouble(55), rs.getDouble(56), rs
						.getDouble(57), rs.getInt(58), rs.getDouble(59), rs
						.getInt(60), rs.getDouble(61), rs.getDouble(62), rs
						.getDouble(63), rs.getDouble(64), rs.getDouble(65), rs
						.getDouble(66), rs.getDouble(67), rs.getDouble(68), rs
						.getDouble(69), rs.getDouble(70), rs.getDouble(71), rs
						.getDouble(72), rs.getDouble(73), rs.getDouble(74), rs
						.getDouble(75), rs.getDouble(76), rs.getDouble(77), rs
						.getDouble(78), rs.getDouble(79)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		GetConnection.free(rs, conn, statement);
		return result;
	}
	
	

}
