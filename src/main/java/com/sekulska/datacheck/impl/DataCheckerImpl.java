package com.sekulska.datacheck.impl;

import com.sekulska.datacheck.DataChecker;
import com.sekulska.http.HttpCaller;
import com.squareup.okhttp.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import retrofit.Call;

import java.io.IOException;

@Component
public class DataCheckerImpl implements DataChecker {

    @Autowired
    private HttpCaller httpCaller;

    public JSONObject getPriceData() throws IOException, JSONException {
        return convertFromResponseBody(getResponseBody());
    }

    public String getResponseBody() throws IOException {
        Call<ResponseBody> data = httpCaller.getAllData();
        return data.execute().body().string();
    }

    private JSONObject convertFromResponseBody(String responseBody) throws JSONException {
        JSONObject jsonObject =new JSONObject(responseBody);
        return  jsonObject.getJSONObject("Time Series FX (Daily)");
    }
}
