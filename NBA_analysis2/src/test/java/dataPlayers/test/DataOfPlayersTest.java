package dataPlayers.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.players.PlayerReader;

public class DataOfPlayersTest {
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
	public void testReader() {
		PlayerReader PlayerRead = new PlayerReader();
		boolean result = (PlayerRead.readPlayers() != null);
		assertTrue(result);
	}

	@Test
	public void testReaderAction() {
		PlayerReader PlayerRead = new PlayerReader();
		boolean result = false;

		try {
			PlayerRead.readAction();
			result = true;
		} catch (Exception e) {
			result = false;
		}

		assertTrue(result);
	}
	
	@Test
	public void testReaderImage() {
		PlayerReader PlayerRead = new PlayerReader();
		boolean result = false;

		try {
			PlayerRead.readPortrait();
			result = true;
		} catch (Exception e) {
			result = false;
		}

		assertTrue(result);
	}
}
