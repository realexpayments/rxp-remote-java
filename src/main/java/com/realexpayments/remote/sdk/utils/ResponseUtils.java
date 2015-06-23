package com.realexpayments.remote.sdk.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.realexpayments.remote.sdk.RealexException;
import com.realexpayments.remote.sdk.RealexServerException;
import com.realexpayments.remote.sdk.domain.Response;

/**
 * Utils class offering methods which act on the Realex response. 
 * 
 * @author markstanford
 *
 */
public class ResponseUtils {

	/**
	 * Logger 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(ResponseUtils.class);

	/**
	 * <p>
	 * Realex responses can be basic or full. A basic response indicates the request could not
	 * be processed. In this case a {@link RealexServerException} will be thrown by the SDK containing the
	 * result code and message from the response. 
	 * </p>
	 * 
	 * <p>
	 * A full response indicates the request could be processed and the response object will return fully populated.
	 * </p>
	 * 
	 * <p>
	 * Please note, full responses may still contain errors e.g. Bank errors (1xx). The result code should be 
	 * checked for success. For example a full response with a result code of 101 will not throw an exception and will return 
	 * a fully populated response object. 
	 * </p>
	 *   
	 * @param result
	 * @return boolean
	 */
	public static boolean isBasicResponse(String result) {
		boolean inErrorRange = false;

		try {
			int initialNumber = Integer.parseInt(result.substring(0, 1));
			inErrorRange = initialNumber >= Response.RESULT_CODE_PREFIX_ERROR_RESPONSE_START;
		} catch (Exception ex) {
			LOGGER.error("Error parsing result {}", result, ex);
			throw new RealexException("Error parsing result.", ex);
		}

		return inErrorRange;
	}

	/**
	 * Returns <code>true</code> if the response contains a result code representing success.
	 * 
	 * @return boolean
	 */
	public static <T extends Response<T>> boolean isSuccess(Response<T> response) {
		return Response.RESULT_CODE_SUCCESS.equals(response.getResult());
	}

}
