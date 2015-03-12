package data.teams;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import logic.teams.Team;

import org.apache.batik.transcoder.Transcoder;
import org.apache.batik.transcoder.TranscoderInput;
import org.apache.batik.transcoder.TranscoderOutput;
import org.apache.batik.transcoder.image.PNGTranscoder;

public class TeamReader {
    private ArrayList<Team> teamList = new ArrayList<Team>();
    private static String dataPath = "Data/teams/";
    
    public ArrayList<Team> readTeams(){
        double current = System.currentTimeMillis();
        
        File file = new File(dataPath + "teams");

        readText(file);
        readImage();
        
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
        
        for(Team token:teamList){
            String name = token.getName();
            
            Image image = null;
            try {//
                image = ImageIO.read(new File(dataPath + name + ".svg"));
                token.setLogo(image);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Image convert2PNG(String path){ 
        Image image = null;
        Transcoder transcoder = new PNGTranscoder(); 
        //TranscoderInput input = new TranscoderInput(new FileInputStream(new File(path))); 
        //TranscoderOutput output = new TranscoderOutput(out); 
        //transcoder.transcode(input, output); 
        //image = ImageIO.read
        return null; 
    }  
    
    
    public static void main(String[] args){
        TeamReader reader = new TeamReader();
        reader.readTeams();
    }
}
