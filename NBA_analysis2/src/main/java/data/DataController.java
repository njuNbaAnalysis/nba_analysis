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
        
    }
    
    public static DataController getInstance(){
        if(dataController != null){
            return dataController;
        }
        else{
        	dataController = new DataController();
            return dataController;
        }
    }

	public ArrayList<Player> getAllPlayers() {
	    if(playerReader == null){  //通过对是否已经初始化的判断防止重复读取文件
	        playerReader = new PlayerReader();
	        playerReader.init();
	        return playerReader.getPlayerList();
	    }
	    else{
	        return playerReader.getPlayerList();
	    }
	}

	public ArrayList<Team> getAllTeams() {
	    if(teamReader == null){
	        teamReader = new TeamReader();
	        teamReader.init();
	        return teamReader.getTeamList();
	    }
	    else{
	        return teamReader.getTeamList();
	    }
	}

	public ArrayList<Match> getAllMatches() {
	    if(matchReader == null){
	        matchReader = new MatchReader();
	        matchReader.init();
	        return matchReader.getMatchList();
	    }
	    else{
	        return matchReader.getMatchList();
	    }
	}

    @Override
    public boolean isMatchChanged() {
        if(matchReader == null){
            matchReader = new MatchReader();
            matchReader.init();
            return matchReader.isChanged();
        }
        else{
            return matchReader.isChanged();
        }
    }

    @Override
    public void readAllImages() {
        if(playerReader == null){  //通过对是否已经初始化的判断防止重复读取文件
            playerReader = new PlayerReader();
            playerReader.init();
        }
        
        if(teamReader == null){
            teamReader = new TeamReader();
            teamReader.init();
        }
        
        Thread thread_readImage = new Thread(){
            public void run(){
                playerReader.readImage();
            }
        };
        thread_readImage.start();
        
        Thread thread_readImage2 = new Thread(){
            public void run(){
                teamReader.readImage();
            }
        };
        thread_readImage2.start();
    }

}
