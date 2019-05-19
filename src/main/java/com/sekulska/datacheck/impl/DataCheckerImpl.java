package com.sekulska.datacheck.impl;

import com.sekulska.datacheck.DataChecker;
import com.sekulska.datacheck.PropertiesLoader;
import com.sekulska.http.HttpCaller;
import com.squareup.okhttp.ResponseBody;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit.Call;

import java.io.IOException;
import java.util.Map;

@Component
public class DataCheckerImpl implements DataChecker {

    @Autowired
    private HttpCaller httpCaller;

    public String getPriceData(Map<String, String> requestedParameters) throws IOException, JSONException {
        return getResponseBody(requestedParameters);
    }

    public String getResponseBody(Map<String, String> requestedParameters) throws IOException {
        PropertiesLoader.loadProperties(requestedParameters);
        Call<ResponseBody> data = httpCaller.getAllData(requestedParameters);
        return data.execute().body().string();
    }

}
