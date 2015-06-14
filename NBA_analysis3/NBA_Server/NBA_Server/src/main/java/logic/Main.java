package logic;

import java.rmi.RemoteException;


import logic.teams.TeamController;
import logic.teams.TeamvoGenerator;
import vo.Teamvo;


public class Main {
    public static void main(String args[]) throws RemoteException{
        
        long start = System.currentTimeMillis();
        
        /*TeamvoGenerator generator = TeamvoGenerator.getInstance();
        Teamvo vo = generator.getTeamvo("CHI", "14-15", false);*/
        
        
        //TeamController controller = TeamController.getInstance();
        Teamvo vo = TeamvoGenerator.getInstance().getTeamvoWithLatest10Data("ATL", "14-15", false);
        System.out.println(vo);
        //ArrayList<Teamvo> voList = controller.getAllTeams("14-15", false);
/*        for(Teamvo vo:voList){
            System.out.println(vo.toString());
        }*/
        
/*        for(Teamvo vo:voList){
            double c = vo.getNumOfVictory() / vo.getNumOfMatches();
            //System.out.println(c);
            System.out.println(vo.getNumOfVictory());
            
        }*/
        /*System.out.println(vo);
        System.out.println(vo.toJSONObject());*/
        
        //BLController.getInstance().getTeamAbility("ATL", "14-15", false);
        //BLController.getInstance().getAllPlayers("14-15", false);
        
 
        long end =  System.currentTimeMillis();
        
        System.out.println("totalTime:" + (end - start));
    }
}
