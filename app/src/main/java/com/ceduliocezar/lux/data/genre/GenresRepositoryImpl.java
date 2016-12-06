package com.ceduliocezar.lux.data.genre;

import android.support.annotation.NonNull;

import java.util.List;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by cedulio on 25/11/16.
 */

public class GenresRepositoryImpl implements GenresRepository {

    private final GenresServiceApi genresServiceApi;

    @Inject
    public GenresRepositoryImpl(@NonNull GenresServiceApi genresServiceApi) {
        this.genresServiceApi = checkNotNull(genresServiceApi, "genres service api can not be null");
    }

    @Override
    public void getGenres(final LoadGenresCallback callback) {

        genresServiceApi.getGenres(new GenresServiceApi.GenresServiceCallback<List<Genre>>() {
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
