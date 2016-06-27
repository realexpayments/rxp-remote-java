package com.realexpayments.remote.sdk.domain.payment;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * <p>
 * Class representing the FraudFilter mode in a Realex request. This optional XML element is used to
 * determine to what degree Fraud Filter executes. If the field is not present, Fraud Filter
 * will behave in accordance with the RealControl mode configuration. Please note values are case sensitive.
 * </p>
 * <p>
 * Helper methods are provided (prefixed with 'add') for object creation.
 * </p>
 * <p>
 * <p>
 * Example creation:
 * </p>
 * <p><code><pre>
 * FraudFilter fraudFilter = new FraudFilter().addMode(FraudFilterMode.ACTIVE);
 * </pre></code></p>
 * 
 * @author alessandro
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class FraudFilter {

	/**
	 * Enumeration representing the mode filter .
	 */
	public enum FraudFilterMode {
		ACTIVE("ACTIVE"),
		PASSIVE("PASSIVE"),
		OFF("OFF"),
		ERROR("ERROR");

		/**
		 * The mode field.
		 */
		private String mode;

		/**
		 * The enum constructor.
		 *
		 * @param mode
		 */
		FraudFilterMode(String mode) {
			this.mode = mode;
		}

		/**
		 * Getter for the mode.
		 *
		 * @return String
		 */
		public String toString() {
			return mode;
		}
	}

	/**
	 * Enumeration representing the mode filter .
	 */
	public enum FraudFilterResult {
		BLOCK("BLOCK"),
		PASS("PASS"),
		HOLD("HOLD"),
		NOT_SUPPORTED("NOT SUPPORTED");

		/**
		 * The result field.
		 */
		private String result;

		/**
		 * The enum constructor.
		 *
		 * @param result
		 */
		FraudFilterResult(String result) {
			this.result = result;
		}

		/**
		 * Getter for the result.
		 *
		 * @return String
		 */
		public String toString() {
			return result;
		}
	}
	/**
	 * The FraudFilter mode value.
	 */
	@XmlAttribute(name = "mode")
	private FraudFilterMode mode;

	/**
	 * The FraudFilter mode value.
	 */
	@XmlElement(name = "result")
	private FraudFilterResult result;

	/**
	 * The FraudFilter mode value.
	 */
	@XmlElements(@XmlElement(name = "rule", type = FraudFilterRule.class))
	private List<FraudFilterRule> rules;
	/**
	 * FraudFilter constructor
	 */
	public FraudFilter() {
	}

	/**
	 * Getter for mode
	 *
	 * @return FraudFilterMode
	 */
	public FraudFilterMode getMode() {
		return mode;
	}

	/**
	 * Setter for mode
	 *
	 * @param mode
	 */
	public void setMode(FraudFilterMode mode) {
		this.mode = mode;
	}

	/**
	 * Helper method for adding the mode value.
	 *
	 * @param mode
	 * @return FraudFilter
	 */
	public FraudFilter addMode(FraudFilterMode mode) {
		this.mode = mode;
		return this;
	}


	/**
	 * Setter for result
	 *
	 * @param result
	 */
	public void setResult(FraudFilterResult result) {
		this.result = result;
	}

	/**
	 * Getter for result
	 *
	 * @return result
	 */
	public FraudFilterResult getResult() {
		return result;
	}

	/**
	 * Helper method for adding the {@link FraudFilter} value.
	 *
	 * @param result
	 * @return FraudFilter
	 */
	public FraudFilter addResult(FraudFilterResult result) {
		this.result = result;
		return this;
	}


	/**
	 * Getter for FraudFilter rules.
	 *
	 * @return List<FraudFilterRule>
	 */
	public List<FraudFilterRule> getRules() {
		return rules;
	}

	/**
	 * Setter for FraudFilter rules.
	 *
	 * @param rules
	 */
	public void setRules(List<FraudFilterRule> rules) {
		this.rules = rules;
	}

	/**
	 * Convert to string representation
	 *
	 * @return string representation in format of <overall_rules>:<rule_id>-<rule_name>-<rule_value>;...
	 */
	@Override
	public String toString() {
		if (this.getResult() == null)
			return "";

		StringBuffer rules = new StringBuffer(this.getResult().toString());
		rules.append(":");
		for (FraudFilterRule check : this.getRules()) {
			rules.append(check.getId());
			rules.append("-");
			rules.append(check.getName());
			rules.append("-");
			rules.append(check.getValue());
			rules.append(";");
		}
		return rules.toString();
	}


}
