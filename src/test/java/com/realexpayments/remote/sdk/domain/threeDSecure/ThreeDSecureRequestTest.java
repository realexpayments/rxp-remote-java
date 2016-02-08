package com.realexpayments.remote.sdk.domain.threeDSecure;

import com.realexpayments.remote.sdk.domain.Card;
import com.realexpayments.remote.sdk.domain.threeDSecure.ThreeDSecureRequest.ThreeDSecureType;
import org.junit.Assert;
import org.junit.Test;

import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.*;

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

	/**
	 * Tests the hash calculation for a card-update transaction.
	 */
	@Test
	public void verifyCardEnrolledHashGenerationTest() {

		ThreeDSecureRequest request = new ThreeDSecureRequest().addType(ThreeDSecureType.VERIFY_CARD_ENROLLED)
				.addTimeStamp(CARD_VERIFY_TIMESTAMP)
				.addMerchantId(CARD_VERIFY_MERCHANT_ID)
				.addOrderId(CARD_VERIFY_ORDER_ID)
				.addAmount(Long.parseLong(CARD_VERIFY_AMOUNT))
				.addCurrency(CARD_VERIFY_CURRENCY)
				.addPayerReference(CARD_VERIFY_PAYER_REF);

		request.hash(SECRET);

		Assert.assertEquals(CARD_VERIFY_REQUEST_HASH, request.getHash());
	}
}
