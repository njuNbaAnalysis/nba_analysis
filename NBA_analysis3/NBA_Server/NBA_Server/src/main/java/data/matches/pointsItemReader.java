package data.matches;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import po.pointsItem;
import data.GetConnection;

public class pointsItemReader {
	
	public ArrayList<pointsItem> getpointsItemById(String id){
		ArrayList<pointsItem> result = new ArrayList<pointsItem>();

		Connection conn = GetConnection.getConnection();
		ResultSet rs = null;
		Statement statement = null;

		try {
			statement = conn.createStatement();
			rs = statement.executeQuery("select * from  pointslist where  Mid = "+id);
			while (rs.next()) {
				result.add(new pointsItem(rs.getString(1), rs.getInt(2), rs.getInt(3), rs.getInt(4)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		GetConnection.free(rs, conn, statement);

		return result;
	}
	
	
}
