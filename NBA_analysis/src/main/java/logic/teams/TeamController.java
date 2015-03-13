package logic.teams;

import java.util.ArrayList;

import logic.BLController;
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
        BLController.progress ++;
    }
}
