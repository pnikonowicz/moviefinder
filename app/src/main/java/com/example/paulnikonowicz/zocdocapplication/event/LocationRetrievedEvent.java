package com.example.paulnikonowicz.zocdocapplication.event;

public class LocationRetrievedEvent {
    public String latitude;
    public String longitude;

    public LocationRetrievedEvent(String latitude, String longitude) {
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
