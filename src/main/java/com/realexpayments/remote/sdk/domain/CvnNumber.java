package com.realexpayments.remote.sdk.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Domain object representing PaymentData CVN number information to be passed to
 * Realex Realvault for Receipt-in transactions.
 * Contains the CVN number for the stored card
 * </p>
 * <p/>
 * <p><code><pre>
 * CvnNumber cvnNumber = new CvnNumber()
 * 	.addNumber("123") ;
 * </pre></code></p>
 *
 * @author vicpada
 */

@XmlAccessorType(XmlAccessType.FIELD)
public class CvnNumber {

    /**
     * A three-digit number on the reverse of the card. It is called the CVC for VISA and the CVV2 for MasterCard.
     * For an AMEX card, it is a four digit number.
     */
    @XmlElement(name = "number")
    private String number;


    /**
     * CvnNumber constructor
     */
    public CvnNumber() {
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
     * Helper method to add a verification number.
     *
     * @param number
     * @return CvnNumber
     */
    public CvnNumber addNumber(String number) {
        this.number = number;
        return this;
    }
}
