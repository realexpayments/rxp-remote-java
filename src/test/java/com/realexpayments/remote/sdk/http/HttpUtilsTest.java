package com.realexpayments.remote.sdk.http;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Matchers;

import com.realexpayments.remote.sdk.RealexException;

/**
 * HTTP Utils such as getting a default http client and sending a message.
 * 
 * @author thomasduffy
 */
public class HttpUtilsTest {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	/**
	 * Test sending a message, expecting a successful response.
	 */
	@Test
	public void sendMessageSuccessTest() {

		try {
			String endpoint = "https://some-test-endpoint";
			boolean onlyAllowHttps = true;

			String xml = "<element>test response xml</element>";

			// Dummy and Mock required objects
			ProtocolVersion protocolVersion = new ProtocolVersion("https", 1, 1);
			int statusCode = 200;
			String statusReason = "";
			HttpResponse httpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, statusCode, statusReason));
			httpResponse.setEntity(new StringEntity(xml));

			HttpConfiguration httpConfigurationMock = mock(HttpConfiguration.class);
			when(httpConfigurationMock.getEndpoint()).thenReturn(endpoint);
			when(httpConfigurationMock.isOnlyAllowHttps()).thenReturn(onlyAllowHttps);

			HttpClient httpClientMock = mock(HttpClient.class);
			when(httpClientMock.execute(Matchers.any(HttpUriRequest.class))).thenReturn(httpResponse);

			// Execute the method
			String response = HttpUtils.sendMessage(xml, httpClientMock, httpConfigurationMock);

			// Check the repsonse
			Assert.assertEquals(response, xml);
		} catch (Exception e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Test sending a message, expecting an exception due to failure response.
	 */
	@Test
	public void sendMessageFailureTest() {

		// Dummy required objects
		ProtocolVersion protocolVersion = new ProtocolVersion("https", 1, 1);
		int statusCode = 400;
		String statusReason = "";

		thrown.expect(RealexException.class);
		thrown.expectMessage("Unexpected http status code [" + statusCode + "]");

		try {
			String endpoint = "https://some-test-endpoint";
			boolean onlyAllowHttps = true;

			String xml = "<element>test response xml</element>";

			// Mock required objects
			HttpResponse httpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, statusCode, statusReason));
			httpResponse.setEntity(new StringEntity(xml));

			HttpConfiguration httpConfigurationMock = mock(HttpConfiguration.class);
			when(httpConfigurationMock.getEndpoint()).thenReturn(endpoint);
			when(httpConfigurationMock.isOnlyAllowHttps()).thenReturn(onlyAllowHttps);

			HttpClient httpClientMock = mock(HttpClient.class);
			when(httpClientMock.execute(Matchers.any(HttpUriRequest.class))).thenReturn(httpResponse);

			// Execute the method
			String response = HttpUtils.sendMessage(xml, httpClientMock, httpConfigurationMock);
		} catch (IOException e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
	}

	/**
	 * Test sending a message, expecting an exception due to failure response.
	 */
	@Test
	public void sendMessageFailureHttpNotAllowedTest() {

		// Dummy required objects
		ProtocolVersion protocolVersion = new ProtocolVersion("http", 1, 1);
		int statusCode = 200;
		String statusReason = "";

		thrown.expect(RealexException.class);
		thrown.expectMessage("Protocol must be https");

		try {
			String endpoint = "http://some-test-endpoint";
			boolean onlyAllowHttps = true;

			String xml = "<element>test response xml</element>";

			// Mock required objects
			HttpResponse httpResponse = new BasicHttpResponse(new BasicStatusLine(protocolVersion, statusCode, statusReason));
			httpResponse.setEntity(new StringEntity(xml));

			HttpConfiguration httpConfigurationMock = mock(HttpConfiguration.class);
			when(httpConfigurationMock.getEndpoint()).thenReturn(endpoint);
			when(httpConfigurationMock.isOnlyAllowHttps()).thenReturn(onlyAllowHttps);

			HttpClient httpClientMock = mock(HttpClient.class);
			when(httpClientMock.execute(Matchers.any(HttpUriRequest.class))).thenReturn(httpResponse);

			// Execute the method
			String response = HttpUtils.sendMessage(xml, httpClientMock, httpConfigurationMock);
		} catch (IOException e) {
			Assert.fail("Unexpected exception: " + e.getMessage());
		}
	}

}
