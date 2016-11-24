package com.ceduliocezar.lux.data.poster;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by ceduliocezar on 24/11/16.
 */

public interface PosterHandler {

    void loadImage(String imagePath, ImageView target, Context context);

}
