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
import data.matches.MatchMistake;
import data.matches.MatchMistake.Kind;
import logic.BLController;
import logic.matches.Match;
import logic.matches.RecordOfPlayer;

public class MatchReader {
	public static int numberOFbefore;
	public static int numberOFcurrent;
	private ArrayList<Match> listOfMatches = new ArrayList<Match>();
	private String path = Setting.getPath() + "/matches/";

	public void init() {

		double current = System.currentTimeMillis();

		File file = new File(path);
		String[] list = file.list();
		numberOFbefore = list.length;
		readText(list);
		double now = System.currentTimeMillis();
		System.out.println("matchsort:" + (now - current));
		BLController.progress++;
	}

	public void readText(String[] list) {
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
					String strtemp = dateOfTime.substring(6, 8);
					if (Integer.parseInt(strtemp) >= 10)
						dateOfTime = dateOfTime.substring(0, 6) + "20"
								+ dateOfTime.substring(0, 2) + "-"
								+ dateOfTime.substring(6, 11);
					else
						dateOfTime = dateOfTime.substring(0, 6) + "20"
								+ dateOfTime.substring(3, 5) + "-"
								+ dateOfTime.substring(6, 11);
					// SimpleDateFormat sdf = new
					// SimpleDateFormat("yy-yy_MM-dd_");// 小写的mm表示的是分钟
					// Date date = null;
					// try {
					// date = sdf.parse(dataOfTime);
					// } catch (ParseException e) {
					// // TODO Auto-generated catch block
					// e.printStackTrace();
					// }
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
					ArrayList<MatchMistake> ListOfMistake = new ArrayList<MatchMistake>();
					// 初始化主场球队信息：
					ArrayList<RecordOfPlayer> firstRecordList = new ArrayList<RecordOfPlayer>();
					data = br.readLine();// 可以在这里校验球队数据是否出错
					int number = 1;
					while ((data = br.readLine()) != null) {
						// str = data.split(";");
						str = fastSplit(data, ';');
						// if (str.length == 1)
						// break;
						if (str[2] == null)
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
						// str = data.split(";");
						str = fastSplit(data, ';');
						// if (str.length != 18)
						// break;
						if (str[2] == null)
							break;
						int[] num = new int[str.length];
						if ((str[2].equals("")) || (str[2].equals("null"))
								|| (str[2].equals("None")))
							num[2] = -1;
						else {
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
		if (num[2] == -1) {
			ListOfMistake.add(new MatchMistake(name, Kind.TIME));
		}
	}

	private String[] fastSplit(final String text, char separator) {
		String[] result = new String[18];

		int num = 0;
		if (text != null && text.length() > 0) {
			int index1 = 0;
			int index2 = text.indexOf(separator);
			while (index2 >= 0) {
				result[num] = text.substring(index1, index2);
				index1 = index2 + 1;
				index2 = text.indexOf(separator, index1);
				num++;
			}

			if (index1 < text.length() - 1) {
				result[num] = text.substring(index1);
			}
		}// else: input unavailable

		return result;
	}

	public boolean isChanged() {
		File file = new File(path);
		String[] list = file.list();
		numberOFcurrent = list.length;
		if (numberOFcurrent < numberOFbefore) {
			System.out
					.println("Error in DataReader:the number of Data is declined!!!");
		} else if (numberOFcurrent == numberOFbefore) {
			return false;
		} else {
			String[] str = new String[numberOFcurrent - numberOFbefore];
			int numberOfbreSeason = -1; // 表示赛季前半段比赛次数
			if (list[0].charAt(6) == '0') {
				numberOfbreSeason = 0;
				for (int i = 0; i < list.length; i++) {
					if (list[i].charAt(6) != '0')
						numberOfbreSeason++;
				}
			}
			for (int i = numberOFbefore; i < numberOFcurrent; i++) {
				if (numberOfbreSeason > 0 && i >= numberOfbreSeason) {
					str[i - numberOFbefore] = list[i - numberOfbreSeason]; // 466暂时硬编码，以后再想办法。
				} else {
					str[i - numberOFbefore] = list[i];
				}
			}
			readText(str);
			return true;
		}

		return false;
	}
}
