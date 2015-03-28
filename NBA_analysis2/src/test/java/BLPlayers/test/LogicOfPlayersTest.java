package BLPlayers.test;

import static org.junit.Assert.assertTrue;
import logic.players.PlayerController;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class LogicOfPlayersTest {
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
	public void testGet() {
		PlayerController playercontrol = PlayerController.getInstance();
		boolean result = (playercontrol. getAllPlayers()!= null);
		assertTrue(result);
	}

	@Test
	public void testInit() {
		PlayerController playercontrol = PlayerController.getInstance();
		boolean result = false;

		try {
			playercontrol.init();
			result = true;
		} catch (Exception e) {
			result = false;
		}

		assertTrue(result);
	}
	
	@Test
	public void testgetPlayer() {
		PlayerController playercontrol = PlayerController.getInstance();
		boolean result = (playercontrol. getPlayer("")== null);
		assertTrue(result);
	}
}
