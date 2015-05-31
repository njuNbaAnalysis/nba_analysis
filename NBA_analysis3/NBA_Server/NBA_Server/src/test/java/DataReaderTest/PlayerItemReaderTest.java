package DataReaderTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import po.player;
import po.playerItem;
import data.players.PlayerItemReader;
import data.players.PlayerReader;

public class PlayerItemReaderTest {

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
	public void testgetPlayerItemById() {
		PlayerItemReader pr = new PlayerItemReader();
		ArrayList<playerItem> list = pr.getPlayerItemById("00195");
		boolean result = (list.size() != 0);
		System.out.println(list.size());
		assertTrue(result);
	}

	@Test
	public void testgetPlayerItemBySeason() {
		PlayerItemReader pr = new PlayerItemReader();
		ArrayList<playerItem> list = pr.getPlayerItemBySeason("14-15");
		boolean result = (list.size() != 0);
		System.out.println(list.size());
		assertTrue(result);
	}

}
