package com.sekulska.http;

import com.squareup.okhttp.ResponseBody;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.QueryMap;

import java.util.Map;

public interface HttpCaller {
    @GET("/query")
    Call<ResponseBody> getAllData(@QueryMap Map<String, String> requestedParameters);
}
