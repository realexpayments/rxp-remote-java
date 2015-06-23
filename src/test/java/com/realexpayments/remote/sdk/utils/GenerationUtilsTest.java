package com.realexpayments.remote.sdk.utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

/**
 * GenerationUtils unit tests.
 *
 * @author thomasduffy
 */
public class GenerationUtilsTest {

	/**
	 * Test Hash generation success case.
	 */
	@Test
	public void testGenerateHash() {

		String testString = "20120926112654.thestore.ORD453-11.00.Successful.3737468273643.79347";
		String secret = "mysecret";
		String expectedResult = "368df010076481d47a21e777871012b62b976339";

		String result = GenerationUtils.generateHash(testString, secret);
		assertTrue("Expected and resultant Hash do not match. expected: " + expectedResult + " result: " + result, expectedResult.equals(result));
	}

	/**
	 * Test timestamp generation. Hard to test this in a meaningful way. Checking length and valid characters.
	 * 
	 * @return current timestamp in YYYYMMDDHHSS format
	 */
	@Test
	public void testGenerateTimestamp() {
		String result = GenerationUtils.generateTimestamp();

		assertTrue("Timestamp should be 14 digits: " + result, result.matches("[0-9]{14}"));
	}

	/**
	 * Test order Id generation. Hard to test this in a meaningful way. Checking length and valid characters.
	 */
	@Test
	public void testGenerateOrderId() {
		String result = GenerationUtils.generateOrderId();

		assertEquals("OrderId " + result + " should be 22 characters, is " + result.length() + " characters: " + result, "22",
				String.valueOf(result.length()));
		assertTrue("OrderId " + result + " - Regexp doesn't match [A-Za-z0-9-_]{22}", result.matches("[A-Za-z0-9-_]{22}"));
	}
}
