package logic.players;

import java.util.ArrayList;

import logic.teams.TeamController;
import logic.teams.TeamNameList;
import po.TeamListItem;
import po.player;
import po.playerItem;
import vo.Playervo;
import vo.Teamvo;
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

	private Playervo changePlayertoVO(player p,String season,boolean isplayoff) {
		Playervo result = null;
		playerItem temp = p.getCurrentPlayerItem();
		if (temp != null) {
			TeamNameList lsit = TeamNameList.getIntance();
			TeamListItem team = lsit.getTeamListItem(temp.getTeam());
			String location = "";
			String division = "";
			String conference = "";
			if(team!=null){
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
					temp.getFreethrowHit(), temp.getFieldGoalsAttempt(),
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
				result.add(changePlayertoVO(list.get(i),Season,isPlayOff));
			}
			return result;
		} else
			return checkisexit(Season, isPlayOff);
	}

	public Playervo getPlayerById(String Id, String season,
			boolean isplayoff) {
		// TODO Auto-generated method stub
		ArrayList<Playervo> temp = getAllPlayers(season, isplayoff);
		for(int i=0;i<temp.size();i++){
			if(temp.get(i).getPid().equals(Id))
				return temp.get(i);
		}
		return null;
	}

}
