package data.matches;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import logic.matches.Match;
import logic.matches.RecordOfPlayer;

public class MatchReader {
	public ArrayList<Match> read() {
		ArrayList<Match> listOfMatches = new ArrayList<Match>();

		double current = System.currentTimeMillis();

		File file = new File("Data/matches");
		String[] list = file.list();

		for (String token : list) {
			try {//
				BufferedReader br = new BufferedReader(new FileReader(new File(
						"Data/matches/" + token)));
				String data = br.readLine();
				if (data == null)
					break;
				else {
					// 初始化时间
					String dataOfTime = token.substring(0, 12);
					SimpleDateFormat sdf = new SimpleDateFormat("yy-yy_MM-dd_");// 小写的mm表示的是分钟
					Date date = null;
					try {
						date = sdf.parse(dataOfTime);
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// 初始化球队
					String NameOfTeams = token.substring(12, token.length());
					String[] teams = NameOfTeams.split("-");
					// 初始化总比分
					String[] str = data.split(";");
					String[] ponitOfGame = str[str.length - 1].split("-");
					int[] points = { Integer.parseInt(ponitOfGame[0]),
							Integer.parseInt(ponitOfGame[1]) };
					// 初始化分节比分数
					data = br.readLine();
					ArrayList<int[]> pointsList = new ArrayList<int[]>();
					if (data != null) {
						str = data.split(";");
						for (int i = 0; i < str.length; i++) {
							ponitOfGame = str[i].split("-");
							int[] temp = { Integer.parseInt(ponitOfGame[0]),
									Integer.parseInt(ponitOfGame[1]) };
							pointsList.add(temp);
						}
					}
					// 初始化主场球队信息：
					ArrayList<RecordOfPlayer> firstRecordList = new ArrayList<RecordOfPlayer>();
					data = br.readLine();// 可以在这里校验球队数据是否出错
					while ((data = br.readLine()) != null) {
						str = data.split(";");
						if (str.length == 1)
							break;
						int[] num = new int[str.length];
						if ((str[2].equals("")) || (str[2].equals("null"))||(str[2].equals("None"))) {
							num[2] = -1;
						} else {
							String[] temp = str[2].split(":");
							num[2] = Integer.parseInt(temp[0]) * 60
									+ Integer.parseInt(temp[1]);
						}
						for (int i = 3; i < str.length; i++) {
							if ((str[i].equals("")) || (str[i].equals("null"))||(str[i].equals("None")))
								num[i] = 0;
							else
								num[i] = Integer.parseInt(str[i]);
						}
						RecordOfPlayer temp = new RecordOfPlayer(str[0],
								str[1], num[2], num[3], num[4], num[5], num[6],
								num[7], num[8], num[9], num[10], num[11],
								num[12], num[13], num[14], num[15], num[16],
								num[17]);
						firstRecordList.add(temp);
					}
					// 初始化客场球队信息：
					ArrayList<RecordOfPlayer> secondRecordList = new ArrayList<RecordOfPlayer>();
					while ((data = br.readLine()) != null) {
						str = data.split(";");
						if (str.length != 18)
							break;
						int[] num = new int[str.length];
						if ((str[2].equals("")) || (str[2].equals("null"))||(str[2].equals("None")))
							num[2] = -1;
						else {
							String[] temp = str[2].split(":");
							num[2] = Integer.parseInt(temp[0])
									* Integer.parseInt(temp[1]);
						}
						for (int i = 3; i < str.length; i++) {
							if ((str[i].equals("")) || (str[i].equals("null"))||(str[i].equals("None")))
								num[i] = 0;
							else
								num[i] = Integer.parseInt(str[i]);
						}
						RecordOfPlayer temp = new RecordOfPlayer(str[0],
								str[1], num[2], num[3], num[4], num[5], num[6],
								num[7], num[8], num[9], num[10], num[11],
								num[12], num[13], num[14], num[15], num[16],
								num[17]);
						secondRecordList.add(temp);
					}
					listOfMatches.add(new Match(date, teams, points,
							pointsList, firstRecordList, secondRecordList));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		double now = System.currentTimeMillis();
		System.out.println(now - current);

		return listOfMatches;
	}
}
