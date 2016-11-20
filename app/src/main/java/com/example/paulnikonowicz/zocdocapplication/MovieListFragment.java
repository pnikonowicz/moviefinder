package com.example.paulnikonowicz.zocdocapplication;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.paulnikonowicz.zocdocapplication.adapter.MovieListAdapter;
import com.example.paulnikonowicz.zocdocapplication.dao.FetchMoviesService;
import com.example.paulnikonowicz.zocdocapplication.dao.Movie;
import com.example.paulnikonowicz.zocdocapplication.event.CriticalErrorEvent;
import com.example.paulnikonowicz.zocdocapplication.event.MovieDataRequest;
import com.example.paulnikonowicz.zocdocapplication.event.MovieDataResponse;
import com.example.paulnikonowicz.zocdocapplication.event.StatusEvent;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import static android.R.attr.value;


public class MovieListFragment extends ListFragment implements AdapterView.OnItemClickListener {
    private FetchMoviesService fetchMoviesService;
    private Gson gson;
    private ListView listView;

    public MovieListFragment() {
        this(new FetchMoviesService(), new Gson());
    }

    public MovieListFragment(FetchMoviesService fetchMoviesService, Gson gson) {
        this.fetchMoviesService = fetchMoviesService;
        this.gson = gson;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.movielist_fragment, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();

        listView = (ListView) getView();
        listView.setOnItemClickListener(this);

        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new MovieDataRequest(98102));
    }

    @Override
    public void onStop() {
        EventBus.getDefault().unregister(this);

        listView.setOnItemClickListener(null);
        listView = null;

        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void loadBasicListData(MovieDataResponse event){
        EventBus.getDefault().post(new StatusEvent("Found " + event.movieCount() + " movies!"));

        listView.setAdapter(new MovieListAdapter(getActivity(), R.id.listview, event.getMovies()));

        getView().setVisibility(View.VISIBLE);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void getMoviesFromNetwork(MovieDataRequest event) {
        EventBus.getDefault().post(new StatusEvent("Retrieving movie data for zip: " + event.zip));

        ArrayList<Movie> movies = null;
        try {
            movies = fetchMoviesService.fetchMovies(event.zip);
            EventBus.getDefault().post(new MovieDataResponse(movies));
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
