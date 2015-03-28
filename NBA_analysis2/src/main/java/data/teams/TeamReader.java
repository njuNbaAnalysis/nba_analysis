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
        
        /*Thread thread1 = new Thread(){
            public void run(){
                readImage();
                BLController.progress ++;
            }
        };
        thread1.start();*/
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
        
        double current = System.currentTimeMillis();
        
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
        
        correctDivision();
        
        double now = System.currentTimeMillis();
        System.out.println("teamReader:" + (now - current));
        
        return ;
    }
    
    public void readImage(){
        String path = System.getProperty("user.dir");
        
        File file = new File("Data/teams");
        String[] list = file.list();
        
        for(Team token:teamList){
            //if(!token.getName().contains(".svg")) continue;
            
            String abbreviation = token.getAbbreviation();
            SvgImage image = null;
            try {
                image = new SvgImage(new URL("file:///" + path + "/Data/teams/" + abbreviation + ".svg"));
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

    //通过对分区所属赛区的学习修正少数赛区不正确的球队
    private void correctDivision(){
        ArrayList<DivisionConferencePair> pairList = new ArrayList<DivisionConferencePair>();
        
        //study process
        for(Team token:teamList){
            //如果为空，
            if(pairList.isEmpty()){
                pairList.add(new DivisionConferencePair(token.getDivision(),token.getConference(),1));
            }
            //如果不为空，则看情况加入对或对原有对+1
            else{
                boolean isFound = false;
                for(DivisionConferencePair pair:pairList){
                    if(pair.isRelatedTo(token)){
                        pair.setNumber(pair.getNumber() + 1);
                        isFound = true;
                        break;
                        //System.out.println("if");
                    }
                }
                if(!isFound){
                    pairList.add(new DivisionConferencePair(token.getDivision(),token.getConference(),1));
                }
            }
        }
        
        //debug
/*        System.out.println("size: " + pairList.size());
        for(DivisionConferencePair pair:pairList){
            System.out.println(pair);
        }*/
        
        //correct process
        for(Team token:teamList){
            int currentNumber = 0;
            char currentConference = '\0';
            
            //遍历每一个分区赛区对，将分区所对应的赛区最频繁情况作为正确赛区
            for(DivisionConferencePair pair:pairList){
                if(pair.getDivision().equals(token.getDivision())){
                    if(pair.getNumber() > currentNumber){
                        currentConference = pair.getConference();
                        currentNumber = pair.getNumber();
                    }
                }
            }
            token.setConference(currentConference);
        }
        
        //debug
/*        for(Team team:teamList){
            System.out.println(team.getName() + "      " + team.getConference());
        }*/
    }
}
