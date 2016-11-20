package com.example.paulnikonowicz.zocdocapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulnikonowicz.zocdocapplication.dao.FetchMoviesService;
import com.example.paulnikonowicz.zocdocapplication.event.CriticalErrorEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.io.PrintStream;
import java.util.Arrays;

public class MainActivity extends FragmentActivity {
    private FetchMoviesService fetchMoviesService;

    public MainActivity() {
        this(new FetchMoviesService());
    }

    public MainActivity(FetchMoviesService fetchMoviesService) {
        this.fetchMoviesService = fetchMoviesService;
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
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onCriticalError(CriticalErrorEvent e) {
        View progressView = findViewById(R.id.progress);
        View listView = findViewById(R.id.list);
        TextView textView = (TextView) findViewById(R.id.errorText);
        String exceptionString = Arrays.toString(e.exception.getStackTrace());
        textView.setText(exceptionString);

        textView.setVisibility(View.VISIBLE);
        listView.setVisibility(View.GONE);
        progressView.setVisibility(View.GONE);
    }
}
