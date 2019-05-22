package com.sekulska.datacheck;

import java.util.Objects;

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

    @Override
    public boolean equals(Object otherObject) {
        if (this == otherObject) {
            return true;
        }
        if (otherObject instanceof PriceData) {
            PriceData that = (PriceData) otherObject;
            return  price.equals(that.price) &&
                  date.equals(that.date);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, price);
    }
}
