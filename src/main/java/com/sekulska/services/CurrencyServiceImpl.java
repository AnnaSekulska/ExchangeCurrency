package com.sekulska.services;

import com.sekulska.datacheck.DataChecker;
import com.sekulska.datacheck.impl.PriceData;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    private DataChecker dataChecker;

    @Override
    public List<PriceData> getPriceData() throws IOException, JSONException {
        JSONObject j = dataChecker.getPriceData();
        List<PriceData> l = createPriceDataList(j);
        return createPriceDataList(j);
    }

    private List<PriceData> createPriceDataList(JSONObject jsonChildObject) {
        List<PriceData> priceDataList = new ArrayList<>();
        Iterator iterator = jsonChildObject.keys();

        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            String value = jsonChildObject.getJSONObject(key).getString("1. open");
            priceDataList.add(new PriceData(key, value));
        }
        return priceDataList;
    }
}

