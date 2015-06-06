package BLTest.TeamBLTest;

import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;
import java.util.ArrayList;

import logic.BLController;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vo.HotZonevo;
import vo.Teamvo;
import BLservice.BLservice;

public class TeamBLServiceTest {
    
    BLservice service;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        service = BLController.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

/*    @Test
    public void testGetHotZone() {
        HotZonevo vo;
		try {
			vo = service.getHotZone("CHI", true, false);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        assertTrue(true);
    }*/
    
    @Test
    public void testGetAllTeams(){
        long start = System.currentTimeMillis();
        
        try {
            ArrayList<Teamvo> list = service.getAllTeams("14-15", false);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        long end =  System.currentTimeMillis();
        
        System.out.println(end - start);
    }
    
    @Test
    public void testGetTeamByPlayerId(){
        long start = System.currentTimeMillis();
        
        try {
            Teamvo vo = service.getTeamByPlayerId("2317", "14-15", false);
            System.out.println(vo);
        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        
        
        long end =  System.currentTimeMillis();
        
        System.out.println(end - start);
    }
}
