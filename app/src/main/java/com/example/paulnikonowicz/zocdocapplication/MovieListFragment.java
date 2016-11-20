package com.example.paulnikonowicz.zocdocapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import com.example.paulnikonowicz.zocdocapplication.adapter.MovieListAdapter;
import com.example.paulnikonowicz.zocdocapplication.dao.FetchMoviesService;
import com.example.paulnikonowicz.zocdocapplication.dao.Movie;
import com.example.paulnikonowicz.zocdocapplication.event.CriticalErrorEvent;
import com.example.paulnikonowicz.zocdocapplication.event.MovieDataRequest;
import com.example.paulnikonowicz.zocdocapplication.event.MovieDataResponse;
import com.example.paulnikonowicz.zocdocapplication.event.StatusEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


public class MovieListFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private FetchMoviesService fetchMoviesService;

    public MovieListFragment() {
        this(new FetchMoviesService());
    }

    public MovieListFragment(FetchMoviesService fetchMoviesService) {
        this.fetchMoviesService = fetchMoviesService;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movielist_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        getListView().setOnItemClickListener(this);

        EventBus.getDefault().register(this);

        MovieDataResponse movieDataResponse = EventBus.getDefault().getStickyEvent(MovieDataResponse.class);
        if(movieDataResponse == null) {
            EventBus.getDefault().post(new MovieDataRequest(11234));
        } else {
            loadBasicListData(movieDataResponse);
        }
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);

        getListView().setOnItemClickListener(null);

        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadBasicListData(MovieDataResponse event){
        EventBus.getDefault().post(new StatusEvent("Found " + event.movieCount() + " movies!"));
        setListAdapter(new MovieListAdapter(getActivity(), R.layout.movierow_layout, event.getMovies()));
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void getMoviesFromNetwork(MovieDataRequest event) {
        EventBus.getDefault().post(new StatusEvent("Retrieving movie data for zip: " + event.zip));

        ArrayList<Movie> movies = null;
        try {
            movies = fetchMoviesService.fetchMovies();
            EventBus.getDefault().postSticky(new MovieDataResponse(movies));
        } catch (Exception e) {
            EventBus.getDefault().postSticky(new CriticalErrorEvent(e));
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Movie m = (Movie) adapterView.getItemAtPosition(i);
        Intent intent = MovieDetailsActivity.createIntent(getContext(), m);
        startActivity(intent);
    }
}
