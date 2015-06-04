package logic.teams;

import vo.HotZonevo;

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
        return null;
    }
    
    
}
