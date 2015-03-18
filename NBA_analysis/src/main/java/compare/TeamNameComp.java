package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamNameComp implements Comparator<Team> {

	public int compare(Team o1, Team o2) {
		String p1 = o1.getName();
		String p2 = o2.getName();
		
		return p1.compareTo(p2);
	}

}
