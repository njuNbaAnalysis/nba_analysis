package compare;

import java.util.Comparator;

import logic.players.Player;

public class PlayerTeamNameComp implements Comparator<Player> {

	public int compare(Player o1, Player o2) {
		if(o1.getTeam()==null){
			return -1;
		}else if(o2.getTeam()==null){
			return 1;
		}
		return o1.getTeam().compareTo(o2.getTeam());
	}

}
