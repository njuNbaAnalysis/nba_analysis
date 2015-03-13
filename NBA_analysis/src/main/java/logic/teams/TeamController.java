package logic.teams;

import java.util.ArrayList;

import logic.BLController;
import logic.matches.Match;
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
        
        Thread computeThread = new Thread(){
            public void run(){
                computeData();
            }
        };
        computeThread.start();
    }
    
    //对team数据进一步计算
    private void computeData(){
        ArrayList<Match> matchList = new ArrayList<Match>();
        BLController controller = BLController.getInstance();
        matchList = controller.getAllMatches();
        
        for(Match token:matchList){
            
            parseRecordList(token,0);
            parseRecordList(token,1);
            
            postProcessing(token);
        }
        
        BLController.progress ++;
    }
    
    //根据名字查找，如果没有找到则返回null
    private Team getTeam(String name){
        for(Team token:teamList){
            if(token.getName().equals(name)){
                return token;
            }
        }
        return null;
    }

    //对某一个recordList进行处理
    //并对team做除防守回合相关以外的后处理
    private void parseRecordList(Match match,int num){
        String teamName = match.getTeams()[num];
        Team team = getTeam(teamName);
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
        
        team.setNumOfMatches(team.getNumOfMatches() + 1);
        
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
            team.setPoints(team.getPoints() + token.getPoints());
            
            //向teamList中加入球员
            String name = token.getPlayerName();
            if(!team.getPlayerList().contains(name)){
                team.getPlayerList().add(name);
            }
        }
        
        //对team做除，进攻防守回合相关以外的后处理
        team.setRebounds(team.getOffensiveRebounds() + team.getDefensiveRebounds());
        team.setThreePointersPercentage(1.0 * team.getThreePointerHits() / team.getThreePointerAttempts());
        team.setFreeThrowsPercentage(1.0 * team.getFreeThrowHits() / team.getFreeThrows());
        team.setWinningPercentage(1.0 * team.getNumOfVictory() / team.getNumOfMatches());
    }

    //涉及进攻防守回合的后期处理
    private void postProcessing(Match match){
        Team team1 = getTeam(match.getTeams()[0]);
        Team team2 = getTeam(match.getTeams()[1]);
        
        team1.setOffensiveRounds(team1.getFieldGoalAttemps() + 0.4 * team1.getFieldGoalAttemps() - 1.07 * 
                (1.0 * team1.getOffensiveRebounds() + team2.getDefensiveRebounds()) * (team1.getFieldGoalAttemps() 
                        + team1.getFieldGoalAttemps() - team1.getFieldGoalHits() - team1.getFieldGoalHits()) 
                        + 1.07 * team1.getTurnOver());
                        
        team2.setOffensiveRounds(team2.getFieldGoalAttemps() + 0.4 * team2.getFieldGoalAttemps() - 1.07 * 
                (1.0 * team2.getOffensiveRebounds() + team1.getDefensiveRebounds()) * (team2.getFieldGoalAttemps() 
                        + team2.getFieldGoalAttemps() - team2.getFieldGoalHits() - team2.getFieldGoalHits()) 
                        + 1.07 * team2.getTurnOver());  
        
        team1.setOffenseEfficiency(team1.getPoints() * 1.0 / team1.getOffensiveRounds() / 100);
        team2.setOffenseEfficiency(team2.getPoints() * 1.0 / team2.getOffensiveRounds() / 100);
        
        team1.setDefenseEfficiency(1.0 * team2.getPoints() / team2.getOffensiveRounds() / 100);
        team2.setDefenseEfficiency(1.0 * team1.getPoints() / team1.getOffensiveRounds() / 100);
        
        team1.setReboundsEfficiency(team1.getRebounds() * 1.0 / (team1.getRebounds() / team2.getRebounds()));
        team2.setReboundsEfficiency(team2.getRebounds() * 1.0 / (team2.getRebounds() / team1.getRebounds()));
        
        team1.setStealsEfficiency(1.0 * team1.getSteals() / team2.getOffensiveRounds() / 100);
        team2.setStealsEfficiency(1.0 * team2.getSteals() / team1.getOffensiveRounds() / 100);
        
        team1.setAssistsPercentage(1.0 * team1.getAssists() / team1.getOffensiveRounds() / 100);
        team2.setAssistsPercentage(1.0 * team2.getAssists() / team2.getOffensiveRounds() / 100);
    }
}
