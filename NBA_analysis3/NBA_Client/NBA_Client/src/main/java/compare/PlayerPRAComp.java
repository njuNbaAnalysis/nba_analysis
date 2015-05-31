package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerPRAComp implements Comparator<Playervo> {

	@Override
	public int compare(Playervo o1, Playervo o2) {
		
		return o2.getPRA()-o1.getPRA();
	}

}
