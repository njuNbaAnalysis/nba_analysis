package compare;

import java.util.Comparator;

import logic.matches.Match;

public class MatchComp implements Comparator<Match>{

	@Override
	public int compare(Match o1, Match o2) {
		// TODO Auto-generated method stub
		return o1.getDate().compareTo(o2.getDate());
	}
	
}
