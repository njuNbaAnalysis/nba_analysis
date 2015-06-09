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
		ArrayList<matchsimpleitem> result = new ArrayList<matchsimpleitem>();

		int[] gameplayed = new int[30];

		double k = 0.31;
		double theta = 0.7;
		double h = 3.97;

//		ArrayList<match> list2 = mr.getMatchesBySeason("13-14", false);
//		for (int i = 0; i < list2.size(); i++) {
//			for (int j = 0; j < names.length; j++) {
//				if (names[j].equals(list2.get(i).getHome_team())) {
//					points[indexofStr(names[j])] += (list2.get(i)
//							.getHome_points() - list2.get(i).getAway_points());
//				} else if (names[j].equals(list2.get(i).getAway_team())) {
//					points[indexofStr(names[j])] += (0 - list2.get(i)
//							.getHome_points() + list2.get(i).getAway_points());
//				}
//			}
//		}

//		for (int i = 0; i < 30; i++) {
//			points[i]/=82;
//			System.out.println(points[i]);
//		}
		// double min = 1000000000;
		// for(double k = 0.1;k<1;k+=0.01){
		// for (double theta = 0; theta < 1; theta += 0.1) {
		// double r = getFangcha(list, h, theta, k);
		// if (r < min) {
		// min = r;
		// theta1 = theta;
		// k1 = k;
		// }
		// }}
		// System.out.println(k1 + " " + theta1 + " " + h);
		// ;
		for (int i = 0; i < list.size(); i++) {
			result.add(new matchsimpleitem((i + 1), list.get(i).getHome_team(),
					list.get(i).getAway_team(),
					(list.get(i).getHome_points() - list.get(i)
							.getAway_points())));

			gameplayed[indexofStr(list.get(i).getHome_team())]++;
			gameplayed[indexofStr(list.get(i).getAway_team())]++;

			points[indexofStr(list.get(i).getHome_team())] += (Math.expm1((-1)
					* k * (i + 1)) + 1)
					* theta
					* ((list.get(i).getHome_points() - list.get(i)
							.getAway_points()) - ((points[indexofStr(list
							.get(i).getHome_team())])
							- points[indexofStr(list.get(i).getAway_team())] + h));

			points[indexofStr(list.get(i).getAway_team())] += (Math.expm1((-1)
					* k * (i + 1)) + 1)
					* theta
					* (-1)
					* ((list.get(i).getHome_points() - list.get(i)
							.getAway_points()) - ((points[indexofStr(list
							.get(i).getHome_team())])
							- points[indexofStr(list.get(i).getAway_team())] + h));

			if (list.get(i).getHome_team().equals("CLE")) {
				System.out
						.println("对手: "
								+ list.get(i).getAway_team()
								+ " 实际："
								+ (list.get(i).getHome_points() - list.get(i)
										.getAway_points())
								+ " 预估 ："
								+ (points[indexofStr(list.get(i).getHome_team())] - points[indexofStr(list
										.get(i).getAway_team())]));
			}

			if (list.get(i).getAway_team().equals("CLE")) {
				System.out
						.println("对手: "
								+ list.get(i).getHome_team()
								+ " 实际："
								+ (list.get(i).getAway_points() - list.get(i)
										.getHome_points())
								+ " 预估 ："
								+ (points[indexofStr(list.get(i).getAway_team())] - points[indexofStr(list
										.get(i).getHome_team())]));
			}

		}
	}

	public static double getFangcha(ArrayList<match> list, double h,
			double theta, double k) {
		double result = 0;
		double[] points = new double[30];

		for (int i = 0; i < list.size(); i++) {
			result += ((list.get(i).getHome_points() - list.get(i)
					.getAway_points()) - (points[indexofStr(list.get(i)
					.getHome_team())] - points[indexofStr(list.get(i)
					.getAway_team())]))
					* ((list.get(i).getHome_points() - list.get(i)
							.getAway_points()) - (points[indexofStr(list.get(i)
							.getHome_team())] - points[indexofStr(list.get(i)
							.getAway_team())]));

			points[indexofStr(list.get(i).getHome_team())] += (Math.expm1((-1)
					* k * (i + 1)) + 1)
					* theta
					* ((list.get(i).getHome_points() - list.get(i)
							.getAway_points()) - ((points[indexofStr(list
							.get(i).getHome_team())])
							- points[indexofStr(list.get(i).getAway_team())] + h));

			points[indexofStr(list.get(i).getAway_team())] += (Math.expm1((-1)
					* k * (i + 1)) + 1)
					* theta
					* (-1)
					* ((list.get(i).getHome_points() - list.get(i)
							.getAway_points()) - ((points[indexofStr(list
							.get(i).getHome_team())])
							- points[indexofStr(list.get(i).getAway_team())] + h));

		}
		System.out.println("result:" + result);
		return result / list.size();
	}

	public static int indexofStr(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name))
				return i;
		}
		return 30;
	}
}
