package BLTeams.test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import logic.BLParameter;
import logic.teams.Team;
import logic.teams.TeamController;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LogicOfTeamsTest {
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGet() {
		TeamController teamcontrol = TeamController.getInstance();
		boolean result = (teamcontrol. getAllTeams()!= null);
		assertTrue(result);
	}

	@Test
	public void testInit() {
		TeamController teamcontrol = TeamController.getInstance();
		boolean result = false;

		try {
			teamcontrol.init();
			result = true;
		} catch (Exception e) {
			result = false;
		}

		assertTrue(result);
	}
	
	@Test
	public void testgetTeam() {
		TeamController teamcontrol = TeamController.getInstance();
		boolean result = (teamcontrol. getTeam("")== null);
		assertTrue(result);
	}
	
	@Test
	public void testGetAllTeams(){
	    TeamController teamcontroller = TeamController.getInstance();
        ArrayList<Team> result = teamcontroller.getAllTeams();
        assertNotNull(result);
	}
	
	@Test
    public void testGetResult(){
	    TeamController teamcontroller = TeamController.getInstance();
        BLParameter parameter = new BLParameter();
        parameter.setPlayer(false);
        ArrayList<Object> result = teamcontroller.getResult(parameter);
        assertNotNull(result);
    }
	
	@Test
    public void testGetSeasonKingTeam(){
	    TeamController teamcontroller = TeamController.getInstance();
        teamcontroller.init();
        ArrayList<Team> result = teamcontroller.getSeasonKingTeam("rebound", 5);
        assertNotNull(result);
    }
	
	@Test
    public void testGetAllianceAverageData(){
	    TeamController teamcontroller = TeamController.getInstance();
        teamcontroller.init();
        double[] result = teamcontroller.getAllianceAverageData();
        assertNotNull(result);
    }
}
