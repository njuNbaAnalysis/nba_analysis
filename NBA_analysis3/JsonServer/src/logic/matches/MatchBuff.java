package logic.matches;

import java.util.ArrayList;

import po.match;

public class MatchBuff {
	private String season;
	private ArrayList<match> matchlist;
	public MatchBuff(String season,
			ArrayList<match> matchlist) {
		super();
		this.season = season;
		this.matchlist = matchlist;
	}
	public String getSeason() {
		return season;
	}
	public ArrayList<match> getMatchlist() {
		return matchlist;
	}
	
	
	
}
