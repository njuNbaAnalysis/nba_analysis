package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerGameStartedComp implements Comparator<Playervo> {

	public int compare(Playervo o1, Playervo o2) {
		int p1 = o1.getGameStarted();
		int p2 = o2.getGameStarted();
		if(p1>p2){
			return -1;
		}else if(p1<p2){
			return 1;
		}
		return 0;
	}

}
