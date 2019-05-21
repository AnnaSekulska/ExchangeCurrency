package com.sekulska.services;

import org.json.JSONException;

import java.io.IOException;

public interface CurrencyService <T> {
    T getPriceData(String from_symbol, String to_symbol) throws IOException, JSONException;
}
