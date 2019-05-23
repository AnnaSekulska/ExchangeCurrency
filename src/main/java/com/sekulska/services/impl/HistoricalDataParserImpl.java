package com.sekulska.services.impl;

import com.sekulska.datacheck.PriceData;
import com.sekulska.datacheck.ResourcesNotFoundException;
import com.sekulska.services.HistoricalDataParser;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Component
public class HistoricalDataParserImpl implements HistoricalDataParser {

    @Override
    public List<PriceData> parse(String input) throws JSONException {
        return createPriceDataList(convertFromResponseBody(input));
    }

    private List<PriceData> createPriceDataList(JSONObject jsonChildObject) {
        List<PriceData> priceDataList = new ArrayList<>();
        Iterator iterator = jsonChildObject.keys();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = jsonChildObject.getJSONObject(key).getString("1. open");
            priceDataList.add(new PriceData(key, value));
        }
        sortPriceDataList(priceDataList);
        return priceDataList;
    }

    private JSONObject convertFromResponseBody(String responseBody) throws JSONException {
        JSONObject jsonObject = new JSONObject(responseBody);
        if(jsonObject.has("Error Message")) throw new ResourcesNotFoundException("Please check other currencies");
        return jsonObject.getJSONObject("Time Series FX (Daily)");
    }

    private void sortPriceDataList(List<PriceData> priceDataList){
        priceDataList.sort(Comparator.comparing(PriceData::getDate));
    }
}