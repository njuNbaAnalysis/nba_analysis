package logic.players;

import java.util.ArrayList;

import logic.matches.Match;
import logic.matches.MatchController;

public class PlayerController {
    ArrayList<Player> playerList = null;
    private static PlayerController playerController = null;
    
    private PlayerController(){
        //initialize matchList,
        
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
}
