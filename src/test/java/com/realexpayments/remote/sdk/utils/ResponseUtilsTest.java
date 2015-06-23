package com.realexpayments.remote.sdk.utils;

import org.junit.Assert;
import org.junit.Test;

import com.realexpayments.remote.sdk.domain.Response;
import com.realexpayments.remote.sdk.domain.payment.PaymentResponse;

/**
 * Unit test class for {@link ResponseUtils}.
 * 
 * @author markstanford
 *
 */
public class ResponseUtilsTest {

	/**
	 * Test if the response is a basic response.
	 */
	@Test
	public void isBasicResponseTest() {
		//test success response
		Assert.assertFalse(ResponseUtils.isBasicResponse(Response.RESULT_CODE_SUCCESS));

		//test 1xx code
		Assert.assertFalse(ResponseUtils.isBasicResponse("100"));

		//test 2xx code
		Assert.assertFalse(ResponseUtils.isBasicResponse("200"));

		//test 3xx code
		Assert.assertTrue(ResponseUtils.isBasicResponse("300"));

		//test 5xx code
		Assert.assertTrue(ResponseUtils.isBasicResponse("500"));

	}

	/**
	 * Test if the full response is successful.
	 */
	@Test
	public void isSuccessTest() {
		PaymentResponse response = new PaymentResponse();

		//test success
		response.setResult(Response.RESULT_CODE_SUCCESS);
		Assert.assertTrue(ResponseUtils.isSuccess(response));

		//test failure
		response.setResult("101");
		Assert.assertFalse(ResponseUtils.isSuccess(response));
	}
}
