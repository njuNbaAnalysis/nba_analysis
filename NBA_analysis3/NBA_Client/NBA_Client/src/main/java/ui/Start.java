package ui;



import java.rmi.RemoteException;
import java.util.ArrayList;





import ui.start.AnimationFrame;
import ui.start.MainFrame;
import ui.team.ImageReader;
import dataFactory.DataFactoryMySql;
import BLservice.BLservice;


public class Start {

	public static void main(String[] args) {
		
		int progress = 0;

		long past = System.currentTimeMillis();
		
		final BLservice bl = DataFactoryMySql.getInstance().getBLservice();
		
		final ImageReader reader = ImageReader.getInstance();
		final AnimationFrame animation = new AnimationFrame(reader);
		/*try {
			System.out.println("test");
			System.out.println("list size:"+bl.getTodayKingPlayer("14-15_2015-06-07", "point",5).size());
			System.out.println("finish test");
		} catch (RemoteException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}*/
		
		Thread read = new Thread(reader);
		read.start();
		
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
		
		
	/*	Test frame = new Test();
		frame.setVisible(true);*/
		
	}
}
