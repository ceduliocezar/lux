package com.ceduliocezar.lux.movie.list;

import com.ceduliocezar.lux.data.Movie;

import java.util.List;

/**
 * Created by cedulio on 08/08/2016.
 */
public interface MoviesRepository {

    interface LoadMoviesCallback {
        void onLoadMovies(List<Movie> movies, int currentPage, int maxPage);
        void onErrorLoadingMovies(Throwable e);
    }

    void getMovies(int pageIndex, LoadMoviesCallback callback);
}
