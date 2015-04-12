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
	public ArrayList<Player> getMostImprovedPlayer(String field,int num);//把field可能值列在这里,可能值:"场均得分"、"场均篮板"、"场均助攻"
	public void init();    //界面层专用的初始化，干所有事情之前调用，progress从0~9
	public Team[] getTeamsByMatch(Match match);
	public ArrayList<Match> getTodayMatches(String date); //13-14_2014-01-01
	public ArrayList<Player> getTodayKingPlayer(String date,String field,int num);//得到今天的联盟数据王,date日期格式:13-14_2014-01-01.field排序字段，num人数,field可能值：得分、篮板、助攻、抢断、盖帽
	public ArrayList<Player> getSeasonKingPlayer(String field,int num);//得到赛季的联盟数据王，格式同上,field可能值： "得分", "篮板", "助攻", "抢断", "盖帽" 
	public ArrayList<Team> getSeasonKingTeam(String field,int num);//得到赛季的热门球队，格式同上，field可能值："得分", "篮板", "助攻", "抢断", "盖帽", "三分%", "%","罚球%" 
	public Team getTeamByPlayer(Player player);
	public ArrayList<Match> getLatestMatchByPlayer(Player player);
	public double[] getAllianceAverageData();//得到联盟的平均数据（场均得分，场均篮板，场均助攻，罚球%，三分%）
}
