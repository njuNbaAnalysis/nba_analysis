package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerDoubleDoubleComp implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		
		return o2.getDoubledouble()-o1.getDoubledouble();
	}


}
