package logic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;

import logic.matches.NBALiveBLControllor;
import logic.matches.matchBLcontrollor;
import logic.players.playerBLcontrollor;
import logic.teams.TeamController;
import logic.teams.TeamvoGenerator;
import vo.EventVo;
import vo.HotZonevo;
import vo.Matchvo;
import vo.Playervo;
import vo.Teamvo;
import BLservice.BLservice;

public class BLController extends UnicastRemoteObject implements BLservice {
	private static BLController blController = null;

	private BLController() throws RemoteException {
		matchBLcontrollor mc = matchBLcontrollor.getInstance();
		Thread thread1 = new Thread() {
			public void run() {
				mc.loadNewMatchvo(0);
			}
		};
		thread1.start();
	}

	public static BLController getInstance() throws RemoteException {
		if (blController != null) {
			return blController;
		} else {
			blController = new BLController();
			return blController;
		}
	}

	@Override
	public boolean initNBALive() throws RemoteException {
		// TODO Auto-generated method stub
		NBALiveBLControllor NBALive = NBALiveBLControllor.getInstance();
		return NBALive.setPeriod(1);
	}

	@Override
	public Matchvo getLiveMatchInfo() throws RemoteException {
		// TODO Auto-generated method stub
		NBALiveBLControllor NBALive = NBALiveBLControllor.getInstance();
		return NBALive.getLiveMatchInfo();
	}

	@Override
	public ArrayList<EventVo> getLiveEvent() throws RemoteException {
		// TODO Auto-generated method stub
		NBALiveBLControllor NBALive = NBALiveBLControllor.getInstance();
		return NBALive.getLiveEvent();
	}

	@Override
	public ArrayList<Playervo> getAllPlayers(String Season, boolean isPlayOff)
			throws RemoteException {
		// TODO Auto-generated method stub
		playerBLcontrollor playerBLcontro = playerBLcontrollor.getInstance();
		return playerBLcontro.getAllPlayers(Season, isPlayOff);
	}
	
	@Override
	public double[] getAlliancePlayerAverageData(String season,
			boolean isPlayOff) throws RemoteException {
		// TODO Auto-generated method stub
		playerBLcontrollor playerBLcontro = playerBLcontrollor.getInstance();
		return playerBLcontro.getAlliancePlayerAverageData(season,isPlayOff);
	}
	

	@Override
	public ArrayList<Teamvo> getAllTeams(String Season, boolean isPlayOff)
			throws RemoteException {
		return TeamController.getInstance().getAllTeams(Season, isPlayOff);
	}

	@Override
	public HotZonevo getHotZone(String teamNameEn, boolean isSeason,
			boolean isTotal) throws RemoteException {
		return TeamController.getInstance().getHotZone(teamNameEn, isSeason,
				isTotal);
	}

	@Override
	public double[] getTeamAbility(String teamNameEn) throws RemoteException {
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
	public Playervo getPlayerById(String Id) throws RemoteException {
		// TODO Auto-generated method stub
		playerBLcontrollor pbl = playerBLcontrollor.getInstance();
		return pbl.getPlayerById(Id);
	}

	@Override
	public Playervo getPlayerById(String Id, String season, boolean isplayoff)
			throws RemoteException {
		// TODO Auto-generated method stubs
		playerBLcontrollor pbl = playerBLcontrollor.getInstance();
		return pbl.getPlayerById(Id, season, isplayoff);
	}
	
	@Override
	public Playervo getPlayerByNameAndTeam(String playerName, String teamName)
			throws RemoteException {
		// TODO Auto-generated method stub
		playerBLcontrollor pbl = playerBLcontrollor.getInstance();
		return pbl.getPlayerByNameAndTeam(playerName, teamName);
	}
	

	@Override
	public Teamvo getTeamByPlayerId(String playerId, String season,
			boolean isPlayOff) throws RemoteException {
		return TeamController.getInstance().getTeamByPlayerId(playerId, season,
				isPlayOff);
	}

	@Override
	public Teamvo getTeamByTeamName(String teamNameEn, String season,
			boolean isPlayOff) throws RemoteException {
		return TeamController.getInstance().getTeamByTeamName(teamNameEn,
				season, isPlayOff);
	}

	@Override
	public Teamvo[] getHotTeams(String field, String season, boolean isPlayOff)
			throws RemoteException {
		// TODO Auto-generated method stub
		return null;
	}

    @Override
    public Teamvo getTeamWithLatest10Data(String teamNameEn, String season, boolean isPlayOff)
            throws RemoteException {
        return TeamvoGenerator.getInstance().getTeamvoWithLatest10Data(teamNameEn, season, isPlayOff);
    }

}