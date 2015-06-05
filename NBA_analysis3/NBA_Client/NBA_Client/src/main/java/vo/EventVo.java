package vo;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/* 比赛直播中的更新事件
 * 事件属性：所在节(1~4，加时用5之后的数字表示)、时间（格式：mm:ss.ms,例如：10:10.1,表示本节还剩10分10.1秒）、比分(xx-xx)、参与球员（球员头像、球员姓名）、事件描述、球队（球队名、队标）
 */
public class EventVo implements Serializable{
	int section;
	int num;// 0表示第主队，1表示第客队
	String time;
	String points;// 得到两队比分
	String playerName;
	String description;
	String teamName;

	public EventVo(int section, int num, String time, String points,
			String playerName, String description,
			String teamName) {
		super();
		this.section = section;
		this.num = num;
		this.time = time;
		this.points = points;
		this.playerName = playerName;
		this.description = description;
		this.teamName = teamName;
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


	public String getPlayerName() {
		return playerName;
	}

	public String getDescription() {
		return description;
	}

	public String getTeamName() {
		return teamName;
	}

	public Image getTeamImage(){
		Image image = null;
        try {
            image = ImageIO.read(new File("./Data/teamImage/" + getTeamName() + ".gif"));
        } catch (IOException e) {
        }
        return image;
	}
	
	public Image getPlayerImage(){
		Image image = null;
        try {
            image = ImageIO.read(new File("./Data/PlayerImage/" + getPlayerName() + ".png"));
        } catch (IOException e) {
        }
        return image;
	}

	// 以秒数的形式返回时间
	public int getTimeInSecond() {
		String[] array = time.split(":");
		int minute = Integer.parseInt(array[0]);
		double second = Double.parseDouble(array[1]);
		
		for(int i=1;i<section;i++){
			minute+=12;
		}
		for(int i=4;i<section;i++){
			minute+=5;
		}
		return (int) (minute * 60 + second);
	}
	
	// 以秒数的形式返回时间
		public int getSectionTime() {
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
