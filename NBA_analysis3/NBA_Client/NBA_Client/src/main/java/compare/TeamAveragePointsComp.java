package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamAveragePointsComp implements Comparator<Teamvo> {
	public int compare(Teamvo o1, Teamvo o2) {
		double t1 = o1.getAveragePoints();
		double t2 = o2.getAveragePoints();

		if (t1 > t2) {
			return -1;
		} else if (t1 < t2) {
			return 1;
		}
		return 0;

		// 按得分排序

	}
}
