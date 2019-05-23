package com.sekulska.http;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;

public class RetrofitBuilder {

    private final static String baseURL = "https://www.alphavantage.co/";
    private final static String activeCurrenciesURL = "https://openexchangerates.org/api/currencies.json";

    private Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    public Retrofit getRetrofit() {
        return retrofit;
    }

    public static String getActiveCurrenciesURL() {
        return activeCurrenciesURL;
    }
}
