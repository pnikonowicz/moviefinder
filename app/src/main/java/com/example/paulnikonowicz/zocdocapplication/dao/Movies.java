package com.example.paulnikonowicz.zocdocapplication.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Movies {
    private JSONArray json;

    public Movies(String jsonString) {
        try {
            json = new JSONArray(new JSONObject(jsonString));
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }

    public int getCount() {
        return json.length();
    }
}
