package logic.matches;

import java.util.ArrayList;

import po.match;
import data.matches.MatchReader;

public class MatchWinPercentage {

	static String[] names = { "POR", "SAC", "GSW", "LAL", "LAC", "UTA", "PHX",
			"DEN", "MIN", "OKC", "DAL", "SAS", "HOU", "NOP", "MEM", "MIL",
			"DET", "IND", "CLE", "ATL", "BOS", "NYK", "BKN", "WAS", "CHA",
			"ORL", "MIA", "PHI", "CHI", "TOR" };

	public static double getWinPercent(String teamName1, String teamName2,
			String season, boolean isplayoff) {
		double[] points = new double[30];

		MatchReader mr = new MatchReader();
		ArrayList<match> list = mr.getMatchesBySeason(season, isplayoff);

		double[] result = new double[30];
		double[] resultPred = new double[20];
		double[] resultReal = new double[20];

		double k = 0;
		double theta = 0.052;
		double h = 2.4097560975609755;

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
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 11) {
				resultPred[1]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[1]++;
				}
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 9) {
				resultPred[2]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[2]++;
				}
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 7.5) {
				resultPred[3]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[3]++;
				}
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 6.5) {
				resultPred[4]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[4]++;
				}
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 5) {
				resultPred[5]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[5]++;
				}
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 4.5) {
				resultPred[6]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[6]++;
				}
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 4) {
				resultPred[7]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[7]++;
				}
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 3.5) {
				resultPred[8]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[8]++;
				}
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 2.5) {
				resultPred[9]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[9]++;
				}
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 2) {
				resultPred[10]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[10]++;
				}
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 1.5) {
				resultPred[11]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 1) {
					resultReal[11]++;
				}
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 1) {
				resultPred[12]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[12]++;
				}
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 0.5) {
				resultPred[13]++;
				if (((list.get(i).getHome_points() - list.get(i)
						.getAway_points()))
						* ((points[indexofStr(list.get(i).getHome_team())]
								- points[indexofStr(list.get(i).getAway_team())] + h)) > 0) {
					resultReal[13]++;
				}
			} else if (Math.abs(points[indexofStr(list.get(i).getHome_team())]
					- points[indexofStr(list.get(i).getAway_team())] + h) > 0) {
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

		}
		double temp = (points[indexofStr(teamName1)]
				- points[indexofStr(teamName2)] + h);
		if (Math.abs(temp) > 15) {
			return resultReal[0] / resultPred[0];
		} else if (Math.abs(temp) > 11) {
			return resultReal[1] / resultPred[1];
		} else if (Math.abs(temp) > 9) {
			return resultReal[2] / resultPred[2];
		} else if (Math.abs(temp) > 7.5) {
			return resultReal[3] / resultPred[3];
		} else if (Math.abs(temp) > 6.5) {
			return resultReal[4] / resultPred[4];
		} else if (Math.abs(temp) > 5) {
			return resultReal[5] / resultPred[5];
		} else if (Math.abs(temp) > 4.5) {
			return resultReal[6] / resultPred[6];
		} else if (Math.abs(temp) > 4) {
			return resultReal[7] / resultPred[7];
		} else if (Math.abs(temp) > 3.5) {
			return resultReal[8] / resultPred[8];
		} else if (Math.abs(temp) > 2.5) {
			return resultReal[9] / resultPred[9];
		} else if (Math.abs(temp) > 2) {
			return resultReal[10] / resultPred[10];
		} else if (Math.abs(temp) > 1.5) {
			return (resultReal[11] + 4)/ (resultPred[11] + 4);
		} else if (Math.abs(temp) > 1) {
			return resultReal[12] / resultPred[12];
		} else if (Math.abs(temp) > 0.5) {
			return resultReal[13] / resultPred[13];
		} else if (Math.abs(temp) > 0) {
			return (resultReal[14] - 1) / resultPred[14];
		}
		return 0;
	}

	public static int indexofStr(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name))
				return i;
		}
		return 30;
	}

}
