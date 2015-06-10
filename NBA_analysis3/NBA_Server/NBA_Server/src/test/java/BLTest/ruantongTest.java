package BLTest;

import java.util.ArrayList;

import po.match;
import data.matches.MatchReader;

public class ruantongTest {

	static String[] names = { "POR", "SAC", "GSW", "LAL", "LAC", "UTA", "PHO",
			"DEN", "MIN", "OKC", "DAL", "SAS", "HOU", "NOP", "MEM", "MIL",
			"DET", "IND", "CLE", "ATL", "BOS", "NYK", "BKN", "WAS", "CHA",
			"ORL", "MIA", "PHI", "CHI", "TOR" };
	static double[] points = new double[30];

	public static void main(String[] args) {
		MatchReader mr = new MatchReader();
		ArrayList<match> list = mr.getMatchesBySeason("14-15", false);
		// ArrayList<matchsimpleitem> result = new ArrayList<matchsimpleitem>();

		int[] gameplayed = new int[30];
		int[] result = new int[30];

		double k = 0.31;
		double theta = 0.7;
		double h = 3.97;

		// ArrayList<match> list2 = mr.getMatchesBySeason("13-14", false);
		// for (int i = 0; i < list2.size(); i++) {
		// for (int j = 0; j < names.length; j++) {
		// if (names[j].equals(list2.get(i).getHome_team())) {
		// points[indexofStr(names[j])] += (list2.get(i)
		// .getHome_points() - list2.get(i).getAway_points());
		// } else if (names[j].equals(list2.get(i).getAway_team())) {
		// points[indexofStr(names[j])] += (0 - list2.get(i)
		// .getHome_points() + list2.get(i).getAway_points());
		// }
		// }
		// }

//		double min = 0;
//		double temp = 0;
//		
//		for (h = -3; h <-2; h += 0.01) {
//			for(int m = 0;m<30;m++){
//				result[m] = 0;
//			}
			for (int i = 0; i < list.size(); i++) {

				gameplayed[indexofStr(list.get(i).getHome_team())]++;
				gameplayed[indexofStr(list.get(i).getAway_team())]++;

				points[indexofStr(list.get(i).getHome_team())] += (Math
						.expm1((-1) * k * (i + 1)) + 1)
						* theta
						* ((list.get(i).getHome_points() - list.get(i)
								.getAway_points()) - ((points[indexofStr(list
								.get(i).getHome_team())])
								- points[indexofStr(list.get(i).getAway_team())] + h));

				points[indexofStr(list.get(i).getAway_team())] += (Math
						.expm1((-1) * k * (i + 1)) + 1)
						* theta
						* (-1)
						* ((list.get(i).getHome_points() - list.get(i)
								.getAway_points()) - ((points[indexofStr(list
								.get(i).getHome_team())])
								- points[indexofStr(list.get(i).getAway_team())] + h));

				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					result[indexofStr(list.get(i).getAway_team())]++;
					result[indexofStr(list.get(i).getHome_team())]++;
				}
				
			}
//			double[] calcu = new double[30];
//			for(int n=0;n<30;n++){
//				calcu[n] = result[n]*1.0/82;
//			}
//			if(getFangcha(calcu)>min){
//				min = getFangcha(calcu);
//				temp = h;
//				System.out.println("temp：" + temp);
//			}
//		}

//		System.out.println(temp);
			double temp = 0;
		for (int i = 0; i < 30; i++) {
			System.out.println(names[i] + " : " + result[i] * 1.0 / 82);
			temp += result[i] * 1.0 / 82;
		}
		System.out.println(temp/30);
	}

	public static double getFangcha(double[] list) {
		double result = 0;

		for (int i = 0; i < list.length; i++) {
			result += list[i];

		}
		System.out.println("result:" + result);
		return result / list.length;
	}

	public static int indexofStr(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name))
				return i;
		}
		return 30;
	}
}
