package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerGamePlayedComp implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		int p1 = o1.getGamePlayed();
		int p2 = o2.getGamePlayed();
		if(p1>p2){
			return -1;
		}else if(p1<p2){
			return 1;
		}
		return 0;
	}

}
