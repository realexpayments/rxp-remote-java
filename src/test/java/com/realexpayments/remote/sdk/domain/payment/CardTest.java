package com.realexpayments.remote.sdk.domain.payment;

import com.realexpayments.remote.sdk.RealexClient;
import com.realexpayments.remote.sdk.RealexException;
import com.realexpayments.remote.sdk.domain.Cvn;
import com.realexpayments.remote.sdk.http.HttpConfiguration;

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
