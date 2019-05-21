package com.sekulska.datacheck;


import org.json.JSONException;

import java.io.IOException;

public interface DataChecker {
    String getPriceData(String from_symbol, String to_symbol) throws IOException, JSONException;
}
