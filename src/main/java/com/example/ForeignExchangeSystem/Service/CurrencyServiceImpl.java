package com.example.ForeignExchangeSystem.Service;


import com.example.ForeignExchangeSystem.model.Currency;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

@Service
public class CurrencyServiceImpl implements CurrencyService{


    @Override
    public Map<String, Float> getRates() {

        ObjectMapper mapper = new ObjectMapper();

        Currency currency = new Currency();

        String jsonStr = CurrencyService.getJson();

        try {
            currency = mapper.readValue(jsonStr, Currency.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Map<String,Float> rates = currency.getRates();

        return rates;
    }

    @Override
    public ArrayList<String> getCurrencies() {
        ObjectMapper mapper = new ObjectMapper();

        Currency currency = new Currency();

        String jsonStr = CurrencyService.getJson();

        try {
            currency = mapper.readValue(jsonStr, Currency.class);
        } catch (IOException e) {
            e.printStackTrace();
        }


        Map<String,Float> rates = currency.getRates();

        ArrayList<String> currencies = new ArrayList<>();

        Iterator<Map.Entry<String, Float>> it = rates.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String,Float> pair = it.next();
            currencies.add(pair.getKey());

        }

        return currencies;
    }
}
