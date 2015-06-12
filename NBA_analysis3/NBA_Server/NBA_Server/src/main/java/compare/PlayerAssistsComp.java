package compare;

import java.util.Comparator;

import vo.Playervo;

public class PlayerAssistsComp implements Comparator<Playervo> {

	@Override
	public int compare(Playervo p1, Playervo p2) {
		// TODO Auto-generated method stub
		return p2.getAverageAssists() == p1.getAverageAssists() ? 0 : (p2
				.getAverageAssists() > p1.getAverageAssists() ? 1 : -1);
	}

}
