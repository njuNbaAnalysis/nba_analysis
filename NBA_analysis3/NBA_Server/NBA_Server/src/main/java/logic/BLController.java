package logic;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.Collection;

import logic.matches.NBALiveBLControllor;
import logic.matches.NBALiveList;
import logic.matches.matchBLcontrollor;
import logic.players.playerBLcontrollor;
import logic.teams.TeamController;
import logic.teams.TeamvoGenerator;
import vo.EventVo;
import vo.FutureMatchvo;
import vo.HotZonevo;
import vo.MatchSimpleInfovo;
import vo.Matchvo;
import vo.Playervo;
import vo.RecordOfPlayervo;
import vo.Teamvo;
import vo.TodayPlayervo;
import BLservice.BLservice;

public class BLController extends UnicastRemoteObject implements BLservice {
	private static BLController blController = null;

	private BLController() throws RemoteException {
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
	public ArrayList<FutureMatchvo> getFutureMatches(String date)
			throws RemoteException {
		// TODO Auto-generated method stub
		return NBALiveList.getFutureMatches(date);
	}

	@Override
	public boolean initNBALive() throws RemoteException {
		// TODO Auto-generated method stub
		NBALiveBLControllor NBALive = NBALiveBLControllor.getInstance();
		return NBALive.setPeriod(1);
	}

	@Override
	public Matchvo getLiveMatchInfo(String Mid) throws RemoteException {
		// TODO Auto-generated method stub
		NBALiveBLControllor NBALive = NBALiveBLControllor.getInstance();
		return NBALive.getLiveMatchInfo(Mid);
	}

	@Override
	public ArrayList<EventVo> getLiveEvent(String Mid) throws RemoteException {
		// TODO Auto-generated method stub
		NBALiveBLControllor NBALive = NBALiveBLControllor.getInstance();
		return NBALive.getLiveEvent(Mid);
	}

	@Override
	public ArrayList<Playervo> getAllPlayers(String Season, boolean isPlayOff)
			throws RemoteException {
		// TODO Auto-generated method stub
		playerBLcontrollor playerBLcontro = playerBLcontrollor.getInstance();
		return playerBLcontro.getAllPlayers(Season, isPlayOff);
	}

	@Override
	public ArrayList<Playervo> getSeasonKingPlayer(String transferField,
			int number, String season, boolean isplayoff)
			throws RemoteException {
		// TODO Auto-generated method stub
		playerBLcontrollor playerBLcontro = playerBLcontrollor.getInstance();
		return playerBLcontro.getSeasonKingPlayer(transferField, number,
				season, isplayoff);
	}

	@Override
	public ArrayList<TodayPlayervo> getTodayKingPlayer(String date,
			String transferField, int number) throws RemoteException {
		// TODO Auto-generated method stub
		playerBLcontrollor playerBLcontro = playerBLcontrollor.getInstance();
		return playerBLcontro.getTodayKingPlayer(date, transferField, number);
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
	public Playervo getPlayerByNameAndTeam(String playerName)
			throws RemoteException {
		// TODO Auto-generated method stub
		playerBLcontrollor pbl = playerBLcontrollor.getInstance();
		return pbl.getPlayerByNameAndTeam(playerName);
	}

	@Override
	public double[] getAlliancePlayerAverageData(String season,
			boolean isPlayOff) throws RemoteException {
		// TODO Auto-generated method stub
		playerBLcontrollor playerBLcontro = playerBLcontrollor.getInstance();
		return playerBLcontro.getAlliancePlayerAverageData(season, isPlayOff);
	}

	@Override
	public ArrayList<RecordOfPlayervo> getRecordOfPlayerById(String Pid)
			throws RemoteException {
		// TODO Auto-generated method stub
		matchBLcontrollor mbl = matchBLcontrollor.getInstance();
		return mbl.getRecordOfPlayerById(Pid);
	}

	@Override
	public Collection<? extends Matchvo> getTodayMatches(String string)
			throws RemoteException {
		// TODO Auto-generated method stub
		matchBLcontrollor matchBL = matchBLcontrollor.getInstance();
		return matchBL.getTodayMatched(string);
	}

	@Override
	public ArrayList<MatchSimpleInfovo> getMatchSimpleInfo(String teamName,
			String season) throws RemoteException {
		// TODO Auto-generated method stub
		matchBLcontrollor matchBL = matchBLcontrollor.getInstance();
		return matchBL.getMatchSimpleInfo(teamName, season);
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
	public Teamvo getTeamWithLatest10Data(String teamNameEn, String season,
			boolean isPlayOff) throws RemoteException {
		return TeamvoGenerator.getInstance().getTeamvoWithLatest10Data(
				teamNameEn, season, isPlayOff);
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
	public double[] getTeamAbility(String teamNameEn, String season,
			boolean isplayoff) throws RemoteException {
		return TeamController.getInstance().getTeamAbility(teamNameEn, season,
				isplayoff);
	}

	@Override
	public ArrayList<MatchSimpleInfovo> getLatestMatchSimpleInfo(
			String teamName1, String teamName2) throws RemoteException {
		// TODO Auto-generated method stub
		matchBLcontrollor m = matchBLcontrollor.getInstance();
		return m.getLatestMatchSimpleInfo(teamName1, teamName2);
	}

	@Override
	public double getWinPercentage(String teamName1, String teamName2,
			String season, boolean isplayoff) throws RemoteException {
		// TODO Auto-generated method stub
		matchBLcontrollor m = matchBLcontrollor.getInstance();
		return m.getWinPercentage(teamName1, teamName2, season, isplayoff);
	}

	@Override
	public double getWinPercentage(String teamName1, String season,
			boolean isPlayOff) throws RemoteException {
		// TODO Auto-generated method stub
		Teamvo v1 = getTeamByTeamName(teamName1, season, isPlayOff);
		double result = 0;
		result = v1.getFieldGoalsPercentage() * 0.3932
				+ v1.getThreePointersPercentage() * (-1.2581)
				+ v1.getFreeThrowsPercentage() * (2.1271)
				+ v1.getAverageRebounds() * (-0.1592)
				+v1.getAverageBlockShots()*(-0.9521)
				+v1.getAverageAssists()*(1.3477)
				+v1.getAverageSteals()*(24.6545)
				+v1.getAverageTurnOver()*(53.1659)
				+v1.getAverageFouls()*(114.0270) - 45.4872;
		return result;
	}
}