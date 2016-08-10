package com.ceduliocezar.lux.movies;

import com.ceduliocezar.lux.data.Movie;

import java.util.List;

/**
 * Created by TECBMCCS on 08/08/2016.
 */
public interface MoviesServiceApi {

    interface MoviesServiceCallback<T> {
        void onLoaded(T load, int currentPage, int maxPage);
        void onError(Throwable e);
    }

    void getMovies(int page, MoviesServiceCallback<List<Movie>> callback);
}