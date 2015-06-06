package logic.matches;

import vo.Matchvo;

public class MatchBuff {
	private String Mid;
	private Matchvo matchvo;
	public MatchBuff(String Mid, boolean isplayoff,
			Matchvo matchvo) {
		super();
		this.Mid = Mid;
		this.matchvo = matchvo;
	}
	
	
	public String getMid() {
		return Mid;
	}
	public Matchvo getMatchvo() {
		return matchvo;
	}
	
}
