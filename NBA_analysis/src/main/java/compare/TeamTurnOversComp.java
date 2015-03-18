package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamTurnOversComp implements Comparator<Team> {

	@Override
	public int compare(Team o1, Team o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getTurnOver() - o1.getTurnOver());
	}

}
