package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerBlockShotsPercentageComp implements Comparator<Playervo> {

	@Override
	public int compare(Playervo o1, Playervo o2) {
		if(o1.getBlockShotsPercentage()>o2.getBlockShotsPercentage()){
			return -1;
		}else if(o1.getBlockShotsPercentage()<o2.getBlockShotsPercentage()){
			return 1;
		}
		return 0;
	}

}
