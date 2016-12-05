package com.ceduliocezar.lux.data.movie;

import java.util.List;

import javax.inject.Inject;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Created by ceduliocezar on 24/11/16.
 */

public class MoviesRepositoryImpl implements MoviesRepository {


    private final MoviesServiceApi moviesServiceApi;

    @Inject
    public MoviesRepositoryImpl(MoviesServiceApi moviesServiceApi) {
        this.moviesServiceApi = moviesServiceApi;
    }


    @Override
    public void getMovie(int movieId, final LoadMovieCallback callback) {
        moviesServiceApi.getMovie(movieId, new MoviesServiceApi.MovieServiceCallback() {
            @Override
            public void onLoadMovie(Movie movie) {
                callback.onLoadMovie(movie);
            }

            @Override
            public void errorLoadingMovie(Throwable t) {
                callback.onErrorLoadingMovie(t);
            }
        });
    }

    @Override
    public void getMovies(final int pageIndex, final LoadMoviesCallback callback) {

        checkNotNull(callback);

        moviesServiceApi.getMovies(pageIndex, new MoviesServiceApi.MoviesServiceCallback<List<Movie>>() {
            @Override
            public void onLoaded(List<Movie> load, int currentPage, int maxPage) {
                callback.onLoadMovies(load, currentPage, maxPage);
            }

            @Override
            public void onError(Throwable e) {
                callback.onErrorLoadingMovies(e);
            }
        });
    }

}
