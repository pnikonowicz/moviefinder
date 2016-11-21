package com.example.paulnikonowicz.zocdocapplication;

import com.example.paulnikonowicz.zocdocapplication.dao.Movie;
import com.google.gson.Gson;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class MovieTest {

    private Gson gson;
    private Movie movie;

    @Before
    public void setup() {
        movie = new Movie();
        movie.description = "description";
        movie.imageLink = "imageLink";
        movie.rating = "rating";
        movie.title = "title";
        gson = new Gson();
    }

    @Test
    public void canCovertToJson() {
        String json = gson.toJson(movie);
        String expected = "{'rating':'rating','title':'title','imageLink':'imageLink','description':'description'}";
        Assert.assertEquals(expected, json.replaceAll("\"", "'"));
    }
    @Test
    public void conConvertFromJson() {
        String json = gson.toJson(movie);
        Movie returnMovie = gson.fromJson(json, Movie.class);
        Assert.assertEquals(movie.description, returnMovie.description);
        Assert.assertEquals(movie.imageLink, returnMovie.imageLink);
        Assert.assertEquals(movie.rating, returnMovie.rating);
    }
}
