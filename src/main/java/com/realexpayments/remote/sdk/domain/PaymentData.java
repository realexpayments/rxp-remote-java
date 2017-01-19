package com.realexpayments.remote.sdk.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Domain object representing PaymentData information to be passed to Realex Card Storage
 * for Receipt-in transactions.
 * Payment data contains the CVN number for the stored card
 * </p>
 * <p/>
 * <p><code><pre>
 * PaymentData paymentData = new PaymentData()
 * 	.addCvnNumber("123") ;
 * </pre></code></p>
 *
 * @author vicpada
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentData {


    /**
     * A container for the CVN number
     */
    @XmlElement(name = "cvn")
    private CvnNumber cvnNumber;

    /**
     * PaymentData constructor
     */
    public PaymentData() {
    }

    /**
     * Getter for verification number.
     *
     * @return CvnNumber
     */
    public CvnNumber getCvnNumber() {
        return cvnNumber;
    }

    /**
     * Setter for verification number.
     *
     * @param cvnNumber
     */
    public void setCvnNumber(CvnNumber cvnNumber) {
        this.cvnNumber = cvnNumber;
    }

    /**
     * Helper method to add a verification number.
     *
     * @param number
     * @return PaymentData
     */
    public PaymentData addCvnNumber(String number) {
        if (this.cvnNumber == null) {
            this.cvnNumber = new CvnNumber();
        }

        this.cvnNumber.addNumber(number);
        return this;
    }

    /**
     * Helper method to add a verification number.
     *
     * @param cvnNumber
     * @return PaymentData
     */
    public PaymentData addCvnNumber(CvnNumber cvnNumber) {
        this.cvnNumber = cvnNumber;
        return this;
    }
}
