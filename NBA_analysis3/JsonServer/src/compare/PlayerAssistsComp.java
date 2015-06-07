package compare;

import java.util.Comparator;

import vo.Playervo;



public class PlayerAssistsComp implements Comparator<Playervo> {

	@Override
	public int compare(Playervo o1, Playervo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getAssists() - o1.getAssists());
	}

}
