package com.sekulska.model;

public class TrendLineInfo {
    private String startDate;
    private String endDate;
    private String direction;

    public TrendLineInfo(String startDate, String endDate, String direction) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.direction = direction;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public String getDirection() {
        return direction;
    }
}
