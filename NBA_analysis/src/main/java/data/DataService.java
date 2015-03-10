package data;

import java.util.ArrayList;

import logic.matches.Match;
import logic.players.Player;
import logic.teams.Team;

public interface DataService {
	public ArrayList<Player> getAllPlayers();          //得到所有的球员
	public ArrayList<Team> getAllTeams();				//得到所有的球队
	public ArrayList<Match> getAllMatches();			//得到所有的比赛
}
