package logic.teams;

import java.util.ArrayList;

import logic.matches.matchBLcontrollor;
import po.TeamListItem;
import po.TeamRecordItem;
import util.Tools;
import vo.Matchvo;
import vo.RecordOfPlayervo;
import vo.Teamvo;

import compare.WinningPercentageComp;

import data.teams.TeamRecordItemData;

public class TeamvoGenerator {
    
    private static TeamvoGenerator teamvoGenerator = null;
    
  //某个赛季每只球队的球队总条目    的缓存，使得计算球队在联盟内排名时无需重复读取，只有参数为常规赛时才会使用
    private ArrayList<TeamRecordItem> totalItemList = new ArrayList<TeamRecordItem>();
    
    private TeamvoGenerator(){}
    
    public static TeamvoGenerator getInstance(){
        if(teamvoGenerator == null){
            teamvoGenerator = new TeamvoGenerator();
        }
        return teamvoGenerator;
    }
    
    public Teamvo getTeamvo(String teamNameEn, String season, boolean isPlayOff){
        Teamvo vo = new Teamvo();
        
        
        TeamNameList teamNameList = TeamNameList.getIntance();
        //得到基本信息
        {
          //得到teamNameEn所对应的teamListItem
            TeamListItem teamListItem = teamNameList.getTeamListItem(teamNameEn);
            
            vo.setAbbreviation(teamNameEn);
            vo.setName(teamListItem.getTeamNameZh());
            
            if(teamListItem.getConference() != null && !teamListItem.getConference().equals("")){
                vo.setConference(Character.toUpperCase(teamListItem.getConference().charAt(0)));
            }
            
            if(teamListItem.getDivision() != null && !teamListItem.getDivision().equals("")){
                vo.setDivision(teamListItem.getDivision());
            }
            
            
            vo.setSeason(season);
            vo.setPlayOff(isPlayOff);
        }
        
        TeamRecordItemList teamRecordItemList = TeamRecordItemList.getInstance();
        
        //得到球员列表
        vo.setPlayerList(teamRecordItemList.getPlayerIdList(teamNameEn, season, isPlayOff));
        
        
        //得到各种数据 
        {
          //得到低阶数据
            TeamRecordItem teamTotalRecord = new TeamRecordItem();
            teamTotalRecord.setDataType("teamItem");
            teamTotalRecord.setTeamNameEn(teamNameEn);
            teamTotalRecord.setSeason(isPlayOff?false:true);
            teamTotalRecord.setBeginYear(Tools.xx_xxToxxxx(season));
            teamTotalRecord = teamRecordItemList.getRecordItemList(teamTotalRecord).get(0);
            
            TeamRecordItem teamTotalRecordRival = new TeamRecordItem();
            teamTotalRecordRival.setDataType("rivalTeamItem");
            teamTotalRecordRival.setTeamNameEn(teamNameEn);
            teamTotalRecordRival.setSeason(isPlayOff?false:true);
            teamTotalRecordRival.setBeginYear(Tools.xx_xxToxxxx(season));
            teamTotalRecordRival = teamRecordItemList.getRecordItemList(teamTotalRecordRival).get(0);
            
            //己方
            vo.setNumOfMatches(teamTotalRecord.getNumOfFailure() + teamTotalRecord.getNumOfVictory());
            vo.setNumOfVictory(teamTotalRecord.getNumOfVictory());
            vo.setFieldGoalAttemps((int)(teamTotalRecord.getFieldGoalAttempsAverage() * vo.getNumOfMatches()));
            vo.setFieldGoalHits((int)(teamTotalRecord.getFieldGoalHitsAverage() * vo.getNumOfMatches()));
            vo.setThreePointerAttempts((int)(teamTotalRecord.getThreePointerAttemptsAverage() * vo.getNumOfMatches()));
            vo.setThreePointerHits((int)(teamTotalRecord.getThreePointerHitsAverage() * vo.getNumOfMatches()));
            vo.setFreeThrowAttempts((int)(teamTotalRecord.getFreeThrowAttemptsAverage() * vo.getNumOfMatches()));
            vo.setFreeThrowHits((int)(teamTotalRecord.getFreeThrowHitsAverage() * vo.getNumOfMatches()));
            vo.setOffensiveRebounds((int)(teamTotalRecord.getOffensiveReboundsAverage() * vo.getNumOfMatches()));
            vo.setDefensiveRebounds((int)(teamTotalRecord.getDefensiveReboundsAverage() * vo.getNumOfMatches()));
            vo.setAssists((int)(teamTotalRecord.getAssistsAverage() * vo.getNumOfMatches()));
            vo.setSteals((int)(teamTotalRecord.getStealsAverage() * vo.getNumOfMatches()));
            vo.setBlockShots((int)(teamTotalRecord.getBlockShotsAverage() * vo.getNumOfMatches()));
            vo.setTurnOver((int)(teamTotalRecord.getTurnOversAverage() * vo.getNumOfMatches()));
            vo.setFouls((int)(teamTotalRecord.getFoulsAverage()) * vo.getNumOfMatches());
            vo.setPoints((int)(teamTotalRecord.getPointsAverage() * vo.getNumOfMatches()));
            
            //对手
            int numOfMatchesRival = teamTotalRecordRival.getNumOfFailure() + teamTotalRecordRival.getNumOfVictory();
            vo.setPointsRival((int)(teamTotalRecordRival.getPointsAverage() * numOfMatchesRival));
            vo.setFieldGoalAttempsRival((int)(teamTotalRecordRival.getFieldGoalAttempsAverage() * numOfMatchesRival));
            vo.setThreePointerAttemptsRival((int)(teamTotalRecordRival.getThreePointerAttemptsAverage() * numOfMatchesRival));
            vo.setOffenseReboundsRival((int)(teamTotalRecordRival.getOffensiveReboundsAverage() * numOfMatchesRival));
            vo.setDefenseReboundsRival((int)(teamTotalRecordRival.getDefensiveReboundsAverage() * numOfMatchesRival));
            
            
            
          //计算高阶数据：进攻防守回合以及各种效率
            int fieldGoalAttempts1 = vo.getFieldGoalAttemps();
            int freeThrowAttemps1 = vo.getFreeThrowAttempts();
            int offensiveRebounds1 = vo.getOffensiveRebounds();
            int defensiveRebounds1 = vo.getDefensiveRebounds();
            int fieldGoalHits1 = vo.getFieldGoalHits();
            int turnOver1 = vo.getTurnOver();
            
            int fieldGoalAttempts2 = vo.getFieldGoalAttempsRival();
            int freeThrowAttemps2 = (int)(teamTotalRecordRival.getFreeThrowHitsAverage() * numOfMatchesRival);
            int offensiveRebounds2 = vo.getOffenseReboundsRival();
            int defensiveRebounds2 = vo.getDefenseReboundsRival();
            int fieldGoalHits2 = (int)(teamTotalRecordRival.getFieldGoalHitsAverage() * numOfMatchesRival);
            int turnOver2 = (int)(teamTotalRecordRival.getTurnOversAverage() * numOfMatchesRival); 
            
            //进攻回合的增量
            double incrementOfOffensiveRounds1 = fieldGoalAttempts1 + 0.4 * freeThrowAttemps1 - 1.07 * 
                    (1.0 * offensiveRebounds1 / (offensiveRebounds1 + defensiveRebounds2)
                            * (fieldGoalAttempts1 - fieldGoalHits1))
                            + 1.07 * turnOver1;
            
            //防守回合的增量
            double incrementOfOffensiveRounds2 = fieldGoalAttempts2 + 0.4 * freeThrowAttemps2 - 1.07 * 
                    (1.0 * offensiveRebounds2 / (offensiveRebounds2 + defensiveRebounds1)
                            * (fieldGoalAttempts2 - fieldGoalHits2))
                            + 1.07 * turnOver2;
            
            

            vo.setOffensiveRounds(incrementOfOffensiveRounds1);
            vo.setDefensiveRounds(incrementOfOffensiveRounds2);
            
            //计算各种效率
            computeEfficiency(vo);
        }
        
        //如果是常规赛则计算在联盟的排名 
        if(!isPlayOff){
            loadTotalItemListIfNotHas(season);
            
            boolean isCurrentTeamEastern = teamNameList.isEastern(teamNameEn);
            int rank = 0;
            for(TeamRecordItem token:totalItemList){
                //每碰到一个相同分区则rank加一
                if(isCurrentTeamEastern == teamNameList.isEastern(token.getTeamNameEn())){
                    rank++;
                }
                //遇到自己则直接返回
                if(token.getTeamNameEn().equals(teamNameEn)){
                    break;
                }
            }
            vo.setRankingInLeague(rank);
        }
        
        //计算球队近十场的各种数据
        //computeLatest10Data(vo,teamNameEn, season, isPlayOff);

        return vo;
    }
    
    //对每个球队计算各种效率
    private void computeEfficiency(Teamvo team){
        team.setOffenseEfficiency(team.getPoints() * 1.0 / team.getOffensiveRounds() * 100);
        team.setDefenseEfficiency(1.0 * team.getPointsRival() / team.getDefensiveRounds() * 100); 
        team.setReboundsEfficiency(team.getRebounds() * 1.0 / (team.getRebounds() + team.getReboundsRival()));
        team.setStealsEfficiency(1.0 * team.getSteals() / team.getDefensiveRounds() * 100);
        team.setAssistsPercentage(1.0 * team.getAssists() / team.getOffensiveRounds() * 100);
    }

    
    
    /**
     * 判断是否totalItemList缓存中是否有当前赛季的team的所有Item，如果没有则
     * 删除原先的并载入新的
     * 兼具按胜率降序排序的功能
     * @param teamNameEn，例"ATL"
     * @param season，例"11-12"
     */
    private void loadTotalItemListIfNotHas(String season){
        //对第一个进行判断，如果已经包含则直接返回
        if(totalItemList.size() >= 1){
            TeamRecordItem item = totalItemList.get(0);
            if(item.getBeginYear().equals(Tools.xx_xxToxxxx(season))){
                return;
            }
        }
        
        //否则清空并进行读取
        
        TeamRecordItem totalItemPara = new TeamRecordItem();
        totalItemPara.setDataType("teamItem");
        totalItemPara.setSeason(true);
        totalItemPara.setBeginYear(Tools.xx_xxToxxxx(season));
        totalItemList = TeamRecordItemList.getInstance().getRecordItemList(totalItemPara);
        
        //按胜率进行降序排序
        totalItemList.sort(new WinningPercentageComp());
    }

    /**
     * @param vo
     * @param teamNameEn
     * @param season
     * @param isPlayOff
     */
    private void computeLatest10Data(Teamvo vo,String teamNameEn, String season, boolean isPlayOff){
        matchBLcontrollor matchController = matchBLcontrollor.getInstance();
        ArrayList<Matchvo> voList = matchController.getLast10Matches(teamNameEn, season, isPlayOff);
        
        //for debug
        //如果voList为null或为空则不进行任何计算
        if(voList == null || voList.isEmpty()){
            return ;
        }
        
        //index = 0 表示最近一场,index = 9表示最远一场，容量为10
        int[] points = new int[10];
        int[] pointsRival = new int[10];
        double[] offensiveRounds = new double[10];
        double[] defensiveRounds = new double[10];
        
        //得分赋值
        for(int i = 0;i <= 9;i ++){
            if(voList.get(i).getTeams()[0].equals(teamNameEn)){
                points[i] = voList.get(i).getPoints()[0];
                pointsRival[i] = voList.get(i).getPoints()[1];
            }else{
                points[i] = voList.get(i).getPoints()[1];
                pointsRival[i] = voList.get(i).getPoints()[0];
            }
            
            //进攻防守回合赋值
            {
                Matchvo match = voList.get(i);
                ArrayList<RecordOfPlayervo> recordList1 = match.getFirstRecordList();
                ArrayList<RecordOfPlayervo> recordList2 = match.getSecondRecordList();
                
                //判断本队是否是主场，即是否是team数组的第一个 
                boolean isAtHome = false;
                if(match.getTeams()[0].equals(teamNameEn)){
                    isAtHome = true;
                }
                
                //进攻防守回合累加赋值
                    //正常比赛的各项数据
                int fieldGoalAttempts1 = 0;
                int freeThrowAttemps1 = 0;
                int offensiveRebounds1 = 0;
                int defensiveRebounds1 = 0;
                int fieldGoalHits1 = 0;
                int turnOver1 = 0;
                
                int fieldGoalAttempts2 = 0;
                int freeThrowAttemps2 = 0;
                int offensiveRebounds2 = 0;
                int defensiveRebounds2 = 0;
                int fieldGoalHits2 = 0;
                int turnOver2 = 0;
                
                for(RecordOfPlayervo record:recordList1){
                    fieldGoalAttempts1 += record.getFieldGoalAttempts();
                    freeThrowAttemps1 += record.getFreeThrowAttemps();
                    offensiveRebounds1 += record.getOffensiveRebounds();
                    defensiveRebounds1 += record.getDefensiveRebounds();
                    fieldGoalHits1 += record.getFieldGoalHits();
                    turnOver1 += record.getTurnOver();
                }
                
                for(RecordOfPlayervo record:recordList2){
                    fieldGoalAttempts2 += record.getFieldGoalAttempts();
                    freeThrowAttemps2 += record.getFreeThrowAttemps();
                    offensiveRebounds2 += record.getOffensiveRebounds();
                    defensiveRebounds2 += record.getDefensiveRebounds();
                    fieldGoalHits2 += record.getFieldGoalHits();
                    turnOver2 += record.getTurnOver();
                }
                
                //主队的进攻回合数
                double incrementOfOffensiveRounds1 = fieldGoalAttempts1 + 0.4 * freeThrowAttemps1 - 1.07 * 
                        (1.0 * offensiveRebounds1 / (offensiveRebounds1 + defensiveRebounds2)
                                * (fieldGoalAttempts1 - fieldGoalHits1))
                                + 1.07 * turnOver1;
                //客队的进攻回合数
                double incrementOfOffensiveRounds2 = fieldGoalAttempts2 + 0.4 * freeThrowAttemps2 - 1.07 * 
                        (1.0 * offensiveRebounds2 / (offensiveRebounds2 + defensiveRebounds1)
                                * (fieldGoalAttempts2 - fieldGoalHits2))
                                + 1.07 * turnOver2;

                if(isAtHome){
                    offensiveRounds[i] = incrementOfOffensiveRounds1;
                    defensiveRounds[i] = incrementOfOffensiveRounds2;
                }
                else{
                    offensiveRounds[i] = incrementOfOffensiveRounds2;
                    defensiveRounds[i] = incrementOfOffensiveRounds1;
                }
            }
        }
        
        // 近十场的输赢情况,
        {
            boolean[] latestWinOrLose = new boolean[10];
            for(int i = 0;i < 10;i ++){
                if(points[i] > pointsRival[i]){
                    latestWinOrLose[i] = true;
                }
                else{
                    latestWinOrLose[i] = false;
                }
            }
            vo.setLatestWinOrLose(latestWinOrLose);
        }
        
        // 得到近十场的战绩,暂定返回比分(格式:"100-101")，可能需要更多
        {
            String[] latestRecord = new String[10];
            for(int i = 0;i < 10;i ++){
                latestRecord[i] = String.valueOf(points[i]) + "-" + String.valueOf(pointsRival[i]);
            }
            vo.setLatestRecord(latestRecord);
        }
        
        // 得到近十场的攻防比(得分/失分)
        {
            double[] latestOffendThanDefend = new double[10];
            for(int i = 0;i < 10;i ++){
                latestOffendThanDefend[i] = points[i]/pointsRival[i];
            }
            vo.setLatestOffendThanDefend(latestOffendThanDefend);
        }

        // 得到近十场的得分(每一场的每百进攻回合得分)
        {
            double[] latestOffend = new double[10];
            for(int i = 0;i < 10;i ++){
                latestOffend[i] = points[i]/offensiveRounds[i];
            }
            vo.setLatestOffend(latestOffend);
        }
        

        // 得到近十场的失分(每一场的每百防守回合失分)
        {
            double[] latestDefend = new double[10];
            for(int i = 0;i < 10;i ++){
                latestDefend[i] = pointsRival[i]/defensiveRounds[i];
            }
            vo.setLatestDefend(latestDefend);
        }
        

        // 得到近十场的节奏(每一场的进攻回合数)
        {
            double[] latestTempo = new double[10];
            for(int i = 0;i < 10;i ++){
                latestTempo[i] = offensiveRounds[i];
            }
            vo.setLatestTempo(latestTempo);
        }
        
    }
}


