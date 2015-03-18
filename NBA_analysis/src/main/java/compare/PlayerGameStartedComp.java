package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerGameStartedComp implements Comparator<Player> {

	public int compare(Player o1, Player o2) {
		int p1 = o1.getGameStarted();
		int p2 = o2.getGameStarted();
		if(p1>p2){
			return -1;
		}else if(p1<p2){
			return 1;
		}
		return 0;
	}

}
