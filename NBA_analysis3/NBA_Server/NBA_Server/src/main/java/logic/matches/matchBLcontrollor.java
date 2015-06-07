package logic.matches;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

import data.matches.MatchItemReader;
import data.matches.MatchReader;
import data.matches.pointsItemReader;
import po.match;
import po.matchItem;
import vo.MatchSimpleInfovo;
import vo.Matchvo;
import vo.RecordOfPlayervo;

public class matchBLcontrollor {

	private ArrayList<MatchBuff> BuffList;
	private MatchItemReader matchItemReader = null;
	private MatchReader matchReader = null;
	private pointsItemReader pointsItemReader = null;

	private static matchBLcontrollor matchController = null;

	private matchBLcontrollor() {
		matchReader = new MatchReader();
		matchItemReader = new MatchItemReader();
		pointsItemReader = new pointsItemReader();
		BuffList = new ArrayList<MatchBuff>();
	}

	public static matchBLcontrollor getInstance() {
		if (matchController != null) {
			return matchController;
		} else {
			matchController = new matchBLcontrollor();
			return matchController;
		}
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

	private Matchvo checkisexit(match temp) { // 判断是否在缓冲区，如果在，则返回，否则加进缓冲区
		for (int i = 0; i < BuffList.size(); i++) {
			if (BuffList.get(i).getMid().equals(temp.getMid()))
				return BuffList.get(i).getMatchvo();
		}
		temp.setPointsItemList(pointsItemReader.getpointsItemById(temp.getMid()));
		temp.setMatchItemList(matchItemReader.getMatchItemById(temp.getMid()));
		Matchvo result = changematchToMatchvo(temp);
		BuffList.add(new MatchBuff(temp.getMid(), result));
		return result;
	}

	public void loadNewMatchvo(int start) { // 缓存某一段时间的所有数据
		ArrayList<match> list = matchReader.getAllMatch();
		for (int i = 0; i < 1000; i++) {
			System.out.println(list.get(i).getMid());
			checkisexit(list.get(i + start));
		}
	}

	private Matchvo changematchToMatchvo(match m) {
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

	public Collection<? extends Matchvo> getTodayMatched(String string) {
		// TODO Auto-generated method stub
		// String season = string.substring(0, 5);
		String date = string.substring(6);
		ArrayList<match> list = matchReader.getMatchesByTime(date);
		ArrayList<Matchvo> result = new ArrayList<Matchvo>();
		for (int i = 0; i < list.size(); i++) {
			match temp = list.get(i);
			result.add(checkisexit(temp));
		}
		return result;
	}

	public ArrayList<MatchSimpleInfovo> getMatchSimpleInfo(String teamName,
			String season) {
		// TODO Auto-generated method stub
		ArrayList<MatchSimpleInfovo> result = new ArrayList<MatchSimpleInfovo>();
		ArrayList<match> list = matchReader.getMatchesByTeam(teamName, season);
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
		ArrayList<match> list = matchReader.getMatchesByTeam(teamNameEn,
				season, isPlayOff, 10);
		ArrayList<Matchvo> result = new ArrayList<Matchvo>();
		for (int i = 0; i < list.size(); i++) {
			match temp = list.get(i);
			result.add(checkisexit(temp));
		}
		return result;
	}
}
