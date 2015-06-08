package vo;

import java.io.Serializable;

/**用于记录某个球员某一个赛季的历史各项数据（包括基础数据和高阶数据）*/
public class playerItem implements Serializable{
	/**该球员的唯一标识符Pid*/
	private String Pid;
	/**该记录是否为季后赛*/
	private boolean isplayoff;
	/**该记录的赛季数，例如13-14*/
	private String season;
	/**该球员该赛季所效力的球队*/
	private String team;
	/**该球员该赛季上场次数*/
	private int gameplayed;
	/**该球员该赛季首发次数*/
	private int gamestarted;
	/**该球员该赛季平均上场时间*/
	private double averageTime;
	/**该球员该赛季投篮命中率*/
	private double fieldGoalsPercent;
	/**该球员该赛季平均投篮命中数*/
	private double averagefieldGoalsHit;
	/**该球员该赛季平均投篮出手数*/
	private double averagefieldGoalsAttempt;
	/**该球员该赛季三分命中率*/
	private double threePointPercent;
	/**该球员该赛季平均三分命中数*/
	private double averagethreePointHit;
	/**该球员该赛季平均三分出手数*/
	private double averagethreePointAttempt;
	/**该球员该赛季罚球命中率*/
	private double freethrowPercent;
	/**该球员该赛季平均罚球命中数*/
	private double averagefreethrowHit;
	/**该球员该赛季平均罚球出手数*/
	private double averagefreethrowAttempt;
	/**该球员该赛季平均篮板数*/	
	private double averagerebounds;
	/**该球员该赛季平均进攻篮板数*/	
	private double averageoffenseRebounds;
	/**该球员该赛季平均防守篮板数*/	
	private double averagedefenseRebounds;
	/**该球员该赛季平均助攻数*/	
	private double averageassisit;
	/**该球员该赛季平均抢断数*/	
	private double averagesteals;
	/**该球员该赛季平均盖帽数*/	
	private double averageblockShots;
	/**该球员该赛季平均失误数*/	
	private double averageturnOver;
	/**该球员该赛季平均犯规数*/	
	private double averagefouls;
	/**该球员该赛季平均得分*/	
	private double averagepoints;
	/**该球员该赛季总胜利场数*/	
	private int wins;
	/**该球员该赛季总失败场数*/	
	private int defeats;
	/**该球员该赛季总投篮命中数*/	
	private int fieldGoalsHit;
	/**该球员该赛季总投篮出手数*/	
	private int fieldGoalsAttempt;
	/**该球员该赛季总三分命中数*/	
	private int threethrowHit;
	/**该球员该赛季总三分出手数*/
	private int threethrowAttempt;
	/**该球员该赛季总罚球命中数*/
	private int freethrowHit;
	/**该球员该赛季总罚球出手数*/
	private int freethrowAttempt;
	/**该球员该赛季总篮板数数*/
	private int rebounds;
	/**该球员该赛季总进攻篮板数数*/
	private int offenseRebounds;
	/**该球员该赛季总防守篮板数数*/
	private int defenseRebounds;
	/**该球员该赛季总上场时间*/
	private double time;
	/**该球员该赛季总助攻数”*/
	private int assists;
	/**该球员该赛季总抢断数”*/
	private int steals;
	/**该球员该赛季总盖帽数”*/
	private int blockShots;
	/**该球员该赛季总失误数”*/
	private int turnOver;
	/**该球员该赛季总犯规数”*/
	private int fouls;
	/**该球员该赛季总得分数”*/
	private int points;
	/**该球员该赛季篮板率”*/
	private double reboundsPercentage;
	/**该球员该赛季进攻篮板率”*/
	private double offenseReboundsPercentage;
	/**该球员该赛季防守篮板率”*/
	private double defenseReboundsPercentage;
	/**该球员该赛季助攻率”*/
	private double assistsPercentage;
	/**该球员该赛季抢断率”*/
	private double stealsPercentage;
	/**该球员该赛季盖帽率率”*/
	private double blockShotsPercentage;
	/**该球员该赛季失误率”*/
	private double turnOverPercentage;
	/**该球员该赛季使用率”*/
	private double usage;
	/**该球员该赛季进攻效率”*/
	private double offenseEfficiency;
	/**该球员该赛季防守效率”*/
	private double defenseEfficiency;
	/**该球员该赛季WS值：（团队胜利贡献，简单说就是该球员在球队胜利中所占的股份 ）”*/
	private double WS;
	/**
	 * 进攻端的WS值 = 该名球员的边际进攻贡献 / （0.32*联盟平均每场球队得分），而球员的边际进攻贡献 = 球员赛季总得分 - 0.92*联盟平均每次进攻得分*球员总进攻次数
	 * */
	private double offenseWS;
	/**
	 * 防守端的WS值 = 该名球员的边际防守贡献 / （0.32*联盟平均每场球队得分），而球员边际防守贡献 = 球员出场时间占全队比例*球队防守的回合数*（1.08*联盟平均每次持球得分 - 球员每一次防守让对方所得的分数 
	 * */
	private double defenseWS;
	/**该球员该赛季平均效率值*/
	private double PER;
	/**该球员该赛季总扣篮数*/
	private int dunk;			//扣篮数
	/**该球员该赛季提升率*/
	private double riseRate;	//	提升率
	/**该球员该赛季总被帽数*/
	private int dunked;			//被帽数
	/**该球员该赛季平均出手距离*/
	private double distance;
	/**该球员该赛季篮下命中率*/
	private double Baskethitrate;
	/**该球员该赛季平均篮下命中数*/
	private double averageBaskethit;
	/**该球员该赛季平均篮下出手数*/
	private double averageBasketattempt;
	/**该球员该赛季篮下出手占比*/
	private double BasketPercent;
	/**该球员该赛季短距两分命中率*/
	private double Shorthitrate;
	/**该球员该赛季平均短距两分命中数*/;
	private double averageShorthit;
	/**该球员该赛季平均短距两分出手数*/;
	private double averageShortattempt;
	/**该球员该赛季短距两分占比*/;
	private double ShortPercent;
	/**该球员该赛季中距两分命中率*/
	private double Middlehitrate;
	/**该球员该赛季平均中距两分命中数*/;
	private double averageMiddlehit;
	/**该球员该赛季平均中距两分出手数*/;
	private double averageMiddleattempt;
	/**该球员该赛季中距两分占比*/;
	private double MiddlePercent;
	/**该球员该赛季长距两分命中率*/
	private double Longhitrate;
	/**该球员该赛季平均长距两分命中数*/;
	private double averageLonghit;
	/**该球员该赛季平均中距两分出手数*/;
	private double averageLongattempt;
	/**该球员该赛季长距两分占比*/;
	private double LongPercent;
	/**该球员该赛季真实命中率*/;
	private double trueshootingPercent;
	/**该球员该赛季投篮效率值*/;
	private double shootingEfficiency;

	public String getPid() {
		return Pid;
	}

	public void setPid(String pid) {
		Pid = pid;
	}

	public boolean isIsplayoff() {
		return isplayoff;
	}

	public void setIsplayoff(boolean isplayoff) {
		this.isplayoff = isplayoff;
	}

	public String getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = season;
	}

	public String getTeam() {
		return team;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public int getGameplayed() {
		return gameplayed;
	}

	public void setGameplayed(int gameplayed) {
		this.gameplayed = gameplayed;
	}

	public int getGamestarted() {
		return gamestarted;
	}

	public void setGamestarted(int gamestarted) {
		this.gamestarted = gamestarted;
	}

	public double getAverageTime() {
		return averageTime;
	}

	public void setAverageTime(double averageTime) {
		this.averageTime = averageTime;
	}

	public double getFieldGoalsPercent() {
		return fieldGoalsPercent;
	}

	public void setFieldGoalsPercent(double fieldGoalsPercent) {
		this.fieldGoalsPercent = fieldGoalsPercent;
	}

	public double getAveragefieldGoalsHit() {
		return averagefieldGoalsHit;
	}

	public void setAveragefieldGoalsHit(double averagefieldGoalsHit) {
		this.averagefieldGoalsHit = averagefieldGoalsHit;
	}

	public double getAveragefieldGoalsAttempt() {
		return averagefieldGoalsAttempt;
	}

	public void setAveragefieldGoalsAttempt(double averagefieldGoalsAttempt) {
		this.averagefieldGoalsAttempt = averagefieldGoalsAttempt;
	}

	public double getThreePointPercent() {
		return threePointPercent;
	}

	public void setThreePointPercent(double threePointPercent) {
		this.threePointPercent = threePointPercent;
	}

	public double getAveragethreePointHit() {
		return averagethreePointHit;
	}

	public void setAveragethreePointHit(double averagethreePointHit) {
		this.averagethreePointHit = averagethreePointHit;
	}

	public double getAveragethreePointAttempt() {
		return averagethreePointAttempt;
	}

	public void setAveragethreePointAttempt(double averagethreePointAttempt) {
		this.averagethreePointAttempt = averagethreePointAttempt;
	}

	public double getFreethrowPercent() {
		return freethrowPercent;
	}

	public void setFreethrowPercent(double freethrowPercent) {
		this.freethrowPercent = freethrowPercent;
	}

	public double getAveragefreethrowHit() {
		return averagefreethrowHit;
	}

	public void setAveragefreethrowHit(double averagefreethrowHit) {
		this.averagefreethrowHit = averagefreethrowHit;
	}

	public double getAveragefreethrowAttempt() {
		return averagefreethrowAttempt;
	}

	public void setAveragefreethrowAttempt(double averagefreethrowAttempt) {
		this.averagefreethrowAttempt = averagefreethrowAttempt;
	}

	public double getAveragerebounds() {
		return averagerebounds;
	}

	public void setAveragerebounds(double averagerebounds) {
		this.averagerebounds = averagerebounds;
	}

	public double getAverageoffenseRebounds() {
		return averageoffenseRebounds;
	}

	public void setAverageoffenseRebounds(double averageoffenseRebounds) {
		this.averageoffenseRebounds = averageoffenseRebounds;
	}

	public double getAveragedefenseRebounds() {
		return averagedefenseRebounds;
	}

	public void setAveragedefenseRebounds(double averagedefenseRebounds) {
		this.averagedefenseRebounds = averagedefenseRebounds;
	}

	public double getAverageassisit() {
		return averageassisit;
	}

	public void setAverageassisit(double averageassisit) {
		this.averageassisit = averageassisit;
	}

	public double getAveragesteals() {
		return averagesteals;
	}

	public void setAveragesteals(double averagesteals) {
		this.averagesteals = averagesteals;
	}

	public double getAverageblockShots() {
		return averageblockShots;
	}

	public void setAverageblockShots(double averageblockShots) {
		this.averageblockShots = averageblockShots;
	}

	public double getAverageturnOver() {
		return averageturnOver;
	}

	public void setAverageturnOver(double averageturnOver) {
		this.averageturnOver = averageturnOver;
	}

	public double getAveragefouls() {
		return averagefouls;
	}

	public void setAveragefouls(double averagefouls) {
		this.averagefouls = averagefouls;
	}

	public double getAveragepoints() {
		return averagepoints;
	}

	public void setAveragepoints(double averagepoints) {
		this.averagepoints = averagepoints;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getDefeats() {
		return defeats;
	}

	public void setDefeats(int defeats) {
		this.defeats = defeats;
	}

	public int getFieldGoalsHit() {
		return fieldGoalsHit;
	}

	public void setFieldGoalsHit(int fieldGoalsHit) {
		this.fieldGoalsHit = fieldGoalsHit;
	}

	public int getFieldGoalsAttempt() {
		return fieldGoalsAttempt;
	}

	public void setFieldGoalsAttempt(int fieldGoalsAttempt) {
		this.fieldGoalsAttempt = fieldGoalsAttempt;
	}

	public int getThreethrowHit() {
		return threethrowHit;
	}

	public void setThreethrowHit(int threethrowHit) {
		this.threethrowHit = threethrowHit;
	}

	public int getThreethrowAttempt() {
		return threethrowAttempt;
	}

	public void setThreethrowAttempt(int threethrowAttempt) {
		this.threethrowAttempt = threethrowAttempt;
	}

	public int getFreethrowHit() {
		return freethrowHit;
	}

	public void setFreethrowHit(int freethrowHit) {
		this.freethrowHit = freethrowHit;
	}

	public int getFreethrowAttempt() {
		return freethrowAttempt;
	}

	public void setFreethrowAttempt(int freethrowAttempt) {
		this.freethrowAttempt = freethrowAttempt;
	}

	public int getRebounds() {
		return rebounds;
	}

	public void setRebounds(int rebounds) {
		this.rebounds = rebounds;
	}

	public int getOffenseRebounds() {
		return offenseRebounds;
	}

	public void setOffenseRebounds(int offenseRebounds) {
		this.offenseRebounds = offenseRebounds;
	}

	public int getDefenseRebounds() {
		return defenseRebounds;
	}

	public void setDefenseRebounds(int defenseRebounds) {
		this.defenseRebounds = defenseRebounds;
	}

	public double getTime() {
		return time;
	}

	public void setTime(double time) {
		this.time = time;
	}

	public int getAssists() {
		return assists;
	}

	public void setAssists(int assists) {
		this.assists = assists;
	}

	public int getSteals() {
		return steals;
	}

	public void setSteals(int steals) {
		this.steals = steals;
	}

	public int getBlockShots() {
		return blockShots;
	}

	public void setBlockShots(int blockShots) {
		this.blockShots = blockShots;
	}

	public int getTurnOver() {
		return turnOver;
	}

	public void setTurnOver(int turnOver) {
		this.turnOver = turnOver;
	}

	public int getFouls() {
		return fouls;
	}

	public void setFouls(int fouls) {
		this.fouls = fouls;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public double getReboundsPercentage() {
		return reboundsPercentage;
	}

	public void setReboundsPercentage(double reboundsPercentage) {
		this.reboundsPercentage = reboundsPercentage;
	}

	public double getOffenseReboundsPercentage() {
		return offenseReboundsPercentage;
	}

	public void setOffenseReboundsPercentage(double offenseReboundsPercentage) {
		this.offenseReboundsPercentage = offenseReboundsPercentage;
	}

	public double getDefenseReboundsPercentage() {
		return defenseReboundsPercentage;
	}

	public void setDefenseReboundsPercentage(double defenseReboundsPercentage) {
		this.defenseReboundsPercentage = defenseReboundsPercentage;
	}

	public double getAssistsPercentage() {
		return assistsPercentage;
	}

	public void setAssistsPercentage(double assistsPercentage) {
		this.assistsPercentage = assistsPercentage;
	}

	public double getStealsPercentage() {
		return stealsPercentage;
	}

	public void setStealsPercentage(double stealsPercentage) {
		this.stealsPercentage = stealsPercentage;
	}

	public double getBlockShotsPercentage() {
		return blockShotsPercentage;
	}

	public void setBlockShotsPercentage(double blockShotsPercentage) {
		this.blockShotsPercentage = blockShotsPercentage;
	}

	public double getTurnOverPercentage() {
		return turnOverPercentage;
	}

	public void setTurnOverPercentage(double turnOverPercentage) {
		this.turnOverPercentage = turnOverPercentage;
	}

	public double getUsage() {
		return usage;
	}

	public void setUsage(double usage) {
		this.usage = usage;
	}

	public double getOffenseEfficiency() {
		return offenseEfficiency;
	}

	public void setOffenseEfficiency(double offenseEfficiency) {
		this.offenseEfficiency = offenseEfficiency;
	}

	public double getDefenseEfficiency() {
		return defenseEfficiency;
	}

	public void setDefenseEfficiency(double defenseEfficiency) {
		this.defenseEfficiency = defenseEfficiency;
	}

	public double getWS() {
		return WS;
	}

	public void setWS(double wS) {
		WS = wS;
	}

	public double getOffenseWS() {
		return offenseWS;
	}

	public void setOffenseWS(double offenseWS) {
		this.offenseWS = offenseWS;
	}

	public double getDefenseWS() {
		return defenseWS;
	}

	public void setDefenseWS(double defenseWS) {
		this.defenseWS = defenseWS;
	}

	public double getPER() {
		return PER;
	}

	public void setPER(double pER) {
		PER = pER;
	}

	public int getDunk() {
		return dunk;
	}

	public void setDunk(int dunk) {
		this.dunk = dunk;
	}

	public double getRiseRate() {
		return riseRate;
	}

	public void setRiseRate(double riseRate) {
		this.riseRate = riseRate;
	}

	public int getDunked() {
		return dunked;
	}

	public void setDunked(int dunked) {
		this.dunked = dunked;
	}

	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public double getBaskethitrate() {
		return Baskethitrate;
	}

	public void setBaskethitrate(double baskethitrate) {
		Baskethitrate = baskethitrate;
	}

	public double getAverageBaskethit() {
		return averageBaskethit;
	}

	public void setAverageBaskethit(double averageBaskethit) {
		this.averageBaskethit = averageBaskethit;
	}

	public double getAverageBasketattempt() {
		return averageBasketattempt;
	}

	public void setAverageBasketattempt(double averageBasketattempt) {
		this.averageBasketattempt = averageBasketattempt;
	}

	public double getBasketPercent() {
		return BasketPercent;
	}

	public void setBasketPercent(double basketPercent) {
		BasketPercent = basketPercent;
	}

	public double getShorthitrate() {
		return Shorthitrate;
	}

	public void setShorthitrate(double shorthitrate) {
		Shorthitrate = shorthitrate;
	}

	public double getAverageShorthit() {
		return averageShorthit;
	}

	public void setAverageShorthit(double averageShorthit) {
		this.averageShorthit = averageShorthit;
	}

	public double getAverageShortattempt() {
		return averageShortattempt;
	}

	public void setAverageShortattempt(double averageShortattempt) {
		this.averageShortattempt = averageShortattempt;
	}

	public double getShortPercent() {
		return ShortPercent;
	}

	public void setShortPercent(double shortPercent) {
		ShortPercent = shortPercent;
	}

	public double getMiddlehitrate() {
		return Middlehitrate;
	}

	public void setMiddlehitrate(double middlehitrate) {
		Middlehitrate = middlehitrate;
	}

	public double getAverageMiddlehit() {
		return averageMiddlehit;
	}

	public void setAverageMiddlehit(double averageMiddlehit) {
		this.averageMiddlehit = averageMiddlehit;
	}

	public double getAverageMiddleattempt() {
		return averageMiddleattempt;
	}

	public void setAverageMiddleattempt(double averageMiddleattempt) {
		this.averageMiddleattempt = averageMiddleattempt;
	}

	public double getMiddlePercent() {
		return MiddlePercent;
	}

	public void setMiddlePercent(double middlePercent) {
		MiddlePercent = middlePercent;
	}

	public double getLonghitrate() {
		return Longhitrate;
	}

	public void setLonghitrate(double longhitrate) {
		Longhitrate = longhitrate;
	}

	public double getAverageLonghit() {
		return averageLonghit;
	}

	public void setAverageLonghit(double averageLonghit) {
		this.averageLonghit = averageLonghit;
	}

	public double getAverageLongattempt() {
		return averageLongattempt;
	}

	public void setAverageLongattempt(double averageLongattempt) {
		this.averageLongattempt = averageLongattempt;
	}

	public double getLongPercent() {
		return LongPercent;
	}

	public void setLongPercent(double longPercent) {
		LongPercent = longPercent;
	}

	public double getTrueshootingPercent() {
		return trueshootingPercent;
	}

	public void setTrueshootingPercent(double trueshootingPercent) {
		this.trueshootingPercent = trueshootingPercent;
	}

	public double getShootingEfficiency() {
		return shootingEfficiency;
	}

	public void setShootingEfficiency(double shootingEfficiency) {
		this.shootingEfficiency = shootingEfficiency;
	}

	/**
	 * 对该类的所有属性进行构造
	 * */
	public playerItem(String Pid, boolean isplayoff, String season,
			String team, int gameplayed, int gamestarted, double averageTime,
			double fieldGoalsPercent, double averagefieldGoalsHit,
			double averagefieldGoalsAttempt, double threePointPercent,
			double averagethreePointHit, double averagethreePointAttempt,
			double freethrowPercent, double averagefreethrowHit,
			double averagefreethrowAttempt, double averagerebounds,
			double averageoffenseRebounds, double averagedefenseRebounds,
			double averageassisit, double averagesteals,
			double averageblockShots, double averageturnOver,
			double averagefouls, double averagepoints, int wins, int defeats,
			int fieldGoalsHit, int fieldGoalsAttempt, int threethrowHit,
			int threethrowAttempt, int freethrowHit, int freethrowAttempt,
			int rebounds, int offenseRebounds, int defenseRebounds,
			double time, int assists, int steals, int blockShots, int turnOver,
			int fouls, int points, double reboundsPercentage,
			double offenseReboundsPercentage, double defenseReboundsPercentage,
			double assistsPercentage, double stealsPercentage,
			double blockShotsPercentage, double turnOverPercentage,
			double usage, double offenseEfficiency, double defenseEfficiency,
			double WS, double offenseWS, double defenseWS, double PER,
			int dunk, double riseRate, int dunked, double distance,
			double Baskethitrate, double averageBaskethit,
			double averageBasketattempt, double BasketPercent,
			double Shorthitrate, double averageShorthit,
			double averageShortattempt, double ShortPercent,
			double Middlehitrate, double averageMiddlehit,
			double averageMiddleattempt, double MiddlePercent,
			double Longhitrate, double averageLonghit,
			double averageLongattempt, double LongPercent,
			double trueshootingPercent, double shootingEfficiency) {

		this.Pid = Pid;
		this.isplayoff = isplayoff;
		this.season = season;
		this.team = team;
		this.gameplayed = gameplayed;
		this.gamestarted = gamestarted;
		this.averageTime = averageTime;
		this.fieldGoalsPercent = fieldGoalsPercent;
		this.averagefieldGoalsHit = averagefieldGoalsHit;
		this.averagefieldGoalsAttempt = averagefieldGoalsAttempt;
		this.threePointPercent = threePointPercent;
		this.averagethreePointHit = averagethreePointHit;
		this.averagethreePointAttempt = averagethreePointAttempt;
		this.freethrowPercent = freethrowPercent;
		this.averagefreethrowHit = averagefreethrowHit;
		this.averagefreethrowAttempt = averagefreethrowAttempt;
		this.averagerebounds = averagerebounds;
		this.averageoffenseRebounds = averageoffenseRebounds;
		this.averagedefenseRebounds = averagedefenseRebounds;
		this.averageassisit = averageassisit;
		this.averageblockShots = averageblockShots;
		this.averageturnOver = averageturnOver;
		this.averagefouls = averagefouls;
		this.averagepoints = averagepoints;
		this.wins = wins;
		this.defeats = defeats;
		this.fieldGoalsHit = fieldGoalsHit;
		this.fieldGoalsAttempt = fieldGoalsAttempt;
		this.threethrowHit = threethrowHit;
		this.threethrowAttempt = threethrowAttempt;
		this.freethrowHit = freethrowHit;
		this.freethrowAttempt = freethrowAttempt;
		this.rebounds = rebounds;
		this.offenseRebounds = offenseRebounds;
		this.defenseRebounds = defenseRebounds;
		this.time = time;
		this.assists = assists;
		this.steals = steals;
		this.blockShots = blockShots;
		this.turnOver = turnOver;
		this.fouls = fouls;
		this.points = points;
		this.reboundsPercentage = reboundsPercentage;
		this.offenseReboundsPercentage = offenseReboundsPercentage;
		this.defenseReboundsPercentage = defenseReboundsPercentage;
		this.assistsPercentage = assistsPercentage;
		this.stealsPercentage = stealsPercentage;
		this.blockShotsPercentage = blockShotsPercentage;
		this.turnOverPercentage = turnOverPercentage;
		this.usage = usage;
		this.offenseEfficiency = offenseEfficiency;
		this.defenseEfficiency = defenseEfficiency;
		this.WS = WS;
		this.offenseWS = offenseWS;
		this.defenseWS = defenseWS;
		this.PER = PER;
		this.dunk = dunk;
		this.riseRate = riseRate;
		this.dunked = dunked;
		this.distance = distance;
		this.Baskethitrate = Baskethitrate;
		this.averageBaskethit = averageBaskethit;
		this.averageBasketattempt = averageBasketattempt;
		this.BasketPercent = BasketPercent;
		this.Shorthitrate = Shorthitrate;
		this.averageShorthit = averageShorthit;
		this.averageShortattempt = averageShortattempt;
		this.ShortPercent = ShortPercent;
		this.Middlehitrate = Middlehitrate;
		this.averageMiddlehit = averageMiddlehit;
		this.averageMiddleattempt = averageMiddleattempt;
		this.MiddlePercent = MiddlePercent;
		this.Longhitrate = Longhitrate;
		this.averageLonghit = averageLonghit;
		this.averageLongattempt = averageLongattempt;
		this.LongPercent = LongPercent;
		this.trueshootingPercent = trueshootingPercent;
		this.shootingEfficiency = shootingEfficiency;
	}

}
