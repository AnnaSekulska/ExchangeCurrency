package com.sekulska.services;

import com.sekulska.datacheck.PriceData;
import com.sekulska.model.TrendLineInfo;

import java.util.List;

public interface TrendLineDataSelector {
    List<TrendLineInfo> getTrendLineInfo(List<PriceData> priceData, int windowSize);
}
