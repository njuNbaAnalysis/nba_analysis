package compare;

import java.util.Comparator;

import logic.players.Player;
import vo.PlayerVo;

public class PlayerPointComparator implements Comparator<Player> {

	public int compare(Player p1,Player p2) {
		if(p1==p2){
			return 0;
		}
		// 按得分排序
		return (int)(p2.getPoints() - p1.getPoints());
	}

}
