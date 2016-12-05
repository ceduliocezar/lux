package com.ceduliocezar.lux.data.backdrop;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by cedulio on 27/11/16.
 */

public class AssetBackdropImageProvider implements BackdropImageProvider {
    @Override
    public void load(ImageView target, Backdrop backdrop, Context context) {
        Picasso.with(context)
                .load(backdrop.getFilePath())
                .into(target);
    }
}
