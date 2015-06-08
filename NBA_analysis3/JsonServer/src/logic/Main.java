package logic;

import java.util.ArrayList;

import logic.matches.NBALiveBLControllor;
import vo.EventVo;
import vo.Matchvo;
import vo.RecordOfPlayervo;

public class Main {
    public static void main(String[] args) {

		final NBALiveBLControllor mbl = NBALiveBLControllor.getInstance();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mbl.setPeriod(1);
				Matchvo m = mbl.getLiveMatchInfo();
				System.out.println(m.getTeams()[0]+" "+m.getRebounds()[0] + " "
						+ m.getFreeThrowsPercentage()[0] + " "
						+ m.getFieldGoalsPercentage()[0]);
				System.out.println(m.getTeams()[1]+" "+m.getRebounds()[1] + " "
						+ m.getFreeThrowsPercentage()[1] + " "
						+ m.getFieldGoalsPercentage()[1]);
				ArrayList<RecordOfPlayervo> list1 = m.getFirstRecordList();
				ArrayList<RecordOfPlayervo> list2 = m.getSecondRecordList();
				for(int i=0;i<list1.size();i++){
					System.out.println("1: "+list1.get(i).getPlayerName());
				}
				for(int i=0;i<list2.size();i++){
					System.out.println("2: "+list2.get(i).getPlayerName());
				}
				while (true) {
					ArrayList<EventVo> list = mbl.getLiveEvent();
					System.out.println(list.size());
					if (list.size() == 0)
						break;
					for (int i = 0; i < list.size(); i++) {
						System.out.println(list.get(i).getSection() + " : "
								+ list.get(i).getTime() + " : "
								+ list.get(i).getDescription() + "  "
								+ list.get(i).getPlayerName() + " : "
								+ list.get(i).getTeamName());
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}).start();
	}
}
