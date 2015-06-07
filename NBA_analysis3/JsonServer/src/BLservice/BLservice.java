package BLservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import vo.EventVo;
import vo.HotZonevo;
import vo.MatchSimpleInfovo;
import vo.Matchvo;
import vo.Playervo;
import vo.Teamvo;
import vo.TodayPlayervo;

public interface BLservice extends Remote {
	/**
	 * 直播前调用的初始化
	 * */
	public boolean initNBALive() throws RemoteException;

	/**
	 * 得到当前正在直播的比赛信息
	 * 
	 * @throws RemoteException
	 */
	public Matchvo getLiveMatchInfo() throws RemoteException;

	/**
	 * 得到当前正在直播的直播事件
	 */
	public ArrayList<EventVo> getLiveEvent() throws RemoteException;

	/**
	 * 得到赛季的所有球员
	 * 
	 * @param Season
	 *            赛季名，例如"12-13"
	 * @param isPlayOff
	 *            true为季后赛，false为常规赛
	 */
	public ArrayList<Playervo> getAllPlayers(String Season, boolean isPlayOff)
			throws RemoteException;

	/**
	 * 得到某个赛季的球员王
	 * 
	 * @param Season
	 *            赛季名，例如"12-13"
	 * @param isPlayOff
	 *            true为季后赛，false为常规赛
	 * @param transferField
	 *            transferField为类型:point代表得分王，rebound代表篮板王，assist代表助攻王,steal代表抢断王,blockShot代表盖帽王
	 * @param number
	 *            number为人数
	 */
	public ArrayList<Playervo> getSeasonKingPlayer(String transferField,
			int number, String season, boolean isplayoff)
			throws RemoteException;
	
	
	/**
	 * 得到某日的球员王
	 * 
	 * @param Date
	 *            日期，例如“13-14_2014-01-01”
	 * @param transferField
	 *            transferField为类型:point代表得分王，rebound代表篮板王，assist代表助攻王，steal代表抢断王,blockShot代表盖帽王
	 * @param number
	 *            number为人数
	 * @return 如果今日没有比赛，则返回过去最近一日的数据王
	 */
	public ArrayList<TodayPlayervo> getTodayKingPlayer(String date,String transferField,
			int number)
			throws RemoteException;

	/**
	 * 得到赛季的所有球队
	 * 
	 * @param Season
	 *            赛季名，例如"12-13"
	 * @param isPlayOff
	 *            true为季后赛，false为常规赛
	 */
	public ArrayList<Teamvo> getAllTeams(String Season, boolean isPlayOff)
			throws RemoteException;

	/**
	 * @param teamNameEn
	 *            球队的3大写字母名称
	 * @param isSeason
	 *            true表示常规赛，false表示季后赛
	 * @param isTotal
	 *            此项为true时，返回所有球队的热区总计数据，此时teamNameEn项被忽略
	 * @return
	 */
	public HotZonevo getHotZone(String teamNameEn, boolean isSeason,
			boolean isTotal) throws RemoteException;

	/**
	 * in abeyance,waiting for the formulas
	 * 
	 * @param teamNameEn
	 * @return 内线(中锋加上大前锋能力综合)、外线(其他位置能力综合)、配合（每百回合的传球次数）、进攻（得分）、防守（失分）
	 */
	public double[] getTeamAbility(String teamNameEn) throws RemoteException;

	/**
	 * @param string
	 *            例如13-14_2014-01-01 :前面表示13-14赛季，后面表示日期
	 * 
	 * @return Matchvo
	 */
	public Collection<? extends Matchvo> getTodayMatches(String string)
			throws RemoteException;

	/**
	 * 
	 * @param Id
	 *            球员的唯一标识符Id
	 * @param season
	 *            赛季
	 * @param isplayoff
	 *            是否为季后赛
	 * @return 返回的Playervo中为当前赛季该球员的所有属性,此时Playervo中的getPlayerItem为null
	 */
	public Playervo getPlayerById(String Id, String season, boolean isplayoff)
			throws RemoteException;

	/**
	 * 
	 * @param Id
	 *            球员的唯一标识符Id
	 * @return 返回的Playervo中getPlayerItem()可得到该球员所有历史数据
	 */
	public Playervo getPlayerById(String Id) throws RemoteException;

	/**
	 * 
	 * @param playerName
	 * @param season
	 * @param isPlayOff
	 * @return
	 */
	public Teamvo getTeamByPlayerId(String playerId, String season,
			boolean isPlayOff) throws RemoteException;

	/**
	 * 
	 * @param teamName
	 *            三个大写英文字母
	 * @return teamvo
	 */
	public Teamvo getTeamByTeamName(String teamNameEn, String season,
			boolean isPlayOff) throws RemoteException;

	/**
	 * @param field
	 *            :points,rebounds,assists,blockShots,steals,
	 *            threePointersPercentage
	 *            ,fieldGoalsPercentage,freeThrowsPercentage
	 * @return teamvo
	 */
	public Teamvo[] getHotTeams(String field, String season, boolean isPlayOff)
			throws RemoteException;

	/**
	 * @return 场均得分，场均篮板，场均助攻，罚球%，三分%
	 */
	public double[] getAlliancePlayerAverageData(String season,
			boolean isPlayOff) throws RemoteException;

	/**
	 * 根据球队和球员姓名返回playervo
	 * 
	 * @param playerName
	 *            球员英文姓名
	 * @param teamName
	 *            三个大写英文字母
	 */
	public Playervo getPlayerByNameAndTeam(String playerName)
			throws RemoteException;

	/**
	 * 
	 * @param season
	 *            没有isPlayOff是需要返回常规赛和季后赛比赛的简略信息
	 * @param teamName
	 *            三个大写英文字母
	 */
	public ArrayList<MatchSimpleInfovo> getMatchSimpleInfo(String teamName,
			String season) throws RemoteException;

	/**
	 * 
	 * @param teamNameEn
	 *            球队英文缩写
	 * @param season
	 *            赛季13-14
	 * @param isPlayOff
	 *            是否是季后赛
	 */
	Teamvo getTeamWithLatest10Data(String teamNameEn, String season,
			boolean isPlayOff) throws RemoteException;
}