package com.realexpayments.remote.sdk.utils;

/**
 * Created by adnans on 30/07/2015.
 */
public class CvnNumberFormatter {

    public static String printInt(Integer value) {
        String result = String.valueOf(value);
        for(int x=0, length = 3 - result.length(); x<length; x++) {
            result = "0" + result;
        }
        return result;
    }

    public static Integer parseInt(String value) {
        return Integer.valueOf(value);
    }

}
