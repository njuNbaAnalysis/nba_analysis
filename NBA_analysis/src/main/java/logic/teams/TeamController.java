package logic.teams;

import java.util.ArrayList;

import logic.BLController;
import logic.matches.Match;
import logic.matches.MatchController;
import logic.matches.RecordOfPlayer;
import data.DataController;
import data.DataService;


public class TeamController {
    private ArrayList<Team> teamList = null;
    private static TeamController teamController = null;
    private DataService dataService;
    
    private TeamController(){
        dataService = DataController.getInstance();
        init();
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
        
        long current = System.currentTimeMillis();
        computeData();
        long now = System.currentTimeMillis();
        System.out.println("here: " + (now - current));
    }

    public ArrayList<Team> getAllTeams(){
        return teamList;
    }
    
    //对team数据进一步计算
    private void computeData(){
        ArrayList<Match> matchList = new ArrayList<Match>();
        MatchController controller = MatchController.getInstance();
        matchList = controller.getAllMatches();
        
        for(Match token:matchList){
            
            parseRecordList(token,0);
            parseRecordList(token,1);
            
            crossAssign(token);
        }
        
        BLController.progress ++;
    }
    
    //根据名字缩写查找，如果没有找到则返回null
    public Team getTeam(String name){
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
        
        team.setPoints(team.getPoints() + match.getPoints()[num]);
        team.setNumOfMatches(team.getNumOfMatches() + 1);
        
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
            team.setFieldGoalAttemps(team.getFieldGoalAttemps() + token.getFieldAttempts());
            team.setFieldGoalHits(team.getFieldGoalHits() + token.getFieldGoals());
            team.setThreePointerAttempts(team.getThreePointerAttempts() + token.getThreePointAttemps());
            team.setThreePointerHits(team.getThreePointerHits() + token.getThreePoints());
            team.setFreeThrowHits(team.getFreeThrowHits() + token.getFreeThrows());
            team.setFreeThrows(team.getFreeThrows() + token.getFreeThrowAttemps());
            team.setOffensiveRebounds(team.getOffensiveRebounds() + token.getOffensiveRebounds());
            team.setDefensiveRebounds(team.getDefensiveRebounds() + token.getDefensiveRebounds());
            
            team.setAssists(team.getAssists() + token.getAssists());
            team.setSteals(team.getSteals() + token.getSteals());
            team.setBlockShots(team.getBlockShots() + token.getBlocks());
            team.setTurnOver(team.getTurnOver() + token.getThreePoints());
            team.setFouls(team.getFouls() + token.getFauls());
            
            
            //向teamList中加入球员
            String name = token.getPlayerName();
            if(!team.getPlayerList().contains(name)){
                team.getPlayerList().add(name);
            }
        }
        
    }

    //交叉属性赋值
    private void crossAssign(Match match){
        Team team1 = getTeam(match.getTeams()[0]);
        Team team2 = getTeam(match.getTeams()[1]);
        
        //进攻防守回合累加赋值
        double incrementOfOffensiveRounds1 = team1.getFieldGoalAttemps() + 0.4 * team1.getFreeThrows() - 1.07 * 
                (1.0 * team1.getOffensiveRebounds() / (team1.getOffensiveRebounds() + team2.getDefensiveRebounds())
                        * (team1.getFieldGoalAttemps() + team1.getFreeThrows() - team1.getFieldGoalHits() - team1.getFreeThrowHits())
                        + 1.07 * team1.getTurnOver());
        double incrementOfOffensiveRounds2 = team2.getFieldGoalAttemps() + 0.4 * team2.getFreeThrows() - 1.07 * 
                (1.0 * team2.getOffensiveRebounds() / (team2.getOffensiveRebounds() + team1.getDefensiveRebounds())
                        * (team2.getFieldGoalAttemps() + team2.getFreeThrows() - team2.getFieldGoalHits() - team2.getFreeThrowHits())
                        + 1.07 * team2.getTurnOver());
        team1.setOffensiveRounds(team1.getOffensiveRounds() + incrementOfOffensiveRounds1);
        team1.setDefensiveRounds(team1.getDefensiveRounds() + incrementOfOffensiveRounds2);
        team2.setOffensiveRounds(team2.getOffensiveRounds() + incrementOfOffensiveRounds2);  
        team2.setDefensiveRounds(team2.getDefensiveRounds() + incrementOfOffensiveRounds2);
        
        //对手各种属性累加赋值，
        team1.setPointsRival(team1.getPointsRival() + match.getPoints()[1]);
        team2.setPointsRival(team2.getPointsRival() + match.getPoints()[0]);
        for(RecordOfPlayer token:match.getFirstRecordList()){
            team2.setFieldGoalAttempsRival(team2.getFieldGoalAttempsRival() + token.getFieldAttempts());
            team2.setThreePointerAttemptsRival(team2.getThreePointerAttemptsRival() + token.getFreeThrowAttemps());
            team2.setOffenseReboundsRival(team2.getOffenseReboundsRival() + token.getOffensiveRebounds());
            team2.setDefenseReboundsRival(team2.getDefenseReboundsRival() + token.getDefensiveRebounds());
        }
        for(RecordOfPlayer token:match.getSecondRecordList()){
            team1.setFieldGoalAttempsRival(team1.getFieldGoalAttempsRival() + token.getFieldAttempts());
            team1.setThreePointerAttemptsRival(team1.getThreePointerAttemptsRival() + token.getFreeThrowAttemps());
            team1.setOffenseReboundsRival(team1.getOffenseReboundsRival() + token.getOffensiveRebounds());
            team1.setDefenseReboundsRival(team1.getDefenseReboundsRival() + token.getDefensiveRebounds());
        }
        
        //各种效率计算
        team1.setOffenseEfficiency(team1.getPoints() * 1.0 / team1.getOffensiveRounds() / 100);
        team2.setOffenseEfficiency(team2.getPoints() * 1.0 / team2.getOffensiveRounds() / 100);
        
        team1.setDefenseEfficiency(1.0 * team2.getPoints() / team1.getDefensiveRounds() / 100);
        team2.setDefenseEfficiency(1.0 * team1.getPoints() / team2.getDefensiveRounds() / 100);
        
        team1.setReboundsEfficiency(team1.getRebounds() * 1.0 / (team1.getRebounds() / team2.getRebounds()));
        team2.setReboundsEfficiency(team2.getRebounds() * 1.0 / (team2.getRebounds() / team1.getRebounds()));
        
        team1.setStealsEfficiency(1.0 * team1.getSteals() / team1.getDefensiveRounds() / 100);
        team2.setStealsEfficiency(1.0 * team2.getSteals() / team2.getDefensiveRounds() / 100);
        
        team1.setAssistsPercentage(1.0 * team1.getAssists() / team1.getOffensiveRounds() / 100);
        team2.setAssistsPercentage(1.0 * team2.getAssists() / team2.getOffensiveRounds() / 100);
    }
}
