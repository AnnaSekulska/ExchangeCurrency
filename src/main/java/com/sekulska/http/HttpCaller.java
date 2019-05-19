package com.sekulska.http;

import com.squareup.okhttp.ResponseBody;
import retrofit.Call;
import retrofit.http.GET;

public interface HttpCaller {
    @GET("/query?function=FX_DAILY&from_symbol=EUR&to_symbol=USD&interval=5min&apikey=<api_key>")
    Call<ResponseBody> getAllData();

}
