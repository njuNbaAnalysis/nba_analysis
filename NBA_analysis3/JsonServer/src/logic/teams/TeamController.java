package logic.teams;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import logic.players.playerBLcontrollor;
import po.HotZone;
import po.TeamRecordItem;
import util.Tools;
import vo.HotZonevo;
import vo.HotZonevo.Data;
import vo.Playervo;
import vo.Teamvo;
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
        ArrayList<Teamvo> resultList = new ArrayList<Teamvo>();
        
        TeamvoGenerator generator = TeamvoGenerator.getInstance();
        ArrayList<String> nameList = TeamRecordItemList.getInstance().getAllTeamNameEns(season, isPlayOff);
        for(String token:nameList){
            resultList.add(generator.getTeamvo(token, season, isPlayOff)); 
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

    /**
     * in abeyance,waiting for the formulas
     * 
     * @param teamNameEn  三个大写英文字母
     * @param season  赛季数 例如：13-14赛季
     * @param isplayoff  是否为季后赛
     * @return 内线(中锋加上大前锋能力综合(综合能力即为playervo中的getREP()))、外线(其他位置能力综合(综合能力即为playervo中的getREP()))、
     * 内线外线值强行保证不大于10
     * 配合（场均助攻命中（除去罚球命中数）比）、进攻（平均得分）、防守（平均失分）
     *
     * @throws RemoteException  rmi服务器连接异常
     */
    public double[] getTeamAbility(String teamNameEn,String season,boolean isplayoff) throws RemoteException {
        TeamvoGenerator generator = TeamvoGenerator.getInstance();
        Teamvo vo = generator.getTeamvo(teamNameEn, season, isplayoff);
        
        ArrayList<Playervo> playerList = playerBLcontrollor.getInstance().getAllPlayers(season, isplayoff);
        ArrayList<String> playerIdList = vo.getPlayerList();
        
        double forward = getAbility(playerList,playerIdList,"前锋");
        double center = getAbility(playerList,playerIdList,"中锋");
        double guard = getAbility(playerList,playerIdList,"后卫");
        
        double[] result = new double[5];
        result[0] = forward + center;
        result[1] = center + guard;
        if(result[0] >= 10){
            result[0] = 10;
        }
        if(result[1] >= 10){
            result[1] = 10;
        }
        
        result[2] = vo.getAssists() * 1.0 / vo.getFieldGoalHits();
        result[3] = vo.getPoints() / vo.getNumOfMatches();
        result[4] = vo.getPointsRival() / vo.getNumOfMatches();
        
        return result; 
    }
    
    /**
     * @param playerList
     * @param playerIdList
     * @param role 示例：前锋，中锋，后卫
     * @return
     */
    private double getAbility(ArrayList<Playervo> playerList,ArrayList<String> playerIdList,String role){
        int totalMinites = 0;
        double ability = 0;//能力值乘以打的时间
        
        for(String id:playerIdList){
            Playervo vo = null;
            //得到id对应的playervo
            for(Playervo token:playerList){
                if(token.getPid().equals(id)){
                    vo = token;
                    break;
                }
            }
            
            if(vo.getPosition().contains(role)){
                totalMinites += vo.getMinutes();
                ability = vo.getEfficiency() * vo.getMinutes();
            }
        }
        
        return ability / totalMinites;
    }
}
