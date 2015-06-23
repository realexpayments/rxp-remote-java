package com.realexpayments.remote.sdk.domain.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * The Address of the customer.
 * </p>
 * 
 * <p>
 * Helper methods are provided (prefixed with 'add') for object creation.
 * </p>
 * <p><code>
 * Address address = new Address().addCode("D2").addCountry("IE").addType(AddressType.BILLING);
 * </code><p>
 * 
 * @author markstanford
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Address {

	/**
	 * The address type enum. Can be shipping or billing.
	 */
	public enum AddressType {
		NONE(""),
		SHIPPING("shipping"),
		BILLING("billing");

		/**
		 * The type value
		 */
		private String addressType;

		/**
		 * Constructor for enum.
		 * 
		 * @param type
		 */
		AddressType(String addressType) {
			this.addressType = addressType;
		}

		/**
		 * Returns String value for the type
		 * 
		 * @return String 
		 */
		public String getAddressType() {
			return this.addressType;
		}
	}

	/**
	 * The address type. Can be shipping or billing.
	 */
	@XmlAttribute(name = "type")
	private String type;

	/**
	 * The ZIP|Postal code of the address. This can be checked (in conjunction with the country) 
	 * against a table of high-risk area codes. This field is used address verification with certain acquirers.
	 */
	@XmlElement(name = "code")
	private String code;

	/**
	 * The country of the address. This can be checked against a table of high-risk countries.
	 */
	@XmlElement(name = "country")
	private String country;

	/**
	 * Address constructor.
	 */
	public Address() {
	}

	/**
	 * Getter for the code.
	 * 
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Setter for the code. 
	 * 
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
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
	 * Getter for the type.
	 * 
	 * @return String
	 */
	public String getType() {
		return type;
	}

	/**
	 * Setter for the type. 
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Helper method for setting the code.
	 * 
	 * @param code
	 * @return Address
	 */
	public Address addCode(String code) {
		this.code = code;
		return this;
	}

	/**
	 * Helper method for setting the country.
	 * 
	 * @param country
	 * @return Address
	 */
	public Address addCountry(String country) {
		this.country = country;
		return this;
	}

	/**
	 * Helper method for setting the type.
	 * 
	 * @param type
	 * @return Address
	 */
	public Address addType(String type) {
		this.type = type;
		return this;
	}

	/**
	 * Helper method for setting the type.
	 * 
	 * @param type
	 * @return Address
	 */
	public Address addType(AddressType type) {
		this.type = type.getAddressType();
		return this;
	}
}
