package logic;

import java.rmi.RemoteException;

import po.HotZone;
import vo.HotZonevo;


public class Main {
    public static void main(String args[]) throws RemoteException{
        
        long start = System.currentTimeMillis();
        
        //cha,mia
        
        HotZonevo hotZone = BLController.getInstance().getHotZone("CHI", false, false);
        System.out.println(hotZone);
        
        long end =  System.currentTimeMillis();
        
        System.out.println("111totalTime:" + (end - start));
    }
}
