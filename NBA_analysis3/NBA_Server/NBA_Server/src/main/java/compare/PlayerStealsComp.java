package compare;

import java.util.Comparator;

import vo.Playervo;

public class PlayerStealsComp implements Comparator<Playervo> {

	@Override
	public int compare(Playervo p1, Playervo p2) {
		// TODO Auto-generated method stub
		return p2.getAverageSteals()  == p1.getAverageSteals() ? 0 : (p2
				.getAverageSteals()  > p1.getAverageSteals() ? 1 : -1);
	}
}
