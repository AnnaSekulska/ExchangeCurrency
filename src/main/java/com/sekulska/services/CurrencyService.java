package com.sekulska.services;

import org.json.JSONException;

import java.io.IOException;
import java.util.Map;

public interface CurrencyService <T> {
    T getPriceData(Map<String, String> requestedParameters) throws IOException, JSONException;
}
