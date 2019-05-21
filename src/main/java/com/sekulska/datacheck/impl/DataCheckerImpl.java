package com.sekulska.datacheck.impl;

import com.sekulska.datacheck.DataChecker;
import com.sekulska.datacheck.Properties;
import com.sekulska.http.HttpCaller;
import com.squareup.okhttp.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit.Call;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class DataCheckerImpl implements DataChecker {

    @Autowired
    private HttpCaller httpCaller;
    @Autowired
    private Properties propertiesLoader;


    public String getPriceData(String from_symbol, String to_symbol) throws IOException {
        Map<String, String> requestedParam = new HashMap<>();
        requestedParam.put("from_symbol", from_symbol);
        requestedParam.put("to_symbol", to_symbol);
        requestedParam.put("apikey", propertiesLoader.getApiKey());
        requestedParam.put("function", propertiesLoader.getDailyFunction());

        Call<ResponseBody> data = httpCaller.getAllData(requestedParam);
        return data.execute().body().string();
    }

}
