package com.sekulska.services.impl;

import com.sekulska.datacheck.PriceData;
import com.sekulska.model.TrendLineInfo;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TrendLineDataSelectorImplTest {

    private TrendLineDataSelectorImpl cut  = new TrendLineDataSelectorImpl();
    private String downtrend = "downtrend";
    private String uptrend = "uptrend";

    private List<PriceData> getPriceData(){
        List<PriceData> priceData = new ArrayList<>();
        priceData.add(new PriceData("1maj", 10f));
        priceData.add(new PriceData("2maj", 9f));
        priceData.add(new PriceData("3maj", 8f));
        priceData.add(new PriceData("4maj", 7f));
        priceData.add(new PriceData("5maj", 3f));
        priceData.add(new PriceData("6maj", 4f));
        priceData.add(new PriceData("7maj", 5f));
        priceData.add(new PriceData("8maj", 2f));
        priceData.add(new PriceData("9maj", 1f));
        priceData.add(new PriceData("10maj", 0f));
        return priceData;
    }

    @Test
    public void testCheckIfMethodReturnCorrectlyListOfTrendLineInfosForWindowSizeEqual3(){
        List<TrendLineInfo> trendLineInfos = cut.getTrends(getPriceData(), 3);
        assertEquals(downtrend, trendLineInfos.get(0).getDirection());
        assertEquals("1maj", trendLineInfos.get(0).getStart().getDate());
        assertEquals("7maj", trendLineInfos.get(0).getEnd().getDate());
    }
    @Test
    public void testCheckIfMethodReturnCorrectlyListOfTrendLineInfosForWindowSizeEqual2(){

        List<TrendLineInfo> trendLineInfos = cut.getTrends(getPriceData(), 2);
        assertEquals(downtrend, trendLineInfos.get(0).getDirection());
        assertEquals("1maj", trendLineInfos.get(0).getStart().getDate());
        assertEquals("6maj", trendLineInfos.get(0).getEnd().getDate());

        assertEquals(uptrend, trendLineInfos.get(1).getDirection());
        assertEquals("6maj", trendLineInfos.get(1).getStart().getDate());
        assertEquals("7maj", trendLineInfos.get(1).getEnd().getDate());

        assertEquals(downtrend, trendLineInfos.get(2).getDirection());
        assertEquals("7maj", trendLineInfos.get(2).getStart().getDate());
        assertEquals("9maj", trendLineInfos.get(2).getEnd().getDate());
    }
    @Test
    public void testCheckIfMethodReturnCorrectlyListOfTrendLineInfosForWindowSizeEqual4(){

        List<TrendLineInfo> trendLineInfos = cut.getTrends(getPriceData(), 4);
        assertEquals(downtrend, trendLineInfos.get(0).getDirection());
        assertEquals("1maj", trendLineInfos.get(0).getStart().getDate());
        assertEquals("7maj", trendLineInfos.get(0).getEnd().getDate());

    }
    @Test
    public void testCheckIfReturnedListHasCorrectlySize(){

        assertEquals(1, cut.getTrends(getPriceData(), 4).size());
        assertEquals(1, cut.getTrends(getPriceData(), 5).size());
        assertEquals(1, cut.getTrends(getPriceData(), 3).size());
        assertEquals(3, cut.getTrends(getPriceData(), 2).size());

    }

    @Test(expected = IllegalArgumentException.class)
    public void testCheckIfErrorMessageIsHandledWhenWindowIsWiderThanPriceDataListSize(){
        cut.getTrendLineInfo(getPriceData(), 100);
    }
    @Test(expected = IllegalArgumentException.class)
    public void testCheckIfErrorMessageIsHandledWhenWindowIsLessThanOne(){
        cut.getTrendLineInfo(getPriceData(), 0);
    }
}