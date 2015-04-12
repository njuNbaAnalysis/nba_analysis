package logic;

import java.util.ArrayList;
import java.util.Date;

import logic.matches.Match;
import logic.players.Player;
import logic.teams.Team;

public interface BLService {
	public ArrayList<Player> getAllPlayers();          
	public ArrayList<Team> getAllTeams();				
	public ArrayList<Match> getAllMatches();		
	public int getProgress();
	
	//返回值由testData定义，请进行根据输入参数进行适当转换
	public ArrayList<Object> getResult(BLParameter parameter); //
	public boolean isMatchChanged();
	public ArrayList<Player> getMostImprovedPlayer(String field,int num);//把field可能值列在这里
	public void init();    //界面层专用的初始化，干所有事情之前调用，progress从0~9
	public Team[] getTeamsByMatch(Match match);
	public ArrayList<Match> getTodayMatches(String date); //13-14_2014-01-01
}
