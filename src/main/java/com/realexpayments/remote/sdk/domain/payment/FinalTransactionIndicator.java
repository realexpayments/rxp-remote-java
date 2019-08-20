package com.realexpayments.remote.sdk.domain.payment;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * <p>
 * Represents the flag wrapper that indicates whether this is the final capture or not
 * </p>
 * <p>
 * Helper methods are provided (prefixed with 'add') for object creation.
 * </p>
 * <p>
 * Example creation:
 * </p>
 * <p><code><pre>
 * FinalTransactionIndicator indicator = new FinalTransactionIndicator()
 * 		.addFinalTransactionFlag("1")
 * </pre></code></p>
 *
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class FinalTransactionIndicator {

    /**
     * Attribute that indicates whether this is the final capture or not
     *
     * Value range: 0 or 1
     */
    @XmlAttribute(name = "flag")
    private String isFinalTransaction;

    /**
     * Constructor for FinalTransactionIndicator.
     */
    public FinalTransactionIndicator() {
    }

    /**
     * Getter for isFinalTransaction flag.
     */
    public String getIsFinalTransaction() {
        return isFinalTransaction;
    }

    /**
     *  Setter for isFinalTransaction flag.
     */
    public void setIsFinalTransaction(final String isFinalTransaction) {
        this.isFinalTransaction = isFinalTransaction;
    }

    /**
     * Helper method for adding a isFinalTransaction flag.
     */
    public FinalTransactionIndicator addFinalTransactionFlag(final String isFinalTransaction) {
        this.isFinalTransaction = isFinalTransaction;
        return this;
    }
}
