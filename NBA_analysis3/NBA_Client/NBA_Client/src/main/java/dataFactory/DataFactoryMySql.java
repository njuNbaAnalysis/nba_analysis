package dataFactory;

import java.rmi.Naming;

import BLservice.BLservice;
import BLservice.matchBLservice;
import BLservice.playerBLservice;
import BLservice.teamBLservice;

public class DataFactoryMySql implements DataFactory {

	public matchBLservice getmatchBLservice() {
		// TODO Auto-generated method stub
		try {
			return (matchBLservice) Naming.lookup("rmi://" + "ip" + ":"
					+ "port" + "/" + "matchBLservice");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public playerBLservice getplayerBLservice() {
		// TODO Auto-generated method stub
		try {
			return (playerBLservice) Naming.lookup("rmi://" + "ip" + ":"
					+ "port" + "/" + "playerBLservice");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public teamBLservice getteamBLservice() {
		// TODO Auto-generated method stub
		try {
			return (teamBLservice) Naming.lookup("rmi://" + "ip" + ":" + "port"
					+ "/" + "teamBLservice");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public BLservice getBLservice() {
		// TODO Auto-generated method stub
		try {
			return (BLservice) Naming.lookup("rmi://" + "ip" + ":" + "port"
					+ "/" + "BLservice");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
