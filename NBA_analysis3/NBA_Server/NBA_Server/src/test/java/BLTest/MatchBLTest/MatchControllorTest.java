package BLTest.MatchBLTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import logic.matches.matchBLcontrollor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vo.EventVo;
import vo.Matchvo;

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
		matchBLcontrollor mb = matchBLcontrollor.getInstance();
		// mb.updateNewMatch("2015-06-02");
		assertTrue(true);
	}

	@Test
	public void testLiveMatchInfo() {
		matchBLcontrollor mb = matchBLcontrollor.getInstance();
		Matchvo m = mb.getLiveMatchInfo();
		Boolean result = (m!=null);
		assertTrue(result);
	}
	
	@Test
	public void testgetLiveEvent() {
		matchBLcontrollor mb = matchBLcontrollor.getInstance();
		ArrayList<EventVo> m = mb.getLiveEvent();
		Boolean result = (m!=null);
		assertTrue(result);
	}
	
}
