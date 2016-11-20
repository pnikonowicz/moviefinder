package com.example.paulnikonowicz.zocdocapplication.event;

import com.example.paulnikonowicz.zocdocapplication.dao.Movie;

import java.util.ArrayList;

public class MovieDataResponse {
    public ArrayList<Movie> movies;

    public MovieDataResponse(ArrayList<Movie> movies) {
        this.movies = movies;
    }

    public Movie[] getMovies() {
        return movies.toArray(new Movie[0]);
    }
}
