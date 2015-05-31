package compare;

import java.util.Comparator;

import vo.Playervo;


public class PlayerPointsComp implements Comparator<Playervo> {

	public int compare(Playervo p1,Playervo p2) {
		
		// 按得分排序
		return (int)(p2.getPoints() - p1.getPoints());
	}

}
