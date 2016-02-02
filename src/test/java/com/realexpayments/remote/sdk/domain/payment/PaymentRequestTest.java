package com.realexpayments.remote.sdk.domain.payment;

import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.AMOUNT;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.AUTH_MOBILE_MERCHANT_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.AUTH_MOBILE_ORDER_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.AUTH_MOBILE_REQUEST_HASH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.AUTH_MOBILE_TIMESTAMP;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.AUTH_MOBILE_TOKEN;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.CARD_NUMBER;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.CREDIT_AMOUNT;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.CREDIT_AUTH_CODE;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.CREDIT_CURRENCY;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.CREDIT_MERCHANT_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.CREDIT_ORDER_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.CREDIT_PASREF;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.CREDIT_REFUND_HASH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.CREDIT_REQUEST_HASH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.CREDIT_TIMESTAMP;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.CURRENCY;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.HOLD_MERCHANT_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.HOLD_ORDER_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.HOLD_PASREF;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.HOLD_REQUEST_HASH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.HOLD_TIMESTAMP;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.MERCHANT_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.ORDER_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.OTB_MERCHANT_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.OTB_ORDER_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.OTB_REQUEST_HASH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.OTB_TIMESTAMP;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.REBATE_AMOUNT;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.REBATE_AUTH_CODE;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.REBATE_CURRENCY;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.REBATE_MERCHANT_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.REBATE_ORDER_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.REBATE_PASREF;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.REBATE_REFUND_HASH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.REBATE_REQUEST_HASH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.REBATE_TIMESTAMP;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.RELEASE_MERCHANT_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.RELEASE_ORDER_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.RELEASE_PASREF;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.RELEASE_REQUEST_HASH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.RELEASE_TIMESTAMP;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.REQUEST_HASH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.SECRET;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.SETTLE_AMOUNT;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.SETTLE_AUTH_CODE;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.SETTLE_CURRENCY;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.SETTLE_MERCHANT_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.SETTLE_ORDER_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.SETTLE_PASREF;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.SETTLE_REQUEST_HASH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.SETTLE_TIMESTAMP;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.TIMESTAMP;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.VOID_AUTH_CODE;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.VOID_MERCHANT_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.VOID_ORDER_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.VOID_PASREF;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.VOID_REQUEST_HASH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.VOID_TIMESTAMP;

import org.junit.Assert;
import org.junit.Test;

import com.realexpayments.remote.sdk.domain.Card;
import com.realexpayments.remote.sdk.domain.payment.Address.AddressType;
import com.realexpayments.remote.sdk.domain.payment.PaymentRequest.PaymentType;

/**
 * Unit test class for PaymentRequest utility methods.
 * 
 * @author markstanford
 *
 */
public class PaymentRequestTest {

	/**
	 * Tests the population of a billing address for the Address Verification Service.
	 */
	@Test
	public void addAddressVerificationServiceDetailsTest() {
		//test variations of address and postcode with TSS Info field null
		String addressLine = "123 Fake St";
		String postcode = "WB1 A42";
		String expectedBillingCode = "142|123";

		PaymentRequest request = new PaymentRequest();
		request.addAddressVerificationServiceDetails(addressLine, postcode);

		Assert.assertEquals(expectedBillingCode, request.getTssInfo().getAddresses().get(0).getCode());
		Assert.assertEquals(AddressType.BILLING.getAddressType(), request.getTssInfo().getAddresses().get(0).getType());

		//test 2
		addressLine = "123 5 Fake St";
		postcode = "1WB 5A2";
		expectedBillingCode = "152|1235";

		request = new PaymentRequest();
		request.addAddressVerificationServiceDetails(addressLine, postcode);

		Assert.assertEquals(expectedBillingCode, request.getTssInfo().getAddresses().get(0).getCode());
		Assert.assertEquals(AddressType.BILLING.getAddressType(), request.getTssInfo().getAddresses().get(0).getType());

		//test 3
		addressLine = "Apt 15, 123 Fake St";
		postcode = "ABC 5A2";
		expectedBillingCode = "52|15123";

		request = new PaymentRequest();
		request.addAddressVerificationServiceDetails(addressLine, postcode);

		Assert.assertEquals(expectedBillingCode, request.getTssInfo().getAddresses().get(0).getCode());
		Assert.assertEquals(AddressType.BILLING.getAddressType(), request.getTssInfo().getAddresses().get(0).getType());

		//test 4
		addressLine = "Fake St";
		postcode = "AI10 9AB";
		expectedBillingCode = "109|";

		request = new PaymentRequest();
		request.addAddressVerificationServiceDetails(addressLine, postcode);

		Assert.assertEquals(expectedBillingCode, request.getTssInfo().getAddresses().get(0).getCode());
		Assert.assertEquals(AddressType.BILLING.getAddressType(), request.getTssInfo().getAddresses().get(0).getType());

		//test 5
		addressLine = "30 Fake St";
		postcode = "";
		expectedBillingCode = "|30";

		request = new PaymentRequest();
		request.addAddressVerificationServiceDetails(addressLine, postcode);

		Assert.assertEquals(expectedBillingCode, request.getTssInfo().getAddresses().get(0).getCode());
		Assert.assertEquals(AddressType.BILLING.getAddressType(), request.getTssInfo().getAddresses().get(0).getType());
	}

	/**
	 * Tests the population of a billing address for the Address Verification Service when TSS Info already exists.
	 */
	@Test
	public void addAddressVerificationServiceDetailsWithTssInfoTest() {
		String addressLine = "123 Fake St";
		String postcode = "WB1 A42";
		String expectedBillingCode = "142|123";

		PaymentRequest request = new PaymentRequest();
		request.addTssInfo(new TssInfo()).addAddressVerificationServiceDetails(addressLine, postcode);

		Assert.assertEquals(expectedBillingCode, request.getTssInfo().getAddresses().get(0).getCode());
		Assert.assertEquals(AddressType.BILLING.getAddressType(), request.getTssInfo().getAddresses().get(0).getType());
	}

	/**
	 * Tests the hash calculation for an auth payment.
	 */
	@Test
	public void authHashGenerationTest() {
		PaymentRequest request = new PaymentRequest().addType(PaymentType.AUTH).addTimeStamp(TIMESTAMP).addMerchantId(MERCHANT_ID)
				.addOrderId(ORDER_ID).addAmount(AMOUNT).addCurrency(CURRENCY).addCard(new Card().addNumber(CARD_NUMBER));
		request.hash(SECRET);

		Assert.assertEquals(REQUEST_HASH, request.getHash());

	}

	/**
	 * Tests the hash calculation for an auth-mobile payment.
	 */
	@Test
	public void authMobileHashGenerationTest() {
		PaymentRequest request = new PaymentRequest().addType(PaymentType.AUTH_MOBILE).addTimeStamp(AUTH_MOBILE_TIMESTAMP)
				.addMerchantId(AUTH_MOBILE_MERCHANT_ID).addOrderId(AUTH_MOBILE_ORDER_ID).addToken(AUTH_MOBILE_TOKEN);
		request.hash(SECRET);

		Assert.assertEquals(AUTH_MOBILE_REQUEST_HASH, request.getHash());
	}

	/**
	 * Tests the hash calculation for a settle payment.
	 */
	@Test
	public void settleHashGenerationTest() {
		PaymentRequest request = new PaymentRequest().addType(PaymentType.SETTLE).addTimeStamp(SETTLE_TIMESTAMP).addMerchantId(SETTLE_MERCHANT_ID)
				.addOrderId(SETTLE_ORDER_ID).addPaymentsReference(SETTLE_PASREF).addAuthCode(SETTLE_AUTH_CODE)
				.addAmount(Long.parseLong(SETTLE_AMOUNT)).addCurrency(SETTLE_CURRENCY);
		request.hash(SECRET);

		Assert.assertEquals(SETTLE_REQUEST_HASH, request.getHash());
	}

	/**
	 * Tests the hash calculation for a void payment.
	 */
	@Test
	public void voidHashGenerationTest() {
		PaymentRequest request = new PaymentRequest().addType(PaymentType.VOID).addTimeStamp(VOID_TIMESTAMP).addMerchantId(VOID_MERCHANT_ID)
				.addOrderId(VOID_ORDER_ID).addPaymentsReference(VOID_PASREF).addAuthCode(VOID_AUTH_CODE);
		request.hash(SECRET);

		Assert.assertEquals(VOID_REQUEST_HASH, request.getHash());
	}

	/**
	 * Tests the hash calculation for a rebate payment.
	 */
	@Test
	public void rebateHashGenerationTest() {
		PaymentRequest request = new PaymentRequest().addType(PaymentType.REBATE).addTimeStamp(REBATE_TIMESTAMP).addMerchantId(REBATE_MERCHANT_ID)
				.addOrderId(REBATE_ORDER_ID).addPaymentsReference(REBATE_PASREF).addAuthCode(REBATE_AUTH_CODE)
				.addAmount(Long.parseLong(REBATE_AMOUNT)).addCurrency(REBATE_CURRENCY).addRefundHash(REBATE_REFUND_HASH);
		request.hash(SECRET);

		Assert.assertEquals(REBATE_REQUEST_HASH, request.getHash());
	}

	/**
	 * Tests the hash calculation for an OTB request.
	 */
	@Test
	public void otbHashGenerationTest() {
		PaymentRequest request = new PaymentRequest().addType(PaymentType.OTB).addTimeStamp(OTB_TIMESTAMP).addMerchantId(OTB_MERCHANT_ID)
				.addOrderId(OTB_ORDER_ID);
		request.hash(SECRET);

		Assert.assertEquals(OTB_REQUEST_HASH, request.getHash());
	}

	/**
	 * Tests the hash calculation for a credit payment.
	 */
	@Test
	public void creditHashGenerationTest() {
		PaymentRequest request = new PaymentRequest().addType(PaymentType.CREDIT).addTimeStamp(CREDIT_TIMESTAMP).addMerchantId(CREDIT_MERCHANT_ID)
				.addOrderId(CREDIT_ORDER_ID).addPaymentsReference(CREDIT_PASREF).addAuthCode(CREDIT_AUTH_CODE)
				.addAmount(Long.parseLong(CREDIT_AMOUNT)).addCurrency(CREDIT_CURRENCY).addRefundHash(CREDIT_REFUND_HASH);
		request.hash(SECRET);

		Assert.assertEquals(CREDIT_REQUEST_HASH, request.getHash());
	}

	/**
	 * Tests the hash calculation for a hold payment.
	 */
	@Test
	public void holdHashGenerationTest() {
		PaymentRequest request = new PaymentRequest().addType(PaymentType.HOLD).addTimeStamp(HOLD_TIMESTAMP).addMerchantId(HOLD_MERCHANT_ID)
				.addOrderId(HOLD_ORDER_ID).addPaymentsReference(HOLD_PASREF);
		request.hash(SECRET);

		Assert.assertEquals(HOLD_REQUEST_HASH, request.getHash());
	}

	/**
	 * Tests the hash calculation for a release payment.
	 */
	@Test
	public void releaseHashGenerationTest() {
		PaymentRequest request = new PaymentRequest().addType(PaymentType.RELEASE).addTimeStamp(RELEASE_TIMESTAMP).addMerchantId(RELEASE_MERCHANT_ID)
				.addOrderId(RELEASE_ORDER_ID).addPaymentsReference(RELEASE_PASREF);
		request.hash(SECRET);

		Assert.assertEquals(RELEASE_REQUEST_HASH, request.getHash());
	}
}
