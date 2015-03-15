package data.teams;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

import logic.BLController;
import logic.teams.Team;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderException;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

import util.SvgImage;

public class TeamReader {
    private ArrayList<Team> teamList = new ArrayList<Team>();
    private static String dataPath = "Data/teams/";
    
    public void init(){
        File file = new File(dataPath + "teams");
        readText(file);
        BLController.progress ++;
        
        Thread thread1 = new Thread(){
            public void run(){
                readImage();
                BLController.progress ++;
            }
        };
        thread1.start();
    }
    
    @Deprecated
    public ArrayList<Team> readTeams(){
        double current = System.currentTimeMillis();
        
        File file = new File(dataPath + "teams");

        readText(file);
        //readImage();
        
        double now = System.currentTimeMillis();
        System.out.println(now - current);
        
        return teamList;
    }
    
    public void readText(File file){
        
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(dataPath + "teams")));
            String line = null;
            while((line = reader.readLine()) != null){
                if(line.contains("═"))  continue;   //过滤第一行和最后一行
                String[] temp = line.split("║");//所有的Value
                temp = temp[1].split("│");
                for(int i = 0;i < temp.length;i ++){
                    temp[i] = temp[i].trim();
                }
                
                String name = temp[0];
                String abbreviation = temp[1]; 
                String location = temp[2];
                char conference = temp[3].charAt(0); 
                String division = temp[4];
                String homeCourt = temp[5];  
                int setUpTime = Integer.parseInt(temp[6]);
                
                Team team = new Team(name, abbreviation, location, conference, division, homeCourt, setUpTime, null);
                teamList.add(team);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return ;
    }
    
    public void readImage(){
        String path = System.getProperty("user.dir");
        
        File file = new File("Data/teams");
        String[] list = file.list();
        
        for(Team token:teamList){
            if(!token.getName().contains(".svg")) continue;
            
            String name = token.getName();
            SvgImage image = null;
            try {
                image = new SvgImage(new URL("file:///" + path + "/Data/teams/" + token));
                token.setLogo(image);
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        
    }
    
    public void convert2PNG(String path){ 
        Transcoder transcoder = new PNGTranscoder(); 
        TranscoderInput input;
        try {
            input = new TranscoderInput(new FileInputStream(new File(path + ".svg")));
            FileOutputStream out = new FileOutputStream(new File(path + ".png"));
            TranscoderOutput output = new TranscoderOutput(out); 
            transcoder.transcode(input, output); 
            
            out.flush();
            out.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (TranscoderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } 
    }  
    
    public static void main(String[] args){
/*        TeamReader reader = new TeamReader();
        reader.convert2PNG("Data/teams/ATL");*/
        
        //System.out.println(a.getAbsolutePath());
        long current = System.currentTimeMillis();

        



        long now = System.currentTimeMillis();
        System.out.println(now - current);
        
/*        JFrame frame = new JFrame("test");
        frame.setSize(500, 500);
        ImageIcon icon = new ImageIcon();
        JLabel label = new JLabel();
        icon.setImage(image.getImage(500, 500));
        
        label.setIcon(icon);
        frame.add(label);
        frame.setVisible(true);*/
    }

    public ArrayList<Team> getTeamList() {
        return teamList;
    }
}
