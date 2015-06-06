package data.players;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.teams.TeamNameList;
import data.GetConnection;
import po.player;
import vo.playerItem;

public class PlayerReader {

	public player getPlayerById(String id) {
		player result = null;
		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from playerlist where Pid = "
					+ id);
			while (rs.next()) {
				result = new player(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getDouble(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getString(11),
						rs.getString(12));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GetConnection.free(rs, conn, statement);
		return result;
	}

	public ArrayList<player> getAllPlayer() {
		ArrayList<player> listOfPlayer = new ArrayList<player>();
		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from playerlist");

			while (rs.next()) {
				listOfPlayer.add(new player(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getDouble(4), rs.getDouble(5), rs
								.getString(6), rs.getString(7),
						rs.getString(8), rs.getString(9), rs.getString(10), rs
								.getString(11), rs.getString(12)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GetConnection.free(rs, conn, statement);
		return listOfPlayer;
	}

	public ArrayList<player> getPlayersBySeason(String season,boolean isPlayoff) {             //得到某个赛季所有比赛球员
		ArrayList<player> listOfPlayer = new ArrayList<player>();
		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("SELECT * FROM playerlist p1, playeritem p2 WHERE p1.Pid = p2.Pid AND p2.season =  '"
							+ season + "' AND p2.isplayoff = "+isPlayoff);
			while (rs.next()) {
				player p = new player(rs.getString(1), rs.getString(2),
						rs.getString(3), rs.getDouble(4), rs.getDouble(5),
						rs.getString(6), rs.getString(7), rs.getString(8),
						rs.getString(9), rs.getString(10), rs.getString(11),
						rs.getString(12));
				TeamNameList list = TeamNameList.getIntance();
				String team = list.getEnAbbrByFullZh(rs.getString(4+12));
				p.setCurrentPlayerItem(new playerItem(rs.getString(1+12), rs
						.getBoolean(2+12), rs.getString(3+12), team, rs
						.getInt(5+12), rs.getInt(6+12), rs.getDouble(7+12), rs
						.getDouble(8+12), rs.getDouble(9+12), rs.getDouble(10+12), rs
						.getDouble(11+12), rs.getDouble(12+12), rs.getDouble(13+12), rs
						.getDouble(14+12), rs.getDouble(15+12), rs.getDouble(16+12), rs
						.getDouble(17+12), rs.getDouble(18+12), rs.getDouble(19+12), rs
						.getDouble(20+12), rs.getDouble(21+12), rs.getDouble(22+12), rs
						.getDouble(23+12), rs.getDouble(24+12), rs.getDouble(25+12), rs
						.getInt(26+12), rs.getInt(27+12), rs.getInt(28+12), rs
						.getInt(29+12), rs.getInt(30+12), rs.getInt(31+12), rs
						.getInt(32+12), rs.getInt(33+12), rs.getInt(34+12), rs
						.getInt(35+12), rs.getInt(36+12), rs.getDouble(37+12), rs
						.getInt(38+12), rs.getInt(39+12), rs.getInt(40+12), rs
						.getInt(41+12), rs.getInt(42+12), rs.getInt(43+12), rs
						.getDouble(44+12), rs.getDouble(45+12), rs.getDouble(46+12), rs
						.getDouble(47+12), rs.getDouble(48+12), rs.getDouble(49+12), rs
						.getDouble(50+12), rs.getDouble(51+12), rs.getDouble(52+12), rs
						.getDouble(53+12), rs.getDouble(54+12), rs.getDouble(55+12), rs
						.getDouble(56+12), rs.getDouble(57+12), rs.getInt(58+12), rs
						.getDouble(59+12), rs.getInt(60+12), rs.getDouble(61+12), rs
						.getDouble(62+12), rs.getDouble(63+12), rs.getDouble(64+12), rs
						.getDouble(65+12), rs.getDouble(66+12), rs.getDouble(67+12), rs
						.getDouble(68+12), rs.getDouble(69+12), rs.getDouble(70+12), rs
						.getDouble(71+12), rs.getDouble(72+12), rs.getDouble(73+12), rs
						.getDouble(74+12), rs.getDouble(75+12), rs.getDouble(76+12), rs
						.getDouble(77+12), rs.getDouble(78+12), rs.getDouble(79+12)));
				listOfPlayer.add(p);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GetConnection.free(rs, conn, statement);
		return listOfPlayer;
	}
}
