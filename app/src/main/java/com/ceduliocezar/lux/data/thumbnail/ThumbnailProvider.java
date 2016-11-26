package com.ceduliocezar.lux.data.thumbnail;

import android.content.Context;
import android.widget.ImageView;

import com.ceduliocezar.lux.data.video.Video;

/**
 * Created by cedulio on 25/11/16.
 */

public interface ThumbnailProvider {

    void loadThumbnail(ImageView target, Video video, Context context);
}
