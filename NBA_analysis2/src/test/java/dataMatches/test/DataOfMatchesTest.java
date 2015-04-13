package dataMatches.test;

import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.matches.MatchReader;


public class DataOfMatchesTest {
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
       MatchReader matchRead = new MatchReader();
       matchRead.init();
       boolean result = (matchRead.getMatchList()==null);
       assertTrue(result);
    }
    
    @Test
    public void testisChanged() {
       MatchReader matchRead = new MatchReader();
       matchRead.init();
       boolean result = (matchRead.isChanged()==false);
       assertTrue(result);
    }
}
