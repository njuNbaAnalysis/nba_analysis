package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamAverageStealsComp implements Comparator<Teamvo> {

	public int compare(Teamvo o1, Teamvo o2) {
		double p1 = o1.getAverageSteals();
		double p2 = o2.getAverageSteals();
		if (p1 > p2) {
			return -1;
		} else if (p1 < p2) {
			return 1;
		}
		return 0;
	}

}
