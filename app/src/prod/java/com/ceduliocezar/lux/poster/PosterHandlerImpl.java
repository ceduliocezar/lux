package com.ceduliocezar.lux.poster;

import android.content.Context;
import android.widget.ImageView;

import com.ceduliocezar.lux.data.poster.PosterHandler;
import com.squareup.picasso.Picasso;

/**
 * Created by ceduliocezar on 24/11/16.
 */

public class PosterHandlerImpl implements PosterHandler {

    public static final String BASE_PORSTER_URL = "http://image.tmdb.org/t/p/w500";

    @Override
    public void loadImage(String posterPath, ImageView target, Context context) {
        Picasso.with(context)
                .load(PosterHandlerImpl.BASE_PORSTER_URL + posterPath)
                .into(target);
    }

}
