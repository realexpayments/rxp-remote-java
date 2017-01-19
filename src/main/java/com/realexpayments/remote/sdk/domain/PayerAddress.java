package com.realexpayments.remote.sdk.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Domain object representing Payer information to be passed to Realex.
 * </p>
 *
 * <p><code><pre>
 *
 * PayerAddress address = new PayerAddress()
 * .addLine1("Apt 167 Block 10")
 * .addLine2("The Hills")
 * .addLine3("67-69 High St")
 * .addCity("Hytown")
 * .addCounty("Dunham")
 * .addPostCode("3")
 * .addCountryCode("IE")
 * .addCountryName("Ireland");
 *
 * </pre></code></p>
 *
 * @author vicpada
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PayerAddress {

    /**
     * Address line 1
     */
    @XmlElement(name = "line1")
    private String line1;

    /**
     * Address line 2
     */
    @XmlElement(name = "line2")
    private String line2;

    /**
     * Address line 3
     */
    @XmlElement(name = "line3")
    private String line3;

    /**
     * Address city
     */
    @XmlElement(name = "city")
    private String city;

    /**
     * Address county
     */
    @XmlElement(name = "county")
    private String county;

    /**
     * Address postcode
     */
    @XmlElement(name = "postcode")
    private String postcode;

    /**
     * Address country
     */
    @XmlElement(name = "country")
    private Country country;

    /**
     * Constructor
     */
    public PayerAddress() {
    }

    /**
     * Getter for line 1
     *
     * @return String
     */
    public String getLine1() {
        return line1;
    }

    /**
     * Setter for line 1
     *
     * @param line1
     */
    public void setLine1(String line1) {
        this.line1 = line1;
    }

    /**
     * Getter for line 2
     *
     * @return String
     */
    public String getLine2() {
        return line2;
    }

    /**
     * Setter for line 2
     *
     * @param line2
     */
    public void setLine2(String line2) {
        this.line2 = line2;
    }

    /**
     * Getter for line 3
     *
     * @return String
     */
    public String getLine3() {
        return line3;
    }

    /**
     * Setter for line 3
     *
     * @param line3
     */
    public void setLine3(String line3) {
        this.line3 = line3;
    }

    /**
     * Getter for city
     *
     * @return String
     */
    public String getCity() {
        return city;
    }

    /**
     * Setter for city
     *
     * @param city
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * Getter for county
     *
     * @return String
     */
    public String getCounty() {
        return county;
    }

    /**
     * Setter for county
     *
     * @param county
     */
    public void setCounty(String county) {
        this.county = county;
    }

    /**
     * Getter for postcode
     *
     * @return String
     */
    public String getPostcode() {
        return postcode;
    }

    /**
     * Setter for postcode
     *
     * @param postcode
     */
    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    /**
     * Getter for country
     *
     * @return Country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Setter for country
     *
     * @param country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    /**
     * Helper method for adding address line 1
     *
     * @param line1
     * @return PayerAddress
     */
    public PayerAddress addLine1(String line1) {
        this.line1 = line1;
        return this;
    }

    /**
     * Helper method for adding address line 2
     *
     * @param line2
     * @return PayerAddress
     */
    public PayerAddress addLine2(String line2) {
        this.line2 = line2;
        return this;
    }


    /**
     * Helper method for adding address line 3
     *
     * @param line3
     * @return PayerAddress
     */
    public PayerAddress addLine3(String line3) {
        this.line3 = line3;
        return this;
    }


    /**
     * Helper method for adding City
     *
     * @param city
     * @return PayerAddress
     */
    public PayerAddress addCity(String city) {
        this.city = city;
        return this;
    }


    /**
     * Helper method for adding address county
     *
     * @param county
     * @return PayerAddress
     */
    public PayerAddress addCounty(String county) {
        this.county = county;
        return this;
    }


    /**
     * Helper method for adding address postcode
     *
     * @param postcode
     * @return PayerAddress
     */
    public PayerAddress addPostcode(String postcode) {
        this.postcode = postcode;
        return this;
    }

    /**
     * Helper method for adding address country Code. The list of country
     * codes is available in the realauth developers guide.
     *
     * @param countryCode
     * @return PayerAddress
     */
    public PayerAddress addCountryCode(String countryCode) {
        if (null == this.country)
        {
            this.country = new Country();
        }

        country.setCode(countryCode);
        return this;
    }

    /**
     * Helper method for adding address country name.
     *
     * @param countryName
     * @return PayerAddress
     */
    public PayerAddress addCountryName(String countryName) {
        if (null == this.country)
        {
            this.country = new Country();
        }

        country.setName(countryName);
        return this;
    }

}
