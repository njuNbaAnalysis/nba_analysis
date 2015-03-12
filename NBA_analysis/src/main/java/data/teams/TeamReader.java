package data.teams;

import java.io.File;
import java.util.ArrayList;

import logic.teams.Team;

public class TeamReader {
    private ArrayList<Team> teamList = new ArrayList<Team>();
    private static String dataPath = "Data/teams/";
    
    public ArrayList<Team> readTeams(){
        double current = System.currentTimeMillis();
        
        File file = new File(dataPath + "teams");

        readText(file);
        readImage();
        
        double now = System.currentTimeMillis();
        System.out.println(now - current);
        
        return teamList;
    }
    
    public void readText(File file){
        
        return;
    }
    
    public void readImage(){
        
    }
    
}
