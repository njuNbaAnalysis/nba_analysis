package logic;

import java.util.ArrayList;
import java.util.Date;

import logic.matches.Match;
import logic.players.Player;
import logic.players.todayPlayer;
import logic.teams.Team;

public interface BLService {
	public ArrayList<Player> getAllPlayers();          
	public ArrayList<Team> getAllTeams();				
	public ArrayList<Match> getAllMatches();		
	public int getProgress();
	public void init();    //界面层专用的初始化，干所有事情之前调用，progress从0~9
	public boolean isMatchChanged();
	
	//返回值由testData定义，请进行根据输入参数进行适当转换
	public ArrayList<Object> getResult(BLParameter parameter); //
	
	public double[] getAllianceAverageData();//得到联盟(30个球队加起来)的平均数据（场均得分，场均篮板，场均助攻，罚球%，三分%）
	
	//player
	public ArrayList<Player> getMostImprovedPlayer(String field,int num);//把field可能值列在这里,可能值:"场均得分"、"场均篮板"、"场均助攻"
	public ArrayList<todayPlayer> getTodayKingPlayer(String date,String field,int num);//得到今天的联盟数据王,date日期格式:13-14_2014-01-01.field排序字段，num人数,field可能值：得分、篮板、助攻、抢断、盖帽
    public ArrayList<Player> getSeasonKingPlayer(String field,int num);//得到赛季的联盟数据王，格式同上,field可能值： "得分", "篮板", "助攻", "抢断", "盖帽" 
    public Player getPlayerByName(String name);//根据球员名字得到球员
    public double[] getAlliancePlayerAverageData();//得到所有球员的平均场均数据（场均得分，场均篮板，场均助攻，罚球%，三分%）
	//match
    public ArrayList<Match> getTodayMatches(String date); //13-14_2014-01-01
    public String getTime();//得到当前日期：默认为最近一场比赛日期
    public Match getMatch(Date date,String[] teamNameAbb);//另一个为主客场的球队名缩写 
    public ArrayList<Match> getLatestMatchesByTeam(String teamName);//根据球队名称返回最近十场比赛，比赛信息指：进攻（得分）、防守（失分）、节奏（48分钟的进攻回合数）、进攻防守比，不包括历史球队
    
    //team
    public ArrayList<Team> getSeasonKingTeam(String field,int num);//得到赛季的热门球队，格式同上，field可能值："得分", "篮板", "助攻", "抢断", "盖帽", "三分%", "%","罚球%" 
    public Team[] getTeamsByMatch(Match match);
    public Team getTeamByName(String teamName);
    public double getLineUpAbility(String teamName);//五个位置主力球员能力值:C,PG,SG,PF,SF,能力值取值(0~10)
    public double getTeamAbility(String teamName);//内线(中锋加上大前锋能力综合)、外线(其他位置能力综合)、配合（每百回合的传球次数）、进攻（得分）、防守（失分）
}
