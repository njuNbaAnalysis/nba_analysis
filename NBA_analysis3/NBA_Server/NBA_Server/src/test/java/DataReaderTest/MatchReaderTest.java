package DataReaderTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import po.match;
import po.matchItem;
import data.matches.MatchReader;

public class MatchReaderTest {

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
//
//	@Test
//	public void testGetAllMatch() {
//		MatchReader mr = new MatchReader();
//		ArrayList<match> list = mr.getAllMatch();
//		boolean result = (list.size() != 0);
//		System.out.println(list.size());
//		assertTrue(result);
//	}
//	
	@Test
	public void testGetAllMatchBySeason() {
		MatchReader mr = new MatchReader();
		ArrayList<match> list = mr.getMatchesBySeason("14-15",false);
		boolean result = (list.size() != 0);
		System.out.println("result'size: "+list.size());
		match m = list.get(1000);
		System.out.println(m.getMid());
		ArrayList<matchItem> temp = m.getMatchItemList();
		for(int i=0;i<temp.size();i++){
			System.out.println(temp.get(i).getPid());
		}
		assertTrue(result);
	}
//	
//	@Test
//	public void testGetAllMatchBySeason2() {
//		MatchReader mr = new MatchReader();
//		ArrayList<match> list = mr.getMatchesBySeason("65-66");
//		boolean result = (list.size() == 0);
//		System.out.println(list.size());
//		assertTrue(result);
//	}
//	
//	@Test
//	public void testGetAllMatchBySeason3() {
//		MatchReader mr = new MatchReader();
//		ArrayList<match> list = mr.getMatchesBySeason("14-15",true);
//		boolean result = (list.size() != 0);
//		System.out.println(list.size());
//		assertTrue(result);
//	}
//	
//	@Test
//	public void testGetAllMatchBySeason4() {
//		MatchReader mr = new MatchReader();
//		ArrayList<match> list = mr.getMatchesBySeason("14-15",false);
//		boolean result = (list.size() != 0);
//		System.out.println(list.size());
//		assertTrue(result);
//	}
//	
//	@Test
//	public void testGetAllMatchByTime() {
//		MatchReader mr = new MatchReader();
//		ArrayList<match> list = mr.getMatchesByTime("1985-10-25");
//		boolean result = (list.size() != 0);
//		System.out.println(list.size());
//		assertTrue(result);
//	}
//	
//	@Test
//	public void testGetAllMatchByTime2() {
//		MatchReader mr = new MatchReader();
//		ArrayList<match> list = mr.getMatchesByTime("1985-10-28");
//		boolean result = (list.size() == 0);
//		System.out.println(list.size());
//		assertTrue(result);
//	}
//	
//	@Test
//	public void testGetAllMatchById() {
//		MatchReader mr = new MatchReader();
//		match m = mr.getMatchesById("01000");
//		boolean result = (m != null);
//		assertTrue(result);
//	}
//	
//	@Test
//	public void testGetAllMatchById2() {
//		MatchReader mr = new MatchReader();
//		match m = mr.getMatchesById("80000");
//		boolean result = (m == null);
//		assertTrue(result);
//	}
//	
//	@Test
//	public void testGetMatchesByTeam() {
//		MatchReader mr = new MatchReader();
//		ArrayList<match> m = mr.getMatchesByTeam("CLE", "14-15", true, 10);
//		for(int i=0;i<m.size();i++){
//			System.out.println(m.get(i).getDate());
//		}
//		boolean result = (m.size()!= 0);
//		assertTrue(result);
//	}
//	
}
