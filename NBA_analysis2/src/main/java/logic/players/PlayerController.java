package logic.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import test.data.PlayerKingInfo;
import compare.PlayerComparator;
import compare.TeamComparator;
import logic.BLController;
import logic.BLParameter;
import logic.BLParameter.Filter;
import logic.BLParameter.Mode;
import logic.BLParameter.Sort;
import logic.matches.Match;
import logic.matches.MatchController;
import logic.matches.RecordOfPlayer;
import logic.teams.Team;
import data.DataController;
import data.DataService;

public class PlayerController {
	private ArrayList<Player> playerList = null;
	private static PlayerController playerController = null;
	private DataService dataService;

	private PlayerController() {
		dataService = DataController.getInstance();
		init();// 此时做init为了使构造和init不能分开调用，防止BLController中的机制出错
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
		computeNormalInfo();
	}

	// 对Player数据进一步计算
	private void computeNormalInfo() {

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
		BLController.progress++;
		double now = System.currentTimeMillis();
		System.out.println("computeData_player:" + (now - current));
	}

	private void computeHighInfo() {
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).init();
		}
	}

	private void UpdataPlayer(RecordOfPlayer record, String team) {
		for (Player temp:playerList) {
			if (temp.getName().equals(record.getPlayerName())) {
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
				temp.AddRecord(temp.getPoints(), temp.getRebounds(), temp.getAssists());
				int flag = 0;// 用于计算两双或三双
				if (record.getAssists() >= 10)
					flag++;
				if (record.getBlocks() >= 10)
					flag++;
				if (record.getPoints() >= 10)
					flag++;
				if (record.getSteals() >= 10)
					flag++;
				if (record.getRebounds() >= 10)
					flag++;
				if (flag == 2)
					temp.setThreedouble(temp.getDoubledouble() + 1);
				if (flag == 3)
					temp.setDoubledouble(temp.getThreedouble() + 1);
				if (flag == 4)
					temp.setFourdouble(temp.getFourdouble() + 1);
				if (flag == 5)
					temp.setFivedouble(temp.getFivedouble() + 1);
				break;
			}
		}
	}

	public Player getPlayer(String name) {
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).getName().equals(name))
				return playerList.get(i);
		}
		return null; // 没找到
	}

	public ArrayList<Object> getResult(BLParameter parameter) {
		ArrayList<Object> result = new ArrayList<Object>();

		//进行筛选：
		ArrayList<Filter> FilterList = parameter.getFilterList();
		ArrayList<Player> PlayerAfterFliter = new ArrayList<Player>();
		for(Player player : playerList){
			if(isSuitable(player,FilterList))
				PlayerAfterFliter.add(player);
		}
		// 进行数据加载
		if (parameter.isHigh()) {
			computeHighInfo();
		}
		// 进行mode判断,如果是hot直接处理并返回
		Mode mode = parameter.getMode();
		if (mode.getMode().equals("hot")) {
			String field = mode.getField();
			Sort sort = parameter.new Sort(field, false);
			parameter.addSort(sort);
			this.sort(PlayerAfterFliter, parameter);

			int num = 0;// 已经添加的球队数
			for (Player player : PlayerAfterFliter) {
				if (num == 5)
					break;
				result.add(player.getHotInfo(field));
				num++;
			}
			return result;
		}

		if (mode.getMode().equals("king")) {
			if (mode.isDaily()) {
				
			} else {
				String field = mode.getField();
				Sort sort = parameter.new Sort(field, false);
				parameter.addSort(sort);
				this.sort(PlayerAfterFliter, parameter);
				Player player = PlayerAfterFliter.get(0);
				result.add(player.getKingInfo(field));
			}
			return result;
		} else {
			// 排序
			this.sort(PlayerAfterFliter, parameter);
			// 进行Number值判断
			int num = 0;// 已经添加的球队数
			for (Player player : PlayerAfterFliter) {
				if (num == parameter.getNumber())
					break;
				if (parameter.isHigh()) {
					result.add(player.getHighInfo());
				} else {
					result.add(player.getNormalInfo(parameter.isAvarage()));
				}
				num++;
			}
			return result;
		}
	}

	private boolean isSuitable(Player player, ArrayList<Filter> filterList) {
		// TODO Auto-generated method stub
		for(Filter filter:filterList){
			switch (filter.getFilterName()){
			case "position":
				if((!filter.getFilterValue().equals("All"))&&(!player.getPosition().contains(filter.getFilterValue())))
					return false;
				break;
			case "league":
				if((!filter.getFilterValue().equals("All"))&&(!(player.getConference()==filter.getFilterValue().charAt(0))))
					return false;
				break;
			case "age":
				if(!(player.getAge()>filter.getRange()[0]&&player.getAge()<=filter.getRange()[0]))
					return false;
				break;
			}
		}
		return true;
	}

	private void sort(ArrayList<Player> playerList, BLParameter parameter) {
		Comparator<Player> comparator = new PlayerComparator(parameter);
		Collections.sort(playerList, comparator);
	}

}
