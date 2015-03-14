package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamAveragePointComparator implements Comparator {
	public int compare(Object op1, Object op2) {
		Team p1 = (Team) op1;
		Team p2 = (Team) op2;

		// 按得分排序
		return (int)(p2.getAveragePoints() - p1.getAveragePoints());
	}
}
