package com.ceduliocezar.lux.poster;

import android.content.Context;
import android.widget.ImageView;

import com.ceduliocezar.lux.data.poster.PosterHandler;
import com.squareup.picasso.Picasso;

/**
 * Created by ceduliocezar on 24/11/16.
 */

public class AssetPosterHandler implements PosterHandler{
    @Override
    public void loadImage(String imagePath, ImageView target, Context context) {
        Picasso.with(context)
                .load(imagePath)
                .into(target);
    }
}
