package compare;

import java.util.Comparator;

import vo.Playervo;

public class PlayerStealsComp implements Comparator<Playervo> {

	@Override
	public int compare(Playervo o1, Playervo o2) {
		// TODO Auto-generated method stub
		return (int) (o2.getAverageSteals() - o1.getAverageSteals());
	}
}
