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


		double[] result = new double[30];
//		int[] gameplayed = new int[30];
		
		double k = 0;
		double theta = 0.052;
		double h =2.4097560975609755;
//		double h = 0;;

		int sum = 0;

		for (int i = 0; i < list.size()/4*3; i++) {
			
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

			sum += (list.get(i).getHome_points() - list.get(i).getAway_points());
//			h = sum/(i+1);
		}
		for(int i=list.size()/4*3;i<list.size();i++){
			if (((list.get(i).getHome_points() - list.get(i).getAway_points()))
					* ((points[indexofStr(list.get(i).getHome_team())]
							- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
				result[indexofStr(list.get(i).getAway_team())]++;
				result[indexofStr(list.get(i).getHome_team())]++;
			}
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

			sum += (list.get(i).getHome_points() - list.get(i).getAway_points());
		}

		double temp = 0;
		for (int i = 0; i < 30; i++) {
//			System.out.println(names[i] + " : " + result[i] * 1.0 / 82);
			temp += result[i] * 1.0;
		}
		System.out.println(temp/ list.size()*2);

		System.out.println("H: " + sum * 1.0 / list.size());
		
		double max = 1000000000;
		double k1 = 0;
		double theta1 = 0;
		for( k = 0;k<0.2;k+=0.01){
			for( theta = 0.051;theta<0.053;theta+=0.0001){
				 temp = getFangcha(list, k, theta, 2.4097560975609755);
				if(temp<max){
					max = temp;
					k1 = k;
					theta1 = theta;
				}
			}
		}

		System.out.println("K: "+k1+" Theta:  "+theta1);
	}

	public static double getFangcha(ArrayList<match> list, double k,
			double theta, double h) {
		double result = 0;

		double[] points = new double[30];
		
		for (int i = 0; i < list.size(); i++) {
			result += (((list.get(i).getHome_points() - list.get(i)
					.getAway_points())) - ((points[indexofStr(list.get(i)
					.getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h))) * (((list.get(i).getHome_points() - list.get(i)
							.getAway_points())) - ((points[indexofStr(list.get(i)
									.getHome_team())]
									- points[indexofStr(list.get(i).getAway_team())] + h)));

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
