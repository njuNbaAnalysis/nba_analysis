package logic.matches;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InvokeLiveMatchItem {

	 private String ID;
	    private BlockingQueue<ArrayList<String>> queue = new ArrayBlockingQueue<>(10);

	    public InvokeLiveMatchItem(String ID) {
	        this.ID = ID;
	    }

	    public void run() {
	        try {
	            ProcessBuilder pb = new ProcessBuilder("python", "Spider-NBA/NBALiveMatchItem.py", ID);
	            Process p = pb.start();
	            InputStreamReader isr=new InputStreamReader(p.getInputStream(),"GBK");
	            BufferedReader in = new BufferedReader(isr);
	            String line;

	            ArrayList<String> res = new ArrayList<>();
	            while ((line = in.readLine()) != null) {
	                res.add(line);
	            }
	            queue.put(res);

	        } catch (Exception e) {
	            System.out.println(e);
	        }
	    }

	    public ArrayList<String> getLive() throws InterruptedException {
	        return queue.take();
	    }
}
