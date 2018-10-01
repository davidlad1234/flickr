package com.hsbc.flickr.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.hsbc.flickr.R;
import com.hsbc.flickr.fragments.PhotoDetailFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * An activity representing a single AlbumMatches detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item songs are presented side-by-side with a list of items
 * in a {@link PhotoListActivity}.
 */
public class PhotoDetailActivity extends PhotoActivity {

    @BindView(R.id.detail_toolbar)
    Toolbar toolbar;

    @BindView(R.id.fab)
    FloatingActionButton fab;

    @BindView(R.id.toolbar_layout)
    @Nullable
    CollapsingToolbarLayout appBarLayout;

    String title = null;
    String author = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_detail);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        // Show the Up button in the action bar.
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // savedInstanceState is non-null when there is fragment state
        // saved from previous configurations of this activity
        // (e.g. when rotating the screen from portrait to landscape).
        // In this case, the fragment will automatically be re-added
        // to its container so we don't need to manually add it.
        // For more information, see the Fragments API guide at:
        //
        // http://developer.android.com/guide/components/fragments.html
        //
        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            Bundle arguments = new Bundle();
            title = getIntent().getStringExtra(PhotoDetailFragment.ARG_TITLE);
            author = getIntent().getStringExtra(PhotoDetailFragment.ARG_AUTHOR);

            arguments.putParcelable(PhotoDetailFragment.ARG_PHOTO,
                    getIntent().getParcelableExtra(PhotoDetailFragment.ARG_PHOTO));

            arguments.putString(PhotoDetailFragment.ARG_TITLE, title);

            arguments.putString(PhotoDetailFragment.ARG_AUTHOR, author);

            PhotoDetailFragment fragment = new PhotoDetailFragment();
            fragment.setArguments(arguments);
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.album_detail_container, fragment)
                    .commit();
        }
    }

    @OnClick(R.id.fab)
    public void onClickFab(View view) {
        Snackbar.make(view, title + " - "+ author, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            // This ID represents the Home or Up button. In the case of this
            // activity, the Up button is shown. For
            // more songs, see the Navigation pattern on Android Design:
            //
            // http://developer.android.com/design/patterns/navigation.html#up-vs-back
            //
            navigateUpTo(new Intent(this, PhotoListActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setAppBarTitle(String artist) {
        if (appBarLayout != null) {
            appBarLayout.setTitle(artist);
            setTitle(artist);
        }
    }
}
