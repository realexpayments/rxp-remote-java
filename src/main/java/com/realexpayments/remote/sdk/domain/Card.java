package com.realexpayments.remote.sdk.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Represents the card which is required in AUTH requests.
 * </p>
 * <p>
 * Helper methods are provided (prefixed with 'add') for object creation.
 * </p>
 * <p>
 * Example creation:
 * </p>
 * <p><code><pre>
 * Card card = new Card().addExpiryDate("0119")
 * 		.addNumber("420000000000000000")
 *		.addType(CardType.VISA)
 *		.addCardHolderName("Joe Smith")
 *		.addCvn("123")
 *		.addCvnPresenceIndicator(PresenceIndicator.CVN_PRESENT); 
 * </pre></code></p>
 * 
 * @author markstanford
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Card {

	/* Masks for sanitising card numbers methods */
	private static final String SHORT_MASK = "******";

	/**
	 * Enumeration representing card types. 
	 */
	public enum CardType {

		// @formatter:off
		VISA("VISA"),
		MASTERCARD("MC"),
		AMEX("AMEX"),
		CB("CB"),
		DINERS("DINERS"),
		JCB("JCB");
		// @formatter:on

		/**
		 * The card type.
		 */
		private String type;

		/**
		 * Constructor for enum.
		 */
		CardType(String type) {
			this.type = type;
		}

		/**
		 * Getter for card type.
		 * 
		 * @return String
		 */
		public String getType() {
			return this.type;
		}
	}

	/**
	 * The reference for this card (RealVault)
	 *
	 * This must be unique within the Payer record if you are adding multiple
	 * cards, but it does not need to be unique in relation to other Payers.
	 */
	@XmlElement(name = "ref")
	private String reference;


	/**
	 * The payer ref for this customer  (RealVault)
	 */
	@XmlElement(name = "payerref")
	private String payerReference;

	/**
	 * The card number used for the transaction.
	 */
	@XmlElement(name = "number")
	private String number;

	/**
	 * The card expiry date, in the format MMYY, which must be a date in the future.
	 */
	@XmlElement(name = "expdate")
	private String expiryDate;

	/**
	 * The card holder's name
	 */
	@XmlElement(name = "chname")
	private String cardHolderName;

	/**
	 * The card type used in the transaction.
	 */
	@XmlElement(name = "type")
	private String type;

	/**
	 * The card issue number.
	 */
	@XmlElement(name = "issueno")
	private Integer issueNumber;

	/**
	 * {@link Cvn}, the card verification number.
	 */
	@XmlElement(name = "cvn")
	private Cvn cvn;

	/**
	 * The {@link Card} constructor.
	 */
	public Card() {
	}

	/**
	 * Getter for the card reference.
	 *
	 * @return String
	 */
	public String getReference() {
		return reference;
	}

	/**
	 * Setter for the card reference.
	 *
	 * @param reference
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * Getter for the payer reference
	 *
	 * @return String
	 */
	public String getPayerReference() {
		return payerReference;
	}

	/**
	 * Setter for the payer reference
	 *
	 * @param payerReference
	 */
	public void setPayerReference(String payerReference) {
		this.payerReference = payerReference;
	}


	/**
	 * Getter for the card number.
	 * 
	 * @return String
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Setter for the card number.
	 * 
	 * @param number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Getter for the expiry date.
	 * 
	 * @return String
	 */
	public String getExpiryDate() {
		return expiryDate;
	}

	/**
	 * Setter for the expiry date.
	 * 
	 * @param expiryDate
	 */
	public void setExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
	}

	/**
	 * Getter for the card holder name.
	 * 
	 * @return String 
	 */
	public String getCardHolderName() {
		return cardHolderName;
	}

	/**
	 * Setter for the card holder name.
	 * 
	 * @param cardHolderName
	 */
	public void setCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
	}

	/**
	 * Getter for the card type.
	 * 
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter for the card type.
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter for the issue number.
	 * 
	 * @return Integer
	 */
	public Integer getIssueNumber() {
		return issueNumber;
	}

	/**
	 * Setter for the issue number.
	 * 
	 * @param issueNumber
	 */
	public void setIssueNumber(Integer issueNumber) {
		this.issueNumber = issueNumber;
	}

	/**
	 * Getter for the {@link Cvn}.
	 * 
	 * @return Cvn
	 */
	public Cvn getCvn() {
		return cvn;
	}

	/**
	 * Setter for the {@link Cvn}.
	 * 
	 * @param cvn
	 */
	public void setCvn(Cvn cvn) {
		this.cvn = cvn;
	}

	/**
	 * Helper method to add the card reference.
	 *
	 * @param reference
	 * @return Card
	 */
	public Card addReference(String reference) {
		this.reference = reference;
		return this;
	}


	/**
	 * Helper method to add a payer reference.
	 *
	 * @param payerReference
	 * @return Card
	 */
	public Card addPayerReference(String payerReference) {
		this.payerReference = payerReference;
		return this;
	}

	/**
	 * Helper method to add a card number.
	 * 
	 * @param number
	 * @return Card
	 */
	public Card addNumber(String number) {
		this.number = number;
		return this;
	}

	/**
	 * Helper method to add CVN. If the {@link Cvn} field is null then one is created. 
	 * 
	 * @param cvn
	 * @return Card
	 */
	public Card addCvn(String cvn) {
		if (null == this.cvn) {
			this.cvn = new Cvn().addNumber(cvn);
		} else {
			this.cvn.addNumber(cvn);
		}
		return this;
	}

	/**
	 * Helper method to add CVN presence indicator. If the {@link Cvn} is null then one is created.
	 * 
	 * @param presenceIndicator
	 * @return Card
	 */
	public Card addCvnPresenceIndicator(Cvn.PresenceIndicator presenceIndicator) {
		if (null == this.cvn) {
			this.cvn = new Cvn().addPresenceIndicator(presenceIndicator);
		} else {
			this.cvn.addPresenceIndicator(presenceIndicator);
		}
		return this;
	}

	/**
	 * Helper method to add CVN presence indicator. If the {@link Cvn} is null then one is created.
	 * 
	 * @param presenceIndicator
	 * @return Card
	 */
	public Card addCvnPresenceIndicator(String presenceIndicator) {
		if (null == this.cvn) {
			this.cvn = new Cvn().addPresenceIndicator(presenceIndicator);
		} else {
			this.cvn.addPresenceIndicator(presenceIndicator);
		}
		return this;
	}

	/**
	 * Helper method to add a card expiry date. The format is MMYY e.g. 1219 for December 2019.
	 * 
	 * @param expiryDate
	 * @return Card
	 */
	public Card addExpiryDate(String expiryDate) {
		this.expiryDate = expiryDate;
		return this;
	}

	/**
	 * Helper method to add an issue number.
	 * 
	 * @param issueNumber
	 * @return Card
	 */
	public Card addIssueNumber(Integer issueNumber) {
		this.issueNumber = issueNumber;
		return this;
	}

	/**
	 * Helper method to add a card holder name.
	 * 
	 * @param cardHolderName
	 * @return Card
	 */
	public Card addCardHolderName(String cardHolderName) {
		this.cardHolderName = cardHolderName;
		return this;
	}

	/**
	 * Helper method to add a card type.
	 * 
	 * @param type
	 * @return Card
	 */
	public Card addType(String type) {
		this.type = type;
		return this;
	}

	/**
	 * Helper method to add a {@link CardType}.
	 * 
	 * @param type
	 * @return Card
	 */
	public Card addType(CardType type) {
		this.type = type.getType();
		return this;
	}

	/**
	 * Get a sanitised version of the card number displaying only the first six and last four digits.
	 * 
	 * @return String
	 */
	public String displayFirstSixLastFour() {
		StringBuffer result = new StringBuffer(this.number.substring(0, 6));
		result.append(SHORT_MASK);
		result.append(this.number.substring(this.number.length() - 4));
		return result.toString();
	}

}
