package logic;

import java.util.ArrayList;

import logic.matches.Match;
import logic.players.Player;
import logic.teams.Team;

public interface BLService {
	public ArrayList<Player> getAllPlayers();          
	public ArrayList<Team> getAllTeams();				
	public ArrayList<Match> getAllMatches();		
	public int getProgress();
}
