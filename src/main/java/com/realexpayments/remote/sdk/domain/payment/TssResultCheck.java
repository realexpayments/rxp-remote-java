package com.realexpayments.remote.sdk.domain.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Domain object representing the results of an individual realscore check.
 * 
 * @author markstanford
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TssResultCheck {

	/**
	 * The ID of the realscore check
	 */
	@XmlAttribute(name = "id")
	private String id;

	/**
	 * The value of the realscore check
	 */
	@XmlValue
	private String value;

	/**
	 * Getter for check ID.
	 * 
	 * @return String 
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter for check ID.
	 * 
	 * @param id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Getter for check value.
	 * 
	 * @return String 
	 */
	public String getValue() {
		return value;
	}

	/**
	 * Setter for check value.
	 * 
	 * @param value
	 */
	public void setValue(String value) {
		this.value = value;
	}

}
