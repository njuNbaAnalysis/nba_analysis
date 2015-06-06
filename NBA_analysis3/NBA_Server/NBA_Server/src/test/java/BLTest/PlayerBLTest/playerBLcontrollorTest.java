package BLTest.PlayerBLTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import logic.players.playerBLcontrollor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import po.player;
import vo.Playervo;

public class playerBLcontrollorTest {

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
	public void testgetAllPlayers() {
		playerBLcontrollor pl = playerBLcontrollor.getInstance();
		ArrayList<Playervo> list = pl.getAllPlayers("14-15", true);
		boolean result = (list.size() != 0);
		assertTrue(result);
	}

	@Test
	public void testgetAllPlayers2() {
		playerBLcontrollor pl = playerBLcontrollor.getInstance();
		ArrayList<Playervo> list = pl.getAllPlayers("14-15", true);
		boolean result = (list.size() != 0);
		assertTrue(result);
	}

	@Test
	public void testgetAllPlayers3() {
		playerBLcontrollor pl = playerBLcontrollor.getInstance();
		ArrayList<Playervo> list = pl.getAllPlayers("14-15", false);
		boolean result = (list.size() != 0);
		assertTrue(result);
	}

	@Test
	public void testgetAllPlayers4() {
		playerBLcontrollor pl = playerBLcontrollor.getInstance();
		ArrayList<Playervo> list = pl.getAllPlayers("14-15", false);
		boolean result = (list.size() != 0);
		assertTrue(result);
	}

	@Test
	public void testgetPlayerById() {
		playerBLcontrollor pl = playerBLcontrollor.getInstance();
		Playervo list = pl.getPlayerById("00195", "12-13", false);
		boolean result = (list != null);
		assertTrue(result);
	}

	@Test
	public void testgetPlayerById2() {
		playerBLcontrollor pl = playerBLcontrollor.getInstance();
		Playervo list = pl.getPlayerById("00195");
		System.out.println(list.getRecordOfPlayer().size() + "HEHEHEHEHEHE");
		boolean result = (list != null);
		assertTrue(result);
	}

	@Test
	public void testgetPlayerByNameAndTeam() {
		playerBLcontrollor pl = playerBLcontrollor.getInstance();
		Playervo p = pl.getPlayerByNameAndTeam("Kobe Bean Bryant", "LAL");
		System.out.println(p.getPid() + "HEHEHEHEHEHE"
				+ p.getRecordOfPlayer().size() + "ssss");
		assertTrue(true);
	}

	@Test
	public void testgetAlliancePlayerAverageData() {
		playerBLcontrollor pl = playerBLcontrollor.getInstance();
		double[] d = pl.getAlliancePlayerAverageData("12-13", false);
		System.out
				.println(d[1] + " " + d[2] + " " + d[3] + " " + d[4] + " " + d[0]);
		assertTrue(true);
	}

}
