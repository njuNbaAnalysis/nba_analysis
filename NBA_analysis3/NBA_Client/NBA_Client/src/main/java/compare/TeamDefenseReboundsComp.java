package compare;

import java.util.Comparator;

import vo.Teamvo;


public class TeamDefenseReboundsComp implements Comparator<Teamvo> {

	public int compare(Teamvo o1, Teamvo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getDefensiveRebounds() - o1.getDefensiveRebounds());
	}

}
