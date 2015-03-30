package logic;

import java.util.ArrayList;

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
            System.out.println("error in Console.execute: can not parsing input parameters!");
        }
    }
    
    //如果传入空数组，返回null
    //返回此数组删去第num项的值
    private String[] remove(String[] args,int num){
        if(args.length == 0) return null;
        String[] result = new String[args.length - 1];
        for(int i = 0;i < num;i ++){
            result[i] = args[i];
        }
        for(int i = num + 1;i < args.length;i ++){
            result[i - 1] = args[i];
        }
        return result;
    }
    

    
    
}
