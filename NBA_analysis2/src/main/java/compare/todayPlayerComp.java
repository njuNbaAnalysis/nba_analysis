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
			if(t1.getAveragePoints()>t2.getAveragePoints())
				return 1;
			else if(t1.getAveragePoints()<t2.getAveragePoints())
				return -1;
			break;
		case "assist":
			if(t1.getAverageAssists()>t2.getAverageAssists())
				return 1;
			else if(t1.getAverageAssists()<t2.getAverageAssists())
				return -1;
			break;
		case "rebound":
			if(t1.getAverageRebounds()>t2.getAverageRebounds())
				return 1;
			else if(t1.getAverageRebounds()<t2.getAverageRebounds())
				return -1;
			break;
		default:
			System.out.println("error in TodayPlayer.compare: " + field);
		}
		return 0;
	}
	
}
