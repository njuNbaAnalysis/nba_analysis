package compare;

import java.util.Comparator;

import vo.Playervo;


public class PlayerFoulsComp implements Comparator<Playervo> {

	public int compare(Playervo o1, Playervo o2) {
		// TODO Auto-generated method stub
		return (int)(o2.getFouls() - o1.getFouls());
	}

}
