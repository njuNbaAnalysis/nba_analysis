package NBAServerTest;

import java.rmi.RemoteException;

import logic.BLController;
import BLservice.BLservice;

public class BLControllorTest {

	public static void main(String[] args){
		try {
			BLservice bl = BLController.getInstance();
//			System.out.println(bl.getAllPlayers("14-15", false).size());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			BLservice bl = BLController.getInstance();
			System.out.println(bl.getTodayKingPlayer("14-15_2015-06-01", "point", 5).size());
			System.out.println();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
