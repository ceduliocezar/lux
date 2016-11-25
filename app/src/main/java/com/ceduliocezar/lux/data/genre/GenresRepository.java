package com.ceduliocezar.lux.data.genre;

import java.util.List;

/**
 * Created by cedulio on 03/08/2016.
 */
public interface GenresRepository {

    interface LoadGenresCallback{
        void onLoadGenres(List<Genre> genres);
        void onError(Throwable t);
    }

    void getGenres(LoadGenresCallback callback);
}
