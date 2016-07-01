package com.realexpayments.remote.sdk.domain;

import javax.xml.bind.annotation.*;

/**
 * <p>
 * Domain object representing Payer phone numbers information to be passed to Realex.
 * </p>
 *
 * <p><code><pre>
 * PhoneNumbers phoneNumbers = new PhoneNumbers();
 * phoneNumbers.setHomePhoneNumber("+35317285355");
 * phoneNumbers.setWorkPhoneNumber("+35317433923");
 * phoneNumbers.setFaxPhoneNumber("+35317893248");
 * phoneNumbers.setMobilePhoneNumber("+353873748392")
 *
 * </pre></code></p>
 *
 * @author vicpada
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PhoneNumbers {

    /**
     * Home phone number
     */
    @XmlElement(name = "home")
    private String homePhoneNumber;

    /**
     * Work phone number
     */
    @XmlElement(name = "work")
    private String workPhoneNumber;

    /**
     * Fax phone number
     */
    @XmlElement(name = "fax")
    private String faxPhoneNumber;

    /**
     * Mobile phone number
     */
    @XmlElement(name = "mobile")
    private String mobilePhoneNumber;

    /**
     * Constructor
     */
    public PhoneNumbers() {
    }

    /**
     * Getter for home phone number
     *
     * @return String
     */
    public String getHomePhoneNumber() {
        return homePhoneNumber;
    }

    /**
     * Setter for home phone number
     *
     * @param homePhoneNumber
     */
    public void setHomePhoneNumber(String homePhoneNumber) {
        this.homePhoneNumber = homePhoneNumber;
    }

    /**
     * Getter for work phone number
     *
     * @return String
     */
    public String getWorkPhoneNumber() {
        return workPhoneNumber;
    }

    /**
     * Setter for work phone number
     *
     * @param workPhoneNumber
     */
    public void setWorkPhoneNumber(String workPhoneNumber) {
        this.workPhoneNumber = workPhoneNumber;
    }

    /**
     * Getter for fax phone number
     *
     * @return String
     */
    public String getFaxPhoneNumber() {
        return faxPhoneNumber;
    }

    /**
     * Setter for fax phone number
     *
     * @param faxPhoneNumber
     */
    public void setFaxPhoneNumber(String faxPhoneNumber) {
        this.faxPhoneNumber = faxPhoneNumber;
    }

    /**
     * Getter for mobile phone number
     *
     * @return String
     */
    public String getMobilePhoneNumber() {
        return mobilePhoneNumber;
    }

    /**
     * Setter for mobile phone number
     *
     * @param mobilePhoneNumber
     */
    public void setMobilePhoneNumber(String mobilePhoneNumber) {
        this.mobilePhoneNumber = mobilePhoneNumber;
    }
}
