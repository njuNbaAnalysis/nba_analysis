package compare;

import java.util.Comparator;

import vo.Playervo;

public class PlayerTeamNameComp implements Comparator<Playervo> {

	public int compare(Playervo o1, Playervo o2) {
		if(o1.getTeam()==null){
			return -1;
		}else if(o2.getTeam()==null){
			return 1;
		}
		return o1.getTeam().compareTo(o2.getTeam());
	}

}
