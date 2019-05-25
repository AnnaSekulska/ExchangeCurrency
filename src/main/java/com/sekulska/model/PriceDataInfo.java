package com.sekulska.model;

import com.sekulska.datacheck.PriceData;

import java.util.List;

public class PriceDataInfo {

    private List<PriceData> priceData;
    private int totalCount;
    private List<TrendLineInfo> trendLineInfos;

    public PriceDataInfo(List<PriceData> priceData, int totalCount, List<TrendLineInfo> trendLineInfos) {
        this.priceData = priceData;
        this.totalCount = totalCount;
        this.trendLineInfos = trendLineInfos;
    }

    public List<PriceData> getPriceData() {
        return priceData;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public List<TrendLineInfo> getTrendLineInfos() {
        return trendLineInfos;
    }
}
