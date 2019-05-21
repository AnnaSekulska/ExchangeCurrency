package com.sekulska.datacheck.impl;

import com.sekulska.datacheck.DataChecker;
import com.sekulska.datacheck.ApiProperties;
import com.sekulska.http.HttpCaller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataCheckerImpl implements DataChecker {

    @Autowired
    private HttpCaller httpCaller;
    @Autowired
    private ApiProperties properties;

    @Override
    public String getDailyPriceData(String from_symbol, String to_symbol) throws IOException {
        Map<String, String> requestedParam = new HashMap<>();
        requestedParam.put("function", properties.getDailyFunction());
        requestedParam.put("apikey", properties.getApiKey());
        requestedParam.put("outputsize", properties.getOutputSize());
        requestedParam.put("from_symbol", from_symbol);
        requestedParam.put("to_symbol", to_symbol);

        return httpCaller.getAllData(requestedParam).execute().body().string();
    }

    @Override
    public String getRealTimePriceData(String from_symbol, String to_symbol) throws IOException {
        Map<String, String> requestedParam = new HashMap<>();
        requestedParam.put("function", properties.getRealTimeFunction());
        requestedParam.put("apikey", properties.getApiKey());
        requestedParam.put("from_currency", from_symbol);
        requestedParam.put("to_currency", to_symbol);

        return httpCaller.getAllData(requestedParam).execute().body().string();
    }
}
