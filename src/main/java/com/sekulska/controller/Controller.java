package com.sekulska.controller;

import com.sekulska.datacheck.PriceData;
import com.sekulska.datacheck.PropertiesLoader;
import com.sekulska.services.impl.DailyCurrencyService;
import com.sekulska.services.impl.RealTimeCurrencyService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private DailyCurrencyService dailyCurrencyService;
    @Autowired
    private RealTimeCurrencyService realTimeCurrencyService;

    @GetMapping("/checkPriceData")
    public ResponseEntity checkPriceData(
            @RequestParam(value = "from_symbol") String from_symbol,
            @RequestParam(value = "to_symbol") String to_symbol) throws IOException, JSONException {
        PropertiesLoader.setRequestedParamFxDaily("from_symbol", from_symbol);
        PropertiesLoader.setRequestedParamFxDaily("to_symbol", to_symbol);

        List<PriceData> priceData = dailyCurrencyService.getPriceData(PropertiesLoader.getRequestedParamFxDaily());
        return ResponseEntity.ok(priceData);
    }

    @GetMapping("/checkRealTimePriceData")
    public ResponseEntity checkRealTimePrice( @RequestParam(value = "from_currency") String from_symbol,
                                             @RequestParam(value = "to_currency") String to_symbol) throws IOException{
        PropertiesLoader.setRequestedParamRealTime("from_currency", from_symbol);
        PropertiesLoader.setRequestedParamRealTime("to_currency", to_symbol);
        PriceData priceData = realTimeCurrencyService.getPriceData(PropertiesLoader.getRequestedParamRealTime());
        return ResponseEntity.ok(priceData);

    }
}
