package logic.matches;

import java.awt.List;
import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import data.matches.MatchItemReader;
import data.matches.MatchReader;
import data.matches.pointsItemReader;
import po.match;
import po.matchItem;
import po.pointsItem;
import vo.MatchSimpleInfovo;
import vo.Matchvo;
import vo.RecordOfPlayervo;

public class matchBLcontrollor {

	private ArrayList<MatchBuff> BuffList;
	private MatchReader matchReader = null;
	private pointsItemReader pointsItemReader = null;

	private static matchBLcontrollor matchController = null;

	private matchBLcontrollor() {
		matchReader = new MatchReader();
		pointsItemReader = new pointsItemReader();
		BuffList = new ArrayList<MatchBuff>();
		BuffList.add(new MatchBuff("14-15", getAllMatchBySeason("14-15")));
	}

	public static matchBLcontrollor getInstance() {
		if (matchController != null) {
			return matchController;
		} else {
			matchController = new matchBLcontrollor();
			return matchController;
		}
	}

	private ArrayList<match> checkisexit(String season) { // 判断是否在缓冲区，如果在，则返回，否则加进缓冲区
		for (int i = 0; i < BuffList.size(); i++) {
			if ((BuffList.get(i).getSeason().equals(season)))
				return BuffList.get(i).getMatchlist();
		}
		ArrayList<match> result = getAllMatchBySeason(season);
		BuffList.add(new MatchBuff(season, result));
		return result;
	}

	private void SetPointList(match m) {
		// TODO Auto-generated method stub
		ArrayList<pointsItem> pointlist = pointsItemReader.getpointsItemById(m
				.getMid());
		m.setPointsItemList(pointlist);
	}

	private Matchvo changematchToMatchvo(match m) {
		SetPointList(m);
		int[] points = { m.getHome_points(), m.getAway_points() };
		String[] teams = { m.getHome_team(), m.getAway_team() };
		ArrayList<int[]> pointsList = new ArrayList<int[]>();
		for (int i = 0; i < m.getPointsItemList().size(); i++) {
			pointsList.add(m.getPointsItemList().get(i).getPoints());
		}
		ArrayList<RecordOfPlayervo> firstRecordList = new ArrayList<RecordOfPlayervo>();
		ArrayList<RecordOfPlayervo> secondRecordList = new ArrayList<RecordOfPlayervo>();
		for (int i = 0; i < m.getMatchItemList().size(); i++) {
			matchItem temp = m.getMatchItemList().get(i);
			if (temp.isIshome()) {
				firstRecordList.add(new RecordOfPlayervo(temp.getPid(), temp
						.getPid(), temp.getTime(), temp.getFieldGoalsHit(),
						temp.getFieldGoalsAttempt(), temp.getThreepointHit(),
						temp.getThreepointAttempt(), temp.getFreethrowHit(),
						temp.getFreethrowAttempt(), temp.getOffenseRebounds(),
						temp.getDefenseRebounds(), temp.getRebounds(), temp
								.getAssists(), temp.getSteals(), temp
								.getBlockShots(), temp.getTurnOver(), temp
								.getFouls(), temp.getPoints(),
						temp.isIsstart(), temp.getThreepointpercent(), temp
								.getFreethrowpercent(), temp
								.getFieldGoalspercent()));
			} else {
				secondRecordList.add(new RecordOfPlayervo(temp.getPid(), temp
						.getPid(), temp.getTime(), temp.getFieldGoalsHit(),
						temp.getFieldGoalsAttempt(), temp.getThreepointHit(),
						temp.getThreepointAttempt(), temp.getFreethrowHit(),
						temp.getFreethrowAttempt(), temp.getOffenseRebounds(),
						temp.getDefenseRebounds(), temp.getRebounds(), temp
								.getAssists(), temp.getSteals(), temp
								.getBlockShots(), temp.getTurnOver(), temp
								.getFouls(), temp.getPoints(),
						temp.isIsstart(), temp.getThreepointpercent(), temp
								.getFreethrowpercent(), temp
								.getFieldGoalspercent()));
			}
		}
		Matchvo result = new Matchvo(m.getMid(), m.getSeason() + "_"
				+ m.getDate(), m.isIsplayoff(), teams, points, pointsList,
				firstRecordList, secondRecordList);
		return result;
	}

	private ArrayList<match> getAllMatchBySeason(String season) {
		ArrayList<match> result =  matchReader.getMatchesBySeason(season);
		return result;
	}

	public ArrayList<Matchvo> getTodayMatched(String string) {
		// TODO Auto-generated method stub
		String season = string.substring(0, 5);
		ArrayList<match> list = checkisexit(season);
		String date = string.substring(6);
		ArrayList<Matchvo> result = new ArrayList<Matchvo>();

		ArrayList<String> list2 = matchReader.getMatchesByTime(date);

		for (int i = 0; i < list2.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list2.get(i).equals(list.get(j).getMid()))
					result.add(changematchToMatchvo(list.get(j)));
			}
		}
		return result;
	}

	public ArrayList<MatchSimpleInfovo> getMatchSimpleInfo(String teamName,
			String season) {
		// TODO Auto-generated method stub
		ArrayList<MatchSimpleInfovo> result = new ArrayList<MatchSimpleInfovo>();
		ArrayList<match> list = matchReader.getMatchSimpleByTeam(teamName, season);
		for (int i = 0; i < list.size(); i++) {
			int[] points = new int[2];
			boolean isWin = false;
			boolean isAtHome = false;
			if (teamName.equals(list.get(i).getHome_team())) {
				isAtHome = true;
				points[0] = list.get(i).getHome_points();
				points[1] = list.get(i).getAway_points();
				if (points[0] > points[1])
					isWin = true;
			} else {
				isAtHome = false;
				points[1] = list.get(i).getHome_points();
				points[0] = list.get(i).getAway_points();
				if (points[0] > points[1])
					isWin = true;
			}
			result.add(new MatchSimpleInfovo(list.get(i).getDate(), isWin,
					points, teamName, isAtHome));
		}
		return null;
	}

	/**
	 * 得到最近十场比赛的信息 顺序:index = 0为最近一场，index=9为最远一场
	 * 
	 * @param teamNameEn
	 *            例"ATL"
	 * @param season
	 *            例"14-15"
	 * @param isPlayOff
	 * @return
	 */
	public ArrayList<Matchvo> getLast10Matches(String teamNameEn,
			String season, boolean isPlayOff) {
		ArrayList<match> list = checkisexit(season);
		ArrayList<String> list2 = matchReader.getMatchesByTeam(teamNameEn,
				season, isPlayOff, 10);
		ArrayList<Matchvo> result = new ArrayList<Matchvo>();
		System.out.println("testtest:"+list2.size());
		for (int i = 0; i < list2.size(); i++) {
			for (int j = 0; j < list.size(); j++) {
				if (list2.get(i).equals(list.get(j).getMid()))
					result.add(changematchToMatchvo(list.get(j)));
			}
		}
		return result;
	}

	public void updateNewMatch(String time) { // 只需每天运行一次
		// Date now = new Date();
		// SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		// String current = df.format(now).substring(0, 10);
		// System.out.println(df.format(now));
		// System.out.println(current);
		// System.out.println(time);
		try {
			ProcessBuilder pb = new ProcessBuilder("python",
					"Spider-NBA/NBAUpdate.py", time);
			Process p = pb.start();
			File log = new File("log.txt");
			pb.redirectErrorStream(true);
			pb.redirectOutput(ProcessBuilder.Redirect.to(log));
			System.out.println(p.waitFor());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
