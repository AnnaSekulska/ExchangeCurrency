package com.sekulska.model;

import java.util.Objects;

public class ActiveCurrencyInfo {

    private String shortName;
    private String fullName;

    public ActiveCurrencyInfo(String shortName, String fullName) {
        this.shortName = shortName;
        this.fullName = fullName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getFullName() {
        return fullName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ActiveCurrencyInfo that = (ActiveCurrencyInfo) o;
        return Objects.equals(shortName, that.shortName) &&
                Objects.equals(fullName, that.fullName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(shortName, fullName);
    }
}
