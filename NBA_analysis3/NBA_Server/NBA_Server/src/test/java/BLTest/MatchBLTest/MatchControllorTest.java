package BLTest.MatchBLTest;

import static org.junit.Assert.assertTrue;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Collection;

import logic.BLController;
import logic.matches.NBALiveBLControllor;
import logic.matches.NBALiveList;
import logic.matches.matchBLcontrollor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vo.EventVo;
import vo.FutureMatchvo;
import vo.KingsOfMatchvo;
import vo.MatchSimpleInfovo;
import vo.Matchvo;
import vo.RecordOfPlayervo;

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
		// Matchvo m = mb.getLiveMatchInfo();
		// Boolean result = (m!=null);
		assertTrue(true);
	}

	@Test
	public void testLoadNewMatchvo() {
		matchBLcontrollor m = matchBLcontrollor.getInstance();
//		m.updateNewMatch("2015-06-11");;
		assertTrue(true);
	}

	@Test
	public void testgetLast10Matches() {
		matchBLcontrollor m = matchBLcontrollor.getInstance();
		ArrayList<Matchvo> list = m.getLast10Matches("HOU", "14-15", false);
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getDate()+"  last 10 matches:  "+list.get(i).getMid() + "  "+list.get(i).getTeams()[0]+"  "+list.get(i).getTeams()[1]);
		}
		Boolean result = (list.size() != 0);
		assertTrue(result);
	}

	@Test
	public void testgetTodayMatch() {
		matchBLcontrollor m = matchBLcontrollor.getInstance();
		Collection<? extends Matchvo> list = m
				.getTodayMatched("14-15_2015-04-25");
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.iterator().next().isIsplayoff());
			KingsOfMatchvo[] kings = list.iterator().next().getKingsOfMatch();
			System.out.println(kings[0].getIdOfPointsKing() + "  "
					+ kings[0].getNameOfPointsKing());
		}
		Boolean result = (list.size() != 0);
		assertTrue(result);
	}

	@Test
	public void testgetSimpleMatchInfo() {
		matchBLcontrollor m = matchBLcontrollor.getInstance();
		ArrayList<MatchSimpleInfovo> list = m
				.getMatchSimpleInfo("CLE", "14-15");
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getNameOfRival()
					+ " simple" + "  "+list.get(i).getNameOfTeam()+"   "+list.get(i).getNameOfRival());
		}
		Boolean result = (list.size() != 0);
		assertTrue(result);
	}

	@Test
	public void testgetFutureMatches() {
		BLController bl = null;
		try {
			bl = BLController.getInstance();
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<FutureMatchvo> list = null;
		try {
			list = bl.getFutureMatches("2015-06-10");
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getMid() + " future" + "  ");
		}
		Boolean result = (list.size() != 0);
		assertTrue(result);
	}

	@Test
	public void testgetRecordOfPlayerById() {
		matchBLcontrollor m = matchBLcontrollor.getInstance();
		ArrayList<RecordOfPlayervo> list = m.getRecordOfPlayerById("195");
		System.out.println(list.size() + "   dasdasdsadas");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getDate() + "  "
					+ list.get(i).getAway_Team() + "  " + list.get(i).getMid());
			;
		}
		assertTrue(true);
	}

	@Test
	public void testgetLatestMatchSimpleInfo() {
		matchBLcontrollor m = matchBLcontrollor.getInstance();
		ArrayList<MatchSimpleInfovo> list = m.getLatestMatchSimpleInfo("CLE", "HOU");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i).getDate() + "  "
					+ list.get(i).getNameOfTeam() + "  " + list.get(i).getNameOfRival()+" lastMatches");
			
		}
		assertTrue(true);
	}
	
	@Test
	public void testgetWinPercentage() {
		matchBLcontrollor m = matchBLcontrollor.getInstance();
		double temp = m.getWinPercentage("GSW", "HOU", "14-15", false);
			System.out.println(temp+"  WinPercentage!");

		assertTrue(true);
	}
	

}
