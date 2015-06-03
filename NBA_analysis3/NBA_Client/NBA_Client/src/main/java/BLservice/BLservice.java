package BLservice;

import java.util.ArrayList;

import vo.EventVo;
import vo.Matchvo;
import vo.Playervo;
import vo.Teamvo;

public interface BLservice {
	/**
	 * 得到当前正在直播的比赛信息
	 */
	public Matchvo getLiveMatchInfo();

	/**
	 * 得到当前正在直播的直播事件
	 */
	public ArrayList<EventVo> getLiveEvent();

	/**
	 * 得到赛季的所有球员
	 * 
	 * @param Season 赛季名，例如"12-13"
	 * @param isPlayOff  true为季后赛，false为常规赛
	 */
	public ArrayList<Playervo> getAllPlayers(String Season, boolean isPlayOff);

	/**
	 * 得到赛季的所有球队
	 * 
	 * @param Season 赛季名，例如"12-13"
	 * @param isPlayOff true为季后赛，false为常规赛
	 */
	public ArrayList<Teamvo> getAllTeams(String Season, boolean isPlayOff);
}