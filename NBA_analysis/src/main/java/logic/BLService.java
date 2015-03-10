package logic;

import java.util.ArrayList;

import logic.matches.Match;
import logic.players.Player;
import logic.teams.Team;

public interface BLService {
	public ArrayList<Player> getAllPlayers();          //�õ����е���Ա
	public ArrayList<Team> getAllTeams();				//�õ����е����
	public ArrayList<Match> getAllMatches();			//�õ����еı���
}
