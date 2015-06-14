package logic.matches;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class InvokeLive implements Runnable {
    private int period;
    private String ID;
    private BlockingQueue<ArrayList<String>> queue = new ArrayBlockingQueue<>(10);

    public InvokeLive(int period,String ID) {
        this.period = period;
        this.ID = ID;
    }

    @Override
    public void run() {
        try {
            ProcessBuilder pb = new ProcessBuilder("python", "C://Users/lionel233/Desktop/NBA_analysis/NBA_analysis3/JsonServer/Spider-NBA/NBALive.py", ID,""+ period);
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

