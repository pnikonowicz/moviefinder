package com.example.paulnikonowicz.zocdocapplication.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movie {
    private JSONObject jsonObject;

    public Movie(JSONObject jsonObject) {
        this.jsonObject = jsonObject;
    }

    public String getTitle() throws JSONException {
        return jsonObject.getString("title");
    }

    public String getRating() throws JSONException {
        JSONArray ratings = jsonObject.getJSONArray("ratings");
        if(ratings.length()>0) {
            JSONObject rating = ratings.getJSONObject(0);
            String code = rating.getString("code");
            return code;
        } else {
            return "N/A";
        }
    }
}
