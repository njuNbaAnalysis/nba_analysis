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
            
            parseRecordList(matchList,token,0);
            parseRecordList(matchList,token,1);
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
    private void parseRecordList(ArrayList<Match> matchList,Match match,int num){
        String teamName = match.getTeams()[num];
        Team team = getTeam(teamName);
        ArrayList<RecordOfPlayer> recordList = null;
        if(num == 0){
            recordList = match.getFirstRecordList();
        }
        else{
            recordList = match.getSecondRecordList();   
        }
        
        
    }
}
