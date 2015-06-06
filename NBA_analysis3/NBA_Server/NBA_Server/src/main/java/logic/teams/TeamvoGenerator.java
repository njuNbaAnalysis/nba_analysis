package logic.teams;

import java.util.ArrayList;

import logic.matches.matchBLcontrollor;
import po.TeamListItem;
import po.TeamRecordItem;
import util.Tools;
import vo.Matchvo;
import vo.Teamvo;

import compare.WinningPercentageComp;

import data.teams.TeamNameListData;
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
        
        TeamNameListData data = new TeamNameListData();
        data.init();
        //得到基本信息
        {
            TeamListItem teamListItem = new TeamListItem();
            teamListItem.setTeamNameEn(teamNameEn);
            ArrayList<TeamListItem> teamList = data.getTeamList(teamListItem);
            teamListItem = teamList.get(0); //得到teamNameEn所对应的teamListItem
            
            vo.setAbbreviation(teamNameEn);
            vo.setName(teamListItem.getTeamNameZh());
            vo.setConference(Character.toUpperCase(teamListItem.getConference().charAt(0)));
            vo.setDivision(teamListItem.getDivision());
            vo.setSeason(season);
            vo.setPlayOff(isPlayOff);
        }
        
        
        TeamRecordItemData teamRecordItemData = new TeamRecordItemData();
        teamRecordItemData.init();
        
        //得到球员列表
        {
            TeamRecordItem playerItemPara = new TeamRecordItem();
            playerItemPara.setDataType("playerItem");
            playerItemPara.setTeamNameEn(teamNameEn);
            playerItemPara.setSeason(isPlayOff?false:true);
            playerItemPara.setBeginYear(Tools.xx_xxToxxxx(season));
            ArrayList<String> playerList = new ArrayList<String>();
            ArrayList<TeamRecordItem> resultList = teamRecordItemData.getTeamRecords(playerItemPara);
            for(TeamRecordItem token:resultList){
                playerList.add(token.getPlayerId());
            }
            vo.setPlayerList(playerList);
        }
        
        
        //得到各种数据 
        {
          //得到低阶数据
            TeamRecordItem teamTotalRecord = new TeamRecordItem();
            teamTotalRecord.setDataType("teamItem");
            teamTotalRecord.setTeamNameEn(teamNameEn);
            teamTotalRecord.setSeason(isPlayOff?false:true);
            teamTotalRecord.setBeginYear(Tools.xx_xxToxxxx(season));
            teamTotalRecord = teamRecordItemData.getTeamRecords(teamTotalRecord).get(0);
            
            TeamRecordItem teamTotalRecordRival = new TeamRecordItem();
            teamTotalRecordRival.setDataType("rivalTeamItem");
            teamTotalRecordRival.setTeamNameEn(teamNameEn);
            teamTotalRecordRival.setSeason(isPlayOff?false:true);
            teamTotalRecordRival.setBeginYear(Tools.xx_xxToxxxx(season));
            teamTotalRecordRival = teamRecordItemData.getTeamRecords(teamTotalRecordRival).get(0);
            
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
            
            TeamNameList teamNameList = TeamNameList.getIntance();
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
        {
            matchBLcontrollor matchController = matchBLcontrollor.getInstance();
            ArrayList<Matchvo> voList = matchController.getLast10Matches(teamNameEn, season, isPlayOff);
            
            
        }
        
        
        
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
        
        TeamRecordItemData teamRecordItemData = new TeamRecordItemData();
        teamRecordItemData.init();
        
        TeamRecordItem totalItemPara = new TeamRecordItem();
        totalItemPara.setDataType("teamItem");
        totalItemPara.setSeason(true);
        totalItemPara.setBeginYear(Tools.xx_xxToxxxx(season));
        totalItemList = teamRecordItemData.getTeamRecords(totalItemPara);   //同时起到清空作用 
        
        //按胜率进行降序排序
        totalItemList.sort(new WinningPercentageComp());
    }
}


