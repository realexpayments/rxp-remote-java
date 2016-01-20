package com.realexpayments.remote.sdk.domain.payment;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import com.realexpayments.remote.sdk.domain.Amount;
import com.realexpayments.remote.sdk.domain.Card;
import com.realexpayments.remote.sdk.domain.PaymentData;
import com.realexpayments.remote.sdk.domain.Request;
import com.realexpayments.remote.sdk.domain.payment.Address.AddressType;
import com.realexpayments.remote.sdk.utils.GenerationUtils;
import com.realexpayments.remote.sdk.utils.XmlUtils;
import com.realexpayments.remote.sdk.utils.XmlUtils.MessageType;

/**
 * <p>
 * Class representing a Payment request to be sent to Realex.
 * </p>
 * <p>
 * Helper methods are provided (prefixed with 'add') for object creation.
 * </p>
 * <p>
 * Example AUTH:
 * </p>
 * <p><code><pre>
 * Card card = new Card()
 *	.addExpiryDate("0119")
 *	.addNumber("420000000000000000")
 *	.addType(CardType.VISA)
 *	.addCardHolderName("Joe Smith")
 *	.addCvn("123")
 *	.addCvnPresenceIndicator(PresenceIndicator.CVN_PRESENT);
 * 
 * PaymentRequest request = new PaymentRequest()
 *	.addAccount("yourAccount")
 *	.addMerchantId("yourMerchantId")
 *	.addType(PaymentType.AUTH)
 *	.addAmount(100)
 *	.addCurrency("EUR")
 *	.addCard(card)
 *	.addAutoSettle(new AutoSettle().addFlag(AutoSettleFlag.TRUE));
 * </pre></code></p>
 * 
 * <p>
 * Example AUTH with Address Verification:
 * <p>
 * <p><code><pre>
 * Card card = new Card()
 *	.addExpiryDate("0119")
 *	.addNumber("420000000000000000")
 *	.addType(CardType.VISA)
 *	.addCardHolderName("Joe Smith")
 *	.addCvn("123")
 *	.addCvnPresenceIndicator(PresenceIndicator.CVN_PRESENT);
 * 
 * PaymentRequest request = new PaymentRequest()
 *	.addAccount("yourAccount")
 *	.addMerchantId("yourMerchantId")
 *	.addType(PaymentType.AUTH)
 *	.addAmount(100)
 *	.addCurrency("EUR")
 *	.addCard(card)
 *	.addAutoSettle(new AutoSettle().addFlag(AutoSettleFlag.TRUE))
 *	.addAddressVerificationServiceDetails("382 The Road", "WB1 A42");
 * </pre></code></p>
 * 
 * <p>
 * Example AUTH MOBILE
 * <p>
 * <p><code><pre>
 * PaymentRequest request = new PaymentRequest()
 *	.addAccount("yourAccount")
 *	.addMerchantId("yourMerchantId")
 *	.addType(PaymentType.AUTH_MOBILE)
 *	.addAutoSettle(new AutoSettle().addFlag(AutoSettleFlag.TRUE))
 *	.addMobile("apple-pay")
 *	.addToken("{auth mobile payment token}");
 * </pre></code></p>
 * 
 * <p>
 * Example SETTLE:
 * </p>
 * <p><code><pre>
 * PaymentRequest request = new PaymentRequest()
 *	.addAccount("yourAccount")
 *	.addMerchantId("yourMerchantId")
 *	.addType(PaymentType.SETTLE)
 *	.addOrderId("Order ID from original transaction")
 *	.addAmount(100)
 *	.addCurrency("EUR")
 *	.addPaymentsReference("pasref from original transaction")
 *	.addAuthCode("Auth code from original transaction");
 * </pre></code></p>
 * 
 * <p>
 * Example VOID:
 * </p>
 * <p><code><pre>
 * PaymentRequest request = new PaymentRequest()
 *	.addAccount("yourAccount")
 *	.addMerchantId("yourMerchantId")
 *	.addType(PaymentType.VOID)
 *	.addOrderId("Order ID from original transaction")
 *	.addPaymentsReference("pasref from original transaction")
 *	.addAuthCode("Auth code from original transaction");
 * </pre></code></p>
 * 
 * <p>
 * Example REBATE:
 * </p>
 * <p><code><pre>
 * PaymentRequest request = new PaymentRequest()
 *	.addAccount("yourAccount")
 *	.addMerchantId("yourMerchantId")
 *	.addType(PaymentType.REBATE)
 *	.addOrderId("Order ID from original transaction")
 *	.addAmount(100)
 *	.addCurrency("EUR")
 *	.addPaymentsReference("pasref from original transaction")
 *	.addAuthCode("Auth code from original transaction")
 *	.addRefundHash("Hash of rebate password shared with Realex");
 * </pre></code></p> 
 * 
 * <p>
 * Example OTB:
 * </p>
 * <p><code><pre>
 * Card card = new Card()
 *	.addExpiryDate("0119")
 *	.addNumber("420000000000000000")
 *	.addType(CardType.VISA)
 *	.addCardHolderName("Joe Smith")
 *	.addCvn("123")
 *	.addCvnPresenceIndicator(PresenceIndicator.CVN_PRESENT);
 * 
 * PaymentRequest request = new PaymentRequest()
 *	.addAccount("yourAccount")
 *	.addMerchantId("yourMerchantId")
 *	.addType(PaymentType.OTB)
 *	.addCard(card);
 * </pre></code></p>
 * 
 * <p>
 * Example CREDIT:
 * </p>
 * <p><code><pre>
 * PaymentRequest request = new PaymentRequest()
 *	.addAccount("yourAccount")
 *	.addMerchantId("yourMerchantId")
 *	.addType(PaymentType.CREDIT)
 *	.addAmount(100)
 *	.addCurrency("EUR")
 *	.addPaymentsReference("Pasref from original transaction")
 *	.addAuthCode("Auth code from original transaction")
 *	.addRefundHash("Hash of credit password shared with Realex");
 * </pre></code></p> 
 * 
 * <p>
 * Example HOLD:
 * </p>
 * <p><code><pre>
 * PaymentRequest request = new PaymentRequest()
 *	.addAccount("yourAccount")
 *	.addMerchantId("yourMerchantId")
 *	.addType(PaymentType.HOLD)
 *	.addOrderId("Order ID from original transaction")
 *	.addPaymentsReference("Pasref from original transaction");
 * </pre></code></p> 
 * 
 * <p>
 * Example RELEASE:
 * </p>
 * <p><code><pre>
 * PaymentRequest request = new PaymentRequest()
 *	.addAccount("yourAccount")
 *	.addMerchantId("yourMerchantId")
 *	.addType(PaymentType.RELEASE)
 *	.addOrderId("Order ID from original transaction")
 *	.addPaymentsReference("Pasref from original transaction");
 * </pre></code></p>
 *
 * <p>
 * Example Receipt-in:
 * </p>
 * <p><code><pre>
 * PaymentData paymentData = new PaymentData()
 * 	.addCvnNumber("123");
 *
 * PaymentRequest request = new PaymentRequest()
 *	.addAccount("yourAccount")
 *	.addMerchantId("yourMerchantId")
 *	.addType(PaymentType.RECEIPT_IN)
 *	.addOrderId("Order ID from original transaction")
 *	.addAmount(100)
 *	.addCurrency("EUR")
 *	.addPayerRef("payer ref from customer")
 *	.addPaymentMethod("payment method ref from customer")
 *	.addPaymentData(paymentData);
 * </pre></code></p>
 *
 * <p>
 * Example Payment-out:
 * </p>
 * <p><code><pre>
 * PaymentRequest request = new PaymentRequest()
 *  .addAccount("yourAccount")
 *  .addMerchantId("yourMerchantId")
 *  .addType(PaymentType.PAYMENT_OUT)
 *  .addAmount(100)
 *  .addCurrency("EUR")
 *  .addPayerRef("payer ref from customer")
 *  .addPaymentMethod("payment method ref from customer")
 *  .addRefundHash("Hash of rebate password shared with Realex");
 *
 *  RealexClient client = new RealexClient("shared secret");
 *  PaymentResponse response = client.send(request);
 *
 * @author markstanford
 */

@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentRequest implements Request<PaymentRequest, PaymentResponse> {

	/**
	 * Enumeration for the Payment type.
	 */
	public enum PaymentType {
		AUTH("auth"),
		AUTH_MOBILE("auth-mobile"),
		SETTLE("settle"),
		VOID("void"),
		REBATE("rebate"),
		OTB("otb"),
		CREDIT("credit"),
		HOLD("hold"),
		RELEASE("release"),
		RECEIPT_IN("receipt-in"),
		PAYMENT_OUT("payment-out");

		/**
		 * The payment type String value 
		 */
		private String type;

		/**
		 * PaymentType constructor
		 * 
		 * @param type
		 */
		PaymentType(String type) {
			this.type = type;
		}

		/**
		 * Get the string value of the payment type
		 * 
		 * @return String 
		 */
		public String getType() {
			return type;
		}
	}

	/**
	 * Format of timestamp is yyyyMMddhhmmss  e.g. 20150131094559 for 31/01/2015 09:45:59. 
	 * If the timestamp is more than a day (86400 seconds) away from the server time, then the request is rejected. 
	 */
	@XmlAttribute(name = "timestamp")
	private String timeStamp;

	/**
	 * The payment type. 
	 */
	@XmlAttribute(name = "type")
	private String type;

	/**
	 * Represents Realex Payments assigned merchant id.
	 */
	@XmlElement(name = "merchantid")
	private String merchantId;

	/**
	 * Represents the Realex Payments subaccount to use. If this element is omitted, then the
	 * default account is used.
	 */
	@XmlElement(name = "account")
	private String account;

	/**
	 * For certain acquirers it is possible to specify whether a transaction is to be processed as a
	 * Mail Order/Telephone Order or Ecommerce transaction. For other banks, this is configured on the
	 * Merchant ID level.
	 */
	@XmlElement(name = "channel")
	private String channel;

	/**
	 * Represents the unique order id of this transaction. Must be unique across all of the sub-accounts.
	 */
	@XmlElement(name = "orderid")
	private String orderId;

	/**
	 * {@link Amount} object containing the amount value and the currency type.
	 */
	@XmlElement(name = "amount")
	private Amount amount;

	/**
	 * {@link Card} object containing the card details to be passed in request.
	 */
	@XmlElement(name = "card")
	private Card card;

	/**
	 * {@link AutoSettle} object containing the auto settle flag.
	 */
	@XmlElement(name = "autosettle")
	private AutoSettle autoSettle;

	/**
	 * Hash constructed from the time stamp, merchand ID, order ID, amount, currency, card number
	 * and secret values.
	 */
	@XmlElement(name = "sha1hash")
	private String hash;

	/**
	 * List of {@link Comment} objects to be passed in request. Optionally, up to two comments can be associated with any transaction.
	 */
	@XmlElementWrapper(name = "comments")
	@XmlElements(@XmlElement(name = "comment", type = Comment.class))
	private List<Comment> comments;

	/**
	 * <p>
	 * Represents the Realex Payments reference of the original transaction (this is included in the 
	 * response to the auth).
	 * </p>
	 * <p>
	 * Used in requests where the original transaction must be referenced e.g. a REBATE request.
	 * </p> 
	 */
	@XmlElement(name = "pasref")
	private String paymentsReference;

	/**
	 * <p>
	 * Represents the authcode of the original transaction, which was included in the response.
	 * </p>
	 * <p>
	 * Used in requests where the original transaction must be referenced e.g. a REBATE request.
	 * </p> 
	 */
	@XmlElement(name = "authcode")
	private String authCode;

	/**
	 * Represents a hash of the refund password, which Realex Payments will provide. The SHA1 
	 * algorithm must be used to generate this hash.
	 */
	@XmlElement(name = "refundhash")
	private String refundHash;

	/**
	 * Fraud filter flag
	 */
	@XmlElement(name = "fraudfilter")
	private String fraudFilter;

	/**
	 * If you are configured for recurring/continuous authority transactions, you must set the recurring values.
	 */
	@XmlElement(name = "recurring")
	private Recurring recurring;

	/**
	 * TSS Info contains optional variables which can be used to identify customers in the
	 * Realex Payments system.
	 */
	@XmlElement(name = "tssinfo")
	private TssInfo tssInfo;

	/**
	 * Contains 3D Secure/Secure Code information if this transaction has used a 3D 
	 * Secure/Secure Code system, either Realex's RealMPI or a third party's.
	 */
	@XmlElement(name = "mpi")
	private Mpi mpi;

	/**
	 * The mobile auth payment type e.g. apple-pay.
	 */
	@XmlElement(name = "mobile")
	private String mobile;

	/**
	 * The mobile auth payment token to be sent in place of payment data. 
	 */
	@XmlElement(name = "token")
	private String token;

	/**
	 * The payer ref for this customer
	 */
	@XmlElement(name = "payerref")
	private String payerRef;

	/**
	 * The payment reference
	 */
	@XmlElement(name = "paymentmethod")
	private String paymentMethod;


	/**
	 * Contains payment information to be used on Receipt-in transactions
	 */
	@XmlElement(name = "paymentdata")
	private PaymentData paymentData;



	/**
	 * Constructor for PaymentRequest.
	 * 
	 */
	public PaymentRequest() {
	}

	/**
	 * Getter for time stamp.
	 * 
	 * @return String
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

	/**
	 * Setter for time stamp
	 * 
	 * @param timeStamp
	 */
	public void setTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
	}

	/**
	 * Getter for payment type.
	 * 
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter for payment type
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
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
	 * Getter for merchant ID.
	 * 
	 * @return String
	 */
	public String getMerchantId() {
		return merchantId;
	}

	/**
	 * Getter for account.
	 * 
	 * @return Account
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
	 * Getter for {@link Amount}
	 * 
	 * @return Amount
	 */
	public Amount getAmount() {
		return amount;
	}

	/**
	 * Setter for {@link Amount}
	 * 
	 * @param amount
	 */
	public void setAmount(Amount amount) {
		this.amount = amount;
	}

	/**
	 * Getter for {@link Card}
	 * 
	 * @return Card
	 */
	public Card getCard() {
		return card;
	}

	/**
	 * Setter for {@link Card}
	 * 
	 * @param card
	 */
	public void setCard(Card card) {
		this.card = card;
	}

	/**
	 * Getter for {@link AutoSettle}
	 * 
	 * @return AutoSettle
	 */
	public AutoSettle getAutoSettle() {
		return autoSettle;
	}

	/**
	 * Setter for {@link AutoSettle}
	 * 
	 * @param autoSettle
	 */
	public void setAutoSettle(AutoSettle autoSettle) {
		this.autoSettle = autoSettle;
	}

	/**
	 * Getter for hash
	 * 
	 * @return String
	 */
	public String getHash() {
		return hash;
	}

	/**
	 * Setter for hash.
	 * 
	 * @param hash
	 */
	public void setHash(String hash) {
		this.hash = hash;
	}

	/**
	 * Getter for {@link Comment} list.
	 * 
	 * @return Comments
	 */
	public List<Comment> getComments() {
		return comments;
	}

	/**
	 * Setter for {@link Comment} list. 
	 * 
	 * @param comments
	 */
	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	/**
	 * Getter for payments reference (pasref). 
	 * 
	 * @return String
	 */
	public String getPaymentsReference() {
		return paymentsReference;
	}

	/**
	 * Setter for payments reference (pasref).
	 * 
	 * @param paymentsReference
	 */
	public void setPaymentsReference(String paymentsReference) {
		this.paymentsReference = paymentsReference;
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
	 * Getter for refund hash. 
	 * 
	 * @return String
	 */
	public String getRefundHash() {
		return refundHash;
	}

	/**
	 * Setter for refund hash.
	 * 
	 * @param refundHash
	 */
	public void setRefundHash(String refundHash) {
		this.refundHash = refundHash;
	}

	/**
	 * Getter for channel.
	 * 
	 * @return String 
	 */
	public String getChannel() {
		return channel;
	}

	/**
	 * Setter for channel.
	 * 
	 * @param channel
	 */
	public void setChannel(String channel) {
		this.channel = channel;
	}

	/**
	 * Getter for fraud filter.
	 * 
	 * @return String 
	 */
	public String getFraudFilter() {
		return fraudFilter;
	}

	/**
	 * Setter for fraudFilter.
	 * 
	 * @param fraudFilter
	 */
	public void setFraudFilter(String fraudFilter) {
		this.fraudFilter = fraudFilter;
	}

	/**
	 * Getter for recurring info.
	 * 
	 * @return String 
	 */
	public Recurring getRecurring() {
		return recurring;
	}

	/**
	 * Setter for recurring info.
	 * 
	 * @param recurring
	 */
	public void setRecurring(Recurring recurring) {
		this.recurring = recurring;
	}

	/**
	 * Getter for TSS info.
	 * 
	 * @return TssInfo 
	 */
	public TssInfo getTssInfo() {
		return tssInfo;
	}

	/**
	 * Setter for TSS Info.
	 * 
	 * @param tssInfo
	 */
	public void setTssInfo(TssInfo tssInfo) {
		this.tssInfo = tssInfo;
	}

	/**
	 * Getter for mpi.
	 * 
	 * @return Mpi 
	 */
	public Mpi getMpi() {
		return mpi;
	}

	/**
	 * Setter for mpi.
	 * 
	 * @param mpi
	 */
	public void setMpi(Mpi mpi) {
		this.mpi = mpi;
	}

	/**
	 * Getter for mobile.
	 * 
	 * @return String
	 */
	public String getMobile() {
		return mobile;
	}

	/**
	 * Setter for mobile.
	 * 
	 * @param mobile
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * Getter for token.
	 * 
	 * @return String
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Setter for token.
	 *
	 * @param token
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * Setter for payerRef.
	 * 
	 * @param payerRef
	 */
	public void setPayerRef(String payerRef) {
		this.payerRef = payerRef;
	}

	/**
	 * Getter for payerRef.
	 *
	 * @return String
	 */
	public String getPayerRef() {
		return payerRef;
	}

	/**
	 * Setter for paymentMethod.
	 *
	 * @param paymentMethod
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

	/**
	 * Getter for paymentMethod.
	 *
	 * @return String
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * Setter for paymentData.
	 *
	 * @param paymentData
	 */
	public void setPaymentData(PaymentData paymentData) {
		this.paymentData = paymentData;
	}

	/**
	 * Getter for paymentData.
	 *
	 * @return PaymentData
	 */
	public PaymentData getPaymentData() {
		return paymentData;
	}


	/**
	 * Helper method for adding a merchant ID.
	 * 
	 * @param merchantId
	 * @return PaymentRequest
	 */
	public PaymentRequest addMerchantId(String merchantId) {
		this.merchantId = merchantId;
		return this;
	}

	/**
	 * Helper method for adding an account.
	 * 
	 * @param account
	 * @return PaymentRequest
	 */
	public PaymentRequest addAccount(String account) {
		this.account = account;
		return this;
	}

	/**
	 * Helper method for adding a time stamp.
	 * 
	 * @param timeStamp
	 * @return PaymentRequest
	 */
	public PaymentRequest addTimeStamp(String timeStamp) {
		this.timeStamp = timeStamp;
		return this;
	}

	/**
	 * Helper method for adding an order ID.
	 * 
	 * @param orderId
	 * @return PaymentRequest
	 */
	public PaymentRequest addOrderId(String orderId) {
		this.orderId = orderId;
		return this;
	}

	/**
	 * Helper method for adding an amount. If an {@link Amount} has not been set, then one is created.
	 * 
	 * @param amount
	 * @return PaymentRequest
	 */
	public PaymentRequest addAmount(long amount) {
		if (null == this.amount) {
			this.amount = new Amount().addAmount(amount);
		} else {
			this.amount.addAmount(amount);
		}
		return this;
	}

	/**
	 * Helper method for adding a currency. If an {@link Amount} has not been set, then one is created.
	 * 
	 * @param currency
	 * @return PaymentRequest
	 */
	public PaymentRequest addCurrency(String currency) {
		if (null == this.amount) {
			this.amount = new Amount().addCurrency(currency);
		} else {
			this.amount.addCurrency(currency);
		}
		return this;
	}

	/**
	 * Helper method for adding a {@link Card}.
	 * 
	 * @param card
	 * @return PaymentRequest
	 */
	public PaymentRequest addCard(Card card) {
		this.card = card;
		return this;
	}

	/**
	 * Helper method for adding an {@link AutoSettle}.
	 * 
	 * @param autoSettle
	 * @return PaymentRequest
	 */
	public PaymentRequest addAutoSettle(AutoSettle autoSettle) {
		this.autoSettle = autoSettle;
		return this;
	}

	/**
	 * Helper method for adding a {@link PaymentType}.
	 * 
	 * @param type
	 * @return PaymentRequest
	 */
	public PaymentRequest addType(PaymentType type) {
		this.type = type.getType();
		return this;
	}

	/**
	 * Helper method for adding a payment type.
	 * 
	 * @param type
	 * @return PaymentRequest
	 */
	public PaymentRequest addType(String type) {
		this.type = type;
		return this;
	}

	/**
	 * Helper method for adding a comment. NB Only 2 comments will be accepted by Realex.
	 * 
	 * @param comment
	 * @return PaymentRequest
	 */
	public PaymentRequest addComment(String comment) {
		//create new comments array list if null
		if (null == this.comments) {
			this.comments = new ArrayList<Comment>();
		}

		int size = comments.size();
		this.comments.add(new Comment().addComment(comment).addId(++size));
		return this;
	}

	/**
	 * Helper method for adding a payments reference (pasref).
	 * 
	 * @param paymentsReference
	 * @return PaymentRequest
	 */
	public PaymentRequest addPaymentsReference(String paymentsReference) {
		this.paymentsReference = paymentsReference;
		return this;
	}

	/**
	 * Helper method for adding an auth code.
	 * 
	 * @param authCode
	 * @return PaymentRequest
	 */
	public PaymentRequest addAuthCode(String authCode) {
		this.authCode = authCode;
		return this;
	}

	/**
	 * Helper method for adding a refund hash.
	 * 
	 * @param refundHash
	 * @return PaymentRequest
	 */
	public PaymentRequest addRefundHash(String refundHash) {
		this.refundHash = refundHash;
		return this;
	}

	/**
	 * Helper method for adding hash.
	 * 
	 * @param hash
	 * @return PaymentRequest
	 */
	public PaymentRequest addHash(String hash) {
		this.hash = hash;
		return this;
	}

	/**
	 * Helper method for adding a channel.
	 * 
	 * @param channel
	 * @return PaymentRequest
	 */
	public PaymentRequest addChannel(String channel) {
		this.channel = channel;
		return this;
	}

	/**
	 * Helper method for adding a fraud filter.
	 * 
	 * @param fraudFilter
	 * @return PaymentRequest
	 */
	public PaymentRequest addFraudFilter(String fraudFilter) {
		this.fraudFilter = fraudFilter;
		return this;
	}

	/**
	 * Helper method for adding recurring info.
	 * 
	 * @param recurring
	 * @return PaymentRequest
	 */
	public PaymentRequest addRecurring(Recurring recurring) {
		this.recurring = recurring;
		return this;
	}

	/**
	 * Helper method for adding TSS info.
	 * 
	 * @param tssInfo
	 * @return PaymentRequest
	 */
	public PaymentRequest addTssInfo(TssInfo tssInfo) {
		this.tssInfo = tssInfo;
		return this;
	}

	/**
	 * Helper method for adding Mpi.
	 * 
	 * @param mpi
	 * @return PaymentRequest
	 */
	public PaymentRequest addMpi(Mpi mpi) {
		this.mpi = mpi;
		return this;
	}

	/**
	 * Helper method for adding mobile.
	 * 
	 * @param mobile
	 * @return PaymentRequest
	 */
	public PaymentRequest addMobile(String mobile) {
		this.mobile = mobile;
		return this;
	}

	/**
	 * Helper method for adding a token.
	 * 
	 * @param token
	 * @return PaymentRequest
	 */
	public PaymentRequest addToken(String token) {
		this.token = token;
		return this;
	}

	/**
	 * Helper method for adding a payer ref.
	 *
	 * @param payerRef
	 * @return PaymentRequest
	 */
	public PaymentRequest addPayerRef(String payerRef) {
		this.payerRef = payerRef;
		return this;
	}

	/**
	 * Helper method for adding a payment method.
	 *
	 * @param paymentMethod
	 * @return PaymentRequest
	 */
	public PaymentRequest addPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
		return this;
	}

	/**
	 * Helper method for adding payment data.
	 *
	 * @param paymentData
	 * @return PaymentRequest
	 */
	public PaymentRequest addPaymentData(PaymentData paymentData) {
		this.paymentData = paymentData;
		return this;
	}

	/**
	 * <p>
	 * This helper method adds Address Verification Service (AVS) fields to the request.
	 * </p>
	 * <p>
	 * The Address Verification Service (AVS) verifies the cardholder's address by checking the 
	 * information provided by at the time of sale against the issuing bank's records.
	 * </p>
	 * 
	 * @param addressLine The first line of the address e.g. 123 Fake St
	 * @param postcode The UK postcode e.g. WB1 A42
	 */
	public PaymentRequest addAddressVerificationServiceDetails(String addressLine, String postcode) {

		//build code in format <digits from postcode>|<digits from address>
		StringBuilder code = new StringBuilder()
				.append(postcode.replaceAll("\\D+", ""))
				.append("|")
				.append(addressLine.replaceAll("\\D+", ""));

		//construct billing address from code
		Address address = new Address().addCode(code.toString()).addType(AddressType.BILLING);

		//add address to TSS Info
		if (null == this.tssInfo) {
			this.tssInfo = new TssInfo();
		}

		this.tssInfo.addAddress(address);
		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toXml() {
		return XmlUtils.toXml(this, MessageType.PAYMENT);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PaymentRequest fromXml(Source xml) {
		return (PaymentRequest) XmlUtils.fromXml(xml, MessageType.PAYMENT);
	}

	/**
	 * Helper method which unmarshals the passed XML String to a PaymentRequest object.  
	 * 
	 * @param xml
	 * @return PaymentRequest
	 */
	public PaymentRequest fromXml(String xml) {
		return this.fromXml(new StreamSource(new StringReader(xml)));
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PaymentRequest generateDefaults(String secret) {

		//generate timestamp if not set
		if (null == this.timeStamp) {
			this.timeStamp = GenerationUtils.generateTimestamp();
		}

		//generate order ID if not set
		if (null == this.orderId) {
			this.orderId = GenerationUtils.generateOrderId();
		}

		//generate hash
		hash(secret);

		return this;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public PaymentResponse responseFromXml(Source xml) {
		return new PaymentResponse().fromXml(xml);
	}

	/**
	 * Creates the security hash from a number of fields and the shared secret. 
	 * 
	 * @param secret
	 * @return PaymentRequest
	 */
	public PaymentRequest hash(String secret) {

		//check for any null values and set them to empty string for hashing
		String timeStamp = null == this.timeStamp ? "" : this.timeStamp;
		String merchantId = null == this.merchantId ? "" : this.merchantId;
		String orderId = null == this.orderId ? "" : this.orderId;
		String amount = "";
		String currency = "";
		String token = null == this.token ? "" : this.token;
		String payerRef = null == this.payerRef ? "" : this.payerRef;

		if (null != this.amount) {
			amount = null == this.amount.getAmount() ? "" : this.amount.getAmount().toString();
			currency = null == this.amount.getCurrency() ? "" : this.amount.getCurrency().toString();
		}

		String cardNumber = "";

		if (null != this.card) {
			cardNumber = null == this.card.getNumber() ? "" : this.card.getNumber();
		}

		//create String to hash
		String toHash;
		if (PaymentType.AUTH_MOBILE.getType().equals(this.type)) {
			toHash = new StringBuilder().append(timeStamp)
					.append(".")
					.append(merchantId)
					.append(".")
					.append(orderId)
					.append("...")
					.append(token)
					.toString();

		} else if (PaymentType.OTB.getType().equals(this.type)) {
			toHash = new StringBuilder().append(timeStamp)
					.append(".")
					.append(merchantId)
					.append(".")
					.append(orderId)
					.append(".")
					.append(cardNumber)
					.toString();

		} else if (PaymentType.RECEIPT_IN.getType().equals(this.type) ||
				   PaymentType.PAYMENT_OUT.getType().equals(this.type))  {
			toHash = new StringBuilder().append(timeStamp)
					.append(".")
					.append(merchantId)
					.append(".")
					.append(orderId)
					.append(".")
					.append(amount)
					.append(".")
					.append(currency)
					.append(".")
					.append(payerRef)
					.toString();

		} else {
			toHash = new StringBuilder().append(timeStamp)
					.append(".")
					.append(merchantId)
					.append(".")
					.append(orderId)
					.append(".")
					.append(amount)
					.append(".")
					.append(currency)
					.append(".")
					.append(cardNumber)
					.toString();
		}

		this.hash = GenerationUtils.generateHash(toHash, secret);

		return this;
	}
}
