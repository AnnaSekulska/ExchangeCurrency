package com.sekulska.controller;

import com.sekulska.datacheck.PriceData;
import com.sekulska.model.PriceDataInfo;
import com.sekulska.services.ActiveCurrenciesService;
import com.sekulska.services.impl.DailyCurrencyServiceImpl;
import com.sekulska.services.impl.RealTimeCurrencyService;
import org.json.JSONException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;

@Controller
@RequestMapping("price")
public class CurrencyExchangeController {

    @Autowired
    private DailyCurrencyServiceImpl dailyCurrencyService;
    @Autowired
    private RealTimeCurrencyService realTimeCurrencyService;
    @Autowired
    private ActiveCurrenciesService activeCurrenciesService;

    @GetMapping("/currencies")
    public ResponseEntity getActiveCurrencies() throws IOException {
        return ResponseEntity.ok(activeCurrenciesService.getActiveCurrencies());
    }
    @GetMapping("/historical")
    public ResponseEntity checkPriceData(
            @RequestParam(value = "from_symbol") String from_symbol,
            @RequestParam(value = "to_symbol") String to_symbol,
            @RequestParam(value = "range") String range) throws IOException, JSONException {
        RangeAndStep rangeAndStep = getRangeAndStep(range);
        PriceDataInfo priceDataInfo = dailyCurrencyService.getHistorical(from_symbol, to_symbol, rangeAndStep.range, rangeAndStep.step);

        return ResponseEntity.ok(priceDataInfo);
    }

    @GetMapping("/realtime")
    public ResponseEntity checkRealTimePrice( @RequestParam(value = "from_currency") String from_symbol,
                                             @RequestParam(value = "to_currency") String to_symbol) throws IOException{

        PriceData priceData = realTimeCurrencyService.getPriceData(from_symbol, to_symbol);
        return ResponseEntity.ok(priceData);

    }

    @GetMapping("/chart")
    public String chart(){
        return "currency_chart";
    }

    private RangeAndStep getRangeAndStep(String range){
        if("1W".equals(range)) return new RangeAndStep(7,1);
        if("1M".equals(range)) return new RangeAndStep(30,1);
        if("1Y".equals(range)) return new RangeAndStep(365,1);
        if("2Y".equals(range)) return new RangeAndStep(365 * 2,2);
        if("5Y".equals(range)) return new RangeAndStep(365 * 5,5);
        if("10Y".equals(range)) return new RangeAndStep(365 * 10, 10);
        else throw new IllegalArgumentException("Invalid range " + range);
    }

    private class RangeAndStep{
        private int range;
        private int step;

        private RangeAndStep(int range, int step) {
            this.range = range;
            this.step = step;
        }
    }
}
