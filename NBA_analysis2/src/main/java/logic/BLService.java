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
	
	//返回值由testData定义，请进行根据输入参数进行适当转换
	public ArrayList<Object> getAllPlayers(String[] parameters);          
    public ArrayList<Object> getAllTeams(String[] parameters);            
}
