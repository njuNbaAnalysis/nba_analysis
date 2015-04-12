package ui;

import logic.BLController;
import logic.matches.KingOfMatch;
import logic.matches.Match;

public class Start {

	public static void main(String[] args) {
		int progress = 0;

		long past = System.currentTimeMillis();
		
		final BLController bl = BLController.getInstance();
		
		final AnimationFrame animation = new AnimationFrame(bl);

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (bl.getProgress() < 9) {
					System.out.println(bl.getProgress());
				    try {
	                    Thread.sleep(300);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
				    
				}
				animation.dispose();
				
				MainFrame start = new MainFrame(bl);
				start.setVisible(true);
			}
		});
		thread.start();
		bl.init();
		try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
		Match match = bl.getTodayMatches("13-14_2014-01-01").get(0);
		KingOfMatch[] kings = match.getKingsOfMatch();
		System.out.println(kings[0]);
		
		long now = System.currentTimeMillis();
		System.out.print("初始化：" + (now - past) / 1000.0 + "s");
		
		// Thread.sleep(3000);

		/*try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		
		/*MainFrame start = new MainFrame(null);
		start.setVisible(true);*/
	}
}
