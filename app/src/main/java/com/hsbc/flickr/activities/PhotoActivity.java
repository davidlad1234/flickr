package com.hsbc.flickr.activities;

import android.support.v7.app.AppCompatActivity;

public abstract class PhotoActivity extends AppCompatActivity {

    public static final int STATUS_STARTED = 1;
    public static final int STATUS_RESUMED = 2;
    public static final int STATUS_PAUSED = 3;
    public static final int STATUS_STOPPED = 4;
    protected int currentStatus;

    protected void onStart() {
        super.onStart();
        currentStatus = STATUS_STARTED;
    }

    protected void onPause() {
        super.onPause();
        currentStatus = STATUS_PAUSED;
    }

    protected void onResume() {
        super.onResume();
        currentStatus = STATUS_RESUMED;
    }

    protected void onStop() {
        super.onStop();
        currentStatus = STATUS_STOPPED;
    }


    public abstract void setAppBarTitle(String artist);
}
