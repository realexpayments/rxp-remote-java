package com.realexpayments.remote.sdk.domain.payment;

import javax.swing.text.StringContent;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlValue;

/**
 * Created by alessandro on 17/06/2016.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class FraudFilterRule {

    /**
     * The FraudFilterRule id value.
     */
    @XmlAttribute(name = "id")
    private String id;

    /**
     * The FraudFilterRule name value.
     */
    @XmlAttribute(name = "name")
    private String name;

    /**
     * The FraudFilterRule value value.
     */
    @XmlValue
    private String value;

    /**
     * Getter for id
     *
     * @return string
     */
    public String getId() {
        return this.id;
    }

    /**
     * Setter for id
     *
     * @param id
     */
    public void setId( String id ) {
        this.id = id;
    }

    /**
     * Getter for value
     *
     * @return string
     */
    public String getValue() {
        return this.value;
    }

    /**
     * Setter for value
     *
     * @param value
     */
    public void setValue(String value ) {
        this.value = value;
    }

    /**
     * Setter for name
     *
     * @param name
     */
    public void setName(String name ) {
        this.name = name;
    }

    /**
     * Getter for name
     *
     * @return string
     */
    public String getName() {
        return this.name;
    }

}
