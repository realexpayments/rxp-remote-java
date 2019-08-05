package com.realexpayments.remote.sdk.http;

import java.io.Closeable;
import java.io.IOException;

import org.apache.http.ConnectionReuseStrategy;
import org.apache.http.HttpHeaders;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.NoConnectionReuseStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.realexpayments.remote.sdk.RealexException;

/**
 * HTTP Utils class for dealing with HTTP and actual message sending.
 * 
 * @author thomasduffy
 */
public class HttpUtils {

    private final static String HTTPS_PROTOCOL = "https";
    private final static String UTF_8 = "UTF-8";

    private final static Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * Get a default HttpClient based on the HttpConfiguration object. If required the defaults can 
     * be altered to meet the requirements of the SDK user. The default client does not use connection 
     * pooling and does not reuse connections. Timeouts for connection and socket are taken from the 
     * {@link HttpConfiguration} object.
     * 
     * @param httpConfiguration
     * @return CloseableHttpClient
     */
    public static CloseableHttpClient getDefaultClient(HttpConfiguration httpConfiguration) {

        RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(httpConfiguration.getTimeout())
                .setSocketTimeout(httpConfiguration.getTimeout()).build();

        HttpClientConnectionManager connectionManager = new BasicHttpClientConnectionManager();
        ConnectionReuseStrategy connectionResuseStrategy = new NoConnectionReuseStrategy();

        logger.debug("Creating HttpClient with simple no pooling/no connection reuse default settings.");
        CloseableHttpClient httpClient = HttpClients.custom().setDefaultRequestConfig(requestConfig).setConnectionManager(connectionManager)
                .setConnectionReuseStrategy(connectionResuseStrategy).build();
        return httpClient;
    }

    /**
     * Perform the actual send of the message, according to the HttpConfiguration, and get the response. 
     * This will also check if only HTTPS is allowed, based on the {@link HttpConfiguration}, and will 
     * throw a {@link RealexException} if HTTP is used when only HTTPS is allowed. A {@link RealexException} 
     * is also thrown if the response from Realex is not success (ie. if it's not 200 status code). 
     * 
     * 
     * @param xml
     * @param httpClient
     * @param httpConfiguration
     * @return the xml response
     */
    public static String sendMessage(String xml, HttpClient httpClient, HttpConfiguration httpConfiguration) {

        logger.debug("Setting endpoint of: " + httpConfiguration.getEndpoint());
        HttpPost httpPost = new HttpPost(httpConfiguration.getEndpoint());
        httpPost.addHeader(HttpHeaders.CONTENT_TYPE, ContentType.APPLICATION_XML.getMimeType());
        HttpResponse response = null;

        // Confirm protocol is HTTPS (ie. secure) if such is configured
        if (httpConfiguration.isOnlyAllowHttps()) {
            String scheme = httpPost.getURI().getScheme();
            if (!scheme.equalsIgnoreCase(HTTPS_PROTOCOL)) {
                logger.error("Protocol must be " + HTTPS_PROTOCOL);
                throw new RealexException("Protocol must be " + HTTPS_PROTOCOL);
            }
        } else {
            logger.warn("Allowed send message over HTTP. This should NEVER be allowed in a production environment.");
        }

        try {
            logger.debug("Setting entity in POST message.");
            httpPost.setEntity(new StringEntity(xml, UTF_8));

            logger.debug("Executing HTTP Post message to: " + httpPost.getURI());
            response = httpClient.execute(httpPost);

            logger.debug("Checking the HTTP response status code.");
            int statusCode = (response.getStatusLine().getStatusCode());
            if (statusCode != HttpStatus.SC_OK) {
                throw new RealexException("Unexpected http status code [" + statusCode + "]");
            }

            logger.debug("Converting HTTP entity (the xml response) back into a string.");
            String xmlResponse = EntityUtils.toString(response.getEntity());
            EntityUtils.consume(response.getEntity());
            return xmlResponse;
        } catch (IOException ioe) {
            // Also catches ClientProtocolException (from httpClient.execute()) and UnsupportedEncodingException (from response.getEntity()
            logger.error("Exception communicating with Realex.", ioe.getMessage());
            throw new RealexException("Exception communicating with Realex.", ioe);
        } finally {
            // Test if response Closeable
            if (response instanceof Closeable) {
                try {
                    ((Closeable) response).close();
                } catch (IOException ioe) {
                    // Ignore
                }
            }
        }
    }
}
