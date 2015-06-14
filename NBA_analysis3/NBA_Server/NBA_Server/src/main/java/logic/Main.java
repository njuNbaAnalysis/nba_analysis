package logic;

import java.rmi.RemoteException;
import java.util.ArrayList;

import logic.teams.TeamvoGenerator;
import vo.FutureMatchvo;
import vo.Teamvo;


public class Main {
    public static void main(String args[]) throws RemoteException{
        
        long start = System.currentTimeMillis();
        

        
        Teamvo vo = TeamvoGenerator.getInstance().getTeamvoWithLatest10Data("ATL", "14-15", false);
        Teamvo vo1 = TeamvoGenerator.getInstance().getTeamvoWithLatest10Data("BKN", "14-15", false);
        
        System.out.println(vo);
        System.out.println(vo1);
        
        
        long end =  System.currentTimeMillis();
        
        System.out.println("111totalTime:" + (end - start));
    }
}
