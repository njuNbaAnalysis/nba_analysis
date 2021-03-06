package logic.matches;

import java.util.ArrayList;
import java.util.Date;

import data.matches.MatchMistake;

public class Match {
	private String date;    //例如13-14_2014-01-01 :前面表示13-14赛季，后面表示日期 
	private String[] teams;// 对阵队伍 2容量的teams数组,这里面存的是缩写！！！
	private int[] points;// 比分 2容量
	private ArrayList<int[]> pointsList;// 每一节的比分，可能会有加时赛

	private ArrayList<RecordOfPlayer> firstRecordList; // 主场记录
	private ArrayList<RecordOfPlayer> secondRecordList; // 客场记录

	private ArrayList<MatchMistake> ListOfMistake; // 记录数据错误；如果没有错误，则为null

	public Match(String date, String[] teams, int[] points,
			ArrayList<int[]> pointsList,
			ArrayList<RecordOfPlayer> firstRecordList,
			ArrayList<RecordOfPlayer> secondRecordList,
			ArrayList<MatchMistake> ListOfMistake) {
		super();
		this.date = date;
		this.teams = teams;
		this.points = points;
		this.pointsList = pointsList;
		this.firstRecordList = firstRecordList;
		this.secondRecordList = secondRecordList;
		this.ListOfMistake = ListOfMistake;
	}

	public String getDate() {
		return date;
	}

	public String[] getTeams() {
		return teams;
	}

	public int[] getPoints() {
		return points;
	}

	public ArrayList<int[]> getPointsList() {
		return pointsList;
	}

	public ArrayList<RecordOfPlayer> getFirstRecordList() {
		return firstRecordList;
	}

	public ArrayList<RecordOfPlayer> getSecondRecordList() {
		return secondRecordList;
	}
	
	public ArrayList<MatchMistake> getMatchMistakeList(){
		return ListOfMistake;
	}
	
	
	
	//即时生成，得分相同比篮板 
	public KingsOfMatch[] getKingsOfMatch(){
	    KingsOfMatch[] kings = new KingsOfMatch[2];
	    kings[0] = getKings(this.firstRecordList);
	    kings[1] = getKings(this.secondRecordList);
	    
	    return kings;
	}
	
	private KingsOfMatch getKings(ArrayList<RecordOfPlayer> list){
	    KingsOfMatch king = new KingsOfMatch();
	    
	    RecordOfPlayer record = list.get(0);
	    for(RecordOfPlayer token:list){
	        if(token.getPoints() > record.getPoints()){
	            record = token;
	        }
	        else if(token.getPoints() == record.getPoints()
	                && token.getRebounds() > record.getRebounds()){
	            record = token;
	        }
	    }
	    
	    king.setNameOfPointsKing(record.getPlayerName());
	    king.setPoints(record.getPoints());
	    
	    record = list.get(0);
        for(RecordOfPlayer token:list){
            if(token.getRebounds() > record.getRebounds()){
                record = token;
            }
        }
        king.setNameOfReboundsKing(record.getPlayerName());
        king.setRebounds(record.getRebounds());
        
        record = list.get(0);
        for(RecordOfPlayer token:list){
            if(token.getAssists() > record.getAssists()){
                record = token;
            }
        }
        king.setNameOfAssistsKing(record.getPlayerName());
        king.setAssists(record.getAssists());
        
	    return king;
	}

	//得到月份，以Date的形式，month是0~11
	public Date getDateInDate(){
	    Date date = new Date();
	    String[] dateString = this.date.split("_")[1].split("-");
	    date.setYear(Integer.parseInt(dateString[0]));
	    date.setMonth(Integer.parseInt(dateString[1]) - 1);
	    date.setDate(Integer.parseInt(dateString[2]));
	    return date;
	}
	
	//得到RecordOfPlayer的总计数据
	public RecordOfPlayer[] getTotalRecordOfPlayer(){
	    RecordOfPlayer[] result = new RecordOfPlayer[2];
	    result[0] = getTotalRecordOfPlayer(this.firstRecordList);
	    result[0] = getTotalRecordOfPlayer(this.secondRecordList);
	    return result;
	}
	
	private RecordOfPlayer getTotalRecordOfPlayer(ArrayList<RecordOfPlayer> recordList){
	    RecordOfPlayer resultRecord = new RecordOfPlayer();
	    int minutes = 0;    //在场时间，以秒为单位
        int fieldGoalHits = 0; //投篮命中数
        int fieldGoalAttempts = 0;//投篮出手数
        int threePointHits = 0;//三分球命中数
        int threePointAttemps = 0;//三分球出手数
        int freeThrowHits = 0;//罚球命中数
        int freeThrowAttemps = 0;//罚球出手数
        int offensiveRebounds = 0;//进攻篮板数
        int defensiveRebounds = 0;//防守篮板数
        int rebounds = 0;//总篮板数
        int assists = 0;//助攻数
        int steals = 0;//抢断数
        int blocks = 0;//盖帽数
        int turnOver = 0;//失误数
        int fauls = 0;//犯规数
        int points = 0;//个人得分
        
        for(RecordOfPlayer record: firstRecordList){
            minutes += record.getMinutes();
            fieldGoalHits += record.getFieldGoalHits();
            fieldGoalAttempts += record.getFieldGoalAttempts();
            threePointHits += record.getThreePointHits();
            threePointAttemps += record.getThreePointAttemps();
            freeThrowHits += record.getFreeThrowHits();
            freeThrowAttemps += record.getFreeThrowAttemps();
            offensiveRebounds += record.getOffensiveRebounds();
            defensiveRebounds += record.getDefensiveRebounds();
            rebounds += record.getRebounds();
            assists += record.getAssists();
            steals += record.getSteals();
            blocks += record.getBlocks();
            turnOver += record.getTurnOver();
            fauls += record.getFauls();
            points += record.getPoints();
        }
        
        resultRecord.setMinutes(minutes);
        resultRecord.setFieldGoalAttempts(fieldGoalAttempts);
        resultRecord.setFieldGoalHits(fieldGoalHits);
        resultRecord.setThreePointHits(threePointHits);
        resultRecord.setThreePointAttemps(threePointAttemps);
        resultRecord.setFreeThrowHits(freeThrowHits);
        resultRecord.setFreeThrowAttemps(freeThrowAttemps);
        resultRecord.setOffensiveRebounds(offensiveRebounds);
        resultRecord.setDefensiveRebounds(defensiveRebounds);
        resultRecord.setRebounds(rebounds);
        resultRecord.setAssists(assists);
        resultRecord.setSteals(steals);
        resultRecord.setBlocks(blocks);
        resultRecord.setTurnOver(turnOver);
        resultRecord.setFauls(fauls);
        resultRecord.setPoints(points);
        
        return resultRecord;
	}
}
