package compare;

import java.util.Comparator;

import vo.Playervo;


public class PlayerDoubleDoubleComp implements Comparator<Playervo> {

	@Override
	public int compare(Playervo o1, Playervo o2) {
		
		return o2.getDoubledouble()-o1.getDoubledouble();
	}


}
