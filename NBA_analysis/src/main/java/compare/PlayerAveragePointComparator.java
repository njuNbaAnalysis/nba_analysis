package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerAveragePointComparator implements Comparator<Player>{
	public int compare(Player p1, Player p2) {
	 	if (Math.abs(p1.getAveragePoints()-p2.getAveragePoints())<0.00001){
	 		return 0;
	 	}else if(p1.getAveragePoints()>p2.getAveragePoints()){
	 		return -1;
	 	}else{
	 		return 1;
	 	}

		// 按得分排序
		//return (int)(p2.getAveragePoints() - p1.getAveragePoints());
		//return (int)(p2.getPoints() - p1.getPoints());
	 	//return 0;
	}
}
