package com.realexpayments.remote.sdk.domain.payment;

import com.realexpayments.remote.sdk.domain.DccInfoResult;
import com.realexpayments.remote.sdk.domain.Response;
import com.realexpayments.remote.sdk.utils.GenerationUtils;
import com.realexpayments.remote.sdk.utils.ResponseUtils;
import com.realexpayments.remote.sdk.utils.XmlUtils;
import com.realexpayments.remote.sdk.utils.XmlUtils.MessageType;

import javax.xml.bind.annotation.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;

/**
 * <p>
 * Class representing a Payment response received from Realex.
 * </p>
 * 
 * @author markstanford
 *
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentResponse implements Response<PaymentResponse> {

	/**
	 * Time stamp in the format YYYYMMDDHHMMSS, which represents the time in the format year
	 * month date hour minute second. 
	 */
	@XmlAttribute(name = "timestamp")
	private String timeStamp;

	/**
	 * Represents Realex Payments assigned merchant id.
	 */
	@XmlElement(name = "merchantid")
	private String merchantId;

	/**
	 * Represents the Realex Payments subaccount to use. If you omit this element then we will use your default account. 
	 */
	@XmlElement(name = "account")
	private String account;

	/**
	 * Represents the unique order id of this transaction. Must be unique across all of your accounts.
	 */
	@XmlElement(name = "orderid")
	private String orderId;

	/**
	 * The result codes returned by the Realex Payments system.
	 */
	@XmlElement(name = "result")
	private String result;

	/**
	 * If successful an authcode is returned from the bank. Used when referencing this transaction in refund and void requests.
	 */
	@XmlElement(name = "authcode")
	private String authCode;

	/**
	 * The text of the response.
	 */
	@XmlElement(name = "message")
	private String message;

	/**
	 * The Realex payments reference (pasref) for the transaction. Used when referencing this transaction in refund and void requests.
	 */
	@XmlElement(name = "pasref")
	private String paymentsReference;

	/**
	 * <p>
	 * The result of the Card Verification check.
	 * </p>
	 * <p>
	 * <ul>
	 * <li>M: CVV Matched</li>
	 * <li>N: CVV Not Matched</li>
	 * <li>I: CVV Not checked due to circumstances</li>
	 * <li>U: CVV Not checked - issuer not certified</li>
	 * <li>P: CVV Not Processed</li>
	 * </ul>
	 * </p>
	 */
	@XmlElement(name = "cvnresult")
	private String cvnResult;

	/**
	 * The time taken.
	 */
	@XmlElement(name = "timetaken")
	private Long timeTaken;

	/**
	 * The AUTH time taken.
	 */
	@XmlElement(name = "authtimetaken")
	private Long authTimeTaken;

	/**
	 * The raw XML response from the acquirer (if the account is set up to return this).
	 */
	@XmlElement(name = "acquirerresponse")
	private String acquirerResponse;

	/**
	 * The batch id of the transaction. Returned in the case of auth and refund requests. This can be used to assist with the reconciliation of your batches.
	 */
	@XmlElement(name = "batchid")
	private Long batchId;

	/**
	 * The details of the cardholder's bank (if available).
	 */
	@XmlElement(name = "cardissuer")
	private CardIssuer cardIssuer;

	/**
	 * The SHA-1 hash of certain elements of the response. The details of this are to be found in the realauth developer's guide.
	 */
	@XmlElement(name = "sha1hash")
	private String hash;

	/**
	 * The results of realscore.
	 */
	@XmlElement(name = "tss")
	private TssResult tssResult;

	/**
	 * <p>
	 * Contains postcode match result from Address Verification Service.
	 * </p>
	 * <ul>
	 * <li>M: Matched</li>
	 * <li>N: Not Matched</li>
	 * <li>I: Problem with check</li>
	 * <li>U: Unable to check</li>
	 * <li>P: Partial match</li>
	 * </ul> 
	 */
	@XmlElement(name = "avspostcoderesponse")
	private String avsPostcodeResponse;

	/**
	 * <p>
	 * Contains address match result from Address Verification Service.
	 * </p>
	 * <ul>
	 * <li>M: Matched</li>
	 * <li>N: Not Matched</li>
	 * <li>I: Problem with check</li>
	 * <li>U: Unable to check</li>
	 * <li>P: Partial match</li>
	 * </ul> 
	 */
	@XmlElement(name = "avsaddressresponse")
	private String avsAddressResponse;

	/**
	 * The results of dcc rate lookup
	 */
	@XmlElement(name = "dccinfo")
	private DccInfoResult dccInfoResult;

	/**
	 * The fraudFilter field.
	 */
	@XmlElement(name = "fraudfilter")
	private FraudFilter fraudFilter;

	/**
	 * PaymentResponse constructor.
	 */
	public PaymentResponse() {
	}

	/**
	 * Getter for time stamp.
	 * 
	 * @return String
	 */
	@Override
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Setter for time stamp.
	 * 
	 * @param timeStamp
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Getter for merchant ID.
	 * 
	 * @return String
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * Setter for merchant ID.
	 * 
	 * @param merchantId
	 */
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	/**
	 * Setter for account.
	 * 
	 * @return String
	 */
	public String getAccount() {
		return account;
	}

	/**
	 * Setter for account.
	 * 
	 * @param account
	 */
	public void setAccount(String account) {
		this.account = account;
	}

	/**
	 * Getter for order ID.
	 * 
	 * @return String
	 */
	@Override
	public String getOrderId() {
		return orderId;
	}

	/**
	 * Setter for order ID.
	 * 
	 * @param orderId
	 */
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	/**
	 * Getter for result.
	 * 
	 * @return String
	 */
	@Override
	public String getResult() {
		return result;
	}

	/**
	 * Setter for result.
	 * 
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Getter for message.
	 * 
	 * @return String
	 */
	@Override
	public String getMessage() {
		return message;
	}

	/**
	 * Setter for message.
	 * 
	 * @param message
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Getter for payments reference.
	 * 
	 * @return String
	 */
	public String getPaymentsReference() {
		return paymentsReference;
	}

	/**
	 * Setter for payments reference.
	 * 
	 * @param paymentsReference
	 */
	public void setPaymentsReference(String paymentsReference) {
		this.paymentsReference = paymentsReference;
	}

	/**
	 * Getter for CVN result.
	 * 
	 * @return String
	 */
	public String getCvnResult() {
		return cvnResult;
	}

	/**
	 * Setter for CVN result.
	 * 
	 * @param cvnResult
	 */
	public void setCvnResult(String cvnResult) {
		this.cvnResult = cvnResult;
	}

	/**
	 * Getter for batch ID.
	 * 
	 * @return Long
	 */
	public Long getBatchId() {
		return batchId;
	}

	/**
	 * Setter for batch ID.
	 * 
	 * @param batchId
	 */
	public void setBatchId(Long batchId) {
		this.batchId = batchId;
	}

	/**
	 * Getter for {@link CardIssuer}.
	 * 
	 * @return CardIssuer
	 */
	public CardIssuer getCardIssuer() {
		return cardIssuer;
	}

	/**
	 * Setter for {@link CardIssuer}.
	 * 
	 * @param cardIssuer
	 */
	public void setCardIssuer(CardIssuer cardIssuer) {
		this.cardIssuer = cardIssuer;
	}

	/**
	 * Getter for SHA1 hash.
	 * 
	 * @return String
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * Setter for SHA1 hash.
	 * 
	 * @param hash	
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Getter for time taken.
	 * 
	 * @return Long
	 */
	public Long getTimeTaken() {
		return timeTaken;
	}

	/**
	 * Setter for time taken.
	 * 
	 * @param timeTaken
	 */
	public void setTimeTaken(Long timeTaken) {
		this.timeTaken = timeTaken;
	}

	/**
	 * Getter for AUTH time taken.
	 * 
	 * @return Long
	 */
	public Long getAuthTimeTaken() {
		return authTimeTaken;
	}

	/**
	 * Setter for AUTH time taken.
	 * 
	 * @param authTimeTaken
	 */
	public void setAuthTimeTaken(Long authTimeTaken) {
		this.authTimeTaken = authTimeTaken;
	}

	/**
	 * Getter for acquirer response
	 * 
	 * @return String
	 */
	public String getAcquirerResponse() {
		return acquirerResponse;
	}

	/**
	 * Setter for acquirer response.
	 * 
	 * @param acquirerResponse
	 */
	public void setAcquirerResponse(String acquirerResponse) {
		this.acquirerResponse = acquirerResponse;
	}

	/**
	 * Getter for auth code.
	 * 
	 * @return String
	 */
	public String getAuthCode() {
		return authCode;
	}

	/**
	 * Setter for auth code.
	 * 
	 * @param authCode
	 */
	public void setAuthCode(String authCode) {
		this.authCode = authCode;
	}

	/**
	 * Getter for {@link TssResult}
	 * 
	 * @return TssResult
	 */
	public TssResult getTssResult() {
		return tssResult;
	}

	/**
	 * Getter for avsPostcodeResponse.
	 * 
	 * @return String
	 */
	public String getAvsPostcodeResponse() {
		return avsPostcodeResponse;
	}

	/**
	 * Setter for avsPostcodeResponse.
	 * 
	 * @param avsPostcodeResponse
	 */
	public void setAvsPostcodeResponse(String avsPostcodeResponse) {
		this.avsPostcodeResponse = avsPostcodeResponse;
	}

	/**
	 * Getter for avsAddressResponse.
	 * 
	 * @return String
	 */
	public String getAvsAddressResponse() {
		return avsAddressResponse;
	}

	/**
	 * Setter for avsAddressResponse.
	 * 
	 * @param avsAddressResponse
	 */
	public void setAvsAddressResponse(String avsAddressResponse) {
		this.avsAddressResponse = avsAddressResponse;
	}

	/**
	 * Setter for {@link TssResult}
	 * 
	 * @param tssResult
	 */
	public void setTssResult(TssResult tssResult) {
		this.tssResult = tssResult;
	}


	/**
	 * Getter for {@link DccInfoResult}
	 *
	 * @return DccInfoResult
	 */
	public DccInfoResult getDccInfoResult() {
		return dccInfoResult;
	}

	/**
	 * Setter for {@link DccInfoResult}
	 *
	 * @param dccInfoResult
	 */
	public void setDccInfoResult(DccInfoResult dccInfoResult) {
		this.dccInfoResult = dccInfoResult;
	}

	/**
	 * Getter for {@link DccInfoResult}
	 *
	 * @return FraudFilter
	 */
	public FraudFilter getFraudFilter() {
		return fraudFilter;
	}

	/**
	 * Setter for {@link FraudFilter}
	 *
	 * @param fraudFilter
	 */
	public void setFraudFilter(FraudFilter fraudFilter) {
		this.fraudFilter = fraudFilter;
	}


	/**
	 * {@inheritDoc}
	 */
	@Override
	public PaymentResponse fromXml(Source xml) {
		return (PaymentResponse) XmlUtils.fromXml(xml, MessageType.PAYMENT);
	}

	/**
	 * Helper method which unmarshals the passed XML to a PaymentResponse object.  
	 * 
	 * @param xml
	 * @return PaymentResponse
	 */
	public PaymentResponse fromXml(String xml) {
		return fromXml(new StreamSource(new StringReader(xml)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toXml() {
		return XmlUtils.toXml(this, MessageType.PAYMENT);
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean isHashValid(String secret) {

		boolean hashValid = false;

		//check for any null values and set them to empty string for hashing
		String timeStamp = null == this.timeStamp ? "" : this.timeStamp;
		String merchantId = null == this.merchantId ? "" : this.merchantId;
		String orderId = null == this.orderId ? "" : this.orderId;
		String result = null == this.result ? "" : this.result;
		String message = null == this.message ? "" : this.message;
		String paymentsReference = null == this.paymentsReference ? "" : this.paymentsReference;
		String authCode = null == this.authCode ? "" : this.authCode;

		//create String to hash
		String toHash = new StringBuilder().append(timeStamp)
				.append(".")
				.append(merchantId)
				.append(".")
				.append(orderId)
				.append(".")
				.append(result)
				.append(".")
				.append(message)
				.append(".")
				.append(paymentsReference)
				.append(".")
				.append(authCode)
				.toString();

		//check if calculated hash matches returned value
		String expectedHash = GenerationUtils.generateHash(toHash, secret);
		if (expectedHash.equals(this.hash)) {
			hashValid = true;
		}

		return hashValid;
	}

	/**
	 * 
	 * {@inheritDoc}
	 */
	@Override
	public boolean isSuccess() {
		return ResponseUtils.isSuccess(this);
	}
}
