package logic.teams;

import java.util.ArrayList;
import java.util.HashMap;

import data.teams.TeamRecordItemData;
import po.TeamRecordItem;
import util.Tools;

public class TeamRecordItemList {
    private static TeamRecordItemList teamRecordItemList = null;
    private ArrayList<TeamRecordItem> recordItemList = new ArrayList<TeamRecordItem>();
    //private HashMap<TeamRecordItemKey,TeamRecordItem> recordItemList = new HashMap<TeamRecordItemKey,TeamRecordItem>();
    
    private TeamRecordItemList(){
        load("14-15",false);
        load("14-15",true);
    }
    
    public static TeamRecordItemList getInstance(){
        if(teamRecordItemList == null){
            teamRecordItemList = new TeamRecordItemList();
        }
        return teamRecordItemList;
    }
    
    /**
     * 加载某个赛季的常规赛或季后赛的条目
     * @param season
     * @param isPlayOff
     */
    private void load(String season,boolean isPlayOff){
        TeamRecordItemData teamRecordItemData = new TeamRecordItemData();
        teamRecordItemData.init();
        
        TeamRecordItem para = new TeamRecordItem();
        para.setSeason(!isPlayOff);
        para.setBeginYear(Tools.xx_xxToxxxx(season));
        recordItemList.addAll(teamRecordItemData.getTeamRecords(para));
        
        //System.out.println("size:" + recordItemList.size());
    }
    
    private void loadIfNotHas(String season,boolean isPlayOff){
        boolean has = false;
        for(TeamRecordItem token:recordItemList){
            if(token.getBeginYear().equals(Tools.xx_xxToxxxx(season))
                    && token.isSeason() != isPlayOff){
                has = true;
                break;
            }
        }
        
        if(!has){
            System.out.println("load: season:" + season + " isPlayOff:" + isPlayOff);
            load(season,isPlayOff);
        }
    }
    
/*    public ArrayList<TeamRecordItem> getRecordItemList(TeamRecordItemKey key){
        ArrayList<TeamRecordItem> resultList = new ArrayList<TeamRecordItem>();
        //recordItemList.get
        return null;
    }*/
    
    public ArrayList<String> getPlayerIdList(String teamNameEn, String season, boolean isPlayOff){
        ArrayList<String> resultList = new ArrayList<String>();
        
        loadIfNotHas(season,isPlayOff);
        
        for(TeamRecordItem token:recordItemList){
            if(token.getTeamNameEn().equals(teamNameEn)
                    && token.getDataType().equals("playerItem")
                    && token.getBeginYear().equals(Tools.xx_xxToxxxx(season))
                    && token.isSeason() != isPlayOff){
                resultList.add(token.getPlayerId());
            }
        }
        
        return resultList;
    }

    public ArrayList<TeamRecordItem> getRecordItemList() {
        return recordItemList;
    }

    public ArrayList<TeamRecordItem> getRecordItemList(TeamRecordItem item) {
        ArrayList<TeamRecordItem> resultList = new ArrayList<TeamRecordItem>();
        
        loadIfNotHas(Tools.xxxxToxx_xx(item.getBeginYear()),!item.isSeason());
        
        for(TeamRecordItem token:recordItemList){
            if(token.equals(item)){
                resultList.add(token);
            }
        }
        
        return resultList;
    }

    /**
     * 得到此赛季，常规赛或季候赛所有的球队名称的集合
     * @param season
     * @param isPlayOff
     * @return
     */
    public ArrayList<String> getAllTeamNameEns(String season,boolean isPlayOff){
        
        ArrayList<String> resultList = new ArrayList<String>();
        
        loadIfNotHas(season,isPlayOff);
        
        for(TeamRecordItem item:recordItemList){
            //System.out.println(item);
            if(item.getDataType().equals("teamItem")
                    && item.getBeginYear().equals(Tools.xx_xxToxxxx(season))
                    && item.isSeason() != isPlayOff){
                resultList.add(item.getTeamNameEn());
            }
        }
        
        return resultList;
    }
}
