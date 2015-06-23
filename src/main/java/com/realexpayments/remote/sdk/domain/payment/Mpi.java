package com.realexpayments.remote.sdk.domain.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Domain object representing MPI (realmpi) information to be passed to Realex. 
 * Realmpi is Realex's product to implement card scheme-certified payer authentication via the bank 
 * and the 3D Secure system (Verified by Visa for Visa, Secure Code for Mastercard and SafeKey for Amex).
 * </p>
 * 
 * <p><code><pre>
 * Mpi mpi = new Mpi()
 * 	.addCavv("cavv")
 * 	.addXid("xid")
 * 	.addEci("eci");
 * </pre></code></p>
 * 
 * @author thomasduffy
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Mpi {

	/**
	 * The CAVV(Visa)/UCAF(Mastercard) if present.
	 */
	@XmlElement(name = "cavv")
	private String cavv;

	/**
	 * The XID.
	 */
	@XmlElement(name = "xid")
	private String xid;

	/**
	 * The e-commerce indicator.
	 * <th>
	 * 		<td>Visa</td><td>MC</td><td>ECI</td>
	 * </th>
	 * <tr>
	 * 		<td>5</td><td>2</td><td>Fully secure, card holder enrolled</td>
	 * </tr>
	 * <tr>
	 * 		<td>6</td><td>1</td><td>Merchant secure, card holder not enrolled or attempt ACS server was used</td>
	 * </tr>
	 * <tr>
	 * 		<td>7</td><td>0</td><td>Transaction not secure</td>
	 * </tr>
	 * <li>
	 */
	@XmlElement(name = "eci")
	private String eci;

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
	 * Helper method for adding the CAVV.
	 * 
	 * @param cavv
	 * @return Mpi
	 */
	public Mpi addCavv(String cavv) {
		this.cavv = cavv;
		return this;
	}

	/**
	 * Helper method for adding the xid.
	 * 
	 * @param xid
	 * @return Mpi
	 */
	public Mpi addXid(String xid) {
		this.xid = xid;
		return this;
	}

	/**
	 * Helper method for adding the ECI.
	 * 
	 * @param eci
	 * @return Mpi
	 */
	public Mpi addEci(String eci) {
		this.eci = eci;
		return this;
	}

}
