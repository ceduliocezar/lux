package com.ceduliocezar.lux.data.genre;

import java.util.List;

/**
 * Created by cedulio on 25/11/16.
 */

public class GenresRepositoryImpl implements GenresRepository {

    private final GenresServiceApi genresServiceApi;

    public GenresRepositoryImpl(GenresServiceApi genresServiceApi) {
        this.genresServiceApi = genresServiceApi;
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
