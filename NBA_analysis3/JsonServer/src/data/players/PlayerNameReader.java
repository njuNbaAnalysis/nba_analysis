package data.players;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import po.PlayerName;
import data.GetConnection;

public class PlayerNameReader {

	public ArrayList<PlayerName> getAllPlayerName() {
		ArrayList<PlayerName> result = new ArrayList<PlayerName>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from pnameofnbastat");
			while (rs.next()) {
				result.add(new PlayerName(rs.getString(1), rs.getString(2), rs
						.getString(3)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// GetConnection.free(rs, conn, statement);

		return result;
	}
}
