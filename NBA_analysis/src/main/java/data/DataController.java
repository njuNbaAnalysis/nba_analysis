package data;

import java.util.ArrayList;

import logic.matches.Match;
import logic.players.Player;
import logic.teams.Team;
import data.matches.MatchReader;
import data.players.PlayerReader;
import data.teams.TeamReader;

public class DataController implements DataService{
    private static DataController dataController = null;
    
    private MatchReader matchReader = null;
    private PlayerReader playerReader = null;
    private TeamReader teamReader = null;

    private DataController(){
        matchReader = new MatchReader();
        playerReader = new PlayerReader();
        teamReader = new TeamReader();
        matchReader.init();
        playerReader.init();
        teamReader.init();
    }
    
    public static DataController getInstance(){
        if(dataController != null){
            return dataController;
        }
        else{
            return new DataController();
        }
    }
    
    public void init(){
        
    }

	public ArrayList<Player> getAllPlayers() {
		PlayerReader reader = new PlayerReader();
		return reader.getPlayerList();
	}

	public ArrayList<Team> getAllTeams() {
	    TeamReader reader = new TeamReader();
        return reader.getTeamList();
	}

	public ArrayList<Match> getAllMatches() {
        MatchReader reader = new MatchReader();
        return reader.getMatchList();
	}

}
