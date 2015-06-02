package logic.matches;

import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;

import vo.EventVo;
import vo.Matchvo;
import vo.Playervo;
import BLservice.matchBLservice;

public class matchBLcontrollor implements matchBLservice{

	@Override
	public Matchvo getLiveMatchInfo() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<EventVo> getLiveEvent() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void updateNewMatch(String time){                            //只需每天运行一次
//		Date now = new Date();
//		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
//		String current = df.format(now).substring(0, 10);
//		System.out.println(df.format(now));
//		System.out.println(current);
//		System.out.println(time);
		try {
            ProcessBuilder pb = new ProcessBuilder("python", "Spider-NBA/NBAUpdate.py",time);
            Process p = pb.start();
            File log = new File("log.txt");
            pb.redirectErrorStream(true);
            pb.redirectOutput(ProcessBuilder.Redirect.to(log));
            System.out.println(p.waitFor());

        } catch (Exception e) {
            System.out.println(e);
        }
		
	}
	
	public Playervo changePlayertoVO(player p){
		
		Playervo pvo = new 
	}

}
