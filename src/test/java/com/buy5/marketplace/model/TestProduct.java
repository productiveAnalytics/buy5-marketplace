package com.buy5.marketplace.model;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.buy5.marketplace.model.Product;

public class TestProduct {
	private static Product testProd;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testProd = new Product(1, "Future_of_AI", "Book on AI", 9, 11, 300 /* book */);
	}

	@Test
	public void testToString() {
		String jsonResult = testProd.toString();
		String jsonExpected = "{\"pid\":1,\"name\":\"Future_of_AI\",\"display_name\":\"Book on AI\",\"price\":11,\"inventory\":9,\"cat_id\":300}";
		assertEquals("Comparing toString()", jsonExpected, jsonResult);
	}

}
