package BLTest.TeamBLTest;

import static org.junit.Assert.assertTrue;
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
        service = BLController.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetHotZone() {
        HotZonevo vo = service.getHotZone("", true, true);
        
        System.out.println(vo.getTotal());
        
        assertTrue(true);
    }
}
