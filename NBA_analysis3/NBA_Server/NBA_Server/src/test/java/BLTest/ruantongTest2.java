package BLTest;

import java.util.ArrayList;

import po.match;
import data.matches.MatchReader;

public class ruantongTest2 {

	static String[] names = { "POR", "SAC", "GSW", "LAL", "LAC", "UTA", "PHX",
			"DEN", "MIN", "OKC", "DAL", "SAS", "HOU", "NOP", "MEM", "MIL",
			"DET", "IND", "CLE", "ATL", "BOS", "NYK", "BKN", "WAS", "CHA",
			"ORL", "MIA", "PHI", "CHI", "TOR" };

	static int pingshu[] = new int[13];

	public static void main(String[] args) {

		MatchReader mr = new MatchReader();
		ArrayList<match> list = mr.getMatchesBySeason("14-15", false);

		// String result = "";
		int max = -100;
		int min = 100;
		String result = "";
		for (int i = 0; i < list.size(); i++) {
			result += (list.get(i).getHome_points() - list.get(i)
					.getAway_points()) + ",";
			if ((list.get(i).getHome_points() - list.get(i).getAway_points()) > max) {
				max = (list.get(i).getHome_points() - list.get(i)
						.getAway_points());
			}
			if ((list.get(i).getHome_points() - list.get(i).getAway_points()) < min) {
				min = (list.get(i).getHome_points() - list.get(i)
						.getAway_points());
			}

		}
		int c = (max - min) / 13;
		System.out.println(result);
		System.out.println("MAX: " + max + "   MIN: " + min + "     "
				+ (max - min) / 13);

		for (int i = 0; i < list.size(); i++) {
			for (int j = 0; j < 13; j++)
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()) > min + j * c)
						&& ((list.get(i).getHome_points() - list.get(i)
								.getAway_points()) < min + j * c + c)) {
					pingshu[j]++;
				}
		}
		
		for( int i=0;i<13;i++){
			System.out.println(pingshu[i]);
		}
		System.out.println(list.size());
	}

	public static int indexofStr(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name))
				return i;
		}
		return 30;
	}
}
