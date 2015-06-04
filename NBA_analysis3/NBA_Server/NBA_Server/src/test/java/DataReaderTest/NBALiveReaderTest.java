package DataReaderTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import po.NBALivePlayer;
import po.NBALiveTeam;
import po.match;
import data.matches.MatchReader;
import data.matches.NBALiveReader;

public class NBALiveReaderTest {
	
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
	public void testGetNBALivePlayer() {
		NBALiveReader nr = new NBALiveReader();
		ArrayList<NBALivePlayer> list = nr.getAllNBALivePlayer();
		boolean result = (list.size() != 0);
		System.out.println(list.size());
		assertTrue(result);
	}
	
	@Test
	public void testgetAllNBALiveTeam() {
		NBALiveReader nr = new NBALiveReader();
		ArrayList<NBALiveTeam> list = nr.getAllNBALiveTeam();
		boolean result = (list.size() != 0);
		System.out.println(list.size());
		assertTrue(result);
	}
}
