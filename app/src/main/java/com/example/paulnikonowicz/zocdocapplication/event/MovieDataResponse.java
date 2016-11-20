package com.example.paulnikonowicz.zocdocapplication.event;

import com.example.paulnikonowicz.zocdocapplication.dao.Movies;

public class MovieDataResponse {
    private Movies movies;

    public MovieDataResponse(Movies movies) {
        this.movies = movies;
    }
}
