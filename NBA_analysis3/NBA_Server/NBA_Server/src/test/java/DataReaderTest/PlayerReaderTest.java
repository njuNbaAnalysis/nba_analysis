package DataReaderTest;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import po.player;
import vo.playerItem;
import data.players.PlayerItemReader;
import data.players.PlayerReader;

public class PlayerReaderTest {

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
	public void testGetAllPlayer() {
		PlayerReader pr = new PlayerReader();
		ArrayList<player> list = pr.getAllPlayer();
		boolean result = (list.size() != 0);
		System.out.println(list.size());
		assertTrue(result);
	}

	@Test
	public void testGetPlayerById() {
		PlayerReader pr = new PlayerReader();
		player p = pr.getPlayerById("195");
		boolean result = (p != null);
		System.out.println(p.getPid() + "  " + p.getName());
		assertTrue(result);
	}

	@Test
	public void testGetPlayerById2() {
		PlayerReader pr = new PlayerReader();
		player p = pr.getPlayerById("5000");
		boolean result = (p == null);
		assertTrue(result);
	}

	@Test
	public void testGetPlayersBySeason() {
		PlayerReader pr = new PlayerReader();
		ArrayList<player> list = pr.getPlayersBySeason("14-15",false);
		boolean result = (list.size() != 0);
		System.out.println(list.size()+"ALLPlayer　　！");
		for(int i=0;i<list.size();i++){
			System.out.println(i+":"+list.get(i).getName()+" "+list.get(i).getPid());
		}
		assertTrue(result);
	}
	
	@Test
	public void testGetPlayersBySeason2() {
		PlayerReader pr = new PlayerReader();
		ArrayList<player> list = pr.getPlayersBySeason("66-65",false);
		boolean result = (list.size() == 0);
		System.out.println(list.size());
		assertTrue(result);
	}
	
	@Test
	public void testgetPlayerByNameAndTeam() {
		PlayerReader pr = new PlayerReader();
		String p  = pr.getPlayerByNameAndTeam("Kobe Bean Bryant", "LAL").getPid();
		System.out.println(p);
		assertTrue(true);
	}
}
