package com.ceduliocezar.lux.data.movie;

import java.util.List;

/**
 * Created by cedulio on 08/08/2016.
 */
public interface MoviesRepository {


    interface LoadMoviesCallback {
        void onLoadMovies(List<Movie> movies, int currentPage, int maxPage);

        void onErrorLoadingMovies(Throwable e);
    }

    interface LoadMovieCallback {
        void onLoadMovie(Movie movie);

        void onErrorLoadingMovie(Throwable t);
    }

    void getMovie(int movieId, LoadMovieCallback callback);

    void getMovies(int pageIndex, LoadMoviesCallback callback);

}
