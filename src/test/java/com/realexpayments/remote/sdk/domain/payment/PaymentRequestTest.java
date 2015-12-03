package com.realexpayments.remote.sdk.domain.payment;

import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.AMOUNT;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.AUTH_MOBILE_MERCHANT_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.AUTH_MOBILE_ORDER_ID;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.AUTH_MOBILE_REQUEST_HASH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.AUTH_MOBILE_TIMESTAMP;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.AUTH_MOBILE_TOKEN;
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

}
