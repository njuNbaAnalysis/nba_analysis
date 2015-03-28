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
		init();//此时做init为了使构造和init不能分开调用，防止BLController中的机制出错
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
	    
	    double current = System.currentTimeMillis();

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
		initPlayers();
		BLController.progress++;
		
		double now = System.currentTimeMillis();
        System.out.println("computeData_player:" + (now - current));
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
						+ record.getFieldGoalAttempts());
				temp.setFieldGoalHits(temp.getFieldGoalHits()
						+ record.getFieldGoalHits());
				temp.setFouls(temp.getFouls() + record.getFauls());
				temp.setFreeThrowAttempts(temp.getFreeThrowAttempts()
						+ record.getFreeThrowAttemps());
				temp.setFreeThrowHits(temp.getFreeThrowHits()
						+ record.getFreeThrowHits());
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
						+ record.getThreePointHits());
				temp.setTurnOver(temp.getTurnOver() + record.getTurnOver());
				if (record.isStarted())
					temp.setGameStarted(temp.getGameStarted() + 1);
				temp.setGamePlayed(temp.getGamePlayed() + 1);
				int flag = 0;//用于计算两双或三双
				if(record.getAssists()>=10) flag++;
				if(record.getBlocks()>=10) flag++;
				if(record.getPoints()>=10) flag++;
				if(record.getSteals()>=10) flag++;
				if(record.getRebounds()>=10) flag++;
				if(flag==2) temp.setThreedouble(temp.getDoubledouble() + 1);
				if(flag==3) temp.setDoubledouble(temp.getThreedouble() + 1);
				if(flag==4) temp.setFourdouble(temp.getFourdouble() + 1);
				if(flag==5) temp.setFivedouble(temp.getFivedouble() + 1);
				break;
			}
		}
	}
	private void initPlayers(){
		for(int i=0;i<playerList.size();i++){
			playerList.get(i).init();
		}
	}
	
	public Player getPlayer(String name){
		for(int i=0;i<playerList.size();i++){
			if(playerList.get(i).getName().equals(name))
				return playerList.get(i);
		}
		return null;  //没找到
	}

	
	
	
	//只计算Hot的三项，为了速度，这三个通常只会有一个被调用
	private void computeHotInfo(){
	    
	}
	
	//只计算普通数据
	private void computeNormalInfo(){
	    
	}
	
	//只计算高阶数据
	private void computeHighInfo(){
	    
	}
	
	
}
