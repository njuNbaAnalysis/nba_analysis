package logic.matches;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InvokeLiveCompare {

	 private String ID;
	    private BlockingQueue<ArrayList<String>> queue = new ArrayBlockingQueue<>(10);

	    public InvokeLiveCompare(String ID) {
	        this.ID = ID;
	    }

	    public void run() {
	        try {
	            ProcessBuilder pb = new ProcessBuilder("python", "C://Users/Lionel's PC/Desktop/NBA_analysis/NBA_analysis3/NBA_Server/NBA_Server/Spider-NBA/NBALiveCompare.py", ID);
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
	            e.printStackTrace();
	        }
	    }

	    public ArrayList<String> getLive() throws InterruptedException {
	        return queue.take();
	    }
}
