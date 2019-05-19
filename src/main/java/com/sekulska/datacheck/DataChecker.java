package com.sekulska.datacheck;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

public interface DataChecker {
    JSONObject getPriceData(Map<String, String> requestedParameters) throws IOException, JSONException;
}
