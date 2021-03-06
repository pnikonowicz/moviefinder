package com.example.paulnikonowicz.zocdocapplication.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.paulnikonowicz.zocdocapplication.R;
import com.example.paulnikonowicz.zocdocapplication.dao.Movie;

public class MovieListAdapter extends ArrayAdapter<Movie> {

    public MovieListAdapter(Context context, int resource, Movie[] objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, View rowView, ViewGroup parent) {
        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rowView = inflater.inflate(R.layout.movierow_layout, parent, false);
        }

        Movie movie = getItem(position);

        TextView title = (TextView) rowView.findViewById(R.id.title);
        TextView rating = (TextView) rowView.findViewById(R.id.rating);

        title.setText(movie.title);
        rating.setText(movie.rating);

        return rowView;
    }
}
