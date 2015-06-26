package com.realexpayments.remote.sdk.utils;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Source;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.realexpayments.remote.sdk.RealexException;
import com.realexpayments.remote.sdk.domain.payment.PaymentRequest;
import com.realexpayments.remote.sdk.domain.payment.PaymentResponse;
import com.realexpayments.remote.sdk.domain.threeDSecure.ThreeDSecureRequest;
import com.realexpayments.remote.sdk.domain.threeDSecure.ThreeDSecureResponse;

/**
 * XML helper class. Marshals/unmarshals XML.
 * 
 * @author markstanford
 *
 */
public class XmlUtils {

	/**
	 * {@link JAXBContext} map used for XML marshalling, unmarshalling. 
	 */
	private static final Map<MessageType, JAXBContext> JAXB_CONTEXT_MAP = new HashMap<MessageType, JAXBContext>();

	/**
	 * Enumeration for the message type.
	 */
	public enum MessageType {
		PAYMENT, THREE_D_SECURE;
	}

	/**
	 * Logger 
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(XmlUtils.class);

	static {
		try {
			//populate JAXBContext map for all message types
			JAXBContext paymentJaxbContext = JAXBContext.newInstance(PaymentRequest.class, PaymentResponse.class);
			JAXBContext threeDSecureJaxbContext = JAXBContext.newInstance(ThreeDSecureRequest.class, ThreeDSecureResponse.class);

			JAXB_CONTEXT_MAP.put(MessageType.PAYMENT, paymentJaxbContext);
			JAXB_CONTEXT_MAP.put(MessageType.THREE_D_SECURE, threeDSecureJaxbContext);

		} catch (Exception ex) {
			LOGGER.error("Error initialising JAXBContext", ex);
			throw new RealexException("Error initialising JAXBContext", ex);
		}
	}

	/**
	 * Marshals object to XML.
	 * 
	 * @param object
	 * @param messageType
	 * @return String
	 */
	public static String toXml(Object object, MessageType messageType) {

		LOGGER.debug("Marshalling domain object to XML.");

		StringWriter result = new StringWriter();

		try {
			Marshaller jaxbMarshaller = JAXB_CONTEXT_MAP.get(messageType).createMarshaller();
			jaxbMarshaller.marshal(object, result);
		} catch (Exception ex) {
			LOGGER.error("Error marshalling to XML", ex);
			throw new RealexException("Error marshalling to XML", ex);
		}

		return result.toString();
	}

	/**
	 * Unmarshals XML to request object. 
	 * 
	 * @param xml
	 * @param messageType
	 * @return Object
	 */
	public static Object fromXml(Source xml, MessageType messageType) {
		LOGGER.debug("Unmarshalling XML to domain object.");

		Object response = null;

		try {
			Unmarshaller jaxbUnmarshaller = JAXB_CONTEXT_MAP.get(messageType).createUnmarshaller();
			response = jaxbUnmarshaller.unmarshal(xml);
		} catch (JAXBException ex) {
			LOGGER.error("Error unmarshalling from XML", ex);
			throw new RealexException("Error unmarshalling from XML", ex);
		}

		return response;
	}

}
