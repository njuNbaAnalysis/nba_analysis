package logic.teams;

import java.util.ArrayList;
import java.util.HashMap;

import po.HotZone;
import vo.HotZonevo;
import vo.HotZonevo.Data;
import data.teams.HotZoneData;

public class TeamController {
    private static TeamController teamController = null;
    
    private TeamController(){
        
    }
    
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
    
    
}
