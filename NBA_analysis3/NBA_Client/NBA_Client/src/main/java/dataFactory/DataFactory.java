package dataFactory;


import java.rmi.Naming;

import BLservice.BLservice;


public interface DataFactory {
	public BLservice getBLservice();
}
