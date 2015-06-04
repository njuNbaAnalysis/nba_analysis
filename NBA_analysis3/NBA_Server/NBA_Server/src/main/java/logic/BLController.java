package logic;

import java.util.ArrayList;

import logic.matches.matchBLcontrollor;
import vo.EventVo;
import vo.Matchvo;
import vo.Playervo;
import vo.Teamvo;
import BLservice.BLservice;

public class BLController implements BLservice {
	private static BLController blController = null;
	public static int progress = 0;
	public static int maxProgress = 9;
	
	private matchBLcontrollor matchController = null;

	private BLController() {
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
		return null;
	}

	@Override
	public ArrayList<EventVo> getLiveEvent() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Playervo> getAllPlayers(String Season, boolean isPlayOff) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Teamvo> getAllTeams(String Season, boolean isPlayOff) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
