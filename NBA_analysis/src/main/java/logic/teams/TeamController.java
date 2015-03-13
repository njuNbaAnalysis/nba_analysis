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
            ArrayList<RecordOfPlayer> recordList1 = token.getFirstRecordList();
            ArrayList<RecordOfPlayer> recordList2 = token.getSecondRecordList();
            
            
        }
        
        BLController.progress ++;
    }
}
