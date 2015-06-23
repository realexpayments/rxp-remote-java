package com.realexpayments.remote.sdk.domain.threeDSecure;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Domain object representing 3D Secure (realmpi) information passed back from Realex. 
 * Realmpi is a real time card holder verification system to assist a merchant with the 
 * identification of potentially fraudulent transactions.
 * </p>
 * 
 * @author thomasduffy
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ThreeDSecure {

	/**
	 * The outcome of the authentication, required for the authorisation request.
	 */
	@XmlElement(name = "status")
	private String status;

	/**
	 * The e-commerce indicator, required for the authorisation request.
	 */
	@XmlElement(name = "eci")
	private String eci;

	/**
	 * The XID field, required for the authorisation request.
	 */
	@XmlElement(name = "xid")
	private String xid;

	/**
	 * The CAVV or UCAF, required for the authorisation request.
	 */
	@XmlElement(name = "cavv")
	private String cavv;

	/**
	 * The address of the customer.
	 */
	@XmlElement(name = "algorithm")
	private String algorithm;

	/**
	 * Getter for the status.
	 * 
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Setter for the status.
	 * 
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * Getter for the ECI.
	 * 
	 * @return the eci
	 */
	public String getEci() {
		return eci;
	}

	/**
	 * Setter for the ECI.
	 * 
	 * @param eci the eci to set
	 */
	public void setEci(String eci) {
		this.eci = eci;
	}

	/**
	 * Getter for the XID.
	 * 
	 * @return the xid
	 */
	public String getXid() {
		return xid;
	}

	/**
	 * Setter for the XID.
	 * 
	 * @param xid the xid to set
	 */
	public void setXid(String xid) {
		this.xid = xid;
	}

	/**
	 * Getter for the CAVV.
	 * 
	 * @return the cavv
	 */
	public String getCavv() {
		return cavv;
	}

	/**
	 * Setter for the CAVV.
	 * 
	 * @param cavv the cavv to set
	 */
	public void setCavv(String cavv) {
		this.cavv = cavv;
	}

	/**
	 * Getter for the algorithm.
	 * 
	 * @return the algorithm
	 */
	public String getAlgorithm() {
		return algorithm;
	}

	/**
	 * Setter for the algorithm.
	 * 
	 * @param algorithm the algorithm to set
	 */
	public void setAlgorithm(String algorithm) {
		this.algorithm = algorithm;
	}

	/**
	 * Helper method for adding the status.
	 * 
	 * @param status
	 * @return ThreeDSecure
	 */
	public ThreeDSecure addStatus(String status) {
		this.status = status;
		return this;
	}

	/**
	 * Helper method for adding the ECI.
	 * 
	 * @param eci
	 * @return ThreeDSecure
	 */
	public ThreeDSecure addEci(String eci) {
		this.eci = eci;
		return this;
	}

	/**
	 * Helper method for adding the XID.
	 * 
	 * @param xid
	 * @return ThreeDSecure
	 */
	public ThreeDSecure addXid(String xid) {
		this.xid = xid;
		return this;
	}

	/**
	 * Helper method for adding the CAVV/UCAF.
	 * 
	 * @param cavv
	 * @return ThreeDSecure
	 */
	public ThreeDSecure addCavv(String cavv) {
		this.cavv = cavv;
		return this;
	}

	/**
	 * Helper method to add the algorithm.
	 * 
	 * @param algorithm
	 * @return ThreeDSecure
	 */
	public ThreeDSecure addAlgorithm(String algorithm) {
		this.algorithm = algorithm;
		return this;
	}

}
