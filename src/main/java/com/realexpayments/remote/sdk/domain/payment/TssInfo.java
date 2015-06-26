package com.realexpayments.remote.sdk.domain.payment;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * <p>
 * Domain object representing TSS (realscore) information to be passed to Realex. 
 * Realscore is a real time transaction screening and data checking system to assist a merchant 
 * with the identification of potentially high-risk transactions.
 * </p>
 * 
 * <p><code><pre>
 * TssInfo tssInfo = new TssInfo()
 * 	.addCustomerNumber("customer number")
 * 	.addProductId("product ID")
 * 	.addVariableReference("variable ref")
 * 	.addCustomerIpAddress("127.0.0.1")
 * 	.addAddress(
 * 		new Address()
 * 			.addType("billing")
 * 			.addCode("12|123")
 * 			.addCountry("IE"))
 * 	.addAddress(
 * 		new Address()
 * 			.addType("shipping")
 * 			.addCountry("GB"));
 * </pre></code></p>
 * 
 * @author markstanford
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TssInfo {

	/**
	 * The number you assign to the customer. This can allow checking of previous transactions by this customer.
	 */
	@XmlElement(name = "custnum")
	private String customerNumber;

	/**
	 * The product code you assign to the product.
	 */
	@XmlElement(name = "prodid")
	private String productId;

	/**
	 * Any reference you also would like to assign to the customer. This can allow checking, using realscore, of previous 
	 * transactions by this customer.
	 */
	@XmlElement(name = "varref")
	private String variableReference;

	/**
	 * The IP address of the customer.
	 */
	@XmlElement(name = "custipaddress")
	private String customerIpAddress;

	/**
	 * The address of the customer.
	 */
	@XmlElements(@XmlElement(name = "address", type = Address.class))
	private List<Address> addresses;

	/**
	 * Getter for customer number.
	 * 
	 * @return String
	 */
	public String getCustomerNumber() {
		return customerNumber;
	}

	/**
	 * Setter for customer number.
	 * 
	 * @param customerNumber
	 */
	public void setCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
	}

	/**
	 * Getter for product ID.
	 * 
	 * @return String
	 */
	public String getProductId() {
		return productId;
	}

	/**
	 * Setter for product ID.
	 * 
	 * @param productId
	 */
	public void setProductId(String productId) {
		this.productId = productId;
	}

	/**
	 * Getter for variable reference.
	 * 
	 * @return String
	 */
	public String getVariableReference() {
		return variableReference;
	}

	/**
	 * Setter for variable reference. 
	 * 
	 * @param variableReference
	 */
	public void setVariableReference(String variableReference) {
		this.variableReference = variableReference;
	}

	/**
	 * Getter for customer IP address.
	 * 
	 * @return String
	 */
	public String getCustomerIpAddress() {
		return customerIpAddress;
	}

	/**
	 * Setter for customer IP address.
	 * 
	 * @param customerIpAddress
	 */
	public void setCustomerIpAddress(String customerIpAddress) {
		this.customerIpAddress = customerIpAddress;
	}

	/**
	 * Getter for addresses.
	 * 
	 * @return String
	 */
	public List<Address> getAddresses() {
		return addresses;
	}

	/**
	 * Setter for address list.
	 * 
	 * @param addresses
	 */
	public void setAddresses(List<Address> addresses) {
		this.addresses = addresses;
	}

	/**
	 * Helper method for adding a customer number.
	 * 
	 * @param customerNumber
	 * @return TssInfo
	 */
	public TssInfo addCustomerNumber(String customerNumber) {
		this.customerNumber = customerNumber;
		return this;
	}

	/**
	 * Helper method for adding a product ID.
	 * 
	 * @param productId
	 * @return TssInfo
	 */
	public TssInfo addProductId(String productId) {
		this.productId = productId;
		return this;
	}

	/**
	 * Helper method for adding a variable reference.
	 * 
	 * @param variableReference
	 * @return TssInfo
	 */
	public TssInfo addVariableReference(String variableReference) {
		this.variableReference = variableReference;
		return this;
	}

	/**
	 * Helper method for adding a customer IP address.
	 * 
	 * @param customerIpAddress
	 * @return TssInfo
	 */
	public TssInfo addCustomerIpAddress(String customerIpAddress) {
		this.customerIpAddress = customerIpAddress;
		return this;
	}

	/**
	 * Helper method for adding an address.
	 * 
	 * @param address
	 * @return TssInfo
	 */
	public TssInfo addAddress(Address address) {
		if (null == this.addresses) {
			this.addresses = new ArrayList<Address>();
		}
		this.addresses.add(address);
		return this;
	}

}
