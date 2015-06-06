package logic;

import java.rmi.RemoteException;
import java.util.ArrayList;

import logic.teams.TeamController;
import logic.teams.TeamvoGenerator;
import vo.Teamvo;

public class Main {
    public static void main(String args[]) throws RemoteException{
        
        long start = System.currentTimeMillis();
        
        /*TeamvoGenerator generator = TeamvoGenerator.getInstance();
        Teamvo vo = generator.getTeamvo("CHI", "14-15", false);*/
        TeamController controller = TeamController.getInstance();
        //Teamvo vo = TeamvoGenerator.getInstance().getTeamvoWithLatest10Data("CHI", "14-15", false);
        ArrayList<Teamvo> voList = controller.getAllTeams("13-14", false);
        for(Teamvo vo:voList){
            System.out.println(vo.toString());
        }
        
        //System.out.println(vo);
        
 
        long end =  System.currentTimeMillis();
        
        System.out.println("totalTime:" + (end - start));
    }
}
