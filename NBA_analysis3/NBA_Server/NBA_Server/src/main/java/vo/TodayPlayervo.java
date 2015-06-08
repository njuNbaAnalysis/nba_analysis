package vo;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;

/**
 * 用于记录某个球员某一天的数据情况，包括：该球员唯一标识符Pid，该球员姓名，该球员所在球队，今日的分。今日篮板，今日助攻，今日抢断，今日盖帽
 * */
public class TodayPlayervo implements Serializable{
	/**该球员唯一标识符Pid*/
	String pid;
	/**该球员姓名*/
	String name;
	/**该球员所在球队*/
	String team;
	/**该球员今日得分*/
	private int Points;
	/**该球员今日篮板*/
	private int Rebounds;
	/**该球员今日助攻*/
	private int Assists;
	/**该球员今日抢断*/
	private int steals;
	/**该球员今日盖帽*/
	private int blockShots;

	/**
	 * 对该类的所有属性进行构造
	 * */
	public TodayPlayervo(String pid,String name, String team, int Points,
			int Rebounds, int Assists, int steals, int blockShots) {
		this.name = name;
		this.team = team;
		this.Assists = Assists;
		this.Points = Points;
		this.Rebounds = Rebounds;
		this.steals = steals;
		this.blockShots = blockShots;
	}
	/**的发哦该球员的唯一标识符Pid*/
	public String getPid(){
		return pid;
	}
	/**的发哦该球员的姓名*/
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/**的发哦该球员所在球队名*/
	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	/**的发哦该球员的今日得分*/
	public int getPoints() {
		return Points;
	}

	/**的发哦该球员的今日篮板*/
	public int getRebounds() {
		return Rebounds;
	}

	/**的发哦该球员的今日助攻*/
	public int getAssists() {
		return Assists;
	}

	/**的发哦该球员的今日抢断*/	
	public int getSteals() {
		return steals;
	}

	/**的发哦该球员的今日盖帽*/
	public int getBlockShots() {
		return blockShots;
	}

	/**的发哦该球员的头像，如果头像不存在，则返回null*/
	public Image getAction() {
		Image image = null;
		try {
			String name = getName();
			String[] temp = name.split(" ");
			if(temp.length>2){
				name = temp[0]+" "+temp[temp.length-1];
			}
			image = ImageIO.read(new File("./Data/PlayerAction/" + name
					+ ".png"));
		} catch (IOException e) {
		}
		return image;
	}

}
