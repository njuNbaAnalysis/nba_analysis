package BLPlayers.test;

import static org.junit.Assert.*;

import java.util.ArrayList;

import logic.BLParameter;
import logic.players.Player;
import logic.players.PlayerController;
import logic.players.todayPlayer;

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
	
	@Test
	public void testGetResult(){
	    BLParameter parameter = new BLParameter();
	    parameter.setPlayer(true);
	    
	    PlayerController playerController = PlayerController.getInstance();
	    ArrayList<Object> result = playerController.getResult(parameter);
	    assertNotNull(result);
	}
	
	@Test
	public void testGetTotalPlayer(){
	    PlayerController playerController = PlayerController.getInstance();
	    ArrayList<todayPlayer> result = playerController.getTotalPlayer("13_14-2014-01-01","rebound");
	    assertNotNull(result);
	}
	
	@Test
	public void testGetSeasonKingPlayer(){
	    PlayerController playerController = PlayerController.getInstance();
        ArrayList<Player> result = playerController.getSeasonKingPlayer("rebound", 5);
        assertNotNull(result);
	}
	
	@Test
	public void testGetMostImprovedPlayer(){
	    PlayerController playerController = PlayerController.getInstance();
        ArrayList<Player> result = playerController.getMostImprovedPlayer("rebound");
        assertNotNull(result);
	}
}
