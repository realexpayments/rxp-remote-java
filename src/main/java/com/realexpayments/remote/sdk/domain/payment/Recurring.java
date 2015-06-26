package com.realexpayments.remote.sdk.domain.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * <p>
 *  If you are configured for recurring/continuous authority transactions, you must set the recurring values.
 * </p>
 * 
 * <p>
 * Helper methods are provided (prefixed with 'add') for object creation.
 * </p>
 * <p><code>
 * Recurring recurring = new Recurring().addFlag(RecurringFlag.ONE).addSequence(RecurringSequence.FIRST).addType(RecurringType.FIXED);
 * </code></p>								
 * 
 * @author markstanford
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class Recurring {

	/**
	 * Enum for recurring type. Type can be either fixed or variable depending on whether you will be changing 
	 * the amounts or not.
	 */
	public enum RecurringType {
		NONE(""),
		VARIABLE("variable"),
		FIXED("fixed");

		/**
		 * The type value
		 */
		private String type;

		/**
		 * Constructor for enum.
		 * 
		 * @param type
		 */
		RecurringType(String type) {
			this.type = type;
		}

		/**
		 * Returns String value for the type
		 * 
		 * @return String 
		 */
		public String getType() {
			return this.type;
		}
	}

	/**
	 * Enumeration representing the recurring sequence. Must be first for the first transaction for this card, 
	 * subsequent for transactions after that, and last for the final transaction of the set. 
	 * Only supported by some acquirers.
	 */
	public enum RecurringSequence {
		NONE(""),
		FIRST("first"),
		SUBSEQUENT("subsequent"),
		LAST("last");

		/**
		 * The sequence value
		 */
		private String sequence;

		/**
		 * Constructor for enum.
		 * 
		 * @param sequence
		 */
		RecurringSequence(String sequence) {
			this.sequence = sequence;
		}

		/**
		 * Returns String value for sequence
		 * 
		 * @return String 
		 */
		public String getSequence() {
			return this.sequence;
		}

	}

	/**
	 * Enumeration representing the recurring flag.
	 */
	public enum RecurringFlag {
		NONE(""),
		ZERO("0"),
		ONE("1"),
		TWO("2");

		/**
		 * The flag value
		 */
		private String recurringFlag;

		/**
		 * The RecurringFlag constructor
		 * 
		 * @param recurringFlag
		 */
		RecurringFlag(String recurringFlag) {
			this.recurringFlag = recurringFlag;
		}

		/**
		 * Getter for the flag value
		 * 
		 * @return String
		 */
		public String getRecurringFlag() {
			return this.recurringFlag;
		}
	}

	/**
	 * Type can be either fixed or variable depending on whether you will be changing the amounts or not.
	 */
	@XmlAttribute(name = "type")
	private String type;

	/**
	 * The recurring sequence. Must be first for the first transaction for this card, 
	 * subsequent for transactions after that, and last for the final transaction of the set. 
	 * Only supported by some acquirers.
	 */
	@XmlAttribute(name = "sequence")
	private String sequence;

	/**
	 * The recurring flag. Optional field taking values 0, 1 or 2.
	 */
	@XmlAttribute(name = "flag")
	private String flag;

	/**
	 * Getter for the type
	 * 
	 * @return String 
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Setter for the type
	 * 
	 * @param type
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * Getter for the sequence
	 * 
	 * @return String 
	 */
	public String getSequence() {
		return this.sequence;
	}

	/**
	 * Setter for the sequence
	 * 
	 * @param sequence
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	/**
	 * Getter for the flag
	 * 
	 * @return String 
	 */
	public String getFlag() {
		return this.flag;
	}

	/**
	 * Setter for the flag
	 * 
	 * @param flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * Helper method for adding a type
	 * 
	 * @param type
	 */
	public Recurring addType(String type) {
		this.type = type;
		return this;
	}

	/**
	 * Helper method for adding a type
	 * 
	 * @param type
	 */
	public Recurring addType(RecurringType type) {
		this.type = type.getType();
		return this;
	}

	/**
	 * Helper method for adding a sequence
	 * 
	 * @param sequence
	 */
	public Recurring addSequence(RecurringSequence sequence) {
		this.sequence = sequence.getSequence();
		return this;
	}

	/**
	 * Helper method for adding a sequence
	 * 
	 * @param sequence
	 * @return Recurring
	 */
	public Recurring addSequence(String sequence) {
		this.sequence = sequence;
		return this;
	}

	/**
	 * Helper method for adding a flag
	 * 
	 * @param flag
	 * @return Recurring 
	 */
	public Recurring addFlag(RecurringFlag flag) {
		this.flag = flag.getRecurringFlag();
		return this;
	}

	/**
	 * Helper method for adding a flag
	 * 
	 * @param flag
	 * @return Recurring
	 */
	public Recurring addFlag(String flag) {
		this.flag = flag;
		return this;
	}
}
