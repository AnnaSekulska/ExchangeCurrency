package com.sekulska.services;

import com.sekulska.model.PriceDataInfo;
import org.json.JSONException;

import java.io.IOException;

public interface DailyCurrencyService {
    PriceDataInfo getHistorical(String fromSymbol, String toSymbol, int range, int step) throws IOException, JSONException;
}
