package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamAveragePointsComp implements Comparator<Team> {
	public int compare(Team o1, Team o2) {
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
