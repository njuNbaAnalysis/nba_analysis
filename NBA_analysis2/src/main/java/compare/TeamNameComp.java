package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamNameComp implements Comparator<Team> {

	public int compare(Team o1, Team o2) {
		
		String p1 = o1.getName();
		String p2 = o2.getName();
		if(p1==null){
			return -1;
		}else if(p2==null){
			return 1;
		}
		return p1.compareTo(p2);
	}

}
