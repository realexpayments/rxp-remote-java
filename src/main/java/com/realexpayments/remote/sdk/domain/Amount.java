package com.realexpayments.remote.sdk.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * <p>
 * Class representing the Amount in a Realex request.
 * </p>
 * <p>
 * Helper methods are provided (prefixed with 'add') for object creation.
 * </p>
 * <p>
 * Example creation:
 * </p>
 * <p><code><pre>
 * Amount amount = new Amount().addAmount(100l).addCurrency(CurrencyType.EUR); 
 * </pre></code></p>
 * 
 * @author markstanford
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Amount {

	/**
	 * The amount should be in the smallest unit of the required currency (For example: 2000=20 euro, dollar or pounds).
	 */
	@XmlValue
	private Long amount;

	/**
	 * The type of curency, e.g. GBP (Sterling) or EUR (Euro)
	 */
	@XmlAttribute(name = "currency")
	private String currency;

	/**
	 * Amount constructor
	 */
	public Amount() {
	}

	/**
	 * Getter for amount.
	 * 
	 * @return Long
	 */
	public Long getAmount() {
		return amount;
	}

	/**
	 * Setter for amount.
	 * 
	 * @param amount
	 */
	public void setAmount(Long amount) {
		this.amount = amount;
	}

	/**
	 * Getter for currency
	 * 
	 * @return CurrencyType
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Setter for currency
	 * 
	 * @param currency
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * Helper method for adding an amount.
	 * 
	 * @param amount
	 * @return Amount
	 */
	public Amount addAmount(Long amount) {
		this.amount = amount;
		return this;
	}

	/**
	 * Helper method for adding a currency.
	 * 
	 * @param currency
	 * @return Amount
	 */
	public Amount addCurrency(String currency) {
		this.currency = currency;
		return this;
	}
}
