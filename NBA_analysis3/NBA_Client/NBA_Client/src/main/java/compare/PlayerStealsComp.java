package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerStealsComp implements Comparator<Playervo> {

	public int compare(Playervo o1, Playervo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getSteals() - o1.getSteals());
	}

}
