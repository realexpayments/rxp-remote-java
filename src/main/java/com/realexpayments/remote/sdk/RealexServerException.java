package com.realexpayments.remote.sdk;

/**
 * This exception will be thrown when an error occurs on the Realex server when attempting to process
 * the request.
 * 
 * @author markstanford
 */
public class RealexServerException extends RealexException {

	private static final long serialVersionUID = -298850091427275465L;

	/**
	 * The error code returned from Realex.
	 */
	private final String errorCode;

	/**
	 * The order Id of the request/response.
	 */
	private final String orderId;

	/**
	 * The timestamp of the request/response.
	 */
	private final String timeStamp;

	/**
	 * Constructor for RealexServerException. 
	 * 
	 * @param timeStamp
	 * @param orderId
	 * @param errorCode
	 * @param message
	 */
	public RealexServerException(String timeStamp, String orderId, String errorCode, String message) {
		super(message);
		this.errorCode = errorCode;
		this.orderId = orderId;
		this.timeStamp = timeStamp;
	}

	/**
	 * Getter for error code.
	 * 
	 * @return String
	 */
	public String getErrorCode() {
		return errorCode;
	}

	/**
	 * Get the order Id of the request which generated this exception.
	 * 
	 * @return String
	 */
	public String getOrderId() {
		return orderId;
	}

	/**
	 * Get the timestamp of the request which generated this exception.
	 * 
	 * @return String
	 */
	public String getTimeStamp() {
		return timeStamp;
	}

}
