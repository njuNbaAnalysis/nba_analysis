package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerFieldGoalsPercentageComp implements Comparator<Playervo> {

	public int compare(Playervo o1, Playervo o2) {
		double p1 = o1.getFieldGoalsPercentage();
		double p2 = o2.getFieldGoalsPercentage();
		if (p1 > p2) {
			return -1;
		} else if (p1 < p2) {
			return 1;
		}
		return 0;
	}

}
