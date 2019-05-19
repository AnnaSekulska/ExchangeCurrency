package com.sekulska.datacheck;


import org.json.JSONException;

import java.io.IOException;
import java.util.Map;

public interface DataChecker {
    String getPriceData(Map<String, String> requestedParameters) throws IOException, JSONException;
}
