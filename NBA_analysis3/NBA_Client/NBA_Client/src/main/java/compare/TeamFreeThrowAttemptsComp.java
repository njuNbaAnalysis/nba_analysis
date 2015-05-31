package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamFreeThrowAttemptsComp implements Comparator<Teamvo> {

	@Override
	public int compare(Teamvo o1, Teamvo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getFreeThrows() - o1.getFreeThrows());
	}

}
