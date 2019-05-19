package com.sekulska.datacheck;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public interface DataChecker {
    JSONObject getPriceData() throws IOException, JSONException;
}
