package com.example.paulnikonowicz.zocdocapplication.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Movies {
    private int length;
    private ArrayList<Movie> movies;

    public static Movies create(String jsonString) throws JSONException {
        JSONArray json = new JSONArray(jsonString);
        Movies movies = new Movies();

        movies.length = json.length();
        movies.movies = new ArrayList<Movie>();

        for(int i=0; i<json.length(); i++) {
            JSONObject movieJson = json.getJSONObject(i);
            Movie movie = Movie.create(movieJson);
            movies.movies.add(movie);
        }

        return movies;
    }

    public int getCount() {
        return length;
    }

    public Movie get(int i) {
        return movies.get(i);
    }
}
