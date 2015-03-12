package ui;

import ui.AnimationFrame;

public class Start {
   
	
	
	public static void main(String[] args){
    	/*AnimationFrame animation = new AnimationFrame();
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	animation.dispose();
    	try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
    	*/
       MainFrame start = new MainFrame();
       start.setVisible(true);
    }
}
