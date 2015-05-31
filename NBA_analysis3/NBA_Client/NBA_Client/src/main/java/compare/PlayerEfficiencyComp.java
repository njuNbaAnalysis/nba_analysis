package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerEfficiencyComp implements Comparator<Playervo> {

	public int compare(Playervo o1, Playervo o2) {
		double p1 = o1.getEfficiency();
		double p2 = o2.getEfficiency();
		if (p1 > p2) {
			return -1;
		} else if (p1 < p2) {
			return 1;
		}
		return 0;
	}

}
