package logic;

import java.io.Serializable;
import java.util.ArrayList;

import logic.matches.matchBLcontrollor;
import logic.teams.TeamController;
import logic.players.playerBLcontrollor;
import vo.EventVo;
import vo.HotZonevo;
import vo.Matchvo;
import vo.Playervo;
import vo.Teamvo;
import BLservice.BLservice;

public class BLController implements BLservice,Serializable{
	private static BLController blController = null;
	
	private matchBLcontrollor matchController = null;
	private playerBLcontrollor playerBLcontrollor = null;

	private BLController() {
		matchController = matchBLcontrollor.getInstance();
		playerBLcontrollor = playerBLcontrollor.getInstance();
	}

	public static BLController getInstance() {
		if (blController != null) {
			return blController;
		} else {
			blController = new BLController();
			return blController;
		}
	}

	@Override
	public Matchvo getLiveMatchInfo() {
		// TODO Auto-generated method stub
		matchController = matchBLcontrollor.getInstance();
		return matchController.getLiveMatchInfo();
	}

	@Override
	public ArrayList<EventVo> getLiveEvent() {
		// TODO Auto-generated method stub
		matchController = matchBLcontrollor.getInstance();
		return matchController.getLiveEvent();
	}

	@Override
	public ArrayList<Playervo> getAllPlayers(String Season, boolean isPlayOff) {
		// TODO Auto-generated method stub
		playerBLcontrollor = playerBLcontrollor.getInstance();
		return playerBLcontrollor.getAllPlayers(Season,isPlayOff);
	}

	@Override
	public ArrayList<Teamvo> getAllTeams(String Season, boolean isPlayOff) {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public HotZonevo getHotZone(String teamNameEn, boolean isSeason, boolean isTotal) {
        return TeamController.getInstance().getHotZone(teamNameEn, isSeason, isTotal);
    }

    @Override
    public double[] getTeamAbility(String teamNameEn) {
        // TODO Auto-generated method stub
        return null;
    }
	
}
