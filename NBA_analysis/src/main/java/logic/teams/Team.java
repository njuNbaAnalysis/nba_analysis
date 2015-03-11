package logic.teams;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Team {
	private String name;                        //球队名
	private BufferedImage logo;					//队标
	private ArrayList<String> playerList;       //球员列表
	private int numOfMatches;                   //比赛场数
	private int fieldGoalAttemps;                 //投篮出手次数
	
	private int threePointerAttempts;                    //三分出手次数
	private int threePointerHits;               //三分命中次数
	private double threePointersPercentage;     //三分命中率
	
	private int freeThrows;               //罚球出手次数
	private int freeThrowHits;           //罚球命中次数
	private double freeThrowsPercentage;        //罚球命中率
	
	private int rebounds;                       //篮板数
	private int offensiveRebounds;             //进攻篮板数
	private int defensiveRebounds;             //防守篮板数
	
	private int assists;                        //助攻数
	private int Steals;                         //抢断数
	private int blockShots;						//总盖帽数
	private int turnOver;						//总失误数
	private int fouls;							//总犯规数
	private int points;							//总比赛得分数
	
	private double winningPercentage;       	//胜率
	private int offensiveRounds;                   //进攻回合
	private double offenseEfficiency;        //进攻效率
	private double defenseEfficiency; 		//防守效率
	private double reboundsEfficiency;             //篮板效率
	private double stealsEfficiency;			//抢断效率
	private double assistsPercentage;             		//助攻率 

}
