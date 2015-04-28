package logic.players;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Date;

import test.data.PlayerHighInfo;
import test.data.PlayerHotInfo;
import test.data.PlayerKingInfo;
import test.data.PlayerNormalInfo;
import test.data.TeamHighInfo;
import test.data.TeamHotInfo;
import test.data.TeamNormalInfo;
import logic.teams.Team;
import logic.teams.TeamController;

public class Player {
	private String name;
	private int number; // 球衣号
	private String position; // G,G-F 类似格式
	private int[] height; // int[0]: foot,int[1]: inch
	private int weight;
	private Date birthday; // year,month,day
	private int age;
	private int experience; // 打了几年球，取值可能为R，如果为R则存储0
	private String school;
	private Image portrait;
	private Image action;

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
	private ArrayList<playerData> ListOfRecord = new ArrayList<playerData>(); // 近五场比赛记录

	public ArrayList<playerData> getListOfRecord() {
		return ListOfRecord;
	}

	public void setListOfRecord(ArrayList<playerData> listOfRecord) {
		ListOfRecord = listOfRecord;
	}

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

	public void setTeam(String team) {
		this.team = team;
	}

	public void setGamePlayed(int gamePlayed) {
		this.gamePlayed = gamePlayed;
	}

	public void setGameStarted(int gameStarted) {
		this.gameStarted = gameStarted;
	}

	public void setDoubledouble(int doubledouble) {
		this.doubledouble = doubledouble;
	}

	public void setThreedouble(int threedouble) {
		this.threedouble = threedouble;
	}

	public void setRebounds(int rebounds) {
		this.rebounds = rebounds;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public void setMinutes(int minutes) {
		this.minutes = minutes;
	}

	public void setOffenseRebounds(int offenseRebounds) {
		this.offenseRebounds = offenseRebounds;
	}

	public void setDefenseRebounds(int defenseRebounds) {
		this.defenseRebounds = defenseRebounds;
	}

	public void setSteals(int steals) {
		this.steals = steals;
	}

	public void setBlockShots(int blockShots) {
		this.blockShots = blockShots;
	}

	public void setTurnOver(int turnOver) {
		this.turnOver = turnOver;
	}

	public void setFouls(int fouls) {
		this.fouls = fouls;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public void setFieldGoalsPercentage(double fieldGoalsPercentage) {
		this.fieldGoalsPercentage = fieldGoalsPercentage;
	}

	public void setThreePointersPercentage(double threePointersPercentage) {
		this.threePointersPercentage = threePointersPercentage;
	}

	public void setFreeThrowsPercentage(double freeThrowsPercentage) {
		this.freeThrowsPercentage = freeThrowsPercentage;
	}

	public void setTrueShootingPercentage(double trueShootingPercentage) {
		this.trueShootingPercentage = trueShootingPercentage;
	}

	public void setReboundsPercentage(double reboundsPercentage) {
		this.reboundsPercentage = reboundsPercentage;
	}

	public void setOffenseReboundsPercentage(double offenseReboundsPercentage) {
		this.offenseReboundsPercentage = offenseReboundsPercentage;
	}

	public void setDefenseReboundsPercentage(double defenseReboundsPercentage) {
		this.defenseReboundsPercentage = defenseReboundsPercentage;
	}

	public void setAssistsPercentage(double assistsPercentage) {
		this.assistsPercentage = assistsPercentage;
	}

	public void setStealsPercentage(double stealsPercentage) {
		this.stealsPercentage = stealsPercentage;
	}

	public void setBlockShotsPercentage(double blockShotsPercentage) {
		this.blockShotsPercentage = blockShotsPercentage;
	}

	public void setTurnOverPercentage(double turnOverPercentage) {
		this.turnOverPercentage = turnOverPercentage;
	}

	public void setUsage(double usage) {
		this.usage = usage;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}

	public void setGmsc(double gmsc) {
		this.gmsc = gmsc;
	}

	public void setShootingEfficiency(double shootingEfficiency) {
		this.shootingEfficiency = shootingEfficiency;
	}

	public void setFieldGoalHits(int fieldGoalHits) {
		this.fieldGoalHits = fieldGoalHits;
	}

	public void setFieldGoalAttempts(int fieldGoalAttempts) {
		this.fieldGoalAttempts = fieldGoalAttempts;
	}

	public void setThreePointerHits(int threePointerHits) {
		this.threePointerHits = threePointerHits;
	}

	public void setThreePointerAttempts(int threePointerAttempts) {
		this.threePointerAttempts = threePointerAttempts;
	}

	public void setFreeThrowHits(int freeThrowHits) {
		this.freeThrowHits = freeThrowHits;
	}

	public void setFreeThrowAttempts(int freeThrowAttempts) {
		this.freeThrowAttempts = freeThrowAttempts;
	}

	public int getFourdouble() {
		return fourdouble;
	}

	public void setFourdouble(int fourdouble) {
		this.fourdouble = fourdouble;
	}

	public int getFivedouble() {
		return fivedouble;
	}

	public void setFivedouble(int fivedouble) {
		this.fivedouble = fivedouble;
	}

	public int getDoubledouble() {
		return doubledouble;
	}

	public int getThreedouble() {
		return threedouble;
	}

	public void init() { // 每次得到这些高阶数据时，都需要init()
		TeamController teamcontrol = TeamController.getInstance();
		PlayerController playercontrol = PlayerController.getInstance();
		Team teamName = teamcontrol.getTeam(team);
		if (teamName != null) {
			location = teamName.getLocation();
			division = teamName.getDivision();
			conference = teamName.getConference();
			ArrayList<String> playerList = teamName.getPlayerList();
			double sumOfTime = 0;
			int sumOffieldGoalAttempts = 0;
			int sumOfrebounds = 0;
			int sumOfoffenseRebounds = 0;
			int sumOfdefenseRebounds = 0;
			int sumOfturnOver = 0;
			int sumOffreeThrowAttempts = 0;
			int sumOffreeThrowHits = 0;
			int sumOffieldGoalHits = 0;
			int sumOfopponentrebounds = teamName.getReboundsRival();
			int sumOfopponentoffenserebounds = teamName
					.getOffenseReboundsRival();
			int sumOfopponentdefenseRebounds = teamName
					.getDefenseReboundsRival();
			for (int i = 0; i < playerList.size(); i++) {
				if (playercontrol.getPlayer(playerList.get(i)) == null)
					continue; // 如果有些球员打了比赛，但是没有该球员的数据
				sumOfTime += playercontrol.getPlayer(playerList.get(i))
						.getMinutes();
				sumOffieldGoalAttempts += playercontrol.getPlayer(
						playerList.get(i)).getFieldGoalAttempts();
				sumOfrebounds += playercontrol.getPlayer(playerList.get(i))
						.getRebounds();
				sumOfdefenseRebounds += playercontrol.getPlayer(
						playerList.get(i)).getDefenseRebounds();
				sumOfoffenseRebounds += playercontrol.getPlayer(
						playerList.get(i)).getOffenseRebounds();
				sumOfturnOver += playercontrol.getPlayer(playerList.get(i))
						.getTurnOver();
				sumOffreeThrowAttempts += playercontrol.getPlayer(
						playerList.get(i)).getFreeThrowAttempts();
				sumOffreeThrowHits += playercontrol
						.getPlayer(playerList.get(i)).getFreeThrowHits();
				sumOffieldGoalHits += playercontrol
						.getPlayer(playerList.get(i)).getFieldGoalHits();
			}
			reboundsPercentage = rebounds * (sumOfTime * 1.0 / 5) / (minutes)
					/ (sumOfrebounds + sumOfopponentrebounds);// 球员篮板数×(球队所有球员上场时间÷5)÷球员上场时间÷(球队总篮板+对手总篮板)
			// if (name.equals("DeAndre Liggins")) {
			// System.out.println("offenseRebounds: " + offenseRebounds
			// + " sumofTime: " + (sumOfTime * 1.0 / 5) + " minutes: "
			// + minutes + " 球队总篮板: " + sumOfoffenseRebounds
			// + " 对手总篮板： " + sumOfopponentoffenserebounds);
			// }
			offenseReboundsPercentage = offenseRebounds * (sumOfTime * 1.0 / 5)
					/ (minutes)
					/ (sumOfoffenseRebounds + sumOfopponentoffenserebounds);
			defenseReboundsPercentage = defenseRebounds * (sumOfTime * 1.0 / 5)
					/ (minutes)
					/ (sumOfdefenseRebounds + sumOfopponentdefenseRebounds);
			assistsPercentage = assists
					/ (minutes / (sumOfTime * 1.0 / 5) * (sumOffieldGoalHits) - (fieldGoalHits));
			// 球员助攻数÷(球员上场时间÷(球队所有球员上场时间÷5)×球队总进球数-球员进球数)
			stealsPercentage = 1.0 * steals * (sumOfTime * 1.0 / 5) / minutes
					/ (teamName.getDefensiveRounds());// 球员抢断数×(球队所有球员上场时间÷5)÷球员上场时间÷对手进攻次数)
			blockShotsPercentage = blockShots
					* (sumOfTime / 5)
					/ minutes
					/ (teamName.getFieldGoalAttempsRival() - teamName
							.getThreePointerAttemptsRival());// 球员盖帽数×(球队所有球员上场时间÷5)÷球员上场时间÷对手两分球出手次数
			turnOverPercentage = turnOver
					/ ((fieldGoalAttempts - threePointerAttempts) + 0.44
							* freeThrowAttempts + turnOver);// ：球员失误数÷(球员两分球出手次数+0.44×球员罚球次数+球员失误数)
			usage = (fieldGoalAttempts + 0.44 * freeThrowAttempts + turnOver)
					* (sumOfTime / 5)
					/ (minutes)
					/ (sumOffieldGoalAttempts + 0.44 * sumOffreeThrowAttempts + sumOfturnOver);
			// ： (球员出手次数+0.44×球员罚球次数+球员失误次数)×(球队所有球员
			// 上场时间÷5)÷球员上场时间÷(球队所有总球员出手次数+0.44×球队所有球员罚球
			// 次数+球队所有球员失误次数)
		}
	}

	public int getPRA() {
		PRA = points + rebounds + assists;
		return PRA;
	}

	public double getPRAaverage() {
		if (gamePlayed != 0)
			PRAaverage = PRA / gamePlayed;
		return PRAaverage;
	}

	public String getDivision() {
		return division;
	}

	public char getConference() {
		return conference;
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

	public Player(String name, int number, String position, int[] height,
			int weight, Date birthday, int age, int experience, String school) {
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
	}

	// raw data
	public String getName() {
		return name;
	}

	public int getNumber() {
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
		return portrait;
	}

	public Image getAction() {
		return action;
	}

	public void setPortrait(Image portrait) {
		this.portrait = portrait;
	}

	public void setAction(Image action) {
		this.action = action;
	}

	// not raw data
	public String getTeam() {
		return team;
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

	public double getFieldGoalsPercentage() {
		if (fieldGoalAttempts != 0) {
			fieldGoalsPercentage = fieldGoalHits * 1.0 / fieldGoalAttempts;
		} else {
			fieldGoalsPercentage = 0;
		}
		return fieldGoalsPercentage;
	}

	public double getThreePointersPercentage() {
		if (threePointerAttempts != 0) {
			threePointersPercentage = threePointerHits * 1.0
					/ threePointerAttempts;
		} else {
			threePointersPercentage = 0;
		}
		return threePointersPercentage;
	}

	public double getFreeThrowsPercentage() {
		if (freeThrowAttempts != 0) {
			freeThrowsPercentage = freeThrowHits * 1.0 / freeThrowAttempts;
		} else {
			freeThrowsPercentage = 0;
		}
		return freeThrowsPercentage;
	}

	public double getTrueShootingPercentage() {
		if (fieldGoalAttempts != 0 || freeThrowAttempts != 0) {
			trueShootingPercentage = points * 1.0
					/ (fieldGoalAttempts + 0.44 * freeThrowAttempts) / 2;// 得分÷(2×(投篮出手数+0.44×罚球出手数))
		} else {
			trueShootingPercentage = 0;
		}
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

	public double getEfficiency() {
		efficiency = (rebounds + points + blockShots + steals + assists)
				- (fieldGoalAttempts - fieldGoalHits)
				- (freeThrowAttempts - freeThrowHits) - turnOver; // (得分+篮板+助攻+抢断+盖帽)-（出手次数-命中次数）-（罚球次数-罚球命中次数）-失误次数
		return efficiency;
	}

	public double getGmsc() {
		gmsc = points + 0.4 * fieldGoalHits - 0.7 * fieldGoalAttempts - 0.4
				* (freeThrowAttempts - freeThrowHits) + 0.7 * offenseRebounds
				+ 0.3 * defenseRebounds + steals + 0.7 * assists + 0.7
				* blockShots - 0.4 * fouls - turnOver;
		// 得分+0.4×投篮命中数-0.7×投篮出手数-0.4×(罚球出手数-罚球命
		// 中数)+0.7×前场篮板数+0.3×后场篮板数+抢断数+0.7×助攻数+0.7×盖帽数
		// -0.4×犯规数-失误数
		return gmsc;
	}

	public double getShootingEfficiency() {
		if (fieldGoalAttempts != 0) {
			shootingEfficiency = (fieldGoalHits + 0.5 * threePointerHits)
					/ (fieldGoalAttempts * 1.0);// (投篮命中数+0.5×三分命中数)÷投篮出手数
		} else {
			shootingEfficiency = 0;
		}
		return shootingEfficiency;
	}

	public String getLocation() {
		return location;
	}

	public String toString() {
		return name + " " + number + " " + position + " " + height[0] + "-"
				+ height[1] + " " + weight + " " + birthday.getYear() + " "
				+ birthday.getMonth() + " " + birthday.getDate() + " " + age
				+ " " + experience + " " + school + " ";

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

	// 逻辑方法

	public PlayerHighInfo getHighInfo() {
		PlayerHighInfo info = new PlayerHighInfo();
		info.setAssistEfficient(getAssistsPercentage());
		info.setBlockShotEfficient(getBlockShotsPercentage());
		info.setDefendReboundEfficient(getDefenseReboundsPercentage());
		info.setFaultEfficient(getTurnOverPercentage());
		info.setFrequency(getUsage());
		info.setGmSc(getAverageGmSc());
		if (conference == 'E')
			info.setLeague("East"); // 暂定
		else
			info.setLeague("West"); // 暂定
		info.setName(getName());
		info.setOffendReboundEfficient(getOffenseReboundsPercentage());
		info.setPosition(getPosition());
		info.setRealShot(getTrueShootingPercentage());
		info.setReboundEfficient(getReboundsPercentage());
		info.setShotEfficient(getShootingEfficiency());
		info.setStealEfficient(getStealsPercentage());
		info.setTeamName(getTeam());
		return info;
	}

	public PlayerHotInfo getHotInfo(String field) {
		// 默认average
		PlayerHotInfo info = new PlayerHotInfo();

		info.setField(field);
		switch (field) {
		case "point":
		case "score": // 热门球队处为score，sort处
			info.setValue(this.getAveragePoints());
			info.setUpgradeRate(this.getUpgradeRate()[0]);
			break;
		case "rebound":
			info.setValue(this.getAverageRebounds());
			info.setUpgradeRate(this.getUpgradeRate()[1]);
			break;
		case "assist":
			info.setValue(this.getAverageAssists());
			info.setUpgradeRate(this.getUpgradeRate()[2]);
			break;
		default:
			System.out.println("error in Player.getHotInfo: " + field);
		}

		info.setName(name);
		info.setTeamName(team);
		info.setPosition(position);
		return info;
	}

	public PlayerKingInfo getKingInfo(String field) {
		// 默认average
		PlayerKingInfo info = new PlayerKingInfo();

		info.setField(field);
		switch (field) {
		case "point":
		case "score": // 热门球队处为score，sort处
			info.setValue(this.getAveragePoints());
			break;
		case "rebound":
			info.setValue(this.getAverageRebounds());
			break;
		case "assist":
			info.setValue(this.getAverageAssists());
			break;
		default:
			System.out.println("error in Player.getHotInfo: " + field);
		}

		info.setName(name);
		info.setTeamName(team);
		info.setPosition(position);
		return info;
	}

	public double[] getUpgradeRate() {
		if (gamePlayed > 5) {
			double FivePoints = 0;
			double FiveRebounds = 0;
			double FiveAssists = 0;
			for (int i = 0; i < ListOfRecord.size(); i++) {
				FivePoints += ListOfRecord.get(i).getPoints();
				FiveRebounds += ListOfRecord.get(i).getRebounds();
				FiveAssists += ListOfRecord.get(i).getAssists();
			}
			if (getAveragePoints() != 0)
				upgradeRate[0] = (FivePoints / 5 - (getPoints() - FivePoints)
						/ (gamePlayed - 5))
						/ (getAveragePoints());
			else
				upgradeRate[0] = 0;
			if (getAverageRebounds() != 0)
				upgradeRate[1] = (FiveRebounds / 5 - (getRebounds() - FiveRebounds)
						/ (gamePlayed - 5))
						/ (getAverageRebounds());
			else
				upgradeRate[1] = 0;
			if (getAssists() != 0)
				upgradeRate[2] = (FiveAssists / 5 - (getAssists() - FiveAssists)
						/ (gamePlayed - 5))
						/ (getAverageAssists());
			else
				upgradeRate[2] = 0;
		} else {
			upgradeRate[0] = 0;
			upgradeRate[1] = 0;
			upgradeRate[2] = 0;
		}
		return upgradeRate;
	}

	public void setUpgradeRate(double[] upgradeRate) {
		this.upgradeRate = upgradeRate;
	}

	public void AddRecord(double points, double rebounds, double assists,
			double steal, double block, String date, String enemy) {
		if (ListOfRecord.size() >= 5)
			ListOfRecord.remove(0);
		ListOfRecord.add(new playerData(points, rebounds, assists, steal,
				block, date, enemy));
	}

	public PlayerNormalInfo getNormalInfo(boolean isAverage) {

		PlayerNormalInfo info = new PlayerNormalInfo();

		if (isAverage) {
			info.setPoint(this.getAveragePoints());
			info.setRebound(this.getAverageRebounds());
			info.setAssist(this.getAverageAssists());
			info.setBlockShot(this.getAverageBlockShots());
			info.setSteal(this.getAverageSteals());
			info.setFoul(this.getAverageFouls());
			info.setFault(this.getAverageTurnOver());
			info.setDefend(this.getAverageDefenseRebounds()); // 待定
			info.setEfficiency(this.getAverageEfficiency());
			info.setMinute(this.getAverageMinutes());
			info.setOffend(this.getAverageOffenseRebounds());
			info.setPoint(this.getAveragePoints());

		} else {
			info.setPoint(this.getPoints());
			info.setRebound(this.getRebounds());
			info.setAssist(this.getAssists());
			info.setBlockShot(this.getBlockShots());
			info.setSteal(this.getSteals());
			info.setFoul(this.getFouls());
			info.setFault(this.getTurnOver());
			info.setDefend(this.getDefenseRebounds());
			info.setEfficiency(this.getAverageEfficiency()); 	// 待定
			info.setMinute(this.getMinutes() / 60); 
			info.setOffend(this.getOffenseRebounds());
			info.setPoint(this.getPoints());
		}
		info.setAge(getAge());
		info.setName(getName());
		info.setTeamName(getTeam());
		info.setStart(getGameStarted());
		info.setNumOfGame(getGamePlayed());
		info.setShot(this.getFieldGoalsPercentage());
		info.setThree(this.getThreePointersPercentage());
		info.setPenalty(this.getFreeThrowsPercentage());
		return info;
	}

	public double FivePoints() {
		if (ListOfRecord.size() != 5)
			return 0;
		else {
			double total = 0;
			for (int i = 0; i < ListOfRecord.size(); i++) {
				total += ListOfRecord.get(i).getPoints();
			}
			return total / 5.0;
		}
	}

	public double FiveRebounds() {
		if (ListOfRecord.size() != 5)
			return 0;
		else {
			double total = 0;
			for (int i = 0; i < ListOfRecord.size(); i++) {
				total += ListOfRecord.get(i).getRebounds();
			}
			return total / 5.0;
		}
	}

	public double FiveAssists() {
		if (ListOfRecord.size() != 5)
			return 0;
		else {
			double total = 0;
			for (int i = 0; i < ListOfRecord.size(); i++) {
				total += ListOfRecord.get(i).getAssists();
			}
			return total / 5.0;
		}
	}
}
