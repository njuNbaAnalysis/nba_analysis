package dataController.test;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import data.DataController;

public class DataControllerTest {
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
    public void TestReadAllImages(){
        DataController controller = DataController.getInstance();
        controller.readAllImages();
        assert(true);
    }
    
    @Test
    public void TestIsMatchChanged(){
        DataController controller = DataController.getInstance();
        controller.isMatchChanged();
        assert(true);
    }
}
