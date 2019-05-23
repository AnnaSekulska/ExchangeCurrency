package com.sekulska.services;

import com.sekulska.model.ActiveCurrencyInfo;

import java.io.IOException;
import java.util.List;

public interface ActiveCurrenciesService {

    List<ActiveCurrencyInfo> getActiveCurrencies() throws IOException;
}
