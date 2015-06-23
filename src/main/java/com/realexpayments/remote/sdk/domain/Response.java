package com.realexpayments.remote.sdk.domain;

import javax.xml.transform.Source;

/**
 * Interface to be implemented by all classes which represent Realex responses.
 * 
 * @author markstanford
 *
 */
public interface Response<T> {

	/**
	 * Defines the result code which represents success
	 */
	String RESULT_CODE_SUCCESS = "00";

	/**
	 * Realex error result codes in the range 3xx to 5xx will not return a full response message.
	 * Instead a short response will be returned with only the result code and message populated.   
	 */
	Integer RESULT_CODE_PREFIX_ERROR_RESPONSE_START = 3;

	/**
	 * <p>
	 * Method returns a concrete implementation of the response class from an XML source.
	 * </p>
	 * 
	 * @param xml
	 * @return T
	 */
	T fromXml(Source xml);

	/**
	 * <p>
	 * Method returns an XML representation of the interface's implementing class.
	 * </p>
	 * 
	 * @return String
	 */
	String toXml();

	/**
	 * <p>
	 * Validates the hash in the response is correct. Returns <code>true</code> if valid, 
	 * <code>false</code> if not.
	 * </p>
	 * 
	 * @param secret
	 * @return boolean
	 */
	boolean isHashValid(String secret);

	/**
	 * Returns the result from the response. 
	 * 
	 * @return String
	 */
	String getResult();

	/**
	 * Returns the message from the response. 
	 * 
	 * @return String
	 */
	String getMessage();

	/**
	 * Returns the orderId from the response. 
	 * 
	 * @return String
	 */
	String getOrderId();

	/**
	 * Returns the timestamp from the response. 
	 * 
	 * @return String
	 */
	String getTimeStamp();

	/**
	 * Returns <code>true</code> if response message has processed successfully.
	 * 
	 * @return boolean
	 */
	boolean isSuccess();
}
