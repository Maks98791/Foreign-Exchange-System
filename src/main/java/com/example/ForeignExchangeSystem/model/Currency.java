package com.example.ForeignExchangeSystem.model;

import java.util.Map;



public class Currency {

    private String date;
    private Map<String, Float> rates;
    private String base;


    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Map<String, Float> getRates() {
        return rates;
    }

    public Float getSingleRate(String rateKey) {
        if (rates.containsKey(rateKey)) {
            return rates.get(rateKey);
        }

        return 0.0F;
    }

    public void setRates(Map<String, Float> rates) {
        this.rates = rates;
    }
}