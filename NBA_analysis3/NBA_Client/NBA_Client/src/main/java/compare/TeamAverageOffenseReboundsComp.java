package compare;

import java.util.Comparator;

import logic.teams.Team;

public class TeamAverageOffenseReboundsComp implements Comparator<Teamvo> {

	public int compare(Teamvo o1, Teamvo o2) {
		double p1 = o1.getAverageOffenseRebounds();
		double p2 = o2.getAverageOffenseRebounds();
		if(p1>p2){
			return -1;
		}else if(p1<p2){
			return 1;
		}
		return 0;
	}

}
