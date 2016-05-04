package com.realexpayments.remote.sdk.utils;

import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CardValidationUtilsTest {

	private static final String VALID_CARD_NUM = "4242424242424242";
	private static final String INVALID_CARD_NUM = "1234567890123456";
	private static final String VALID_CARD_NUM_WITH_SPACES = "4242 4242 4242 4242";
	private static final String INVALID_CARD_NUM_WITH_SPACES = "1234 5678 9012 3456";
	private static final String ALPHA_STRING = "abcdefghijklmop";
	private static final String EMPTY_STRING = "";

	@Test
	public void testValidCardNumber() {
		boolean cardIsValid = CardValidationUtils.performLuhnCheck(VALID_CARD_NUM);
		assertTrue("Test Valid Card Number " + VALID_CARD_NUM, cardIsValid);
	}

	@Test
	public void testInvalidCardNumber() {
		boolean cardIsValid = CardValidationUtils.performLuhnCheck(INVALID_CARD_NUM);
		assertFalse("Test Invalid Card Number " + INVALID_CARD_NUM, cardIsValid);
	}

	@Test
	public void testValidCardNumberWithSpaces() {
		boolean cardIsValid = CardValidationUtils.performLuhnCheck(VALID_CARD_NUM_WITH_SPACES);
		assertFalse("Test Valid Card Number " + VALID_CARD_NUM_WITH_SPACES, cardIsValid);
	}

	@Test
	public void testInvalidCardNumberWithSpaces() {
		boolean cardIsValid = CardValidationUtils.performLuhnCheck(INVALID_CARD_NUM_WITH_SPACES);
		assertFalse("Test Invalid Card Number " + INVALID_CARD_NUM_WITH_SPACES, cardIsValid);
	}

	@Test
	public void testAlphaStringAsCardNumber() {
		boolean cardIsValid = CardValidationUtils.performLuhnCheck(ALPHA_STRING);
		assertFalse("Test Invalid Card Number " + ALPHA_STRING, cardIsValid);
	}

	@Test
	public void testEmptyStringAsCardNumber() {
		boolean cardIsValid = CardValidationUtils.performLuhnCheck(EMPTY_STRING);
		assertFalse("Test Invalid Card Number " + EMPTY_STRING, cardIsValid);
	}

	@Test
	public void testValidAmexCvv() {
		String cvvNumber = "1234";
		String cardType = "AMEX";

		boolean cvvIsValid = CardValidationUtils.performCvvCheck(cvvNumber, cardType);
		assertTrue("Testing valid " + cardType + " card type with CVV number " + cvvNumber + EMPTY_STRING, cvvIsValid);
	}

	@Test
	public void testValidAmexLowerCaseCvv() {
		String cvvNumber = "1234";
		String cardType = "amex";

		boolean cvvIsValid = CardValidationUtils.performCvvCheck(cvvNumber, cardType);
		assertTrue("Testing valid " + cardType + " card type with CVV number " + cvvNumber + EMPTY_STRING, cvvIsValid);
	}

	@Test
	public void testInvalidAmexCvv() {
		String cvvNumber = "12345";
		String cardType = "AMEX";

		boolean cvvIsValid = CardValidationUtils.performCvvCheck(cvvNumber, cardType);
		assertFalse("Testing invalid " + cardType + " card type with CVV number " + cvvNumber + EMPTY_STRING, cvvIsValid);
	}

	@Test
	public void testValidVisaCvv() {
		String cvvNumber = "123";
		String cardType = "VISA";

		boolean cvvIsValid = CardValidationUtils.performCvvCheck(cvvNumber, cardType);
		assertTrue("Testing valid " + cardType + " card type with CVV number " + cvvNumber + EMPTY_STRING, cvvIsValid);
	}

	@Test
	public void testInvalidVisaCvv() {
		String cvvNumber = "1234";
		String cardType = "VISA";

		boolean cvvIsValid = CardValidationUtils.performCvvCheck(cvvNumber, cardType);
		assertFalse("Testing valid " + cardType + " card type with CVV number " + cvvNumber + EMPTY_STRING, cvvIsValid);
	}

	@Test
	public void testValidExpiryDateCurrentMonthThisYear() {

		String message = "Correct date MMYY - this month";
		int month = ((Calendar.getInstance().get(Calendar.MONTH)) + 1); // Current month
		int year = (Calendar.getInstance().get(Calendar.YEAR));
		String mm = (month < 10 ? ("0".concat(Integer.toString(month))) : Integer.toString(month));
		String yy = Integer.toString(year).substring(2);
		String expiryDate = mm + yy;
		boolean expectedResult = true;

		boolean result = CardValidationUtils.performExpiryDateCheck(expiryDate);
		Assert.assertEquals(message + " : " + expiryDate, expectedResult, result);
	}

	@Test
	public void testValidExpiryDateFutureMonthThisYear() {

		String message = "Correct date MMYY - this month";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, 1); // Move to next month (if December will be next year but will have to live with that
		int month = ((cal.get(Calendar.MONTH)) + 1);
		int year = (cal.get(Calendar.YEAR));
		String mm = (month < 10 ? ("0".concat(Integer.toString(month))) : Integer.toString(month));
		String yy = Integer.toString(year).substring(2);
		String expiryDate = mm + yy;
		boolean expectedResult = true;

		boolean result = CardValidationUtils.performExpiryDateCheck(expiryDate);
		Assert.assertEquals(message + " : " + expiryDate, expectedResult, result);
	}

	@Test
	public void testValidExpiryDatePastMonthThisYear() {

		String message = "Correct date MMYY - this month";
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.MONTH, -1); // Move to last month (if January will be last year but will have to live with that
		int month = ((cal.get(Calendar.MONTH)) + 1);
		int year = (cal.get(Calendar.YEAR));
		if (month == 1)
		{
			year++;
		}

		String mm = (month < 10 ? ("0".concat(Integer.toString(month))) : Integer.toString(month));
		String yy = Integer.toString(year).substring(2);
		String expiryDate = mm + yy;
		boolean expectedResult = false;

		boolean result = CardValidationUtils.performExpiryDateCheck(expiryDate);
		Assert.assertEquals(message + " : " + expiryDate, expectedResult, result);
	}
}
