package com.example.paulnikonowicz.zocdocapplication;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.paulnikonowicz.zocdocapplication.dao.FetchMoviesService;
import com.example.paulnikonowicz.zocdocapplication.dao.Movies;
import com.example.paulnikonowicz.zocdocapplication.event.CriticalErrorEvent;
import com.example.paulnikonowicz.zocdocapplication.event.MovieDataRequest;
import com.example.paulnikonowicz.zocdocapplication.event.MovieDataResponse;
import com.example.paulnikonowicz.zocdocapplication.event.StatusEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;


public class MovieListFragment extends Fragment {
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

        getView().setVisibility(View.GONE);

        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new MovieDataRequest(98102));
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadBasicListData(MovieDataResponse event){
        getView().setVisibility(View.VISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void getMovies(MovieDataRequest event) {
        EventBus.getDefault().post(new StatusEvent("Retrieving movie data for zip: " + event.zip));

        Movies movies = null;
        try {
            movies = fetchMoviesService.fetchMovies(event.zip);
            int movieCount = movies.getCount();
            EventBus.getDefault().post(new StatusEvent("Found " + movieCount + " movies!"));
            EventBus.getDefault().post(new MovieDataResponse(movies));
        } catch (Exception e) {
            EventBus.getDefault().post(new CriticalErrorEvent(e));
        }
    }
}
