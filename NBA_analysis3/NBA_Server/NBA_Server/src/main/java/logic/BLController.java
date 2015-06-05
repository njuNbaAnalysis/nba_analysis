package logic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;

import logic.matches.NBALiveBLControllor;
import logic.matches.matchBLcontrollor;
import logic.teams.TeamController;
import logic.players.playerBLcontrollor;
import vo.EventVo;
import vo.HotZonevo;
import vo.Matchvo;
import vo.Playervo;
import vo.Teamvo;
import BLservice.BLservice;

public class BLController extends UnicastRemoteObject implements BLservice{
	private static BLController blController = null;

	private BLController()throws RemoteException {
	}

	public static BLController getInstance()throws RemoteException {
		if (blController != null) {
			return blController;
		} else {
			blController = new BLController();
			return blController;
		}
	}

	@Override
	public Matchvo getLiveMatchInfo() throws RemoteException{
		// TODO Auto-generated method stub
		NBALiveBLControllor NBALive = NBALiveBLControllor.getInstance();
		return NBALive.getLiveMatchInfo();
	}

	@Override
	public ArrayList<EventVo> getLiveEvent() throws RemoteException{
		// TODO Auto-generated method stub
		NBALiveBLControllor NBALive = NBALiveBLControllor.getInstance();
		return NBALive.getLiveEvent();
	}

	@Override
	public ArrayList<Playervo> getAllPlayers(String Season, boolean isPlayOff) throws RemoteException{
		// TODO Auto-generated method stub
		playerBLcontrollor playerBLcontro = playerBLcontrollor.getInstance();
		return playerBLcontro.getAllPlayers(Season,isPlayOff);
	}

	@Override
	public ArrayList<Teamvo> getAllTeams(String Season, boolean isPlayOff) throws RemoteException{
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public HotZonevo getHotZone(String teamNameEn, boolean isSeason, boolean isTotal) throws RemoteException{
        return TeamController.getInstance().getHotZone(teamNameEn, isSeason, isTotal);
    }

    @Override
    public double[] getTeamAbility(String teamNameEn)throws RemoteException {
        // TODO Auto-generated method stub
        return null;
    }

	@Override
	public Collection<? extends Matchvo> getTodayMatches(String string)
			throws RemoteException {
		// TODO Auto-generated method stub
		matchBLcontrollor matchBL = matchBLcontrollor.getInstance();
		return matchBL.getTodayMatched(string);
	}

	@Override
	public Teamvo[] getTeamsByMatch(String[] teamNames) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Playervo getPlayerByName(String nameOfReboundsKing)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Teamvo getTeamByPlayerName(String playerName, String season,
			boolean isPlayOff) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Teamvo getTeamByTeamName(String teamName) throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean initNBALive() throws RemoteException {
		// TODO Auto-generated method stub
		NBALiveBLControllor NBALive = NBALiveBLControllor.getInstance();
		return NBALive.setPeriod(1);
	}
	
}
