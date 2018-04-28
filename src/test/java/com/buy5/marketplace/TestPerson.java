package com.buy5.marketplace;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import com.buy5.marketplace.model.Person;

public class TestPerson {
	
	private static Person testPers;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		testPers = new Person(1, "Lalit C", "LChawathe");
	}

	@Test
	public void testToString() {
		String jsonResult = testPers.toString();
		String jsonExpected = "{\"person_id\":1,\"person_name\":\"Lalit C\",\"user_name\":\"LChawathe\"}";
		assertEquals("Comparing users : ", jsonExpected, jsonResult);
	}

}
