package com.hsbc.flickr.activities;

import android.content.Context;

import com.hsbc.flickr.pojos.Photo;

public interface LocalCallback {
    Context getContext();

    void showPhotos(Photo[] photos);

    void showErrorMessage(String message);

    void showErrorMessage(int server_error);
}
