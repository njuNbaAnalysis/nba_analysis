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
    
    /**
     * 根据中文全名得到英文缩写
     * @param fullZh
     * @return 未找到返回null
     */
    public String getEnAbbrByFullZh(String fullZh){
        for(TeamListItem item:teamList){
            if(item.getTeamNameZh().equals(fullZh)){
                return item.getTeamNameEn();
            }
        }
        return null;
    }
    
    /**
     * 根据中文缩写得到英文缩写
     * @param zhAbbr
     * @return 未找到返回null
     */
    public String getEnAbbrByZhAbbr(String zhAbbr,String season){
        if(zhAbbr.equals("篮网")){
            if(season.equals("11-12")){
                return "NJN";
            }
            else{
                return "BKN";
            }
        }
        
        for(TeamListItem item:teamList){
            if(item.getTeamNameZhAbbr().equals(zhAbbr)){
                return item.getTeamNameEn();
            }
        }
        return null;
    }
    
    /**
     * 根据英文缩写得到中文全名
     * @param enAbbr
     * @return 未找到返回null
     */
    public String getFullZhByEnAbbr(String enAbbr){
        for(TeamListItem item:teamList){
            if(item.getTeamNameEn().equals(enAbbr)){
                return item.getTeamNameZh();
            }
        }
        return null;
    }
    
    /**
     * 根据英文缩写得到中文缩写
     * @param enAbbr
     * @return 未找到返回null
     */
    public String getZhAbbrByEnAbbr(String enAbbr){
        for(TeamListItem item:teamList){
            if(item.getTeamNameEn().equals(enAbbr)){
                return item.getTeamNameZhAbbr();
            }
        }
        return null;
    }
    
    /**
     * 如果没有则返回null
     * @param teamNameEn
     * @return
     */
    public TeamListItem getTeamListItem(String teamNameEn){
        for(TeamListItem item:teamList){
            if(item.getTeamNameEn().equals(teamNameEn)){
                return item;
            }
        }
        return null;
    }
}
