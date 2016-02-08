package com.realexpayments.remote.sdk.domain.threeDSecure;

import com.realexpayments.remote.sdk.domain.Amount;
import com.realexpayments.remote.sdk.domain.Card;
import com.realexpayments.remote.sdk.domain.PaymentData;
import com.realexpayments.remote.sdk.domain.Request;
import com.realexpayments.remote.sdk.domain.payment.AutoSettle;
import com.realexpayments.remote.sdk.domain.payment.Comment;
import com.realexpayments.remote.sdk.utils.GenerationUtils;
import com.realexpayments.remote.sdk.utils.XmlUtils;
import com.realexpayments.remote.sdk.utils.XmlUtils.MessageType;

import javax.xml.bind.annotation.*;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * Class representing a 3DSecure request to be sent to Realex.
 * </p>
 * <p>
 * Helper methods are provided (prefixed with 'add') for object creation.
 * </p>
 * <p>
 * Example:
 * </p>
 * <p><code><pre>
 * Card card = new Card()
 * 	.addExpiryDate("0119")
 * 	.addNumber("420000000000000000")
 * 	.addType(CardType.VISA)
 * 	.addCardHolderName("Joe Smith")
 * 	.addCvn("123")
 * 	.addCvnPresenceIndicator(PresenceIndicator.CVN_PRESENT);
 * <p/>
 * ThreeDSecureRequest request = new ThreeDSecureRequest()
 * 	.addAccount("yourAccount")
 * 	.addMerchantId("yourMerchantId")
 * 	.addType(ThreeDSecureType.VERIFY_ENROLLED)
 * .addAmount(100)
 * 	.addCurrency("EUR")
 * 	.addCard(card)
 *	.addAutoSettle(new AutoSettle().addFlag(AutoSettleFlag.TRUE));
 * </pre></code></p>
 *
 *
 * <p>
 * Example verify enrolled:
 * </p>
 * <p><code><pre>
 * ThreeDSecureRequest request = new ThreeDSecureRequest()
 * .addAccount("yourAccount")
 * .addMerchantId("yourMerchantId")
 * .addType(ThreeDSecureType.VERIFY_CARD_ENROLLED)
 * .addAmount(100)
 * .addCurrency("EUR")
 * .addPayerReference("payer ref from customer")
 * .addPaymentMethod("payment method ref from customer")
 * .addPaymentData(paymentData);
 *
 * </pre></code></p>
 *
 * @author markstanford
 */
@XmlRootElement(name = "request")
@XmlAccessorType(XmlAccessType.FIELD)
public class ThreeDSecureRequest implements Request<ThreeDSecureRequest, ThreeDSecureResponse> {

    /**
     * Enumeration for the ThreeDSecure type.
     */
    public enum ThreeDSecureType {
        VERIFY_ENROLLED("3ds-verifyenrolled"),
        VERIFY_SIG("3ds-verifysig"),
        VERIFY_CARD_ENROLLED("realvault-3ds-verifyenrolled");

        /**
         * The ThreeDSecure type String value
         */
        private String type;

        /**
         * ThreeDSecureType constructor
         *
         * @param type
         */
        ThreeDSecureType(String type) {
            this.type = type;
        }

        /**
         * Get the string value of the ThreeDSecure type
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
     * The ThreeDSecure type.
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
     * The pre-encoded PaRes that you obtain from the Issuer's ACS.
     */
    @XmlElement(name = "pares")
    private String pares;

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
     * {@link AutoSettle} object containing the auto settle flag.
     */
    @XmlElement(name = "autosettle")
    private AutoSettle autoSettle;

    /**
     * Constructor for ThreeDSecureRequest.
     */
    public ThreeDSecureRequest() {
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
     * Getter for ThreeDSecure type.
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for ThreeDSecure type
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
     * Getter for PaRes
     *
     * @return String
     */
    public String getPares() {
        return pares;
    }

    /**
     * Setter for PaRes.
     *
     * @param pares
     */
    public void setPares(String pares) {
        this.pares = pares;
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
     * Helper method for adding a merchant ID.
     *
     * @param merchantId
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addMerchantId(String merchantId) {
        this.merchantId = merchantId;
        return this;
    }

    /**
     * Helper method for adding an account.
     *
     * @param account
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addAccount(String account) {
        this.account = account;
        return this;
    }

    /**
     * Helper method for adding a time stamp.
     *
     * @param timeStamp
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
        return this;
    }

    /**
     * Helper method for adding an order ID.
     *
     * @param orderId
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addOrderId(String orderId) {
        this.orderId = orderId;
        return this;
    }

    /**
     * Helper method for adding an amount. If an {@link Amount} has not been set, then one is created.
     *
     * @param amount
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addAmount(long amount) {
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
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addCurrency(String currency) {
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
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addCard(Card card) {
        this.card = card;
        return this;
    }

    /**
     * Helper method for adding a {@link ThreeDSecureType}.
     *
     * @param type
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addType(ThreeDSecureType type) {
        this.type = type.getType();
        return this;
    }

    /**
     * Helper method for adding a ThreeDSecure type.
     *
     * @param type
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addType(String type) {
        this.type = type;
        return this;
    }

    /**
     * Helper method for adding a comment. NB Only 2 comments will be accepted by Realex.
     *
     * @param comment
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addComment(String comment) {
        //create new comments array list if null
        if (null == this.comments) {
            this.comments = new ArrayList<Comment>();
        }

        int size = comments.size();
        this.comments.add(new Comment().addComment(comment).addId(++size));
        return this;
    }

    /**
     * Helper method for adding hash.
     *
     * @param hash
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addHash(String hash) {
        this.hash = hash;
        return this;
    }

    /**
     * Helper method for adding PaRes.
     *
     * @param pares
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addPares(String pares) {
        this.pares = pares;
        return this;
    }

    /**
     * Helper method for adding a payer ref.
     *
     * @param payerRef
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addPayerReference(String payerRef) {
        this.payerRef = payerRef;
        return this;
    }

    /**
     * Helper method for adding a payment method.
     *
     * @param paymentMethod
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
        return this;
    }


    /**
     * Helper method for adding payment data.
     *
     * @param paymentData
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest addPaymentData(PaymentData paymentData) {
        this.paymentData = paymentData;
        return this;
    }

    /**
     * Helper method for adding an {@link AutoSettle}.
     *
     * @param autoSettle
     * @return PaymentRequest
     */
    public ThreeDSecureRequest addAutoSettle(AutoSettle autoSettle) {
        this.autoSettle = autoSettle;
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toXml() {
        return XmlUtils.toXml(this, MessageType.THREE_D_SECURE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ThreeDSecureRequest fromXml(Source xml) {
        return (ThreeDSecureRequest) XmlUtils.fromXml(xml, MessageType.THREE_D_SECURE);
    }

    /**
     * Helper method which unmarshals the passed XML String to a ThreeDSecureRequest object.
     *
     * @param xml
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest fromXml(String xml) {
        return this.fromXml(new StreamSource(new StringReader(xml)));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ThreeDSecureRequest generateDefaults(String secret) {

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
    public ThreeDSecureResponse responseFromXml(Source xml) {
        return new ThreeDSecureResponse().fromXml(xml);
    }

    /**
     * Creates the security hash from a number of fields and the shared secret.
     *
     * @param secret
     * @return ThreeDSecureRequest
     */
    public ThreeDSecureRequest hash(String secret) {

        //check for any null values and set them to empty string for hashing
        String timeStamp = null == this.timeStamp ? "" : this.timeStamp;
        String merchantId = null == this.merchantId ? "" : this.merchantId;
        String orderId = null == this.orderId ? "" : this.orderId;
        String payerRef = null == this.payerRef ? "" : this.payerRef;
        String amount = "";
        String currency = "";

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
        if (ThreeDSecureType.VERIFY_CARD_ENROLLED.getType().equals(this.type)) {
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
