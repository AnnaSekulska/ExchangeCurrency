package com.sekulska.datacheck.impl;

public class PriceData {

    private String date;
    private String open;

    public PriceData(String date, String open) {
        this.date = date;
        this.open = open;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

}
