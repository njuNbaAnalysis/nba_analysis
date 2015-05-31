package DataReaderTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.apache.xpath.axes.MatchPatternIterator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import po.matchItem;
import po.pointsItem;
import data.matches.MatchItemReader;
import data.matches.pointsItemReader;

public class pointsItemReaderTest {

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
	public void testgetpointsItemById() {
		pointsItemReader pr = new pointsItemReader();
		ArrayList<pointsItem> list = pr.getpointsItemById("01000");
		boolean result = (list.size() != 0);
		System.out.println(list.size());
		assertTrue(result);
	}
	
	@Test
	public void testgetpointsItemById2() {
		pointsItemReader pr = new pointsItemReader();
		ArrayList<pointsItem> list = pr.getpointsItemById("50000");
		boolean result = (list.size() == 0);
		System.out.println(list.size());
		assertTrue(result);
	}
	
}
