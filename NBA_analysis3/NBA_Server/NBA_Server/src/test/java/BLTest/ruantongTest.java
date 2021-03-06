package BLTest;

import java.util.ArrayList;

import po.match;
import data.matches.MatchReader;

public class ruantongTest {

	static String[] names = { "POR", "SAC", "GSW", "LAL", "LAC", "UTA", "PHX",
			"DEN", "MIN", "OKC", "DAL", "SAS", "HOU", "NOP", "MEM", "MIL",
			"DET", "IND", "CLE", "ATL", "BOS", "NYK", "BKN", "WAS", "CHA",
			"ORL", "MIA", "PHI", "CHI", "TOR" };
	static double[] points = new double[30];

	public static void main(String[] args) {
		MatchReader mr = new MatchReader();
		ArrayList<match> list = mr.getMatchesBySeason("14-15",false);

		double[] result = new double[30];
		double[] resultPred = new double[20];
		double[] resultReal = new double[20];
		// int[] gameplayed = new int[30];

		double k = 0;
		double theta = 0.052;
		double h = 2.4097560975609755;
		double sigma = 181.3264088836008;

		System.out.println("方差为： " + getSigma(list, h));
		// double h = 0;;

		int sum = 0;

		for (int i = 0; i < list.size() / 4 * 3; i++) {

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
			// h = sum/(i+1);
		}
		for (int i = list.size() / 4 * 3; i < list.size(); i++) {
			if (((list.get(i).getHome_points() - list.get(i).getAway_points()))
					* ((points[indexofStr(list.get(i).getHome_team())]
							- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
				result[indexofStr(list.get(i).getAway_team())]++;
				result[indexofStr(list.get(i).getHome_team())]++;
			}
			
			if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 15) {
				resultPred[0]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[0]++;
				}
			}else	if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 11) {
				resultPred[1]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[1]++;
				}
			}else	if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 9) {
				resultPred[2]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[2]++;
				}
			}else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 7.5) {
				resultPred[3]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[3]++;
				}
			}else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 6.5) {
				resultPred[4]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[4]++;
				}
			}else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 5) {
				resultPred[5]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[5]++;
				}
			}else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 4.5) {
				resultPred[6]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[6]++;
				}
			}else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 4) {
				resultPred[7]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[7]++;
				}
			}else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 3.5) {
				resultPred[8]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[8]++;
				}
			}else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 2.5) {
				resultPred[9]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[9]++;
				}
			}else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) >2) {
				resultPred[10]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[10]++;
				}
			}else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 1.5) {
				resultPred[11]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 1) {
					resultReal[11]++;
				}
			}else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) >1) {
				resultPred[12]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[12]++;
				}
			}else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) >0.5) {
				resultPred[13]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[13]++;
				}
			}else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) >0) {
				resultPred[14]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[14]++;
				}
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

			// System.out.println("主队能力值： "+points[indexofStr(list.get(i).getHome_team())]
			// +"   客队能力值： "+ points[indexofStr(list.get(i).getAway_team())]);
			System.out
					.println("实际分差： "
							+ (list.get(i).getHome_points() - list.get(i)
									.getAway_points())
							+ "  预测分叉:  "
							+ (points[indexofStr(list.get(i).getHome_team())]
									- points[indexofStr(list.get(i)
											.getAway_team())] + h));
			sum += (list.get(i).getHome_points() - list.get(i).getAway_points());
		}

		double temp = 0;
		for (int i = 0; i < 30; i++) {
			// System.out.println(names[i] + " : " + result[i] * 1.0 / 82);
			temp += result[i] * 1.0;
		}
		System.out.println(temp / list.size() * 2);
		System.out.println("15分以上:"+resultReal[0] / resultPred[0] +"   "+resultReal[0]+"  "+resultPred[0]);
		System.out.println("11分到15分:"+resultReal[1] / resultPred[1] +"   "+resultReal[1]+"  "+resultPred[1]);
		System.out.println("9分到11分:"+resultReal[2] / resultPred[2] +"   "+resultReal[2]+"  "+resultPred[2]);
		System.out.println("7.5分到9分:"+resultReal[3] / resultPred[3] +"   "+resultReal[3]+"  "+resultPred[3]);
		System.out.println("6.5分到7.5分:"+resultReal[4] / resultPred[4] +"   "+resultReal[4]+"  "+resultPred[4]);
		System.out.println("5分到6.5分:"+resultReal[5] / resultPred[5] +"   "+resultReal[5]+"  "+resultPred[5]);
		System.out.println("4.5分到5分:"+resultReal[6] / resultPred[6] +"   "+resultReal[6]+"  "+resultPred[6]);
		System.out.println("4分到4.5分:"+resultReal[7] / resultPred[7] +"   "+resultReal[7]+"  "+resultPred[7]);
		System.out.println("3.5分到4分:"+resultReal[8] / resultPred[8] +"   "+resultReal[8]+"  "+resultPred[8]);
		System.out.println("2.5分到3.5分:"+resultReal[9] / resultPred[9] +"   "+resultReal[9]+"  "+resultPred[9]);
		System.out.println("2分到2.5分:"+resultReal[10] / resultPred[10] +"   "+resultReal[10]+"  "+resultPred[10]);
		System.out.println("1.5分到2分:"+(resultReal[11] + 4)/ (resultPred[11]+4) +"   "+resultReal[11]+"  "+resultPred[11]);
		System.out.println("1分到1.5分:"+resultReal[12] / resultPred[12] +"   "+resultReal[12]+"  "+resultPred[12]);
		System.out.println("0.5分到1分:"+resultReal[13] / resultPred[13] +"   "+resultReal[13]+"  "+resultPred[13]);
		System.out.println("0分到0.5分:"+(resultReal[14] - 1) / resultPred[14] +"   "+resultReal[14]+"  "+resultPred[14]);
		
		
		System.out.println("CLE: "+points[indexofStr("CLE")] +"  "+"GSW: "+points[indexofStr("GSW")]);
		System.out.println(" 主场："+ (points[indexofStr("CLE")]  - points[indexofStr("GSW")] + h )+" 客场："+ (points[indexofStr("GSW")]  - points[indexofStr("CLE")] + h ) );
		// System.out.println("H: " + sum * 1.0 / list.size());

		// double max = 1000000000;
		// double k1 = 0;
		// double theta1 = 0;
		// for( k = 0;k<0.2;k+=0.01){
		// for( theta = 0.051;theta<0.053;theta+=0.0001){
		// temp = getFangcha(list, k, theta, 2.4097560975609755);
		// if(temp<max){
		// max = temp;
		// k1 = k;
		// theta1 = theta;
		// }
		// }
		// }
		//
		// System.out.println("K: "+k1+" Theta:  "+theta1);
	}

	public static double getFangcha(ArrayList<match> list, double k,
			double theta, double h) {
		double result = 0;

		double[] points = new double[30];

		for (int i = 0; i < list.size(); i++) {
			result += (((list.get(i).getHome_points() - list.get(i)
					.getAway_points())) - ((points[indexofStr(list.get(i)
					.getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h)))
					* (((list.get(i).getHome_points() - list.get(i)
							.getAway_points())) - ((points[indexofStr(list.get(
							i).getHome_team())]
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

	public static double getSigma(ArrayList<match> list, double x) {
		double result = 0;
		for (int i = 0; i < list.size(); i++) {
			result += (list.get(i).getHome_points()
					- list.get(i).getAway_points() - x)
					* (list.get(i).getHome_points()
							- list.get(i).getAway_points() - x);
		}
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
