package data;

import java.util.ArrayList;

import logic.matches.Match;
import logic.players.Player;
import logic.teams.Team;
import data.players.PlayerReader;

public class DataController implements DataService{

	public ArrayList<Player> getAllPlayers() {
		PlayerReader reader = new PlayerReader();
		return reader.readPlayers();
	}

	public ArrayList<Team> getAllTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Match> getAllMatches() {
		// TODO Auto-generated method stub
		return null;
	}

}
