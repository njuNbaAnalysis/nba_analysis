package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerNameComp implements Comparator<Playervo> {

	public int compare(Playervo o1, Playervo o2) {
		String p1 = o1.getName();
		String p2 = o2.getName();
		
		return p1.compareTo(p2);
	}

}
