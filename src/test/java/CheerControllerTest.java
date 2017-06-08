package demo;

import org.junit.Test;
import org.junit.Before;
import org.junit.Assert;
 
import java.util.*;

public class CheerControllerTest {
	private String testVal;
	
	@Before
	public void setUp() throws Exception {
	    testVal = "HelloWorld";
    }
	
    @Test
    public void testTrue() {
    	Assert.assertTrue("Unit test 1 passed.", true);
    }

    @Test
    public void testFalse() {
    	Assert.assertFalse("Unit test 2 passed.", false);
    }
    
    @Test
    public void testEquals() {
    	Assert.assertEquals("Unit test 3 passed.", "HelloWorld", testVal);
    }
    
}
