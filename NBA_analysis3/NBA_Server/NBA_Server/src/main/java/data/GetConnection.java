package data;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetConnection {
    public static Connection connection = null;
    
    public static Connection getConnection(){
        if(connection == null){
            connection = connect();
        }
        return connection;
    }
    
	private static Connection connect() { // 链接数据库
		Connection conn = null;
		String driverName = "com.mysql.jdbc.Driver";
		String dbURL = "jdbc:mysql://172.26.192.164:3306/nba?characterEncoding=utf-8";

		// "jdbc:mysql://192.168.1.102:3306/nba?characterEncoding=utf-8"

		String userName = "admin";
		String userPwd = "123";
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

	public static void free(ResultSet rs, Connection conn,PreparedStatement pstm) { // 断开数据库
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("关闭ResultSet失败！");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("关闭Connection失败!");
				e.printStackTrace();
			}
		}
	}

	public static void free(ResultSet rs, Connection conn, Statement stmt) { // 断开数据库
		// TODO Auto-generated method stub
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			System.out.println("关闭ResultSet失败！");
			e.printStackTrace();
		} finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				System.out.println("关闭Connection失败!");
				e.printStackTrace();
			}
		}
	}
}
