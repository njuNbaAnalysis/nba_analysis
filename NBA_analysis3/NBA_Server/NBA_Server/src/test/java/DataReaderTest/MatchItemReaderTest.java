package DataReaderTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import po.matchItem;
import data.matches.MatchItemReader;

public class MatchItemReaderTest {

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
	public void testgetMatchItemById() {
		MatchItemReader mr = new MatchItemReader();
		ArrayList<matchItem> list = mr.getMatchItemById("01000");
		boolean result = (list.size() != 0);
		System.out.println(list.size());
		assertTrue(result);
	}

	@Test
	public void testgetMatchItemById2() {
		MatchItemReader mr = new MatchItemReader();
		ArrayList<matchItem> list = mr.getMatchItemById("50000");
		boolean result = (list.size() == 0);
		System.out.println(list.size());
		assertTrue(result);
	}

	@Test
	public void testgetMatchItemByPlayerId() {
		MatchItemReader mr = new MatchItemReader();
		ArrayList<matchItem> list = mr.getMatchItemByPlayerId("00195");
		boolean result = (list.size() != 0);
		System.out.println(list.size());
		assertTrue(result);
	}

	@Test
	public void testgetMatchItemByPlayerId2() {
		MatchItemReader mr = new MatchItemReader();
		ArrayList<matchItem> list = mr.getMatchItemByPlayerId("0500");
		boolean result = (list.size() == 0);
		System.out.println(list.size());
		assertTrue(result);
	}

}
