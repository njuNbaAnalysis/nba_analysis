package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamBlockShotsComp implements Comparator<Teamvo> {

	public int compare(Teamvo o1, Teamvo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getBlockShots() - o1.getBlockShots());
	}

}
