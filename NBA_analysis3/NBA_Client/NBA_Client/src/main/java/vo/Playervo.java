package vo;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.Date;

import javax.imageio.ImageIO;

public class Playervo implements Serializable {
	private String name;
	private String number; // 球衣号
	private String position; // G,G-F 类似格式
	private int[] height; // int[0]: foot,int[1]: inch
	private int weight;
	private Date birthday; // year,month,day
	private int age;
	private int experience; // 打了几年球，取值可能为R，如果为R则存储0
	private String school;
	// not raw data
	// 以***结尾的说明此项数据不需要存储
	private String team; // current team
	private String location;// 城市
	private String division;// 分区
	private char conference;// 分区
	private int gamePlayed;// 出场次数
	private int gameStarted;// 首发次数
	// 可场均
	private int rebounds;// 总篮板数
	private int assists;// 总助攻数
	private int minutes;// 总上场分钟数
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
	private int doubledouble;// 两双
	private int threedouble;// 三双
	private int fourdouble;// 四双
	private int fivedouble;// 五双
	private int PRA; // 得分，篮板，助攻一比一比一
	private double PRAaverage;// 场均

	private double sumOfTime = 0;
	private int sumOffieldGoalAttempts = 0;
	private int sumOfrebounds = 0;
	private int sumOfoffenseRebounds = 0;
	private int sumOfdefenseRebounds = 0;
	private int sumOfturnOver = 0;
	private int sumOffreeThrowAttempts = 0;
	private int sumOffieldGoalHits = 0;
	private int sumOfopponentrebounds = 0;
	private int sumOfopponentoffenserebounds = 0;
	private int sumOfopponentdefenseRebounds = 0;
	private int sumOfoppnentattempts = 0;// 两分出手次数
	private double sumOfoppnentDefensiveRounds = 0;// 对手进攻次数

	public Playervo(String name, String number, String position, int[] height,
			int weight, Date birthday, int age, int experience, String school,
			String team, String location, String division, char conference,
			int gamePlayed, int gameStarted, int rebounds, int assists,
			int minutes, int offenseRebounds, int defenseRebounds, int steals,
			int blockShots, int turnOver, int fouls, int points,
			double fieldGoalsPercentage, double threePointersPercentage,
			double freeThrowsPercentage, double trueShootingPercentage,
			double reboundsPercentage, double offenseReboundsPercentage,
			double defenseReboundsPercentage, double assistsPercentage,
			double stealsPercentage, double blockShotsPercentage,
			double turnOverPercentage, double usage, double[] upgradeRate,
			double efficiency, double gmsc, double shootingEfficiency,
			int fieldGoalHits, int fieldGoalAttempts, int threePointerHits,
			int threePointerAttempts, int freeThrowHits, int freeThrowAttempts,
			int doubledouble, int threedouble, int fourdouble, int fivedouble,
			int pRA, double pRAaverage, double sumOfTime,
			int sumOffieldGoalAttempts, int sumOfrebounds,
			int sumOfoffenseRebounds, int sumOfdefenseRebounds,
			int sumOfturnOver, int sumOffreeThrowAttempts,
			int sumOffieldGoalHits, int sumOfopponentrebounds,
			int sumOfopponentoffenserebounds, int sumOfopponentdefenseRebounds,
			int sumOfoppnentattempts, double sumOfoppnentDefensiveRounds) {
		super();
		this.name = name;
		this.number = number;
		this.position = position;
		this.height = height;
		this.weight = weight;
		this.birthday = birthday;
		this.age = age;
		this.experience = experience;
		this.school = school;
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
		this.gmsc = gmsc;
		this.shootingEfficiency = shootingEfficiency;
		this.fieldGoalHits = fieldGoalHits;
		this.fieldGoalAttempts = fieldGoalAttempts;
		this.threePointerHits = threePointerHits;
		this.threePointerAttempts = threePointerAttempts;
		this.freeThrowHits = freeThrowHits;
		this.freeThrowAttempts = freeThrowAttempts;
		this.doubledouble = doubledouble;
		this.threedouble = threedouble;
		this.fourdouble = fourdouble;
		this.fivedouble = fivedouble;
		PRA = pRA;
		PRAaverage = pRAaverage;
		this.sumOfTime = sumOfTime;
		this.sumOffieldGoalAttempts = sumOffieldGoalAttempts;
		this.sumOfrebounds = sumOfrebounds;
		this.sumOfoffenseRebounds = sumOfoffenseRebounds;
		this.sumOfdefenseRebounds = sumOfdefenseRebounds;
		this.sumOfturnOver = sumOfturnOver;
		this.sumOffreeThrowAttempts = sumOffreeThrowAttempts;
		this.sumOffieldGoalHits = sumOffieldGoalHits;
		this.sumOfopponentrebounds = sumOfopponentrebounds;
		this.sumOfopponentoffenserebounds = sumOfopponentoffenserebounds;
		this.sumOfopponentdefenseRebounds = sumOfopponentdefenseRebounds;
		this.sumOfoppnentattempts = sumOfoppnentattempts;
		this.sumOfoppnentDefensiveRounds = sumOfoppnentDefensiveRounds;
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

	public int[] getHeight() {
		return height;
	}

	public int getWeight() {
		return weight;
	}

	public Date getBirthday() {
		return birthday;
	}

	public int getAge() {
		return age;
	}

	public int getExperience() {
		return experience;
	}

	public String getSchool() {
		return school;
	}

	public Image getPortrait() {
		Image image = null;
        try {
            image = ImageIO.read(new File("./Data/PlayerAction/" + getName() + ".png"));
        } catch (IOException e) {
        }
        return image;
	}

	public Image getAction() {
		Image image = null;
        try {
            image = ImageIO.read(new File("./Data/PlayerImage/" + getName() + ".png"));
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

	public char getConference() {
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

	public int getMinutes() {
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

	public int getDoubledouble() {
		return doubledouble;
	}

	public int getThreedouble() {
		return threedouble;
	}

	public int getFourdouble() {
		return fourdouble;
	}

	public int getFivedouble() {
		return fivedouble;
	}

	public int getPRA() {
		return PRA;
	}

	public double getPRAaverage() {
		return PRAaverage;
	}

	public double getSumOfTime() {
		return sumOfTime;
	}

	public int getSumOffieldGoalAttempts() {
		return sumOffieldGoalAttempts;
	}

	public int getSumOfrebounds() {
		return sumOfrebounds;
	}

	public int getSumOfoffenseRebounds() {
		return sumOfoffenseRebounds;
	}

	public int getSumOfdefenseRebounds() {
		return sumOfdefenseRebounds;
	}

	public int getSumOfturnOver() {
		return sumOfturnOver;
	}

	public int getSumOffreeThrowAttempts() {
		return sumOffreeThrowAttempts;
	}

	public int getSumOffieldGoalHits() {
		return sumOffieldGoalHits;
	}

	public int getSumOfopponentrebounds() {
		return sumOfopponentrebounds;
	}

	public int getSumOfopponentoffenserebounds() {
		return sumOfopponentoffenserebounds;
	}

	public int getSumOfopponentdefenseRebounds() {
		return sumOfopponentdefenseRebounds;
	}

	public int getSumOfoppnentattempts() {
		return sumOfoppnentattempts;
	}

	public double getSumOfoppnentDefensiveRounds() {
		return sumOfoppnentDefensiveRounds;
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

	public double getAverageDoubleTwo() {
		if (gamePlayed == 0) {
			return 0;
		}
		return getDoubledouble() * 1.0 / gamePlayed;
	}

}
