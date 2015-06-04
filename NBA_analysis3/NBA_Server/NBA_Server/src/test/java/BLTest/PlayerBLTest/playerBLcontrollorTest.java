package BLTest.PlayerBLTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import logic.players.playerBLcontrollor;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
	public void testUpdateNewMatch() {
		playerBLcontrollor pl  = playerBLcontrollor.getInstance();
		ArrayList<Playervo> list = pl.getAllPlayers("12-13", true);
		boolean result = (list.size()!=0);
		assertTrue(result);
	}
	
}
