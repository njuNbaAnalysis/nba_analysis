package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * 此类暂用于对路径进行读取和设置，可使用getPath(),和setPath()
 *
 */
public class Setting {
    
    private static String path = "./Data/";
    
    //静态初始化，防止路径信息的重复读取
/*    static{
        File file = new File("./dataPath");
        if(file.exists()){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                path = reader.readLine();
                reader.close();
            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }*/
    
    public static String getPath(){
        return path;
    }
    
    public static void setPath(String path){
        Setting.path = path;
        File file = new File("./dataPath");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(path);
            writer.flush();
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
