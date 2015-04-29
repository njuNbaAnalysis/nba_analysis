package compare;

import java.util.Comparator;

import logic.players.Player;
import logic.players.todayPlayer;

public class todayPlayerComp implements Comparator<todayPlayer>{
	String field;
	public todayPlayerComp(String s) {
		// TODO Auto-generated constructor stub
		this.field = s;
	}
	@Override
	public int compare(todayPlayer t1, todayPlayer t2) {
		// TODO Auto-generated method stub
		switch (field) {
		case "point":
		case "score":
			if(t1.getPoints()>t2.getPoints())
				return -1;
			else if(t1.getPoints()<t2.getPoints())
				return 1;
			break;
		case "assist":
			if(t1.getAssists()>t2.getAssists())
				return -1;
			else if(t1.getAssists()<t2.getAssists())
				return 1;
			break;
		case "rebound":
			if(t1.getRebounds()>t2.getRebounds())
				return -1;
			else if(t1.getRebounds()<t2.getRebounds())
				return 1;
			break;
		case "blockShot":
			if(t1.getBlockShots()>t2.getBlockShots())
				return -1;
			else if(t1.getBlockShots()<t2.getBlockShots())
				return 1;
			break;
		case "steal":
			if(t1.getSteals()>t2.getSteals())
				return -1;
			else if(t1.getSteals()<t2.getSteals())
				return 1;
			break;
		default:
			System.out.println("error in TodayPlayer.compare: " + field);
		}
		return t1.getName().compareTo(t2.getName());
	}
	
}
