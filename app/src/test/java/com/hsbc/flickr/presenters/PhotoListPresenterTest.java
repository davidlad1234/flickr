package com.hsbc.flickr.presenters;

import android.content.Context;

import com.hsbc.flickr.activities.LocalCallback;
import com.hsbc.flickr.pojos.Photo;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PhotoListPresenterTest {

    PhotoListPresenter pla = null;

    @Before
    public void setUp() throws Exception {
        pla = new PhotoListPresenter();
    }

    @After
    public void tearDown() throws Exception {
        pla = null;
    }

    @Test
    public void fetchPhotos() {
        pla.fetchPhotos(new LocalCallback() {
            @Override
            public Context getContext() {
                return null;
            }

            @Override
            public void showPhotos(Photo[] photos) {
                testPhotoExists(photos);
            }

            @Override
            public void showErrorMessage(String message) {
                testMessage1(message);

            }

            @Override
            public void showErrorMessage(int server_error) {
                testMessage2(server_error > 1);
            }
        });
        assertNotNull(pla);
    }

    private void testMessage2(boolean b) {
        assertTrue(b);
    }

    private void testMessage1(String message) {
        assertNotNull(message);
    }

    private void testPhotoExists(Photo[] photos) {
        assertNotNull(photos);
    }

    @Test
    public void onStop() {
        pla.onStop();
        assertTrue(pla.isStopped);
    }
}