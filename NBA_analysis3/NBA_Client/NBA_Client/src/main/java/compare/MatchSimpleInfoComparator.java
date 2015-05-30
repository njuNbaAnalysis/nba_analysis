package compare;

import java.util.Comparator;

import logic.teams.MatchSimpleInfo;

public class MatchSimpleInfoComparator implements Comparator<MatchSimpleInfo>{

    @Override
    public int compare(MatchSimpleInfo o1, MatchSimpleInfo o2) {
        if(o1.getDate().getTime() < o2.getDate().getTime()){
            return -1;
        }
        else if(o1.getDate().getTime() > o2.getDate().getTime()){
            return 1;
        }
        return 0;
    }
}
