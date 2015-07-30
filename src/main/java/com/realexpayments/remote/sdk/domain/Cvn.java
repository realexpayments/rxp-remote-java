package com.realexpayments.remote.sdk.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Class representing the card verification details.
 * </p>
 * <p>
 * Helper methods are provided (prefixed with 'add') for object creation.
 * </p>
 * <p>
 * Example creation:
 * </p>
 * <p><code><pre>
 * Cvn cvn = new Cvn().addNumber(123).addPresenceIndicator(PresenceIndicator.CVN_PRESENT); 
 * </pre></code></p>
 * 
 * @author markstanford
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Cvn {

	/**
	 * <p>
	 * Enumeration of the possible presence indicator values. 4 values are permitted:
	 * <ol>
	 * <li>cvn present</li>
	 * <li>cvn illegible</li>
	 * <li>cvn not on card</li>
	 * <li>cvn not requested</li>
	 * </ol>
	 * </p>
	 */
	public enum PresenceIndicator {
		CVN_PRESENT("1"),
		CVN_ILLEGIBLE("2"),
		CVN_NOT_ON_CARD("3"),
		CVN_NOT_REQUESTED("4");

		/**
		 * The indicator.
		 */
		private String indicator;

		/**
		 * Constructor for enum.
		 */
		PresenceIndicator(String indicator) {
			this.indicator = indicator;
		}

		/**
		 * Getter for indicator.
		 * 
		 * @return String
		 */
		public String getIndicator() {
			return this.indicator;
		}
	}

	/**
	 * A three-digit number on the reverse of the card. It is called the CVC for VISA and the CVV2 for MasterCard. 
	 * For an AMEX card, it is a four digit number.
	 */
	@XmlElement(name = "number")
	private String number;

	/**
	 * <p>
	 * Presence indicator. 4 values are permitted:
	 * <ol>
	 * <li>cvn present</li>
	 * <li>cvn illegible</li>
	 * <li>cvn not on card</li>
	 * <li>cvn not requested</li>
	 * </ol>
	 * </p>
	 */
	@XmlElement(name = "presind")
	private String presenceIndicator;

	/**
	 * Cvn constructor
	 */
	public Cvn() {
	}

	/**
	 * Getter for verification number.
	 * 
	 * @return String
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Setter for verification number.
	 * 
	 * @param number
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * Getter for the presence indicator.
	 * 
	 * @return PresenceIndicator
	 */
	public String getPresenceIndicator() {
		return presenceIndicator;
	}

	/**
	 * Setter for the presence indicator.
	 * 
	 * @param presenceIndicator
	 */
	public void setPresenceIndicator(String presenceIndicator) {
		this.presenceIndicator = presenceIndicator;
	}

	/**
	 * Helper method to add a verification number.
	 * 
	 * @param number
	 * @return Cvn
	 */
	public Cvn addNumber(String number) {
		this.number = number;
		return this;
	}

	/**
	 * Helper method to add a presence indicator.
	 * 
	 * @param presenceIndicator
	 * @return Cvn
	 */
	public Cvn addPresenceIndicator(String presenceIndicator) {
		this.presenceIndicator = presenceIndicator;
		return this;
	}

	/**
	 * Helper method to add a {@link PresenceIndicator}.
	 * 
	 * @param presenceIndicator
	 * @return Cvn
	 */
	public Cvn addPresenceIndicator(PresenceIndicator presenceIndicator) {
		this.presenceIndicator = presenceIndicator.getIndicator();
		return this;
	}
}
