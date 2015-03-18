package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerDefenseReboundsComp implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getDefenseRebounds() - o1.getDefenseRebounds());
	}

}
