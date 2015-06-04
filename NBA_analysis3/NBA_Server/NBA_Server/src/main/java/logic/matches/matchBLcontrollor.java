package logic.matches;

import java.io.File;
import java.util.ArrayList;

import po.player;
import po.playerItem;
import vo.Playervo;
public class matchBLcontrollor {

	public void updateNewMatch(String time) { // 只需每天运行一次
	// Date now = new Date();
	// SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd HH:MM:ss");
	// String current = df.format(now).substring(0, 10);
	// System.out.println(df.format(now));
	// System.out.println(current);
	// System.out.println(time);
		try {
			ProcessBuilder pb = new ProcessBuilder("python",
					"Spider-NBA/NBAUpdate.py", time);
			Process p = pb.start();
			File log = new File("log.txt");
			pb.redirectErrorStream(true);
			pb.redirectOutput(ProcessBuilder.Redirect.to(log));
			System.out.println(p.waitFor());

		} catch (Exception e) {
			System.out.println(e);
		}

	}

	public void NBALive() {

		InvokeLive NBALive = new InvokeLive(1, "0041400311");
		NBALive.run();
		try {
			ArrayList<String> Live = NBALive.getLive();
			for (int i = 0; i < Live.size(); i++) {
				String[] data = Live.get(i).split(",");
				if (data.length == 22) {
					System.out.println(data[3]+" "+data[0]+"-"+data[4]+" "+data[1]+" "+data[2]+" "+data[19]+" ");
//					System.out.println(Live.get(i));
				}
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void NBALiveMatchItem() {

		InvokeLiveMatchItem NBALiveMatchItem = new InvokeLiveMatchItem("0041400311");
		NBALiveMatchItem.run();
		try {
			ArrayList<String> Live = NBALiveMatchItem.getLive();
			for (int i = 0; i < Live.size(); i+=6) {
				String[] data1 = Live.get(i).split(",");
				String[] data2 = Live.get(i+2).split(",");
				String[] data3 = Live.get(i+4).split(",");
				System.out.println(data1[3]+" "+data1[4]+" "+data1[15]+" "+data1[16]+" "+data2[0]+" "+Live.get(i+4));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void NBALiveCompare() {

		InvokeLiveCompare NBALiveCompare = new InvokeLiveCompare("0041400311");
		NBALiveCompare.run();
		try {
			ArrayList<String> Live = NBALiveCompare.getLive();
			for (int i = 0; i < Live.size(); i++) {
				System.out.println(Live.get(i));
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	public Playervo changePlayertoVO(player p) {
		playerItem temp = p.getCurrentPlayerItem();
		if (temp != null) {
		}

		return null;
	}

	public static void main(String[] args) {
		matchBLcontrollor mb = new matchBLcontrollor();
		mb.NBALiveCompare();
	}
}
