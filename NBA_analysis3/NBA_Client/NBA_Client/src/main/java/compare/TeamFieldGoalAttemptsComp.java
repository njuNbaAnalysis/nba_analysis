package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamFieldGoalAttemptsComp implements Comparator<Team> {

	public int compare(Team o1, Team o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getFieldGoalAttemps() - o1.getFieldGoalAttemps());
	}

}
