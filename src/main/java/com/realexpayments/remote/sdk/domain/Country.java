package com.realexpayments.remote.sdk.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * <p>
 * Domain object representing Country information to be passed to Realex.
 * </p>
 *
 * <p><code><pre>
 *
 * Country country = new Country();
 * country.setCode("IE");
 * country.setName("Ireland");
 *
 * </pre></code></p>
 *
 * @author vicpada
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Country {

    /**
     * The country code. The list of country codes is available
     * in the realauth developers guide.
     */
    @XmlAttribute(name = "code")
    private String code;

    /**
     * The country name.
     */
    @XmlValue
    private String name;

    /**
     * Constructor
     */
    public Country() {
    }

    /**
     * Getter for code
     *
     * @return String
     */
    public String getCode() {
        return code;
    }

    /**
     * Setter for code
     *
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * Getter for name
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Setter for name
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }
}
