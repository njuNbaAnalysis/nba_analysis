package BLMatches.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import logic.matches.Match;
import logic.matches.MatchController;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LogicOfMatchesTest {
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
		MatchController matchcontrol = MatchController.getInstance();
		boolean result = (matchcontrol. getAllMatches()!= null);
		assertTrue(result);
	}

	@Test
	public void testInit() {
		MatchController matchcontrol = MatchController.getInstance();
		boolean result = false;

		try {
			matchcontrol.init();
			result = true;
		} catch (Exception e) {
			result = false;
		}

		assertTrue(result);
	}
	
	@Test
	public void testGetTodayMatches(){
	    MatchController matchcontroller = MatchController.getInstance();
	    ArrayList<Match> list = matchcontroller.getTodayMatches("13-14_2014-01-01");
	    assertNotNull(list);
	}
	
	@Test
	public void testGetKingsOfMatch(){
	    MatchController matchcontroller = MatchController.getInstance();
	    Match match = matchcontroller.getAllMatches().get(0);
	    assertNotNull(match.getKingsOfMatch());
	}
	
	@Test
	public void testAddMatches(){
	    MatchController matchcontroller = MatchController.getInstance();
	    matchcontroller.addMatches();
	    assert(true);
	}
	
}
