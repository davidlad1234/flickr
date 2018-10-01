package com.hsbc.flickr.activities;

import android.content.Intent;
import android.support.test.annotation.UiThreadTest;
import android.support.test.rule.ActivityTestRule;

import com.hsbc.flickr.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static com.hsbc.flickr.fragments.PhotoDetailFragment.ARG_AUTHOR;
import static com.hsbc.flickr.fragments.PhotoDetailFragment.ARG_PHOTO;
import static com.hsbc.flickr.fragments.PhotoDetailFragment.ARG_TITLE;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PhotoDetailActivityTest {

    private PhotoDetailActivity pda;

    @Rule
    public ActivityTestRule<PhotoDetailActivity> mActivity = new ActivityTestRule(PhotoDetailActivity.class){
        @Override
        protected Intent getActivityIntent() {
           Intent intent = new Intent();
           intent.putExtra(ARG_PHOTO,TestUtils.getPhotos()[0]);
            intent.putExtra(ARG_AUTHOR,TestUtils.getPhotos()[0].getAuthor());
            intent.putExtra(ARG_TITLE,TestUtils.getPhotos()[0].getTitle());
            return intent;
        }

    };

    @Before
    public void setUp() {
        pda = mActivity.getActivity();
    }

    @After
    public void tearDown() {
        pda = null;
    }

    @Test
    public void onCreate() {
        assertNotNull(pda.fab);
        assertNotNull(pda.appBarLayout);
        assertNotNull(pda.toolbar);

    }

    @Test
    public void onClickFab() {
        boolean b = pda.fab.callOnClick();
        assertTrue(b);
    }

    @Test
    @UiThreadTest
    public void setAppBarTitle() {
        String someTitle = "some title";
        pda.setAppBarTitle(someTitle);
        assertEquals(someTitle, pda.appBarLayout.getTitle());
    }
}