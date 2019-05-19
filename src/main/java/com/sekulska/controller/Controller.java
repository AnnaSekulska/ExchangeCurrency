package com.sekulska.controller;

import com.sekulska.datacheck.impl.PriceData;
import com.sekulska.services.CurrencyService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Controller {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping("/checkPriceData")
    public ResponseEntity checkPriceData(
            @RequestParam(value = "from_symbol") String from_symbol,
            @RequestParam(value = "to_symbol") String to_symbol) throws IOException, JSONException {

        Map<String, String> requestedParameters = new HashMap<>();
        requestedParameters.put("function", "FX_DAILY");
        requestedParameters.put("from_symbol", from_symbol);
        requestedParameters.put("to_symbol", to_symbol);
        requestedParameters.put("apikey", "H3GW5N01LN6LF");

        List<PriceData> priceData = currencyService.getPriceData(requestedParameters);
        return ResponseEntity.ok(priceData);
    }
}
