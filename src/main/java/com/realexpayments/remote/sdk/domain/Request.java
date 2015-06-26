package com.realexpayments.remote.sdk.domain;

import javax.xml.transform.Source;

/**
 * Interface to be implemented by all classes which represent Realex requests.
 * 
 * @author markstanford
 *
 */
public interface Request<T, U extends Response<U>> {

	/**
	 * <p>
	 * Method returns an XML representation of the interface implementation.
	 * </p>
	 * 
	 * @return String
	 */
	String toXml();

	/**
	 * <p>
	 * Method returns a concrete implementation of the request class from an XML source.
	 * </p>
	 * 
	 * @param xml
	 * @return T
	 */
	T fromXml(Source xml);

	/**
	 * <p>
	 * Generates default values for fields such as hash, timestamp and order ID.
	 * </p>
	 * 
	 * @param secret
	 * @return T
	 */
	T generateDefaults(String secret);

	/**
	 * <p>
	 * Method returns a concrete implementation of the response class from an XML source.
	 * </p>
	 * 
	 * @param xml
	 * @return U
	 */
	U responseFromXml(Source xml);

}