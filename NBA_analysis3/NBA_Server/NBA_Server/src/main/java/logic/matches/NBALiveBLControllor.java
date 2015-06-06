package logic.matches;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import vo.EventVo;
import vo.Matchvo;
import vo.RecordOfPlayervo;

public class NBALiveBLControllor {
	private int period;
	private String Id;

	private static NBALiveBLControllor NBALiveBLControllor = null;

	private NBALiveBLControllor() {
		
	}

	public static NBALiveBLControllor getInstance() {
		if (NBALiveBLControllor != null) {
			return NBALiveBLControllor;
		} else {
			NBALiveBLControllor = new NBALiveBLControllor();
			return NBALiveBLControllor;
		}
	}
	
	public boolean setPeriod(int period){
		this.period = period;
		return true;
	}

	public Matchvo getLiveMatchInfo() {
		// TODO Auto-generated method stub
		ArrayList<RecordOfPlayervo> firstRecordList = NBALiveMatchItem(getId(),
				true);
		ArrayList<RecordOfPlayervo> secondRecordList = NBALiveMatchItem(
				getId(), false);
		NBALiveMatch Livematch = NBALiveCompare(getId());
		Matchvo m = new Matchvo(Livematch.getDate(),true,Livematch.getTeams(),
				Livematch.getPoints(), Livematch.getPointsList(),
				firstRecordList, secondRecordList, Livematch.getRebounds(),
				Livematch.getAssists(), Livematch.getBlocks(),
				Livematch.getTurnOver(), Livematch.getQuickPoints(),
				Livematch.getRestrictedPoints(), Livematch.getTurnOverPoints(),
				Livematch.getMaxPoints(), Livematch.getFieldGoalsPercentage(),
				Livematch.getThreePointersPercentage(),
				Livematch.getFieldGoalsPercentage());
		return m;
	}

	public ArrayList<EventVo> getLiveEvent() {
		// TODO Auto-generated method stub
		ArrayList<EventVo> list = NBALive(getPeriod());
		if(list.size()==0) return list;
		if (list.get(0).getTime().equals("00:00.0"))
			increasePeriod();
		return list;
	}

	private ArrayList<EventVo> NBALive(int period) {
		ArrayList<EventVo> result = new ArrayList<EventVo>();

		InvokeLive NBALive = new InvokeLive(period, getId());
		NBALive.run();
		try {
			ArrayList<String> Live = NBALive.getLive();
			if (Live.size() >2) {
				String Home_TeamId = Live.get(2).split(",")[1];
				String Away_TeamId = Live.get(2).split(",")[3];
				for (int i = 6; i < Live.size(); i++) {
					String[] data = Live.get(i).split(",");
					if (data.length == 22) {
						String playerName = NBALiveList
								.getPlayerNameById(data[2]);
						String teamName = NBALiveList.getTeamNameById(data[19]);
						int num = 0;
						if (data[19].equals(Home_TeamId))
							num = 0;
						else if (data[19].equals(Away_TeamId))
							num = 1;
						result.add(new EventVo(period, num, data[3], data[0]
								+ "-" + data[4], playerName, data[1], teamName));
					}
				}
			} else {
				
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	private ArrayList<RecordOfPlayervo> NBALiveMatchItem(String Id,
			boolean ishome) {
		ArrayList<RecordOfPlayervo> list = new ArrayList<RecordOfPlayervo>();
		InvokeLiveMatchItem NBALiveMatchItem = new InvokeLiveMatchItem(Id,
				ishome);
		NBALiveMatchItem.run();
		try {
			ArrayList<String> Live = NBALiveMatchItem.getLive();
			for (int i = 0; i < Live.size(); i += 6) {
				String[] data1 = Live.get(i).split(",");
//				String[] data2 = Live.get(i + 2).split(",");
				String[] data3 = Live.get(i + 4).split(",");
				list.add(new RecordOfPlayervo(data1[4], data1[16],
						Integer.parseInt(data3[10]) * 60
								+ Integer.parseInt(data3[14]), Integer
								.parseInt(data3[4]),
						Integer.parseInt(data3[3]),
						Integer.parseInt(data3[17]), Integer
								.parseInt(data3[16]), Integer
								.parseInt(data3[8]),
						Integer.parseInt(data3[7]),
						Integer.parseInt(data3[11]),
						Integer.parseInt(data3[2]),
						Integer.parseInt(data3[13]),
						Integer.parseInt(data3[0]),
						Integer.parseInt(data3[15]),
						Integer.parseInt(data3[1]),
						Integer.parseInt(data3[19]),
						Integer.parseInt(data3[6]),
						Integer.parseInt(data3[12]), false, Double
								.parseDouble(data3[18]), Double
								.parseDouble(data3[9]), Double
								.parseDouble(data3[5])));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	private NBALiveMatch NBALiveCompare(String Id) {
		NBALiveMatch match = null;
		InvokeLiveCompare NBALiveCompareOFHome = new InvokeLiveCompare(Id);
		ArrayList<String> Live = null;
		NBALiveCompareOFHome.run();
		// NBALiveCompareOFAway.run();
		try {
			Live = NBALiveCompareOFHome.getLive();

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if (Live != null) {
			String[] teams = { Live.get(0).split(",")[0],
					Live.get(4).split(",")[0] };
			String[] data1 = Live.get(2).split(",");
			String[] data2 = Live.get(6).split(",");
			Date now = new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			String date = df.format(now).substring(0, 10);
			if(Integer.parseInt(date.substring(5, 6))<=8){
				date = (Integer.parseInt(date.substring(2, 4))-1)+"-"+date.substring(2, 4)+"_"+date;
			}else{
				date = date.substring(2, 4)+"-"+(Integer.parseInt(date.substring(2, 4))+1)+"_"+date;
			}
			int[] rebounds = { Integer.parseInt(data1[36]),
					Integer.parseInt(data2[36]) };
			int[] assists = { Integer.parseInt(data1[0]),
					Integer.parseInt(data2[0]) };
			int[] turnOver = { Integer.parseInt(data1[44]),
					Integer.parseInt(data2[44]) };
			int[] maxPoints = { Integer.parseInt(data1[1]),
					Integer.parseInt(data2[1]) };
			int[] blocks = { Integer.parseInt(data1[2]),
					Integer.parseInt(data2[2]) };
			int[] quickPoints = { Integer.parseInt(data1[7]),
					Integer.parseInt(data2[7]) };
			int[] restrictedPoints = { Integer.parseInt(data1[29]),
					Integer.parseInt(data2[29]) };
			int[] turnOverPoints = { Integer.parseInt(data1[30]),
					Integer.parseInt(data2[30]) };
			int[] points = { Integer.parseInt(data1[36]),
					Integer.parseInt(data2[36]) };
			double[] fieldGoalsPercentage = { Double.parseDouble(data1[10]),
					Double.parseDouble(data2[10]) };
			double[] threePointersPercentage = { Double.parseDouble(data1[43]),
					Double.parseDouble(data2[43]) };
			double[] freeThrowsPercentage = { Double.parseDouble(data1[15]),
					Double.parseDouble(data2[15]) };
			ArrayList<int[]> pointsList = new ArrayList<int[]>();
			for (int i = 31; i <= 34; i++) {
				int[] temp = { Integer.parseInt(data1[i]),
						Integer.parseInt(data2[i]) };
				pointsList.add(temp);
			}
			for (int i = 20; i < 29; i++) {
				if (Integer.parseInt(data1[i]) == 0
						&& Integer.parseInt(data2[i]) == 0) {
					break;
				} else {
					int[] temp = { Integer.parseInt(data1[i]),
							Integer.parseInt(data2[i]) };
					pointsList.add(temp);
				}
			}
			match = new NBALiveMatch(rebounds, assists, blocks, turnOver,
					quickPoints, restrictedPoints, turnOverPoints, maxPoints,
					fieldGoalsPercentage, threePointersPercentage,
					freeThrowsPercentage, date, teams, points, pointsList);
		}
		return match;
	}

	private void increasePeriod() {
		period++;
	}

	private int getPeriod() {
		return this.period;
	}

	private String getId() {
		Date now = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
		String current = df.format(now).substring(0, 10);
		Id = NBALiveList.NBAMatchLiveList.get(current);
		return "0041400401";
	}

}
