package compare;

import java.util.Comparator;

import vo.Teamvo;

public class TeamPointsComp implements Comparator<Teamvo>{
	public int compare(Teamvo p1, Teamvo p2) {

		// 按得分排序
		return (int)(p2.getPoints() - p1.getPoints());
	}

}
