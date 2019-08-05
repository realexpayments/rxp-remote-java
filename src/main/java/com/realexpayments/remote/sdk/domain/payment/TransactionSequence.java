package com.realexpayments.remote.sdk.domain.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * <p>
 * Domain object representing transaction sequence information.
 * </p>
 * <p/>
 * <p><code><pre>
 * TransactionSequence sequence = new TransactionSequence();
 * sequence.setCardHolderCurrency(finalTransactionIndicator);
 *
 * <p/>
 * </pre></code></p>
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TransactionSequence {

    /**
     * Attribute wrapper that indicates whether this is the final capture or not
     */
    @XmlElement(name = "final")
    private FinalTransactionIndicator finalTransactionIndicator;

    /**
     * Constructor for TransactionSequence.
     */
    public TransactionSequence() {
    }

    /**
     *  Getter for finalTransactionIndicator.
     */
    public FinalTransactionIndicator getFinalTransactionIndicator() {
        return finalTransactionIndicator;
    }

    /**
     *  Setter for finalTransactionIndicator.
     */
    public void setFinalTransactionIndicator(final FinalTransactionIndicator finalTransactionIndicator) {
        this.finalTransactionIndicator = finalTransactionIndicator;
    }
}
