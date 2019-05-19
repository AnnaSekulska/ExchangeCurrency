package com.sekulska.services;

import com.sekulska.datacheck.impl.PriceData;
import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public interface CurrencyService {
    List<PriceData> getPriceData() throws IOException, JSONException;
}
