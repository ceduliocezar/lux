package com.ceduliocezar.lux.data;

import android.content.Context;
import android.support.annotation.NonNull;

import com.ceduliocezar.lux.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cedulio on 05/06/16.
 */
public class MovieAPIFactory {


    public static MovieDBRESTApi create(@NonNull Context context) {

        checkNotNull(context, "context can not be null");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.MOVIE_DB_BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieDBRESTApi service = retrofit.create(MovieDBRESTApi.class);

        return service;
    }


}
