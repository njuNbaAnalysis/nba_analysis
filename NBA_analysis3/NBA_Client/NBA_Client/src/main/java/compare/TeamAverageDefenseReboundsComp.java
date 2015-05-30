package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamAverageDefenseReboundsComp implements Comparator<Team> {

	public int compare(Team o1, Team o2) {
		double p1 = o1.getAverageDefenseRebounds();
		double p2 = o2.getAverageDefenseRebounds();
		if(p1>p2){
			return -1;
		}else if(p1<p2){
			return 1;
		}
		return 0;
	}

}
