package com.example.paulnikonowicz.zocdocapplication.dao;

import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Network {
    public String retrieveMovieListFromZipCode(int zip) throws IOException {
        String todaysDate = todaysDate();
        String apiKey = "488kpuyjtxzat8q3qtg7sekx";
        String host = "data.tmsapi.com/v1.1/movies/showings";
        String urlString = getUrl(host, todaysDate, apiKey, zip+"");

        URL url = new URL(urlString);

        HttpURLConnection urlConnection = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            String json = IOUtils.toString(in);
            return json;
        } finally {
            if(urlConnection!=null)
                urlConnection.disconnect();
        }
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
