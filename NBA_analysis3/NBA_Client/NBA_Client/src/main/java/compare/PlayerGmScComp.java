package compare;

import java.util.Comparator;

import vo.Playervo;


public class PlayerGmScComp implements Comparator<Playervo> {

	public int compare(Playervo o1, Playervo o2) {
		double p1 = o1.getGmsc();
		double p2 = o2.getGmsc();
		if (p1 > p2) {
			return -1;
		} else if (p1 < p2) {
			return 1;
		}
		return 0;
	}

}
