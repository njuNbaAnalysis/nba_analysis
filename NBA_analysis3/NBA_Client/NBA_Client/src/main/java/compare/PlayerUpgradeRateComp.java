package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerUpgradeRateComp implements Comparator<Playervo> {
	int filed = 0;
	public PlayerUpgradeRateComp(int num){
		this.filed = num;
	}
	public int compare(Playervo o1, Playervo o2) {
		double p1 = o1.getUpgradeRate()[filed];
		double p2 = o2.getUpgradeRate()[filed];
		if(Math.abs(p1-p2)<=0.000-01){
			return 0;
		}else if (p1 > p2) {
			return -1;
		} else if (p1 < p2) {
			return 1;
		}
		return 0;
	}
}
