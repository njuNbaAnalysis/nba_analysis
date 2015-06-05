package logic.players;

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
					temp.getDunk(), temp.getDunked());
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

}
