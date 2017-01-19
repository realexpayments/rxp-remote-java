package com.realexpayments.remote.sdk.domain.payment;

import javax.xml.bind.annotation.*;

/**
 * Created by alessandro on 17/06/2016.
 */

@XmlRootElement(name = "rule")
@XmlAccessorType(XmlAccessType.FIELD)
public class FraudFilterRule {

    /**
     * The FraudFilterRule id action.
     */
    @XmlAttribute(name = "id")
    private String id;

    /**
     * The FraudFilterRule name action.
     */
    @XmlAttribute(name = "name")
    private String name;

    /**
     * The FraudFilterRule action action.
     */
    @XmlElement(name = "action")
    private String action;

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
     * Getter for action
     *
     * @return string
     */
    public String getAction() {
        return this.action;
    }

    /**
     * Setter for action
     *
     * @param action
     */
    public void setAction(String action) {
        this.action = action;
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
