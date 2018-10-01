package com.hsbc.flickr.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hsbc.flickr.R;
import com.hsbc.flickr.pojos.Photo;
import com.hsbc.flickr.presenters.PhotoListPresenter;
import com.hsbc.flickr.utils.PhotoRecyclerViewAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * An activity representing a list of Albums. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of Albums, which when touched,
 * lead to a {@link PhotoDetailActivity} representing
 * AlbumMatches songs. On tablets, the activity presents the list of Albums and
 * AlbumMatches songs side-by-side using two vertical panes.
 */
public class PhotoListActivity extends PhotoActivity implements LocalCallback {

    private static final String TAG = PhotoListActivity.class.getClass().getSimpleName();
    public static final String PHOTO = "painting";

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean isTwoPane;


    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.fab)
    FloatingActionButton fab;
    @BindView(R.id.album_detail_container)
    @Nullable
    LinearLayout detailContainer;

    @BindView(R.id.album_list)
    RecyclerView recyclerView;

    @BindView(R.id.toolbar_layout)
    @Nullable
    CollapsingToolbarLayout appBarLayout;

    //@TODO inject using Dagger
    PhotoListPresenter photoListPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);
        ButterKnife.bind(this);
        photoListPresenter = new PhotoListPresenter();
        setSupportActionBar(toolbar);
        toolbar.setTitle(getTitle());


        if (detailContainer != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            isTwoPane = true;
        }
        assert recyclerView != null;
        //
    }

    @Override
    public void onStart() {
        super.onStart();
        photoListPresenter.fetchPhotos(this);
    }

    @OnClick(R.id.fab)
    public void onClickFab(View view) {
        Snackbar.make(view, "Click a picture to view details ", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }

    @Override
    public Context getContext() {
        return this;
    }

    public void showPhotos(Photo[] photos) {

        recyclerView.setAdapter(new PhotoRecyclerViewAdapter(PhotoListActivity.this, photos, isTwoPane));


    }

    @Override
    public void showErrorMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showErrorMessage(int server_error) {
        Toast.makeText(this, getString(R.string.data_error), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStop() {
        super.onStop();
        photoListPresenter.onStop();
    }

    public void setAppBarTitle(String artist) {
        if (appBarLayout != null) {
            appBarLayout.setTitle(artist);
            setTitle(artist);
        }
    }


}
