package compare;

import java.util.Comparator;

import logic.players.Player;
import vo.PlayerVo;

public class PlayerPointComparator implements Comparator {

	public int compare(Object op1, Object op2) {
		Player p1 = (Player) op1;
		Player p2 = (Player) op2;

		// 按得分排序
		return (p2.getPoints() - p1.getPoints());
	}

}
