package vo;

import java.awt.Image;
import java.io.Serializable;

/* 比赛直播中的更新事件
 * 事件属性：所在节(1~4，加时用5之后的数字表示)、时间（格式：mm:ss.ms,例如：10:10.1,表示本节还剩10分10.1秒）、比分(xx-xx)、参与球员（球员头像、球员姓名）、事件描述、球队（球队名、队标）
 */
public class EventVo implements Serializable{
	int section;
	int num;// 0表示第一个队，1表示第二个队
	String time;
	String points;// 得到两队比分
	Image playerPortrait;
	String playerName;
	String description;
	String teamName;
	Image teamImage;

	public EventVo(int section, int num, String time, String points,
			Image playerPortrait, String playerName, String description,
			String teamName, Image teamImage) {
		super();
		this.section = section;
		this.num = num;
		this.time = time;
		this.points = points;
		this.playerPortrait = playerPortrait;
		this.playerName = playerName;
		this.description = description;
		this.teamName = teamName;
		this.teamImage = teamImage;
	}

	public int getNum() {
		return num;
	}

	public int getSection() {
		return section;
	}

	public String getTime() {
		return time;
	}

	public String getPoints() {
		return points;
	}

	public Image getPlayerPortrait() {
		return playerPortrait;
	}

	public String getPlayerName() {
		return playerName;
	}

	public String getDescription() {
		return description;
	}

	public String getTeamName() {
		return teamName;
	}

	public Image getTeamImage() {
		return teamImage;
	}

	// 以秒数的形式返回时间
	public int getTimeInSecond() {
		String[] array = time.split(":");
		int minute = Integer.parseInt(array[0]);
		double second = Double.parseDouble(array[1]);
		return (int) (minute * 60 + second);
	}

	// 返回本队的得分
	public int getTeamPoint() {
		String[] array = points.split("-");
		return Integer.parseInt(array[num]);
	}

	// 返回本队的得分
	public int getRivalPoint() {
		String[] array = points.split("-");
		return Integer.parseInt(array[1-num]);
	}

}
