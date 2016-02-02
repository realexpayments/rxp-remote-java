package com.realexpayments.remote.sdk.domain.threeDSecure;

import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.AMOUNT;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.CARD_NUMBER;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.CURRENCY;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.MERCHANT_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.ORDER_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.REQUEST_HASH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.SECRET;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.TIMESTAMP;

import org.junit.Assert;
import org.junit.Test;

import com.realexpayments.remote.sdk.domain.Card;
import com.realexpayments.remote.sdk.domain.threeDSecure.ThreeDSecureRequest.ThreeDSecureType;

/**
 * Unit test class for {@link ThreeDSecureRequest} utility methods.
 * 
 * @author markstanford
 *
 */
public class ThreeDSecureRequestTest {

	/**
	 * Test hash calculation for 3DSecure Verify Enrolled.
	 */
	@Test
	public void verifyEnrolledHashGenerationTest() {
		ThreeDSecureRequest request = new ThreeDSecureRequest().addType(ThreeDSecureType.VERIFY_ENROLLED).addTimeStamp(TIMESTAMP)
				.addMerchantId(MERCHANT_ID).addOrderId(ORDER_ID).addAmount(AMOUNT).addCurrency(CURRENCY).addCard(new Card().addNumber(CARD_NUMBER));
		request.hash(SECRET);

		Assert.assertEquals(REQUEST_HASH, request.getHash());
	}

	/**
	 * Test hash calculation for 3DSecure Verify Sig.
	 */
	@Test
	public void verifySigHashGenerationTest() {
		ThreeDSecureRequest request = new ThreeDSecureRequest().addType(ThreeDSecureType.VERIFY_SIG).addTimeStamp(TIMESTAMP)
				.addMerchantId(MERCHANT_ID).addOrderId(ORDER_ID).addAmount(AMOUNT).addCurrency(CURRENCY).addCard(new Card().addNumber(CARD_NUMBER));
		request.hash(SECRET);

		Assert.assertEquals(REQUEST_HASH, request.getHash());
	}
}
