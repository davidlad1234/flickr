package com.hsbc.flickr.fragments;

import android.widget.TextView;

import com.android21buttons.fragmenttestrule.FragmentTestRule;
import com.hsbc.flickr.R;
import com.hsbc.flickr.activities.PhotoDetailActivity;
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

//TODO Time limit allocated for task expired
public class PhotoDetailFragmentTest {

    private PhotoDetailFragment pdf;
    @Rule
    public FragmentTestRule<PhotoDetailActivity, PhotoDetailFragment> fragmentTestRule =
            new FragmentTestRule(PhotoDetailActivity.class, PhotoDetailFragment.class);

    @Before
    public void setUp() throws Exception {
        pdf = fragmentTestRule.getFragment();
        pdf.getArguments().putParcelable(ARG_PHOTO,TestUtils.getPhotos()[0]);
        pdf.getArguments().putString(ARG_AUTHOR,TestUtils.getPhotos()[0].getAuthor());
        pdf.getArguments().putString(ARG_TITLE,TestUtils.getPhotos()[0].getTitle());


    }

    @After
    public void tearDown() throws Exception {
        pdf = null;
    }

    @Test
    public void onCreate() {
        assertEquals("TITLE", pdf.getArguments().get(PhotoDetailFragment.ARG_TITLE));
        assertEquals("AUTHOR", pdf.getArguments().get(PhotoDetailFragment.ARG_AUTHOR));
    }

    @Test
    public void onCreateView() {
        assertNotNull(pdf.getView());
    }

    @Test
    public void onStart() {
        TextView tview = pdf.getView().findViewById(R.id.title);
        assertEquals(TestUtils.getPhotos()[0].getTitle(), tview.getText());
    }

}