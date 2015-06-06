package vo;

public class PlayerItemvo {
	private String Pid;
	private boolean isplayoff;
	private String season;
	private String team;
	private int gameplayed;
	private int gamestarted;
	private double averageTime;
	private double fieldGoalsPercent;
	private double averagefieldGoalsHit;
	private double averagefieldGoalsAttempt;
	private double threePointPercent;
	private double averagethreePointHit;
	private double averagethreePointAttempt;
	private double freethrowPercent;
	private double averagefreethrowHit;
	private double averagefreethrowAttempt;
	private double averagerebounds;
	private double averageoffenseRebounds;
	private double averagedefenseRebounds;
	private double averageassisit;
	private double averagesteals;
	private double averageblockShots;
	private double averageturnOver;
	private double averagefouls;
	private double averagepoints;
	private int wins;
	private int defeats;
	private int fieldGoalsHit;
	private int fieldGoalsAttempt;
	private int threethrowHit;
	private int threethrowAttempt;
	private int freethrowHit;
	private int freethrowAttempt;
	private int rebounds;
	private int offenseRebounds;
	private int defenseRebounds;
	private double time;
	private int assists;
	private int steals;
	private int blockShots;
	private int turnOver;
	private int fouls;
	private int points;
	private double reboundsPercentage;
	private double offenseReboundsPercentage;
	private double defenseReboundsPercentage;
	private double assistsPercentage;
	private double stealsPercentage;
	private double blockShotsPercentage;
	private double turnOverPercentage;
	private double usage;
	private double offenseEfficiency;
	private double defenseEfficiency;
	private double WS;
	private double offenseWS;
	private double defenseWS;
	private double PER;
	private int dunk;			//扣篮数
	private double riseRate;	//	提升率
	private int dunked;			//被帽数
	private double distance;
	private double Baskethitrate;
	private double averageBaskethit;
	private double averageBasketattempt;
	private double BasketPercent;
	private double Shorthitrate;
	private double averageShorthit;
	private double averageShortattempt;
	private double ShortPercent;
	private double Middlehitrate;
	private double averageMiddlehit;
	private double averageMiddleattempt;
	private double MiddlePercent;
	private double Longhitrate;
	private double averageLonghit;
	private double averageLongattempt;
	private double LongPercent;
	private double trueshootingPercent;
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

	public PlayerItemvo(String Pid, boolean isplayoff, String season,
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
