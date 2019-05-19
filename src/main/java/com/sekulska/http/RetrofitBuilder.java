package com.sekulska.http;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitBuilder {

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://www.alphavantage.co/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Retrofit getRetrofit() {
        return retrofit;
    }
}
