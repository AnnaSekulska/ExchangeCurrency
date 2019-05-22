package com.sekulska.services.impl;

import com.sekulska.datacheck.DataChecker;
import com.sekulska.datacheck.PriceData;
import com.sekulska.services.DailyCurrencyService;
import com.sekulska.services.HistoricalDataParser;
import com.sekulska.services.HistoricalFilter;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class DailyCurrencyServiceImpl implements DailyCurrencyService {

    @Autowired
    private DataChecker dataChecker;

    @Autowired
    private HistoricalFilter filter;

    @Autowired
    private HistoricalDataParser parser;


    @Override
    public List<PriceData> getHistorical(String fromSymbol, String toSymbol, int range, int step) throws IOException, JSONException {
        return filter.filter(
                    parser.parse(
                        dataChecker.getDailyPriceData(fromSymbol, toSymbol)
                    ),
                range,
                step);
    }
}

