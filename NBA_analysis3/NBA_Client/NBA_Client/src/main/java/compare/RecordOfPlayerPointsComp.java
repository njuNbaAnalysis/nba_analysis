package compare;

import java.util.Comparator;

import vo.RecordOfPlayervo;


public class RecordOfPlayerPointsComp implements Comparator<RecordOfPlayervo> {

	public int compare(RecordOfPlayervo p1,RecordOfPlayervo p2) {
		
		// 按得分排序
		return (int)(p2.getPoints() - p1.getPoints());
	}

}
