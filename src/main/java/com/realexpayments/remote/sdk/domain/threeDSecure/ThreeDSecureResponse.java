package com.realexpayments.remote.sdk.domain.threeDSecure;

import java.io.StringReader;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import com.realexpayments.remote.sdk.domain.Response;
import com.realexpayments.remote.sdk.utils.GenerationUtils;
import com.realexpayments.remote.sdk.utils.ResponseUtils;
import com.realexpayments.remote.sdk.utils.XmlUtils;
import com.realexpayments.remote.sdk.utils.XmlUtils.MessageType;

/**
 * <p>
 * Class representing a 3DSecure response received from Realex.
 * </p>
 * 
 * @author markstanford
 *
 */
@XmlRootElement(name = "response")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThreeDSecureResponse implements Response<ThreeDSecureResponse> {

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
	 * The pre-encoded PaReq that you must post to the Issuer's ACS url.
	 */
	@XmlElement(name = "pareq")
	private String pareq;

	/**
	 * The URL of the Issuer ACS.
	 */
	@XmlElement(name = "url")
	private String url;

	/**
	 * Enrolment response from ACS.
	 */
	@XmlElement(name = "enrolled")
	private String enrolled;

	/**
	 * XID from ACS.
	 */
	@XmlElement(name = "xid")
	private String xid;

	/**
	 * The 3D Secure details.
	 */
	@XmlElement(name = "threedsecure")
	private ThreeDSecure threeDSecure;

	/**
	 * The SHA-1 hash of certain elements of the response. The details of this are to be found in the realmpi developer's guide.
	 */
	@XmlElement(name = "sha1hash")
	private String hash;

	/**
	 * ThreeDSecureResponse constructor.
	 */
	public ThreeDSecureResponse() {
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
	 * @return paymentsReference
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
	 * Getter for PAReq.
	 * 
	 * @return String
	 */
	public String getPareq() {
		return pareq;
	}

	/**
	 * Setter for PAReq.
	 * 
	 * @param pareq
	 */
	public void setPareq(String pareq) {
		this.pareq = pareq;
	}

	/**
	 * Getter for ACS URL.
	 * 
	 * @return String
	 */
	public String getUrl() {
		return url;
	}

	/**
	 * Setter for ACS URL.
	 * 
	 * @param url
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * Getter for enrolled.
	 * 
	 * @return String
	 */
	public String getEnrolled() {
		return enrolled;
	}

	/**
	 * Setter for enrolled.
	 * 
	 * @param enrolled
	 */
	public void setEnrolled(String enrolled) {
		this.enrolled = enrolled;
	}

	/**
	 * Getter for XID.
	 * 
	 * @return String
	 */
	public String getXid() {
		return xid;
	}

	/**
	 * Setter for XID.
	 * 
	 * @param xid
	 */
	public void setXid(String xid) {
		this.xid = xid;
	}

	/**
	 * Getter for the 3D Secure details.
	 * 
	 * @return threeDSecure
	 */
	public ThreeDSecure getThreeDSecure() {
		return threeDSecure;
	}

	/**
	 * Setter for the 3D Secure details.
	 * 
	 * @param threeDSecure the threeDSecure to set
	 */
	public void setThreeDSecure(ThreeDSecure threeDSecure) {
		this.threeDSecure = threeDSecure;
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
	 * {@inheritDoc}
	 */
	@Override
	public ThreeDSecureResponse fromXml(Source xml) {
		return (ThreeDSecureResponse) XmlUtils.fromXml(xml, MessageType.THREE_D_SECURE);
	}

	/**
	 * Helper method which unmarshals the passed XML to a ThreeDSecureResponse object.  
	 * 
	 * @param xml
	 * @return ThreeDSecureResponse
	 */
	public ThreeDSecureResponse fromXml(String xml) {
		return fromXml(new StreamSource(new StringReader(xml)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toXml() {
		return XmlUtils.toXml(this, MessageType.THREE_D_SECURE);
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
