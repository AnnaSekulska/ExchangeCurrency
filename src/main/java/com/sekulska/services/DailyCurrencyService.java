package com.sekulska.services;

import com.sekulska.datacheck.PriceData;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface DailyCurrencyService {
    List<PriceData> getHistorical(String fromSymbol, String toSymbol, int range, int step) throws IOException, JSONException;
}
