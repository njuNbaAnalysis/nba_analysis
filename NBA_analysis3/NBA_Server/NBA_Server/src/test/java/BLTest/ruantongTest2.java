package BLTest;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import po.match;
import data.matches.MatchReader;

public class ruantongTest2 {

	static String[] names = { "POR", "SAC", "GSW", "LAL", "LAC", "UTA", "PHO",
			"DEN", "MIN", "OKC", "DAL", "SAS", "HOU", "NOP", "MEM", "MIL",
			"DET", "IND", "CLE", "ATL", "BOS", "NYK", "BKN", "WAS", "CHA",
			"ORL", "MIA", "PHI", "CHI", "TOR" };
	static double[] OSW = new double[30];
	static double[] DSW = new double[30];
	static int[] gameplayed = new int[30];

	public static void main(String[] args) {
		for (int i = 0; i < 30; i++) {
			OSW[i] = 1;
			DSW[i] = 1;
		}

		double lemada = 0.5;
		double KO = 1.08;
		double KD = 1.08;

		MatchReader mr = new MatchReader();
		ArrayList<match> list = mr.getMatchesBySeason("14-15", false);

		int[] points = new int[list.size()];
		// String result = "";
		for (int i = 0; i < list.size(); i++) {
			// result += (list.get(i).getHome_points() ) + ",";

			OSW[indexofStr(list.get(i).getHome_team())] = lemada
					* OSW[indexofStr(list.get(i).getHome_team())]
					+ (1 - lemada) * KO
					* DSW[indexofStr(list.get(i).getAway_team())]
					/ OSW[indexofStr(list.get(i).getHome_team())];

			DSW[indexofStr(list.get(i).getHome_team())] = lemada
					* DSW[indexofStr(list.get(i).getHome_team())]
					+ (1 - lemada) * KD
					* OSW[indexofStr(list.get(i).getAway_team())]
					/ DSW[indexofStr(list.get(i).getHome_team())];
			
			
			OSW[indexofStr(list.get(i).getAway_team())] = lemada
					* OSW[indexofStr(list.get(i).getAway_team())]
					+ (1 - lemada) /KD
					* DSW[indexofStr(list.get(i).getHome_team())]
					/ OSW[indexofStr(list.get(i).getAway_team())];

			DSW[indexofStr(list.get(i).getAway_team())] = lemada
					* DSW[indexofStr(list.get(i).getAway_team())]
					+ (1 - lemada) /KO
					* OSW[indexofStr(list.get(i).getHome_team())]
					/ DSW[indexofStr(list.get(i).getAway_team())];
			
			if(list.get(i).getHome_team().equals("GSW")){
				System.out.println(OSW[indexofStr("GSW")] +"  "+ DSW[indexofStr("GSW")]  );
			}
			gameplayed[indexofStr(list.get(i).getHome_team())]++;
			gameplayed[indexofStr(list.get(i).getAway_team())]++;
		}

		// try {
		// ProcessBuilder pb = new ProcessBuilder("python",
		// "Spider-NBA/test.py", result.substring(0, result.length()-1));
		// Process p = pb.start();
		// InputStreamReader isr = new InputStreamReader(p.getInputStream(),
		// "GBK");
		// BufferedReader in = new BufferedReader(isr);
		// String line;
		//
		// ArrayList<String> res = new ArrayList<>();
		// while ((line = in.readLine()) != null) {
		// res.add(line);
		// System.out.println(res);
		// }
		//
		//
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	public static int indexofStr(String name) {
		for (int i = 0; i < names.length; i++) {
			if (names[i].equals(name))
				return i;
		}
		return 30;
	}
}
