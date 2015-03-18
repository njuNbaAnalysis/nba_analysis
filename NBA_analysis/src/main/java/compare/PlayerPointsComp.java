package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerPointsComp implements Comparator<Player> {

	public int compare(Player p1,Player p2) {
		
		// 按得分排序
		return (int)(p2.getPoints() - p1.getPoints());
	}

}
