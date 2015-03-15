package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamPointComparator implements Comparator<Team>{
	public int compare(Team p1, Team p2) {

		// 按得分排序
		return (int)(p2.getPoints() - p1.getPoints());
	}

}
