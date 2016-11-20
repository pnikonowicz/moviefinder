package com.example.paulnikonowicz.zocdocapplication.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movies {
    public static ArrayList<Movie> create(String jsonString) throws JSONException {
        JSONArray json = new JSONArray(jsonString);
        ArrayList<Movie> movies = new ArrayList<>();

        for(int i=0; i<json.length(); i++) {
            JSONObject movieJson = json.getJSONObject(i);
            Movie movie = Movie.create(movieJson);
            movies.add(movie);
        }

        return movies;
    }
}
