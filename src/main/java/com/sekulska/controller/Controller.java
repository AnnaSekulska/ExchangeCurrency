package com.sekulska.controller;

import com.sekulska.datacheck.impl.PriceData;
import com.sekulska.services.CurrencyService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class Controller {

    @Autowired
    private CurrencyService currencyService;


    @GetMapping("/checkPriceData")
    public ResponseEntity checkPriceData() throws IOException, JSONException {
        List<PriceData> priceData = currencyService.getPriceData();
       return ResponseEntity.ok(priceData);
    }
}
