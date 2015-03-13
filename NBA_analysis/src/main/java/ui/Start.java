package ui;

import logic.BLController;
import ui.AnimationFrame;

public class Start {

	public static void main(String[] args) {
		int progress = 0;

		/*final BLController bl = BLController.getInstance();

		final AnimationFrame animation = new AnimationFrame(bl);

		new Thread(new Runnable() {

			@Override
			public void run() {
				while (bl.getProgress() < 6) {
					System.out.println(bl.getProgress());
				}
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("sssss");
				animation.dispose();
				
				MainFrame start = new MainFrame();
				start.setVisible(true);
			}
		}).start();*/
		// Thread.sleep(3000);

		/*try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}*/
		
		
		MainFrame start = new MainFrame();
		start.setVisible(true);
	}
}
