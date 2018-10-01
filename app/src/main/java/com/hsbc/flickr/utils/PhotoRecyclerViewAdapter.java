package com.hsbc.flickr.utils;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.hsbc.flickr.R;
import com.hsbc.flickr.activities.PhotoListActivity;
import com.hsbc.flickr.pojos.Media;
import com.hsbc.flickr.pojos.Photo;


public class PhotoRecyclerViewAdapter extends RecyclerView.Adapter<PhotosViewHolder> {

    public static final String TAG = PhotoRecyclerViewAdapter.class.getSimpleName();
    private final PhotoListActivity parentActivity;
    private final Photo[] photos;
    private final boolean mTwoPane;

    public PhotoRecyclerViewAdapter(PhotoListActivity parent,
                                    Photo[] photos, boolean twoPane) {
        this.parentActivity = parent;
        this.photos = photos;
        mTwoPane = twoPane;
        Log.d(TAG, "Initialised recycler view with hotos.size: ");
    }


    @NonNull
    @Override
    public PhotosViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.photo_list_content, viewGroup, false);
        Log.d(TAG, "creating view holder..................");
        return new PhotosViewHolder(parentActivity, view, photos, mTwoPane);

    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewHolder holder, int position) {
        Photo photo = photos[position];
        Log.d(TAG, "Binding view holder . ....."+photo);
        if (photo != null) {

            fetchImage(holder.imageView, photo);
            holder.imageView.setTag(position);
            holder.contentView.setTag(position);
            holder.titleView.setText(photo.getTitle());

            holder.authorView.setText(photo.getAuthor());

            holder.urlView.setText(Html.fromHtml(photo.getLink()));
            Log.d(TAG, "Bound view holder ...success..");

        }

    }


    private void fetchImage(ImageView imageView, Photo photo) {
        if (photo != null) {
            Media media = photo.getMedia();
            String imageLocation = null;
            if (media != null) {
                imageLocation = media.getM();
            }

            if (imageLocation != null) {

                FMViewUtils.loadImage(parentActivity, imageView, imageLocation);
            }
        } else {
            Log.d(TAG, "Photo image is not available...");
        }
    }

    @Override
    public int getItemCount() {
        return photos != null ? photos.length : 0;
    }

}
