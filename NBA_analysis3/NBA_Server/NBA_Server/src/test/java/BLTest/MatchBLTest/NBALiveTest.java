package BLTest.MatchBLTest;

import java.util.ArrayList;

import po.match;
import data.matches.MatchReader;
import vo.EventVo;
import logic.matches.NBALiveBLControllor;
import logic.matches.matchBLcontrollor;
import logic.teams.TeamNameList;

public class NBALiveTest {

	public static void main(String[] args) {
		
		final NBALiveBLControllor mbl = NBALiveBLControllor.getInstance();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				mbl.setPeriod(1);
				while (true) {
					ArrayList<EventVo> list = mbl.getLiveEvent();
					if(list.size()==0) break;
					for (int i = 0; i < list.size(); i++) {
						System.out.println(list.get(i).getSection()+" : "+list.get(i).getTime() + " : "
								+ list.get(i).getDescription()+"  "+list.get(i).getPlayerName()+" : "+list.get(i).getTeamName());
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
