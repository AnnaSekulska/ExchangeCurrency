package com.sekulska.services;

import com.sekulska.datacheck.PriceData;
import org.json.JSONException;

import java.io.IOException;

public interface RealTimeCurrencyService  {
    PriceData getPriceData(String from_symbol, String to_symbol) throws IOException, JSONException;
}
