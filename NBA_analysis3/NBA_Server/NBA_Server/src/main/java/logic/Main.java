package logic;

import java.rmi.RemoteException;
import java.util.ArrayList;

import logic.teams.TeamRecordItemList;
import logic.teams.TeamvoGenerator;
import po.TeamRecordItem;
import vo.Teamvo;

public class Main {
    public static void main(String argsp[]) throws RemoteException{
        
        long start = System.currentTimeMillis();
        
        /*TeamvoGenerator generator = TeamvoGenerator.getInstance();
        Teamvo vo = generator.getTeamvo("CHI", "14-15", false);*/
        BLController controller = BLController.getInstance();
        Teamvo vo = controller.getTeamByTeamName("CHI", "14-15", false);
        System.out.println(vo.toString());
 
        long end =  System.currentTimeMillis();
        
        System.out.println("totalTime:" + (end - start));
    }
}
