package com.example.paulnikonowicz.zocdocapplication.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movies {
    private JSONArray json;

    public Movies(String jsonString) {
        try {
            json = new JSONArray(jsonString);
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCount() {
        return json.length();
    }

    public Movie get(int i) throws JSONException {
        JSONObject jsonObject = json.getJSONObject(i);
        Movie movie = new Movie(jsonObject);
        return movie;
    }
}
