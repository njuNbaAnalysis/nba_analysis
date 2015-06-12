package compare;

import java.util.Comparator;

import vo.Playervo;

public class PlayerReboundsComp implements Comparator<Playervo> {

	public int compare(Playervo p1, Playervo p2) {
		// TODO Auto-generated method stub
		return p2.getAverageRebounds() == p1.getAverageRebounds() ? 0 : (p2
				.getAverageRebounds() > p1.getAverageRebounds() ? 1 : -1);
	}

}
