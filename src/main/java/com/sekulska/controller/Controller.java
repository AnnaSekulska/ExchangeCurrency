package com.sekulska.controller;

import com.sekulska.datacheck.PropertiesLoader;
import com.sekulska.datacheck.impl.PriceData;
import com.sekulska.services.CurrencyService;
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
    private CurrencyService currencyService;

    @GetMapping("/checkPriceData")
    public ResponseEntity checkPriceData(
            @RequestParam(value = "from_symbol") String from_symbol,
            @RequestParam(value = "to_symbol") String to_symbol) throws IOException, JSONException {

        PropertiesLoader.setRequestedParameters("from_symbol", from_symbol);
        PropertiesLoader.setRequestedParameters("to_symbol", to_symbol);
        List<PriceData> priceData = currencyService.getPriceData(PropertiesLoader.getRequestedParameters());
        return ResponseEntity.ok(priceData);
    }
}
