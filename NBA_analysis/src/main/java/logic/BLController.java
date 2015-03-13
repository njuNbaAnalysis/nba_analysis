package logic;

import java.util.ArrayList;

import logic.matches.Match;
import logic.matches.MatchController;
import logic.players.Player;
import logic.players.PlayerController;
import logic.teams.Team;
import logic.teams.TeamController;

public class BLController implements BLService{
    private static BLController blController = null;
    public static int progress = 0;
    public static int maxProgress = 9;
    
    private MatchController matchController = null;
    private PlayerController playerController = null;
    private TeamController teamController = null;
    
    private BLController(){
        matchController = MatchController.getInstance();
        teamController = TeamController.getInstance();
        playerController = PlayerController.getInstance();
    }
    
    public static BLController getInstance(){
        if(blController != null){
            return blController;
        }
        else{
        	blController = new BLController();
            return blController;
        }
    }
    
    
	public ArrayList<Player> getAllPlayers() {
		// TODO Auto-generated method stub
		return playerController.getAllPlayers();
	}

	public ArrayList<Team> getAllTeams() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Match> getAllMatches() {
		// TODO Auto-generated method stub
		return matchController.getAllMatches();
	}

    @Override
    public int getProgress() {
        // TODO Auto-generated method stub
        return progress;
    }

    public static void main(String[] args){
        long current = System.currentTimeMillis();
        
        BLController v = BLController.getInstance();
        
        
        Thread thread1 = new Thread(){
            public void run(){
                while(BLController.progress != 6){
                    System.out.println(BLController.progress);
                    System.out.println("now::::::::      " + BLController.progress / 9.0 * 100 + "%");
                    try {
                        this.sleep(100);
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        };
        thread1.start();
        
        
        try {
            thread1.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        long now = System.currentTimeMillis();
        System.out.println(now - current);
    }
}
