package com.sekulska.services.impl;

import com.sekulska.datacheck.DataChecker;
import com.sekulska.datacheck.PriceData;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class RealTimeCurrencyServiceTest {

    @Mock
    private DataChecker dataChecker;

    @InjectMocks
    private RealTimeCurrencyService cut;

    private String createJsonString(){
        return "{\"Realtime Currency Exchange Rate\": {\n" +
                "        \"1. From_Currency Code\": \"USD\",\n" +
                "        \"2. From_Currency Name\": \"United States Dollar\",\n" +
                "        \"3. To_Currency Code\": \"JPY\",\n" +
                "        \"4. To_Currency Name\": \"Japanese Yen\",\n" +
                "        \"5. Exchange Rate\": \"110.16000000\",\n" +
                "        \"6. Last Refreshed\": \"2019-05-20 05:34:35\",\n" +
                "        \"7. Time Zone\": \"UTC\",\n" +
                "        \"8. Bid Price\": \"110.15000000\",\n" +
                "        \"9. Ask Price\": \"110.16000000\"\n" +
                "    }}";
    }
    @Test
    public void testCheckIfPriceDataHasCorrectStructure() throws IOException, JSONException {
        Mockito.when(dataChecker.getRealTimePriceData("test", "test")).thenReturn(createJsonString());

        PriceData priceData = cut.getPriceData("test", "test");
        assertEquals("2019-05-20 05:34:35", priceData.getDate());
        assertEquals(110.16000000f, priceData.getPrice());

    }

}