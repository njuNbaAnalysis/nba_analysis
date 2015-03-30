package data.matches;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import util.Setting;
import data.matches.MatchMistake.Kind;
import logic.BLController;
import logic.matches.Match;
import logic.matches.RecordOfPlayer;

public class MatchReader {
	private ArrayList<Match> listOfMatches = new ArrayList<Match>();
	private String path = Setting.getPath() + "/matches/";

	public void init() {

		double current = System.currentTimeMillis();

		File file = new File(path);
		String[] list = file.list();

		for (String token : list) {
			try {
				BufferedReader br = new BufferedReader(new FileReader(new File(
				        path + token)));
				String data = br.readLine();
				if (data == null)
					break;
				else {
					// 初始化时间
					String dateOfTime = token.substring(0, 12);
//					SimpleDateFormat sdf = new SimpleDateFormat("yy-yy_MM-dd_");// 小写的mm表示的是分钟
//					Date date = null;
//					try {
//						date = sdf.parse(dataOfTime);
//					} catch (ParseException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
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
						str = data.split(";");   //代价很高
						for (int i = 0; i < str.length; i++) {
							ponitOfGame = str[i].split("-");
							int[] temp = { Integer.parseInt(ponitOfGame[0]),
									Integer.parseInt(ponitOfGame[1]) };
							pointsList.add(temp);
						}
					}
					ArrayList<MatchMistake> ListOfMistake = new ArrayList<MatchMistake>();
					// 初始化主场球队信息：
					ArrayList<RecordOfPlayer> firstRecordList = new ArrayList<RecordOfPlayer>();
					data = br.readLine();// 可以在这里校验球队数据是否出错
					int number = 1;
					while ((data = br.readLine()) != null) {
						str = data.split(";");
						if (str.length == 1)
							break;
						int[] num = new int[str.length];
						if ((str[2].equals("")) || (str[2].equals("null"))
								|| (str[2].equals("None"))) {
							num[2] = -1;
						} else {
							String[] temp = str[2].split(":");
							num[2] = Integer.parseInt(temp[0]) * 60
									+ Integer.parseInt(temp[1]);
						}
						for (int i = 3; i < str.length; i++) {
							if ((str[i].equals("")) || (str[i].equals("null"))
									|| (str[i].equals("None")))
								num[i] = 0;
							else
								num[i] = Integer.parseInt(str[i]);
						}
						// 判断是否有脏数据
						dealMistake(str[0], num, ListOfMistake);
						boolean isStarted = false;
						if (number <= 5)
							isStarted = true;
						else
							isStarted = false;
						RecordOfPlayer temp = new RecordOfPlayer(str[0],
								str[1], num[2], num[3], num[4], num[5], num[6],
								num[7], num[8], num[9], num[10], num[11],
								num[12], num[13], num[14], num[15], num[16],
								num[17], isStarted);
						firstRecordList.add(temp);
						number++;
					}
					// 初始化客场球队信息：
					number = 1;
					ArrayList<RecordOfPlayer> secondRecordList = new ArrayList<RecordOfPlayer>();
					while ((data = br.readLine()) != null) {
						str = data.split(";");
						if (str.length != 18)
							break;
						int[] num = new int[str.length];
						if ((str[2].equals("")) || (str[2].equals("null"))
								|| (str[2].equals("None")))
							num[2] = -1;
						else {
							String[] temp = str[2].split(":");
							num[2] = Integer.parseInt(temp[0])
									* Integer.parseInt(temp[1]);
						}
						for (int i = 3; i < str.length; i++) {
							if ((str[i].equals("")) || (str[i].equals("null"))
									|| (str[i].equals("None")))
								num[i] = 0;
							else
								num[i] = Integer.parseInt(str[i]);
						}
						dealMistake(str[0], num, ListOfMistake);
						boolean isStarted = false;
						if (number <= 5)
							isStarted = true;
						else
							isStarted = false;
						RecordOfPlayer temp = new RecordOfPlayer(str[0],
								str[1], num[2], num[3], num[4], num[5], num[6],
								num[7], num[8], num[9], num[10], num[11],
								num[12], num[13], num[14], num[15], num[16],
								num[17], isStarted);
						number++;
						secondRecordList.add(temp);
					}
					if (ListOfMistake.size() == 0)
						ListOfMistake = null;
					listOfMatches.add(new Match(dateOfTime, teams, points,
							pointsList, firstRecordList, secondRecordList,
							ListOfMistake));
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		double now = System.currentTimeMillis();
		System.out.println("matchReader:" + (now - current));

		BLController.progress++;
	}

	public ArrayList<Match> getMatchList() { // 若数据为-1，则表示缺省。迭代一中仅可能有上场时间缺省（未解决）和个人得分缺省（已解决）
		return listOfMatches;
	}

	private void dealMistake(String name, int[] num,
			ArrayList<MatchMistake> ListOfMistake) { // 剩余的错误放在逻辑中判断！！！
		if (num[9] + num[10] != num[11])
			ListOfMistake.add(new MatchMistake(name, Kind.REBOUNDS));
		boolean b = false;
		if (num[3] > num[4]) {
			ListOfMistake.add(new MatchMistake(name, Kind.FIELD_GOAL));
			b = true;
		}
		if (num[5] > num[6]) {
			ListOfMistake.add(new MatchMistake(name, Kind.THREE_POINTER));
			b = true;
		}
		if (num[7] > num[8]) {
			ListOfMistake.add(new MatchMistake(name, Kind.FREE_THROW));
			b = true;
		}
		if (!b) {
			if (((num[3] - num[5]) * 2 + num[5] * 3 + num[7]) != num[17]) {
				num[17] = ((num[3] - num[5]) * 2 + num[5] * 3 + num[7]);
				ListOfMistake.add(new MatchMistake(name, Kind.POINT));
			}
		}
	}

	public boolean isChanged(){
	    return false;
	}
}
