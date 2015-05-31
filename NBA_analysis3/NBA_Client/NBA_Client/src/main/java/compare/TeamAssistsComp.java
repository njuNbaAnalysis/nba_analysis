package compare;

import java.util.Comparator;

import vo.Teamvo;

public class TeamAssistsComp implements Comparator<Teamvo> {

	public int compare(Teamvo o1, Teamvo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getAssists() - o1.getAssists());
	}

}
