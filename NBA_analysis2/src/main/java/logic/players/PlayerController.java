package logic.players;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.lang.model.element.Name;

import compare.PlayerComparator;
import compare.PlayerUpgradeRateComp;
import compare.todayPlayerComp;
import logic.BLController;
import logic.BLParameter;
import logic.BLParameter.Filter;
import logic.BLParameter.Mode;
import logic.BLParameter.Sort;
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
		BLController.progress++;
	}

	// 对Player数据进一步计算
	private void computeNormalInfo() {
		double current = System.currentTimeMillis();
		MatchController matchController = MatchController.getInstance();
		ArrayList<Match> ListOfMatches = matchController.getAllMatches();
		for (int i = 0; i < ListOfMatches.size(); i++) {
			addMatch(ListOfMatches.get(i));
		}
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).getTeamInfo();
		}
		double now = System.currentTimeMillis();
		System.out.println("computeData_player:" + (now - current));
	}

	public void computeHighInfo() {
		for (int i = 0; i < playerList.size(); i++) {
			playerList.get(i).init();
		}
	}

	// 提供给MatchController的方法
	public void addMatch(Match temp) {
		ArrayList<RecordOfPlayer> ListOfPlayers1 = temp.getFirstRecordList();
		ArrayList<RecordOfPlayer> ListOfPlayers2 = temp.getSecondRecordList();
		int sumofTime = 4 * 12 * 60 * 5;
		if (temp.getPointsList().size() > 4) {
			sumofTime += (temp.getPointsList().size() - 4) * 5 * 60 * 5;
		}
		for (int j = 0; j < ListOfPlayers1.size(); j++) {
			UpdataPlayer(ListOfPlayers1.get(j), temp.getTeams()[0],
					temp.getDate(), temp.getTeams()[1], ListOfPlayers1,
					ListOfPlayers2, sumofTime);
		}
		for (int j = 0; j < ListOfPlayers2.size(); j++) {
			UpdataPlayer(ListOfPlayers2.get(j), temp.getTeams()[1],
					temp.getDate(), temp.getTeams()[0], ListOfPlayers2,
					ListOfPlayers1, sumofTime);
		}

	}

	private void UpdataPlayer(RecordOfPlayer record, String team, String Date,
			String enemy, ArrayList<RecordOfPlayer> List1,
			ArrayList<RecordOfPlayer> List2, int sumOfTime) {
		boolean isexit = false;
		for (Player temp : playerList) {
			if (temp.getName().equals(record.getPlayerName())) {
				isexit = true;

				int sumOffieldGoalAttempts = 0;
				int sumOfrebounds = 0;
				int sumOfoffenseRebounds = 0;
				int sumOfdefenseRebounds = 0;
				int sumOfturnOver = 0;
				int sumOffreeThrowAttempts = 0;
				int sumOffieldGoalHits = 0;
				int sumOfopponentrebounds = 0;
				int sumOfopponentoffenserebounds = 0;
				int sumOfopponentdefenseRebounds = 0;
				int sumOfoppnentattempts = 0;// 两分出手次数
				double sumOfoppnentDefensiveRounds = 0;// 对手进攻次数
				int sumOfoppnentGoalsAttempts = 0;
				int sumOfoppnentFreeAttempts = 0;
				int woyaorigou = 0;
				int turnover = 0;
				for (int i = 0; i < List1.size(); i++) {
					sumOfdefenseRebounds += List1.get(i).getDefensiveRebounds();
					sumOffieldGoalAttempts += List1.get(i)
							.getFieldGoalAttempts();
					sumOffieldGoalHits += List1.get(i).getFieldGoalHits();
					sumOffreeThrowAttempts += List1.get(i)
							.getFreeThrowAttemps();
					sumOfoffenseRebounds += List1.get(i).getOffensiveRebounds();
					sumOfrebounds += List1.get(i).getRebounds();
					sumOfturnOver += List1.get(i).getTurnOver();
				}
				for (int i = 0; i < List2.size(); i++) {
					sumOfopponentdefenseRebounds += List2.get(i)
							.getDefensiveRebounds();
					sumOfopponentoffenserebounds += List2.get(i)
							.getOffensiveRebounds();
					sumOfopponentrebounds += List2.get(i).getRebounds();
					sumOfoppnentattempts += (List2.get(i)
							.getFieldGoalAttempts() - List2.get(i)
							.getThreePointAttemps());
					sumOfoppnentGoalsAttempts += List2.get(i)
							.getFieldGoalAttempts();
					sumOfoppnentFreeAttempts += List2.get(i)
							.getFreeThrowAttemps();
					woyaorigou += (List2.get(i).getFieldGoalAttempts() - List2
							.get(i).getFieldGoalHits());
					turnover += List2.get(i).getTurnOver();
					// ：本队回合=投篮数+0.4*球队罚球数-1.07*（本队进攻篮板/（本队进攻篮
					// 板+对手防守篮板）*投失球数）+1.07*失误数
				}
				sumOfoppnentDefensiveRounds = sumOfoppnentGoalsAttempts
						+ 0.4
						* sumOfoppnentFreeAttempts
						- 1.07
						* ((sumOfopponentoffenserebounds * 1.0)
								/ (sumOfopponentoffenserebounds + sumOfdefenseRebounds) * woyaorigou)
						+ 1.07 * turnover;

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
				temp.setSumOfdefenseRebounds(temp.getSumOfdefenseRebounds()
						+ sumOfdefenseRebounds);
				temp.setSumOffieldGoalAttempts(temp.getSumOffieldGoalAttempts()
						+ sumOffieldGoalAttempts);
				temp.setSumOffieldGoalHits(temp.getSumOffieldGoalHits()
						+ sumOffieldGoalHits);
				temp.setSumOffreeThrowAttempts(temp.getSumOffreeThrowAttempts()
						+ sumOffreeThrowAttempts);
				temp.setSumOfoffenseRebounds(temp.getSumOfoffenseRebounds()
						+ sumOfoffenseRebounds);
				temp.setSumOfopponentdefenseRebounds(temp
						.getSumOfopponentdefenseRebounds()
						+ sumOfopponentdefenseRebounds);
				temp.setSumOfopponentoffenserebounds(temp
						.getSumOfopponentoffenserebounds()
						+ sumOfopponentoffenserebounds);
				temp.setSumOfopponentrebounds(temp.getSumOfopponentrebounds()
						+ sumOfopponentrebounds);
				temp.setSumOfoppnentattempts(temp.getSumOfoppnentattempts()
						+ sumOfoppnentattempts);
				temp.setSumOfrebounds(temp.getSumOfrebounds() + sumOfrebounds);
				temp.setSumOfTime(temp.getSumOfTime() + sumOfTime);
				temp.setSumOfoppnentDefensiveRounds(temp
						.getSumOfoppnentDefensiveRounds()
						+ sumOfoppnentDefensiveRounds);
				temp.setSumOfturnOver(temp.getSumOfturnOver() + sumOfturnOver);
				temp.AddRecord(record.getPoints(), record.getRebounds(),
						record.getAssists(), record.getSteals(),
						record.getBlocks(), Date, enemy);
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
		if (!isexit) {
			addPlayer(record.getPlayerName(), record.getPosition());
			UpdataPlayer(record, team, Date, enemy, List1, List2, sumOfTime);
		}
	}

	private void addPlayer(String playerName, String position) {
		// TODO Auto-generated method stub
		int a[] = { 0, 0 };
		playerList
				.add(new Player(playerName, 0, position, a, 0, null, 0, 0, ""));
	}

	public Player getPlayer(String name) {
		for (int i = 0; i < playerList.size(); i++) {
			if (playerList.get(i).getName().equals(name))
				return playerList.get(i);
		}
		return new Player(name, 0, null, null, 0, null, 0, 0, null); // 没找到
	}

	public ArrayList<Object> getResult(BLParameter parameter) {
		ArrayList<Object> result = new ArrayList<Object>();

		// 进行筛选：
		ArrayList<Filter> FilterList = parameter.getFilterList();
		ArrayList<Player> PlayerAfterFliter = new ArrayList<Player>();
		for (Player player : playerList) {
			if (isSuitable(player, FilterList))
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
			int num = 0;// 已经添加的球员数
			for (Player player : PlayerAfterFliter) {
				if (num == 5)
					break;
				result.add(player.getHotInfo(field));
				num++;
			}
			return result;
		}

		if (mode.getMode().equals("king")) {
			String field = mode.getField();
			if (mode.isDaily()) { // 暂时这样
				MatchController matchController = MatchController.getInstance();
				String strDate = matchController.getTime();
				ArrayList<todayPlayer> todayPlayer = this.getTotalPlayer(
						strDate, field);
				if (todayPlayer.size() > 0)
					for (int i = 0; i < 5; i++) { // 默认5个
						result.add(todayPlayer.get(i).getKingInfo(field));
					}
			} else {
				Sort sort = parameter.new Sort(field, false);
				parameter.addSort(sort);
				this.sort(PlayerAfterFliter, parameter);
				for (int i = 0; i < 5; i++) { // 默认5个
					result.add(PlayerAfterFliter.get(i).getKingInfo(field));
				}
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
		for (Filter filter : filterList) {
			switch (filter.getFilterName()) {
			case "position":
				if ((!filter.getFilterValue().equals("All"))
						&& (!player.getPosition().contains(
								filter.getFilterValue()))) 
					return false;
				break;
			case "league":
				if ((!filter.getFilterValue().equals("All"))
						&& (!(player.getConference() == filter.getFilterValue()
								.charAt(0))))
					return false;
				break;
			case "age":
				if (!(player.getAge() > filter.getRange()[0] && player.getAge() <= filter
						.getRange()[1])){
					return false;
				}
				break;
			}
		}
		return true;
	}

	private void sort(ArrayList<Player> playerList, BLParameter parameter) {
		Comparator<Player> comparator = new PlayerComparator(parameter);
		Collections.sort(playerList, comparator);
	}

	public ArrayList<todayPlayer> getTotalPlayer(String strDate, String field) { // 得到当日最热球员
		BLController bl = BLController.getInstance();
		ArrayList<Match> Matchlist = bl.getAllMatches();
		ArrayList<Match> list = new ArrayList<Match>();
		for (int i = 0; i < Matchlist.size(); i++) {
			if ((Matchlist.get(i).getDate().equals(strDate)))
				list.add(Matchlist.get(i));
		}
		ArrayList<todayPlayer> listTodayHotPlayer = new ArrayList<todayPlayer>();
		for (Match m : list) {
			ArrayList<RecordOfPlayer> firstlist = m.getFirstRecordList();
			ArrayList<RecordOfPlayer> secondlist = m.getSecondRecordList();
			for (int i = 0; i < firstlist.size(); i++) {
				listTodayHotPlayer.add(new todayPlayer(firstlist.get(i)
						.getPlayerName(), m.getTeams()[0], firstlist.get(i)
						.getPosition(), firstlist.get(i).getPoints(), firstlist
						.get(i).getRebounds(), firstlist.get(i).getAssists(),
						firstlist.get(i).getSteals(), firstlist.get(i)
								.getBlocks()));
			}
			for (int i = 0; i < secondlist.size(); i++) {
				listTodayHotPlayer.add(new todayPlayer(secondlist.get(i)
						.getPlayerName(), m.getTeams()[1], secondlist.get(i)
						.getPosition(), secondlist.get(i).getPoints(),
						secondlist.get(i).getRebounds(), secondlist.get(i)
								.getAssists(), secondlist.get(i).getSteals(),
						secondlist.get(i).getBlocks()));
			}

		}
		Comparator<todayPlayer> comparator = new todayPlayerComp(field);
		Collections.sort(listTodayHotPlayer, comparator);
		return listTodayHotPlayer;
	}

	public ArrayList<Player> getSeasonKingPlayer(String field, int num) {
		BLParameter parameter = new BLParameter();
		Sort sort = parameter.new Sort(field, false);
		sort.setAsc(false);
		parameter.addSort(sort);
		parameter.setAvarage(true);
		this.sort(playerList, parameter);
		return playerList;
	}

	public ArrayList<Player> getMostImprovedPlayer(String field) {
		int num = 0;
		switch (field) {
		case "points":
			num = 0;
			break;
		case "rebound":
			num = 1;
			break;
		case "assist":
			num = 2;
			break;
		default:
			break;
		}
		Comparator<Player> comparator = new PlayerUpgradeRateComp(num);
		playerList = getAllPlayers();
		Collections.sort(playerList, comparator);
		return playerList;
	}

	public double[] getAllianceAverageDataOFPlayer() {
		double[] result = new double[5];

		double totalPoints = 0; // 30支球队平均得分相加
		double totalRebounds = 0; // 30支球队平均篮板相加
		double totalAssists = 0; // 30支球队平均助攻相加
		double totalFreeThrowPercent = 0;
		double totalThreeThrowPercent = 0;
		for (Player play : playerList) {
			totalPoints += play.getAveragePoints();
			totalRebounds += play.getAverageRebounds();
			totalAssists += play.getAverageAssists();
			totalFreeThrowPercent += play.getFreeThrowsPercentage();
			totalThreeThrowPercent += play.getThreePointersPercentage();
		}
		result[0] = totalPoints / playerList.size();
		result[1] = totalRebounds / playerList.size();
		result[2] = totalAssists / playerList.size();
		result[3] = 1.0 * totalFreeThrowPercent / playerList.size();
		result[4] = 1.0 * totalThreeThrowPercent / playerList.size();

		return result;
	}
}
