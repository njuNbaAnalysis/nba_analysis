package BLservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import vo.EventVo;
import vo.HotZonevo;
import vo.Matchvo;
import vo.Playervo;
import vo.Teamvo;

public interface BLservice extends Remote{
	/**
	 * 直播前调用的初始化
	 * */
	public boolean initNBALive() throws RemoteException;
	/**
	 * 得到当前正在直播的比赛信息
	 * @throws RemoteException 
	 */
	public Matchvo getLiveMatchInfo() throws RemoteException;

	/**
	 * 得到当前正在直播的直播事件
	 */
	public ArrayList<EventVo> getLiveEvent()throws RemoteException;

	/**
	 * 得到赛季的所有球员
	 * 
	 * @param Season 赛季名，例如"12-13"
	 * @param isPlayOff  true为季后赛，false为常规赛
	 */
	public ArrayList<Playervo> getAllPlayers(String Season, boolean isPlayOff)throws RemoteException;

	/**
	 * 得到赛季的所有球队
	 * 不包含近十场的数据，需要单独请求，此方法在1s左右可以返回
	 * @param Season 赛季名，例如"12-13"
	 * @param isPlayOff true为季后赛，false为常规赛
	 */
	public ArrayList<Teamvo> getAllTeams(String Season, boolean isPlayOff)throws RemoteException;

	/**
	 * 得到一个球队的vo，此vo包含近十场比赛的相关数据，在match未读取完成之前，此方法的返回时间在15s左右
	 * @param teamNameEn
	 * @param season
	 * @param isPlayOff
	 * @return
	 * @throws RemoteException
	 */
	public Teamvo getTeamWithLatest10Data(String teamNameEn,String season,boolean isPlayOff)throws RemoteException;
	
	
	/**
	 * @param teamNameEn 球队的3大写字母名称
	 * @param isSeason true表示常规赛，false表示季后赛
	 * @param isTotal 此项为true时，返回所有球队的热区总计数据，此时teamNameEn项被忽略
	 * @return
	 */
	public HotZonevo getHotZone(String teamNameEn,boolean isSeason,boolean isTotal)throws RemoteException;
	
	/**
	 * in abeyance,waiting for the formulas
	 * @param teamNameEn
	 * @return 内线(中锋加上大前锋能力综合)、外线(其他位置能力综合)、配合（每百回合的传球次数）、进攻（得分）、防守（失分）
	 */
	public double[] getTeamAbility(String teamNameEn)throws RemoteException;

	/**
	 * @param string 例如13-14_2014-01-01 :前面表示13-14赛季，后面表示日期 
	 * 
	 * @return Matchvo
	 */
	public Collection<? extends Matchvo> getTodayMatches(String string)throws RemoteException;

	/**
	 * 
	 * @param Id 球员的唯一标识符Id
	 * @param season 赛季
	 * @param isplayoff 是否为季后赛
	 * @return 返回的Playervo中为当前赛季该球员的所有属性,此时Playervo中的getPlayerItem为null
	 */
	public Playervo getPlayerById(String Id,String season,boolean isplayoff)throws RemoteException;
	
	/**
	 * 
	 * @param Id 球员的唯一标识符Id
	 * @return 返回的Playervo中getPlayerItem()可得到该球员所有历史数据
	 */
	public Playervo getPlayerById(String Id)throws RemoteException;

	/**
	 * 
	 * @param playerName
	 * @param season
	 * @param isPlayOff
	 * @return
	 */
	public Teamvo getTeamByPlayerId(String playerId, String season, boolean isPlayOff)throws RemoteException;

	/**
	 * 
	 * @param teamName 三个大写英文字母
	 * @return teamvo
	 */
	public Teamvo getTeamByTeamName(String teamNameEn, String season, boolean isPlayOff)throws RemoteException ;
	
	
	/**
	 * @param field:points,rebounds,assists,blockShots,steals,threePointersPercentage,fieldGoalsPercentage,freeThrowsPercentage	
	 * @return teamvo
	 */
	public Teamvo[] getHotTeams(String field,String season,boolean isPlayOff)throws RemoteException;
	
	/**
	 * @return 场均得分，场均篮板，场均助攻，罚球%，三分%
	 */
	public double[] getAlliancePlayerAverageData(String season,boolean isPlayOff)throws RemoteException;
	
	
	/**
	 * 根据球队和球员姓名返回playervo
	 * @param playerName 球员英文姓名    
	 * @param teamName 三个大写英文字母
	 */
	public Playervo getPlayerByNameAndTeam(String playerName,String teamName)throws RemoteException;
}
