package com.sekulska.datacheck;

public class PriceData {

    private String date;
    private String price;

    public PriceData(String date, String price) {
        this.date = date;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

}
