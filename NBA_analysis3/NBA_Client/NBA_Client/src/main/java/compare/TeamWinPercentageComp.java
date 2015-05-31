package compare;

import java.util.Comparator;

import vo.Teamvo;

//在TeamController中也被复用
//本排序为降序排列!!!
public class TeamWinPercentageComp implements Comparator<Teamvo> {
	public int compare(Teamvo o1, Teamvo o2) {
		double p1 = o1.getWinningPercentage();
		double p2 = o2.getWinningPercentage();
		if (p1 > p2) {
			return -1;
		} else if (p1 < p2) {
			return 1;
		}
		return 0;
	}
}
