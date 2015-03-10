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
	private int gamePlayed;//测试
	private int gameStarted;//发大水
	private int rebounds;
	private int assists;
	private int minutes;
	
	private double fieldGoalsPercentage;
	private double threePointersPercentage;
	private double freeThrowsPercentage;
	
	private int offenseRebounds;
	private int defenseRebounds;
	private int steals;
	private int blockShots;
	private int turnOver;
	private int fouls;
	private int points;
	
	private int efficiency;
	private int gmsc;
	
	
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
