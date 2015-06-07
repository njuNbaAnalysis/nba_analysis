package logic.players;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;

import compare.PlayerAssistsComp;
import compare.PlayerBlockShotsComp;
import compare.PlayerPointsComp;
import compare.PlayerReboundsComp;
import compare.todayPlayerComp;
import logic.matches.matchBLcontrollor;
import logic.teams.TeamNameList;
import po.TeamListItem;
import po.match;
import po.matchItem;
import po.player;
import vo.Matchvo;
import vo.RecordOfPlayervo;
import vo.TodayPlayervo;
import vo.playerItem;
import vo.Playervo;
import data.matches.MatchReader;
import data.players.PlayerItemReader;
import data.players.PlayerReader;

public class playerBLcontrollor {

	private ArrayList<PlayersBuff> BUffList = new ArrayList<PlayersBuff>();
	private static playerBLcontrollor playerBLcontrollor = null;
	private PlayerReader playerReader;
	private PlayerItemReader playerItemReader;

	private playerBLcontrollor() {
		playerReader = new PlayerReader();
		playerItemReader = new PlayerItemReader();
	}

	public static playerBLcontrollor getInstance() {
		if (playerBLcontrollor != null) {
			return playerBLcontrollor;
		} else {
			playerBLcontrollor = new playerBLcontrollor();
			return playerBLcontrollor;
		}
	}

	private ArrayList<Playervo> checkisexit(String Season, boolean isplayoff) {
		for (int i = 0; i < BUffList.size(); i++) {
			if ((BUffList.get(i).getIsplayoff() == isplayoff)
					&& BUffList.get(i).getSeason().equals(Season))
				return BUffList.get(i).getPlayerlist();
		}
		return null;
	}

	private Playervo changePlayertoVO(player p, String season, boolean isplayoff) {
		Playervo result = null;
		playerItem temp = p.getCurrentPlayerItem();
		if (temp != null) {
			TeamNameList lsit = TeamNameList.getIntance();
			TeamListItem team = lsit.getTeamListItem(temp.getTeam());
			String location = "";
			String division = "";
			String conference = "";
			if (team != null) {
				location = team.getHomecourt();
				division = team.getDivision();
				conference = team.getConference();
			}
			double[] upgradeRate = { 0, 0, 0 };
			result = new Playervo(p.getPid(), p.getName(), p.getNumber(),
					p.getPosition(), p.getHeight(), p.getWeight(),
					p.getBirthday(), p.getSelected(), p.getSalary(),
					p.getHighschool(), p.getUniversity(), temp.getTeam(),
					location, division, conference, temp.getGameplayed(),
					temp.getGamestarted(), temp.getRebounds(),
					temp.getAssists(), temp.getTime(),
					temp.getOffenseRebounds(), temp.getDefenseRebounds(),
					temp.getSteals(), temp.getBlockShots(), temp.getTurnOver(),
					temp.getFouls(), temp.getPoints(),
					temp.getFieldGoalsPercent(), temp.getThreePointPercent(),
					temp.getFreethrowPercent(), temp.getTrueshootingPercent(),
					temp.getReboundsPercentage(),
					temp.getOffenseReboundsPercentage(),
					temp.getDefenseReboundsPercentage(),
					temp.getAssistsPercentage(), temp.getStealsPercentage(),
					temp.getBlockShotsPercentage(),
					temp.getTurnOverPercentage(), temp.getUsage(), upgradeRate,
					temp.getPER(), temp.getShootingEfficiency(),
					temp.getFieldGoalsHit(), temp.getFieldGoalsAttempt(),
					temp.getThreethrowHit(), temp.getThreethrowAttempt(),
					temp.getFreethrowHit(), temp.getFreethrowAttempt(),
					temp.getWS(), temp.getOffenseWS(), temp.getDefenseWS(),
					temp.getDunk(), temp.getDunked());
		}
		return result;
	}

	public ArrayList<Playervo> getAllPlayers(String Season, boolean isPlayOff) {
		// TODO Auto-generated method stub
		if (checkisexit(Season, isPlayOff) == null) {
			ArrayList<player> list = playerReader.getPlayersBySeason(Season,
					isPlayOff);
			ArrayList<Playervo> result = new ArrayList<Playervo>();
			for (int i = 0; i < list.size(); i++) {
				result.add(changePlayertoVO(list.get(i), Season, isPlayOff));
			}
			BUffList.add(new PlayersBuff(isPlayOff, result, Season));
			return result;
		} else
			return checkisexit(Season, isPlayOff);
	}

	public Playervo getPlayerById(String Id, String season, boolean isplayoff) {
		// TODO Auto-generated method stub
		ArrayList<Playervo> temp = getAllPlayers(season, isplayoff);
		for (int i = 0; i < temp.size(); i++) {
			if (temp.get(i).getPid().equals(Id))
				return temp.get(i);
		}
		return null;
	}

	public Playervo getPlayerById(String id) {
		// TODO Auto-generated method stub
		player p = playerReader.getPlayerById(id);
		ArrayList<playerItem> list = playerItemReader.getPlayerItemById(id);
		Playervo result = new Playervo(p.getPid(), p.getName(), p.getNumber(),
				p.getPosition(), p.getHeight(), p.getWeight(), p.getBirthday(),
				p.getSelected(), p.getSalary(), p.getHighschool(),
				p.getUniversity(), list);
		return result;
	}

	public double[] getAlliancePlayerAverageData(String season, // 场均得分，场均篮板，场均助攻，罚球%，三分%
			boolean isplayoff) {
		// TODO Auto-generated method stub
		ArrayList<Playervo> temp = getAllPlayers(season, isplayoff);
		double points = 0;
		double rebounds = 0;
		double assist = 0;
		double freeThrowHit = 0;
		double freeThrowAttempt = 0;
		double threeGoalsHit = 0;
		double threeGoalsAttempt = 0;
		int gameplay = 0;
		for (int i = 0; i < temp.size(); i++) {
			points += temp.get(i).getPoints();
			rebounds += temp.get(i).getRebounds();
			assist += temp.get(i).getAssists();
			freeThrowHit += temp.get(i).getFreeThrowHits();
			freeThrowAttempt += temp.get(i).getFreeThrowAttempts();
			threeGoalsHit += temp.get(i).getThreePointerHits();
			threeGoalsAttempt += temp.get(i).getThreePointerAttempts();
			gameplay += temp.get(i).getGamePlayed();
		}
		if (temp.size() != 0) {
			return new double[] { points / gameplay, rebounds / gameplay,
					assist / gameplay, freeThrowHit / freeThrowAttempt,
					threeGoalsHit / threeGoalsAttempt };
		} else {
			return new double[] { 0, 0, 0, 0, 0 };
		}
	}

	public Playervo getPlayerByNameAndTeam(String playerName) {
		// TODO Auto-generated method stub
		PlayerNameList namelist = PlayerNameList.getIntance();
		String Pid = namelist.getIdByEnAbbr(playerName);
		if (Pid == null) {
			return null;

		}
		return getPlayerById(Pid);
	}

	public ArrayList<Playervo> getSeasonKingPlayer(String transferField,
			int number, String season, boolean isplayoff) {
		// TODO Auto-generated method stub
		ArrayList<Playervo> result = new ArrayList<Playervo>();
		ArrayList<Playervo> list = getAllPlayers(season, isplayoff);
		if (list.size() >= number) {
			switch (transferField) {
			case "point":
				Collections.sort(list, new PlayerPointsComp());
				break;
			case "rebound":
				Collections.sort(list, new PlayerReboundsComp());
				break;
			case "assist":
				Collections.sort(list, new PlayerAssistsComp());
				break;
			case "steal":
				Collections.sort(list, new PlayerAssistsComp());
				break;
			case "blockShot":
				Collections.sort(list, new PlayerBlockShotsComp());
				break;
			default:
				System.out.println("Error in getSeasonKingPlayer!!!");
			}
			for (int i = 0; i < number; i++) {
				result.add(list.get(i));
			}
		}
		return result;
	}

	public ArrayList<TodayPlayervo> getTodayKingPlayer(String date,
			String transferField, int number) {
		ArrayList<Matchvo> list = matchBLcontrollor.getInstance()
				.getTodayMatched(date);
		while (list.size() == 0) {
			DeclarationTime(date);
			list = matchBLcontrollor.getInstance().getTodayMatched(date);
		}
		ArrayList<TodayPlayervo> result = new ArrayList<TodayPlayervo>();
		for (int i = 0; i < list.size(); i++) {
			ArrayList<RecordOfPlayervo> temp1 = list.get(i)
					.getFirstRecordList();
			ArrayList<RecordOfPlayervo> temp2 = list.get(i)
					.getFirstRecordList();
			for (int j = 0; j < temp1.size(); j++) {
				result.add(new TodayPlayervo(temp1.get(i).getPid(), temp1
						.get(j).getPlayerName(), list.get(i).getTeams()[0],
						temp1.get(j).getPoints(), temp1.get(j).getRebounds(),
						temp1.get(j).getAssists(), temp1.get(j).getSteals(),
						temp1.get(j).getBlocks()));
			}
			for (int j = 0; j < temp2.size(); j++) {
				result.add(new TodayPlayervo(temp2.get(i).getPid(), temp2
						.get(j).getPlayerName(), list.get(i).getTeams()[1],
						temp2.get(j).getPoints(), temp2.get(j).getRebounds(),
						temp2.get(j).getAssists(), temp2.get(j).getSteals(),
						temp2.get(j).getBlocks()));
			}
		}
		Collections.sort(result, new todayPlayerComp(transferField));
		ArrayList<TodayPlayervo> temp = new ArrayList<TodayPlayervo>();
		if (result.size() >= number) {
			for (int i = 0; i < number; i++) {
				temp.add(result.get(i));
			}
		}
		return temp;
	}

	private String DeclarationTime(String date) {
		// TODO Auto-generated method stub
		GregorianCalendar gc = new GregorianCalendar();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		Date d = null;
		try {
			d = sf.parse(date.substring(6));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gc.setTime(d);
		gc.add(5, -1);
		gc.set(gc.get(Calendar.YEAR), gc.get(Calendar.MONTH),
				gc.get(Calendar.DATE));
		return date.substring(0, 6) + sf.format(gc.getTime());
	}

}
