package logic.players;

import java.util.ArrayList;

import logic.BLController;
import data.DataController;
import data.DataService;

public class PlayerController {
    private ArrayList<Player> playerList = null;
    private static PlayerController playerController = null;
    private DataService dataService;
    
    private PlayerController(){
        dataService = DataController.getInstance();
        init();
    }
    
    public static PlayerController getInstance(){
        if(playerController != null){
            return playerController;
        }
        else{
        	playerController = new PlayerController();
            return playerController;
        }
    }
    
    public ArrayList<Player> getAllPlayers(){
        
        return playerList;
    }
    
    public void init(){
        playerList = dataService.getAllPlayers();
        
        Thread computeThread = new Thread(){
            public void run(){
                computeData();
            }
        };
        computeThread.start();
    }
    
    //对Player数据进一步计算
    private void computeData(){
        
        
        BLController.progress ++;
    }
}
