package com.hsbc.flickr.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hsbc.flickr.R;
import com.hsbc.flickr.activities.PhotoDetailActivity;
import com.hsbc.flickr.activities.PhotoListActivity;
import com.hsbc.flickr.fragments.PhotoDetailFragment;
import com.hsbc.flickr.pojos.Photo;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PhotosViewHolder extends RecyclerView.ViewHolder {
    public static final String TAG = PhotosViewHolder.class.getSimpleName();

    @BindView(R.id.content_area)
    public LinearLayout contentView;
    @BindView(R.id.id_image)
    ImageView imageView;
    @BindView(R.id.title)
    TextView titleView;
    @BindView(R.id.author)
    TextView authorView;

    @BindView(R.id.url)
    TextView urlView;

    private Photo[] photos;
    private boolean mTwoPane;
    private PhotoListActivity parentActivity;

    PhotosViewHolder(PhotoListActivity parentActivity, View view, Photo[] photos, boolean mTwoPane) {
        super(view);
        ButterKnife.bind(this, view);
        this.photos = photos;
        this.mTwoPane = mTwoPane;
        this.parentActivity = parentActivity;

        Log.d(TAG,"Initialised view holder");
    }

    @OnClick({R.id.id_image, R.id.content_area})
    public void onClickViews(View view) {
        int position = (int) view.getTag();
        Photo photo = photos[position];
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(PhotoDetailFragment.ARG_PHOTO, photo);
            arguments.putString(PhotoDetailFragment.ARG_TITLE, photo.getTitle());
            arguments.putString(PhotoDetailFragment.ARG_AUTHOR, photo.getAuthor());

            PhotoDetailFragment fragment = new PhotoDetailFragment();
            fragment.setArguments(arguments);
            parentActivity.getSupportFragmentManager().beginTransaction()
                    .replace(R.id.album_detail_container, fragment)
                    .commit();
        } else {

            Intent intent = new Intent(parentActivity, PhotoDetailActivity.class);
            intent.putExtra(PhotoDetailFragment.ARG_PHOTO, photo);

            intent.putExtra(PhotoDetailFragment.ARG_TITLE, photo.getTitle());
            intent.putExtra(PhotoDetailFragment.ARG_AUTHOR, photo.getAuthor());
            parentActivity.startActivity(intent);
        }
    }
}

