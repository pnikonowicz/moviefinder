package com.example.paulnikonowicz.zocdocapplication.dao;

public class Network {
    public String getSomething() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return "TODO";
    }
}
