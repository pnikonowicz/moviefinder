package com.example.paulnikonowicz.zocdocapplication;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.paulnikonowicz.zocdocapplication.dao.Network;
import com.example.paulnikonowicz.zocdocapplication.event.DataRetrievedEvent;
import com.example.paulnikonowicz.zocdocapplication.event.InitializeEvent;
import com.example.paulnikonowicz.zocdocapplication.event.StatusEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends FragmentActivity {
    private Network network;

    public MainActivity() {
        this(new Network());
    }

    public MainActivity(Network network) {
        this.network = network;
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
        EventBus.getDefault().post(new InitializeEvent());
    }

    @Override
    protected void onStop() {
        super.onStop();

        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onInitialize(InitializeEvent event){
        EventBus.getDefault().post(new StatusEvent("Getting data from fandango"));
        
        String result = network.getSomething();
        EventBus.getDefault().post(new DataRetrievedEvent(result));
    }
}
