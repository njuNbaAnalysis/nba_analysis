package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerDefenseReboundsComp implements Comparator<Playervo> {

	@Override
	public int compare(Playervo o1, Playervo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getDefenseRebounds() - o1.getDefenseRebounds());
	}

}
