package ui;

import java.util.ArrayList;

import dataFactory.DataFactoryMySql;
import BLservice.BLservice;


public class Start {

	public static void main(String[] args) {
		int progress = 0;

		long past = System.currentTimeMillis();
		
		final BLservice bl = DataFactoryMySql.getInstance().getBLservice();
		
		final ImageReader reader = ImageReader.getInstance();
		final AnimationFrame animation = new AnimationFrame(reader);
		
		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {
				while (reader.getProgress() < 9) {
					System.out.println(reader.getProgress());
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
		try {
            thread.join();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		
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
