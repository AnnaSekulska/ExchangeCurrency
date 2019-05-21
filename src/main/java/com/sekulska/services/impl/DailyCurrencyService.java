package com.sekulska.services.impl;

import com.sekulska.datacheck.DataChecker;
import com.sekulska.datacheck.PriceData;
import com.sekulska.datacheck.ResourcesNotFoundException;
import com.sekulska.services.CurrencyService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

@Service
public class DailyCurrencyService implements CurrencyService {

    @Autowired
    private DataChecker dataChecker;

    @Override
    public List<PriceData> getPriceData(String from_symbol, String to_symbol) throws IOException, JSONException {
        return createPriceDataList(convertFromResponseBody(dataChecker.getDailyPriceData(from_symbol, to_symbol)));
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

    public JSONObject convertFromResponseBody(String responseBody) throws JSONException {
        JSONObject jsonObject = new JSONObject(responseBody);
        if(jsonObject.has("Error Message")) throw new ResourcesNotFoundException("Please check other currencies");
        return jsonObject.getJSONObject("Time Series FX (Daily)");
    }

    private void sortPriceDataList(List<PriceData> priceDataList){
        priceDataList.sort(Comparator.comparing(PriceData::getDate));
    }
}

