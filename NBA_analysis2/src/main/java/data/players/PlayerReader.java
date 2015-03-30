package data.players;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.imageio.ImageIO;

import util.Setting;
import logic.BLController;
import logic.players.Player;

public class PlayerReader {
    private ArrayList<Player> playerList = new ArrayList<Player>();
    private static String dataPath = Setting.getPath() + "/players/";
    
    public void init(){
        playerList = readPlayers();
        BLController.progress ++;
        
/*        Thread thread1 = new Thread(){
            public void run(){
                readAction();
                BLController.progress ++;
            }
        };
        Thread thread2 = new Thread(){
            public void run(){
                readPortrait();
                BLController.progress ++;
            }
        };
        thread1.start();
        thread2.start();*/
    }
    
    
    
    //对应字符串不存在即返回空字符串
    //如果date不存在则返回null
    //球衣号如果为N/A，则此项返回-1
    public ArrayList<Player> readPlayers(){
        double current = System.currentTimeMillis();
        
        File infoFile = new File(dataPath + "info");
        String[] infoList = infoFile.list();

        readText(infoList); 
        //readImage();
        //readImageParallel();
        
        double now = System.currentTimeMillis();
        System.out.println("playerReader:" + (now - current));
        
        return playerList;
    }
    
    //将textData存入playerList中
    private void readText(String[] infoList){
        for(String token:infoList){
            try {
                BufferedReader reader = new BufferedReader(new FileReader(new File(dataPath + "info/" + token)));
                {
                    String name = null;
                    int number = 0;
                    String position = null;
                    int[] height = new int[2];
                    int weight = 0;
                    Date birthday = null;
                    int age = 0;
                    int exp = 0;
                    String school = null;
                    
                    String temp = null;
                    
                    //name
                    name = getValue(reader);
                    
                    //number
                    temp = getValue(reader);
                    try{
                        number = Integer.parseInt(temp);
                    }
                    catch(Exception e){
                        number = -1;
                    }
                    
                    //position
                    position = getValue(reader);
                    
                    //height
                    temp = getValue(reader);
                    if(!temp.equals("")){
                        String[] list = temp.split("-");
                        height[0] = Integer.parseInt(list[0]);
                        height[1] = Integer.parseInt(list[1]);
                    }
                    
                    //weight
                    weight = Integer.parseInt(getValue(reader));
                    
                    //birthday
                    temp = getValue(reader);
                    birthday = getDate(temp);
                    
                    //age
                    temp = getValue(reader);
                    if(!temp.equals("")){
                        age = Integer.parseInt(temp);
                    }
                    
                    //exp
                    temp = getValue(reader);
                    if(!temp.equals("") && !temp.equals("R")){
                        exp = Integer.parseInt(temp);
                    }
                    
                    //school
                    school = getValue(reader);
                    
                    Player player = new Player(name, number, position, height, weight, birthday, age, exp, school);
                    playerList.add(player);
                    
                    reader.close();
                }
            }catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
    
    public void readAction(){
        for(Player token:playerList){
            String name = token.getName();
            
            Image image = null;
            try {//
                image = ImageIO.read(new File(dataPath + "action/" + name + ".png"));
                token.setAction(image);
            } catch (IOException e) {
            }
        }
    }
    
    public void readPortrait(){
        for(Player token:playerList){
            String name = token.getName();
            
            Image image = null;
            try {//
                image = ImageIO.read(new File(dataPath + "portrait/" + name + ".png"));
                token.setPortrait(image);
            } catch (IOException e) {
            }
        }
    }
    
    private void readImage(){
        for(Player token:playerList){
            String name = token.getName();
            
            Image image = null;
            try {//
                image = ImageIO.read(new File(dataPath + "action/" + name + ".png"));
                token.setAction(image);
            } catch (IOException e) {
            }
            try {//
                image = ImageIO.read(new File(dataPath + "portrait/" + name + ".png"));
                token.setPortrait(image);
            } catch (IOException e) {
            }
        }
    }
 
    
    //readText中用来一步一步读取
    //如果空置则返回空字符串
    //重复读取文本数据
    private String getValue(BufferedReader reader){
        String line = null;
        try {
            line = reader.readLine();
            line = reader.readLine();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        String[] temp = null;
        temp = line.split("│");
        //System.out.println(temp[0] + "  " + temp[1]);
        temp = temp[1].split("║");
        return temp[0].trim();
    }
    
    //将字符串转为date
    //如果字符串为空，返回null
    private Date getDate(String date){
        if(date.equals("")){
            return null;
        }
        else{
            
            String[] temp = date.split(",");
            int year = Integer.parseInt(temp[1].trim());
            
            temp = temp[0].split(" ");
            int day = Integer.parseInt(temp[1].trim());
            
            temp[0] = temp[0].trim();
            int month = 0;
            switch(temp[0]){
            case "JAN":
                month = 1;
                break;
            case "FEB":
                month = 2;
                break;
            case "MAR":
                month = 3;
                break;
            case "APR":
                month = 4;
                break;
            case "MAY":
                month = 5;
                break;
            case "JUN":
                month = 6;
                break;
            case "JUL":
                month = 7;
                break;
            case "AUG":
                month = 8;
                break;
            case "SEP":
                month = 9;
                break;
            case "OCT":
                month = 10;
                break;
            case "NOV":
                month = 11;
                break;
            case "DEC":
                month = 12;
                break;
            }
            
            return new Date(year,month,day);
        }
    }



    
    public ArrayList<Player> getPlayerList() {
        return playerList;
    }

    
    
}
