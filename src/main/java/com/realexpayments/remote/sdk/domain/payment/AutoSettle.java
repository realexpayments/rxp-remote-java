package com.realexpayments.remote.sdk.domain.payment;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * <p>
 * Class representing the AutoSettle flag in a Realex request. If set to TRUE (1),
 * then the transaction will be included in today's settlement file. If set to FALSE (0), then the
 * transaction will be authorised but not settled. Merchants must manually settle delayed
 * transactions within 28 days of authorisation. 
 * </p>
 * <p>
 * Helper methods are provided (prefixed with 'add') for object creation.
 * </p>
 * <p>
 * Example creation:
 * </p>
 * <p><code><pre>
 * AutoSettle autoSettle = new AutoSettle().addFlag(AutoSettleTrue.TRUE); 
 * </pre></code></p>
 * 
 * @author markstanford
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AutoSettle {

	/**
	 * Enumeration representing the auto settle flag (true (1), false (0) or multi-settle (MULTI)).
	 */
	public enum AutoSettleFlag {
		TRUE("1"),
		FALSE("0"),
		MULTI("MULTI");

		/**
		 * The flag field.
		 */
		private String flag;

		/**
		 * The enum constructor.
		 * 
		 * @param flag
		 */
		AutoSettleFlag(String flag) {
			this.flag = flag;
		}

		/**
		 * Getter for the flag.
		 * 
		 * @return String
		 */
		public String getFlag() {
			return flag;
		}
	}

	/**
	 * The AutoSettle flag value.
	 */
	@XmlAttribute(name = "flag")
	private String flag;

	/**
	 * AutoSettle constructor
	 */
	public AutoSettle() {
	}

	/**
	 * Getter for flag
	 * 
	 * @return AutoSettleFlag
	 */
	public String getFlag() {
		return flag;
	}

	/**
	 * Setter for flag
	 * 
	 * @param flag
	 */
	public void setFlag(String flag) {
		this.flag = flag;
	}

	/**
	 * Helper method for adding the flag value.
	 * 
	 * @param autoSettleFlag
	 * @return AutoSettle
	 */
	public AutoSettle addFlag(String autoSettleFlag) {
		this.flag = autoSettleFlag;
		return this;
	}

	/**
	 * Helper method for adding the {@link AutoSettle} value.
	 * 
	 * @param autoSettleFlag
	 * @return AutoSettle
	 */
	public AutoSettle addFlag(AutoSettleFlag autoSettleFlag) {
		this.flag = autoSettleFlag.getFlag();
		return this;
	}

}
