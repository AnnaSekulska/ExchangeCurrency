package com.sekulska.services;

import com.sekulska.datacheck.impl.PriceData;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface CurrencyService {
    List<PriceData> getPriceData(Map<String, String> requestedParameters) throws IOException, JSONException;
}
