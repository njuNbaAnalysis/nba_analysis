package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamFieldGoalHitsComp implements Comparator<Team> {

	@Override
	public int compare(Team o1, Team o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getFieldGoalHits() - o1.getFieldGoalHits());
	}

}
