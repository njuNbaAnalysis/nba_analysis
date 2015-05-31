package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamOffenseReboundsComp implements Comparator<Teamvo> {

	@Override
	public int compare(Teamvo o1, Teamvo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getOffensiveRebounds() - o1.getOffensiveRebounds());
	}

}
