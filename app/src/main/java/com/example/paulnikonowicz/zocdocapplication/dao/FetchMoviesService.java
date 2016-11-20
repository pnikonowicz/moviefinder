package com.example.paulnikonowicz.zocdocapplication.dao;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class FetchMoviesService {
    public ArrayList<Movie> fetchMovies() throws IOException, JSONException, InterruptedException {
        Thread.sleep(5000); //simulate network delay
        String[] ratings = new String[]{"R", "G", "PG13"};
        ArrayList<Movie> movies = new ArrayList<>();
        for(int i=0; i<20; i++) {
            Movie m = new Movie();
            m.title = "Movie Title: " + i;
            m.rating = ratings[i % ratings.length];
            m.imageLink = "https://placehold.it/350x150?text=\"Movie " + i + "\"";
            m.description = "a movie about " + i + "a movie about " + i + "a movie about " + i+ "a movie about " + i+ "a movie about " + i+ "a movie about " + i+ "a movie about " + i+ "a movie about " + i+ "a movie about " + i+ "a movie about " + i+ "a movie about " + i;
            movies.add(m);
        }
        return movies;
    }
}
