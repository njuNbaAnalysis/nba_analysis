package logic;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.FutureMatchvo;
import vo.Matchvo;


public class Main {
    public static void main(String args[]) throws RemoteException{
        
        long start = System.currentTimeMillis();
        

        
/*        Teamvo vo = TeamvoGenerator.getInstance().getTeamvoWithLatest10Data("ATL", "14-15", false);
        Teamvo vo1 = TeamvoGenerator.getInstance().getTeamvoWithLatest10Data("BKN", "14-15", false);
        
        System.out.println(vo);
        System.out.println(vo1);*/
        
        ArrayList<FutureMatchvo> list = BLController.getInstance().getFutureMatches("2015-06-05");
        
        System.out.println(list.get(0).getMid());
        
        long end =  System.currentTimeMillis();
        
        System.out.println("111totalTime:" + (end - start));
    }
}
