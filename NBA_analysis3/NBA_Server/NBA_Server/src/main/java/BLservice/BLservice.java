package BLservice;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import vo.EventVo;
import vo.FutureMatchvo;
import vo.HotZonevo;
import vo.MatchSimpleInfovo;
import vo.Matchvo;
import vo.Playervo;
import vo.RecordOfPlayervo;
import vo.Teamvo;
import vo.TodayPlayervo;

/**
 * 服务端提供该界面的所有接口定义
 *
 */
public interface BLservice extends Remote {
	
	/**
	 * 直播所用接口
	 * */
	
	/**
	 * 获得未来比赛预告
	 * @param date 今日日期，格式为：2015-06-09
	 * @return 返回值为一个FutureMatchvo的ArrayList，用于储存2015-06-09当日的未进行比赛简要信息
	 * @throws RemoteException rmi服务器连接异常
	 * */
	public ArrayList<FutureMatchvo> getFutureMatches(String date) throws RemoteException;
	
	/**
	 * 直播前调用的通信（初始化）
	 * @return 返回值为true，代表初始化完毕，接受直播调用请求，false则表示请求失败
	 * @throws RemoteException rmi服务器连接异常
	 * */
	public boolean initNBALive() throws RemoteException;

	
	/**
	 * 得到当前正在直播的比赛即时信息，调用前先调用initNBALive（），并确认返回值为true
	 * @param Mid 比赛唯一标号
	 * @return 返回值为一个Matchvo对象，里面存储了一场比赛的各种数据
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public Matchvo getLiveMatchInfo(String Mid) throws RemoteException;

	
	/**
	 * 得到当前正在直播的即时直播事件（即为文字直播时所见到的描述），调用前先调用initNBALive（），并确认返回值为true
	 * @param Mid 比赛唯一标号
	 * @return 返回值为一个EventVo对象，里面存储了一条描述所对应的各种属性
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public ArrayList<EventVo> getLiveEvent(String Mid) throws RemoteException;
	
	
	
	/**
	 * 球员所用接口
	 * */
	
	
	
	/**
	 * 得到赛季的所有球员
	 * 
	 * @param Season
	 *            赛季名，例如"12-13"
	 * @param isPlayOff
	 *            true为季后赛，false为常规赛
	 *            
	 * @return 返回值为一个Playervo的ArrayList，Playervo里面存储了该球员对应赛季的各种属性
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public ArrayList<Playervo> getAllPlayers(String Season, boolean isPlayOff)
			throws RemoteException;

	
	/**
	 * 得到某个赛季的球员王
	 * 
	 * @param Season
	 *            赛季名，例如"12-13"
	 * @param isplayoff
	 *            true为季后赛，false为常规赛
	 * @param transferField
	 *            transferField为类型:point代表得分王，rebound代表篮板王，assist代表助攻王,steal代表抢断王,blockShot代表盖帽王
	 * @param number
	 *            number为人数
	 *    
	 * @return 返回值为一个Playervo的ArrayList，Playervo里面存储了该球员对应赛季的各种属性
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public ArrayList<Playervo> getSeasonKingPlayer(String transferField,
			int number, String Season, boolean isplayoff)
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
	 * @return 返回一个TodayPlayervo，存储某个球队某一天的比赛数据
	 * 			如果今日没有比赛，则返回过去最近一日的数据王
	 * 
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public ArrayList<TodayPlayervo> getTodayKingPlayer(String Date,String transferField,
			int number)
			throws RemoteException;
	
	
	/**
	 * 根据某个球员的ID，得到该球员某个赛季的属性
	 * 
	 * @param Id
	 *            球员的唯一标识符Id
	 * @param season
	 *            赛季
	 * @param isplayoff
	 *            是否为季后赛
	 * @return 返回的Playervo中为当前赛季该球员的所有属性,此时Playervo中的getPlayerItem为null,即球员的历史数据在此接口不提供
	 * 			若要获取某个球员的所有历史记录，情调用getPlayerById(String Id)接口
	 * 
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public Playervo getPlayerById(String Id, String season, boolean isplayoff)
			throws RemoteException;

	
	/**
	 * 根据某个球员的ID，得到该球员全部历史记录
	 * 
	 * @param Id
	 *            球员的唯一标识符Id
	 * @return 返回的Playervo中getPlayerItem()可得到该球员所有历史数据
	 * 
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public Playervo getPlayerById(String Id) throws RemoteException;
	
	
	/**
	 * 得到某个赛季的联盟平均水平
	 * 
	 * @param season
	 *            赛季
	 * @param isPlayOff
	 * 				是否为季后赛
	 * @return double数组依次为 场均得分，场均篮板，场均助攻，罚球%，三分%
	 * 
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public double[] getAlliancePlayerAverageData(String season,
			boolean isPlayOff) throws RemoteException;

	
	/**
	 * 根据球队和球员姓名返回playervo
	 * 
	 * @param playerName
	 *            球员英文姓名
	 * @return 返回的Playervo中为当前赛季该球员的所有属性,此时Playervo中的getPlayerItem为null,即球员的历史数据在此接口不提供
	 * 			若要获取某个球员的所有历史记录，情调用getPlayerById(String Id)接口
	 * 
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public Playervo getPlayerByNameAndTeam(String playerName)
			throws RemoteException;
	
	
	
	/**
	 * 根据球员Pid返回该球员近五场比赛信息RecordOfPlayervo
	 * 
	 * @param Pid
	 *            球员唯一标识符Pid
	 * @return 返回的RecordOfplayervo 的 ArrayList
	 * 
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public ArrayList<RecordOfPlayervo> getRecordOfPlayerById(String Pid)
			throws RemoteException;
	
	
	
	/**
	 * 球队所用接口
	 * */
	
	

	/**
	 * 得到某个赛季的所有球队
	 * 
	 * @param Season
	 *            赛季名，例如"12-13"
	 * @param isPlayOff
	 *            true为季后赛，false为常规赛
	 *
	 * @return 返回一个Teamvo的ArrayList，存储某个球队某赛季的各种数据属性
	 * 
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public ArrayList<Teamvo> getAllTeams(String Season, boolean isPlayOff)
			throws RemoteException;

	/**
	 * 得到某个球队某赛季常规赛或季后赛的热区数据
	 * @param teamNameEn
	 *            球队的3大写字母名称
	 * @param isSeason
	 *            true表示常规赛，false表示季后赛
	 * @param isTotal
	 *            此项为true时，返回所有球队的热区总计数据，此时teamNameEn项被忽略
	 * @return	返回一个HotZonevo，记录了一个球队的各个热区属性
	 * 
	 * @throws RemoteException	rmi服务器连接异常
	 */	
	public HotZonevo getHotZone(String teamNameEn, boolean isSeason,
			boolean isTotal) throws RemoteException;

	
	/**
	 * in abeyance,waiting for the formulas
	 * 
	 * @param teamNameEn  三个大写英文字母
	 * @param season  赛季数 例如：13-14赛季
	 * @param isplayoff  是否为季后赛
	 * @return 内线(中锋加上大前锋能力综合(综合能力即为playervo中的getREP()))、外线(其他位置能力综合(综合能力即为playervo中的getREP()))、
	 * 配合（场均助攻命中（除去罚球命中数）比）、进攻（平均得分）、防守（平均失分）
	 *
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public double[] getTeamAbility(String teamNameEn,String season,boolean isplayoff) throws RemoteException;

	
	/**
	 * 根据球员的id返回某赛季常规赛或季后赛的teamvo
	 * @param playerId	球员的Id
	 * @param season	赛季:如："13-14"
	 * @param isPlayOff 是否为季后赛
	 * @return 一个Teamvo，包含一个team当前赛季的所有属性
	 * 
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public Teamvo getTeamByPlayerId(String playerId, String season,
			boolean isPlayOff) throws RemoteException;

	/**
	 * 根据球队的英文缩写返回某赛季常规赛或季后赛的teamvo
	 * @param teamNameEn
	 *            三个大写英文字母
	 * @param season	赛季:如："13-14"
	 * @param isPlayOff 是否为季后赛
	 * 
	 * @return  一个Teamvo，包含一个team当前赛季的所有属性
	 * 
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public Teamvo getTeamByTeamName(String teamNameEn, String season,
			boolean isPlayOff) throws RemoteException;
	
	
	/**
	 * 根据球队的英文缩写得到某赛季常规赛或季后赛带有最近10场比赛的球队数据
	 * @param teamNameEn
	 *            球队英文缩写
	 * @param season
	 *            赛季13-14
	 * @param isPlayOff
	 *            是否是季后赛
	 *            
	 * @return 一个Teamvo，包含一个team当前赛季的所有属性
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public Teamvo getTeamWithLatest10Data(String teamNameEn, String season,
			boolean isPlayOff) throws RemoteException;
	
	
	
	
	/**
	 * 比赛所用接口
	 * */
	
	
	
	/**
	 * 获取某一天的所有比赛信息
	 * 
	 * @param date
	 *            例如13-14_2014-01-01 :前面表示13-14赛季，后面表示日期
	 * 
	 * @return Matchvo的Collection,包含了一个比赛的所有具体基础信息
	 * 
	 * @throws RemoteException	rmi服务器连接异常
	 */
	public Collection<? extends Matchvo> getTodayMatches(String date)
			throws RemoteException;


	/**
	 * 根据球队名返回一场比赛的简要信息
	 * 
	 * @param season
	 *            没有isPlayOff是需要返回常规赛和季后赛比赛的简略信息
	 * @param teamName
	 *            三个大写英文字母
	 * @return 一个  MatchSimpleInfovo包含一场比赛的简要信息
	 * 
	 * @throws RemoteException 	rmi服务器连接异常
	 */
	public ArrayList<MatchSimpleInfovo> getMatchSimpleInfo(String teamName,
			String season) throws RemoteException;
	
	/**
	 * 根据球队名返回两队近5场比赛简略信息
	 * 
	 * @param teamName1
	 *            三个大写英文字母
	 * @param teamName2
	 *            三个大写英文字母
	 * @return 一个  MatchSimpleInfovo包含一场比赛的简要信息
	 * 
	 * @throws RemoteException 	rmi服务器连接异常
	 */
	public ArrayList<MatchSimpleInfovo> getLatestMatchSimpleInfo(String teamName1,
			String teamName2) throws RemoteException;

	
	
	/**
	 * 根据球队名返回两队比赛胜率
	 * 
	 * @param teamName1
	 *            三个大写英文字母
	 * @param teamName2
	 *            三个大写英文字母
	 * @return 第一个队的胜率
	 * 
	 * @throws RemoteException 	rmi服务器连接异常
	 */
	public double getWinPercentage(String teamName1,
			String teamName2) throws RemoteException;
}
