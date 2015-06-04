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
		// TODO Auto-generated method stub
		try {
			return (BLservice) Naming.lookup("rmi://" + "localhost" + ":" + "6600"
					+ "/" + "BLservice");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}


}
