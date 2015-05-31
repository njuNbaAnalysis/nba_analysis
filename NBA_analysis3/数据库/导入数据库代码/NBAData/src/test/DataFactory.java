package test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

public class DataFactory {

	private static Connection conn = DataConnect.GetConnection();

	public static void getallPlyayer() {
		File file = new File("C:/Users/John/Desktop/NBA/player");
		String[] list = file.list();
		for (String token : list) {
			String Pid = token.substring(0, 5);

			String name = "";
			String position = "";
			double height = -1;
			double weight = -1;
			String birthday = "";
			String city = "";
			String highschool = "";
			String university = "";
			String number = "";
			String selected = ""; // 选秀情况
			String salary = "";
			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						new FileInputStream("C:/Users/John/Desktop/NBA/player/"
								+ token), "UTF-8"));
				String data = null;
				while ((data = br.readLine()) != null) {
					String[] str = data.split("::");
					if (str.length == 2) {
						switch (str[0]) {
						case "全　　名":
							name = str[1];
							break;
						case "位　　置":
							position = str[1];
							break;
						case "身　　高":
							height = Double.parseDouble(str[1].substring(0, 4));
							break;
						case "体　　重 ":
							weight = Double.parseDouble(str[1].substring(0, 3));
							break;
						case "出生日期":
							birthday = str[1];
							break;
						case "出生城市":
							city = str[1];
							break;
						case "高　　中 ":
							highschool = str[1];
							break;
						case "大　　学":
							university = str[1];
							break;
						case "球衣号码":
							number = str[1].substring(0, str[1].indexOf(' '));
							break;
						case "当前薪金":
							salary = str[1].substring(0, str[1].indexOf('<'));
							break;
						case "选秀情况":
							selected = str[1];
							break;
						default:
							if (str[0].equals("高　　中")) {
								highschool = str[1];
							} else if (str[0].equals("体　　重")) {
								if (str[1].charAt(2) <= '9'
										&& str[1].charAt(2) >= '0')
									weight = Double.parseDouble(str[1]
											.substring(0, 3));
								else
									weight = Double.parseDouble(str[1]
											.substring(0, 2));
							}
							break;
						}
					} else if (data.equals("选秀情况::")) {
						data = br.readLine();
						if (data.equals("")) {
							data = br.readLine();
						}
						selected = data.trim();
					} else {
						data = data.trim();
						if (data.length() > 0) {
							System.out.println(data);
							String[] num = data.split(";");
							System.out.println(num.length);
							if (num.length >= 78) {
								System.out.println(num[1] + "  " + Pid);
								insertplayerItem(num, Pid);
							}
						}
					}
				}
				System.out.println(Pid + "after");
				insertplayerlist(new player(Pid, name, position, height,
						weight, birthday, city, highschool, university, number,
						selected, salary));

			} catch (Exception e) {
				System.out.println(e + "   " + Pid);
			}

		}
	}

	public static void insertplayerItem(String[] num, String Pid) {
		boolean isplayoff = false;
		if (num.length == 82 || num.length == 78)
			isplayoff = true;
		Connection conn = DataConnect.GetConnection();
		String sql = "insert into playeritem(`Pid`, `isplayoff`, `season`, `team`, `gameplayed`, `gamestarted`, `averageTime`, `fieldGoalsPercent`, `averagefieldGoalsHit`, `averagefieldGoalsAttempt`, `threePointPercent`, `averagethreePointHit`, `averagethreePointAttempt`, `freethrowPercent`, `averagefreethrowHit`, `averagefreethrowAttempt`, `averagerebounds`, `averageoffenseRebounds`, `averagedefenseRebounds`, `averageassisit`, `averagesteals`, `averageblockShots`, `averageturnOver`, `averagefouls`, `averagepoints`, `wins`, `defeats`, `fieldGoalsHit`, `fieldGoalsAttempt`, `threethrowHit`, `threethrowAttempt`, `freethrowHit`, `freethrowAttempt`, `rebounds`, `offenseRebounds`, `defenseRebounds`, `time`, `assists`, `steals`, `blockShots`, `turnOver`, `fouls`, `points`, `reboundsPercentage`, `offenseReboundsPercentage`, `defenseReboundsPercentage`, `assistsPercentage`, `stealsPercentage`, `blockShotsPercentage`, `turnOverPercentage`, `usage`, `offenseEfficiency`, `defenseEfficiency`, `WS`, `offenseWS`, `defenseWS`, `PER`, `dunk`, `riseRate`, `dunked`, `distance`, `Baskethitrate`, `averageBaskethit`, `averageBasketattempt`, `BasketPercent`, `Shorthitrate`, `averageShorthit`, `averageShortattempt`, `ShortPercent`, `Middlehitrate`, `averageMiddlehit`, `averageMiddleattempt`, `MiddlePercent`, `Longhitrate`, `averageLonghit`, `averageLongattempt`, `LongPercent`, `trueshootingPercent`, `shootingEfficiency`) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

		int dx = 0;
		if (isplayoff) {
			dx = -1;
		}
		try {
			PreparedStatement pstm = (PreparedStatement) conn
					.prepareStatement(sql);
			pstm.setString(1, Pid);
			pstm.setBoolean(2, isplayoff);
			pstm.setString(3, num[0]);
			pstm.setString(4, num[1]);
			pstm.setInt(5, getInt(num[2]));
			if (!isplayoff)
				pstm.setInt(6, getInt(num[3]));
			else
				pstm.setInt(6, -1);
			pstm.setDouble(7, getDouble(num[4 + dx]));
			pstm.setDouble(8, getDouble(num[5 + dx]));
			pstm.setDouble(9, getDouble(num[6 + dx]));
			pstm.setDouble(10, getDouble(num[7 + dx]));
			pstm.setDouble(11, getDouble(num[8 + dx]));
			pstm.setDouble(12, getDouble(num[9 + dx]));
			pstm.setDouble(13, getDouble(num[10 + dx]));
			pstm.setDouble(14, getDouble(num[11 + dx]));
			pstm.setDouble(15, getDouble(num[12 + dx]));
			pstm.setDouble(16, getDouble(num[13 + dx]));
			pstm.setDouble(17, getDouble(num[14 + dx]));
			pstm.setDouble(18, getDouble(num[15 + dx]));
			pstm.setDouble(19, getDouble(num[16 + dx]));
			pstm.setDouble(20, getDouble(num[17 + dx]));
			pstm.setDouble(21, getDouble(num[18 + dx]));
			pstm.setDouble(22, getDouble(num[19 + dx]));
			pstm.setDouble(23, getDouble(num[20 + dx]));
			pstm.setDouble(24, getDouble(num[21 + dx]));
			pstm.setDouble(25, getDouble(num[22 + dx]));
			pstm.setInt(26, getInt(num[23 + dx]));
			pstm.setInt(27, getInt(num[24 + dx]));
			if (isplayoff)
				dx = -2;
			if (num.length == 80)
				dx = -2;
			if (num.length == 78)
				dx = -4;
			pstm.setInt(28, getInt(num[29 + dx]));
			pstm.setInt(29, getInt(num[30 + dx]));
			pstm.setInt(30, getInt(num[32 + dx]));
			pstm.setInt(31, getInt(num[33 + dx]));
			pstm.setInt(32, getInt(num[35 + dx]));
			pstm.setInt(33, getInt(num[36 + dx]));
			pstm.setInt(34, getInt(num[37 + dx]));
			pstm.setInt(35, getInt(num[38 + dx]));
			pstm.setInt(36, getInt(num[39 + dx]));
			pstm.setDouble(37, getDouble(num[27 + dx]));
			pstm.setInt(38, getInt(num[40 + dx]));
			pstm.setInt(39, getInt(num[41 + dx]));
			pstm.setInt(40, getInt(num[42 + dx]));
			pstm.setInt(41, getInt(num[43 + dx]));
			pstm.setInt(42, getInt(num[44 + dx]));
			pstm.setInt(43, getInt(num[45 + dx]));
			if (num.length == 80)
				dx = -4;
			if (num.length == 78)
				dx = -6;
			pstm.setDouble(44, getDouble(num[48 + dx]));
			pstm.setDouble(45, getDouble(num[49 + dx]));
			pstm.setDouble(46, getDouble(num[50 + dx]));
			pstm.setDouble(47, getDouble(num[51 + dx]));
			pstm.setDouble(48, getDouble(num[52 + dx]));
			pstm.setDouble(49, getDouble(num[53 + dx]));
			pstm.setDouble(50, getDouble(num[54 + dx]));
			pstm.setDouble(51, getDouble(num[55 + dx]));
			pstm.setDouble(52, getDouble(num[56 + dx]));
			pstm.setDouble(53, getDouble(num[57 + dx]));
			pstm.setDouble(54, getDouble(num[58 + dx]));
			pstm.setDouble(55, getDouble(num[59 + dx]));
			pstm.setDouble(56, getDouble(num[60 + dx]));
			pstm.setDouble(57, getDouble(num[61 + dx]));
			pstm.setInt(58, getInt(num[62 + dx]));
			pstm.setDouble(59, getDouble(num[63 + dx]));
			pstm.setInt(60, getInt(num[64 + dx]));
			pstm.setDouble(61, getDouble(num[65 + dx]));
			pstm.setDouble(62, getDouble(num[66 + dx]));
			pstm.setDouble(63, getDouble(num[67 + dx]));
			pstm.setDouble(64, getDouble(num[68 + dx]));
			pstm.setDouble(65, getDouble(num[69 + dx]));
			pstm.setDouble(66, getDouble(num[70 + dx]));
			pstm.setDouble(67, getDouble(num[71 + dx]));
			pstm.setDouble(68, getDouble(num[72 + dx]));
			pstm.setDouble(69, getDouble(num[73 + dx]));
			pstm.setDouble(70, getDouble(num[74 + dx]));
			pstm.setDouble(71, getDouble(num[75 + dx]));
			pstm.setDouble(72, getDouble(num[76 + dx]));
			pstm.setDouble(73, getDouble(num[77 + dx]));
			pstm.setDouble(74, getDouble(num[78 + dx]));
			pstm.setDouble(75, getDouble(num[79 + dx]));
			pstm.setDouble(76, getDouble(num[80 + dx]));
			pstm.setDouble(77, getDouble(num[81 + dx]));
			pstm.setDouble(78, getDouble(num[82 + dx]));
			pstm.setDouble(79, getDouble(num[83 + dx]));
			pstm.addBatch();
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void insertplayerlist(player p) {
		Connection conn = DataConnect.GetConnection();

		String sql = "insert into playerlist(Pid,name,position,height,weight,birthday,city,highschool,university,number,selected,salary) values(?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			PreparedStatement pstm = (PreparedStatement) conn
					.prepareStatement(sql);
			pstm.setString(1, p.getPid());
			pstm.setString(2, p.getName());
			pstm.setString(3, p.getPosition());
			pstm.setDouble(4, p.getHeight());
			pstm.setDouble(5, p.getWeight());
			pstm.setString(6, p.getBirthday());
			pstm.setString(7, p.getCity());
			pstm.setString(8, p.getHighschool());
			pstm.setString(9, p.getUniversity());
			pstm.setString(10, p.getNumber());
			pstm.setString(11, p.getSelected());
			pstm.setString(12, p.getSalary());
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// ResultSet rs = null;
		// PreparedStatement pstm = null;
		// Statement statement = null;
		//
		// try {
		// statement = conn.createStatement();
		// rs = statement.executeQuery("select * from playerlist");
		//
		// while (rs.next()) {
		// System.out.println(rs.getString(3));
		// }
		// } catch (SQLException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

	}

	public static void getallmatches() {
		ArrayList<match> listofmatch = new ArrayList<match>();
		ArrayList<matchItem> listofmatchItem = new ArrayList<matchItem>();
		ArrayList<pointsItem> listofpointsItem = new ArrayList<pointsItem>();
		int numberOfId = 1;
		double current = System.currentTimeMillis();

		File file = new File("C:/Users/John/Desktop/NBA/match");
		String[] list = file.list();
		for (String token : list) {
			int period = 1;
			String season = "";
			boolean isplayoff = true;
			boolean ishome = false;
			String Mid = String.format("%05d", numberOfId);
			String date = token.substring(0, 10);
			String temp = token.substring(10, token.indexOf('.'));
			String[] s1 = temp.split("-");
			int i = 0;
			for (; i < s1[0].length(); i++) {
				if (s1[0].charAt(i) >= 48 && s1[0].charAt(i) <= 57) {
					if (i + 3 >= s1[0].length())
						break;
					if (s1[0].substring(i, i + 3).equals("76人")) {
						i += 2;
					} else
						break;
				}
			}
			String home_team = s1[0].substring(0, i);
			int home_points = Integer.parseInt(s1[0].substring(i));
			for (i = 0; i < s1[1].length(); i++) {
				if (!(s1[1].charAt(i) >= 48 && s1[1].charAt(i) <= 57)) {
					break;
				} else if (s1[1].substring(i, s1[1].length()).equals("76人")) {
					break;
				}
			}
			String away_team = s1[1].substring(i, s1[1].length());
			int away_points = Integer.parseInt(s1[1].substring(0, i));

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(
						new FileInputStream("C:/Users/John/Desktop/NBA/match/"
								+ token), "UTF-8"));
				String data = br.readLine();
				season = br.readLine().trim();
				data = br.readLine().trim();
				if (data.equals("常规赛"))
					isplayoff = false;
				data = br.readLine();
				while ((data = br.readLine()) != null) {
					String[] str = data.split(";");
					if (str.length > 1) {
						if (str[0].equals("0"))
							ishome = !ishome;
						listofmatchItem.add(new matchItem(Mid, ishome, str));
						// insertmatchitem(Mid, ishome, str);
					} else {
						str = data.split("-");
						listofpointsItem.add(new pointsItem(Mid, period,
								Integer.parseInt(str[0].trim()), Integer
										.parseInt(str[1].trim())));
						// insertpointslist(Mid, period,
						// Integer.parseInt(str[0].trim()),
						// Integer.parseInt(str[1].trim()));
						period++;
					}

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			System.out.println(numberOfId);
			listofmatch.add(new match(Mid, date, season, home_team, away_team,
					isplayoff, home_points, away_points));
			// insertmatchlist(new match(Mid, date, season, home_team,
			// away_team,
			// isplayoff, home_points, away_points));
			numberOfId++;
		}
		double now = System.currentTimeMillis();
		System.out.println("time: " + (now - current));
		System.out.println("listofmatch: " + listofmatch.size());
		System.out.println("listofmatchItem: " + listofmatchItem.size());
		System.out.println("listofpointsItem: " + listofpointsItem.size());
		// insertmatchlist(listofmatch);
		// insertpointslist(listofpointsItem);
		int abc = listofmatchItem.size() / 10;
//		for (int i = 5; i < 10; i++) {
//			insertmatchitem(listofmatchItem, 0 + abc * i, abc * (i + 1));
//			System.out.println(listofmatchItem.get(abc*(i+1)-1).getMid()+"  :  "+i);
//		}
		insertmatchitem(listofmatchItem, abc*10, listofmatchItem.size());
	}

	public static void insertmatchitem(ArrayList<matchItem> listofmatchItem,
			int begin, int end) {
		// Connection conn = DataConnect.GetConnection();
		System.out.println("MatchItem begin!!!!!");
		double current = System.currentTimeMillis();
		StringBuffer sql = new StringBuffer();
		sql.append("insert into matchitem(`Mid`, `ishome`, `Pid`, `isstart`, `time`, `fieldGoalspercent`, `fieldGoalsAttempt`, `fieldGoalsHit`, `threepointpercent`, `threepointAttempt`, `threepointHit`, `freethrowpercent`, `freethrowAttempt`, `freethrowHit`, `trueshootingpercent`, `rebounds`, `offenseRebounds`, `defenseRebounds`, `assists`, `steals`, `blockShots`, `turnOver`, `fouls`, `points`) "
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
		String temp = ",(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		try {
			for (int i = begin; i < end - 1; i++) {
				sql.append(temp);
				System.out.println(i);
			}
			System.out.println("String end!!");
			PreparedStatement pstm = (PreparedStatement) conn
					.prepareStatement(sql.toString());
			System.out.println("sql begin!!");
			for (int i = 0; i < end-begin; i++) {
				matchItem m = listofmatchItem.get(i+begin);
				String Mid = m.getMid();
				System.out.println(Mid);
				Boolean ishome = m.getIshome();
				String[] num = m.getNum();
				pstm.setString(1 + i * 24, Mid);
				pstm.setBoolean(2 + i * 24, ishome);
				pstm.setString(3 + i * 24, num[1]);
				boolean isstart = false;
				if (num[3].trim().equals("1"))
					isstart = true;
				pstm.setBoolean(4 + i * 24, isstart);
				pstm.setDouble(5 + i * 24, getDouble(num[4]));
				pstm.setDouble(6 + i * 24, getDouble(num[5]));
				pstm.setInt(7 + i * 24, getInt(num[6]));
				pstm.setInt(8 + i * 24, getInt(num[7]));
				pstm.setDouble(9 + i * 24, getDouble(num[8]));
				pstm.setInt(10 + i * 24, getInt(num[9]));
				pstm.setInt(11 + i * 24, getInt(num[10]));
				pstm.setDouble(12 + i * 24, getDouble(num[11]));
				pstm.setInt(13 + i * 24, getInt(num[12]));
				pstm.setInt(14 + i * 24, getInt(num[13]));
				pstm.setDouble(15 + i * 24, getDouble(num[14]));
				pstm.setInt(16 + i * 24, getInt(num[15]));
				pstm.setInt(17 + i * 24, getInt(num[16]));
				pstm.setInt(18 + i * 24, getInt(num[17]));
				pstm.setInt(19 + i * 24, getInt(num[18]));
				pstm.setInt(20 + i * 24, getInt(num[19]));
				pstm.setInt(21 + i * 24, getInt(num[20]));
				pstm.setInt(22 + i * 24, getInt(num[21]));
				pstm.setInt(23 + i * 24, getInt(num[22]));
				pstm.setInt(24 + i * 24, getInt(num[23]));
			}
			pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		double now = System.currentTimeMillis();
		System.out.println("MatchItem end!!!!!Time: " + (now - current));
	}

	public static void insertpointslist(ArrayList<pointsItem> listofpointsItem) {
		// Connection conn = DataConnect.GetConnection();
		System.out.println("pointsItem begin!!!!!");
		double current = System.currentTimeMillis();
		String sql = "insert into pointslist(`Mid`, `period`, `home-points`, `away-points`) "
				+ "values(?,?,?,?)";
		String temp = ",(?,?,?,?)";
		try {
			for (int i = 0; i < listofpointsItem.size() - 1; i++) {
				sql += temp;
			}
			PreparedStatement pstm = (PreparedStatement) conn
					.prepareStatement(sql);
			for (int i = 0; i < listofpointsItem.size(); i++) {
				pointsItem p = listofpointsItem.get(i);
				pstm.setString(1 + i * 4, p.getMid());
				pstm.setInt(2 + i * 4, p.getPeriod());
				pstm.setInt(3 + i * 4, p.getHome_points());
				pstm.setInt(4 + i * 4, p.getAway_points());
			}
			pstm.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}
		double now = System.currentTimeMillis();
		System.out.println("pointsItem end!!!!!Time: " + (now - current));
	}

	public static void insertmatchlist(ArrayList<match> listofmatch) {
		// Connection conn = DataConnect.GetConnection();

		System.out.println("listofmatch begin!!!!!");
		double current = System.currentTimeMillis();
		// String sql =
		// "insert into matchlist(`Mid`, `date`, `season`, `home-team`, `away-team`, `isplayoff`, `home-points`, `away-points`) values(?,?,?,?,?,?,?,?)";
		String sql = "insert into matchlist(`Mid`, `date`, `season`, `home-team`, `away-team`, `isplayoff`, `home-points`, `away-points`) values(?,?,?,?,?,?,?,?)";
		String temp = ",(?,?,?,?,?,?,?,?)";
		try {
			for (int i = 0; i < listofmatch.size() - 1; i++) {
				sql += temp;
			}
			PreparedStatement pstm = (PreparedStatement) conn
					.prepareStatement(sql);
			for (int i = 0; i < listofmatch.size(); i++) {
				match m = listofmatch.get(i);
				pstm.setString(1 + i * 8, m.getMid());
				pstm.setString(2 + i * 8, m.getDate());
				pstm.setString(3 + i * 8, m.getSeason());
				pstm.setString(4 + i * 8, m.getHome_team());
				pstm.setString(5 + i * 8, m.getAway_team());
				pstm.setBoolean(6 + i * 8, m.isIsplayoff());
				pstm.setInt(7 + i * 8, m.getHome_points());
				pstm.setInt(8 + i * 8, m.getAway_points());
			}
			pstm.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double now = System.currentTimeMillis();
		System.out.println("listofmatch end!!!!!Time: " + (now - current));
	}

	private static double getDouble(String str) {
		// System.out.println(str+"aaaaaaaaaaaaaaaaaaaaaaaaaaaaaa");
		if (str.equals("")) {
			return -1;
		} else if (str.equals(" ")) {
			return -1;
		} else if (str.charAt(str.length() - 1) == '%') {
			return Double.parseDouble(str.substring(0, str.length() - 1));
		} else {
			return Double.parseDouble(str);
		}
	}

	private static int getInt(String str) {
		// System.out.println(str+"bbbbbbbbbbbbbbbbbbbbbbbbbbbb"+str);
		if (str.equals("")) {
			return -1;
		} else if (str.equals(" ")) {
			return -1;
		} else {
			return Integer.parseInt(str);
		}
	}

}
