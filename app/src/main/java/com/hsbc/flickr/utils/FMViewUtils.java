package com.hsbc.flickr.utils;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

;

public class FMViewUtils {
    public static void loadImage(Context context, ImageView imageView,  String url) {

        if (url != null && !url.isEmpty()) {
            Picasso picasso = Picasso.with(context);
            picasso.load(url).centerCrop().fit().into(imageView);
        }
    }


}
