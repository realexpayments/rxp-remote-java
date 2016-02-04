package com.realexpayments.remote.sdk.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Domain object representing DCC information to be passed to Realex.
 * </p>
 * <p/>
 * <p><code><pre>
 * DccInfo dccInfo = new DccInfo()
 * .addDccProcessor("fexco");
 * <p/>
 * </pre></code></p>
 *
 * @author vicpada
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DccInfo {

    /**
     * The DCC processor (Currency Conversion Processor)
     */
    @XmlElement(name = "ccp")
    private String dccProcessor;

    /**
     * The type - always "1"
     */
    @XmlElement(name = "type")
    private String type;


    /**
     * Constructor
     */
    public DccInfo() {

        // default type to 1
        this.type = "1";

    }

    /**
     * Getter for dccProcessor
     *
     * @return String
     */
    public String getDccProcessor() {
        return dccProcessor;
    }

    /**
     * Setter for dccProcessor
     *
     * @param dccProcessor
     */
    public void setDccProcessor(String dccProcessor) {
        this.dccProcessor = dccProcessor;
    }

    /**
     * Getter for type
     *
     * @return String
     */
    public String getType() {
        return type;
    }

    /**
     * Setter for type
     *
     * @param type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Helper method for adding a dcc processor.
     *
     * @param dccProcessor
     * @return DccInfo
     */
    public DccInfo addDccProcessor(String dccProcessor) {
        this.dccProcessor = dccProcessor;
        return this;
    }

    /**
     * Helper method for adding a type.
     *
     * @param type
     * @return DccInfo
     */
    public DccInfo addType(String type) {
        this.dccProcessor = type;
        return this;
    }
}
