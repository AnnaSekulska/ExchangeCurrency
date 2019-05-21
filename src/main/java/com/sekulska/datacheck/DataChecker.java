package com.sekulska.datacheck;


import org.json.JSONException;

import java.io.IOException;

public interface DataChecker {
    String getDailyPriceData(String from_symbol, String to_symbol) throws IOException, JSONException;
    String getRealTimePriceData(String from_symbol, String to_symbol) throws IOException;
}
