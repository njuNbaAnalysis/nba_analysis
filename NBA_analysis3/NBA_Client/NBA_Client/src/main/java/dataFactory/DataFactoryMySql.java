package dataFactory;

import java.rmi.Naming;

import BLservice.BLservice;

public class DataFactoryMySql implements DataFactory {
    
    private static DataFactoryMySql factory = null;
    
    private DataFactoryMySql(){}
    
    public static DataFactoryMySql getInstance(){
        if(factory == null){
            factory = new DataFactoryMySql();
        }
        return factory;
    }
	
	public BLservice getBLservice() {
		try {
			return (BLservice) Naming.lookup("rmi://" + "192.168.1.102" + ":" + "6600"
					+ "/" + "BLservice");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}


}
