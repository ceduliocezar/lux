package com.ceduliocezar.lux.genre;

import android.content.Context;

import com.ceduliocezar.lux.data.genre.Genre;
import com.ceduliocezar.lux.data.genre.GenresRepository;
import com.ceduliocezar.lux.data.genre.GenresServiceApi;

import java.util.List;

/**
 * Created by TECBMCCS on 25/11/16.
 */

public class GenresRepositoryImpl implements GenresRepository {

    private final GenresServiceApiEndpoint serviceEndpoint;

    public GenresRepositoryImpl(Context context) {
        this.serviceEndpoint = new GenresServiceApiEndpoint(context);
    }

    @Override
    public void getGenres(final LoadGenresCallback callback) {

        serviceEndpoint.getGenres(new GenresServiceApi.GenresServiceCallback<List<Genre>>() {
            @Override
            public void onLoaded(List<Genre> load) {
                callback.onLoadGenres(load);
            }

            @Override
            public void onError(Throwable t) {
                callback.onError(t);
            }
        });
    }

}
