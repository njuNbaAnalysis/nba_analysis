package NBAServerTest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import data.GetConnection;
import logic.teams.TeamNameList;
import po.match;
import po.matchItem;

public class NBAServerTest {
	
	private static Connection GetConnect() { // 链接数据库
		Connection conn = null;
		String driverName = "com.mysql.jdbc.Driver";


		String dbURL = "jdbc:mysql://localhost/nbaserver?characterEncoding=utf-8";


		String userName = "root";
		String userPwd = "";
		try {
			Class.forName(driverName);
			System.out.println("加载驱动成功");
			conn = DriverManager.getConnection(dbURL, userName, userPwd);
			System.out.println("连接数据库成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			System.out.print("装载驱动失败");
			System.exit(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.print("连接数据库失败");
			System.exit(1);
		}

		return conn;
	}
	
	public static void main(String[] args){
		double current = System.currentTimeMillis();
		Connection conn =GetConnect();
		ArrayList<matchItem> result = new ArrayList<matchItem>();
		double now = System.currentTimeMillis();
		System.out.println(now - current+"   "+result.size());
		ResultSet rs = null;
		Statement statement = null;

		current = System.currentTimeMillis();
		try {
			statement = conn.createStatement();
			rs = statement
					.executeQuery("select * from  matchitem where  Mid = 01230");
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
		
		now = System.currentTimeMillis();
		System.out.println(now - current+"   "+result.size());
//		return result;？
		
	

	
	}
}
