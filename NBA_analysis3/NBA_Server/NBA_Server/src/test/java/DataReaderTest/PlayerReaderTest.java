package DataReaderTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import po.player;
import data.players.PlayerReader;

public class PlayerReaderTest {

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
	public void testGetAllPlayer() {
		PlayerReader pr = new PlayerReader();
		ArrayList<player> list = pr.getAllPlayer();
		boolean result = (list.size() != 0);
		System.out.println(list.size());
		assertTrue(result);
	}

	@Test
	public void testGetPlayerById() {
		PlayerReader pr = new PlayerReader();
		player p = pr.getPlayerById("00195");
		boolean result = (p != null);
		System.out.println(p.getPid() + "  " + p.getName());
		assertTrue(result);
	}

	@Test
	public void testGetPlayerById2() {
		PlayerReader pr = new PlayerReader();
		player p = pr.getPlayerById("05000");
		boolean result = (p == null);
		assertTrue(result);
	}

	@Test
	public void testGetPlayersBySeason() {
		PlayerReader pr = new PlayerReader();
		ArrayList<player> list = pr.getPlayersBySeason("14-15");
		boolean result = (list.size() != 0);
		System.out.println(list.size());
		assertTrue(result);
	}
}
