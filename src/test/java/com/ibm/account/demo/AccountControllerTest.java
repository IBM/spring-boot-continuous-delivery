package com.ibm.account.demo;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AccountControllerTest {
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
