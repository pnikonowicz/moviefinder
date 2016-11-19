package com.example.paulnikonowicz.zocdocapplication;

import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.widget.Toast;

import com.example.paulnikonowicz.zocdocapplication.dao.Network;
import com.example.paulnikonowicz.zocdocapplication.event.DataRetrievedEvent;
import com.example.paulnikonowicz.zocdocapplication.event.InitializeEvent;
import com.example.paulnikonowicz.zocdocapplication.event.LocationRetrievedEvent;
import com.example.paulnikonowicz.zocdocapplication.event.StatusEvent;
import com.example.paulnikonowicz.zocdocapplication.lightweightservice.AndroidLocationService;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends FragmentActivity implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener {
    private Network network;
    private AndroidLocationService androidLocationService;
    private GoogleApiClient mGoogleApiClient;

    public MainActivity() {
        this(new Network(), new AndroidLocationService());
    }

    public MainActivity(Network network, AndroidLocationService androidLocationService) {
        this.network = network;
        this.androidLocationService = androidLocationService;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create an instance of GoogleAPIClient.
        if (mGoogleApiClient == null) {
            mGoogleApiClient = new GoogleApiClient.Builder(this)
                    .addConnectionCallbacks(this)
                    .addOnConnectionFailedListener(this)
                    .addApi(LocationServices.API)
                    .build();
        }
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
        EventBus.getDefault().register(this);
        EventBus.getDefault().post(new InitializeEvent());
    }

    @Override
    protected void onStop() {
        mGoogleApiClient.disconnect();
        EventBus.getDefault().unregister(this);
        super.onStop();
    }

    @Subscribe(threadMode = ThreadMode.BACKGROUND)
    public void onInitialize(LocationRetrievedEvent event) {
        EventBus.getDefault().post(new StatusEvent("Retrieving movie data"));

        network.getSomething();
        EventBus.getDefault().post(new DataRetrievedEvent(event.latitude + "|" + event.longitude));
    }

    @Override
    public void onConnected(@Nullable Bundle bundle) {
        if (ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "location perms not received. cannot continue", Toast.LENGTH_LONG);
            return;
        }

        EventBus.getDefault().post(new StatusEvent("Getting location"));
        Location mLastLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
        if (mLastLocation != null) {
            String latitude = String.valueOf(mLastLocation.getLatitude());
            String longitude = String.valueOf(mLastLocation.getLongitude());

            EventBus.getDefault().post(new LocationRetrievedEvent(latitude, longitude));
        }
    }

    @Override
    public void onConnectionSuspended(int i) {

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }
}
