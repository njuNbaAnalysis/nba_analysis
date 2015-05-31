package BLservice;

import java.util.ArrayList;

import vo.EventVo;
import vo.Matchvo;

public interface matchBLservice {
	//得到当前正在直播的比赛信息，需要实时汇总
	public Matchvo getLiveMatchInfo();
	
	//得到直播事件list
	public ArrayList<EventVo> getLiveEvent();
	
}
