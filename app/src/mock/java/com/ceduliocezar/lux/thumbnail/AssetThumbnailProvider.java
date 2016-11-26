package com.ceduliocezar.lux.thumbnail;

import android.content.Context;
import android.widget.ImageView;

import com.ceduliocezar.lux.data.thumbnail.ThumbnailProvider;
import com.ceduliocezar.lux.data.video.Video;
import com.squareup.picasso.Picasso;

/**
 * Created by cedulio on 25/11/16.
 */
public class AssetThumbnailProvider implements ThumbnailProvider {
    @Override
    public void loadThumbnail(ImageView target, Video video, Context context) {
        Picasso.with(context)
                .load("file:///android_asset/poster_test.jpg")
                .into(target);
    }
}
