package BLTest.MatchBLTest;

import static org.junit.Assert.assertTrue;

import logic.matches.matchBLcontrollor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class MatchControllorTest {

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
	public void testUpdateNewMatch() {
		matchBLcontrollor mb = new matchBLcontrollor();
		// mb.updateNewMatch("2015-06-02");
		assertTrue(true);
	}

	@Test
	public void testNBALive() {
		matchBLcontrollor mb = new matchBLcontrollor();
		mb.NBALive();
		assertTrue(true);
	}
}
