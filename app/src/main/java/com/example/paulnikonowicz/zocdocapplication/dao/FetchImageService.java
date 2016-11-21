package com.example.paulnikonowicz.zocdocapplication.dao;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

public class FetchImageService {
    public Bitmap fetchImage(String imageLink) throws IOException {
        //I am intentionally not resizing the image here
        //because i am focusing on lean code for this interview application
        java.net.URL url = new java.net.URL(imageLink);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setDoInput(true);
        connection.connect();
        InputStream input = connection.getInputStream();
        Bitmap myBitmap = BitmapFactory.decodeStream(input);
        return myBitmap;
    }
}
