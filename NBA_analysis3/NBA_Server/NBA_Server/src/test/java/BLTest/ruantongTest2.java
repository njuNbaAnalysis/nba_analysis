package BLTest;

import java.rmi.RemoteException;
import java.util.ArrayList;

import vo.Teamvo;
import logic.BLController;


public class ruantongTest2 {


	public static void main(String[] args) {
		String result1 = "";
		String result2 = "";
		String result3 = "";
		String result4 = "";
		String result5 = "";
		String result6 = "";
		String result7 = "";
		String result8 = "";
		String result9 = "";
		String result10 = "";
		BLController bl  = null;
		try {
			bl = BLController.getInstance();
			ArrayList<Teamvo> list = bl.getAllTeams("14-15", false);
			for(int i=0;i<list.size();i++){
				result1 += list.get(i).getAveragePoints()+",";
				result2 += list.get(i).getFieldGoalsPercentage()*100+",";
				result3 += list.get(i).getThreePointersPercentage()*100+",";
				result4 += list.get(i).getFreeThrowsPercentage()*100+",";
				result5 += list.get(i).getAverageRebounds()+",";
				result6 += list.get(i).getAverageBlockShots()+",";
				result7 += list.get(i).getAverageAssists()+",";
				result8 += list.get(i).getAverageSteals()+",";
				result9 += list.get(i).getAverageTurnOver()+",";
				result10 += list.get(i).getAverageFouls()+",";
			}
			System.out.println(result1);
			System.out.println(result2);
			System.out.println(result3);
			System.out.println(result4);
			System.out.println(result5);
			System.out.println(result6);
			System.out.println(result7);
			System.out.println(result8);
			System.out.println(result9);
			System.out.println(result10);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			System.out.println(bl.getTeamPoints("GSW", "14-15", false));
			System.out.println(bl.getTeamPoints("HOU", "14-15", false));
			System.out.println(bl.getTeamPoints("CLE", "14-15", false));
			System.out.println(bl.getTeamPoints("PHX", "14-15", false));
			System.out.println(bl.getTeamPoints("ATL", "14-15", false));
			System.out.println(bl.getTeamPoints("WAS", "14-15", false));
			System.out.println(bl.getTeamPoints("MEM", "14-15", false));
			System.out.println(bl.getTeamPoints("BOS", "14-15", false));
			System.out.println(bl.getTeamPoints("MIA", "14-15", false));
			System.out.println(bl.getTeamPoints("TOR", "14-15", false));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
