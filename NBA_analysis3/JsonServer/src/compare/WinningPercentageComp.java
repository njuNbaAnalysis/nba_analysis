package compare;

import java.util.Comparator;

import po.TeamRecordItem;

public class WinningPercentageComp  implements Comparator<TeamRecordItem>{

    @Override
    public int compare(TeamRecordItem o1, TeamRecordItem o2) {
        double winPct1 = 1.0* o1.getNumOfVictory() / (o1.getNumOfFailure() + o1.getNumOfVictory());
        double winPct2 = 1.0* o2.getNumOfVictory() / (o2.getNumOfFailure() + o2.getNumOfVictory());
        //降序排列
        if(winPct1 > winPct2){
            return -1;
        }
        else if(winPct1 < winPct2){
            return 1;
        }
        else{
            return 0;
        }
    }

}
