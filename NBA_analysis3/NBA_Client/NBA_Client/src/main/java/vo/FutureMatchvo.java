package vo;

import java.io.Serializable;


/**
 * 未来比赛简要信息
 * */
public class FutureMatchvo  implements Serializable{
	/**比赛唯一编号*/
	private String Mid;
	/**比赛日期*/
	private String date;
	/**主队名（英文缩写）*/
	private String home_team;
	/**客队名（英文缩写）*/
	private String away_team;
	
	/**全属性构造*/
	public FutureMatchvo(String mid, String date, String home_team,
			String away_team) {
		super();
		Mid = mid;
		this.date = date;
		this.home_team = home_team;
		this.away_team = away_team;
	}

	/**获得比赛唯一标号*/
	public String getMid() {
		return Mid;
	}

	/**获取比赛日期*/
	public String getDate() {
		return date;
	}

	/**获取主队名*/
	public String getHome_team() {
		return home_team;
	}

	/**获取客队名*/
	public String getAway_team() {
		return away_team;
	}
	
	
}
