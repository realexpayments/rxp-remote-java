package com.realexpayments.remote.sdk.utils;

import org.junit.Assert;

import com.realexpayments.remote.sdk.RealexServerException;
import com.realexpayments.remote.sdk.domain.Card.CardType;
import com.realexpayments.remote.sdk.domain.Cvn.PresenceIndicator;
import com.realexpayments.remote.sdk.domain.payment.Address.AddressType;
import com.realexpayments.remote.sdk.domain.payment.AutoSettle.AutoSettleFlag;
import com.realexpayments.remote.sdk.domain.payment.PaymentRequest;
import com.realexpayments.remote.sdk.domain.payment.PaymentRequest.PaymentType;
import com.realexpayments.remote.sdk.domain.payment.PaymentResponse;
import com.realexpayments.remote.sdk.domain.payment.Recurring.RecurringFlag;
import com.realexpayments.remote.sdk.domain.payment.Recurring.RecurringSequence;
import com.realexpayments.remote.sdk.domain.payment.Recurring.RecurringType;
import com.realexpayments.remote.sdk.domain.threeDSecure.ThreeDSecureRequest;
import com.realexpayments.remote.sdk.domain.threeDSecure.ThreeDSecureRequest.ThreeDSecureType;
import com.realexpayments.remote.sdk.domain.threeDSecure.ThreeDSecureResponse;

/**
 * Unit testing utility class to validate the sample XML files. 
 * 
 * @author markstanford
 *
 */
public class SampleXmlValidationUtils {

	public static final String SECRET = "mysecret";

	//payment sample XML
	public static final String PAYMENT_REQUEST_XML_PATH = "/sample-xml/payment-request-sample.xml";
	public static final String PAYMENT_RESPONSE_XML_PATH = "/sample-xml/payment-response-sample.xml";
	public static final String PAYMENT_RESPONSE_BASIC_ERROR_XML_PATH = "/sample-xml/payment-response-basic-error-sample.xml";
	public static final String PAYMENT_RESPONSE_FULL_ERROR_XML_PATH = "/sample-xml/payment-response-full-error-sample.xml";
	public static final String PAYMENT_RESPONSE_XML_PATH_UNKNOWN_ELEMENT = "/sample-xml/payment-response-sample-unknown-element.xml";

	//3DSecure sample XML
	public static final String THREE_D_SECURE_VERIFY_ENROLLED_REQUEST_XML_PATH = "/sample-xml/3ds-verify-enrolled-request-sample.xml";
	public static final String THREE_D_SECURE_VERIFY_ENROLLED_RESPONSE_XML_PATH = "/sample-xml/3ds-verify-enrolled-response-sample.xml";
	public static final String THREE_D_SECURE_VERIFY_ENROLLED_NOT_ENROLLED_RESPONSE_XML_PATH = "/sample-xml/3ds-verify-enrolled-response-sample-not-enrolled.xml";
	public static final String THREE_D_SECURE_VERIFY_SIG_REQUEST_XML_PATH = "/sample-xml/3ds-verify-sig-request-sample.xml";
	public static final String THREE_D_SECURE_VERIFY_SIG_RESPONSE_XML_PATH = "/sample-xml/3ds-verify-sig-response-sample.xml";

	//Card
	public static final String CARD_NUMBER = "420000000000000000";
	public static final CardType CARD_TYPE = CardType.VISA;
	public static final String CARD_HOLDER_NAME = "Joe Smith";
	public static final Integer CARD_CVN_NUMBER = 123;
	public static final PresenceIndicator CARD_CVN_PRESENCE = PresenceIndicator.CVN_PRESENT;
	public static final String CARD_EXPIRY_DATE = "0119";
	public static final Integer CARD_ISSUE_NUMBER = 1;

	//PaymentRequest
	public static final String ACCOUNT = "internet";
	public static final String MERCHANT_ID = "thestore";
	public static final Long AMOUNT = 29900l;
	public static final String CURRENCY = "EUR";
	public static final AutoSettleFlag AUTO_SETTLE_FLAG = AutoSettleFlag.MULTI;
	public static final String TIMESTAMP = "20120926112654";
	public static final String CHANNEL = "yourChannel";
	public static final String ORDER_ID = "ORD453-11";
	public static final String REQUEST_HASH = "581b84c1dbfd0a6c9c7d4e2d0a620157e879dac5";
	public static final String COMMENT1 = "comment 1";
	public static final String COMMENT2 = "comment 2";
	public static final String REFUND_HASH = "hjfdg78h34tyvklasjr89t";
	public static final String FRAUD_FILTER = "fraud filter";
	public static final String CUSTOMER_NUMBER = "cust num";
	public static final String PRODUCT_ID = "prod ID";
	public static final String VARIABLE_REFERENCE = "variable ref 1234";
	public static final String CUSTOMER_IP = "127.0.0.1";

	//Recurring
	public static final RecurringType RECURRING_TYPE = RecurringType.FIXED;
	public static final RecurringFlag RECURRING_FLAG = RecurringFlag.ONE;
	public static final RecurringSequence RECURRING_SEQUENCE = RecurringSequence.FIRST;

	//Address
	public static final AddressType ADDRESS_TYPE_BUSINESS = AddressType.BILLING;
	public static final String ADDRESS_CODE_BUSINESS = "21|578";
	public static final String ADDRESS_COUNTRY_BUSINESS = "IE";

	public static final AddressType ADDRESS_TYPE_SHIPPING = AddressType.SHIPPING;
	public static final String ADDRESS_CODE_SHIPPING = "77|9876";
	public static final String ADDRESS_COUNTRY_SHIPPING = "GB";

	//response fields
	public static final String ACQUIRER_RESPONSE = "<response>test acquirer response</response>";
	public static final Long AUTH_TIME_TAKEN = 1001l;
	public static final Long BATCH_ID = -1L;
	public static final String BANK = "bank";
	public static final String COUNTRY = "Ireland";
	public static final String COUNTRY_CODE = "IE";
	public static final String REGION = "Dublin";
	public static final String CVN_RESULT = "M";
	public static final String MESSAGE = "Successful";
	public static final String RESULT_SUCCESS = "00";
	public static final Long TIME_TAKEN = 54564L;
	public static final String TSS_RESULT = "67";
	public static final String TSS_RESULT_CHECK1_ID = "1";
	public static final String TSS_RESULT_CHECK2_ID = "2";
	public static final String TSS_RESULT_CHECK1_VALUE = "99";
	public static final String TSS_RESULT_CHECK2_VALUE = "199";
	public static final String RESPONSE_HASH = "368df010076481d47a21e777871012b62b976339";
	public static final String PASREF = "3737468273643";
	public static final String AUTH_CODE = "79347";
	public static final String AVS_POSTCODE = "M";
	public static final String AVS_ADDRESS = "P";

	//basic response error fields
	public static final String MESSAGE_BASIC_ERROR = "error message returned from system";
	public static final String RESULT_BASIC_ERROR = "508";
	public static final String TIMESTAMP_BASIC_ERROR = "20120926112654";
	public static final String ORDER_ID_BASIC_ERROR = "ORD453-11";

	//basic response error fields
	public static final String RESULT_FULL_ERROR = "101";
	public static final String MESSAGE_FULL_ERROR = "Bank Error";
	public static final String RESPONSE_FULL_ERROR_HASH = "0ad8a9f121c4041b4b832ae8069e80674269e22f";

	//3DS request fields
	public static final String THREE_D_SECURE_VERIFY_ENROLLED_REQUEST_HASH = "1f6db5dc1a72c35b4c07cc9405a9674e272d57e7";
	public static final String THREE_D_SECURE_VERIFY_SIG_REQUEST_HASH = "1f6db5dc1a72c35b4c07cc9405a9674e272d57e7";

	//3DS response fields
	public static final String THREE_D_SECURE_ENROLLED_RESULT = "00";
	public static final String THREE_D_SECURE_SIG_RESULT = "00";
	public static final String THREE_D_SECURE_NOT_ENROLLED_RESULT = "110";
	public static final String THREE_D_SECURE_ENROLLED_MESSAGE = "Enrolled";
	public static final String THREE_D_SECURE_SIG_MESSAGE = "Authentication Successful";
	public static final String THREE_D_SECURE_NOT_ENROLLED_MESSAGE = "Not Enrolled";
	public static final String THREE_D_SECURE_PAREQ = "eJxVUttygkAM/ZUdnitZFlBw4na02tE6bR0vD+0bLlHpFFDASv++u6i1";
	public static final String THREE_D_SECURE_PARES = "eJxVUttygkAM/ZUdnitZFlBw4na02tE6bR0vD+0bLlHpFFDASv++u6i1";
	public static final String THREE_D_SECURE_URL = "http://myurl.com";
	public static final String THREE_D_SECURE_ENROLLED_NO = "N";
	public static final String THREE_D_SECURE_ENROLLED_YES = "Y";
	public static final String THREE_D_SECURE_STATUS = "Y";
	public static final String THREE_D_SECURE_ECI = "5";
	public static final String THREE_D_SECURE_XID = "e9dafe706f7142469c45d4877aaf5984";
	public static final String THREE_D_SECURE_CAVV = "AAABASY3QHgwUVdEBTdAAAAAAAA=";
	public static final String THREE_D_SECURE_ALGORITHM = "1";
	public static final String THREE_D_SECURE_NOT_ENROLLED_RESPONSE_HASH = "e553ff2510dec9bfee79bb0303af337d871c02ad";
	public static final String THREE_D_SECURE_ENROLLED_RESPONSE_HASH = "728cdbef90ff535ed818748f329ed8b1df6b8f5a";
	public static final String THREE_D_SECURE_SIG_RESPONSE_HASH = "e5a7745da5dc32d234c3f52860132c482107e9ac";

	/**
	 * Check all fields match expected values.
	 * 
	 * @param fromXmlRequest
	 */
	public static void checkUnmarshalledPaymentRequest(PaymentRequest fromXmlRequest) {
		Assert.assertNotNull(fromXmlRequest);
		Assert.assertEquals(CARD_NUMBER, fromXmlRequest.getCard().getNumber());
		Assert.assertEquals(CARD_TYPE.getType(), fromXmlRequest.getCard().getType());
		Assert.assertEquals(CARD_HOLDER_NAME, fromXmlRequest.getCard().getCardHolderName());
		Assert.assertEquals(String.valueOf(CARD_CVN_NUMBER), String.valueOf(fromXmlRequest.getCard().getCvn().getNumber()));
		Assert.assertEquals(CARD_CVN_PRESENCE.getIndicator(), fromXmlRequest.getCard().getCvn().getPresenceIndicator());
		Assert.assertEquals(String.valueOf(CARD_ISSUE_NUMBER), String.valueOf(fromXmlRequest.getCard().getIssueNumber()));
		Assert.assertEquals(CARD_EXPIRY_DATE, fromXmlRequest.getCard().getExpiryDate());

		Assert.assertEquals(ACCOUNT, fromXmlRequest.getAccount());
		Assert.assertEquals(MERCHANT_ID, fromXmlRequest.getMerchantId());
		Assert.assertEquals(PaymentType.AUTH.getType(), fromXmlRequest.getType());
		Assert.assertEquals(String.valueOf(AMOUNT), fromXmlRequest.getAmount().getAmount().toString());
		Assert.assertEquals(CURRENCY, fromXmlRequest.getAmount().getCurrency());
		Assert.assertEquals(AUTO_SETTLE_FLAG.getFlag(), fromXmlRequest.getAutoSettle().getFlag());
		Assert.assertEquals(TIMESTAMP, fromXmlRequest.getTimeStamp());
		Assert.assertEquals(CHANNEL, fromXmlRequest.getChannel());
		Assert.assertEquals(ORDER_ID, fromXmlRequest.getOrderId());
		Assert.assertEquals(REQUEST_HASH, fromXmlRequest.getHash());
		Assert.assertEquals(COMMENT1, fromXmlRequest.getComments().get(0).getComment());
		Assert.assertEquals("1", fromXmlRequest.getComments().get(0).getId().toString());
		Assert.assertEquals(COMMENT2, fromXmlRequest.getComments().get(1).getComment());
		Assert.assertEquals("2", fromXmlRequest.getComments().get(1).getId().toString());
		Assert.assertEquals(PASREF, fromXmlRequest.getPaymentsReference());
		Assert.assertEquals(AUTH_CODE, fromXmlRequest.getAuthCode());
		Assert.assertEquals(REFUND_HASH, fromXmlRequest.getRefundHash());
		Assert.assertEquals(FRAUD_FILTER, fromXmlRequest.getFraudFilter());
		Assert.assertEquals(RECURRING_FLAG.getRecurringFlag(), fromXmlRequest.getRecurring().getFlag());
		Assert.assertEquals(RECURRING_TYPE.getType(), fromXmlRequest.getRecurring().getType());
		Assert.assertEquals(RECURRING_SEQUENCE.getSequence(), fromXmlRequest.getRecurring().getSequence());

		Assert.assertEquals(CUSTOMER_NUMBER, fromXmlRequest.getTssInfo().getCustomerNumber());
		Assert.assertEquals(PRODUCT_ID, fromXmlRequest.getTssInfo().getProductId());
		Assert.assertEquals(VARIABLE_REFERENCE, fromXmlRequest.getTssInfo().getVariableReference());
		Assert.assertEquals(CUSTOMER_IP, fromXmlRequest.getTssInfo().getCustomerIpAddress());
		Assert.assertEquals(ADDRESS_TYPE_BUSINESS.getAddressType(), fromXmlRequest.getTssInfo().getAddresses().get(0).getType());
		Assert.assertEquals(ADDRESS_CODE_BUSINESS, fromXmlRequest.getTssInfo().getAddresses().get(0).getCode());
		Assert.assertEquals(ADDRESS_COUNTRY_BUSINESS, fromXmlRequest.getTssInfo().getAddresses().get(0).getCountry());
		Assert.assertEquals(ADDRESS_TYPE_SHIPPING.getAddressType(), fromXmlRequest.getTssInfo().getAddresses().get(1).getType());
		Assert.assertEquals(ADDRESS_CODE_SHIPPING, fromXmlRequest.getTssInfo().getAddresses().get(1).getCode());
		Assert.assertEquals(ADDRESS_COUNTRY_SHIPPING, fromXmlRequest.getTssInfo().getAddresses().get(1).getCountry());

		Assert.assertEquals(THREE_D_SECURE_CAVV, fromXmlRequest.getMpi().getCavv());
		Assert.assertEquals(THREE_D_SECURE_XID, fromXmlRequest.getMpi().getXid());
		Assert.assertEquals(THREE_D_SECURE_ECI, fromXmlRequest.getMpi().getEci());

	}

	/**
	 * Check all fields match expected values.
	 * 
	 * @param fromXmlResponse
	 */
	public static void checkUnmarshalledPaymentResponse(PaymentResponse fromXmlResponse) {
		Assert.assertEquals(ACCOUNT, fromXmlResponse.getAccount());
		Assert.assertEquals(ACQUIRER_RESPONSE, fromXmlResponse.getAcquirerResponse());
		Assert.assertEquals(AUTH_CODE, fromXmlResponse.getAuthCode());
		Assert.assertEquals(AUTH_TIME_TAKEN.toString(), fromXmlResponse.getAuthTimeTaken().toString());
		Assert.assertEquals(BATCH_ID.toString(), fromXmlResponse.getBatchId().toString());
		Assert.assertEquals(BANK, fromXmlResponse.getCardIssuer().getBank());
		Assert.assertEquals(COUNTRY, fromXmlResponse.getCardIssuer().getCountry());
		Assert.assertEquals(COUNTRY_CODE, fromXmlResponse.getCardIssuer().getCountryCode());
		Assert.assertEquals(REGION, fromXmlResponse.getCardIssuer().getRegion());
		Assert.assertEquals(CVN_RESULT, fromXmlResponse.getCvnResult());
		Assert.assertEquals(MERCHANT_ID, fromXmlResponse.getMerchantId());
		Assert.assertEquals(MESSAGE, fromXmlResponse.getMessage());
		Assert.assertEquals(ORDER_ID, fromXmlResponse.getOrderId());
		Assert.assertEquals(PASREF, fromXmlResponse.getPaymentsReference());
		Assert.assertEquals(RESULT_SUCCESS, fromXmlResponse.getResult());
		Assert.assertEquals(RESPONSE_HASH, fromXmlResponse.getHash());
		Assert.assertEquals(TIMESTAMP, fromXmlResponse.getTimeStamp());
		Assert.assertEquals(TIME_TAKEN.toString(), fromXmlResponse.getTimeTaken().toString());
		Assert.assertEquals(TSS_RESULT, fromXmlResponse.getTssResult().getResult());
		Assert.assertEquals(TSS_RESULT_CHECK1_ID, fromXmlResponse.getTssResult().getChecks().get(0).getId());
		Assert.assertEquals(TSS_RESULT_CHECK1_VALUE, fromXmlResponse.getTssResult().getChecks().get(0).getValue());
		Assert.assertEquals(TSS_RESULT_CHECK2_ID, fromXmlResponse.getTssResult().getChecks().get(1).getId());
		Assert.assertEquals(TSS_RESULT_CHECK2_VALUE, fromXmlResponse.getTssResult().getChecks().get(1).getValue());
		Assert.assertEquals(AVS_ADDRESS, fromXmlResponse.getAvsAddressResponse());
		Assert.assertEquals(AVS_POSTCODE, fromXmlResponse.getAvsPostcodeResponse());
		Assert.assertTrue(fromXmlResponse.isSuccess());
	}

	/**
	 * Check all fields match expected values.
	 * 
	 * @param fromXmlResponse
	 */
	public static void checkBasicResponseError(RealexServerException ex) {
		Assert.assertEquals(RESULT_BASIC_ERROR, ex.getErrorCode());
		Assert.assertEquals(MESSAGE_BASIC_ERROR, ex.getMessage());
		Assert.assertEquals(TIMESTAMP_BASIC_ERROR, ex.getTimeStamp());
		Assert.assertEquals(ORDER_ID_BASIC_ERROR, ex.getOrderId());
	}

	/**
	 * Check all fields match expected values.
	 * 
	 * @param fromXmlResponse
	 */
	public static void checkFullResponseError(PaymentResponse fromXmlResponse) {
		Assert.assertEquals(ACCOUNT, fromXmlResponse.getAccount());
		Assert.assertEquals(ACQUIRER_RESPONSE, fromXmlResponse.getAcquirerResponse());
		Assert.assertEquals(AUTH_CODE, fromXmlResponse.getAuthCode());
		Assert.assertEquals(AUTH_TIME_TAKEN.toString(), fromXmlResponse.getAuthTimeTaken().toString());
		Assert.assertEquals(BATCH_ID.toString(), fromXmlResponse.getBatchId().toString());
		Assert.assertEquals(BANK, fromXmlResponse.getCardIssuer().getBank());
		Assert.assertEquals(COUNTRY, fromXmlResponse.getCardIssuer().getCountry());
		Assert.assertEquals(COUNTRY_CODE, fromXmlResponse.getCardIssuer().getCountryCode());
		Assert.assertEquals(REGION, fromXmlResponse.getCardIssuer().getRegion());
		Assert.assertEquals(CVN_RESULT, fromXmlResponse.getCvnResult());
		Assert.assertEquals(MERCHANT_ID, fromXmlResponse.getMerchantId());
		Assert.assertEquals(MESSAGE_FULL_ERROR, fromXmlResponse.getMessage());
		Assert.assertEquals(ORDER_ID, fromXmlResponse.getOrderId());
		Assert.assertEquals(PASREF, fromXmlResponse.getPaymentsReference());
		Assert.assertEquals(RESULT_FULL_ERROR, fromXmlResponse.getResult());
		Assert.assertEquals(RESPONSE_FULL_ERROR_HASH, fromXmlResponse.getHash());
		Assert.assertEquals(TIMESTAMP, fromXmlResponse.getTimeStamp());
		Assert.assertEquals(TIME_TAKEN.toString(), fromXmlResponse.getTimeTaken().toString());
		Assert.assertEquals(TSS_RESULT, fromXmlResponse.getTssResult().getResult());
		Assert.assertEquals(TSS_RESULT_CHECK1_ID, fromXmlResponse.getTssResult().getChecks().get(0).getId());
		Assert.assertEquals(TSS_RESULT_CHECK1_VALUE, fromXmlResponse.getTssResult().getChecks().get(0).getValue());
		Assert.assertEquals(TSS_RESULT_CHECK2_ID, fromXmlResponse.getTssResult().getChecks().get(1).getId());
		Assert.assertEquals(TSS_RESULT_CHECK2_VALUE, fromXmlResponse.getTssResult().getChecks().get(1).getValue());
		Assert.assertFalse(fromXmlResponse.isSuccess());
	}

	/**
	 * Check all fields match expected values.
	 * 
	 * @param fromXmlResponse
	 */
	public static void checkUnmarshalledThreeDSecureNotEnrolledResponse(ThreeDSecureResponse fromXmlResponse) {
		Assert.assertEquals(ACCOUNT, fromXmlResponse.getAccount());
		Assert.assertEquals(AUTH_CODE, fromXmlResponse.getAuthCode());
		Assert.assertEquals(AUTH_TIME_TAKEN.toString(), fromXmlResponse.getAuthTimeTaken().toString());
		Assert.assertEquals(MERCHANT_ID, fromXmlResponse.getMerchantId());
		Assert.assertEquals(THREE_D_SECURE_NOT_ENROLLED_MESSAGE, fromXmlResponse.getMessage());
		Assert.assertEquals(ORDER_ID, fromXmlResponse.getOrderId());
		Assert.assertEquals(PASREF, fromXmlResponse.getPaymentsReference());
		Assert.assertEquals(THREE_D_SECURE_NOT_ENROLLED_RESULT, fromXmlResponse.getResult());
		Assert.assertEquals(THREE_D_SECURE_NOT_ENROLLED_RESPONSE_HASH, fromXmlResponse.getHash());
		Assert.assertEquals(TIMESTAMP, fromXmlResponse.getTimeStamp());
		Assert.assertEquals(TIME_TAKEN.toString(), fromXmlResponse.getTimeTaken().toString());
		Assert.assertEquals(THREE_D_SECURE_URL, fromXmlResponse.getUrl());
		Assert.assertEquals(THREE_D_SECURE_PAREQ, fromXmlResponse.getPareq());
		Assert.assertEquals(THREE_D_SECURE_ENROLLED_NO, fromXmlResponse.getEnrolled());
		Assert.assertEquals(THREE_D_SECURE_XID, fromXmlResponse.getXid());
		Assert.assertFalse(fromXmlResponse.isSuccess());
	}

	/**
	 * Check all fields match expected values.
	 * 
	 * @param fromXmlResponse
	 */
	public static void checkUnmarshalledThreeDSecureEnrolledResponse(ThreeDSecureResponse fromXmlResponse) {
		Assert.assertEquals(ACCOUNT, fromXmlResponse.getAccount());
		Assert.assertEquals(AUTH_CODE, fromXmlResponse.getAuthCode());
		Assert.assertEquals(AUTH_TIME_TAKEN.toString(), fromXmlResponse.getAuthTimeTaken().toString());
		Assert.assertEquals(MERCHANT_ID, fromXmlResponse.getMerchantId());
		Assert.assertEquals(THREE_D_SECURE_ENROLLED_MESSAGE, fromXmlResponse.getMessage());
		Assert.assertEquals(ORDER_ID, fromXmlResponse.getOrderId());
		Assert.assertEquals(PASREF, fromXmlResponse.getPaymentsReference());
		Assert.assertEquals(THREE_D_SECURE_ENROLLED_RESULT, fromXmlResponse.getResult());
		Assert.assertEquals(THREE_D_SECURE_ENROLLED_RESPONSE_HASH, fromXmlResponse.getHash());
		Assert.assertEquals(TIMESTAMP, fromXmlResponse.getTimeStamp());
		Assert.assertEquals(TIME_TAKEN.toString(), fromXmlResponse.getTimeTaken().toString());
		Assert.assertEquals(THREE_D_SECURE_URL, fromXmlResponse.getUrl());
		Assert.assertEquals(THREE_D_SECURE_PAREQ, fromXmlResponse.getPareq());
		Assert.assertEquals(THREE_D_SECURE_ENROLLED_YES, fromXmlResponse.getEnrolled());
		Assert.assertEquals(THREE_D_SECURE_XID, fromXmlResponse.getXid());
		Assert.assertTrue(fromXmlResponse.isSuccess());
	}

	/**
	 * Check all fields match expected values.
	 * 
	 * @param fromXmlResponse
	 */
	public static void checkUnmarshalledThreeDSecureSigResponse(ThreeDSecureResponse fromXmlResponse) {
		Assert.assertEquals(ACCOUNT, fromXmlResponse.getAccount());
		Assert.assertEquals(MERCHANT_ID, fromXmlResponse.getMerchantId());
		Assert.assertEquals(THREE_D_SECURE_SIG_MESSAGE, fromXmlResponse.getMessage());
		Assert.assertEquals(ORDER_ID, fromXmlResponse.getOrderId());
		Assert.assertEquals(THREE_D_SECURE_SIG_RESULT, fromXmlResponse.getResult());
		Assert.assertEquals(THREE_D_SECURE_SIG_RESPONSE_HASH, fromXmlResponse.getHash());
		Assert.assertEquals(TIMESTAMP, fromXmlResponse.getTimeStamp());
		Assert.assertEquals(THREE_D_SECURE_STATUS, fromXmlResponse.getThreeDSecure().getStatus());
		Assert.assertEquals(THREE_D_SECURE_ECI, fromXmlResponse.getThreeDSecure().getEci());
		Assert.assertEquals(THREE_D_SECURE_XID, fromXmlResponse.getThreeDSecure().getXid());
		Assert.assertEquals(THREE_D_SECURE_CAVV, fromXmlResponse.getThreeDSecure().getCavv());
		Assert.assertEquals(THREE_D_SECURE_ALGORITHM, fromXmlResponse.getThreeDSecure().getAlgorithm());
		Assert.assertTrue(fromXmlResponse.isSuccess());
	}

	/**
	 * Check all fields match expected values.
	 * 
	 * @param fromXmlRequest
	 */
	public static void checkUnmarshalledVerifyEnrolledRequest(ThreeDSecureRequest fromXmlRequest) {
		Assert.assertNotNull(fromXmlRequest);
		Assert.assertEquals(CARD_NUMBER, fromXmlRequest.getCard().getNumber());
		Assert.assertEquals(CARD_TYPE.getType(), fromXmlRequest.getCard().getType());
		Assert.assertEquals(CARD_HOLDER_NAME, fromXmlRequest.getCard().getCardHolderName());
		Assert.assertEquals(CARD_EXPIRY_DATE, fromXmlRequest.getCard().getExpiryDate());

		Assert.assertEquals(ACCOUNT, fromXmlRequest.getAccount());
		Assert.assertEquals(MERCHANT_ID, fromXmlRequest.getMerchantId());
		Assert.assertEquals(ThreeDSecureType.VERIFY_ENROLLED.getType(), fromXmlRequest.getType());
		Assert.assertEquals(String.valueOf(AMOUNT), fromXmlRequest.getAmount().getAmount().toString());
		Assert.assertEquals(CURRENCY, fromXmlRequest.getAmount().getCurrency());
		Assert.assertEquals(TIMESTAMP, fromXmlRequest.getTimeStamp());
		Assert.assertEquals(ORDER_ID, fromXmlRequest.getOrderId());
		Assert.assertEquals(THREE_D_SECURE_VERIFY_ENROLLED_REQUEST_HASH, fromXmlRequest.getHash());
	}

	/**
	 * Check all fields match expected values.
	 * 
	 * @param fromXmlRequest
	 */
	public static void checkUnmarshalledVerifySigRequest(ThreeDSecureRequest fromXmlRequest) {
		Assert.assertNotNull(fromXmlRequest);
		Assert.assertEquals(CARD_NUMBER, fromXmlRequest.getCard().getNumber());
		Assert.assertEquals(CARD_TYPE.getType(), fromXmlRequest.getCard().getType());
		Assert.assertEquals(CARD_HOLDER_NAME, fromXmlRequest.getCard().getCardHolderName());
		Assert.assertEquals(CARD_EXPIRY_DATE, fromXmlRequest.getCard().getExpiryDate());

		Assert.assertEquals(ACCOUNT, fromXmlRequest.getAccount());
		Assert.assertEquals(MERCHANT_ID, fromXmlRequest.getMerchantId());
		Assert.assertEquals(ThreeDSecureType.VERIFY_SIG.getType(), fromXmlRequest.getType());
		Assert.assertEquals(String.valueOf(AMOUNT), fromXmlRequest.getAmount().getAmount().toString());
		Assert.assertEquals(CURRENCY, fromXmlRequest.getAmount().getCurrency());
		Assert.assertEquals(TIMESTAMP, fromXmlRequest.getTimeStamp());
		Assert.assertEquals(ORDER_ID, fromXmlRequest.getOrderId());
		Assert.assertEquals(THREE_D_SECURE_PARES, fromXmlRequest.getPares());
		Assert.assertEquals(THREE_D_SECURE_VERIFY_SIG_REQUEST_HASH, fromXmlRequest.getHash());
	}

}
