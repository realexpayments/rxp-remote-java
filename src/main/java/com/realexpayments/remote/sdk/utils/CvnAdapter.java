package com.realexpayments.remote.sdk.utils;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * Created by adnans on 30/07/2015.
 */
public class CvnAdapter extends XmlAdapter<String, Integer>
{
    @Override
    public Integer unmarshal(String v) throws Exception {
        return CvnNumberFormatter.parseInt(v);
    }

    @Override
    public String marshal(Integer v) throws Exception {
        return CvnNumberFormatter.printInt(v);
    }
}
