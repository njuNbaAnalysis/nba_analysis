package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamTurnOversComp implements Comparator<Teamvo> {

	@Override
	public int compare(Teamvo o1, Teamvo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getTurnOver() - o1.getTurnOver());
	}

}
