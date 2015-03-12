package data;

import java.util.ArrayList;

import logic.matches.Match;
import logic.players.Player;
import logic.teams.Team;
import data.players.PlayerReader;
import data.teams.TeamReader;

public class DataController implements DataService{

	public ArrayList<Player> getAllPlayers() {
		PlayerReader reader = new PlayerReader();
		return reader.readPlayers();
	}

	public ArrayList<Team> getAllTeams() {
	    TeamReader reader = new TeamReader();
        return reader.readTeams();
	}

	public ArrayList<Match> getAllMatches() {
		// TODO Auto-generated method stub
		return null;
	}

}
