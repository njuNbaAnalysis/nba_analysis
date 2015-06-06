package logic.matches;

import java.io.File;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import data.matches.MatchItemReader;
import data.matches.MatchReader;
import data.matches.pointsItemReader;
import data.players.PlayerReader;
import po.match;
import po.matchItem;
import po.player;
import po.playerItem;
import po.pointsItem;
import vo.EventVo;
import vo.Matchvo;
import vo.Playervo;
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

	private Matchvo checkisexit(String Mid) {
		for (int i = 0; i < BuffList.size(); i++) {
			if (BuffList.get(i).getMid().equals(Mid))
				return BuffList.get(i).getMatchvo();
		}
		return null;
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
		Matchvo result = new Matchvo(m.getMid(),m.getSeason() + "_" + m.getDate(),
				m.isIsplayoff(), teams, points, pointsList, firstRecordList,
				secondRecordList);
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
			Matchvo mvo = checkisexit(temp.getMid());
			if (mvo == null) {
				temp.setPointsItemList(pointsItemReader.getpointsItemById(temp
						.getMid()));
				temp.setMatchItemList(matchItemReader.getMatchItemById(temp
						.getMid()));
				result.add(changematchToMatchvo(temp));
			} else {
				result.add(mvo);
			}
		}
		return result;
	}
	
	public Matchvo getMatchById(String mid) {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 得到最近十场比赛的信息
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
		return null;
	}

}
