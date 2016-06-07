package com.realexpayments.remote.sdk.domain.payment;

/**
 * Created by alessandro on 07/06/2016.
 */
public enum ReasonCode {

        // @formatter:off
        FRAUD("FRAUD"),
        OUTOFSTOCK("OUTOFSTOCK"),
        OTHER("OTHER"),
        FALSE_POSITIVE("FALSEPOSITIVE"),
        INSTOCK("INSTOCK"),
        NOTGIVEN("NOTGIVEN");
        // @formatter:on

        /**
         * The card type.
         */
        private String reason;

        /**
         * Constructor for enum.
         */
        ReasonCode(String reason) {
            this.reason = reason;
        }

        /**
         * Getter for reason type.
         *
         * @return String
         */
        public String getType() {
            return this.reason;
        }

}
