package logic;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import logic.teams.Team;
import util.Setting;

public class Console {
    public void execute(java.io.PrintStream out,String[] args){
        
        if(args[0].equals("--datasource")){
            Setting.setPath(args[1]);
        }
        else if(args[0].equals("-player") || args[0].equals("-team")){
            BLParameter parameter = new BLParameter(args);
            BLService service = BLController.getInstance();
            ArrayList<Object> result = service.getResult(parameter);
            for(Object token:result){
                out.append(token.toString());
            }
        }
        else{
            System.out.println("error in Console.execute: can not parsing input parameters: " + args[0]);
        }
    }
    
    //仅作测试用
    public static void main(String[] args){
        System.out.println("use ___ (three times) to split string,example: --datasource___D:nba// enter your command:");
        
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input = null;
        try {
                input = reader.readLine();
                Console c = new Console();
                c.execute(System.out, input.trim().split("___"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    

    
    
}
