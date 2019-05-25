package com.sekulska.model;

import com.sekulska.datacheck.PriceData;

public class TrendLineInfo {
    private PriceData start;
    private PriceData end;
    private String direction;

    public TrendLineInfo(PriceData start, PriceData end, String direction) {
        this.start = start;
        this.end = end;
        this.direction = direction;
    }

    public PriceData getStart() {
        return start;
    }

    public PriceData getEnd() {
        return end;
    }

    public String getDirection() {
        return direction;
    }
}