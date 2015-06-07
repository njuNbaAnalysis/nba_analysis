package logic.matches;

import java.util.ArrayList;
import java.util.HashMap;

import po.NBALivePlayer;
import po.NBALiveTeam;
import data.matches.NBALiveReader;

public class NBALiveList {
	private static ArrayList<NBALivePlayer> listofPlayer;
	private static ArrayList<NBALiveTeam> listofTeam;
	static HashMap<String, String> NBAMatchLiveList = new HashMap<String, String>();
	static{
		NBAMatchLiveList.put("2015-06-05", "0041400401");
		NBAMatchLiveList.put("2015-06-08", "0041400402");
		NBAMatchLiveList.put("2015-06-10", "0041400403");
		NBAMatchLiveList.put("2015-06-12", "0041400404");
		NBAMatchLiveList.put("2015-06-15", "0041400405");
		NBAMatchLiveList.put("2015-06-17", "0041400406");
		NBAMatchLiveList.put("2015-06-20", "0041400407");
		NBALiveReader nr = new NBALiveReader();
		listofPlayer = nr.getAllNBALivePlayer();
		listofTeam = nr.getAllNBALiveTeam();
	}
	public static String getPlayerNameById(String ID){
		for(int i=0;i<listofPlayer.size();i++){
			if(listofPlayer.get(i).getPid().equals(ID)){
				return listofPlayer.get(i).getEname();
			}
		}
		return "";
	}
	public static String getTeamNameById(String ID){
		for(int i=0;i<listofTeam.size();i++){
			if(listofTeam.get(i).getMid().equals(ID)){
				return listofTeam.get(i).getEname();
			}
		}
		return "";
	}
}
