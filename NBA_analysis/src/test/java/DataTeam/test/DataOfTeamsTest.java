package DataTeam.test;

import static org.junit.Assert.assertTrue;
import logic.matches.Match;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.matches.MatchReader;
import data.players.PlayerReader;
import data.teams.TeamReader;

public class DataOfTeamsTest {
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
		TeamReader teamRead = new TeamReader();
		boolean result = (teamRead.readTeams() != null);
		assertTrue(result);
	}
}
