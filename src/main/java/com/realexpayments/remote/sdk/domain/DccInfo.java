package com.realexpayments.remote.sdk.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Domain object representing DCC information to be passed to Realex.
 * </p>
 * <p/>
 * <p>
 * Example dcc rate lookup:
 * </p> *
 * <p><code><pre>
 * DccInfo dccInfo = new DccInfo()
 * .addDccProcessor("fexco");
 * <p/>
 * </pre></code></p>
 * <p/>
 * <p>
 * Example dcc rate lookup + auth:
 * </p>
 * <p><code><pre>
 *
 * DccInfo dccInfo = new DccInfo()
 * .addDccProcessor("fexco")
 * .addRate(0.6868)
 * .addAmount(13049)
 * .addCurrency("GBP");
 *
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
     * The rate returned by DCC Info rate lookup request
     */
    @XmlElement(name = "rate")
    private double rate;


    /**
     * The rate type. Defaulted to S
     */
    @XmlElement(name = "ratetype")
    private String rateType;

    /**
     * The amount. As returned by DCC Info rate lookup request
     */
    @XmlElement(name = "amount")
    private Amount amount;

    /**
     * Constructor
     */
    public DccInfo() {

        // default type to 1 and rate type to "S"
        this.type = "1";
        this.rateType = "S";

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
     * Getter for rate
     *
     * @return Double
     */
    public Double getRate() {
        return rate;
    }

    /**
     * Setter for rate
     *
     * @param rate
     */
    public void setRate(Double rate) {
        this.rate = rate;
    }


    /**
     * Getter for rateType
     *
     * @return String
     */
    public String getRateType() {
        return rateType;
    }

    /**
     * Setter for rateType
     *
     * @param rateType
     */
    public void setRateType(String rateType) {
        this.rateType = rateType;
    }


    /**
     * Getter for amount
     *
     * @return Amount
     */
    public Amount getAmount() {
        return amount;
    }

    /**
     * Setter for amount
     *
     * @param amount
     */
    public void setAmount(Amount amount) {
        this.amount = amount;
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
        this.type = type;
        return this;
    }

    /**
     * Helper method for adding a rate.
     *
     * @param rate
     * @return DccInfo
     */
    public DccInfo addRate(Double rate) {
        this.rate = rate;
        return this;
    }

    /**
     * Helper method for adding a rate type.
     *
     * @param rateType
     * @return DccInfo
     */
    public DccInfo addRateType(String rateType) {
        this.rateType = rateType;
        return this;
    }

    /**
     * Helper method for adding an amount.
     *
     * @param amount
     * @return DccInfo
     */
    public DccInfo addAmount(Long amount) {
        if (null == this.amount) {
            this.amount = new Amount().addAmount(amount);
        } else {
            this.amount.addAmount(amount);
        }
        return this;
    }

    /**
     * Helper method for adding a currency.
     *
     * @param currency
     * @return DccInfo
     */
    public DccInfo addCurrency(String currency) {
        if (null == this.amount) {
            this.amount = new Amount().addCurrency(currency);
        } else {
            this.amount.addCurrency(currency);
        }
        return this;
    }
}
