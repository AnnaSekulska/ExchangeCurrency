package com.sekulska.services;

import com.sekulska.datacheck.PriceData;
import org.json.JSONException;

import java.util.List;

public interface HistoricalDataParser {
    List<PriceData> parse(String input) throws JSONException;
}
