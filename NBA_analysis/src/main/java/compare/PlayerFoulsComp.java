package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerFoulsComp implements Comparator<Player> {

	public int compare(Player o1, Player o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getFouls() - o1.getFouls());
	}

}
