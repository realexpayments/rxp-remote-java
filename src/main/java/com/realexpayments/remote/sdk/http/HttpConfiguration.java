package com.realexpayments.remote.sdk.http;

/**
 * Object containing all configurable HTTP settings.
 */
public class HttpConfiguration {

	/**
	 * The default endpoint for all requests
	 */
	private static final String DEFAULT_ENDPOINT = "https://api.realexpayments.com/epage-remote.cgi";

	/**
	 * The default timeout in milliseconds.
	 */
	private static final int DEFAULT_TIMEOUT = 65000;

	/** The URL of the Realex service. */
	private String endpoint;

	/** The timeout, in milli-seconds, for sending a request to Realex. */
	private int timeout;

	/** Whether only HTTPS is allowed for the endpoint. */
	private boolean onlyAllowHttps = true;

	/* Constructors/Getters/Setters */

	/**
	 * Create a HttpConfiguration object with all defaults in place.
	 * 
	 */
	public HttpConfiguration() {
		// Set defaults
		this.endpoint = DEFAULT_ENDPOINT;
		this.timeout = DEFAULT_TIMEOUT;
	}

	/**
	 * Get the endpoint/destination for the request.
	 *  
	 * @return the endpoint
	 */
	public String getEndpoint() {
		return endpoint;
	}

	/**
	 * Set the endpoint/destination for the request.
	 * 
	 * @param endpoint the endpoint to set
	 */
	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	/**
	 * The timeout for a request to Realex.
	 * 
	 * @return the timeout
	 */
	public int getTimeout() {
		return timeout;
	}

	/**
	 * Set the timeout, in milli-seconds, for sending a request to Realex.
	 * 
	 * @param timeout the timeout to set
	 */
	public void setTimeout(int timeout) {
		this.timeout = timeout;
	}

	/**
	 * Check is HTTPS the only allowed scheme (protocol) to the endpoint.
	 * @return the onlyAllowHttps
	 */
	public boolean isOnlyAllowHttps() {
		return onlyAllowHttps;
	}

	/**
	 * Set whether (true) or not (false) HTTPS is the only allowed scheme (protocol) to the endpoint.
	 * 
	 * @param onlyAllowHttps the onlyAllowHttps to set
	 */
	public void setOnlyAllowHttps(boolean onlyAllowHttps) {
		this.onlyAllowHttps = onlyAllowHttps;
	}

}
