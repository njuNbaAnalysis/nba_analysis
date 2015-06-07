package logic.players;

import java.util.ArrayList;

import vo.Playervo;

public class PlayersBuff {
	private boolean isplayoff;
	private ArrayList<Playervo> playerlist;
	private String season;
	public PlayersBuff(boolean isplayoff, ArrayList<Playervo> playerlist,
			String season) {
		super();
		this.isplayoff = isplayoff;
		this.playerlist = playerlist;
		this.season = season;
	}
	public boolean getIsplayoff() {
		return isplayoff;
	}
	public ArrayList<Playervo> getPlayerlist() {
		return playerlist;
	}
	public String getSeason() {
		return season;
	}
	
	
}
