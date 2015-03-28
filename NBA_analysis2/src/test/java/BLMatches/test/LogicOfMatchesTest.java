package BLMatches.test;

import static org.junit.Assert.assertTrue;
import logic.matches.MatchController;
import logic.teams.TeamController;

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
	
}
