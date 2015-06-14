package data.matches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import logic.teams.TeamNameList;
import po.match;
import po.matchItem;
import data.GetConnection;

public class MatchReader {

	/**
	 * 仅仅返回简略信息 返回值为Mid
	 * */
	public ArrayList<String> getAllMatch() { // 慎用！！！！
		ArrayList<String> result = new ArrayList<String>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("select * from matchlist group by date desc");
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GetConnection.free(rs, conn, statement);

		return result;
	}

	/**
	 * 返回处每节比分外所有信息
	 * */
	public ArrayList<match> getMatchesBySeason(String season, boolean isplayoff) {
		ArrayList<match> result = new ArrayList<match>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("select * from  matchlist m1,matchitem m2 where  m1.mid = m2.mid and m1.season =  '"
							+ season + "赛季' and isplayoff = " + isplayoff + "");
			if (rs.next()) {
				String Mid = rs.getString(1);
				TeamNameList list = TeamNameList.getIntance();
				String home_team = list.getEnAbbrByZhAbbr(rs.getString(4), rs
						.getString(3).substring(0, 5));
				String away_team = list.getEnAbbrByZhAbbr(rs.getString(5), rs
						.getString(3).substring(0, 5));
				match mtemp = new match(rs.getString(1), rs.getString(2),
						rs.getString(3), home_team, away_team,
						rs.getBoolean(6), rs.getInt(7), rs.getInt(8));
				ArrayList<matchItem> listItem = new ArrayList<matchItem>();
				listItem.add(new matchItem(rs.getString(1 + 8), rs
						.getBoolean(2 + 8), rs.getString(3 + 8), rs
						.getBoolean(4 + 8), rs.getDouble(5 + 8), rs
						.getDouble(6 + 8), rs.getInt(7 + 8), rs.getInt(8 + 8),
						rs.getDouble(9 + 8), rs.getInt(10 + 8), rs
								.getInt(11 + 8), rs.getDouble(12 + 8), rs
								.getInt(13 + 8), rs.getInt(14 + 8), rs
								.getDouble(15 + 8), rs.getInt(16 + 8), rs
								.getInt(17 + 8), rs.getInt(18 + 8), rs
								.getInt(19 + 8), rs.getInt(20 + 8), rs
								.getInt(21 + 8), rs.getInt(22 + 8), rs
								.getInt(23 + 8), rs.getInt(24 + 8)));
				while (rs.next()) {
					if (Mid.equals(rs.getString(1))) {
						listItem.add(new matchItem(rs.getString(1 + 8), rs
								.getBoolean(2 + 8), rs.getString(3 + 8), rs
								.getBoolean(4 + 8), rs.getDouble(5 + 8), rs
								.getDouble(6 + 8), rs.getInt(7 + 8), rs
								.getInt(8 + 8), rs.getDouble(9 + 8), rs
								.getInt(10 + 8), rs.getInt(11 + 8), rs
								.getDouble(12 + 8), rs.getInt(13 + 8), rs
								.getInt(14 + 8), rs.getDouble(15 + 8), rs
								.getInt(16 + 8), rs.getInt(17 + 8), rs
								.getInt(18 + 8), rs.getInt(19 + 8), rs
								.getInt(20 + 8), rs.getInt(21 + 8), rs
								.getInt(22 + 8), rs.getInt(23 + 8), rs
								.getInt(24 + 8)));
					} else {
						mtemp.setMatchItemList(listItem);
						result.add(mtemp);
						Mid = rs.getString(1);
						home_team = list.getEnAbbrByZhAbbr(rs.getString(4), rs
								.getString(3).substring(0, 5));
						away_team = list.getEnAbbrByZhAbbr(rs.getString(5), rs
								.getString(3).substring(0, 5));
						mtemp = new match(rs.getString(1), rs.getString(2),
								rs.getString(3), home_team, away_team,
								rs.getBoolean(6), rs.getInt(7), rs.getInt(8));
						listItem = new ArrayList<matchItem>();
						listItem.add(new matchItem(rs.getString(1 + 8), rs
								.getBoolean(2 + 8), rs.getString(3 + 8), rs
								.getBoolean(4 + 8), rs.getDouble(5 + 8), rs
								.getDouble(6 + 8), rs.getInt(7 + 8), rs
								.getInt(8 + 8), rs.getDouble(9 + 8), rs
								.getInt(10 + 8), rs.getInt(11 + 8), rs
								.getDouble(12 + 8), rs.getInt(13 + 8), rs
								.getInt(14 + 8), rs.getDouble(15 + 8), rs
								.getInt(16 + 8), rs.getInt(17 + 8), rs
								.getInt(18 + 8), rs.getInt(19 + 8), rs
								.getInt(20 + 8), rs.getInt(21 + 8), rs
								.getInt(22 + 8), rs.getInt(23 + 8), rs
								.getInt(24 + 8)));
					}
				}
				mtemp.setMatchItemList(listItem);
				result.add(mtemp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GetConnection.free(rs, conn, statement);

		return result;
	}

	/**
	 * 返回处每节比分外所有信息
	 * */
	public ArrayList<match> getMatchesBySeason(String season) {
		ArrayList<match> result = new ArrayList<match>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("select * from  matchlist m1,matchitem m2 where  m1.mid = m2.mid and m1.season =  '"
							+ season + "赛季'");
			if (rs.next()) {
				String Mid = rs.getString(1);
				TeamNameList list = TeamNameList.getIntance();
				String home_team = list.getEnAbbrByZhAbbr(rs.getString(4), rs
						.getString(3).substring(0, 5));
				String away_team = list.getEnAbbrByZhAbbr(rs.getString(5), rs
						.getString(3).substring(0, 5));
				match mtemp = new match(rs.getString(1), rs.getString(2),
						rs.getString(3), home_team, away_team,
						rs.getBoolean(6), rs.getInt(7), rs.getInt(8));
				ArrayList<matchItem> listItem = new ArrayList<matchItem>();
				listItem.add(new matchItem(rs.getString(1 + 8), rs
						.getBoolean(2 + 8), rs.getString(3 + 8), rs
						.getBoolean(4 + 8), rs.getDouble(5 + 8), rs
						.getDouble(6 + 8), rs.getInt(7 + 8), rs.getInt(8 + 8),
						rs.getDouble(9 + 8), rs.getInt(10 + 8), rs
								.getInt(11 + 8), rs.getDouble(12 + 8), rs
								.getInt(13 + 8), rs.getInt(14 + 8), rs
								.getDouble(15 + 8), rs.getInt(16 + 8), rs
								.getInt(17 + 8), rs.getInt(18 + 8), rs
								.getInt(19 + 8), rs.getInt(20 + 8), rs
								.getInt(21 + 8), rs.getInt(22 + 8), rs
								.getInt(23 + 8), rs.getInt(24 + 8)));
				while (rs.next()) {
					if (Mid.equals(rs.getString(1))) {
						listItem.add(new matchItem(rs.getString(1 + 8), rs
								.getBoolean(2 + 8), rs.getString(3 + 8), rs
								.getBoolean(4 + 8), rs.getDouble(5 + 8), rs
								.getDouble(6 + 8), rs.getInt(7 + 8), rs
								.getInt(8 + 8), rs.getDouble(9 + 8), rs
								.getInt(10 + 8), rs.getInt(11 + 8), rs
								.getDouble(12 + 8), rs.getInt(13 + 8), rs
								.getInt(14 + 8), rs.getDouble(15 + 8), rs
								.getInt(16 + 8), rs.getInt(17 + 8), rs
								.getInt(18 + 8), rs.getInt(19 + 8), rs
								.getInt(20 + 8), rs.getInt(21 + 8), rs
								.getInt(22 + 8), rs.getInt(23 + 8), rs
								.getInt(24 + 8)));
					} else {
						mtemp.setMatchItemList(listItem);
						result.add(mtemp);
						Mid = rs.getString(1);
						home_team = list.getEnAbbrByZhAbbr(rs.getString(4), rs
								.getString(3).substring(0, 5));
						away_team = list.getEnAbbrByZhAbbr(rs.getString(5), rs
								.getString(3).substring(0, 5));
						mtemp = new match(rs.getString(1), rs.getString(2),
								rs.getString(3), home_team, away_team,
								rs.getBoolean(6), rs.getInt(7), rs.getInt(8));
						listItem = new ArrayList<matchItem>();
						listItem.add(new matchItem(rs.getString(1 + 8), rs
								.getBoolean(2 + 8), rs.getString(3 + 8), rs
								.getBoolean(4 + 8), rs.getDouble(5 + 8), rs
								.getDouble(6 + 8), rs.getInt(7 + 8), rs
								.getInt(8 + 8), rs.getDouble(9 + 8), rs
								.getInt(10 + 8), rs.getInt(11 + 8), rs
								.getDouble(12 + 8), rs.getInt(13 + 8), rs
								.getInt(14 + 8), rs.getDouble(15 + 8), rs
								.getInt(16 + 8), rs.getInt(17 + 8), rs
								.getInt(18 + 8), rs.getInt(19 + 8), rs
								.getInt(20 + 8), rs.getInt(21 + 8), rs
								.getInt(22 + 8), rs.getInt(23 + 8), rs
								.getInt(24 + 8)));
					}
				}
				mtemp.setMatchItemList(listItem);
				result.add(mtemp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GetConnection.free(rs, conn, statement);

		return result;
	}

	/**
	 * 仅仅返回简略信息 返回值为Mid
	 * */
	public ArrayList<String> getMatchesByTime(String time) {
		ArrayList<String> result = new ArrayList<String>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("select * from  matchlist where  date =  '"
							+ time + "'");
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GetConnection.free(rs, conn, statement);

		return result;
	}

	/**
	 * 仅仅返回简略信息 返回值为Mid
	 * */
	public match getMatchesById(String id) {
		match result = null;

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("select * from  matchlist where  Mid = " + id);
			while (rs.next()) {
				TeamNameList list = TeamNameList.getIntance();
				String home_team = list.getEnAbbrByZhAbbr(rs.getString(4), rs
						.getString(3).substring(0, 5));
				String away_team = list.getEnAbbrByZhAbbr(rs.getString(5), rs
						.getString(3).substring(0, 5));
				result = (new match(rs.getString(1), rs.getString(2),
						rs.getString(3), home_team, away_team,
						rs.getBoolean(6), rs.getInt(7), rs.getInt(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GetConnection.free(rs, conn, statement);

		return result;
	}

	/**
	 * 仅仅返回简略信息 返回值为Mid
	 * */
	public ArrayList<String> getMatchesByTeam(String teamNameEn, String season,
			boolean isPlayOff, int number) {
		ArrayList<String> result = new ArrayList<String>();
		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;
		TeamNameList list = TeamNameList.getIntance();
		String team = list.getZhAbbrByEnAbbr(teamNameEn);
		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("select * from  matchlist where (`home-team` =  '"
							+ team
							+ "' or `away-team` = '"
							+ team
							+ "') and season = '"
							+ season
							+ "赛季' and isplayoff = "
							+ isPlayOff
							+ " order by date desc limit " + number);
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GetConnection.free(rs, conn, statement);

		return result;
	}

	/**
	 * 仅仅返回简略信息 返回值为Mid
	 * */
	public ArrayList<String> getMatchesByTeam(String teamName, String season) {
		// TODO Auto-generated method stub
		ArrayList<String> result = new ArrayList<String>();
		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;
		TeamNameList list = TeamNameList.getIntance();
		String team = list.getZhAbbrByEnAbbr(teamName);
		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("select * from  matchlist where (`home-team` =  '"
							+ team
							+ "' or `away-team` = '"
							+ team
							+ "') and season = '" + season);
			while (rs.next()) {
				result.add(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GetConnection.free(rs, conn, statement);

		return result;
	}

	/**
	 * 仅仅返回部分简略信息
	 * */
	public ArrayList<match> getMatchSimpleByTeam(String teamName, String season) {
		// TODO Auto-generated method stub
		ArrayList<match> result = new ArrayList<match>();
		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;
		TeamNameList list = TeamNameList.getIntance();
		String team = list.getZhAbbrByEnAbbr(teamName);
		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("select * from  matchlist where (`home-team` =  '"
							+ team
							+ "' or `away-team` = '"
							+ team
							+ "') and season = '" + season + "赛季'");
			while (rs.next()) {
				String home_team = list.getEnAbbrByZhAbbr(rs.getString(4), rs
						.getString(3).substring(0, 5));
				String away_team = list.getEnAbbrByZhAbbr(rs.getString(5), rs
						.getString(3).substring(0, 5));
				result.add(new match(rs.getString(1), rs.getString(2), rs
						.getString(3), home_team, away_team, rs.getBoolean(6),
						rs.getInt(7), rs.getInt(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GetConnection.free(rs, conn, statement);

		return result;
	}

	public ArrayList<match> getMatchSimpleByTwoteams(String teamName1,
			String teamName2) {
		// TODO Auto-generated method stub
		ArrayList<match> result = new ArrayList<match>();
		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;
		TeamNameList list = TeamNameList.getIntance();
		String team1 = list.getZhAbbrByEnAbbr(teamName1);
		String team2 = list.getZhAbbrByEnAbbr(teamName2);
		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("select * from  matchlist where( (`home-team` =  '"
							+ team1
							+ "' and `away-team` = '"
							+ team2
							+ "') or (`home-team` =  '"
							+ team2
							+ "' and `away-team` = '"
							+ team1
							+ "') ) order by date desc");
			while (rs.next()) {
				String home_team = list.getEnAbbrByZhAbbr(rs.getString(4), rs
						.getString(3).substring(0, 5));
				String away_team = list.getEnAbbrByZhAbbr(rs.getString(5), rs
						.getString(3).substring(0, 5));
				result.add(new match(rs.getString(1), rs.getString(2), rs
						.getString(3), home_team, away_team, rs.getBoolean(6),
						rs.getInt(7), rs.getInt(8)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GetConnection.free(rs, conn, statement);

		return result;
	}
}
