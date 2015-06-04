package vo;

import java.io.Serializable;
import java.util.ArrayList;

import util.SvgImage;

public class Teamvo implements Serializable {
	// raw data
	private String name; // 球队名
	private String abbreviation; // 缩写
	private String location; // 地区
	private char conference; // 东部赛区or西部赛区 E,W
	private String division;
	private String homeCourt; // 主场
	private int setUpTime; // 建立时间
	private SvgImage logo; // 队标

	// not raw data
	private ArrayList<String> playerList = new ArrayList<String>(); // 球员列表

	// 累加
	private int numOfMatches; // 比赛场数
	private int numOfVictory; // 胜利场数
	private int fieldGoalAttemps; // 投篮出手次数
	private int fieldGoalHits; // 投篮命中次数
	private int threePointerAttempts; // 三分出手次数
	private int threePointerHits; // 三分命中次数
	private int freeThrowAttempts; // 罚球出手次数
	private int freeThrowHits; // 罚球命中次数
	private int offensiveRebounds; // 进攻篮板数
	private int defensiveRebounds; // 防守篮板数

	private int assists; // 助攻数
	private int steals; // 抢断数
	private int blockShots; // 总盖帽数
	private int turnOver; // 总失误数
	private int fouls; // 总犯规数
	private int points; // 总比赛得分数

	// 后期-交叉处理
	private double offensiveRounds; // 进攻回合
	private double defensiveRounds; // 防守回合
	private double offenseEfficiency; // 进攻效率
	private double defenseEfficiency; // 防守效率
	private double reboundsEfficiency; // 篮板效率
	private double stealsEfficiency; // 抢断效率
	private double assistsPercentage; // 助攻率

	// computeData 计算过程中用到，界面后期可能用到
	private int pointsRival; // 对手总得分
	private int fieldGoalAttempsRival; // 对手投篮出手次数
	private int threePointerAttemptsRival; // 对手三分出手次数
	private int offenseReboundsRival; // 对手总进攻篮板
	private int defenseReboundsRival; // 对手总防守篮板

	// 仅界面需要
	private int rankingInLeague; // 本赛季在联盟中的排名，东西部分别计算

	// 后期
	// private int rebounds; //篮板数
	// private double threePointersPercentage; //三分命中率
	// private double freeThrowsPercentage; //罚球命中率
	// private double fieldGoalsPercentage; //投篮命中率
	// private double winningPercentage; //胜率
	// private int reboundsRival; //对手总篮板

	public Teamvo(String name, String abbreviation, String location,
			char conference, String division, String homeCourt, int setUpTime,
			SvgImage logo, ArrayList<String> playerList, int numOfMatches,
			int numOfVictory, int fieldGoalAttemps, int fieldGoalHits,
			int threePointerAttempts, int threePointerHits,
			int freeThrowAttempts, int freeThrowHits, int offensiveRebounds,
			int defensiveRebounds, int assists, int steals, int blockShots,
			int turnOver, int fouls, int points, double offensiveRounds,
			double defensiveRounds, double offenseEfficiency,
			double defenseEfficiency, double reboundsEfficiency,
			double stealsEfficiency, double assistsPercentage, int pointsRival,
			int fieldGoalAttempsRival, int threePointerAttemptsRival,
			int offenseReboundsRival, int defenseReboundsRival,
			int rankingInLeague) {
		super();
		this.name = name;
		this.abbreviation = abbreviation;
		this.location = location;
		this.conference = conference;
		this.division = division;
		this.homeCourt = homeCourt;
		this.setUpTime = setUpTime;
		this.logo = logo;
		this.playerList = playerList;
		this.numOfMatches = numOfMatches;
		this.numOfVictory = numOfVictory;
		this.fieldGoalAttemps = fieldGoalAttemps;
		this.fieldGoalHits = fieldGoalHits;
		this.threePointerAttempts = threePointerAttempts;
		this.threePointerHits = threePointerHits;
		this.freeThrowAttempts = freeThrowAttempts;
		this.freeThrowHits = freeThrowHits;
		this.offensiveRebounds = offensiveRebounds;
		this.defensiveRebounds = defensiveRebounds;
		this.assists = assists;
		this.steals = steals;
		this.blockShots = blockShots;
		this.turnOver = turnOver;
		this.fouls = fouls;
		this.points = points;
		this.offensiveRounds = offensiveRounds;
		this.defensiveRounds = defensiveRounds;
		this.offenseEfficiency = offenseEfficiency;
		this.defenseEfficiency = defenseEfficiency;
		this.reboundsEfficiency = reboundsEfficiency;
		this.stealsEfficiency = stealsEfficiency;
		this.assistsPercentage = assistsPercentage;
		this.pointsRival = pointsRival;
		this.fieldGoalAttempsRival = fieldGoalAttempsRival;
		this.threePointerAttemptsRival = threePointerAttemptsRival;
		this.offenseReboundsRival = offenseReboundsRival;
		this.defenseReboundsRival = defenseReboundsRival;
		this.rankingInLeague = rankingInLeague;
	}

	public String getName() {
		return name;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public String getLocation() {
		return location;
	}

	public char getConference() {
		return conference;
	}

	public String getDivision() {
		return division;
	}

	public String getHomeCourt() {
		return homeCourt;
	}

	public int getSetUpTime() {
		return setUpTime;
	}

	public SvgImage getLogo() {
		return logo;
	}

	public ArrayList<String> getPlayerList() {
		return playerList;
	}

	public int getNumOfMatches() {
		return numOfMatches;
	}

	public int getNumOfVictory() {
		return numOfVictory;
	}

	public int getFieldGoalAttemps() {
		return fieldGoalAttemps;
	}

	public int getFieldGoalHits() {
		return fieldGoalHits;
	}

	public int getThreePointerAttempts() {
		return threePointerAttempts;
	}

	public int getThreePointerHits() {
		return threePointerHits;
	}

	public int getFreeThrowAttempts() {
		return freeThrowAttempts;
	}

	public int getFreeThrowHits() {
		return freeThrowHits;
	}

	public int getOffensiveRebounds() {
		return offensiveRebounds;
	}

	public int getDefensiveRebounds() {
		return defensiveRebounds;
	}

	public int getAssists() {
		return assists;
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

	public double getOffensiveRounds() {
		return offensiveRounds;
	}

	public double getDefensiveRounds() {
		return defensiveRounds;
	}

	public double getOffenseEfficiency() {
		return offenseEfficiency;
	}

	public double getDefenseEfficiency() {
		return defenseEfficiency;
	}

	public double getReboundsEfficiency() {
		return reboundsEfficiency;
	}

	public double getStealsEfficiency() {
		return stealsEfficiency;
	}

	public double getAssistsPercentage() {
		return assistsPercentage;
	}

	public int getPointsRival() {
		return pointsRival;
	}

	public int getFieldGoalAttempsRival() {
		return fieldGoalAttempsRival;
	}

	public int getThreePointerAttemptsRival() {
		return threePointerAttemptsRival;
	}

	public int getOffenseReboundsRival() {
		return offenseReboundsRival;
	}

	public int getDefenseReboundsRival() {
		return defenseReboundsRival;
	}

	public int getRankingInLeague() {
		return rankingInLeague;
	}

	public int getRebounds() {
		return offensiveRebounds + defensiveRebounds;
	}

	public double getFieldGoalsPercentage() {
		if (fieldGoalAttemps == 0) {
			return 0;
		} else {
			return 1.0 * fieldGoalHits / fieldGoalAttemps;
		}
	}

	public double getAverageOffenseRebounds() {
		if (numOfMatches == 0) {
			return 0;
		} else {
			return 1.0 * offensiveRebounds / numOfMatches;
		}
	}

	public double getAverageOffendRounds() {
		if (this.getOffensiveRounds() == 0) {
			return 0;
		} else {
			return this.getOffensiveRounds() / this.getNumOfMatches();
		}
	}

	public double getAverageRebounds() {
		if (this.getNumOfMatches() == 0) {
			return 0;
		} else {
			return 1.0 * this.getRebounds() / this.getNumOfMatches();
		}
	}

	public double getAverageAssists() {
		if (this.getNumOfMatches() == 0) {
			return 0;
		} else {
			return 1.0 * this.getAssists() / this.getNumOfMatches();
		}
	}

	public double getAverageTurnOver() {
		if (this.getNumOfMatches() == 0) {
			return 0;
		} else {
			return 1.0 * this.getTurnOver() / this.getNumOfMatches();
		}
	}

	public double getAverageSteals() {
		if (this.getNumOfMatches() == 0) {
			return 0;
		} else {
			return 1.0 * this.getSteals() / this.getNumOfMatches();
		}
	}

	public double getAverageBlockShots() {
		if (this.getNumOfMatches() == 0) {
			return 0;
		} else {
			return 1.0 * this.getBlockShots() / this.getNumOfMatches();
		}
	}

	public double getAverageFouls() {
		if (this.getNumOfMatches() == 0) {
			return 0;
		} else {
			return 1.0 * this.getFouls() / this.getNumOfMatches();
		}
	}

	public double getAveragePoints() {
		if (this.getNumOfMatches() == 0) {
			return 0;
		} else {
			return 1.0 * this.getPoints() / this.getNumOfMatches();
		}
	}

	public double getAverageDefenseRebounds() {
		if (numOfMatches == 0) {
			return 0;
		} else {
			return 1.0 * defensiveRebounds / numOfMatches;
		}
	}

	public double getWinningPercentage() {
		if (numOfMatches == 0) {
			return 0;
		} else {
			return 1.0 * numOfVictory / numOfMatches;
		}
	}

	public double getThreePointersPercentage() {
		if (threePointerAttempts == 0) {
			return 0;
		} else {
			return 1.0 * threePointerHits / threePointerAttempts;
		}
	}

	public double getFreeThrowsPercentage() {
		if (freeThrowAttempts == 0) {
			return 0;
		} else {
			return 1.0 * freeThrowHits / freeThrowAttempts;
		}
	}

	// 得到近十场的输赢情况,lose = 0, win =1
	public double[] getLatestWinOrLose() {
		return null;
	}

	// 得到近十场的战绩,暂定返回比分(格式:"100-101")，可能需要更多
	public String[] getLatestRecord() {
		return null;
	}

	// 得到近十场的攻防比(得分/失分)
	public double[] getLatestOffendThanDefend() {
		return null;
	}

	// 得到近十场的得分(每一场的每百回合得分)
	public double[] getLatestOffend() {
		return null;
	}

	// 得到近十场的失分(每一场的每百回合失分)
	public double[] getLatestDefend() {
		return null;
	}

	// 得到近十场的节奏(每一场的回合数)
	public double[] getLatestTempo() {
		return null;
	}
}
