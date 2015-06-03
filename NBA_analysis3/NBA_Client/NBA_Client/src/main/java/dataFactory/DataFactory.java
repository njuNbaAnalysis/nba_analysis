package dataFactory;


import java.rmi.Naming;

import BLservice.BLservice;
import BLservice.matchBLservice;
import BLservice.playerBLservice;
import BLservice.teamBLservice;


public interface DataFactory {
	public matchBLservice getmatchBLservice();
	public playerBLservice getplayerBLservice();
	public teamBLservice getteamBLservice();
	public BLservice getBLservice();
}
