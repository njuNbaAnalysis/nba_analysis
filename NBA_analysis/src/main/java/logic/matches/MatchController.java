package logic.matches;

import java.util.ArrayList;

import data.DataController;
import data.DataService;

public class MatchController {
	private ArrayList<Match> matchList = null;
	private static MatchController matchController = null;
	private DataService dataService;
	
	private MatchController(){
	    dataService = DataController.getInstance();
	    init();
	}
	
	public static MatchController getInstance(){
		if(matchController != null){
			return matchController;
		}
		else{
			return new MatchController();
		}
	}
	
	public void init(){
	    matchList = dataService.getAllMatches();
	}
	
	
}
