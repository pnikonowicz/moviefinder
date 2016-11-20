package com.example.paulnikonowicz.zocdocapplication.dao;

import org.json.JSONException;

import java.io.IOException;
import java.util.ArrayList;

public class FetchMoviesService {
    private Network network;

    public FetchMoviesService() {
        this(new Network());
    }

    public FetchMoviesService(Network network) {
        this.network = network;
    }

    public ArrayList<Movie> fetchMovies(int zipCode) throws IOException, JSONException {
        //because of limit issues with the CDN, just ignore this and manually load some movies up
//        try {
//            json = network.retrieveMovieListFromZipCode(zipCode);
//        } catch(Exception e) {
//            //because of limit issues with the CDN, just ignore this and manually load some movies up
//        }

        ArrayList<Movie> movies = new ArrayList<>();
        for(int i=0; i<20; i++) {
            Movie m = new Movie();
            m.title = "Movie Title: " + i;
            m.rating = "R";
            m.imageLink = "https://placehold.it/350x150?text=\"Movie " + i + "\"";
            m.description = "a movie about " + i;
            movies.add(m);
        }
        return movies;
    }
}
