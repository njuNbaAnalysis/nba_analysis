package logic.matches;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import compare.MatchComp;
import compare.PlayerComparator;
import logic.BLController;
import logic.players.Player;
import logic.players.PlayerController;
import logic.teams.Team;
import logic.teams.TeamController;
import data.DataController;
import data.DataService;
import data.matches.MatchMistake;
import data.matches.MatchMistake.Kind;
import data.matches.MatchReader;

public class MatchController {
	private ArrayList<Match> matchList = null;
	private static MatchController matchController = null;
	private DataService dataService;

	private MatchController() {
		dataService = DataController.getInstance();
		init(); // 此时做init为了使构造和init不能分开调用，防止BLController中的机制出错
	}

	public static MatchController getInstance() {
		if (matchController != null) {
			return matchController;
		} else {
			matchController = new MatchController();
			return matchController;
		}
	}

	public void init() {
		if (dataService == null)
			dataService = DataController.getInstance();
		matchList = dataService.getAllMatches();
		Comparator<Match> comparator = new MatchComp();
		Collections.sort(matchList, comparator);
		computeData(matchList);
		BLController.progress++;
	}

	public ArrayList<Match> getAllMatches() {
		return matchList;
	}

	private void computeData(ArrayList<Match> matchList) { // 脏数据处理
		for (int i = 0; i < matchList.size(); i++) {
			ArrayList<MatchMistake> ListOfMistake = matchList.get(i)
					.getMatchMistakeList();
			if (ListOfMistake != null) {
				for (int j = 0; j < ListOfMistake.size(); j++) {
					MatchMistake temp = ListOfMistake.get(j);
					if (temp.getKind() == Kind.POINT) {
						ArrayList<RecordOfPlayer> list1 = matchList.get(i)
								.getFirstRecordList();
						ArrayList<RecordOfPlayer> list2 = matchList.get(i)
								.getSecondRecordList();
						int sum1 = 0;
						int sum2 = 0;
						for (int n = 0; n < list1.size(); n++) {
							sum1 += list1.get(n).getPoints();
						}
						for (int n = 0; n < list2.size(); n++) {
							sum2 += list2.get(n).getPoints();
						}
						int[] points = matchList.get(i).getPoints();
						if (sum1 == points[0] && sum2 == points[1]) {
							ListOfMistake.remove(j);
						} else if (sum1 != points[0]) {
							for (int m = 0; m < list1.size(); m++) {
								if (list1.get(m).getPlayerName()
										.equals(temp.getName())) {
									int numOFPoints = list1.get(m).getPoints();
									list1.get(m).setPoints(
											numOFPoints - (sum1 - points[0]));
									break;
								}
							}
						} else if (sum2 != points[1]) {
							for (int m = 0; m < list2.size(); m++) {
								if (list2.get(m).getPlayerName()
										.equals(temp.getName())) {
									int numOFPoints = list2.get(m).getPoints();
									list2.get(m).setPoints(
											numOFPoints - (sum2 - points[1]));
									break;
								}
							}
						}
					}
				}
			} else {
				// 没有任何其他错误，但每个人得分加起来却不一样。。。。。。如何处理？？！！

			}
		}
	}

	public void addMatches() {
		if (dataService == null)
			dataService = DataController.getInstance();
		matchList = dataService.getAllMatches();
		ArrayList<Match> newMatchList = new ArrayList<Match>();
		for (int i = MatchReader.numberOFbefore; i < MatchReader.numberOFcurrent; i++) {
			newMatchList.add(matchList.get(i));
		}
		MatchReader.numberOFbefore = MatchReader.numberOFcurrent;
		Comparator<Match> comparator = new MatchComp();
		Collections.sort(newMatchList, comparator);
		computeData(newMatchList);
		PlayerController playcontrol = PlayerController.getInstance();
		TeamController teamcontrol = TeamController.getInstance();
		for (int i = 0; i < newMatchList.size(); i++) {
			playcontrol.addMatch(newMatchList.get(i));
			teamcontrol.addMatch(newMatchList.get(i));
		}
	}
}
