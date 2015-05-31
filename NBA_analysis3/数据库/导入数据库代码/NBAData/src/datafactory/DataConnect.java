package datafactory;

import java.sql.Connection;
import java.sql.DriverManager;

public class DataConnect {
	private static Connection conn = DataConnect.GetConnection();

	public static Connection GetConnection() {
		if(!(conn==null)){
			return conn;
		}

		// 椹卞姩绋嬪簭鍚�
		String driver = "com.mysql.jdbc.Driver";

		// URL鎸囧悜瑕佽闂殑鏁版嵁搴撳悕scutcs
		String url = "jdbc:mysql://192.168.1.102:3306/nba?characterEncoding=utf-8";

		// MySQL閰嶇疆鏃剁殑鐢ㄦ埛鍚�
		String user = "admin";

		// MySQL閰嶇疆鏃剁殑瀵嗙爜
		String password = "123";
		
		
		try {
			// 鍔犺浇椹卞姩绋嬪簭
			Class.forName(driver);
			System.out.println(driver.getClass());
			// 杩炵画鏁版嵁搴�
			conn = DriverManager.getConnection(url, user, password);
			System.out.println(conn.getClass());
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database!");
		} catch (Exception e) {
			System.out.println("try connect to the Database!");
		}
		return conn;
	}
}
