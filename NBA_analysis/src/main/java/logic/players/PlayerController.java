package logic.players;

import java.util.ArrayList;

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
            return new PlayerController();
        }
    }
    
    public ArrayList<Player> getAllPlayers(){
        
        return playerList;
    }
    
    public void init(){
        playerList = dataService.getAllPlayers();
    }
}
