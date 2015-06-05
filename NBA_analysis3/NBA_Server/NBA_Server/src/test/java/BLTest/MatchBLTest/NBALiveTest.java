package BLTest.MatchBLTest;

import java.util.ArrayList;

import vo.EventVo;
import logic.matches.matchBLcontrollor;

public class NBALiveTest {

	public static void main(String[] args) {

		final matchBLcontrollor mbl = matchBLcontrollor.getInstance();

		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true) {
					ArrayList<EventVo> list = mbl.getLiveEvent();
					for (int i = 0; i < list.size(); i++) {
						System.out.println(list.get(i).getSection()+" : "+list.get(i).getTime() + " : "
								+ list.get(i).getDescription());
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
