package logic;

import java.rmi.RemoteException;
import java.util.ArrayList;

import BLservice.BLservice;
import vo.Teamvo;

public class Main {
    public static void main(String argsp[]) throws RemoteException{
        BLservice service = BLController.getInstance();
        
        long start = System.currentTimeMillis();
        
        try {
            ArrayList<Teamvo> list = service.getAllTeams("14-15", false);
            //Teamvo vo = service.getTeamByTeamName("ATL", "14-15", false);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        long end =  System.currentTimeMillis();
        
        System.out.println(end - start);
    }
}
