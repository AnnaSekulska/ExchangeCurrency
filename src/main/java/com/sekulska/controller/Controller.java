package com.sekulska.controller;

import com.sekulska.datacheck.PriceData;
import com.sekulska.services.impl.DailyCurrencyServiceImpl;
import com.sekulska.services.impl.RealTimeCurrencyService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("price")
public class Controller {

    @Autowired
    private DailyCurrencyServiceImpl dailyCurrencyService;
    @Autowired
    private RealTimeCurrencyService realTimeCurrencyService;

    @GetMapping("/historical")
    public ResponseEntity checkPriceData(
            @RequestParam(value = "from_symbol") String from_symbol,
            @RequestParam(value = "to_symbol") String to_symbol,
            @RequestParam(value = "range") int range,
            @RequestParam(value = "step") int step) throws IOException, JSONException {

        List<PriceData> priceData = dailyCurrencyService.getHistorical(from_symbol, to_symbol, range, step);
        return ResponseEntity.ok(priceData);
    }

    @GetMapping("/realtime")
    public ResponseEntity checkRealTimePrice( @RequestParam(value = "from_currency") String from_symbol,
                                             @RequestParam(value = "to_currency") String to_symbol) throws IOException{

        PriceData priceData = realTimeCurrencyService.getPriceData(from_symbol, to_symbol);
        return ResponseEntity.ok(priceData);

    }
}
