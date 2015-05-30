package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamOffenseReboundsComp implements Comparator<Team> {

	@Override
	public int compare(Team o1, Team o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getOffensiveRebounds() - o1.getOffensiveRebounds());
	}

}
