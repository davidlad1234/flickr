package com.hsbc.flickr.activities;

import android.content.Context;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;

import com.hsbc.flickr.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.hsbc.flickr.activities.PhotoActivity.STATUS_STOPPED;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PhotoListActivityTest {

    PhotoListActivity pla = null;

    @Rule
    public ActivityTestRule<PhotoListActivity> mActivity = new ActivityTestRule(PhotoListActivity.class);

    @Before
    public void setUp() throws Exception {
        pla = mActivity.getActivity();
    }

    @After
    public void tearDown() throws Exception {
        pla = null;
    }

    @Test
    public void onCreate() {
        assertNotNull(pla);
        assertNotNull(pla.toolbar);
        assertNotNull(pla.fab);
        assertNotNull(pla.photoListPresenter);
    }


    @Test
    public void getContext() {
        Object obj = pla.getContext();
        assertTrue(obj instanceof Context);
    }

    @Test
    @UiThreadTest
    public void showPhotos() {

        pla.showPhotos(TestUtils.getPhotos());
        assertTrue(pla.recyclerView.getAdapter().getItemCount() == 1);
    }

    @Test
    public void onClickFab() {
        boolean b = pla.fab.callOnClick();
        assertTrue(b);
    }

    @Test
    @UiThreadTest
    public void onStop() {
        pla.finish();
        assertEquals(STATUS_STOPPED, pla.currentStatus);

    }

    @Test
    @UiThreadTest
    public void setAppBarTitle() {
        assertEquals("Flickr", pla.getTitle());
    }
}
