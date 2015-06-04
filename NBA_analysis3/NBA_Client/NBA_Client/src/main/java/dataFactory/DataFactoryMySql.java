package dataFactory;

import java.rmi.Naming;

import BLservice.BLservice;

public class DataFactoryMySql implements DataFactory {
	
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
