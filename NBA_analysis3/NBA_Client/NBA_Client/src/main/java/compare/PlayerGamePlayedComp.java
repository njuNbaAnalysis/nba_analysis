package compare;

import java.util.Comparator;

import vo.Playervo;


public class PlayerGamePlayedComp implements Comparator<Playervo> {

	@Override
	public int compare(Playervo o1, Playervo o2) {
		int p1 = o1.getGamePlayed();
		int p2 = o2.getGamePlayed();
		if(p1>p2){
			return -1;
		}else if(p1<p2){
			return 1;
		}
		return 0;
	}

}
