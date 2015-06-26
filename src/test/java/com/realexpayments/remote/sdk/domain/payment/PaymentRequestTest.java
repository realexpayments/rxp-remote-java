package com.realexpayments.remote.sdk.domain.payment;

import org.junit.Assert;
import org.junit.Test;

import com.realexpayments.remote.sdk.domain.payment.Address.AddressType;

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

}
