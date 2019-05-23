package com.sekulska.http;

import com.squareup.okhttp.ResponseBody;
import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;
import retrofit.http.QueryMap;
import retrofit.http.Url;

import java.util.Map;

public interface HttpCaller {
    @GET("/query")
    Call<ResponseBody> getAllData(@QueryMap Map<String, String> requestedParameters);

    @GET
    Call<ResponseBody> getActiveCurrencies(@Url String url, @Query("show_inactive") String showInactive);
}
