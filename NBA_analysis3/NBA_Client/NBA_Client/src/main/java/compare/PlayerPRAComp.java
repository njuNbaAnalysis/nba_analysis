package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerPRAComp implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		
		return o2.getPRA()-o1.getPRA();
	}

}
