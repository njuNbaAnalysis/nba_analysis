package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerAveragePointComparator implements Comparator{
	public int compare(Object op1, Object op2) {
		Player p1 = (Player) op1;
		Player p2 = (Player) op2;

		// 按得分排序
		return (int)(p2.getAveragePoints() - p1.getAveragePoints());
	}
}
