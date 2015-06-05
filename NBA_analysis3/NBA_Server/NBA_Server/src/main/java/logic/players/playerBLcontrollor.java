package logic.players;

import java.io.Serializable;
import java.util.ArrayList;

import po.player;
import po.playerItem;
import vo.Playervo;
import data.players.PlayerItemReader;
import data.players.PlayerReader;

public class playerBLcontrollor {

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

	private Playervo changePlayertoVO(player p) {
		Playervo result = null;
		playerItem temp = p.getCurrentPlayerItem();
		if (temp != null) {
			int doubledouble = 0;
			int threedouble = 0;
			String location = "";
			String division = "";
			char conference = ' ';
			double[] upgradeRate = { 0, 0, 0 };
			result = new Playervo(p.getPid(),p.getName(), p.getNumber(), p.getPosition(),
					p.getHeight(), p.getWeight(), p.getBirthday(),
					p.getSelected(), p.getSalary(), p.getHighschool(),
					p.getUniversity(), temp.getTeam(), location, division,
					conference, temp.getGameplayed(), temp.getGamestarted(),
					temp.getRebounds(), temp.getAssists(), temp.getTime(),
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
					temp.getDunk(), temp.getDunked(),doubledouble,threedouble);
		}
		return result;
	}

	public ArrayList<Playervo> getAllPlayers(String Season, boolean isPlayOff) {
		// TODO Auto-generated method stub
		ArrayList<player> list = playerReader.getPlayersBySeason(Season,
				isPlayOff);
		ArrayList<Playervo> result = new ArrayList<Playervo>();
		for (int i = 0; i < list.size(); i++) {
			result.add(changePlayertoVO(list.get(i)));
		}
		return result;
	}

	/**
	 *
	 * @param season例: "12-13"，isPlayOff:是否是季后赛
	 * @return 得到此球员这赛季所呆的球队的中文全名
	 */
	public String getFullZhName(String playerId,String season,boolean isPlayOff){
	    return null;
	}
}
