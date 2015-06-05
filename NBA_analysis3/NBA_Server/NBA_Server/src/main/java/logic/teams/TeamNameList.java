package logic.teams;

import java.util.ArrayList;

import data.teams.TeamNameListData;
import po.TeamListItem;

/**
 * 此类用作对teamnamelist表的缓存，用于减少对数据库的直接查询
 * 封装对teamNamelist的查询
 * @author Lionel
 *
 */
public class TeamNameList {
    private static TeamNameList teamNameList = null;
    private ArrayList<TeamListItem> teamList = new ArrayList<TeamListItem>();
    
    private TeamNameList(){
        load();
    }
    
    public static TeamNameList getIntance(){
        if(teamNameList == null){
            teamNameList = new TeamNameList();
        }
        return teamNameList;
    }

    private void load(){
        TeamNameListData data = new TeamNameListData();
        data.init();
        
        TeamListItem teamListItem = new TeamListItem();
        teamList = data.getTeamList(teamListItem);
    }

    public boolean isEastern(String teamNameEn){
        boolean result = false;
        
        for(TeamListItem token:teamList){
            if(token.getTeamNameEn().equals(teamNameEn)){
                if(token.getConference().equals("eastern")){
                    result = true;
                }
                
                break;
            }
        }
        
        return result;
    }

    public ArrayList<TeamListItem> getTeamList(){
        return teamList;
    }
}
