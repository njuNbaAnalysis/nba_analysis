package logic.teams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import logic.BLController;
import logic.BLParameter;
import logic.BLParameter.Mode;
import logic.BLParameter.Sort;
import logic.matches.Match;
import logic.matches.MatchController;
import logic.matches.RecordOfPlayer;
import compare.TeamComparator;
import compare.TeamWinPercentageComp;
import data.DataController;
import data.DataService;


public class TeamController {
    private ArrayList<Team> teamList = null;
    private static TeamController teamController = null;
    private DataService dataService;
    
    private TeamController(){
        dataService = DataController.getInstance();
        init();//此时做init为了使构造和init不能分开调用，防止BLController中的机制出错
    }
    
    public static TeamController getInstance(){
        if(teamController != null){
            return teamController;
        }
        else{
        	teamController =  new TeamController();
            return teamController;
        }
    }
    
    public void init(){
        teamList = dataService.getAllTeams();
        computeData();
        
        
//        long current = System.currentTimeMillis();
//        computeData();
//        long now = System.currentTimeMillis();
//        System.out.println("computeData_team:" + (now - current));
        
        BLController.progress ++;
    }

    public ArrayList<Team> getAllTeams(){
        return teamList;
    }
    
    //对team数据进一步计算
    //包括对team被赛季的联盟排名进行赋值
    private void computeData(){
        
        ArrayList<Match> matchList = new ArrayList<Match>();
        MatchController controller = MatchController.getInstance();
        matchList = controller.getAllMatches();
        
        for(Match token:matchList){
            parseRecordList(token,0);
            parseRecordList(token,1);
        }
        
        for(Match token:matchList){
            computeRounds(token);//进攻防守回合赋值
        }
        
        for(Match token:matchList){
            computeRival(token);    //对手各种属性
        }
        
        computeEfficiency(teamList);
        
        computeRankingInLeague();   //计算在联盟中的排名
    }
    
    //根据名字缩写查找，如果没有找到则返回null
    public synchronized Team getTeam(String name){
        for(Team token:teamList){
            if(token.getAbbreviation().equals(name)){
                return token;
            }
        }
        return null;
    }

    //对某一个recordList进行处理
    //对不包含对手team的属性进行赋值
    private void parseRecordList(Match match,int num){
        String teamName = match.getTeams()[num];
        Team team = getTeam(teamName);
        
        //得分，比赛场数赋值
        team.setPoints(team.getPoints() + match.getPoints()[num]);
        team.setNumOfMatches(team.getNumOfMatches() + 1);
        
        //胜利场数赋值
        //getRecordList&if win,plus one
        ArrayList<RecordOfPlayer> recordList = null;
        if(num == 0){
            recordList = match.getFirstRecordList();
            
            if(match.getPoints()[0] > match.getPoints()[1]){
                team.setNumOfVictory(team.getNumOfVictory() + 1);
            }
        }
        else{
            recordList = match.getSecondRecordList();   
            if(match.getPoints()[1] > match.getPoints()[0]){
                team.setNumOfVictory(team.getNumOfVictory() + 1);
            }
        }
        
        for(RecordOfPlayer token:recordList){
            team.setFieldGoalAttemps(team.getFieldGoalAttemps() + token.getFieldGoalAttempts());
            team.setFieldGoalHits(team.getFieldGoalHits() + token.getFieldGoalHits());
            team.setThreePointerAttempts(team.getThreePointerAttempts() + token.getThreePointAttemps());
            team.setThreePointerHits(team.getThreePointerHits() + token.getThreePointHits());
            team.setFreeThrowHits(team.getFreeThrowHits() + token.getFreeThrowHits());
            team.setFreeThrows(team.getFreeThrows() + token.getFreeThrowAttemps());
            team.setOffensiveRebounds(team.getOffensiveRebounds() + token.getOffensiveRebounds());
            team.setDefensiveRebounds(team.getDefensiveRebounds() + token.getDefensiveRebounds());
            
            team.setAssists(team.getAssists() + token.getAssists());
            team.setSteals(team.getSteals() + token.getSteals());
            team.setBlockShots(team.getBlockShots() + token.getBlocks());
            team.setTurnOver(team.getTurnOver() + token.getTurnOver());
            team.setFouls(team.getFouls() + token.getFauls());
            
            
            //向teamList中加入球员
            String name = token.getPlayerName();
            if(!team.getPlayerList().contains(name)){
                team.getPlayerList().add(name);
            }
        }
        
    }

    //进攻防守回合赋值
    private void computeRounds(Match match){
        ArrayList<RecordOfPlayer> recordList1 = match.getFirstRecordList();
        ArrayList<RecordOfPlayer> recordList2 = match.getSecondRecordList();
        
        Team team1 = getTeam(match.getTeams()[0]);
        Team team2 = getTeam(match.getTeams()[1]);
        
        //进攻防守回合累加赋值
            //正常比赛的各项数据
        int fieldGoalAttempts1 = 0;
        int freeThrowAttemps1 = 0;
        int offensiveRebounds1 = 0;
        int defensiveRebounds1 = 0;
        int fieldGoalHits1 = 0;
        int freeThrowHits1 = 0;
        int turnOver1 = 0;
        
        int fieldGoalAttempts2 = 0;
        int freeThrowAttemps2 = 0;
        int offensiveRebounds2 = 0;
        int defensiveRebounds2 = 0;
        int fieldGoalHits2 = 0;
        int freeThrowHits2 = 0;
        int turnOver2 = 0;
        
        for(RecordOfPlayer record:recordList1){
            fieldGoalAttempts1 += record.getFieldGoalAttempts();
            freeThrowAttemps1 += record.getFreeThrowAttemps();
            offensiveRebounds1 += record.getOffensiveRebounds();
            defensiveRebounds1 += record.getDefensiveRebounds();
            fieldGoalHits1 += record.getFieldGoalHits();
            freeThrowHits1 += record.getFreeThrowHits();
            turnOver1 += record.getTurnOver();
        }
        
        for(RecordOfPlayer record:recordList2){
            fieldGoalAttempts2 += record.getFieldGoalAttempts();
            freeThrowAttemps2 += record.getFreeThrowAttemps();
            offensiveRebounds2 += record.getOffensiveRebounds();
            defensiveRebounds2 += record.getDefensiveRebounds();
            fieldGoalHits2 += record.getFieldGoalHits();
            freeThrowHits2 += record.getFreeThrowHits();
            turnOver2 += record.getTurnOver();
        }
        
        double incrementOfOffensiveRounds1 = fieldGoalAttempts1 + 0.4 * freeThrowAttemps1 - 1.07 * 
                (1.0 * offensiveRebounds1 / (offensiveRebounds1 + defensiveRebounds2)
                        * (fieldGoalAttempts1 + freeThrowAttemps1 - fieldGoalHits1 - freeThrowHits1)
                        + 1.07 * turnOver1);
        double incrementOfOffensiveRounds2 = fieldGoalAttempts2 + 0.4 * freeThrowAttemps2 - 1.07 * 
                (1.0 * offensiveRebounds2 / (offensiveRebounds2 + defensiveRebounds1)
                        * (fieldGoalAttempts2 + freeThrowAttemps2 - fieldGoalHits2 - freeThrowHits2)
                        + 1.07 * turnOver2);
        
        team1.setOffensiveRounds(team1.getOffensiveRounds() + incrementOfOffensiveRounds1);
        team2.setDefensiveRounds(team2.getDefensiveRounds() + incrementOfOffensiveRounds1);
        //System.out.println("防守回合:" + record.getFreeThrowAttemps());
        team2.setOffensiveRounds(team1.getOffensiveRounds() + incrementOfOffensiveRounds2);
        team1.setDefensiveRounds(team2.getDefensiveRounds() + incrementOfOffensiveRounds2);
    }
    
    //对对方各种属性赋值
    private void computeRival(Match match){
        Team team1 = getTeam(match.getTeams()[0]);
        Team team2 = getTeam(match.getTeams()[1]);
        
        //对手各种属性累加赋值，
        team1.setPointsRival(team1.getPointsRival() + match.getPoints()[1]);
        team2.setPointsRival(team2.getPointsRival() + match.getPoints()[0]);
        for(RecordOfPlayer token:match.getFirstRecordList()){
            team2.setFieldGoalAttempsRival(team2.getFieldGoalAttempsRival() + token.getFieldGoalAttempts());
            team2.setThreePointerAttemptsRival(team2.getThreePointerAttemptsRival() + token.getFreeThrowAttemps());
            team2.setOffenseReboundsRival(team2.getOffenseReboundsRival() + token.getOffensiveRebounds());
            team2.setDefenseReboundsRival(team2.getDefenseReboundsRival() + token.getDefensiveRebounds());
        }
        for(RecordOfPlayer token:match.getSecondRecordList()){
            team1.setFieldGoalAttempsRival(team1.getFieldGoalAttempsRival() + token.getFieldGoalAttempts());
            team1.setThreePointerAttemptsRival(team1.getThreePointerAttemptsRival() + token.getFreeThrowAttemps());
            team1.setOffenseReboundsRival(team1.getOffenseReboundsRival() + token.getOffensiveRebounds());
            team1.setDefenseReboundsRival(team1.getDefenseReboundsRival() + token.getDefensiveRebounds());
        }
    }

    //对每个球队计算各种效率
    private void computeEfficiency(ArrayList<Team> teamList){
        for(Team team:teamList){
            team.setOffenseEfficiency(team.getPoints() * 1.0 / team.getOffensiveRounds() * 100);
            team.setDefenseEfficiency(1.0 * team.getPoints() / team.getDefensiveRounds() * 100);
/*            System.out.println("本队篮板: " + team.getRebounds());
            System.out.println("对手篮板: " + team.getReboundsRival());*/
            team.setReboundsEfficiency(team.getRebounds() * 1.0 / (team.getRebounds() + team.getReboundsRival()));
            team.setStealsEfficiency(1.0 * team.getSteals() / team.getDefensiveRounds() * 100);
            team.setAssistsPercentage(1.0 * team.getAssists() / team.getOffensiveRounds() * 100);
        }
    }
    
    public ArrayList<Object> getResult(BLParameter parameter){
        
        ArrayList<Object> result = new ArrayList<Object>();
        
        //进行数据加载
        if(parameter.isHigh()){
            computeHighInfo();
        }
        else{
            computeNormalInfo();
        }
        
        //进行mode判断,如果是hot直接处理并返回
        Mode mode = parameter.getMode();
        if(mode.getMode().equals("hot")){
            String field = mode.getField();
            Sort sort = parameter.new Sort(field,false);
            parameter.addSort(sort);
            this.sort(teamList, parameter);
            
            int num = 0;//已经添加的球队数
            for(Team team:teamList){
                if(num == 5) break;
                
                result.add(team.getHotInfo(field));
                num++;
            }
            return result;
        }
        
        //排序
        this.sort(teamList, parameter);
        
        //进行Number值判断
        int num = 0;//已经添加的球队数
        for(Team team:teamList){
            if(num == parameter.getNumber()) break;
            
            if(parameter.isHigh()){
                result.add(team.getHighInfo(parameter.isAvarage()));
            }
            else{
                result.add(team.getNormalInfo(parameter.isAvarage()));
            }
            num++;
        }
        
        return result;
    }
    
    //只计算普通数据，为了数据通常这两个只有一个会被调用，根据matchList即时计算
    private void computeNormalInfo(){
         
        MatchController controller = MatchController.getInstance();
        ArrayList<Match> matchList = controller.getAllMatches();
        
        for(Match token:matchList){
            parseRecordList(token,0);
            parseRecordList(token,1);
        }
    }
    
    //只计算高阶数据，根据matchList即时计算 
    private void computeHighInfo(){
        computeData();  //所有数据必须在normalInfo上进行计算，故直接调用原有接口computeData
    }

    private void sort(ArrayList<Team> teamList,BLParameter parameter){
        Comparator<Team> comparator = new TeamComparator(parameter);
        Collections.sort(teamList,comparator);
    }

    //对球队本赛季在联盟中的排名进行赋值，按照胜率排名
    private void computeRankingInLeague(){
        ArrayList<Team> eastList = new ArrayList<Team>();
        ArrayList<Team> westList = new ArrayList<Team>();
        
        for(Team team:teamList){
            if(team.getConference() == 'E'){
                eastList.add(team);
            }
            else if(team.getConference() == 'W'){
                westList.add(team);
            }
            else{
                System.out.println("error in TeamController.computeRankingInLeague: " + team.getConference());
            }
        }
        
        eastList.sort(new TeamWinPercentageComp());
        westList.sort(new TeamWinPercentageComp());
        
        //写入ranking
        for(Team team:eastList){
            team.setRankingInLeague(eastList.indexOf(team) + 1);
        }
        for(Team team:westList){
            team.setRankingInLeague(westList.indexOf(team) + 1);
        }
    }
    
    //提供给MatchController的接口
    public void addMatch(Match match){
        parseRecordList(match,0);
        parseRecordList(match,1);
        
        computeRounds(match);
        computeRival(match);
        computeEfficiency(this.teamList);
    }

    /**
     * @param field
     * @param num返回前几
     * @return
     */
    public ArrayList<Team> getSeasonKingTeam(String field, int num) {
        // TODO Auto-generated method stub
        return null;
    }
}
