package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerAssistsPercentageComp implements Comparator<Player> {


	@Override
	public int compare(Player o1, Player o2) {
		double p1 = o1.getAssistsPercentage();
		double p2 = o2.getAssistsPercentage();
		if(p1>p2){
			return -1;
		}else if(p1<p2){
			return 1;
		}
		return 0;
	}

}
