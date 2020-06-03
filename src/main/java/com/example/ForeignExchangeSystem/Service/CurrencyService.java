package com.example.ForeignExchangeSystem.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Map;

public interface CurrencyService {

    static final String URL_LATEST = "https://api.exchangeratesapi.io/latest";
    static String getJson() {


        try {
            URL url = new URL(URL_LATEST);

            HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();

            urlConnection.setRequestMethod("GET");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            while ((inputLine = bufferedReader.readLine()) != null) {
                System.out.println(inputLine);
                response.append(inputLine);
            }
            bufferedReader.close();
            urlConnection.disconnect();

            return response.toString();

        } catch (IOException e) {
            e.getStackTrace();
            return null;
        }
    }

     Map<String,Float> getRates();

     ArrayList<String> getCurrencies();
}
