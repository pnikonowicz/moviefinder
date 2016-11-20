package com.example.paulnikonowicz.zocdocapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.paulnikonowicz.zocdocapplication.dao.MovieEndpoint;
import com.example.paulnikonowicz.zocdocapplication.dao.Movies;
import com.example.paulnikonowicz.zocdocapplication.event.RequestMovieData;
import com.example.paulnikonowicz.zocdocapplication.event.StatusEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends FragmentActivity {
    private MovieEndpoint movieEndpoint;

    public MainActivity() {
        this(new MovieEndpoint());
    }

    public MainActivity(MovieEndpoint movieEndpoint) {
        this.movieEndpoint = movieEndpoint;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new RequestMovieData());
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onInitialize(RequestMovieData event) {
        EventBus.getDefault().post(new StatusEvent("Retrieving movie data"));

        Movies movies = movieEndpoint.fetchMovies(98102);
    }
}
