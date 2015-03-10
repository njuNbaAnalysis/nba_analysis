package logic.teams;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Team {
	private String name;                        //球队名
	private BufferedImage logo;					//队标
	private ArrayList<String> playerList;       //球员列表
	private int numOfmatches;                   //比赛常数
	private int shooting_times;                 //投篮出手次数
	
	private int Three_shots;                    //三分出手次数
	private int Three_hit_points;               //三分命中次数
	private double threePointersPercentage;     //三分命中率
	
	private int freeThrows_shots;               //罚球出手次数
	private int freeThrowsHit_points;           //罚球命中次数
	private double freeThrowsPercentage;        //罚球命中率
	
	private int rebounds;                       //篮板数
	private int Offensive_rebounds;             //进攻篮板数
	private int Defensive_rebounds;             //防守篮板数
	
	private int assists;                        //助攻数
	private int Steals;                         //抢断数
	private int blockShots;						//总盖帽数
	private int turnOver;						//总失误数
	private int fouls;							//总犯规数
	private int points;							//总比赛得分数
	
	private double Winning_percentage;       	//胜率
	private int Round_attack;                   //进攻回合
	private double attacking_efficiency;        //进攻效率
	private double Defensive_efficiency; 		//防守效率
	private double Bank_efficiency;             //篮板效率
	private double Steal_efficiency;			//抢断效率
	private double Secondary;             		//助攻率 

}
