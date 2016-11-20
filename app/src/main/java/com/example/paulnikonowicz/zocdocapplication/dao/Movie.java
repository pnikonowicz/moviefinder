package com.example.paulnikonowicz.zocdocapplication.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    private String rating;
    private String title;

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public static Movie create(JSONObject jsonObject) throws JSONException {
        Movie movie = new Movie();

        if(jsonObject.has("ratings")) {
            JSONArray ratings = jsonObject.getJSONArray("ratings");
            if(ratings.length()>0) {
                JSONObject rating = ratings.getJSONObject(0);
                String code = rating.getString("code");
                movie.rating = code;
            } else {
                movie.rating = "N/A";
            }
        } else {
            movie.rating = "N/A";
        }

        movie.title = jsonObject.getString("title");

        return movie;
    }
}
