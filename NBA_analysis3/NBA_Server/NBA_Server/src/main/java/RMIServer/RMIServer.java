package RMIServer;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

import BLservice.BLservice;
import logic.BLController;

public class RMIServer {
    public void RMIServerStart(){
        
        try {
            BLservice service = BLController.getInstance();
            
            // 注册通讯端口
            LocateRegistry.createRegistry(6600);
            // 注册通讯路径
            Naming.rebind("rmi://127.0.0.1:6600/BLservice", service);
            System.out.println("RMIServer开启成功！");
        } catch (Exception e) {
            e.printStackTrace();
        }
        
 
    }
    public static void main(String [] args){
        RMIServer rmiserver = new RMIServer();
        rmiserver.RMIServerStart();
    }
}
