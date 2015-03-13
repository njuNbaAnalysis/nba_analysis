package logic.players;

import java.util.ArrayList;

import logic.BLController;
import logic.matches.Match;
import logic.matches.MatchController;
import logic.matches.RecordOfPlayer;
import data.DataController;
import data.DataService;

public class PlayerController {
	private ArrayList<Player> playerList = null;
	private static PlayerController playerController = null;
	private DataService dataService;

	private PlayerController() {
		dataService = DataController.getInstance();
		init();
	}

	public static PlayerController getInstance() {
		if (playerController != null) {
			return playerController;
		} else {
			playerController = new PlayerController();
			return playerController;
		}
	}

	public ArrayList<Player> getAllPlayers() {

		return playerList;
	}

	public void init() {
		playerList = dataService.getAllPlayers();
		computeData();
	}

	// 对Player数据进一步计算
	private void computeData() {

		MatchController matchController = MatchController.getInstance();
		ArrayList<Match> ListOfMatches = matchController.getAllMatches();
		for (int i = 0; i < ListOfMatches.size(); i++) {
			Match temp = ListOfMatches.get(i);
			ArrayList<RecordOfPlayer> ListOfPlayers1 = temp
					.getFirstRecordList();
			ArrayList<RecordOfPlayer> ListOfPlayers2 = temp
					.getSecondRecordList();
			for (int j = 0; j < ListOfPlayers1.size(); j++) {
				UpdataPlayer(ListOfPlayers1.get(j), ListOfMatches.get(i)
						.getTeams()[0]);
			}
			for (int j = 0; j < ListOfPlayers2.size(); j++) {
				UpdataPlayer(ListOfPlayers2.get(j), ListOfMatches.get(i)
						.getTeams()[1]);
			}
		}

		BLController.progress++;
	}

	private void UpdataPlayer(RecordOfPlayer record, String team) {
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).getName().equals(record.getPlayerName())) {
				Player temp = playerList.get(i);
				temp.setAssists(temp.getAssists() + record.getAssists());
				temp.setBlockShots(temp.getBlockShots() + record.getBlocks());
				temp.setDefenseRebounds(temp.getDefenseRebounds()
						+ record.getDefensiveRebounds());
				temp.setFieldGoalAttempts(temp.getFieldGoalAttempts()
						+ record.getFieldAttempts());
				temp.setFieldGoalHits(temp.getFieldGoalHits()
						+ record.getFieldGoals());
				temp.setFouls(temp.getFouls() + record.getFauls());
				temp.setFreeThrowAttempts(temp.getFieldGoalAttempts()
						+ record.getThreePointAttemps());
				temp.setFreeThrowHits(temp.getFreeThrowHits()
						+ record.getFreeThrows());
				temp.setMinutes(temp.getMinutes() + record.getMinutes());
				temp.setOffenseRebounds(temp.getOffenseRebounds()
						+ record.getOffensiveRebounds());
				temp.setPoints(temp.getPoints() + record.getPoints());
				temp.setRebounds(temp.getRebounds() + record.getRebounds());
				temp.setSteals(temp.getSteals() + record.getSteals());
				temp.setTeam(team);
				temp.setThreePointerAttempts(temp.getThreePointerAttempts()
						+ record.getThreePointAttemps());
				temp.setThreePointerHits(temp.getThreePointerHits()
						+ record.getThreePoints());
				temp.setTurnOver(temp.getTurnOver() + record.getTurnOver());
				if (record.isStarted())
					temp.setGameStarted(temp.getGameStarted() + 1);
				temp.setGamePlayed(temp.getGamePlayed() + 1);
				break;
			}
		}
	}
}
