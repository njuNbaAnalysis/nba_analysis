package vo;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import util.Tools;

/** 比赛直播中的更新事件
 * 事件属性：所在节(1~4，加时用5之后的数字表示)、时间（格式：mm:ss.ms,例如：10:10.1,表示本节还剩10分10.1秒）、比分(xx-xx)、参与球员（球员头像、球员姓名）、事件描述、球队（球队名、队标）
 */
public class EventVo implements Serializable {
	/**比赛节数（此时比赛进行到了第几节）*/
	int section;
	/**判断主客场（0表示第主队，1表示第客队）*/
	int num;
	/**此节比赛还剩多少时间（格式：mm:ss.ms,例如：10:10.1,表示本节还剩10分10.1秒）*/
	String time;
	/**比分(xx-xx 前面表示主队，后面表示客队)*/
	String points;// 得到两队比分
	/**此事件参与球员姓名*/
	String playerName;
	/**事件的描述（例如：科比 突破 上篮 得分！！！）*/
	String description;
	/**此事件参与球队名*/
	String teamName;


	/**
	 * 对该类的所有属性进行构造
	 * */
	public EventVo(int section, int num, String time, String points,
			String playerName, String description, String teamName) {
		super();
		this.section = section;
		this.num = num;
		this.time = time;
		this.points = points;
		this.playerName = playerName;
		this.description = description;
		this.teamName = teamName;
	}

	/**得到是否为主客场的信息
	 * */
	public int getNum() {
		return num;
	}

	/**得到比赛进行的节数s
	 * */
	public int getSection() {
		return section;
	}

	/**得到此节比赛剩余时间
	 * */
	public String getTime() {
		return time;
	}

	/**得到此时比赛的比分
	 * */
	public String getPoints() {
		return points;
	}

	/**得到比事件的球员名字
	 * */
	public String getPlayerName() {
		return playerName;
	}

	/**得到此事件的描述
	 * */
	public String getDescription() {
		return description;
	}

	/**得到此事件的球队名
	 * */
	public String getTeamName() {
		return teamName;
	}
	
	/**得到此事件的球队图像（Image）
	 * */
	public Image getTeamImage() {
		Image image = null;
		try {
			image = ImageIO.read(new File("./Data/teamImage/" + getTeamName()
					+ ".gif"));
		} catch (IOException e) {
		}
		return image;
	}

	/**得到此事件的球员头像（Image），如果该球员头像不存在，返回null
	 * */
	public Image getPlayerImage() {
		Image image = null;
		try {
			image = ImageIO.read(new File("./Data/PlayerImage/"
					+ getPlayerName() + ".png"));
		} catch (IOException e) {
		}
		return image;
	}

	/**以秒数的形式返回时间
	 * */
	public int getTimeInSecond() {

		return Tools.getSectionTimeInSecond(section) + getTimeFromSection();
	}

	/**得到距本节开始时间的秒数
	 * */
	public int getTimeFromSection() {
		String[] array = time.split(":");
		int minute = Integer.parseInt(array[0]);
		double second = Double.parseDouble(array[1]);
		second += minute * 60;
		if (section > 4) {
			second = 5 * 60 - second;
		}
		if (section <= 4) {
			second = 12 * 60 - second;
		}

		return (int) second;
	}

	/**返回本队的得分
	 * */ 
	public int getTeamPoint() {
		String[] array = points.split("-");
		return Integer.parseInt(array[num]);
	}

	/**返回本队对手的得分
	 * */ 
	public int getRivalPoint() {
		String[] array = points.split("-");
		return Integer.parseInt(array[1 - num]);
	}

}
