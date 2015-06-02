package BLservice;

import java.util.ArrayList;

import po.TeamListItem;
import data.teams.TeamNameListData;

public class teamBLservice {
    //for debug only
    public static void main(String[] args){
        TeamListItem item = new TeamListItem();
        item.setConference("eastern");
        item.setTeamNameEn("ATL");
        
        TeamNameListData data = new TeamNameListData();
        data.init();
        ArrayList<TeamListItem> list = data.getTeamList(item);
        data.finish();
        
        System.out.println(list.size() );
    }
}
