package logic.teams;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import po.HotZone;
import po.TeamListItem;
import po.TeamRecordItem;
import util.Tools;
import vo.HotZonevo;
import vo.Teamvo;
import vo.HotZonevo.Data;
import data.teams.HotZoneData;
import data.teams.TeamRecordItemData;

public class TeamController implements Serializable{
    private static TeamController teamController = null;
    
    private TeamController(){}
    
    public static TeamController getInstance(){
        if(teamController == null){
            teamController = new TeamController();
        }
        
        return teamController;
    }
    
    /**
     * @param teamNameEn 球队的3大写字母名称
     * @param isSeason true表示常规赛，false表示季后赛
     * @param isTotal 此项为true时，返回所有球队的热区总计数据，此时teamNameEn项被忽略
     * @return
     */
    public HotZonevo getHotZone(String teamNameEn, boolean isSeason, boolean isTotal) {
        HotZonevo vo = new HotZonevo();
        
        //得到符合条件的hotZone list
        HotZoneData data = new HotZoneData();
        data.init();
        HotZone para = new HotZone();
        para.setTeamNameEn(teamNameEn);
        para.setSeason(isSeason);
        para.setTotal(isTotal);
        ArrayList<HotZone> list = data.getHotZones(para);
        
        vo.setTotal(isTotal);
        vo.setSeason(isSeason);
        vo.setTeamNameEn(teamNameEn);
        
        //对所有的hashMap赋值
        HashMap<String,Data> last5 = new HashMap<String,Data>();
        HashMap<String,Data> total = new HashMap<String,Data>();
        
        for(HotZone hotZone:list){
            if(hotZone.getType().equals("last5")){
                last5.put(hotZone.getZone(), vo.new Data(hotZone.getAttempted(), hotZone.getMade(), hotZone.getPct(), hotZone.getDisPct()));
            }
            else{
                total.put(hotZone.getZone(), vo.new Data(hotZone.getAttempted(), hotZone.getMade(), hotZone.getPct(), hotZone.getDisPct()));
            }
        }
        vo.setLast5(last5);
        vo.setTotal(total);
        
        return vo;
    }
    
    /**
     * 得到赛季的所有球队
     * 
     * @param Season 赛季名，例如"12-13"
     * @param isPlayOff true为季后赛，false为常规赛
     */
    public ArrayList<Teamvo> getAllTeams(String season, boolean isPlayOff){
        TeamNameList teamNameList = TeamNameList.getIntance();
        ArrayList<Teamvo> resultList = new ArrayList<Teamvo>();
        TeamvoGenerator generator = TeamvoGenerator.getInstance();
        for(TeamListItem item:teamNameList.getTeamList()){
            resultList.add(generator.getTeamvo(item.getTeamNameEn(), season, isPlayOff)); 
        }
        return resultList; 
    }
    
    /**
     * 
     * @param teamName
     * @return teamvo
     */
    public Teamvo getTeamByTeamName(String teamNameEn, String season, boolean isPlayOff){
        return TeamvoGenerator.getInstance().getTeamvo(teamNameEn, season, isPlayOff);
    }

    public Teamvo getTeamByPlayerId(String playerId, String season, boolean isPlayOff){

        TeamRecordItemData teamRecordItemData = new TeamRecordItemData();
        teamRecordItemData.init();
        TeamRecordItem playerItemPara = new TeamRecordItem();
        playerItemPara.setDataType("playerItem");
        playerItemPara.setPlayerId(playerId);
        playerItemPara.setSeason(isPlayOff?false:true);
        playerItemPara.setBeginYear(Tools.xx_xxToxxxx(season));
        ArrayList<TeamRecordItem> resultList = teamRecordItemData.getTeamRecords(playerItemPara);
        
        String teamNameEn = resultList.get(0).getTeamNameEn();
        Teamvo vo = TeamvoGenerator.getInstance().getTeamvo(teamNameEn, season, isPlayOff);
        
        return vo;
    }
}
