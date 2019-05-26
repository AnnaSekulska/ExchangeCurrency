package com.sekulska.services.impl;

import com.sekulska.datacheck.DataChecker;
import com.sekulska.datacheck.PriceData;
import com.sekulska.exceptions.CallsLimitExhaustedException;
import com.sekulska.services.RealTimeCurrencyService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class RealTimeCurrencyServiceImpl implements RealTimeCurrencyService {

    @Autowired
    private DataChecker dataChecker;

    @Override
    public PriceData getPriceData(String from_symbol, String to_symbol) throws IOException, JSONException {
        return createPriceData(convertFromResponseBody(dataChecker.getRealTimePriceData(from_symbol, to_symbol)));
    }

    private PriceData createPriceData(JSONObject jsonObject){
        return new PriceData(jsonObject.getString("6. Last Refreshed"), Float.valueOf(jsonObject.getString("5. Exchange Rate")));
    }

    private JSONObject convertFromResponseBody(String jsonString){
        JSONObject jsonObject = new JSONObject(jsonString);
        if(jsonObject.has("Note")) throw new CallsLimitExhaustedException("Calls limit exhausted.");
        return jsonObject.getJSONObject("Realtime Currency Exchange Rate");
    }
}
