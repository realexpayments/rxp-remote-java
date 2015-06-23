package com.realexpayments.remote.sdk.domain.payment;

import org.junit.Assert;
import org.junit.Test;

import com.realexpayments.remote.sdk.domain.Card;

public class CardTest {

	/**
	 * Test method for displayFirstSixLastFour()
	 */
	@Test
	public void testDisplayFirstSixLastFour() {

		Card card = new Card();
		card.setNumber("1234567890ABCDEF");

		String expectedResult = "123456******CDEF";
		String result = card.displayFirstSixLastFour();

		Assert.assertTrue("Results are not as expected.", expectedResult.equals(result));
	}

}
