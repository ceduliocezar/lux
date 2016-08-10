package com.ceduliocezar.lux.data;

import android.content.Context;

import com.ceduliocezar.lux.R;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by cedulio on 05/06/16.
 */
public class MovieAPI {


    public static MovieRESTApi getInstance(Context context){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.MOVIE_DB_BASE_URL))
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        MovieRESTApi service = retrofit.create(MovieRESTApi.class);

        return service;
    }


}
