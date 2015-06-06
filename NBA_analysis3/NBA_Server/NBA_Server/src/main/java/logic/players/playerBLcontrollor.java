package logic.players;

import java.util.ArrayList;

import logic.teams.TeamNameList;
import po.TeamListItem;
import po.player;
import vo.playerItem;
import vo.Playervo;
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

	public Playervo getPlayerByNameAndTeam(String playerName, String teamName) {
		// TODO Auto-generated method stub
		player p = playerReader.getPlayerByNameAndTeam(playerName,teamName);
		ArrayList<playerItem> list = playerItemReader.getPlayerItemById(p.getPid());
		Playervo result = new Playervo(p.getPid(), p.getName(), p.getNumber(),
				p.getPosition(), p.getHeight(), p.getWeight(), p.getBirthday(),
				p.getSelected(), p.getSalary(), p.getHighschool(),
				p.getUniversity(), list);
		return result;
	}

}
