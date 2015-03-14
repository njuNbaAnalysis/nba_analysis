package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamPointComparator implements Comparator{
	public int compare(Object op1, Object op2) {
		Team p1 = (Team) op1;
		Team p2 = (Team) op2;

		// 按得分排序
		return (p2.getPoints() - p1.getPoints());
	}

}
