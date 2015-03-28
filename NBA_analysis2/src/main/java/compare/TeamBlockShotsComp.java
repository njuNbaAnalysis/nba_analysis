package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamBlockShotsComp implements Comparator<Team> {

	public int compare(Team o1, Team o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getBlockShots() - o1.getBlockShots());
	}

}
