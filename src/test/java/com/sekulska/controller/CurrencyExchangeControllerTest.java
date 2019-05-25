package com.sekulska.controller;

import com.sekulska.datacheck.PriceData;
import com.sekulska.model.ActiveCurrencyInfo;
import com.sekulska.model.PriceDataInfo;
import com.sekulska.model.TrendLineInfo;
import com.sekulska.services.ActiveCurrenciesService;
import com.sekulska.services.impl.DailyCurrencyServiceImpl;
import com.sekulska.services.impl.RealTimeCurrencyService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(MockitoJUnitRunner.class)
public class CurrencyExchangeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private DailyCurrencyServiceImpl dailyCurrencyService;
    @Mock
    private RealTimeCurrencyService realTimeCurrencyService;
    @Mock
    private ActiveCurrenciesService activeCurrenciesService;

    @InjectMocks
    private CurrencyExchangeController cut;

    @Before
    public void init(){
        mockMvc = MockMvcBuilders
                .standaloneSetup(cut)
                .build();
    }

    @Test
    public void testCheckIfMethodGetActiveCurrenciesReturnsCorrectlyResponseEntity() throws Exception {
        List<ActiveCurrencyInfo> activeCurrencyInfos = new ArrayList<>();
        Mockito.when(activeCurrenciesService.getActiveCurrencies()).thenReturn(activeCurrencyInfos);

        mockMvc.perform(get("/price/currencies"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        verify(activeCurrenciesService, times(1)).getActiveCurrencies();
        verifyNoMoreInteractions(activeCurrenciesService);

    }
    @Test
    public void testCheckIfMethodCheckPriceDataReturnsCorrectlyResponseEntity() throws Exception {
        String from_symbol = "A";
        String to_symbol = "B";
        int range = 7 ;
        String stringRange = "1W";
        int step = 1;
        List<PriceData> priceData = new ArrayList<>();
        List<TrendLineInfo> trendLineInfos = new ArrayList<>();
        PriceDataInfo priceDataInfo = new PriceDataInfo(priceData, 10, trendLineInfos);

        Mockito.when(dailyCurrencyService.getHistorical(from_symbol, to_symbol,range, step)).thenReturn(priceDataInfo);

        mockMvc.perform(get("/price/historical")
                .param("from_symbol", from_symbol).param("to_symbol", to_symbol).param("range", stringRange))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        verify(dailyCurrencyService, times(1)).getHistorical(from_symbol, to_symbol, range, step);
        verifyNoMoreInteractions(activeCurrenciesService);

    }

    @Test
    public void testCheckIfMethodCheckRealTimePriceReturnsCorrectlyResponseEntity() throws Exception {
        String from_symbol = "A";
        String to_symbol = "B";
        PriceData priceData = new PriceData("1maj", 2.3f);
        Mockito.when(realTimeCurrencyService.getPriceData(from_symbol, to_symbol)).thenReturn(priceData);

        mockMvc.perform(get("/price/realtime")
                .param("from_currency", from_symbol)
                .param("to_currency", to_symbol))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
        verify(realTimeCurrencyService, times(1)).getPriceData(from_symbol, to_symbol);
        verifyNoMoreInteractions(realTimeCurrencyService);
    }

    @Test
    public void testCheckIfViewNameIsReturned(){
        assertEquals("currency_chart", cut.chart());
    }



}