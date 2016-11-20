package com.example.paulnikonowicz.zocdocapplication.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    public String rating;
    public String title;
    public String imageLink;
    public String description;

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

        if(jsonObject.has("title")) {
            movie.title = jsonObject.getString("title");
        } else {
            movie.title = "Title Missing";
        }

        if(jsonObject.has("preferredImage")) {
            JSONObject imageObject = jsonObject.getJSONObject("preferredImage");
            if(imageObject.has("uri")) {
                movie.imageLink = imageObject.getString("uri");
            } else {
                movie.imageLink = null;
            }
        } else {
            movie.imageLink = null;
        }

        if(jsonObject.has("shortDescription")) {
            movie.description = jsonObject.getString("shortDescription");
        } else {
            movie.description = "Description missing";
        }

        return movie;
    }

    public String getImageLink() {
        return imageLink;
    }

    public String getDescription() {
        return description;
    }
}
