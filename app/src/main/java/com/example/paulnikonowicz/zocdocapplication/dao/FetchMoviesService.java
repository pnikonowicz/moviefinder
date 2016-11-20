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
        String json = network.retrieveMovieListFromZipCode(zipCode);
        ArrayList<Movie> movies = Movies.create(json);
        return movies;
    }
}
