package com.example.paulnikonowicz.zocdocapplication.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Network {
    public String getSomething() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "TODO";
    }

    public String retrieveMovieListFromZipCode(int zip) {
        String todaysDate = todaysDate();
        String apiKey = "488kpuyjtxzat8q3qtg7sekx";
        String url = "http://data.tmsapi.com/v1.1/movies/showings";
        return "{}";
    }

    public String getUrl(String host, String startDate, String apiKey, String zip) {
        StringBuilder sb = new StringBuilder();
        sb.append("http://");
        sb.append(host);
        sb.append("?");
        sb.append("startDate=" + startDate);
        sb.append("&");
        sb.append("zip=" + zip);
        sb.append("&");
        sb.append("api_key=" + apiKey);

        String url = sb.toString();
        return url;
    }

    public String todaysDate() {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(new Date());
        return date;
    }
}
