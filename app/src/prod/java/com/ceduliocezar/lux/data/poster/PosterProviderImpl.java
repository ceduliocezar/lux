package com.ceduliocezar.lux.data.poster;

import android.content.Context;
import android.widget.ImageView;

import com.ceduliocezar.lux.data.poster.PosterProvider;
import com.squareup.picasso.Picasso;

/**
 * Created by ceduliocezar on 24/11/16.
 */

public class PosterProviderImpl implements PosterProvider {

    public static final String BASE_PORSTER_URL = "http://image.tmdb.org/t/p/w500";

    @Override
    public void loadImage(String posterPath, ImageView target, Context context) {
        Picasso.with(context)
                .load(PosterProviderImpl.BASE_PORSTER_URL + posterPath)
                .into(target);
    }

}
