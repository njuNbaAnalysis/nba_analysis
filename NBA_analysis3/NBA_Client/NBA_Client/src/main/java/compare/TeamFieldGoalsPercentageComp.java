package compare;

import java.util.Comparator;

import vo.Teamvo;


public class TeamFieldGoalsPercentageComp implements Comparator<Teamvo> {

	public int compare(Teamvo o1, Teamvo o2) {
		double p1 = o1.getFieldGoalsPercentage();
		double p2 = o2.getFieldGoalsPercentage();
		if (p1 > p2) {
			return -1;
		} else if (p1 < p2) {
			return 1;
		}
		return 0;
	}

}
