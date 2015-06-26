package com.realexpayments.remote.sdk.domain.payment;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;

/**
 * The results of realscore checks.
 * 
 * @author markstanford
 *
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class TssResult {

	/**
	 * The weighted total score of realscore. The weights can be adjusted in the realcontrol application.
	 */
	@XmlElement(name = "result")
	private String result;

	/**
	 * The list of realscore check results.
	 */
	@XmlElements(@XmlElement(name = "check", type = TssResultCheck.class))
	private List<TssResultCheck> checks;

	/**
	 * Getter for realscore result.
	 * 
	 * @return String
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Setter for realscore result.
	 * 
	 * @param result
	 */
	public void setResult(String result) {
		this.result = result;
	}

	/**
	 * Getter for realscore checks.
	 * 
	 * @return List<TssResultCheck>
	 */
	public List<TssResultCheck> getChecks() {
		return checks;
	}

	/**
	 * Setter for realscore checks.
	 * 
	 * @param checks
	 */
	public void setChecks(List<TssResultCheck> checks) {
		this.checks = checks;
	}

	/**
	 * Convert to string representation
	 * 
	 * @return string representation in format of <overall_result>:<check_id>-<check_result>;<check_id>-<check_result>;...
	 */
	@Override
	public String toString() {

		StringBuffer result = new StringBuffer(this.getResult());
		result.append(":");
		for (TssResultCheck check : this.getChecks()) {
			result.append(check.getId());
			result.append("-");
			result.append(check.getValue());
			result.append(";");
		}
		return result.toString();
	}
}
