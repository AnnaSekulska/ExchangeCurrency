package com.sekulska.model;

import com.sekulska.datacheck.PriceData;

import java.util.List;

public class PriceDataInfo {

    private List<PriceData> priceData;
    private int totalCount;

    public PriceDataInfo(List<PriceData> priceData, int totalCount) {
        this.priceData = priceData;
        this.totalCount = totalCount;
    }

    public List<PriceData> getPriceData() {
        return priceData;
    }

    public int getTotalCount() {
        return totalCount;
    }
}
