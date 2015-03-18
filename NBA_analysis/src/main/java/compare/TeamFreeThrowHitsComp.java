package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamFreeThrowHitsComp implements Comparator<Team> {

	@Override
	public int compare(Team o1, Team o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getFreeThrowHits() - o1.getFreeThrowHits());
	}

}
