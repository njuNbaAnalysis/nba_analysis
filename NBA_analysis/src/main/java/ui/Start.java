package ui;

import ui.AnimationFrame;

public class Start {
   
	
	
	public static void main(String[] args){
    	AnimationFrame animation = new AnimationFrame();
    	try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		}
    	animation.dispose();
    	try {
			Thread.sleep(300);
		} catch (InterruptedException e) {
			// TODO �Զ���ɵ� catch ��
			e.printStackTrace();
		}
    	
      /* GameFrame game = new GameFrame();
       Thread th = new Thread(game);
       th.start();*/
    }
}