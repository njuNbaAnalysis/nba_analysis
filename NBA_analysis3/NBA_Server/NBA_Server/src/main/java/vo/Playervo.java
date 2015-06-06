package vo;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

public class Playervo implements Serializable {
	private String Pid;  //唯一标识符
	private String name;
	private String number; // 球衣号
	private String position; // G,G-F 类似格式
	private double height; // int[0]: foot,int[1]: inch
	private double weight;
	private String birthday; // year,month,day
	private String selected; // 选举情况
	private String salary; // 薪水
	private String Highschool;
	private String University;
	// not raw data
	// 以***结尾的说明此项数据不需要存储
	private String team; // current team
	private String location;// 城市
	private String division;// 分区
	private String conference;// 分区
	private int gamePlayed;// 出场次数
	private int gameStarted;// 首发次数
	// 可场均
	private int rebounds;// 总篮板数
	private int assists;// 总助攻数
	private double minutes;// 总上场时间
	private int offenseRebounds;// 总进攻篮板
	private int defenseRebounds;// 总防守篮板
	private int steals;// 总抢断数
	private int blockShots;// 总盖帽数
	private int turnOver;// 总失误数
	private int fouls;// 总犯规数
	private int points;// 总得分数
	// 百分比率
	private double fieldGoalsPercentage;// 总投篮命中率
	private double threePointersPercentage;// 三分命中率
	private double freeThrowsPercentage;// 罚球命中率
	private double trueShootingPercentage;// 真实投篮命中率
	private double reboundsPercentage;// 篮板率
	private double offenseReboundsPercentage;// 进攻篮板率
	private double defenseReboundsPercentage;// 防守篮板率
	private double assistsPercentage;// 助攻率
	private double stealsPercentage;// 抢断率
	private double blockShotsPercentage;// 盖帽率
	private double turnOverPercentage;// 失误率
	private double usage;// 使用率
	private double[] upgradeRate = new double[3]; // 近五场比赛提升率,按次序依次为得分，篮板，助攻
	// 效率值
	private double efficiency;// 效率值
	private double gmsc;// gmsc效率值
	private double shootingEfficiency;// 投篮效率
	// tempData 不用做界面显示，在计算中用到，可能用作以后的界面显示
	private int fieldGoalHits; // 投篮命中
	private int fieldGoalAttempts; // 投篮出手
	private int threePointerHits; // 三分命中
	private int threePointerAttempts; // 三分出手
	private int freeThrowHits; // 罚球命中
	private int freeThrowAttempts; // 罚球出手
	
	private double WS;			//胜利贡献值，简称WS，指一名球员为球队胜利所做的贡献
	private double offenseWS;
	private double defenseWS;
	private int dunk;               //扣篮数
	private int blocked;				//被帽数
	
	private ArrayList<PlayerItemvo> recordOfPlayer;
	
	public Playervo(String Pid,String name, String number, String position, double height,
			double weight, String birthday, String selected, String salary,
			String highschool, String university, String team, String location,
			String division, String conference, int gamePlayed, int gameStarted,
			int rebounds, int assists, double minutes, int offenseRebounds,
			int defenseRebounds, int steals, int blockShots, int turnOver,
			int fouls, int points, double fieldGoalsPercentage,
			double threePointersPercentage, double freeThrowsPercentage,
			double trueShootingPercentage, double reboundsPercentage,
			double offenseReboundsPercentage, double defenseReboundsPercentage,
			double assistsPercentage, double stealsPercentage,
			double blockShotsPercentage, double turnOverPercentage,
			double usage, double[] upgradeRate, double efficiency,
			double shootingEfficiency, int fieldGoalHits,
			int fieldGoalAttempts, int threePointerHits,
			int threePointerAttempts, int freeThrowHits, int freeThrowAttempts,
			double WS, double offenseWS, double defenseWS, int dunk, int blocked) {
		super();
		this.Pid = Pid;
		this.name = name;
		this.number = number;
		this.position = position;
		this.height = height;
		this.weight = weight;
		this.birthday = birthday;
		this.selected = selected;
		this.salary = salary;
		Highschool = highschool;
		University = university;
		this.team = team;
		this.location = location;
		this.division = division;
		this.conference = conference;
		this.gamePlayed = gamePlayed;
		this.gameStarted = gameStarted;
		this.rebounds = rebounds;
		this.assists = assists;
		this.minutes = minutes;
		this.offenseRebounds = offenseRebounds;
		this.defenseRebounds = defenseRebounds;
		this.steals = steals;
		this.blockShots = blockShots;
		this.turnOver = turnOver;
		this.fouls = fouls;
		this.points = points;
		this.fieldGoalsPercentage = fieldGoalsPercentage;
		this.threePointersPercentage = threePointersPercentage;
		this.freeThrowsPercentage = freeThrowsPercentage;
		this.trueShootingPercentage = trueShootingPercentage;
		this.reboundsPercentage = reboundsPercentage;
		this.offenseReboundsPercentage = offenseReboundsPercentage;
		this.defenseReboundsPercentage = defenseReboundsPercentage;
		this.assistsPercentage = assistsPercentage;
		this.stealsPercentage = stealsPercentage;
		this.blockShotsPercentage = blockShotsPercentage;
		this.turnOverPercentage = turnOverPercentage;
		this.usage = usage;
		this.upgradeRate = upgradeRate;
		this.efficiency = efficiency;
		this.shootingEfficiency = shootingEfficiency;
		this.fieldGoalHits = fieldGoalHits;
		this.fieldGoalAttempts = fieldGoalAttempts;
		this.threePointerHits = threePointerHits;
		this.threePointerAttempts = threePointerAttempts;
		this.freeThrowHits = freeThrowHits;
		this.freeThrowAttempts = freeThrowAttempts;
		this.WS = WS;
		this.offenseWS = offenseWS;
		this.defenseWS = defenseWS;
		this.dunk = dunk;
		this.blocked = blocked;
	}

	public String getName() {
		return name;
	}

	public String getNumber() {
		return number;
	}

	public String getPosition() {
		return position;
	}

	public double getHeight() {
		return height;
	}

	public double getWeight() {
		return weight;
	}

	public String getBirthday() {
		return birthday;
	}

	public String getSelected() {
		return selected;
	}

	public String getSalary() {
		return salary;
	}

	public String getHighschool() {
		return Highschool;
	}

	public String getUniversity() {
		return University;
	}

	public Image getPortrait() {
		Image image = null;
		try {
			image = ImageIO.read(new File("./Data/PlayerAction/" + getName()
					+ ".png"));
		} catch (IOException e) {
		}
		return image;
	}

	public Image getAction() {
		Image image = null;
		try {
			image = ImageIO.read(new File("./Data/PlayerImage/" + getName()
					+ ".png"));
		} catch (IOException e) {
		}
		return image;
	}

	public String getTeam() {
		return team;
	}

	public String getLocation() {
		return location;
	}

	public String getDivision() {
		return division;
	}

	public String getConference() {
		return conference;
	}

	public int getGamePlayed() {
		return gamePlayed;
	}

	public int getGameStarted() {
		return gameStarted;
	}

	public int getRebounds() {
		return rebounds;
	}

	public int getAssists() {
		return assists;
	}

	public double getMinutes() {
		return minutes;
	}

	public int getOffenseRebounds() {
		return offenseRebounds;
	}

	public int getDefenseRebounds() {
		return defenseRebounds;
	}

	public int getSteals() {
		return steals;
	}

	public int getBlockShots() {
		return blockShots;
	}

	public int getTurnOver() {
		return turnOver;
	}

	public int getFouls() {
		return fouls;
	}

	public int getPoints() {
		return points;
	}

	public double getFieldGoalsPercentage() {
		return fieldGoalsPercentage;
	}

	public double getThreePointersPercentage() {
		return threePointersPercentage;
	}

	public double getFreeThrowsPercentage() {
		return freeThrowsPercentage;
	}

	public double getTrueShootingPercentage() {
		return trueShootingPercentage;
	}

	public double getReboundsPercentage() {
		return reboundsPercentage;
	}

	public double getOffenseReboundsPercentage() {
		return offenseReboundsPercentage;
	}

	public double getDefenseReboundsPercentage() {
		return defenseReboundsPercentage;
	}

	public double getAssistsPercentage() {
		return assistsPercentage;
	}

	public double getStealsPercentage() {
		return stealsPercentage;
	}

	public double getBlockShotsPercentage() {
		return blockShotsPercentage;
	}

	public double getTurnOverPercentage() {
		return turnOverPercentage;
	}

	public double getUsage() {
		return usage;
	}

	public double[] getUpgradeRate() {
		return upgradeRate;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public double getGmsc() {
		gmsc = points + 0.4 * fieldGoalHits - 0.7 * fieldGoalAttempts - 0.4
				* (freeThrowAttempts - freeThrowHits) + 0.7 * offenseRebounds
				+ 0.3 * defenseRebounds + steals + 0.7 * assists + 0.7
				* blockShots - 0.4 * fouls - turnOver;
		return gmsc;
	}

	public double getShootingEfficiency() {
		return shootingEfficiency;
	}

	public int getFieldGoalHits() {
		return fieldGoalHits;
	}

	public int getFieldGoalAttempts() {
		return fieldGoalAttempts;
	}

	public int getThreePointerHits() {
		return threePointerHits;
	}

	public int getThreePointerAttempts() {
		return threePointerAttempts;
	}

	public int getFreeThrowHits() {
		return freeThrowHits;
	}

	public int getFreeThrowAttempts() {
		return freeThrowAttempts;
	}

	public double getAverageRebounds() {
		if (gamePlayed == 0) {
			return 0;
		}
		return getRebounds() * 1.0 / gamePlayed;
	}

	public double getAverageOffenseRebounds() {
		if (gamePlayed == 0) {
			return 0;
		}
		return getOffenseRebounds() * 1.0 / gamePlayed;
	}

	public double getAverageDefenseRebounds() {
		if (gamePlayed == 0) {
			return 0;
		}
		return getDefenseRebounds() * 1.0 / gamePlayed;
	}

	public double getAverageAssists() {
		if (gamePlayed == 0) {
			return 0;
		}
		return getAssists() * 1.0 / gamePlayed;
	}

	public double getAverageBlockShots() {
		if (gamePlayed == 0) {
			return 0;
		}
		return getBlockShots() * 1.0 / gamePlayed;
	}

	public double getAverageSteals() {
		if (gamePlayed == 0) {
			return 0;
		}
		return getSteals() * 1.0 / gamePlayed;
	}

	public double getAverageTurnOver() {
		if (gamePlayed == 0) {
			return 0;
		}
		return getTurnOver() * 1.0 / gamePlayed;
	}

	public double getAverageMinutes() {
		if (gamePlayed == 0) {
			return 0;
		}
		return getMinutes() * 1.0 / gamePlayed / 60;
	}

	public double getAverageEfficiency() {
		if (gamePlayed == 0) {
			return 0;
		}
		return getEfficiency() * 1.0 / gamePlayed;
	}

	public double getAverageGmSc() {
		if (gamePlayed == 0) {
			return 0;
		}
		return getGmsc() * 1.0 / gamePlayed;
	}

	public double getAverageFouls() {
		if (gamePlayed == 0) {
			return 0;
		}
		return getFouls() * 1.0 / gamePlayed;
	}

	public double getAveragePoints() {
		if (gamePlayed == 0) {
			return 0;
		}
		return getPoints() * 1.0 / gamePlayed;
	}

	public double getWS() {
		return WS;
	}

	public double getOffenseWS() {
		return offenseWS;
	}

	public double getDefenseWS() {
		return defenseWS;
	}

	public int getDunk() {
		return dunk;
	}

	public int getBlocked() {
		return blocked;
	}

	public String getPid() {
		return Pid;
	}

	public ArrayList<PlayerItemvo> getRecordOfPlayer() {
		return recordOfPlayer;
	}

	public void setRecordOfPlayer(ArrayList<PlayerItemvo> recordOfPlayer) {
		this.recordOfPlayer = recordOfPlayer;
	}

}
