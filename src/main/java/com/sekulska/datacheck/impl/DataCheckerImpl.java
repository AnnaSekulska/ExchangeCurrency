package com.sekulska.datacheck.impl;

import com.sekulska.datacheck.DataChecker;
import com.sekulska.datacheck.PropertiesLoader;
import com.sekulska.datacheck.ResourcesNotFoundException;
import com.sekulska.http.HttpCaller;
import com.squareup.okhttp.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit.Call;

import java.io.IOException;
import java.util.Map;

@Component
public class DataCheckerImpl implements DataChecker {

    @Autowired
    private HttpCaller httpCaller;

    public JSONObject getPriceData(Map<String, String> requestedParameters) throws IOException, JSONException {
        return convertFromResponseBody(getResponseBody(requestedParameters));
    }

    public String getResponseBody(Map<String, String> requestedParameters) throws IOException {
        PropertiesLoader.loadProperties(requestedParameters);
        Call<ResponseBody> data = httpCaller.getAllData(requestedParameters);
        return data.execute().body().string();
    }

    private JSONObject convertFromResponseBody(String responseBody) throws JSONException {
        JSONObject jsonObject = new JSONObject(responseBody);
        if(jsonObject.has("Error Message")) throw new ResourcesNotFoundException("Please check other currencies");
        return  jsonObject.getJSONObject("Time Series FX (Daily)");
    }


}
