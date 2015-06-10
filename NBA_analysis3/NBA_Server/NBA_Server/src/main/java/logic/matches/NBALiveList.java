package logic.matches;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.Future;

import po.NBALivePlayer;
import po.NBALiveTeam;
import vo.FutureMatchvo;
import data.matches.NBALiveReader;

public class NBALiveList {
	private static ArrayList<NBALivePlayer> listofPlayer;
	private static ArrayList<NBALiveTeam> listofTeam;
	private static ArrayList<FutureMatchvo> NBAMatchLiveList = new ArrayList<FutureMatchvo>();

	static {
		NBALiveReader nr = new NBALiveReader();
		listofPlayer = nr.getAllNBALivePlayer();
		listofTeam = nr.getAllNBALiveTeam();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(new File("Spider-NBA"+File.separator+"FutrueMatches"+File.separator+"FutureMatches.txt")));
			String data = br.readLine();
			while(data!=null){
				String[] temp = data.split(",");
				String[] teams = temp[2].split("-");
				NBAMatchLiveList.add(new FutureMatchvo(temp[1],temp[0] , teams[0], teams[1]));
				data = br.readLine();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static ArrayList<FutureMatchvo> getFutureMatches(String date) {
		ArrayList<FutureMatchvo> result = new ArrayList<FutureMatchvo>();
		for(int i=0;i<NBAMatchLiveList.size();i++){
			if(NBAMatchLiveList.get(i).getDate().compareTo(date)>=0){
				result.add(NBAMatchLiveList.get(i));
			}
		}
		return result;
	}

	public static String getPlayerNameById(String ID) {
		for (int i = 0; i < listofPlayer.size(); i++) {
			if (listofPlayer.get(i).getPid().equals(ID)) {
				return listofPlayer.get(i).getEname();
			}
		}
		return "";
	}

	public static String getTeamNameById(String ID) {
		for (int i = 0; i < listofTeam.size(); i++) {
			if (listofTeam.get(i).getMid().equals(ID)) {
				return listofTeam.get(i).getEname();
			}
		}
		return "";
	}
}
