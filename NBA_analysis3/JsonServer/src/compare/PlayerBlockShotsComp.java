package compare;

import java.util.Comparator;

import vo.Playervo;



public class PlayerBlockShotsComp implements Comparator<Playervo> {

	@Override
	public int compare(Playervo o1, Playervo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getBlockShots() - o1.getBlockShots());
	}

}
