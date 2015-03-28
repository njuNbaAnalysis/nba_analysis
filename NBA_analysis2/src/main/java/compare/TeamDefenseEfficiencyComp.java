package compare;

import java.util.Comparator;

import logic.teams.Team;


public class TeamDefenseEfficiencyComp implements Comparator<Team> {
	public int compare(Team o1, Team o2) {
		double p1 = o1.getDefenseEfficiency();
		double p2 = o2.getDefenseEfficiency();
		if (p1 > p2) {
			return -1;
		} else if (p1 < p2) {
			return 1;
		}
		return 0;
	}
}
