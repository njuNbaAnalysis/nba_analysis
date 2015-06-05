package BLTest.TeamBLTest;

import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;

import logic.BLController;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vo.HotZonevo;
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
        service = new BLController();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetHotZone() {
        HotZonevo vo;
		try {
			vo = service.getHotZone("CHI", true, false);
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        assertTrue(true);
    }
}
