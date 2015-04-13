package logic;

import java.util.ArrayList;
import java.util.Date;

import logic.matches.Match;
import logic.matches.MatchController;
import logic.players.Player;
import logic.players.PlayerController;
import logic.players.todayPlayer;
import logic.teams.Team;
import logic.teams.TeamController;
import data.DataController;
import data.DataService;

public class BLController implements BLService {
	private static BLController blController = null;
	public static int progress = 0;
	public static int maxProgress = 9;

	private MatchController matchController = null;
	private PlayerController playerController = null;
	private TeamController teamController = null;
	private DataService dataService = null;

	private BLController() {
	}

	public static BLController getInstance() {
		if (blController != null) {
			return blController;
		} else {
			blController = new BLController();
			return blController;
		}
	}

	public ArrayList<Player> getAllPlayers() {
		if (playerController == null) {
			playerController = PlayerController.getInstance();
			return playerController.getAllPlayers();
		} else {
			return playerController.getAllPlayers();
		}
	}

	public ArrayList<Team> getAllTeams() {
		if (teamController == null) {
			teamController = TeamController.getInstance();
			return teamController.getAllTeams();
		} else {
			return teamController.getAllTeams();
		}
	}

	public ArrayList<Match> getAllMatches() {
		if (matchController == null) {
			matchController = MatchController.getInstance();
			return matchController.getAllMatches();
		} else {
			return matchController.getAllMatches();
		}
	}

	@Override
	public int getProgress() {
		// TODO Auto-generated method stub
		return progress;
	}

	public static void main(String[] args) {
		long current = System.currentTimeMillis();

		BLController v = BLController.getInstance();
		v.getAllTeams();

		/*
		 * Thread thread1 = new Thread(){ public void run(){
		 * while(BLController.progress != 9){ //
		 * System.out.println(BLController.progress); //
		 * System.out.println("now::::::::      " + BLController.progress / 9.0
		 * * 100 + "%"); try { this.sleep(100); } catch (InterruptedException e)
		 * { // TODO Auto-generated catch block e.printStackTrace(); } } } };
		 * thread1.start();
		 * 
		 * try { thread1.join(); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); }
		 */

		long now = System.currentTimeMillis();
		System.out.println("all:" + (now - current));
	}

	@Override
	public ArrayList<Object> getResult(BLParameter parameter) {
		if (parameter.isPlayer()) {
			return PlayerController.getInstance().getResult(parameter);
		} else {
			return TeamController.getInstance().getResult(parameter);
		}
	}

	@Override
	public boolean isMatchChanged() {
		if (dataService == null) {
			dataService = DataController.getInstance();
		}
		boolean ischanged = dataService.isMatchChanged();
		if (ischanged) {
			if (matchController == null) {
				matchController = MatchController.getInstance();
			}
			matchController.addMatches();
		}
		return ischanged;
	}

	@Override
	public ArrayList<Player> getMostImprovedPlayer(String field, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	// 界面层用来初始化的
	// 所有的getAll
	// 所有的图片
	@Override
	public void init() {
		if (matchController == null) {
			matchController = MatchController.getInstance();
		}
		if (teamController == null) {
			teamController = TeamController.getInstance();
			teamController.init();
		}
		if (playerController == null) {
			playerController = PlayerController.getInstance();
			playerController.computeHighInfo();
		}
		if (dataService == null) {
			dataService = DataController.getInstance();
			dataService.readAllImages();
		}
	}

	@Override
	// 界面层调用
	public Team[] getTeamsByMatch(Match match) {
		if (teamController == null) {
			teamController = TeamController.getInstance();
		}
		Team[] teams = new Team[2];
		String teamNames[] = match.getTeams();
		int num = 0;
		for (Team team : teamController.getAllTeams()) {
			if (team.getAbbreviation().equals(teamNames[0])) {
				teams[0] = team;
				num++;
			}
			if (team.getAbbreviation().equals(teamNames[1])) {
				teams[1] = team;
				num++;
			}
			if (num == 2) {
				break;
			}
		}

		return teams;
	}

    @Override
    public ArrayList<Match> getTodayMatches(String date) {
        if(matchController == null){
            matchController = MatchController.getInstance();
        }
        return matchController.getTodayMatches(date);
    }

	@Override
	public ArrayList<Player> getSeasonKingPlayer(String field, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Team> getSeasonKingTeam(String field, int num) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Match> getLatestMatchByPlayer(Player player) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public double[] getAllianceAverageData() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Team getTeamByName(String teamName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<todayPlayer> getTodayKingPlayer(String date, String field,
			int num) {
		// TODO Auto-generated method stub
		return null;
	}

}
