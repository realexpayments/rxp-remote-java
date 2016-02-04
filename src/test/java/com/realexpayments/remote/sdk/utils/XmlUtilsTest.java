package com.realexpayments.remote.sdk.utils;

import com.realexpayments.remote.sdk.RealexException;
import com.realexpayments.remote.sdk.domain.Amount;
import com.realexpayments.remote.sdk.domain.Card;
import com.realexpayments.remote.sdk.domain.Card.CardType;
import com.realexpayments.remote.sdk.domain.Cvn;
import com.realexpayments.remote.sdk.domain.PaymentData;
import com.realexpayments.remote.sdk.domain.payment.*;
import com.realexpayments.remote.sdk.domain.payment.PaymentRequest.PaymentType;
import com.realexpayments.remote.sdk.domain.threeDSecure.ThreeDSecureRequest;
import com.realexpayments.remote.sdk.domain.threeDSecure.ThreeDSecureRequest.ThreeDSecureType;
import com.realexpayments.remote.sdk.domain.threeDSecure.ThreeDSecureResponse;
import com.realexpayments.remote.sdk.utils.XmlUtils.MessageType;
import org.junit.Test;

import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.*;

/**
 * Unit test class for XmlUtils.
 * 
 * @author markstanford
 *
 */
public class XmlUtilsTest {

	/**
	 * Tests conversion of {@link PaymentRequest} to and from XML using the helper methods.
	 */
	@Test
	public void paymentRequestXmlHelpersTest() {
		Cvn cvn = new Cvn()
				.addNumber(CARD_CVN_NUMBER)
				.addPresenceIndicator(CARD_CVN_PRESENCE);

		Card card = new Card()
				.addExpiryDate(CARD_EXPIRY_DATE)
				.addNumber(CARD_NUMBER)
				.addType(CardType.VISA)
				.addCardHolderName(CARD_HOLDER_NAME)
				.addIssueNumber(CARD_ISSUE_NUMBER);
		card.setCvn(cvn);

		TssInfo tssInfo = new TssInfo()
				.addCustomerNumber(CUSTOMER_NUMBER)
				.addProductId(PRODUCT_ID)
				.addVariableReference(VARIABLE_REFERENCE)
				.addCustomerIpAddress(CUSTOMER_IP)
				.addAddress(new Address()
						.addType(ADDRESS_TYPE_BUSINESS)
						.addCode(ADDRESS_CODE_BUSINESS)
						.addCountry(ADDRESS_COUNTRY_BUSINESS))
				.addAddress(new Address()
						.addType(ADDRESS_TYPE_SHIPPING)
						.addCode(ADDRESS_CODE_SHIPPING)
						.addCountry(ADDRESS_COUNTRY_SHIPPING));

		PaymentRequest request = new PaymentRequest()
				.addAccount(ACCOUNT)
				.addMerchantId(MERCHANT_ID)
				.addType(PaymentType.AUTH)
				.addAmount(AMOUNT)
				.addCurrency(CURRENCY)
				.addCard(card)
				.addAutoSettle(new AutoSettle().addFlag(AUTO_SETTLE_FLAG))
				.addTimeStamp(TIMESTAMP)
				.addChannel(CHANNEL)
				.addOrderId(ORDER_ID)
				.addHash(REQUEST_HASH)
				.addComment(COMMENT1)
				.addComment(COMMENT2)
				.addPaymentsReference(PASREF)
				.addAuthCode(AUTH_CODE)
				.addRefundHash(REFUND_HASH)
				.addFraudFilter(FRAUD_FILTER)
				.addRecurring(new Recurring()
						.addFlag(RECURRING_FLAG)
						.addSequence(RECURRING_SEQUENCE)
						.addType(RECURRING_TYPE))
				.addTssInfo(tssInfo)
				.addMpi(new Mpi()
						.addCavv(THREE_D_SECURE_CAVV)
						.addXid(THREE_D_SECURE_XID)
						.addEci(THREE_D_SECURE_ECI));

		//convert to XML
		String xml = request.toXml();

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(xml);
		checkUnmarshalledPaymentRequest(fromXmlRequest);
	}

	/**
	 * Tests conversion of {@link PaymentRequest} to and from XML using the helper methods with no enums.
	 */
	@Test
	public void paymentRequestXmlHelpersNoEnumsTest() {
		Card card = new Card()
				.addExpiryDate(CARD_EXPIRY_DATE)
				.addNumber(CARD_NUMBER)
				.addType(CardType.VISA.getType())
				.addCardHolderName(CARD_HOLDER_NAME)
				.addCvn(CARD_CVN_NUMBER)
				.addCvnPresenceIndicator(CARD_CVN_PRESENCE.getIndicator())
				.addIssueNumber(CARD_ISSUE_NUMBER);

		TssInfo tssInfo = new TssInfo()
				.addCustomerNumber(CUSTOMER_NUMBER)
				.addProductId(PRODUCT_ID)
				.addVariableReference(VARIABLE_REFERENCE)
				.addCustomerIpAddress(CUSTOMER_IP)
				.addAddress(new Address()
						.addType(ADDRESS_TYPE_BUSINESS.getAddressType())
						.addCode(ADDRESS_CODE_BUSINESS)
						.addCountry(ADDRESS_COUNTRY_BUSINESS))
				.addAddress(new Address()
						.addType(ADDRESS_TYPE_SHIPPING.getAddressType())
						.addCode(ADDRESS_CODE_SHIPPING)
						.addCountry(ADDRESS_COUNTRY_SHIPPING));

		PaymentRequest request = new PaymentRequest()
				.addAccount(ACCOUNT)
				.addMerchantId(MERCHANT_ID)
				.addType(PaymentType.AUTH.getType())
				.addAmount(AMOUNT)
				.addCurrency(CURRENCY)
				.addCard(card)
				.addAutoSettle(new AutoSettle().addFlag(AUTO_SETTLE_FLAG.getFlag()))
				.addTimeStamp(TIMESTAMP)
				.addChannel(CHANNEL)
				.addOrderId(ORDER_ID)
				.addHash(REQUEST_HASH)
				.addComment(COMMENT1)
				.addComment(COMMENT2)
				.addPaymentsReference(PASREF)
				.addAuthCode(AUTH_CODE)
				.addRefundHash(REFUND_HASH)
				.addFraudFilter(FRAUD_FILTER)
				.addRecurring(new Recurring()
						.addFlag(RECURRING_FLAG.getRecurringFlag())
						.addSequence(RECURRING_SEQUENCE.getSequence())
						.addType(RECURRING_TYPE.getType()))
				.addTssInfo(tssInfo)
				.addMpi(new Mpi()
						.addCavv(THREE_D_SECURE_CAVV)
						.addXid(THREE_D_SECURE_XID)
						.addEci(THREE_D_SECURE_ECI));

		//convert to XML
		String xml = request.toXml();

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(xml);
		checkUnmarshalledPaymentRequest(fromXmlRequest);
	}

	/**
	 * Tests conversion of {@link PaymentRequest} to and from XML using setters.
	 */
	@Test
	public void paymentRequestXmlSettersTest() {
		Card card = new Card();
		card.setExpiryDate(CARD_EXPIRY_DATE);
		card.setNumber(CARD_NUMBER);
		card.setType(CardType.VISA.getType());
		card.setCardHolderName(CARD_HOLDER_NAME);
		card.setIssueNumber(CARD_ISSUE_NUMBER);

		Cvn cvn = new Cvn();
		cvn.setNumber(CARD_CVN_NUMBER);
		cvn.setPresenceIndicator(CARD_CVN_PRESENCE.getIndicator());
		card.setCvn(cvn);

		PaymentRequest request = new PaymentRequest();
		request.setAccount(ACCOUNT);
		request.setMerchantId(MERCHANT_ID);
		request.setType(PaymentType.AUTH.getType());

		Amount amount = new Amount();
		amount.setAmount(AMOUNT);
		amount.setCurrency(CURRENCY);
		request.setAmount(amount);

		AutoSettle autoSettle = new AutoSettle();
		autoSettle.setFlag(AUTO_SETTLE_FLAG.getFlag());

		request.setAutoSettle(autoSettle);
		request.setCard(card);
		request.setTimeStamp(TIMESTAMP);
		request.setChannel(CHANNEL);
		request.setOrderId(ORDER_ID);
		request.setHash(REQUEST_HASH);

		List<Comment> comments = new ArrayList<Comment>();
		Comment comment = new Comment();
		comment.setId(1);
		comment.setComment(COMMENT1);
		comments.add(comment);
		comment = new Comment();
		comment.setId(2);
		comment.setComment(COMMENT2);
		comments.add(comment);
		request.setComments(comments);

		request.setPaymentsReference(PASREF);
		request.setAuthCode(AUTH_CODE);
		request.setRefundHash(REFUND_HASH);
		request.setFraudFilter(FRAUD_FILTER);

		Recurring recurring = new Recurring();
		recurring.setFlag(RECURRING_FLAG.getRecurringFlag());
		recurring.setSequence(RECURRING_SEQUENCE.getSequence());
		recurring.setType(RECURRING_TYPE.getType());
		request.setRecurring(recurring);

		TssInfo tssInfo = new TssInfo();
		tssInfo.setCustomerNumber(CUSTOMER_NUMBER);
		tssInfo.setProductId(PRODUCT_ID);
		tssInfo.setVariableReference(VARIABLE_REFERENCE);
		tssInfo.setCustomerIpAddress(CUSTOMER_IP);

		List<Address> addresses = new ArrayList<Address>();
		Address address = new Address();
		address.setType(ADDRESS_TYPE_BUSINESS.getAddressType());
		address.setCode(ADDRESS_CODE_BUSINESS);
		address.setCountry(ADDRESS_COUNTRY_BUSINESS);
		addresses.add(address);

		address = new Address();
		address.setType(ADDRESS_TYPE_SHIPPING.getAddressType());
		address.setCode(ADDRESS_CODE_SHIPPING);
		address.setCountry(ADDRESS_COUNTRY_SHIPPING);
		addresses.add(address);

		tssInfo.setAddresses(addresses);
		request.setTssInfo(tssInfo);

		Mpi mpi = new Mpi();
		mpi.setCavv(THREE_D_SECURE_CAVV);
		mpi.setXid(THREE_D_SECURE_XID);
		mpi.setEci(THREE_D_SECURE_ECI);
		request.setMpi(mpi);

		//convert to XML
		String xml = request.toXml();

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(xml);
		checkUnmarshalledPaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentResponse} to and from XML.
	 */
	@Test
	public void paymentResponseXmlTest() {
		PaymentResponse response = new PaymentResponse();

		response.setAccount(ACCOUNT);
		response.setAcquirerResponse(ACQUIRER_RESPONSE);
		response.setAuthCode(AUTH_CODE);
		response.setAuthTimeTaken(AUTH_TIME_TAKEN);
		response.setBatchId(BATCH_ID);

		CardIssuer cardIssuer = new CardIssuer();
		cardIssuer.setBank(BANK);
		cardIssuer.setCountry(COUNTRY);
		cardIssuer.setCountryCode(COUNTRY_CODE);
		cardIssuer.setRegion(REGION);
		response.setCardIssuer(cardIssuer);

		response.setCvnResult(CVN_RESULT);
		response.setMerchantId(MERCHANT_ID);
		response.setMessage(MESSAGE);
		response.setOrderId(ORDER_ID);
		response.setPaymentsReference(PASREF);
		response.setResult(RESULT_SUCCESS);
		response.setHash(RESPONSE_HASH);
		response.setTimeStamp(TIMESTAMP_RESPONSE);
		response.setTimeTaken(TIME_TAKEN);

		TssResult tssResult = new TssResult();
		tssResult.setResult(TSS_RESULT);

		List<TssResultCheck> checks = new ArrayList<TssResultCheck>();
		TssResultCheck check = new TssResultCheck();
		check.setId(TSS_RESULT_CHECK1_ID);
		check.setValue(TSS_RESULT_CHECK1_VALUE);
		checks.add(check);
		check = new TssResultCheck();
		check.setId(TSS_RESULT_CHECK2_ID);
		check.setValue(TSS_RESULT_CHECK2_VALUE);
		checks.add(check);

		tssResult.setChecks(checks);
		response.setTssResult(tssResult);

		response.setAvsAddressResponse(AVS_ADDRESS);
		response.setAvsPostcodeResponse(AVS_POSTCODE);

		//marshal to XML
		String xml = response.toXml();

		//unmarshal back to response
		PaymentResponse fromXmlResponse = new PaymentResponse().fromXml(xml);
		checkUnmarshalledPaymentResponse(fromXmlResponse);
	}

	/**
	 * Tests conversion of {@link PaymentResponse} from XML file 
	 */
	@Test
	public void paymentResponseXmlFromFileTest() {

		File file = new File(this.getClass().getResource(PAYMENT_RESPONSE_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//unmarshal back to re
		PaymentResponse fromXmlResponse = new PaymentResponse().fromXml(source);
		checkUnmarshalledPaymentResponse(fromXmlResponse);
	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file.
	 */
	@Test
	public void paymentRequestXmlFromFileTest() {

		File file = new File(this.getClass().getResource(PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledPaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentResponse} from XML file with unknown element.
	 */
	@Test
	public void paymentResponseXmlFromFileUnknownElementTest() {

		File file = new File(this.getClass().getResource(PAYMENT_RESPONSE_XML_PATH_UNKNOWN_ELEMENT).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentResponse fromXmlResponse = new PaymentResponse().fromXml(source);
		checkUnmarshalledPaymentResponse(fromXmlResponse);

	}

	/**
	 * Test expected {@link RealexException} when unmarshalling invalid xml.
	 */
	@Test(expected = RealexException.class)
	public void fromXmlErrorTest() {
		//Try to unmarshal invalid XML
		XmlUtils.fromXml(new StreamSource(new StringReader("<xml>test</xml>")), MessageType.PAYMENT);
	}

	/**
	 * Tests conversion of {@link ThreeDSecureRequest} to and from XML using the helper methods.
	 */
	@Test
	public void threeDSecureRequestXmlHelpersTest() {

		Card card = new Card()
				.addExpiryDate(CARD_EXPIRY_DATE)
				.addNumber(CARD_NUMBER)
				.addType(CardType.VISA)
				.addCardHolderName(CARD_HOLDER_NAME)
				.addIssueNumber(CARD_ISSUE_NUMBER)
				.addCvn(CARD_CVN_NUMBER)
				.addCvnPresenceIndicator(CARD_CVN_PRESENCE);

		ThreeDSecureRequest request = new ThreeDSecureRequest()
				.addAccount(ACCOUNT)
				.addMerchantId(MERCHANT_ID)
				.addType(ThreeDSecureType.VERIFY_ENROLLED)
				.addAmount(AMOUNT)
				.addCurrency(CURRENCY)
				.addCard(card)
				.addTimeStamp(TIMESTAMP)
				.addOrderId(ORDER_ID)
				.addHash(THREE_D_SECURE_VERIFY_ENROLLED_REQUEST_HASH);

		//convert to XML
		String xml = request.toXml();

		//Convert from XML back to PaymentRequest
		ThreeDSecureRequest fromXmlRequest = new ThreeDSecureRequest().fromXml(xml);
		checkUnmarshalledVerifyEnrolledRequest(fromXmlRequest);
	}

	/**
	 * Tests conversion of {@link ThreeDSecureRequest} to and from XML using the helper methods with no enums.
	 */
	@Test
	public void threeDSecureRequestXmlHelpersNoEnumsTest() {

		Card card = new Card()
				.addExpiryDate(CARD_EXPIRY_DATE)
				.addNumber(CARD_NUMBER)
				.addType(CardType.VISA.getType())
				.addCardHolderName(CARD_HOLDER_NAME)
				.addIssueNumber(CARD_ISSUE_NUMBER)
				.addCvn(CARD_CVN_NUMBER)
				.addCvnPresenceIndicator(CARD_CVN_PRESENCE.getIndicator());

		ThreeDSecureRequest request = new ThreeDSecureRequest()
				.addAccount(ACCOUNT)
				.addMerchantId(MERCHANT_ID)
				.addType(ThreeDSecureType.VERIFY_ENROLLED.getType())
				.addAmount(AMOUNT)
				.addCurrency(CURRENCY)
				.addCard(card)
				.addTimeStamp(TIMESTAMP)
				.addOrderId(ORDER_ID)
				.addHash(THREE_D_SECURE_VERIFY_ENROLLED_REQUEST_HASH);

		//convert to XML
		String xml = request.toXml();

		//Convert from XML back to PaymentRequest
		ThreeDSecureRequest fromXmlRequest = new ThreeDSecureRequest().fromXml(xml);
		checkUnmarshalledVerifyEnrolledRequest(fromXmlRequest);
	}

	/**
	 * Tests conversion of {@link ThreeDSecureRequest} verify enrolled to and from XML using setters.
	 */
	@Test
	public void threeDSecureEnrolledRequestXmlWithSettersTest() {

		Card card = new Card();
		card.setExpiryDate(CARD_EXPIRY_DATE);
		card.setNumber(CARD_NUMBER);
		card.setType(CardType.VISA.getType());
		card.setCardHolderName(CARD_HOLDER_NAME);
		card.setIssueNumber(CARD_ISSUE_NUMBER);

		Cvn cvn = new Cvn();
		cvn.setNumber(CARD_CVN_NUMBER);
		cvn.setPresenceIndicator(CARD_CVN_PRESENCE.getIndicator());
		card.setCvn(cvn);

		ThreeDSecureRequest request = new ThreeDSecureRequest();
		request.setAccount(ACCOUNT);
		request.setMerchantId(MERCHANT_ID);

		Amount amount = new Amount();
		amount.setAmount(AMOUNT);
		amount.setCurrency(CURRENCY);
		request.setAmount(amount);

		request.setCard(card);
		request.setTimeStamp(TIMESTAMP);
		request.setOrderId(ORDER_ID);
		request.setHash(THREE_D_SECURE_VERIFY_ENROLLED_REQUEST_HASH);
		request.setType(ThreeDSecureType.VERIFY_ENROLLED.getType());

		//convert to XML
		String xml = request.toXml();

		//Convert from XML back to PaymentRequest
		ThreeDSecureRequest fromXmlRequest = new ThreeDSecureRequest().fromXml(xml);
		checkUnmarshalledVerifyEnrolledRequest(fromXmlRequest);
	}

	/**
	 * Tests conversion of {@link ThreeDSecureRequest} verify sig to and from XML using setters.
	 */
	@Test
	public void threeDSecureSigRequestXmlWithSettersTest() {

		Card card = new Card();
		card.setExpiryDate(CARD_EXPIRY_DATE);
		card.setNumber(CARD_NUMBER);
		card.setType(CardType.VISA.getType());
		card.setCardHolderName(CARD_HOLDER_NAME);
		card.setIssueNumber(CARD_ISSUE_NUMBER);

		Cvn cvn = new Cvn();
		cvn.setNumber(CARD_CVN_NUMBER);
		cvn.setPresenceIndicator(CARD_CVN_PRESENCE.getIndicator());
		card.setCvn(cvn);

		ThreeDSecureRequest request = new ThreeDSecureRequest();
		request.setAccount(ACCOUNT);
		request.setMerchantId(MERCHANT_ID);

		Amount amount = new Amount();
		amount.setAmount(AMOUNT);
		amount.setCurrency(CURRENCY);
		request.setAmount(amount);

		request.setCard(card);
		request.setTimeStamp(TIMESTAMP);
		request.setOrderId(ORDER_ID);
		request.setPares(THREE_D_SECURE_PARES);
		request.setHash(THREE_D_SECURE_VERIFY_SIG_REQUEST_HASH);
		request.setType(ThreeDSecureType.VERIFY_SIG.getType());

		//convert to XML
		String xml = request.toXml();

		//Convert from XML back to PaymentRequest
		ThreeDSecureRequest fromXmlRequest = new ThreeDSecureRequest().fromXml(xml);
		checkUnmarshalledVerifySigRequest(fromXmlRequest);
	}

	/**
	 * Tests conversion of {@link ThreeDSecureRequest} card enrolled to and from XML using setters.
	 */
	@Test
	public void threeDSecureCardEnrolledRequestXmlWithSettersTest() {

		ThreeDSecureRequest request = new ThreeDSecureRequest();
		request.setAccount(CARD_VERIFY_ACCOUNT);
		request.setMerchantId(CARD_VERIFY_MERCHANT_ID);


		PaymentData paymentData = new PaymentData()
				.addCvnNumber(CARD_PAYMENT_DATA_CVN);
		request.setPaymentData(paymentData);

		Amount amount = new Amount();
		amount.setAmount(Long.parseLong(CARD_VERIFY_AMOUNT));
		amount.setCurrency(CARD_VERIFY_CURRENCY);
		request.setAmount(amount);


		request.setTimeStamp(CARD_VERIFY_TIMESTAMP);
		request.setOrderId(CARD_VERIFY_ORDER_ID);
		request.setPaymentMethod(CARD_VERIFY_REF);
		request.setPayerRef(CARD_VERIFY_PAYER_REF);
		request.setHash(CARD_VERIFY_REQUEST_HASH);
		request.setType(ThreeDSecureType.VERIFY_CARD_ENROLLED.getType());

		//convert to XML
		String xml = request.toXml();

		//Convert from XML back to PaymentRequest
		ThreeDSecureRequest fromXmlRequest = new ThreeDSecureRequest().fromXml(xml);
		checkUnmarshalledVerifyCardEnrolledPaymentRequest(fromXmlRequest);
	}


	/**
	 * Tests conversion of {@link ThreeDSecureResponse} from XML file for verify enrolled
	 */
	@Test
	public void threeDSecureEnrolledResponseXmlFromFileTest() {

		File file = new File(this.getClass().getResource(THREE_D_SECURE_VERIFY_ENROLLED_RESPONSE_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//unmarshal back to re
		ThreeDSecureResponse fromXmlResponse = new ThreeDSecureResponse().fromXml(source);
		checkUnmarshalledThreeDSecureEnrolledResponse(fromXmlResponse);
	}

	/**
	 * Tests conversion of {@link ThreeDSecureResponse} from XML file for verify sig 
	 */
	@Test
	public void threeDSecureSigResponseXmlFromFileTest() {

		File file = new File(this.getClass().getResource(THREE_D_SECURE_VERIFY_SIG_RESPONSE_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//unmarshal back to response
		ThreeDSecureResponse fromXmlResponse = new ThreeDSecureResponse().fromXml(source);
		checkUnmarshalledThreeDSecureSigResponse(fromXmlResponse);
	}

	/**
	 * Tests conversion of {@link ThreeDSecureRequest} from XML file for verify enrolled.
	 */
	@Test
	public void threeDSecureRequestEnrolledXmlFromFileTest() {

		File file = new File(this.getClass().getResource(THREE_D_SECURE_VERIFY_ENROLLED_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		ThreeDSecureRequest fromXmlRequest = new ThreeDSecureRequest().fromXml(source);
		checkUnmarshalledVerifyEnrolledRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link ThreeDSecureRequest} from XML file for verify sig.
	 */
	@Test
	public void threeDSecureRequestSigXmlFromFileTest() {

		File file = new File(this.getClass().getResource(THREE_D_SECURE_VERIFY_SIG_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		ThreeDSecureRequest fromXmlRequest = new ThreeDSecureRequest().fromXml(source);
		checkUnmarshalledVerifySigRequest(fromXmlRequest);

	}


	/**
	 * Tests conversion of {@link ThreeDSecureRequest} from XML file for verify sig.
	 */
	@Test
	public void threeDSecureRequestCardEnrolledFromFileTest() {

		File file = new File(this.getClass().getResource(CARD_VERIFY_ENROLLED_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		ThreeDSecureRequest fromXmlRequest = new ThreeDSecureRequest().fromXml(source);
		checkUnmarshalledVerifyCardEnrolledPaymentRequest(fromXmlRequest);

	}


	/**
	 * Test expected {@link RealexException} when unmarshalling invalid xml.
	 */
	@Test(expected = RealexException.class)
	public void threeDSecureFromXmlErrorTest() {
		//Try to unmarshal invalid XML
		XmlUtils.fromXml(new StreamSource(new StringReader("<xml>test</xml>")), MessageType.THREE_D_SECURE);
	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for mobile-auth payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileMobileAuthTest() {

		File file = new File(this.getClass().getResource(MOBILE_AUTH_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledMobileAuthPaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for settle payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileSettleTest() {

		File file = new File(this.getClass().getResource(SETTLE_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledSettlePaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for void payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileVoidTest() {

		File file = new File(this.getClass().getResource(VOID_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledVoidPaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for rebate payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileRebateTest() {

		File file = new File(this.getClass().getResource(REBATE_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledRebatePaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for OTB payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileOtbTest() {

		File file = new File(this.getClass().getResource(OTB_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledOtbPaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for credit payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileCreditTest() {

		File file = new File(this.getClass().getResource(CREDIT_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledCreditPaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for hold payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileHoldTest() {

		File file = new File(this.getClass().getResource(HOLD_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledHoldPaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for release payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileReleaseTest() {

		File file = new File(this.getClass().getResource(RELEASE_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledReleasePaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for receipt-in payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileReceiptInTest() {

		File file = new File(this.getClass().getResource(RECEIPT_IN_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledReceiptInPaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for receipt-in payment types.
	 */
	@Test
	public void paymentRequestXmlFromFilePaymentOutTest() {

			File file = new File(this.getClass().getResource(PAYMENT_OUT_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledPaymentOutPaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for payer-new payment types.
	 */
	@Test
	public void paymentRequestXmlFromFilePayerNewTest() {

		File file = new File(this.getClass().getResource(PAYER_NEW_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledPayerNewPaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for payer-new payment types.
	 */
	@Test
	public void paymentRequestXmlFromFilePayerEditTest() {

		File file = new File(this.getClass().getResource(PAYER_EDIT_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledPayerEditPaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for payer-new payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileCardNewTest() {

		File file = new File(this.getClass().getResource(CARD_NEW_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledCardAddPaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for payer-new payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileCardEditReplaceCardTest() {

		File file = new File(this.getClass().getResource(CARD_EDIT_REPLACE_CARD_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledCardEditReplaceCardPaymentRequest(fromXmlRequest);
	}


	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for payer-new payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileCardEditReplaceIssueNoTest() {

		File file = new File(this.getClass().getResource(CARD_EDIT_UPDATE_ISSUE_NO_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledCardEditReplaceIssueNoPaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for payer-new payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileCardEditReplaceCHNameTest() {

		File file = new File(this.getClass().getResource(CARD_EDIT_UPDATE_CH_NAME_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledCardEditReplaceCHNamePaymentRequest(fromXmlRequest);

	}

	/**
	 * Tests conversion of {@link PaymentRequest} from XML file for payer-new payment types.
	 */
	@Test
	public void paymentRequestXmlFromFileCardDeleteTest() {

		File file = new File(this.getClass().getResource(CARD_DELETE_PAYMENT_REQUEST_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);

		//Convert from XML back to PaymentRequest
		PaymentRequest fromXmlRequest = new PaymentRequest().fromXml(source);
		checkUnmarshalledCardDeletePaymentRequest(fromXmlRequest);

	}
}
