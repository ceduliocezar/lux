package com.ceduliocezar.lux.data.backdrop;

import android.content.Context;
import android.widget.ImageView;

import com.ceduliocezar.lux.data.backdrop.Backdrop;
import com.ceduliocezar.lux.data.backdrop.BackdropImageProvider;
import com.squareup.picasso.Picasso;

/**
 * Created by cedulio on 27/11/16.
 */

public class BackdropImageProviderImpl implements BackdropImageProvider {

    public static final String BASE_PORSTER_URL = "http://image.tmdb.org/t/p/w500/%s";

    @Override
    public void load(ImageView target, Backdrop backdrop, Context context) {
        Picasso.with(context)
                .load(String.format(BASE_PORSTER_URL, backdrop.getFilePath()))
                .into(target);
    }
}