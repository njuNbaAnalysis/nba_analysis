package BLTest.MatchBLTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Collection;

import logic.matches.NBALiveBLControllor;
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
		NBALiveBLControllor mb = NBALiveBLControllor.getInstance();
//		Matchvo m = mb.getLiveMatchInfo();
//		Boolean result = (m!=null);
		assertTrue(true);
	}
	
//	@Test
//	public void testLoadNewMatchvo() {
//		matchBLcontrollor m = matchBLcontrollor.getInstance();
//		m.loadNewMatchvo(0);
//		assertTrue(true);
//	}
	
	
	@Test
//	public void testgetTodayMatch() {
//		matchBLcontrollor m = matchBLcontrollor.getInstance();
//		Collection<? extends Matchvo> list = m.getTodayMatched("14-15_2015-05-25");
//		System.out.println(list.size());
//		for(int i=0;i<list.size();i++){
//			System.out.println(list.iterator().next().isIsplayoff());
//		}
//		Boolean result = (list.size()!=0);
//		assertTrue(result);
//	}

	public void testgetLast10Matches() {
		matchBLcontrollor m = matchBLcontrollor.getInstance();
		ArrayList<Matchvo> list = m.getLast10Matches("CLE", "14-15", false);
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			System.out.println(list.iterator().next().getDate());
		}
		Boolean result = (list.size()!=0);
		assertTrue(result);
	}
	
}
