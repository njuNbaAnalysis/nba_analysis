package logic;

import java.util.ArrayList;

import logic.matches.Match;
import logic.matches.MatchController;
import logic.players.Player;
import logic.players.PlayerController;
import logic.teams.Team;
import logic.teams.TeamController;

public class BLController implements BLService{
    private static BLController blController = null;
    
    private MatchController matchController = null;
    private PlayerController playerController = null;
    private TeamController teamController = null;
    
    private BLController(){
        matchController = MatchController.getInstance();
        matchController.init();
        playerController = PlayerController.getInstance();
        playerController.init();
        teamController = TeamController.getInstance();
        teamController.init();
    }
    
    public static BLController getInstance(){
        if(blController != null){
            return blController;
        }
        else{
            return new BLController();
        }
    }
    
    
	public ArrayList<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		return null;
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
