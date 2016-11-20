package com.example.paulnikonowicz.zocdocapplication.dao;

import org.json.JSONException;

import java.io.IOException;

public class FetchMoviesService {
    private Network network;

    public FetchMoviesService() {
        this(new Network());
    }

    public FetchMoviesService(Network network) {
        this.network = network;
    }

    public Movies fetchMovies(int zipCode) throws IOException, JSONException {
        String json = network.retrieveMovieListFromZipCode(zipCode);
        Movies movies = Movies.create(json);
        return movies;
    }
}
