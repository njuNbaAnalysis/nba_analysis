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
import po.player;
import po.playerItem;
import vo.EventVo;
import vo.Matchvo;
import vo.Playervo;
import vo.RecordOfPlayervo;

public class matchBLcontrollor {

	private MatchItemReader matchItemReader = null;
	private MatchReader matchReader = null;
	private pointsItemReader pointsItemReader = null;
	
	private static matchBLcontrollor matchController = null;

	private matchBLcontrollor() {
		matchReader = new MatchReader();
		matchItemReader = new MatchItemReader();
		pointsItemReader = new pointsItemReader();
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

	public Collection<? extends Matchvo> getTodayMatched(String string) {
		// TODO Auto-generated method stub
//		String season = string.substring(0, 5);
		String date = string.substring(6);
		ArrayList<match> list = matchReader.getMatchesByTime(date);
		return null;
	}

}
