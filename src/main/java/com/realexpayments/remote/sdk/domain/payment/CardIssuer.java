package com.realexpayments.remote.sdk.domain.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Class representing details of the card holder's bank (if available). 
 * </p>
 * 
 * @author markstanford
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class CardIssuer {

	/**
	 * The Bank Name (e.g. First Data Bank).
	 */
	@XmlElement(name = "bank")
	private String bank;

	/**
	 * The Bank Country in English (e.g. UNITED STATES).
	 */
	@XmlElement(name = "country")
	private String country;

	/**
	 * The country code of the issuing bank (e.g. US).
	 */
	@XmlElement(name = "countrycode")
	private String countryCode;

	/**
	 * The region the card was issued (e.g. US) Can be MEA (Middle East/Asia), LAT (Latin America), US (United States), 
	 * EUR (Europe), CAN (Canada), A/P (Asia/Pacific).
	 */
	@XmlElement(name = "region")
	private String region;

	/**
	 * Getter for the bank.
	 * 
	 * @return String
	 */
	public String getBank() {
		return bank;
	}

	/**
	 * Setter for the bank.
	 * 
	 * @param bank
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}

	/**
	 * Getter for the country.
	 * 
	 * @return String
	 */
	public String getCountry() {
		return country;
	}

	/**
	 * Setter for the country.
	 * 
	 * @param country
	 */
	public void setCountry(String country) {
		this.country = country;
	}

	/**
	 * Getter for the country code.
	 * 
	 * @return String
	 */
	public String getCountryCode() {
		return countryCode;
	}

	/**
	 * Setter for the country code.
	 * 
	 * @param countryCode
	 */
	public void setCountryCode(String countryCode) {
		this.countryCode = countryCode;
	}

	/**
	 * Getter for the region.
	 * 
	 * @return String
	 */
	public String getRegion() {
		return region;
	}

	/**
	 * Setter for the region.
	 * 
	 * @param region
	 */
	public void setRegion(String region) {
		this.region = region;
	}

}
