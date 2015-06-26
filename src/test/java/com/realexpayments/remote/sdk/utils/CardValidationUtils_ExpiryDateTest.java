package com.realexpayments.remote.sdk.utils;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import com.realexpayments.remote.sdk.utils.CardValidationUtils;

@RunWith(Parameterized.class)
public class CardValidationUtils_ExpiryDateTest {

	private final String message;
	private final String expiryDate;
	private final boolean expectedResult;

	@Parameterized.Parameters
	public static Collection testData() {

		Object[][] testCases = new Object[][] {
				{ "Correct format MMYY", "1220", true },
				{ "Correct format MM-YY", "12-20", false },
				{ "Correct format MM/YY", "12/20", false },
				{ "Correct format MM YY", "12 20", false },
				{ "Incorrect format MM\\YY", "12\\20", false },
				{ "Incorrect format AABB", "AABB", false },
				{ "Correct future date MMYY", "1221", true },
				{ "Incorrect date MMYY", "1221", true },
				{ "Incorrect date MMYY", "1321", false },
				{ "Incorrect date MMYY", "1212", false },
				{ "Incorrect date MMYY", "0015", false },
				{ "Incorrect date MMYY", "0415", false },
				{ "Correct date MMYY", "1215", true },
				{ "Incorrect date MMYY", "0021", false },
		};
		return Arrays.asList(testCases);
	}

	public CardValidationUtils_ExpiryDateTest(String message, String expiryDate, boolean expectedResult) {
		this.message = message;
		this.expiryDate = expiryDate;
		this.expectedResult = expectedResult;
	}

	@Test
	public void testExpiryDateFormats() {
		boolean result = CardValidationUtils.performExpiryDateCheck(expiryDate);
		Assert.assertEquals(message + " : " + expiryDate, expectedResult, result);
	}
}
