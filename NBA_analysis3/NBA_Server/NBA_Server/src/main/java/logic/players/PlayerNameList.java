package logic.players;

import java.util.ArrayList;

import data.players.PlayerNameReader;
import po.PlayerName;

public class PlayerNameList {

	private static PlayerNameList playerNameList = null;
	private PlayerNameReader nameReader;
	private ArrayList<PlayerName> namelist;

	private PlayerNameList() {
		nameReader = new PlayerNameReader();
		namelist = nameReader.getAllPlayerName();
	}

	public static PlayerNameList getIntance() {
		if (playerNameList == null) {
			playerNameList = new PlayerNameList();
		}
		return playerNameList;
	}

	public String getEnAbbrById(String Pid) {
		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).getPid().equals(Pid))
				return namelist.get(i).getEname();
		}
		return null;
	}

	public String getIdByEnAbbr(String EnAbbr) {
		for (int i = 0; i < namelist.size(); i++) {
			if (namelist.get(i).getEname().equals(EnAbbr))
				return namelist.get(i).getPid();
		}
		return null;
	}
}
