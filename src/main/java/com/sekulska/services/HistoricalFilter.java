package com.sekulska.services;

import com.sekulska.datacheck.PriceData;

import java.util.List;

public interface HistoricalFilter {
    List<PriceData> filter(List<PriceData> input, int range, int step);
}
