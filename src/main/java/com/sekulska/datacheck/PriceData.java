package com.sekulska.datacheck;

import java.util.Objects;

public class PriceData {

    private String date;
    private float price;

    public PriceData(String date, float price) {
        this.date = date;
        this.price = price;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PriceData priceData = (PriceData) o;
        return Float.compare(priceData.price, price) == 0 &&
                Objects.equals(date, priceData.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, price);
    }
}
