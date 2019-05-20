package com.sekulska.services.impl;

import com.sekulska.datacheck.DataChecker;
import com.sekulska.datacheck.PriceData;
import com.sekulska.services.CurrencyService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

@Service
public class RealTimeCurrencyService implements CurrencyService<PriceData> {

    @Autowired
    private DataChecker dataChecker;


    @Override
    public PriceData getPriceData(Map<String, String> requestedParameters) throws IOException, JSONException {
        return createPriceData(convertFromResponseBody(dataChecker.getPriceData(requestedParameters)));
    }

    private PriceData createPriceData(JSONObject jsonObject){
        return new PriceData(jsonObject.getString("6. Last Refreshed"), jsonObject.getString("5. Exchange Rate"));
    }

    private JSONObject convertFromResponseBody(String jsonString){
        JSONObject jsonObject = new JSONObject(jsonString);
        return jsonObject.getJSONObject("Realtime Currency Exchange Rate");
    }
}
