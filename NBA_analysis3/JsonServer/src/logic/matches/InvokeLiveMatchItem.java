package logic.matches;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InvokeLiveMatchItem {

	private String ID;
	private boolean ishome;
	private BlockingQueue<ArrayList<String>> queue = new ArrayBlockingQueue<>(
			10);

	public InvokeLiveMatchItem(String ID, boolean ishome) {
		this.ID = ID;
		this.ishome = ishome;
	}

	public void run() {
		try {
			int temp = 0;
			if (ishome)
				temp = 1;
			ProcessBuilder pb = new ProcessBuilder("python",
					"C://Users/lionel233/Desktop/NBA_analysis/NBA_analysis3/JsonServer/Spider-NBA/NBALiveMatchItem.py", ID, "" + temp);
			Process p = pb.start();
			InputStreamReader isr = new InputStreamReader(p.getInputStream(),
					"GBK");
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
