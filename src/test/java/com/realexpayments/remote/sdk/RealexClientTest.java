package com.realexpayments.remote.sdk;

import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.PAYMENT_RESPONSE_BASIC_ERROR_XML_PATH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.PAYMENT_RESPONSE_FULL_ERROR_XML_PATH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.PAYMENT_RESPONSE_XML_PATH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.THREE_D_SECURE_VERIFY_ENROLLED_RESPONSE_XML_PATH;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.checkBasicResponseError;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.checkFullResponseError;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.checkUnmarshalledPaymentResponse;
import static com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils.checkUnmarshalledThreeDSecureEnrolledResponse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.File;
import java.io.IOException;

import javax.xml.transform.stream.StreamSource;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicStatusLine;
import org.junit.Assert;
import org.junit.Test;
import org.mockito.Matchers;

import com.realexpayments.remote.sdk.domain.payment.PaymentRequest;
import com.realexpayments.remote.sdk.domain.payment.PaymentResponse;
import com.realexpayments.remote.sdk.domain.threeDSecure.ThreeDSecureRequest;
import com.realexpayments.remote.sdk.domain.threeDSecure.ThreeDSecureResponse;
import com.realexpayments.remote.sdk.http.HttpConfiguration;
import com.realexpayments.remote.sdk.utils.SampleXmlValidationUtils;

/**
 * Unit test class for {@link RealexClient}.
 * 
 * @author markstanford
 *
 */
public class RealexClientTest {

	/**
	 * Test sending a payment request and receiving a payment response. 
	 */
	@Test
	public void sendTest() throws ClientProtocolException, IOException {

		//get sample response XML
		File file = new File(this.getClass().getResource(PAYMENT_RESPONSE_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);
		PaymentResponse fromXmlResponse = new PaymentResponse().fromXml(source);

		//mock HttpResponse
		HttpResponse httpResponseMock = mock(HttpResponse.class);
		when(httpResponseMock.getEntity()).thenReturn(new StringEntity(fromXmlResponse.toXml()));
		when(httpResponseMock.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null));

		//create empty request 
		PaymentRequest request = new PaymentRequest();

		//create configuration
		HttpConfiguration httpConfiguration = new HttpConfiguration();
		httpConfiguration.setOnlyAllowHttps(false);

		//mock HttpClient instance
		HttpClient httpClientMock = mock(HttpClient.class);
		when(httpClientMock.execute(Matchers.any(HttpUriRequest.class))).thenReturn(httpResponseMock);

		//execute send on client
		RealexClient realexClient = new RealexClient(SampleXmlValidationUtils.SECRET, httpClientMock, httpConfiguration);
		PaymentResponse response = realexClient.send(request);

		//validate response
		checkUnmarshalledPaymentResponse(response);
	}

	/**
	 * Test sending a payment request and receiving a payment response error. 
	 */
	@Test
	public void sendWithShortErrorResponseTest() throws ClientProtocolException, IOException {

		//get sample response XML
		File file = new File(this.getClass().getResource(PAYMENT_RESPONSE_BASIC_ERROR_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);
		PaymentResponse fromXmlResponse = new PaymentResponse().fromXml(source);

		//mock HttpResponse
		HttpResponse httpResponseMock = mock(HttpResponse.class);
		when(httpResponseMock.getEntity()).thenReturn(new StringEntity(fromXmlResponse.toXml()));
		when(httpResponseMock.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null));

		//create empty request 
		PaymentRequest request = new PaymentRequest();

		//create configuration
		HttpConfiguration httpConfiguration = new HttpConfiguration();
		httpConfiguration.setOnlyAllowHttps(false);

		//mock HttpCLient instance
		HttpClient httpClientMock = mock(HttpClient.class);
		when(httpClientMock.execute(Matchers.any(HttpUriRequest.class))).thenReturn(httpResponseMock);

		//execute send on client
		RealexClient realexClient = new RealexClient(SampleXmlValidationUtils.SECRET, httpClientMock, httpConfiguration);

		try {
			realexClient.send(request);
			Assert.fail("RealexException should have been thrown before this point.");
		} catch (RealexServerException ex) {
			//validate error
			checkBasicResponseError(ex);
		}
	}

	/**
	 * Test sending a payment request and receiving a payment response error. 
	 */
	@Test
	public void sendWithLongErrorResponseTest() throws ClientProtocolException, IOException {

		//get sample response XML
		File file = new File(this.getClass().getResource(PAYMENT_RESPONSE_FULL_ERROR_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);
		PaymentResponse fromXmlResponse = new PaymentResponse().fromXml(source);

		//mock HttpResponse
		HttpResponse httpResponseMock = mock(HttpResponse.class);
		when(httpResponseMock.getEntity()).thenReturn(new StringEntity(fromXmlResponse.toXml()));
		when(httpResponseMock.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null));

		//create empty request 
		PaymentRequest request = new PaymentRequest();

		//create configuration
		HttpConfiguration httpConfiguration = new HttpConfiguration();
		httpConfiguration.setOnlyAllowHttps(false);

		//mock HttpCLient instance
		HttpClient httpClientMock = mock(HttpClient.class);
		when(httpClientMock.execute(Matchers.any(HttpUriRequest.class))).thenReturn(httpResponseMock);

		//execute send on client
		RealexClient realexClient = new RealexClient(SampleXmlValidationUtils.SECRET, httpClientMock, httpConfiguration);

		PaymentResponse response = realexClient.send(request);
		checkFullResponseError(response);

	}

	/**
	 * Test sending a payment request and receiving a payment response error. 
	 */
	@Test
	public void sendWithErrorResponseInvalidCodeTest() throws ClientProtocolException, IOException {

		//get sample response XML
		File file = new File(this.getClass().getResource(PAYMENT_RESPONSE_BASIC_ERROR_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);
		PaymentResponse fromXmlResponse = new PaymentResponse().fromXml(source);
		fromXmlResponse.setResult("invalid");

		//mock HttpResponse
		HttpResponse httpResponseMock = mock(HttpResponse.class);
		when(httpResponseMock.getEntity()).thenReturn(new StringEntity(fromXmlResponse.toXml()));
		when(httpResponseMock.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null));

		//create empty request 
		PaymentRequest request = new PaymentRequest();

		//create configuration
		HttpConfiguration httpConfiguration = new HttpConfiguration();

		//mock HttpCLient instance
		HttpClient httpClientMock = mock(HttpClient.class);
		when(httpClientMock.execute(Matchers.any(HttpUriRequest.class))).thenReturn(httpResponseMock);

		//execute send on client
		RealexClient realexClient = new RealexClient(SampleXmlValidationUtils.SECRET, httpClientMock, httpConfiguration);

		boolean correctExceptionThrown = false;

		try {
			realexClient.send(request);
			Assert.fail("RealexException should have been thrown before this point.");
		} catch (RealexServerException ex) {
			Assert.fail("Incorrect exception thrown. Expected RealexException as result code is invalid.");
		} catch (RealexException ex) {
			correctExceptionThrown = true;
		}

		Assert.assertTrue("Incorrect exception thrown.", correctExceptionThrown);
	}

	/**
	 * Test receiving a response which has an invalid hash. 
	 */
	@Test(expected = RealexException.class)
	public void sendInvalidResponseHashTest() throws ClientProtocolException, IOException {
		//get sample response XML
		File file = new File(this.getClass().getResource(PAYMENT_RESPONSE_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);
		PaymentResponse fromXmlResponse = new PaymentResponse().fromXml(source);

		//add invalid hash
		fromXmlResponse.setHash("invalid hash");

		//mock HttpResponse
		HttpResponse httpResponseMock = mock(HttpResponse.class);
		when(httpResponseMock.getEntity()).thenReturn(new StringEntity(fromXmlResponse.toXml()));
		when(httpResponseMock.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null));

		//create empty request 
		PaymentRequest request = new PaymentRequest();

		//create configuration
		HttpConfiguration httpConfiguration = new HttpConfiguration();

		//mock HttpCLient instance
		HttpClient httpClientMock = mock(HttpClient.class);
		when(httpClientMock.execute(Matchers.any(HttpUriRequest.class))).thenReturn(httpResponseMock);

		//execute send on client
		RealexClient realexClient = new RealexClient(SampleXmlValidationUtils.SECRET, httpClientMock, httpConfiguration);
		realexClient.send(request);

		//shouldn't get this far
		Assert.fail("RealexException should have been thrown before this point.");
	}

	/**
	 * Test sending a ThreeDSecure Verify Enrolled request and receiving a ThreeDSecure Verify Enrolled response. 
	 */
	@Test
	public void sendThreeDSecureVerifyEnrolledTest() throws ClientProtocolException, IOException {

		//get sample response XML
		File file = new File(this.getClass().getResource(THREE_D_SECURE_VERIFY_ENROLLED_RESPONSE_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);
		ThreeDSecureResponse fromXmlResponse = new ThreeDSecureResponse().fromXml(source);

		//mock HttpResponse
		HttpResponse httpResponseMock = mock(HttpResponse.class);
		when(httpResponseMock.getEntity()).thenReturn(new StringEntity(fromXmlResponse.toXml()));
		when(httpResponseMock.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null));

		//create empty request 
		ThreeDSecureRequest request = new ThreeDSecureRequest();

		//create configuration
		HttpConfiguration httpConfiguration = new HttpConfiguration();
		httpConfiguration.setOnlyAllowHttps(false);

		//mock HttpClient instance
		HttpClient httpClientMock = mock(HttpClient.class);
		when(httpClientMock.execute(Matchers.any(HttpUriRequest.class))).thenReturn(httpResponseMock);

		//execute send on client
		RealexClient realexClient = new RealexClient(SampleXmlValidationUtils.SECRET, httpClientMock, httpConfiguration);
		ThreeDSecureResponse response = realexClient.send(request);

		//validate response
		checkUnmarshalledThreeDSecureEnrolledResponse(response);
	}

	/**
	 * Test receiving a response which has an invalid hash. 
	 */
	@Test(expected = RealexException.class)
	public void sendThreeDSecureInvalidResponseHashTest() throws ClientProtocolException, IOException {
		//get sample response XML
		File file = new File(this.getClass().getResource(THREE_D_SECURE_VERIFY_ENROLLED_RESPONSE_XML_PATH).getPath());
		StreamSource source = new StreamSource(file);
		ThreeDSecureResponse fromXmlResponse = new ThreeDSecureResponse().fromXml(source);

		//add invalid hash
		fromXmlResponse.setHash("invalid hash");

		//mock HttpResponse
		HttpResponse httpResponseMock = mock(HttpResponse.class);
		when(httpResponseMock.getEntity()).thenReturn(new StringEntity(fromXmlResponse.toXml()));
		when(httpResponseMock.getStatusLine()).thenReturn(new BasicStatusLine(HttpVersion.HTTP_1_1, HttpStatus.SC_OK, null));

		//create empty request 
		ThreeDSecureRequest request = new ThreeDSecureRequest();

		//create configuration
		HttpConfiguration httpConfiguration = new HttpConfiguration();
		httpConfiguration.setOnlyAllowHttps(false);

		//mock HttpClient instance
		HttpClient httpClientMock = mock(HttpClient.class);
		when(httpClientMock.execute(Matchers.any(HttpUriRequest.class))).thenReturn(httpResponseMock);

		//execute send on client
		RealexClient realexClient = new RealexClient(SampleXmlValidationUtils.SECRET, httpClientMock, httpConfiguration);
		realexClient.send(request);

		//shouldn't get this far
		Assert.fail("RealexException should have been thrown before this point.");
	}
}
