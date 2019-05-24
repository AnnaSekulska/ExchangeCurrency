package com.sekulska.services.impl;

import com.sekulska.datacheck.PriceData;
import com.sekulska.datacheck.ResourcesNotFoundException;
import org.json.JSONException;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertEquals;

public class HistoricalDataParserImplTest {

    private HistoricalDataParserImpl cut =  new HistoricalDataParserImpl();

    private String createJsonString() {

        return " {\"Time Series FX (Daily)\": {\n" +
                "        \"2019-05-16\": {\n" +
                "            \"1. open\": \"1.1165\",\n" +
                "            \"2. high\": \"1.1169\",\n" +
                "            \"3. low\": \"1.1154\",\n" +
                "            \"4. close\": \"1.1158\"\n" +
                "        },\n" +
                "        \"2019-05-18\": {\n" +
                "            \"1. open\": \"1.1174\",\n" +
                "            \"2. high\": \"1.1184\",\n" +
                "            \"3. low\": \"1.1154\",\n" +
                "            \"4. close\": \"1.1158\"\n" +
                "        },\n" +
                "        \"2019-05-17\": {\n" +
                "            \"1. open\": \"1.1206\",\n" +
                "            \"2. high\": \"1.1224\",\n" +
                "            \"3. low\": \"1.1164\",\n" +
                "            \"4. close\": \"1.1174\"\n" +
                "        }}}";
    }

    private String createErrorJsonString() {
        return " {\"Error Message\": \"message\"}";
    }

    @Test
    public void testCheckIfParserReturnedListWithCorrectData() throws JSONException {

        List<PriceData> priceData = cut.parse(createJsonString());

        assertEquals("2019-05-16", priceData.get(0).getDate());
        assertEquals(1.1165f, priceData.get(0).getPrice());

        assertEquals("2019-05-17", priceData.get(1).getDate());
        assertEquals(1.1206f, priceData.get(1).getPrice());

        assertEquals("2019-05-18", priceData.get(2).getDate());
        assertEquals(1.1174f, priceData.get(2).getPrice());

    }

    @Test
    public void testCheckIfReturnedListHasCorrectlySize() throws JSONException {
        List<PriceData> priceData = cut.parse(createJsonString());
        assertEquals(3, priceData.size());
    }

        @Test
    public void testCheckIfReturnedListIsSortedAscending() throws JSONException {

        List<PriceData> priceDataList = cut.parse(createJsonString());

        assertEquals("2019-05-16", priceDataList.get(0).getDate());

        assertEquals("2019-05-17", priceDataList.get(1).getDate());

        assertEquals("2019-05-18", priceDataList.get(2).getDate());
    }

    @Test(expected = ResourcesNotFoundException.class)
    public void testCheckIfErrorMessageIsHandled() throws JSONException {
        cut.parse(createErrorJsonString());
    }
}