package logic;

import java.rmi.RemoteException;

public class Main {
    public static void main(String args[]) throws RemoteException{
        
        long start = System.currentTimeMillis();
        
        /*TeamvoGenerator generator = TeamvoGenerator.getInstance();
        Teamvo vo = generator.getTeamvo("CHI", "14-15", false);*/
        //TeamController controller = TeamController.getInstance();
        //Teamvo vo = TeamvoGenerator.getInstance().getTeamvoWithLatest10Data("ATL", "14-15", false);
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
        
        String s = "http://localhost:8080/JsonServer/getAllPlayers?Season=13-14&isPlayOff=true";
        String a[] = s.split("http://localhost:8080/JsonServer/");
        String para = a[1];
        System.out.println(para);
        String[] paras = para.split("\\?");
        for(String token:paras){
            System.out.println(token);
        }
        
        String methodName = paras[0];
        String part2 = paras[1];
        
        System.out.println(methodName);
        System.out.println(part2);
        
 
        long end =  System.currentTimeMillis();
        
        System.out.println("totalTime:" + (end - start));
    }
}
