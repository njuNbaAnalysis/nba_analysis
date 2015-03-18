package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerBlockShotsPercentageComp implements Comparator<Player> {

	@Override
	public int compare(Player o1, Player o2) {
		if(o1.getBlockShotsPercentage()>o2.getBlockShotsPercentage()){
			return -1;
		}else if(o1.getBlockShotsPercentage()<o2.getBlockShotsPercentage()){
			return 1;
		}
		return 0;
	}

}
