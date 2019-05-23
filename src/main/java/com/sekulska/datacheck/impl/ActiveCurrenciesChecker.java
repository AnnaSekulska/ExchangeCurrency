package com.sekulska.datacheck.impl;

import com.sekulska.datacheck.ApiProperties;
import com.sekulska.datacheck.CurrenciesChecker;
import com.sekulska.http.HttpCaller;
import com.sekulska.http.RetrofitBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class ActiveCurrenciesChecker implements CurrenciesChecker {

    @Autowired
    private HttpCaller httpCaller;

    @Autowired
    private ApiProperties properties;

    @Override
    public String getActiveCurrencies() throws IOException {
        return httpCaller.getActiveCurrencies
                (RetrofitBuilder.getActiveCurrenciesURL(), properties.getShowInactive()).execute().body().string();
    }
}
