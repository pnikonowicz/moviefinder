package com.example.paulnikonowicz.zocdocapplication.event;

public class CriticalErrorEvent {
    public Exception exception;

    public CriticalErrorEvent(Exception e) {
        this.exception = e;
    }
}
