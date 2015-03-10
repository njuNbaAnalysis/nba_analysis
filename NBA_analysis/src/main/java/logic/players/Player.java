package logic.players;

import java.util.Date;

public class Player {
	private String name;
	private int number;    //number of his clothes
	private char position;
	private int[] height;   //int[0]: foot,int[1]: inch
	private int weight;
	private Date birthday; //year,month,day
	private int age;
	private int experience;    //time of playing
	private String school;
	
	
	//not raw data
	private String team;   //current team
	private int gamePlayed;//出场次数 
	private int gameStarted;//首发次数
	private int rebounds;//总篮板数
	private int assists;//总助攻数 
	private int minutes;//总上场分钟数
	
	
	private double fieldGoalsPercentage;//总投篮命中率
	private double threePointersPercentage;//三分命中率
	private double freeThrowsPercentage;//罚球命中率
	private double trueShootingPercentage;//真实投篮命中率
	
	
	private int offenseRebounds;//总进攻篮板
	private int defenseRebounds;//总防守篮板
	private int steals;//总罚球数
	private int blockShots;//总盖帽数
	private int turnOver;//总失误数
	private int fouls;//总犯规数
	private int points;//总得分数
	
	private double efficiency;//效率值
	private double gmsc;//gmsc效率值
	
	

	
    public Player(String name, int number, char position, int[] height, int weight, Date birthday, int age,
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
    public String getName() {
        return name;
    }
    public int getNumber() {
        return number;
    }
    public char getPosition() {
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
    
    
	
	
}
