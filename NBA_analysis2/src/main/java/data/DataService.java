package data;

import java.util.ArrayList;

import logic.matches.Match;
import logic.players.Player;
import logic.teams.Team;

public interface DataService {
    //只允许通过此处调用Data所有方法
	public ArrayList<Player> getAllPlayers();          
	public ArrayList<Team> getAllTeams();				
	public ArrayList<Match> getAllMatches();
	
	//判断match是否更改，如果返回true则表示已自动刷新相应数据
	public boolean isMatchChanged();
	public void readAllImages();
}
