package logic.players;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.util.Date;

public class Player {
	private String name;
	private int number;    //球衣号
	private String position;   //G,G-F 类似格式
	private int[] height;   //int[0]: foot,int[1]: inch
	private int weight;
	private Date birthday; //year,month,day
	private int age;
	private int experience;    //打了几年球，取值可能为R，如果为R则存储0
	private String school;
	private Image portrait;
	private Image action;
	
	
	public void setTeam(String team) {
		this.team = team;
	}


	public void setGamePlayed(int gamePlayed) {
		this.gamePlayed = gamePlayed;
	}

	public void setGameStarted(int gameStarted) {
		this.gameStarted = gameStarted;
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



	//not raw data
	//以***结尾的说明此项数据不需要存储
	private String team;   //current team
	private int gamePlayed;//出场次数 
	private int gameStarted;//首发次数
	
	//可场均
	private int rebounds;//总篮板数
	private int assists;//总助攻数 
	private int minutes;//总上场分钟数
    private int offenseRebounds;//总进攻篮板
    private int defenseRebounds;//总防守篮板
    private int steals;//总抢断数
    private int blockShots;//总盖帽数
    private int turnOver;//总失误数
    private int fouls;//总犯规数
    private int points;//总得分数
	
	//百分比率
	private double fieldGoalsPercentage;//总投篮命中率
	private double threePointersPercentage;//三分命中率
	private double freeThrowsPercentage;//罚球命中率
	private double trueShootingPercentage;//真实投篮命中率
	private double reboundsPercentage;//篮板率
	private double offenseReboundsPercentage;//进攻篮板率
	private double defenseReboundsPercentage;//防守篮板率
	private double assistsPercentage;//助攻率
	private double stealsPercentage;//抢断率
	private double blockShotsPercentage;//盖帽率
	private double turnOverPercentage;//失误率
	private double usage;//使用率

	//效率值
	private double efficiency;//效率值
	private double gmsc;//gmsc效率值
	private double shootingEfficiency;//投篮效率
	
	//tempData 不用做界面显示，在计算中用到，可能用作以后的界面显示
	private int fieldGoalHits;         //投篮命中
	private int fieldGoalAttempts;     //投篮出手
	private int threePointerHits;      //三分命中
	private int threePointerAttempts;  //三分出手
	private int freeThrowHits;         //罚球命中
	private int freeThrowAttempts;     //罚球出手
	
	public void init(){
		fieldGoalsPercentage = fieldGoalHits/fieldGoalAttempts;
		threePointersPercentage = threePointerHits/threePointerAttempts;
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

	public Player(String name, int number, String position, int[] height, int weight, Date birthday, int age,
            int experience, String school) {
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
    
    //raw data
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
    
    
    //not raw data
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
        return efficiency;
    }
    public double getGmsc() {
        return gmsc;
    }
    public double getShootingEfficiency() {
        return shootingEfficiency;
    }

    
    public String toString(){
        return name + " " + 
                number + " " + 
                position + " " +
                height[0] + "-" +
                height[1] + " " +
                weight + " " +
                birthday.getYear() + " " +
                birthday.getMonth() + " " +
                birthday.getDate() + " " +
                age + " " +
                experience + " " +
                school + " ";
               
    }




	
}
