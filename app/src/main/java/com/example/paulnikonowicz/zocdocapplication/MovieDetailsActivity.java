package com.example.paulnikonowicz.zocdocapplication;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulnikonowicz.zocdocapplication.dao.FetchImageService;
import com.example.paulnikonowicz.zocdocapplication.dao.Movie;
import com.example.paulnikonowicz.zocdocapplication.event.CriticalErrorEvent;
import com.example.paulnikonowicz.zocdocapplication.event.ImageRequest;
import com.example.paulnikonowicz.zocdocapplication.event.ImageResponse;
import com.example.paulnikonowicz.zocdocapplication.event.MovieDataResponse;
import com.google.android.gms.games.event.Event;
import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Arrays;

public class MovieDetailsActivity extends FragmentActivity implements View.OnClickListener {
    private Movie movie;
    private FetchImageService fetchImageService;
    private Button button;

    public MovieDetailsActivity() {
        this(new FetchImageService());
    }

    public MovieDetailsActivity(FetchImageService fetchImageService) {
        this.fetchImageService = fetchImageService;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moviedetails);
    }

    @Override
    protected void onStart() {
        movie = fromIntent(getIntent());
        TextView title = (TextView) findViewById(R.id.title);
        TextView description = (TextView) findViewById(R.id.description);
        button = (Button) findViewById(R.id.button);

        title.setText(movie.getTitle());
        description.setText(movie.getDescription());
        button.setOnClickListener(this);

        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new ImageRequest(movie.getImageLink()));
        super.onStart();
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);

        movie = null;
        button.setOnClickListener(null);
        button = null;

        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void setImage(ImageResponse response){
        ImageView imageView = (ImageView) findViewById(R.id.background);
        imageView.setImageBitmap(response.image);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void getImageFromNetwork(ImageRequest request) {
        try {
            Bitmap image = fetchImageService.fetchImage(request.imageLink);
            EventBus.getDefault().post(new ImageResponse(image));
        } catch (Exception e) {
            EventBus.getDefault().post(new CriticalErrorEvent(e));
        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCriticalError(CriticalErrorEvent e) {
        Toast.makeText(this, "Could not download image", Toast.LENGTH_LONG);
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

    @Override
    public void onClick(View view) {
        Intent viewIntent =
                new Intent("android.intent.action.VIEW",
                        Uri.parse("http://www.zocdoc.com/"));
        startActivity(viewIntent);
    }
}
