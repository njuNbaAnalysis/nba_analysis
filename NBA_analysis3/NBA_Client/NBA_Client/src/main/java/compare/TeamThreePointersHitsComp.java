package compare;

import java.util.Comparator;

import vo.Teamvo;


public class TeamThreePointersHitsComp implements Comparator<Teamvo> {

	@Override
	public int compare(Teamvo o1, Teamvo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getThreePointerHits() - o1.getThreePointerHits());
	}

}
