package com.sekulska.services.impl;

import com.sekulska.datacheck.DataChecker;
import com.sekulska.datacheck.PriceData;
import com.sekulska.model.PriceDataInfo;
import com.sekulska.model.TrendLineInfo;
import com.sekulska.services.HistoricalDataParser;
import com.sekulska.services.HistoricalFilter;
import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;

@RunWith(MockitoJUnitRunner.class)
public class DailyCurrencyServiceImplTest {

    @Mock
    private DataChecker dataChecker;

    @Mock
    private HistoricalFilter filter;

    @Mock
    private HistoricalDataParser parser;

    @Mock
    private TrendLineDataSelectorImpl selector;

    @InjectMocks
    private DailyCurrencyServiceImpl cut;

    @Test
    public void testCheckGetDailyPriceReturnsFilteredResultsAndATotalCount() throws IOException, JSONException {
        String rawData = "Data_From_API";
        String fromSymbol = "USD";
        String toSymbol = "DDD";
        int range = 10;
        int step = 2;
        List<PriceData> allData = new ArrayList<>();
        allData.add(new PriceData("2019-05-17", 1.1244f));
        List<PriceData> expectedList = new ArrayList<>();
        List<TrendLineInfo> expectedTrendList = new ArrayList<>();

        Mockito.when(dataChecker.getDailyPriceData(fromSymbol, toSymbol)).thenReturn(rawData);
        Mockito.when(parser.parse(rawData)).thenReturn(allData);
        Mockito.when(filter.filter(allData, range, step)).thenReturn(expectedList);
        Mockito.when(selector.getTrendLineInfo(expectedList, range)).thenReturn(expectedTrendList);
        PriceDataInfo result = cut.getHistorical(fromSymbol, toSymbol, range, step);

        assertEquals(expectedList, result.getPriceData());
        assertEquals(allData.size(), result.getTotalCount());
    }
}