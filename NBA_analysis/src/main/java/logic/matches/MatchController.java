package logic.matches;

import java.util.ArrayList;

public class MatchController {
	ArrayList<Match> matchList = null;
	private static MatchController matchController = null;
	
	private MatchController(){
		//initialize matchList,
		
	}
	
	public static MatchController getInstance(){
		if(matchController != null){
			return matchController;
		}
		else{
			return new MatchController();
		}
	}
	
	
}
