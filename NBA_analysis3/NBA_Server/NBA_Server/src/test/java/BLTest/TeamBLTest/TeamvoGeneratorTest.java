package BLTest.TeamBLTest;

import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;

import logic.teams.TeamvoGenerator;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vo.Teamvo;

public class TeamvoGeneratorTest {
    TeamvoGenerator generator;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
        generator = TeamvoGenerator.getInstance();
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGeneratorTeamvo() {
        Teamvo vo = generator.getTeamvo("CHI", "14-15", false);
        System.out.println(vo.toString());
        
        assertTrue(true);
    }
    
    @Test
    public void testGet() {
        Teamvo vo = generator.getTeamvo("CHI", "14-15", false);
        System.out.println(vo.toString());
        
        assertTrue(true);
    }
}
