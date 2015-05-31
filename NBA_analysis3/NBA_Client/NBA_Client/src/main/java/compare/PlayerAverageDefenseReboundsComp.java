package compare;

import java.util.Comparator;

import vo.Playervo;



public class PlayerAverageDefenseReboundsComp implements Comparator<Playervo> {

	public int compare(Playervo o1, Playervo o2) {
		double p1 = o1.getAverageDefenseRebounds();
		double p2 = o2.getAverageDefenseRebounds();
		if(p1>p2){
			return -1;
		}else if(p1<p2){
			return 1;
		}
		return 0;
	}

}
