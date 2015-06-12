package compare;

import java.util.Comparator;

import vo.Playervo;

public class PlayerBlockShotsComp implements Comparator<Playervo> {

	@Override
	public int compare(Playervo p1, Playervo p2) {
		// TODO Auto-generated method stub
		return p2.getAverageBlockShots() == p1.getAverageBlockShots() ? 0 : (p2
				.getAverageBlockShots() > p1.getAverageBlockShots() ? 1 : -1);
	}

}
