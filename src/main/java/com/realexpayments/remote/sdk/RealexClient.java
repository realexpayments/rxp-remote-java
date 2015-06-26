package com.realexpayments.remote.sdk;

import java.io.StringReader;

import javax.xml.transform.stream.StreamSource;

import org.apache.http.client.HttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.realexpayments.remote.sdk.domain.Request;
import com.realexpayments.remote.sdk.domain.Response;
import com.realexpayments.remote.sdk.http.HttpConfiguration;
import com.realexpayments.remote.sdk.http.HttpUtils;
import com.realexpayments.remote.sdk.utils.ResponseUtils;

/**
 * <p>
 * Realex client class for sending requests to Realex. 
 * <p>
 * <p>
 * The client class exposes three constructors which offer different HTTP configuration options.   
 * </p>
 * <p>
 * <ol>
 * 
 * <li>
 * A default {@link HttpClient} instance will be used for sending HTTP requests to Realex. 
 * Some default configuration settings will be applied as outlined in {@link HttpConfiguration}. Example construction:
 * <code><pre>
 * RealexClient client = new RealexClient("shared secret");
 * </pre></code>
 * </li>
 * 
 * <li>
 * A default {@link HttpClient} will be used, but it will be configured based on the 
 * supplied {@link HttpConfiguration} object. Example construction:
 * <code><pre>
 * HttpConfiguration httpConfiguration = new HttpConfiguration("https://epage.payandshop.com/epage.cgi", 1000);
 * RealexClient client = new RealexClient("shared secret", httpConfiguration);
 * </pre></code>
 * </li>
 * 
 * <li>
 * The supplied {@link HttpClient} will be used, and will be further configured based on the 
 * supplied {@link HttpConfiguration} object. Example construction: 
 * <code><pre>
 * HttpConfiguration httpConfiguration = new HttpConfiguration("https://epage.payandshop.com/epage.cgi", 1000);
 * HttpClient httpClient = createCustomHttpClient(); //custom HttpClient created by merchant
 * RealexClient client = new RealexClient("shared secret", httpClient, httpConfiguration);
 * </pre></code>
 * </li>
 * 
 * </ol>
 * </p>
 * @author markstanford
 *
 */
public class RealexClient {

	/**
	 * Logger 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(RealexClient.class);

	/**
	 * The shared secret issued by Realex. Used to create the SHA-1 hash in the request and
	 * to verify the validity of the XML response. 
	 */
	private String secret;

	/**
	 * HttpClient instance.
	 */
	private HttpClient httpClient;

	/**
	 * HttpConfiguration instance.
	 */
	private HttpConfiguration httpConfiguration;

	/**
	 * Realex client constructor. Will use default HTTP configuration. 
	 * 
	 * @param secret
	 */
	public RealexClient(String secret) {
		this.secret = secret;
		this.httpConfiguration = new HttpConfiguration();
		this.httpClient = HttpUtils.getDefaultClient(httpConfiguration);
	}

	/**
	 * Realex client constructor. A HttpConfiguration instance can be passed to this 
	 * constructor if a custom implementation is required. 
	 * 
	 * @param secret
	 * @param httpConfiguration
	 */
	public RealexClient(String secret, HttpConfiguration httpConfiguration) {
		this.secret = secret;
		this.httpConfiguration = httpConfiguration;
		this.httpClient = HttpUtils.getDefaultClient(httpConfiguration);
	}

	/**
	 * Realex client constructor. HttpClient and HttpConfiguration instances can be passed to this 
	 * constructor if a custom implementation is required. 
	 * 
	 * @param secret
	 * @param httpClient
	 * @param httpConfiguration
	 */
	public RealexClient(String secret, HttpClient httpClient, HttpConfiguration httpConfiguration) {
		this.secret = secret;
		this.httpConfiguration = httpConfiguration;
		this.httpClient = httpClient;
	}

	/**
	 * Getter for shared secret. 
	 * 
	 * @return String
	 */
	public String getSecret() {
		return secret;
	}

	/**
	 * Setter for shared secret.
	 * 
	 * @param secret
	 */
	public void setSecret(String secret) {
		this.secret = secret;
	}

	/**
	 * Getter for HttpClient.
	 * 
	 * @return HttpClient
	 */
	public HttpClient getHttpClient() {
		return httpClient;
	}

	/**
	 * Setter for HttpClient.
	 * 
	 * @param httpClient
	 */
	public void setHttpClient(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	/**
	 * Getter for HttpConfiguration.
	 * 
	 * @return HttpConfiguration
	 */
	public HttpConfiguration getHttpConfiguration() {
		return httpConfiguration;
	}

	/**
	 * Setter for HttpConfiguration.
	 * 
	 * @param httpConfiguration
	 */
	public void setHttpConfiguration(HttpConfiguration httpConfiguration) {
		this.httpConfiguration = httpConfiguration;
	}

	/**
	 * <p>
	 * Sends the request to Realex. Actions:
	 * 
	 * <ol>
	 * <li>Generates any defaults on the request e.g. hash, time stamp order ID.</li>
	 * <li>Marshals request to XML.</li>
	 * <li>Sends request to Realex.</li>
	 * <li>Unmarshals response.</li>
	 * <li>Checks result code (If response is an error then throws {@link RealexServerException}).</li>
	 * <li>Validates response hash (If invalid throws {@link RealexException}).</li>
	 * </ol>
	 * </p>
	 * 
	 * @param request
	 * @return Response
	 */
	public <T, U extends Response<U>> U send(Request<T, U> request) {

		LOGGER.info("Sending XML request to Realex.");

		//generate any required defaults e.g. order ID, time stamp, hash
		request.generateDefaults(secret);

		//convert request to XML
		LOGGER.debug("Marshalling request object to XML.");
		String xmlRequest = request.toXml();

		//send request to Realex.
		String xmlResult = HttpUtils.sendMessage(xmlRequest, httpClient, httpConfiguration);

		//log the response
		LOGGER.trace("Response XML from server: {}", xmlResult);

		//convert XML to response object
		LOGGER.debug("Unmarshalling XML to response object.");
		U response = request.responseFromXml(new StreamSource(new StringReader(xmlResult)));

		//throw exception if short response returned (indicating request could not be processed).
		if (ResponseUtils.isBasicResponse(response.getResult())) {
			LOGGER.error("Error response received from Realex with code {} and message {}.", response.getResult(), response.getMessage());
			throw new RealexServerException(response.getTimeStamp(), response.getOrderId(), response.getResult(), response.getMessage());
		}

		//validate response hash
		LOGGER.debug("Verifying response hash.");
		if (!response.isHashValid(secret)) {
			//Hash invalid. Throw exception.
			LOGGER.error("Response hash is invalid. This response's validity cannot be verified.");
			throw new RealexException("Response hash is invalid. This response's validity cannot be verified.");
		}

		return response;
	}
}
