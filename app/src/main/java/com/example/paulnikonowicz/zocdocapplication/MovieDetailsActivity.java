package com.example.paulnikonowicz.zocdocapplication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.widget.TextView;

import com.example.paulnikonowicz.zocdocapplication.dao.Movie;
import com.google.gson.Gson;

public class MovieDetailsActivity extends FragmentActivity{
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        movie = fromIntent(getIntent());
        TextView title = (TextView) findViewById(R.id.title);
        TextView description = (TextView) findViewById(R.id.description);

        super.onStart();
    }

    @Override
    protected void onStop() {
        movie = null;
        super.onStop();
    }

    public static Intent createIntent(Context context, Movie m) {
        Gson gson = new Gson();
        String movieJson = gson.toJson(m);
        Intent intent = new Intent(context, MovieDetailsActivity.class);
        intent.putExtra("movie", movieJson);
        return intent;
    }

    public static Movie fromIntent(Intent intent) {
        String json = intent.getStringExtra("movie");
        Gson gson = new Gson();
        Movie m = gson.fromJson(json, Movie.class);
        return m;
    }
}
