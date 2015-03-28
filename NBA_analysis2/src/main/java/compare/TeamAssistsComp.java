package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamAssistsComp implements Comparator<Team> {

	public int compare(Team o1, Team o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getAssists() - o1.getAssists());
	}

}
