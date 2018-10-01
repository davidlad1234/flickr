package com.hsbc.flickr.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.hsbc.flickr.R;
import com.hsbc.flickr.activities.PhotoActivity;
import com.hsbc.flickr.activities.PhotoDetailActivity;
import com.hsbc.flickr.activities.PhotoListActivity;
import com.hsbc.flickr.pojos.Media;
import com.hsbc.flickr.pojos.Photo;
import com.hsbc.flickr.utils.FMViewUtils;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A fragment representing a single AlbumMatches detail screen.
 * This fragment is either contained in a {@link PhotoListActivity}
 * in two-pane mode (on tablets) or a {@link PhotoDetailActivity}
 * on handsets.
 */
public class PhotoDetailFragment extends Fragment {
    private static final String TAG = PhotoDetailFragment.class.getClass().getSimpleName();

    public static final String ARG_TITLE = "title";
    public static final String ARG_AUTHOR = "author";


    public static final String PHOTO_DETAIL_FRAGMENT = "PhotoDetailFragment";
    public static final String ARG_PHOTO = "photo";

    /**
     * The  artist/album this fragment is presenting.
     */


    private String title;
    private String author;

    private Photo photo;

    @BindView(R.id.id_image)
    ImageView imageView;

    @BindView(R.id.url)
    TextView urlView;

    @BindView(R.id.description)
    TextView descriptionView;


    @BindView(R.id.date_taken)
    TextView dateTakenView;


    @BindView(R.id.publish_date)
    TextView publishedDateView;

    @BindView(R.id.author)
    TextView authorView;


    @BindView(R.id.author_id)
    TextView authorIdView;


    @BindView(R.id.title)
    TextView titleView;

    @BindView(R.id.tags)
    TextView tagsView;


    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public PhotoDetailFragment() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_TITLE)) {
            title = getArguments().getString(ARG_TITLE);
            author = getArguments().getString(ARG_AUTHOR);
            photo = getArguments().getParcelable(ARG_PHOTO);
            PhotoActivity activity = (PhotoActivity) this.getActivity();
            if (activity != null) {
                activity.setAppBarTitle(title);
            }

        }
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.photo_detail, container, false);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @Override
    public void onStart() {
        super.onStart();
        writeScreen();
    }


    /**
     * Writes to the screen of the fragment
     */
    private void writeScreen() {
        PhotoActivity activity = (PhotoActivity) getActivity();
        activity.setAppBarTitle(title + " - " + author);

        titleView.setText(photo.getTitle());
        authorView.setText(photo.getAuthor());

        urlView.setText(Html.fromHtml(photo.getLink()));
        descriptionView.setText(Html.fromHtml(photo.getDescription()));
        tagsView.setText(photo.getTags());
        authorIdView.setText(photo.getAuthorId());

        writeImage();
        writeDate(photo.getPublished(), publishedDateView);
        writeDate(photo.getDateTaken(), dateTakenView);


    }

    private void writeImage() {
        Media media = photo.getMedia();
        Log.d(TAG,"The media object is: "+media);
        if (media != null) {
            String imageString = media.getM();
            if (imageString != null) {
                FMViewUtils.loadImage(getContext(), imageView, imageString);
            }
        }
    }

    private void writeDate(Date published, TextView publishedDateView) {
        Date publishedDate = published;
        if (publishedDate != null) {
            publishedDateView.setText(publishedDate.toString());
        }
    }


}
